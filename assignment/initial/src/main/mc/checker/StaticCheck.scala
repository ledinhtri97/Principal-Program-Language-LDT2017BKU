package mc.checker

/**
 * @author nhphung
 * Student name: Le Dinh Tri
 * Student ID: 1513656
 */

import mc.parser._
import mc.utils._
import java.io.{File, PrintWriter}

//import mc.codegen.Val
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree._

import scala.collection.JavaConverters._

case class MType(partype:List[Type],rettype:Type) extends Type //FunctionType
case class Symbol(val name:String,val mtype:Type,val value:Any)

class StaticChecker(ast:AST) extends BaseVisitor with Utils {
    val global = List(
                    Symbol("getInt",MType(List(),IntType),null),
                    Symbol("putIntLn",MType(List(IntType),VoidType),null), //add another symbok method we need have
                    Symbol("putInt",MType(List(IntType),VoidType),null),
                    Symbol("getFloat",MType(List(),FloatType),null),
                    Symbol("putFloat",MType(List(FloatType),VoidType),null),
                    Symbol("putFloatLn",MType(List(FloatType),VoidType),null),
                    Symbol("putBool",MType(List(BoolType),VoidType),null),
                    Symbol("putBoolLn",MType(List(BoolType),VoidType),null),
                    Symbol("putString",MType(List(StringType),VoidType),null),
                    Symbol("putStringLn",MType(List(StringType),VoidType),null),
                    Symbol("putLn",MType(List(),VoidType),null)
                )

    def check() = visit(ast,global)

    def getName(v: AST): String = {
        if(v.isInstanceOf[VarDecl])  //VarDecl
            v.asInstanceOf[VarDecl].variable.name
        else
            v.asInstanceOf[FuncDecl].name.name //FuncDecl
    }

    def getType(v: AST): Type = {
        if(v.isInstanceOf[VarDecl]) //VarDecl
            v.asInstanceOf[VarDecl].varType
        else {
            val listPara = v.asInstanceOf[FuncDecl].param.map(_.asInstanceOf[VarDecl].varType) //FuncDecl
            MType(listPara, v.asInstanceOf[FuncDecl].returnType)
        }
    }

    def getKind(v: AST): Kind = {
        if(v.isInstanceOf[VarDecl]) //VarDecl
            Variable
        else
            Function //FuncDecl
    }

    //?Questiion here? when does it become to Identifier Kind?

    override def visitProgram(ast: Program, c: Any): Any = {
        val glenv = ast.decl.foldLeft(global)((x,y)=> {
                var s = lookup(getName(y),x,(m:Symbol)=>m.name)
                if(s==None)
                    Symbol(getName(y), getType(y), null)::x
                else throw Redeclared(getKind(y), getName(y)) //error
            }
        )
        ast.decl.filter(_.isInstanceOf[FuncDecl]).map(_.accept(this, glenv))
    }

    override def visitVarDecl(ast: VarDecl, c: Any): Any = {
        null
    }
    override def visitFuncDecl(ast: FuncDecl, c: Any): Any = {
        //in this func.
        
        
        var isReturn: Boolean = false
        var isUnreachableStatement = false

        //Redeclared parameter //done
        val env = c.asInstanceOf[List[Symbol]]
        val lcPara = ast.param.foldLeft(List[Symbol]())((x,y)=>
            if(lookup(getName(y), x, (m:Symbol)=>m.name)==None) {
                Symbol(getName(y), getType(y), null)::x
            }
            else throw Redeclared(Parameter, getName(y)) //error
        ) //need to update local variable if it Redeclared the global variable //done


        //in body --
        //Redeclared VarDecl in body // done
        //Undeclared Variable in body // done
        //Undeclared Function in body // done
        //Undeclared Identifier in body // done
        //TypeMismatchInExpression in body // need to done with expression
        //TypeMismatchInStatement in body // done 1/2 -> 1
        //BreakNotInLoop in body //done
        //ContinueNotInLoop in body //done
        //UnreachableStatement in body //done

        val lcVars = ast.body.asInstanceOf[Block].decl.foldLeft(lcPara)((x,y)=>
            if(lookup(getName(y), x, (m:Symbol)=>m.name)==None) 
                Symbol(getName(y), getType(y), null)::x
            else throw Redeclared(Variable, getName(y)) //error
        ) // check Redeclared variable

        //lcVars.map(x=>println(x.name))

        val lc = env.foldLeft(lcVars)((x,y) => {//lcVars la list dau = x, y la ptu dau cua env //ket hop global va local
                var s = lookup(y.name, x, (m:Symbol)=>m.name)
                //println(s+"-"+y.name)
                s match {
                    case None  => y::x //Some(Symbol(_,MType(_,_),_))
                    case Some(_) => x
                }
            }
        )

        ast.body.asInstanceOf[Block].stmt.map(x =>
            if(isUnreachableStatement) throw UnreachableStatement(x) //error
            else if(x.isInstanceOf[Return]) { 
                val returnType = visit(x, lc)
                //println(returnType +"- f->"+ast.returnType)
                ast.returnType match {
                    case FloatType => 
                        if(returnType != FloatType && returnType != IntType)
                            throw TypeMismatchInStatement(x.asInstanceOf[Return])
                    case ArrayPointerType(eleType) => {
                        returnType match {
                            case ArrayType(_, et) => if(eleType != et) throw TypeMismatchInStatement(x.asInstanceOf[Return])
                            case ArrayPointerType(et) => if(eleType != et) throw TypeMismatchInStatement(x.asInstanceOf[Return]) 
                            case _ => throw TypeMismatchInStatement(x.asInstanceOf[Return])
                        }   
                    }
                    case tpr => if(tpr != returnType) throw TypeMismatchInStatement(x.asInstanceOf[Return])
                }
                isReturn = true
                isUnreachableStatement = true
            }
            else if (x.isInstanceOf[Dowhile]) {
                val exp = x.asInstanceOf[Dowhile].exp
                if(exp.isInstanceOf[BooleanLiteral])
                    if(exp.asInstanceOf[BooleanLiteral].value == true)
                        isUnreachableStatement = true
                visit(x, lc)
            }
            else if (x.isInstanceOf[For]) {
                val expbool = x.asInstanceOf[For].expr2
                if(expbool.isInstanceOf[BooleanLiteral])
                    if(expbool.asInstanceOf[BooleanLiteral].value == true)
                        isUnreachableStatement = true
                visit(x, lc)
            }
            else if (x.isInstanceOf[If]) {
                val expr = x.asInstanceOf[If].expr
                if(expr.isInstanceOf[BooleanLiteral])
                    if(expr.asInstanceOf[BooleanLiteral].value == false)
                        throw UnreachableStatement(x.asInstanceOf[If].thenStmt) //error
                visit(x, lc)
            }
            else if (x==Break) throw BreakNotInLoop
            else if(x==Continue) throw ContinueNotInLoop
            else visit(x, lc)
        ) 
        // -> visitBinaryOp |
        // -> visitUnaryOp  |
        // -> visitCallExpr |
        // -> visitBlock    |
        // -> visit..all expression

        //FunctionNotReturn // done
        if(!isReturn && ast.returnType != VoidType)
            throw FunctionNotReturn(ast.name.name) //error
    }


    override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
        //TypeMismatchInExpression BinaryOps
        val op = ast.op
        val lhs = visit(ast.left, c).asInstanceOf[Type]
        val rhs = visit(ast.right, c).asInstanceOf[Type]
        //println(lhs + op + rhs)
        op match {
            case "+" | "-" | "*" | "/" | "%" => 
                if((lhs == FloatType && rhs == FloatType) || (lhs == FloatType && rhs == IntType) || (lhs == IntType && rhs == FloatType)) FloatType
                else if (lhs == IntType && rhs == IntType) IntType
                else throw TypeMismatchInExpression(ast) //error
            case "<" | "<=" | ">" | ">=" =>
                if((lhs == FloatType && rhs == FloatType) || (lhs == FloatType && rhs == IntType) || (lhs == IntType && rhs == IntType) || (lhs == IntType && rhs == FloatType)) BoolType
                else throw TypeMismatchInExpression(ast) //error
            case "==" | "!=" =>
                if((lhs == IntType && rhs == IntType) || (lhs == BoolType && rhs == BoolType)) BoolType
                else throw TypeMismatchInExpression(ast) //error
            case "&&" | "||" =>
                if(lhs == BoolType && rhs == BoolType) BoolType
                else throw TypeMismatchInExpression(ast) //error
            case "=" =>
                if(lhs == IntType && rhs == IntType) IntType
                else if(lhs == BoolType && rhs == BoolType) BoolType 
                else if((lhs == FloatType && rhs == FloatType) || (lhs == FloatType && rhs == IntType)) FloatType
                else if(lhs == StringType && rhs == StringType) StringType
                else if(
                    lhs match {
                        case ArrayPointerType(eleType_1) => rhs match {
                            case ArrayPointerType(eleType_2) => if (eleType_1 == eleType_2) true else false 
                            case ArrayType(_, eleType_2) => if (eleType_1 == eleType_2) true else false 
                            case _ => false
                        }
                        case ArrayType(_, eleType_1) => rhs match {
                            case ArrayType(_, eleType_2) => if (eleType_1 == eleType_2) true else false
                            case ArrayPointerType(eleType_2) => if (eleType_1 == eleType_2) true else false
                            case eleType_2: Type => if (eleType_1 == eleType_2) true else false 
                            case _ => false  
                        } 
                        case _ => false 
                    }
                ) lhs
                else throw TypeMismatchInExpression(ast) //error
            case _ => throw TypeMismatchInExpression(ast) //error
        }
    }

    override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
        //TypeMismatchInExpression UnaryOp
        val op = ast.op
        val b = visit(ast.body, c)
        op match {
            case "-" =>
                if(b==IntType) IntType
                else if(b==FloatType) FloatType
                else throw TypeMismatchInExpression(ast) //error
            case "!"  =>
                if(b==BoolType) BoolType
                else throw TypeMismatchInExpression(ast) //error
            case _ => throw TypeMismatchInExpression(ast)
        }
    }

    override def visitCallExpr(ast: CallExpr, c: Any): Any = {

        val env = c.asInstanceOf[List[Symbol]] //this is list symbol had from global list, add new???
        //env.map(x=>println(x.name+"-"+x.mtype))
        val at = ast.params.map(visit(_,c).asInstanceOf[Type])
        val res = lookup(ast.method.name,env.filter(_.mtype.isInstanceOf[MType]),(x:Symbol)=>x.name) //ast.method (this is a Id) we need get name of it
        
        //println(res + "para = "+at)

        res match {
            case Some(Symbol(_,MType(pl,rt),_)) => {
                //pl.zip(at).map(x=>println(x._1 +"-"+x._2))
                //(pl.length + " - " + at.length)
                if (pl.length!=at.length || pl.zip(at).exists(x => 
                    x._1 match {
                        case ArrayPointerType(eleType_1) => x._2 match {
                            case ArrayPointerType(eleType_2) => if (eleType_1 == eleType_2) false else true 
                            case ArrayType(_, eleType_2) => if (eleType_1 == eleType_2) false else true 
                            case _ => false
                        }
                        case ArrayType(_, eleType_1) => x._2 match {
                            case ArrayType(_, eleType_2) => if (eleType_1 == eleType_2) false else true
                            case ArrayPointerType(eleType_2) => if (eleType_1 == eleType_2) false else true
                            case eleType_2: Type => if (eleType_1 == eleType_2) false else true 
                            case _ => true  
                        } 
                        case eleType_1 => x._2 match {  //println(eleType_1 +" -- "+eleType_2)
                            case ArrayType(_, _) => true
                            case ArrayPointerType(_) => true
                            case eleType_2 => if (eleType_1 == eleType_2) false else true 
                        }
                    }
                )) throw TypeMismatchInExpression(ast) //error
                else rt
            }
            case Some(_) | None => throw Undeclared(Function,ast.method.name) //error
                //ast.method (this is a Id) we need get name of it
        }
    } 

    override def visitId(ast: Id, c: Any): Any = {
        val env = c.asInstanceOf[List[Symbol]]
        //env.map(x=>println(x.name))
        val res = lookup(ast.name, env, (x:Symbol)=>x.name)
        //Symbol(val name:String,val mtype:Type,val value:Any)
        //println(res)
        res match {
            case Some(Symbol(_,MType(_,_),_)) | None => throw Undeclared(Identifier, ast.name) //error
            case Some(Symbol(_, mtype: Type,_)) => mtype 
        }
    }

    override def visitArrayCell(ast: ArrayCell, c: Any): Any = {
        val arrType = visit(ast.arr, c)
        //println(visit(ast.arr, c) +"--"+ visit(ast.idx, c))
        if(visit(ast.idx, c) != IntType) throw TypeMismatchInExpression(ast)//error
        else if(arrType.isInstanceOf[ArrayType]) arrType.asInstanceOf[ArrayType].eleType
        else if(arrType.isInstanceOf[ArrayPointerType]) arrType.asInstanceOf[ArrayPointerType].eleType
        else throw TypeMismatchInExpression(ast) //error 
    }

    override def visitBlock(ast: Block, c: Any): Any = {
        val env = c.asInstanceOf[List[Symbol]]
        val lcScopebl = ast.decl.foldLeft(List[Symbol]())((x,y)=>
            if(lookup(getName(y), x, (m:Symbol)=>m.name)==None)
                Symbol(getName(y), getType(y), null)::x
            else throw Redeclared(Variable, getName(y)) //error
        ) //Redeclared 
        //error update here local scope variable //done
        val lc = env.foldLeft(lcScopebl)((x,y) => {//lcVars la list dau = x, y la ptu dau cua env //ket hop global va local
                var s = lookup(y.name, x, (m:Symbol)=>m.name)
                //println(s+"-"+y.name)
                s match {
                    case None => y::x
                    case Some(_) => x
                }
            }
        )
        ast.stmt.map(visit(_,lc))
    }

    override def visitIf(ast: If, c: Any): Any = {
        if(visit(ast.expr.asInstanceOf[Expr],c) != BoolType) 
            throw TypeMismatchInStatement(ast) //error
        visit(ast.thenStmt.asInstanceOf[Stmt],c)
        ast.elseStmt match {
            case Some(exp) => visit(exp,c) 
            case _ => Nil
        }
        //check BreakNotInLoop and ContinueNotInLoop
    }
    override def visitFor(ast: For, c: Any): Any = {
        if(visit(ast.expr1.asInstanceOf[Expr],c) != IntType)
            throw TypeMismatchInStatement(ast) //error
        if(visit(ast.expr2.asInstanceOf[Expr],c) != BoolType) 
            throw TypeMismatchInStatement(ast) //error
        if(visit(ast.expr3.asInstanceOf[Expr],c) != IntType) 
            throw TypeMismatchInStatement(ast) //error
        val loop = Symbol("0_loop", null, null)
        visit(ast.loop.asInstanceOf[Stmt], loop::c.asInstanceOf[List[Symbol]])
    }

    override def visitBreak(ast: Break.type, c: Any): Any = {
        val env = c.asInstanceOf[List[Symbol]]
        if(lookup("0_loop", env, (x:Symbol)=>x.name) == None) throw BreakNotInLoop
    }
    
    override def visitContinue(ast: Continue.type, c: Any): Any = {
        val env = c.asInstanceOf[List[Symbol]]
        if(lookup("0_loop", env, (x:Symbol)=>x.name) == None) throw ContinueNotInLoop
    }

    override def visitReturn(ast: Return, c: Any): Any = {
        // val env = c.asInstanceOf[List[Symbol]]
        // println(env)
        ast match {
            case Return(None) => VoidType
            case Return(Some(exp: Expr)) => visit(exp,c)
        }        
    }
    override def visitDowhile(ast: Dowhile, c:Any): Any = {
        if(visit(ast.exp.asInstanceOf[Expr],c) != BoolType)
            throw TypeMismatchInStatement(ast)
        val loop = Symbol("0_loop", null, null)
        ast.sl.map(visit(_,loop::c.asInstanceOf[List[Symbol]]))
    }
    
    override def visitIntType(ast: IntType.type, c: Any): Any = IntType 
    override def visitFloatType(ast: FloatType.type, c: Any): Any = FloatType
    override def visitBoolType(ast: BoolType.type, c: Any): Any = BoolType
    override def visitStringType(ast: StringType.type, c: Any): Any = StringType
    override def visitVoidType(ast: VoidType.type, c: Any): Any = VoidType
    override def visitArrayType(ast: ArrayType, c: Any): Any = ast
    override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = ast
    override def visitIntLiteral(ast: IntLiteral, c: Any): Any = IntType
    override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = FloatType
    override def visitStringLiteral(ast: StringLiteral, c: Any): Any = StringType
    override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = BoolType
}

/*
log change

undeclared
redeclared
return
type mismatch in expression
type mismatch in statement
 */ 