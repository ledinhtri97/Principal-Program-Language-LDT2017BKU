package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._


//import scala.collection.JavaConversions._ 

/*if use this import we dont need use asScala method to cover Java to Scala method */

class ASTGeneration extends MCBaseVisitor[Any] {

//flatten List nested list => only a list
	def flatten(ls: List[Any]): List[Any] = ls flatMap {
    	case i: List[_] => flatten(i)
    	case e => List(e) ///using ::: to flatten
  	}

//Program -> declaration
 	override def visitProgram(ctx: ProgramContext) = {
 		val listDecl = flatten(
 			ctx.declaration()
 				.asScala.map(decl => decl.accept(this)).toList) 		//can use map(visit)
 					.asInstanceOf[List[Decl]]
		Program(listDecl)
	}

//declaration -> variable_decl | function_decl
	override def visitDeclaration(ctx: DeclarationContext) =
		ctx.getChild(0).accept(this)

//variable_decl -> many_variable
	override def visitVariable_decl(ctx: Variable_declContext) = {
		val varType = ctx.primitive_type().accept(this).asInstanceOf[Type]
		val varListId = ctx.many_variable().accept(this).asInstanceOf[List[List[String]]]
		val varDeclList = varListId.map(i => VarDecl(Id(i(0)), if(i(1)=="noArray") varType else ArrayType(IntLiteral(i(1).toInt), varType)))
		varDeclList
	}
		
//many_variable -> variable
	override def visitMany_variable(ctx: Many_variableContext) = {
		ctx.variable().asScala.map(x => x.accept(this)).toList
	}

//variable -> ID LSB INTLIT RSB | ID
	override def visitVariable(ctx: VariableContext) = 
		if(ctx.getChildCount()==4) List(ctx.ID.getText(), ctx.INTLIT.getText())
		else List(ctx.ID.getText(), "noArray")

//array_point_type
	override def visitArray_point_type(ctx: Array_point_typeContext) =
		ctx.getChild(0).accept(this)

//element_of_array
	override def visitElement_of_array(ctx: Element_of_arrayContext) = {
		val arr = Id(ctx.ID(0).getText()).asInstanceOf[Expr]
		val idx = 
			if(ctx.INTLIT != null) IntLiteral(ctx.getChild(2).getText.toInt).asInstanceOf[Expr]
			else Id(ctx.ID(1).getText()).asInstanceOf[Expr]
		if (ctx.getChildCount() == 4) 
			ArrayCell(arr, idx)
		else ctx.getChild(0).accept(this)
	}

//input_parameter
	override def visitInput_parameter(ctx: Input_parameterContext) =
		ArrayPointerType(ctx.getChild(0).accept(this).asInstanceOf[Type])

//output_parameter
	override def visitOutput_parameter(ctx: Output_parameterContext) =
		ArrayPointerType(ctx.getChild(0).accept(this).asInstanceOf[Type])

//array_type
	override def visitArray_type(ctx: Array_typeContext) =
			ArrayType(	
				IntLiteral(ctx.INTLIT.getText.toInt), 
				ctx.getChild(0).accept(this).asInstanceOf[Type])

//function_decl -> typeMC ID LB parameter_list? RB block_stmt 
	override def visitFunction_decl(ctx: Function_declContext) = {
		val name = Id(ctx.ID.getText())
		val listPara = 
			if (ctx.parameter_list() != null) ctx.parameter_list().accept(this).asInstanceOf[List[VarDecl]]
			else List()
		val returnType = ctx.getChild(0).accept(this).asInstanceOf[Type]
		val body = ctx.block_stmt().accept(this).asInstanceOf[Stmt]
		FuncDecl(name, listPara, returnType, body)
	}

//parameter_list -> parameter_decl
	override def visitParameter_list(ctx: Parameter_listContext) = {
		flatten(ctx.parameter_decl().asScala.map(x => x.accept(this)).toList)
	}
		
//parameter_decl
	override def visitParameter_decl(ctx: Parameter_declContext) = {
		val varType = ctx.primitive_type().accept(this).asInstanceOf[Type]
		val varId = Id(ctx.ID.getText())
		if(ctx.getChildCount() == 4) VarDecl(varId, ArrayPointerType(varType))
		else VarDecl(varId, varType)
	}

//typeMC
	override def visitTypeMC(ctx: TypeMCContext) = 
		if (ctx.VOIDTYPE != null) VoidType
		else ctx.getChild(0).accept(this)

/*statement ->  if_stmt | dowhile_stmt | 
			   for_stmt | break_stmt   | 
  		  continue_stmt | return_stmt  | 
		 expression_stmt| block_stmt   | exp-> List(a+b; f(a);) 
		index_expression
*/
//statement
	override def visitStatement(ctx: StatementContext) =
		ctx.getChild(0).accept(this)

//declaration_part -> variable_decl* 
	override def visitDeclaration_part(ctx: Declaration_partContext) = {
		val attributeVardecl = flatten(ctx.variable_decl().asScala.map(x => x.accept(this)).toList) //
		val decl_part = attributeVardecl.map(i => i.asInstanceOf[VarDecl])
		decl_part
	}

//statement_part -> statement* List(
	override def visitStatement_part(ctx: Statement_partContext) = ctx.statement().asScala.map(_.accept(this)).toList.asInstanceOf[List[Stmt]]

	/* {
		val attributeStmt = flatten(ctx.statement().asScala.map(x => x.accept(this)).toList)
		val stmt_part = attributeStmt.map(i => i.asInstanceOf[Stmt])
		stmt_part
		attributeStmt
		//ctx.statement().toList.asIntanceOf[List[Stmt]]
	}*/
		
//if_stmt
	override def visitIf_stmt(ctx: If_stmtContext) = {
		val expr = ctx.expression().accept(this).asInstanceOf[Expr] 
		val thenStmt = ctx.statement(0).accept(this).asInstanceOf[Stmt]
		val elseStmt = 
			if(ctx.statement(1) != null) Option(ctx.statement(1).accept(this).asInstanceOf[Stmt])
			else None	
		If(expr, thenStmt, elseStmt)
	}

//block_stmt -> declaration_part / statement_part  
	override def visitBlock_stmt(ctx: Block_stmtContext) = {
		val decl = 
			if(ctx.declaration_part()!=null) ctx.declaration_part().accept(this).asInstanceOf[List[VarDecl]]
			else List()
		val stmt = 
			if(ctx.statement_part()!=null) ctx.statement_part().accept(this).asInstanceOf[List[Stmt]]
			else List()
		Block(decl,	stmt)
	}

//dowhile_stml
	override def visitDowhile_stmt(ctx: Dowhile_stmtContext) = 
		Dowhile(
			ctx.statement_part().accept(this).asInstanceOf[List[Stmt]],
			ctx.expression().accept(this).asInstanceOf[Expr]
		)

//for_stmt
	override def visitFor_stmt(ctx: For_stmtContext) =
		For(
			ctx.expression(0).accept(this).asInstanceOf[Expr],
			ctx.expression(1).accept(this).asInstanceOf[Expr],
			ctx.expression(2).accept(this).asInstanceOf[Expr],
			ctx.statement().accept(this).asInstanceOf[Stmt]
		)

//break_stmt
	override def visitBreak_stmt(ctx: Break_stmtContext) = Break

//continue_stmt
	override def visitContinue_stmt(ctx: Continue_stmtContext) = Continue

//return_stmt
	override def visitReturn_stmt(ctx: Return_stmtContext) = {
		val res = 
			if(ctx.expression()!= null)
				Option(ctx.expression().accept(this).asInstanceOf[Expr])
			else
				None
		Return(res)
	}


//expression_stmt -> expression (list)
	override def visitExpression_stmt(ctx: Expression_stmtContext) =
		ctx.expression().accept(this) //List[Expr]

//expression
	override def visitExpression(ctx: ExpressionContext) =
		if(ctx.getChildCount() == 1) 
			ctx.getChild(0).accept(this)
		else if (ctx.getChildCount() == 4)
			ArrayCell(
				ctx.expression(0).accept(this).asInstanceOf[Expr],
				ctx.expression(1).accept(this).asInstanceOf[Expr]
			)
		else {
			if(ctx.getChild(0).getText == "(")
				ctx.getChild(1).accept(this)
			else BinaryOp(
				ctx.getChild(1).getText, 
				ctx.getChild(0).accept(this).asInstanceOf[Expr],
				ctx.getChild(2).accept(this).asInstanceOf[Expr]
			)
		}

//assoc_expression
	override def visitAssoc_expression(ctx: Assoc_expressionContext) = 
		if(ctx.getChildCount() == 1) ctx.operands().accept(this)
		else if (ctx.getChildCount() == 2) 
			UnaryOp(
				ctx.getChild(0).getText,
				ctx.getChild(1).accept(this).asInstanceOf[Expr]
			)
		else if (ctx.getChildCount() == 4) 
			ArrayCell(
				ctx.assoc_expression(0).accept(this).asInstanceOf[Expr],
				ctx.assoc_expression(1).accept(this).asInstanceOf[Expr]
			)
		else {
			if(ctx.getChild(0).getText == "(")
				ctx.getChild(1).accept(this)
			else BinaryOp(
				ctx.getChild(1).getText, 
				ctx.getChild(0).accept(this).asInstanceOf[Expr],
				ctx.getChild(2).accept(this).asInstanceOf[Expr]
			)
		}

//expression_SB
	override def visitExpression_SB(ctx: Expression_SBContext) =
		if(ctx.getChildCount() == 1) ctx.operands().accept(this)
		else if (ctx.getChildCount() == 2) 
			UnaryOp(
				ctx.getChild(0).getText,
				ctx.getChild(1).accept(this).asInstanceOf[Expr]
			)
		else if (ctx.getChildCount() == 4) 
			ArrayCell(
				ctx.expression_SB(0).accept(this).asInstanceOf[Expr],
				ctx.expression_SB(1).accept(this).asInstanceOf[Expr]
			)
		else {
			if(ctx.getChild(0).getText == "(")
				ctx.getChild(1).accept(this)
			else BinaryOp(
				ctx.getChild(1).getText, 
				ctx.getChild(0).accept(this).asInstanceOf[Expr],
				ctx.getChild(2).accept(this).asInstanceOf[Expr]
			)
		}
	
//relational_expression
	override def visitRelational_expression(ctx: Relational_expressionContext) =
		if(ctx.getChildCount() == 1) ctx.operands().accept(this)
		else if (ctx.getChildCount() == 2) 
			UnaryOp(
				ctx.getChild(0).getText,
				ctx.getChild(1).accept(this).asInstanceOf[Expr]
			)
		else if (ctx.getChildCount() == 4) 
			ArrayCell(
				ctx.relational_expression(0).accept(this).asInstanceOf[Expr],
				ctx.relational_expression(1).accept(this).asInstanceOf[Expr]
			)
		else {
			if(ctx.getChild(0).getText == "(")
				ctx.getChild(1).accept(this)
			else BinaryOp(
				ctx.getChild(1).getText, 
				ctx.getChild(0).accept(this).asInstanceOf[Expr],
				ctx.getChild(2).accept(this).asInstanceOf[Expr]
			)
		}
	
//equality_expression
	override def visitEquality_expression(ctx: Equality_expressionContext) = 
		if(ctx.getChildCount() == 1) ctx.operands().accept(this)
		else if (ctx.getChildCount() == 2) 
			UnaryOp(
				ctx.getChild(0).getText,
				ctx.getChild(1).accept(this).asInstanceOf[Expr]
			)
		else if (ctx.getChildCount() == 4) 
			ArrayCell(
				ctx.equality_expression(0).accept(this).asInstanceOf[Expr],
				ctx.equality_expression(1).accept(this).asInstanceOf[Expr]
			)
		else {
			if(ctx.getChild(0).getText == "(")
				ctx.getChild(1).accept(this)
			else BinaryOp(
				ctx.getChild(1).getText, 
				ctx.getChild(0).accept(this).asInstanceOf[Expr],
				ctx.getChild(2).accept(this).asInstanceOf[Expr]
			)
		}

//index_expression -> expression(0) & expression(1)
	override def visitIndex_expression(ctx: Index_expressionContext) = 
		flatten(ctx.expression().asScala.map(x => x.accept(this)).toList)

//invocation_expression -> function_call
	override def visitInvocation_expression(ctx: Invocation_expressionContext) = 
		ctx.function_call().accept(this)

//list_expression -> expression*
	override def visitList_expression(ctx: List_expressionContext) = 
		flatten(ctx.expression().asScala.map(x => x.accept(this)).toList)

//operands
	override def visitOperands(ctx: OperandsContext) = 
		if(ctx.ID != null) Id(ctx.ID.getText)
		else ctx.getChild(0).accept(this)

//literals
	override def visitLiterals(ctx: LiteralsContext) = 
		if (ctx.INTLIT != null) IntLiteral(ctx.INTLIT.getText.toInt)
		else if (ctx.FLOATLIT != null) FloatLiteral(ctx.FLOATLIT.getText.toFloat)
		else if (ctx.BOOLLIT != null) BooleanLiteral(ctx.BOOLLIT.getText.toBoolean)
		else StringLiteral(ctx.STRINGLIT.toString)

//function_call	-> ID LB list_expression RB
	override def visitFunction_call(ctx: Function_callContext) = {
		val method = Id(ctx.ID.getText())
		val params = ctx.list_expression().accept(this).asInstanceOf[List[Expr]]
		CallExpr(method, params)
	}

//primitive_type
	override def visitPrimitive_type(ctx: Primitive_typeContext) =
		ctx.getChild(0).getText match {
			case "int" => IntType
			case "boolean" => BoolType
			case "float" => FloatType
			case "string" => StringType
			case _ => throw new IllegalStateException("Type Wrong") 
		}
}