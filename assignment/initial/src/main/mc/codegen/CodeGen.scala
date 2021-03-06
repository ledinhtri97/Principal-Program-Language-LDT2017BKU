// /**
//  *	@author Nguyen Hua Phung
//  *	@version 1.0
//  *	Student: Le Dinh Tri
//  *	MSSV: 1513656
//  *	23/10/2015
//  * 	This file provides a simple version of code generator
//  *
//  */

// package mc.codegen





// //import mc.checker._
// import mc.utils._
// import java.io.{PrintWriter, File}



// case class MType(partype:List[Type],rettype:Type) extends Type //FunctionType
// case class Symbol(val name:String,val mtype:Type,val value:Any, isInit:Boolean)

// object CodeGenerator extends Utils {
//   val libName = "io"
//   def init() = List( Symbol("getInt",MType(List(),IntType),CName(libName),true),
//                      Symbol("putInt",MType(List(IntType),VoidType),CName(libName)),
//                      Symbol("putIntLn",MType(List(IntType),VoidType),CName(libName)),
//                      Symbol("getFloat",MType(List(),FloatType),CName(libName)),
//                      Symbol("putFloat",MType(List(FloatType),VoidType),CName(libName)),
//                      Symbol("putFloatLn",MType(List(FloatType),VoidType),CName(libName)),
//                      Symbol("putBool",MType(List(BoolType),VoidType),CName(libName)),
//                      Symbol("putBoolLn",MType(List(BoolType),VoidType),CName(libName)),
//                      Symbol("putString",MType(List(StringType),VoidType),CName(libName)),
//                      Symbol("putStringLn",MType(List(StringType),VoidType),CName(libName)),
//                      Symbol("putLn",MType(List(),VoidType),CName(libName))
//                     )
    
  
// 	def gen(ast:AST,dir:File) = {
    
//     val gl = init() 
// 		val gc = new CodeGenVisitor(ast,gl,dir)
// 		gc.visit(ast, null);
// 	}
// }




// case class ClassType(cname:String) extends Type

// case class PointerType(ctype: Type) extends Type //add by ledinhtri - added PointerType

// case class SubContext(emit:Emitter,decl:List[Decl]) //use for field vardecl 

// case class SubBody(frame:Frame,sym:List[Symbol]) //use for distribute in method

// class Access(val frame:Frame,val sym:List[Symbol],val isLeft:Boolean,val isFirst:Boolean) //use for expressions

// trait Val
//   case class Index(value:Int) extends Val
//   case class CName(value:String) extends Val





// class CodeGenVisitor(astTree:AST,env:List[Symbol],dir:File) extends BaseVisitor with Utils {

//   val className = "MCClass"
//   val path = dir.getPath()
//   val emit = new Emitter(path+"/"+className+".j")

//   def getName(v: AST): String = {
//         if(v.isInstanceOf[VarDecl])  //VarDecl
//             v.asInstanceOf[VarDecl].variable.name
//         else
//             v.asInstanceOf[FuncDecl].name.name //FuncDecl
//   }

//   def getTypeAst(v: AST): Type = {
//       if(v.isInstanceOf[VarDecl]) //VarDecl
//           v.asInstanceOf[VarDecl].varType
//       else {
//           val listPara = v.asInstanceOf[FuncDecl].param.map(_.asInstanceOf[VarDecl].varType) //FuncDecl
//           MType(listPara, v.asInstanceOf[FuncDecl].returnType)
//       }
//   }

//   override def visitProgram(ast:Program,c:Any) = { 
//       emit.printout(emit.emitPROLOG(className, "java.lang.Object"))
      
//       val glenv = ast.decl.foldLeft(env)((x,y)=> { 
//                 var s = lookup(getName(y),x,(m:Symbol)=>m.name)
//                 if(s==None)
//                     Symbol(getName(y), getTypeAst(y), CName(className), true)::x
//                 else throw IllegalOperandException("Redeclared :"+s) //error
//             }
//         )   

//       val sub = ast.decl.foldLeft(SubBody(null,glenv))((e,x) => if(x.isInstanceOf[VarDecl]) visit(x,e).asInstanceOf[SubBody] else e)
//       ast.decl.foldLeft(sub)((e,x) => if(x.isInstanceOf[FuncDecl]) visit(x,e).asInstanceOf[SubBody] else e) 

//       // generate default constructor 
//       genMETHOD(  //add by ledinhtri - added Id("<init>")
//             FuncDecl(Id("<init>"),List(),null,Block(List(),List())),glenv,new Frame("<init>",VoidType))
//       emit.emitEPILOG()
//       c   
//   }

//   def getType(in:Type):Type = {
//     in match {
//       case ArrayType(_,t) => PointerType(t)
//       case ArrayPointerType(t) => PointerType(t)
//       case MType(_,t) => t
//       case _ => in
//     }
//   }

//   def getTypeP(in:Type):Type ={
//     in match {
//       case ArrayType(_,t) => t 
//       case ArrayPointerType(t) => PointerType(t)
//       case MType(_,t) => t
//       case _ => in
//     }
//   }

//     /** generate code for default constructor 

//    *  @param consdecl the function declaration whose code will be generated by this method
//    *  @param frame the frame where the initialization happen 
//    *  @param o the referencing environment
//    */
//   def genMETHOD(consdecl:FuncDecl,o:Any,frame:Frame) = {
//     //add by ledinhtri - added name.name
//     //println(consdecl)
//     val isInit = consdecl.returnType == null
//     val isMain = consdecl.name.name == "main" && consdecl.param.length == 0 && consdecl.returnType == VoidType
//     val returnType = if (isInit) VoidType else consdecl.returnType
//     val methodName = if (isInit) "<init>" else consdecl.name.name
//     val intype = if (isMain) List(PointerType(StringType)) else consdecl.param.map(_.asInstanceOf[VarDecl].varType) //added paralist not
//     val mtype =  MType(intype,returnType)
//     //println(mtype)
//     emit.printout(emit.emitMETHOD(methodName, mtype, !isInit, frame))

//     frame.enterScope(true);
    
//     val glenv = o.asInstanceOf[List[Symbol]]

//     if(isInit) glenv.map(x=>{
//       //println(x.mtype)
//       if(x.mtype.isInstanceOf[ArrayType]) {
//         val a = x.mtype.asInstanceOf[ArrayType]
//         val cfield = x.value.asInstanceOf[CName].value
//         val x1=emit.emitPUSHICONST(a.dimen.value,frame)
//         //println(x1)
//         val x2=emit.emitNEWARRAY(a.eleType)
//         //println(x2)
//         val x3=emit.emitPUTSTATIC(cfield+"."+x.name,getType(x.mtype),frame)
//         emit.printout(x1+x2+x3)
//       }
//     })
//     // Generate code for parameter declarations
//     if (isInit) emit.printout(emit.emitVAR(frame.getNewIndex,"this",ClassType(className),frame.getStartLabel,frame.getEndLabel,frame))
//     if (isMain) emit.printout(emit.emitVAR(frame.getNewIndex,"args",PointerType(StringType),frame.getStartLabel,frame.getEndLabel,frame))
    
//     //.var parameter
//     val para = consdecl.param.foldLeft(("",SubBody(frame,glenv))) ((es,x) => {
//       val (str1, sym) = visit(x,es._2).asInstanceOf[(String,Symbol)]
//       (es._1 + str1,SubBody(frame,sym::es._2.sym))
//     })

//     emit.printout(para._1)

   
//     emit.printout(emit.emitLABEL(frame.getStartLabel(),frame))
  


//     //Generate code for statements
//     if (isInit) {
//       emit.printout(emit.emitREADVAR("this",ClassType(className),0,frame))
//       emit.printout(emit.emitINVOKESPECIAL(frame))
//     }

    
//     val symret = Symbol("0_ret",returnType,null)
//     //start Block
//     visit(consdecl.body,SubBody(frame,symret::para._2.sym))
//     // val lc1 = para._2.sym //gl + para variable

//     //end Block
    
//     emit.printout(emit.emitLABEL(frame.getEndLabel(),frame))
//     emit.printout(emit.emitFUNRETURN(getType(returnType)));
//     emit.printout(emit.emitENDMETHOD(frame));
//     frame.exitScope();
//   }

//   // def emitDEFAULT(in:Type,frame:Frame):String = {
//   //   in match {
//   //       case IntType => emit.emitPUSHICONST(0, frame)
//   //       case BoolType => emit.emitPUSHICONST(0, frame)
//   //       case FloatType => emit.emitPUSHFCONST("0.0", frame)
//   //       case StringType => emit.emitPUSHCONST("\"null\"", StringType, frame)
//   //       case _ => ""
//   //   }
//   // }


//   override def visitVarDecl(ast: VarDecl, c: Any): Any = { //added by ledinhtri
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val vname = ast.variable.name
//     val vtype = getType(ast.varType)
//     //println(ast)
//     if(frame != null) { 
//       val index = frame.getNewIndex
//       if(ast.varType.isInstanceOf[ArrayType]){
//         val art = ast.varType.asInstanceOf[ArrayType]//triduyen
//         emit.printout(emit.emitINITARRAY(index,art.dimen.asInstanceOf[IntLiteral].value,art.eleType,frame))
//       }
//       //println(vtype)
//       val x1 = emit.emitVAR(index,vname,vtype,frame.getStartLabel,frame.getEndLabel,frame)
      
//       //val x2 = 
//         // if(ast.varType.isInstanceOf[ArrayType]) {
//         //   val dimen = ast.varType.asInstanceOf[ArrayType].dimen
//         //   val etype = ast.varType.asInstanceOf[ArrayType].eleType
//         //   var x = ""
//         //   var a = 0
//         //   for( a <- 0 to dimen){
//         //       x = x + emitDEFAULT(vtype,frame) + 
//         //   }
//         // }
//         // else {
//           //emitDEFAULT(vtype,frame)+emit.emitWRITEVAR(ast.variable.name,vtype,index,frame)
//         //}
      
//       ( x1//+x2
//         ,
//         Symbol(vname,ast.varType,Index(index))
//       )     
//     } //add by ledinhtri
//     else {//emitATTRIBUTE(lexeme:String,  in:Type, isFinal:Boolean, value:String)
//         emit.printout(emit.emitATTRIBUTE(vname,vtype,false,null))
//         SubBody(null,Symbol(vname,ast.varType,CName(className))::ctxt.sym)
//     } //!warning frame.getNewIndex
//   }

//   override def visitFuncDecl(ast:FuncDecl,o:Any) = {
//     val subctxt = o.asInstanceOf[SubBody]
//     val frame = new Frame(ast.name.name,ast.returnType) //add by ledinhtri - added ast.name.name
//     genMETHOD(ast,subctxt.sym,frame)

//     //get MType
//     val listPara = ast.param.map(_.asInstanceOf[VarDecl].varType)

//     SubBody(null,Symbol(ast.name.name,MType(listPara, ast.returnType),CName(className))::subctxt.sym) //add by ledinhtri - added ast.name.name
//     //para
//     //body stmt vardecl don't need to do ==> all of this in genMETHOD
//     //body stmt
//   }

//   override def visitIntType(ast: IntType.type, c: Any): Any = ast
//   override def visitFloatType(ast: FloatType.type, c: Any): Any = ast
//   override def visitBoolType(ast: BoolType.type, c: Any): Any = ast
//   override def visitStringType(ast: StringType.type, c: Any): Any = ast
//   override def visitVoidType(ast: VoidType.type, c: Any): Any = ast
//   override def visitArrayType(ast: ArrayType, c: Any): Any = ast
//   override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = ast
  
//   override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
//     val op = ast.op 
//     //println(ast)
//     val right = visit(ast.right,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//     val inre = right._1
//     val inrt = getTypeP(right._2)
//     //println(inre)
//     if(op != "=") {
//       val left = visit(ast.left,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//       val inle = left._1
//       val inlt = getTypeP(left._2)
//       // //println("1 "+ast)
//       // //println("left :"+left._2+"-"+inlt)
//       // //println("right :"+right._2+"-"+inrt)
//       op match {
//         case "+" | "-" => {
//           if(inlt == inrt)
//             (inle + inre +emit.emitADDOP(op,inlt,frame),inlt)
//           else if(inlt==IntType && inrt == FloatType)
//             (inle + emit.emitI2F(frame)+inre+emit.emitADDOP(op,inrt,frame),inrt)
//           else (inle+inre+emit.emitI2F(frame)+emit.emitADDOP(op,inlt,frame),inlt)
//         }
//         case "*" | "/" => {
//           if(inlt == inrt)
//             (inle + inre +emit.emitMULOP(op,inlt,frame),inlt)
//           else if(inlt==IntType && inrt == FloatType)
//             (inle + emit.emitI2F(frame)+inre+emit.emitMULOP(op,inrt,frame),inrt)
//           else (inle+inre+emit.emitI2F(frame)+emit.emitMULOP(op,inlt,frame),inlt)
//         }
//         case "%" => {
//           if(inlt == inrt)
//             (inle + inre + emit.emitMOD(op,inlt,frame),inlt)
//           else if(inlt==IntType && inrt == FloatType)
//             (inle + emit.emitI2F(frame)+inre+emit.emitMOD(op,inrt,frame),inrt)
//           else (inle+inre+emit.emitI2F(frame)+emit.emitMOD(op,inlt,frame),inlt)
//         }
//         case "&&" => {
//           (inle + inre +emit.emitANDOP(frame),BoolType)
//         }
//         case "||" => {
//           (inle + inre +emit.emitOROP(frame),inlt)
//         }
//         case ">" | "<" | "<=" | ">=" |"==" | "!=" => {
//           //println(inlt+"-"+inrt)
//           if(inlt == inrt)
//             (inle + inre +emit.emitREOP(op,inlt,frame),BoolType)
//           else if(inlt==IntType && inrt == FloatType)
//             (inle + emit.emitI2F(frame)+inre+emit.emitREOP(op,inrt,frame),BoolType)
//           else (inle+inre+emit.emitI2F(frame)+emit.emitREOP(op,inlt,frame),BoolType)
//         }
//         case _ => throw IllegalOperandException(ast.toString)
//       }
//     }
//     else { //case assign 
//       val left = visit(ast.left,new Access(frame,nenv,true,ctxt.isFirst)).asInstanceOf[(String,Type)]
//       val inle = left._1
//       val inlt = getType(left._2)

//       if(ast.left.isInstanceOf[ArrayCell]) {
//         //println(ast)
//         //println(inlt+"=="+inrt)
//         //println(ctxt.isLeft+"=="+ ctxt.isFirst)
//         if(inlt == inrt) {
//           (ctxt.isLeft, ctxt.isFirst) match {
//             case (false, true) => (inle+inre+emit.emitASTORE(getTypeP(left._2),frame),inlt)
//             case (false, false) => (inle+inre+emit.emitDUPX2(frame)+emit.emitASTORE(getTypeP(left._2),frame),inlt)
//             case (_,_)=> (inle+inre,inlt)
//           }
//         }   
//         else if(inlt==FloatType && inrt == IntType)
//         {
//           (ctxt.isLeft, ctxt.isFirst) match {
//             case (false, true) => 
//               {
//                 //println(ctxt.isLeft+"---"+ ctxt.isFirst)
//               (inle+inre+emit.emitI2F(frame)+emit.emitASTORE(getTypeP(left._2),frame),inlt)
//               }
//             case (false, false) => 
//               {
//                 //println(ctxt.isLeft+"--"+ ctxt.isFirst)
//               (inle+inre+emit.emitI2F(frame)+emit.emitDUPX2(frame)+emit.emitASTORE(getTypeP(left._2),frame),inlt)
//               }
//             case (_,_)=>
//               {
//                 //println(ctxt.isLeft+"-"+ ctxt.isFirst)
//               (inle+inre,inlt)
//               }
//           }
//         }
//         else throw IllegalOperandException("")
//       }
//       else if(inlt.isInstanceOf[PointerType]) {
        
//         //emit.printout(inle) //store in left
//         //emit.printout(inre) //load on right
//         //emit.printout(emit.emitASTORE(inlt.asInstanceOf[PointerType].ctype,frame))
//         //println(left._2)
//         //println(inlt+"-"+left._2)
//         // if(left._2.isInstanceOf[ArrayType])
//         //   (inle+inre+emit.emitASTORE(getTypeP(left._2),frame),inlt)  
//         // else
//           (inre+inle,inlt)
//       }
//       else {
//         //emit.printout(inre) //load on right
//         //emit.printout(inle) //store in left
//         //println(inlt+"-"+inrt)
//         //println(inre+"-"+inle)
//         if(inlt == FloatType && inrt == IntType)
//           (inre+emit.emitI2F(frame)+inle,inlt)
//         else (inre+inle,inlt)
//       }
//     }
//   }
//   // override def visitBinaryOp(ast: BinaryOp,c:Any): Any = {
//   //   val ctxt = c.asInstanceOf[Access]
//   //   val frame = ctxt.frame
//   //   val left = visit(ast.left,new Access(frame,ctxt.sym,false,true)).asInstanceOf[(String,Type)]
//   //   val right = visit(ast.right,new Access(frame,ctxt.sym,false,true)).asInstanceOf[(String,Type)]
//   //   if(inlt == inrt)
//   //     (inle + inre +emit.emitADDOP(op,inlt,frame),inlt)
//   //   else if(inlt==IntType && inrt == FloatType)
//   //     (inle + emit.emitI2F(frame)+inre+emit.emitADDOP(op,inrt,frame),inrt)
//   //   else (inle + inre+emit.emitI2F(frame)+emit.emitADDOP(op,inlt,frame),inlt)
//   // }

//   override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
//     val op = ast.op

//     val (be,bt) = visit(ast.body,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//     val et = getTypeP(bt)
//     op match {
//       case "-" => (be+emit.emitNEGOP(et,frame),bt)
//       case "!" => (be+emit.emitNOT(et,frame),BoolType)
//       case _ => IllegalOperandException(ast.toString)
//     }
//   }  
  
//   override def visitCallExpr(ast:CallExpr,c:Any) = {
//     //println(ast)
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
//     val sym = lookup(ast.method.name,nenv,(x:Symbol)=>x.name).get //add by ledinhtri - added ast.name.name
//     //println(sym)
//     val cname = sym.value.asInstanceOf[CName].value
//     val ctype = sym.mtype.asInstanceOf[MType]
//     //println(ctype)
//     val in = ast.params.foldLeft((List[String](),List[Type]())) ((y,x)=>
//       {
//         val (str1,typ1) = visit(x,new Access(frame,nenv,false,true)).asInstanceOf[(String,Type)]
//         (y._1 :+ str1,y._2 :+ typ1)
//       }
//     )
//     val listT = ctype.partype.zip(in._2).zip(in._1)
//     val emitpara = listT.foldLeft("") ((x,y) => {
//       if(y._1._1 == FloatType && y._1._2 == IntType) x+y._2+emit.emitI2F(frame)
//       else x+y._2
//     })
//     //emit.printout(emit.emitINVOKESTATIC(cname+"/"+ast.method.name,ctype,frame))  //add by ledinhtri - added ast.name.name
//     // if(ctype.rettype.isInstanceOf[ArrayPointerType])
//     //   (in._1+emit.emitINVOKESTATIC(cname+"/"+ast.method.name,ctype,frame) + emit.emitPOP(frame), ctype)
//     // else 
//     (emitpara+emit.emitINVOKESTATIC(cname+"/"+ast.method.name,ctype,frame), ctype)
//   }

//   override def visitId(ast: Id, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
//     val sym = lookup(ast.name,nenv,(x:Symbol)=>x.name).get
    
//     val ctype = getType(sym.mtype)
//     //println(ast+"--"+sym.mtype+"--"+ctxt.isLeft)
//     //println(ctxt.isLeft+"--"+ctxt.isFirst)
//     //println(emit.emitREADVAR(ast.name,ctype,cindex,frame))
//     if(sym.value.isInstanceOf[Index]) {
//       val cindex = sym.value.asInstanceOf[Index].value
      
//       (ctxt.isLeft, ctxt.isFirst) match {
//         case (true, true) => {
//           (emit.emitWRITEVAR(ast.name,ctype,cindex,frame),sym.mtype)
//         }
//         case (true, false) => (emit.emitDUP(frame)+emit.emitWRITEVAR(ast.name,ctype,cindex,frame),sym.mtype)
//         case (_,_) => (emit.emitREADVAR(ast.name,ctype,cindex,frame),sym.mtype)
//       }

      
//       // if(ctxt.isLeft)
//       //   (emit.emitWRITEVAR(ast.name,ctype,cindex,frame),sym.mtype)
//       // else
//       //   (emit.emitREADVAR(ast.name,ctype,cindex,frame),sym.mtype)
//     }
//     else {
//       val cfield = sym.value.asInstanceOf[CName].value
//       (ctxt.isLeft, ctxt.isFirst) match {
//         case (true, true) => {
//           (emit.emitPUTSTATIC(cfield+"."+ast.name,ctype,frame),sym.mtype)
//         }
//         case (true, false) => (emit.emitDUP(frame)+emit.emitPUTSTATIC(cfield+"."+ast.name,ctype,frame),sym.mtype)
//         case (_,_) => (emit.emitGETSTATIC(cfield+"."+ast.name,ctype,frame),sym.mtype)
//       }

//       // if(ctxt.isLeft) 
//       //   (emit.emitPUTSTATIC(cfield+"."+ast.name,ctype,frame),sym.mtype)
//       // else
//       //   (emit.emitGETSTATIC(cfield+"."+ast.name,ctype,frame),sym.mtype)
//     }
//   }

//   override def visitArrayCell(ast: ArrayCell, c: Any): Any = {
//     //println(ast)
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym

//     val (arr,arrt) = visit(ast.arr,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//     val (idx,idxt) = visit(ast.idx,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//     val inType = arrt match {
//       case ArrayType(_,t) => t
//       case ArrayPointerType(t) => t
//       case MType(_,t) => t
//       case _ => arrt
//     }

//     if(!ctxt.isLeft)//BinaryOp a[1]=b
//       (arr+idx+emit.emitALOAD(inType,frame),inType) //int
//     else
//       (arr+idx,inType)
//   }

//   override def visitBlock(ast: Block, c: Any): Any = {
//     //println(c)
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym

//     //frame.enterScope(true)

//     //VarDecl
//     //add local VarDecl
//     val vari = ast.decl.foldLeft(("",SubBody(frame,nenv))) ((es,x) => {
//       val (str1, sym) = visit(x,es._2).asInstanceOf[(String,Symbol)]
//       //println(sym)
//       (es._1+str1,SubBody(frame,sym::es._2.sym))
//     })        
//     //emit.printout(vari._1)
//     val local = vari._2.sym // lc1 + vardecl ===> list local + global
//     //stmt
//     ast.stmt.map(x => if(x.isInstanceOf[Expr]) {
//       //println(x)
//       val expr = visit(x,new Access(frame,local,false,true)).asInstanceOf[(String,Type)]
//       emit.printout(expr._1)
//       } else
//         visit(x,SubBody(frame,local))
//       )
//     //frame.exitScope()
//   }
  
//   override def visitIf(ast: If, c: Any): Any = {
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
    
//     //println(frame.currIndex)

//     val condition = visit(ast.expr,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]

//     //println("e :"+condition._1+"\ntype con: "+condition._2)
//     if(getTypeP(condition._2) == BoolType) {
      
//       emit.printout(condition._1)

//       val labelF = frame.getNewLabel();

//       emit.printout(emit.emitIFFALSE(labelF,frame))
      
//       if(ast.thenStmt.isInstanceOf[Expr])
//         emit.printout(visit(ast.thenStmt,new Access(frame,nenv,false,true)).asInstanceOf[(String,Type)]._1)
//       else
//         visit(ast.thenStmt,new SubBody(frame,nenv))

//       ast.elseStmt match {
//         case Some(exp) => {
//           val labelT = frame.getNewLabel();
          
//           emit.printout(emit.emitGOTO(labelT,frame) + emit.emitLABEL(labelF,frame))
//           visit(exp,new SubBody(frame,nenv))
//           emit.printout(emit.emitLABEL(labelT,frame))
//         }
//         case _ => emit.printout(emit.emitLABEL(labelF,frame))
//       }
//     }
//     else throw IllegalOperandException(ast.expr+"")
//   }
  
//   override def visitFor(ast: For, c: Any): Any = {

//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
    
//     //println(frame.getEndLabel)

//     frame.enterLoop()
//     //println(ast.expr1)
//     val expr1 = visit(ast.expr1, new Access(frame,nenv,false,true)).asInstanceOf[(String,Type)]

//     val condition = visit(ast.expr2,new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//     if(condition._2 == BoolType) {

//       emit.printout(expr1._1)
      
//       //println(frame.getContinueLabel)
//       val labelLoop = frame.getNewLabel();
//       val labelF = frame.getBreakLabel;

//       emit.printout(emit.emitLABEL(labelLoop,frame))

//       emit.printout(condition._1)

//       emit.printout(emit.emitIFFALSE(labelF,frame))

//       if(ast.loop.isInstanceOf[Expr])
//         emit.printout(visit(ast.loop,new Access(frame,nenv,false,true)).asInstanceOf[(String,Type)]._1)
//       else
//         visit(ast.loop,new SubBody(frame,nenv)) 

//       val expr3 = visit(ast.expr3,new Access(frame,nenv,false,true)).asInstanceOf[(String,Type)]

//       emit.printout(emit.emitLABEL(frame.getContinueLabel,frame))

//       emit.printout(expr3._1)

//       emit.printout(emit.emitGOTO(labelLoop,frame))

//       emit.printout(emit.emitLABEL(labelF,frame))
//     }
//     else throw IllegalOperandException(ast.expr1+"")

//     frame.exitLoop()
//   }

//   override def visitBreak(ast: Break.type, c: Any): Any = {
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     emit.printout(emit.emitGOTO(frame.getBreakLabel,frame))
//   }

//   override def visitContinue(ast: Continue.type, c: Any): Any = {
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     emit.printout(emit.emitGOTO(frame.getContinueLabel,frame))
//   }

//   override def visitReturn(ast: Return, c: Any): Any = {
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym
//     val x = ast.expr match {
//       case Some(exp) => visit(exp, new Access(frame,nenv,false,false)).asInstanceOf[(String,Type)]
//       case _ => ("", VoidType)
//     }
//     //case class Symbol(val name:String,val mtype:Type,val value:Any)
//     val ret = lookup("0_ret",nenv,(m:Symbol)=>m.name) match {
//       case Some(Symbol(_,rt,_)) => rt
//       case _ => VoidType 
//     }
//     //println(ret +"-"+x._2)
//     if(ret==FloatType && x._2==IntType) 
//       emit.printout(x._1 + emit.emitI2F(frame) + emit.emitGOTO(frame.getEndLabel,frame))
//     else
//       emit.printout(x._1 + emit.emitGOTO(frame.getEndLabel,frame))
//   }

//   override def visitDowhile(ast: Dowhile, c:Any): Any = {
//     val ctxt = c.asInstanceOf[SubBody]
//     val frame = ctxt.frame
//     val nenv = ctxt.sym

//     frame.enterLoop()

//     val exp = visit(ast.exp,new Access(frame,nenv,true,true)).asInstanceOf[(String,Type)]
    
//     if(exp._2 == BoolType) {
//       val labelLoop = frame.getNewLabel();
//       val labelF = frame.getBreakLabel;

//       emit.printout(emit.emitLABEL(labelLoop,frame))
//       //println(ast.sl)
//       ast.sl.map(x => if(x.isInstanceOf[Expr]) {
        
//         //dont go to here
        
//         val expr = visit(x,new Access(frame,nenv,true,true)).asInstanceOf[(String,Type)]
        
//         emit.printout(expr._1)

//       } else {
//         //println(x)
//         visit(x,SubBody(frame,nenv))
//       })
//       emit.printout(emit.emitLABEL(frame.getContinueLabel,frame))

//       emit.printout(exp._1)

//       emit.printout(emit.emitIFTRUE(labelLoop,frame)) 

//       emit.printout(emit.emitLABEL(labelF,frame))

//     } 
//     else throw IllegalOperandException(ast.exp+"")   

//     frame.exitLoop()
//   }


//   override def visitIntLiteral(ast:IntLiteral, c:Any) = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     (emit.emitPUSHICONST(ast.value, frame),IntType)
//   }

//   override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     (emit.emitPUSHFCONST(ast.value+"", frame),FloatType) //float to string
//   }

//   override def visitStringLiteral(ast: StringLiteral, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     (emit.emitPUSHCONST("\""+ast.value+"\"", StringType, frame),StringType)
//   }

//   override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = {
//     val ctxt = c.asInstanceOf[Access]
//     val frame = ctxt.frame
//     (emit.emitPUSHICONST(ast.value+"", frame),BoolType) //Boolean to string
//   }  
// }