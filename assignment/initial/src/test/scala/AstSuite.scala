import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class AstSuite extends FunSuite with TestAst {
  test("201 _ sub or plus expression in main function void as return type of main") {
    val input = "void main () {}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,201))
  }
  test("202 _ another simple program with int as return type of main") {
    val input = "int main () {}"
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List()))))
    assert(checkAst(input,expected,202))
  }
  test("203 _ program has _ call putIntLn") {
    val input = "void foo () {putIntLn(5);}"
    val expected = Program(List(FuncDecl(Id("foo"),List(),VoidType,Block(List(),List(CallExpr(Id("putIntLn"),List(IntLiteral(5))))))))
    assert(checkAst(input,expected,203))
  }
  test("204 _ program decl varible") {
    val input = "int a;"
    val expected = Program(List(VarDecl(Id("a"), IntType)))
    assert(checkAst(input,expected,204))
  }
  test("205 _ program decl multi varibles") {
    val input = "int a,b,c;"
    val expected = Program(List(VarDecl(Id("a"),IntType), VarDecl(Id("b"),IntType), VarDecl(Id("c"),IntType)))
    assert(checkAst(input,expected,205))
  }
  test("206 _ program decl multi varibles other type") {
    val input = """ boolean a,b,c_abc[10];
        int a;
    """
    val expected = Program(List(VarDecl(Id("a"),BoolType),VarDecl(Id("b"),BoolType),VarDecl(Id("c_abc"),ArrayType(IntLiteral(10),BoolType)),VarDecl(Id("a"),IntType)))
    assert(checkAst(input,expected,206))
  }
  test("207 _ program has _ call putIntLn and varibles decl") {
    val input = "string test () {int a; putIntLn(5);}"
    val expected = Program(List(FuncDecl(Id("test"),List(),StringType,Block(List(VarDecl(Id("a"),IntType)),List(CallExpr(Id("putIntLn"),List(IntLiteral(5))))))))
    assert(checkAst(input,expected,207))
  }
  test("208 _ program has a varibles decl") {
    val input = "string flash () {int a;}"
    val expected = Program(List(FuncDecl(Id("flash"),List(),StringType,Block(List(VarDecl(Id("a"),IntType)),List()))))
    assert(checkAst(input,expected,208))
  }
  test("209 _ 1 a cuong decl") {
    val input = "string flash () {int a, b[1];}"
    val expected = Program(
      List(
        FuncDecl(
          Id("flash"),
          List(),
          StringType,
          Block(
            List(
              VarDecl(
                Id("a"),
                IntType),
              VarDecl(
                Id("b"),
                ArrayType(IntLiteral(1),IntType))),
            List()
          ))))
    assert(checkAst(input,expected,209))
  }
  test("210 _ test varibles") {
    val input ="""
      string flash (int a, float b) {     
        int a,d,f[9];
        if(a) foo(b);
        else d;
      }
      float[] foo(int b){
        do {
          a;
          break;
        }
        while b;
        return b;
      }
      """
    val expected = Program(
      List(
        FuncDecl(
          Id("flash"),
          List(
            VarDecl(Id("a"),IntType),
            VarDecl(Id("b"),FloatType)),
          StringType,
          Block(
            List(
              VarDecl(Id("a"),IntType),
              VarDecl(Id("d"),IntType),
              VarDecl(Id("f"),ArrayType(IntLiteral(9),IntType))),
            List(If(Id("a"),
              CallExpr(
                Id("foo"),
                List(Id("b"))),
              Some(Id("d")))))),
        FuncDecl(Id("foo"),
          List(
            VarDecl(
              Id("b"),
              IntType)),
          ArrayPointerType(FloatType),
          Block(List(),
            List(
              Dowhile(
                List(
                  Block(
                    List(),
                    List(Id("a"),Break))),
                Id("b")),
              Return(Some(Id("b")))
          )))))
    assert(checkAst(input,expected,210))
  }
  test("211 _ 2 test varibles") {
    val input ="""
      string flash (int a, float b) {     
        int a,d,f[9];
        if(a) foo(b);
        else d;
      }
      // float[] foo(int b) {
      //   do {
      //     a;
      //     break;
      //   }
      //   while b
      //   //return b;
      // }
      """
    val expected = Program(List(
      FuncDecl(
        Id("flash"),
        List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType)),
        StringType,
        Block(
          List(
            VarDecl(Id("a"),IntType),
            VarDecl(Id("d"),IntType),
            VarDecl(Id("f"),ArrayType(IntLiteral(9),IntType))
          ),
          List(
            If(
              Id("a"),CallExpr(Id("foo"),List(Id("b"))),
              Some(Id("d"))
            ))))))
    assert(checkAst(input,expected,211))
  }
  test("212 _ 3 test varibles") {
    val input ="""
      // string flash (int a, float b) {     
      //   int a,d,f[9];
      //   if(a) foo(b);
      //   else d;
      // }
      float[] foo(int b){
        do {
          a;
          break;
        }
        while b;
        return b+2;
      }
      """
    val expected = Program(
      List(
        FuncDecl(
          Id("foo"),
          List(VarDecl(Id("b"),IntType)), 
          ArrayPointerType(FloatType),
          Block(
            List(),
            List(
              Dowhile(
                List(Block(List(),List(Id("a"),Break))),Id("b")),
              Return(Some(BinaryOp("+",Id("b"),IntLiteral(2)))))
        ))))
    assert(checkAst(input,expected,212))
  }
  test("213 _ 4 test varibles") {
    val input ="""
      // string flash (int a, float b) {     
      //   int a,d,f[9];
      //   if(a) foo(b);
      //   else d;
      // }
      float[] foo(int b){
        do {
          a;
          break;
        }
        while b;
        //return b+2;
      }
      """
    val expected = Program(
      List(
        FuncDecl(
          Id("foo"),
          List(VarDecl(Id("b"),IntType)),
          ArrayPointerType(FloatType),
          Block(List(),
            List(
              Dowhile(
                List(Block(List(),List(Id("a"),Break))),
                Id("b")
              ))))))
    assert(checkAst(input,expected,213))
  }
  test("214 _ Express's current codes & discounts ArrayPointerType function") {
    val input = "boolean[] train (boolean a[]) {return a;}"
    val expected = Program(List(FuncDecl(Id("train"),List(VarDecl(Id("a"),ArrayPointerType(BoolType))),ArrayPointerType(BoolType),Block(List(),List(Return(Some(Id("a"))))))))
    assert(checkAst(input,expected,214))
  }
  test("215 _ involocation expression or String values in main function function has list tparameter decl") {
    val input = "void main (int a, int b, string a[]) {}"
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("a"),ArrayPointerType(StringType))),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,215))
  }
  test("216 _ String values in main function if statment contains else") {
    val input = "void main () {if(true) a; else b;}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BooleanLiteral(true),Id("a"),Some(Id("b"))))))))
    assert(checkAst(input,expected,216))
  }
  test("217 _ String leagal \\ t \\ n values in main function if statment without else") {
    val input = "void main () {if(false) return 10;}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BooleanLiteral(false),Return(Some(IntLiteral(10))),None))))))
    assert(checkAst(input,expected,217))
  }
  test("218 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc do while statment") {
    val input = """void foo (int a) {
      do
        a=a+1;
        break;
      while true;
    }"""
    val expected = Program(List(FuncDecl(Id("foo"),List(VarDecl(Id("a"),IntType)),VoidType,Block(List(),List(Dowhile(List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),IntLiteral(1))),Break),BooleanLiteral(true)))))))
    assert(checkAst(input,expected,218))
  }
  test("219 _ main function decalaration great then or equal for statment") {
    val input = """void foo () {
      int a;
      for(a=1; a < 10; a=a+2.3e-1){
        print(a);
      }
    }"""
    val expected = Program(
      List(
        FuncDecl(
          Id("foo"),
          List(),
          VoidType,
          Block(
            List(
              VarDecl(Id("a"),IntType)),
            List(
              For(
                BinaryOp("=",Id("a"),IntLiteral(1)),
                BinaryOp("<",Id("a"),IntLiteral(10)),
                BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),FloatLiteral(0.23.toFloat))),
                Block(List(),List(CallExpr(Id("print"),List(Id("a"))))))
            )))))
    assert(checkAst(input,expected,219))
  }
  test("220 _ another String leagal test values in main function break statment") {
    val input = """void main () {
      int a[10];
      int i;
      for(i = 0; i <= length(a); i=i+1){
        show(a[i]);
        break;
      }
    }"""
    val expected = Program(
      List(
        FuncDecl(
          Id("main"),
          List(),
          VoidType,
          Block(
            List(
              VarDecl(Id("a"),ArrayType(IntLiteral(10),IntType)),
              VarDecl(Id("i"),IntType)),
            List(
              For(
                BinaryOp("=",Id("i"),IntLiteral(0)),
                BinaryOp("<=",Id("i"),CallExpr(Id("length"),List(Id("a")))),
                BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),
                Block(
                  List(),
                  List(
                    CallExpr(Id("show"),
                    List(
                      ArrayCell(Id("a"),Id("i")))),
                    Break
                  ))))))))
    assert(checkAst(input,expected,220))
  }
  test("221 _ sub or plus expression in main function continue statment") {
    val input = """void main () {
      string _string ;
      do {
        _string = "f*ck";
        print(1);
        continue;
      } while true;
      return _string;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("_string"),StringType)),List(Dowhile(List(Block(List(),List(BinaryOp("=",Id("_string"),StringLiteral("f*ck")),CallExpr(Id("print"),List(IntLiteral(1))),Continue))),BooleanLiteral(true)),Return(Some(Id("_string"))))))))
    assert(checkAst(input,expected,221))
  }
  test("222 _ decalaration function main great then or equal return statment") {
    val input = """float main (string args[]) {
        float a;
        a = 1.2E-20;
        return a;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("args"),ArrayPointerType(StringType))),FloatType,Block(List(VarDecl(Id("a"),FloatType)),List(BinaryOp("=",Id("a"),FloatLiteral(1.2E-20.toFloat)),Return(Some(Id("a"))))))))
    assert(checkAst(input,expected,222))
  }
  test("223 _ function main declaration with expresion lessthen or great then or equal expression with 1 op") {
    val input = """void main () {
      int a,b;
      a = b+2+(b/2)*2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",BinaryOp("+",Id("b"),IntLiteral(2)),BinaryOp("*",BinaryOp("/",Id("b"),IntLiteral(2)),IntLiteral(2)))))))))
    assert(checkAst(input,expected,223))
  }
  test("224 _ Express's current codes & discounts expression with 2 op") {
    val input = """float main () {
      int a, b;
      if(a>=b){
        a=(a+b)-2;
      }
      return;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),FloatType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(If(BinaryOp(">=",Id("a"),Id("b")),Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("-",BinaryOp("+",Id("a"),Id("b")),IntLiteral(2))))),None),Return(None))))))
    assert(checkAst(input,expected,224))
  }
  test("225 _ involocation expression or String values in main function") {
    val input = """
    float a, b[10];
    int foo_main (int a_) {
      if(a=="string"){
        return b[1];
      }
      else return b[2];
    }"""
    val expected = Program(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),ArrayType(IntLiteral(10),FloatType)),FuncDecl(Id("foo_main"),List(VarDecl(Id("a_"),IntType)),IntType,Block(List(),List(If(BinaryOp("==",Id("a"),StringLiteral("string")),Block(List(),List(Return(Some(ArrayCell(Id("b"),IntLiteral(1)))))),Some(Return(Some(ArrayCell(Id("b"),IntLiteral(2)))))))))))
    assert(checkAst(input,expected,225))
  }
  test("226 _ String values in main function") {
    val input = """void main () {
      int a;
      a = "string \t a+b";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),StringLiteral("""string \t a+b""")))))))
    assert(checkAst(input,expected,226))
  }
  test("227 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      int fuck;
      fuck = foo("bash + d");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("fuck"),IntType)),List(BinaryOp("=",Id("fuck"),CallExpr(Id("foo"),List(StringLiteral("bash + d")))))))))
    assert(checkAst(input,expected,227))
  }
  test("228 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      int A;
      A = 1.2E-31;
      A = (A+10)/2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("A"),IntType)),List(BinaryOp("=",Id("A"),FloatLiteral(1.2E-31f)),BinaryOp("=",Id("A"),BinaryOp("/",BinaryOp("+",Id("A"),IntLiteral(10)),IntLiteral(2))))))))
    assert(checkAst(input,expected,228))
  }
  test("229 _ main function decalaration great then or equal") {
    val input = """void main () {
      boolean a_, b, c;
      a_ = (b==c) >= (c+10);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a_"),BoolType),VarDecl(Id("b"),BoolType),VarDecl(Id("c"),BoolType)),List(BinaryOp("=",Id("a_"),BinaryOp(">=",BinaryOp("==",Id("b"),Id("c")),BinaryOp("+",Id("c"),IntLiteral(10)))))))))
    assert(checkAst(input,expected,229))
  }
  test("230 _ another String leagal test values in main function") {
    val input = """void main () {
      float test;
      test = 1.2E-10;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("test"),FloatType)),List(BinaryOp("=",Id("test"),FloatLiteral(1.2E-10.toFloat)))))))
    assert(checkAst(input,expected,230))
  }
  test("231 _ sub or plus expression in main function") {
    val input = """void main () {
      cast("string + a");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("cast"),List(StringLiteral("string + a"))))))))
    assert(checkAst(input,expected,231))
  }
  test("232 _ decalaration function main great then or equal") {
    val input = """void main () {
      int a, b, c;
      (a/2)+(b*2 || c);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("+",BinaryOp("/",Id("a"),IntLiteral(2)),BinaryOp("||",BinaryOp("*",Id("b"),IntLiteral(2)),Id("c"))))))))
    assert(checkAst(input,expected,232))
  }
  test("233 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      string a[4];
      a[1] = "{1, 2, 3, 4}";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(4),StringType))),List(BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),StringLiteral("{1, 2, 3, 4}")))))))
    assert(checkAst(input,expected,233))
  }
  test("234 _ Express's current codes & discounts") {
    val input = """void main () {
      int a, b;
      a>=b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,234))
  }
  test("235 _ involocation expression or String values in main function") {
    val input = """void main () {
      int a, b;
      a<= b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,235))
  }
  test("236 _ String values in main function") {
    val input = """void main () {
      int a, b;
      a < b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,236))
  }
  test("237 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      int a, b;
      a > b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,237))
  }
  test("238 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      int a, b;
      a == b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("==",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,238))
  }
  test("239 _ main function decalaration great then or equal") {
    val input = """void main () {
      int a, b;
      a != b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("!=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,239))
  }
  test("240 _ another String leagal test values in main function") {
    val input = """void main () {
      int a, b;
      a && b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("&&",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,240))
  }
  test("241 _ sub or plus expression in main function") {
    val input = """void main () {
      int a, b;
      a || b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("||",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,241))
  }
  test("242 _ decalaration function main great then or equal") {
    val input = """void main () {
      int a, b;
      a = b*b/2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("/",BinaryOp("*",Id("b"),Id("b")),IntLiteral(2))))))))
    assert(checkAst(input,expected,242))
  }
  test("243 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      int a, b;
      a = a+b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),Id("b"))))))))
    assert(checkAst(input,expected,243))
  }
  test("244 _ Express's current codes & discounts") {
    val input = """void main () {
      int a, b;
      a = a-b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("-",Id("a"),Id("b"))))))))
    assert(checkAst(input,expected,244))
  }
  test("245 _ involocation expression or String values in main function") {
    val input = """void main () {
      float a, b;
      !a == !(b+2);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),FloatType)),List(BinaryOp("==",UnaryOp("!",Id("a")),UnaryOp("!",BinaryOp("+",Id("b"),IntLiteral(2)))))))))
    assert(checkAst(input,expected,245))
  }
  test("246 _ String values in main function") {
    val input = """void main () {
      string a, b;
      a = b + "fuck, \n translations";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),StringType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("b"),StringLiteral("""fuck, \n translations"""))))))))
    assert(checkAst(input,expected,246))
  }
  test("247 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      string a;
      int b;
      a = b*("string");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("*",Id("b"),StringLiteral("string"))))))))
    assert(checkAst(input,expected,247))
  }
  test("248 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      //int x,a,b,c;
      foo(2)[2+x] = a[b[2]] + c;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(2))),BinaryOp("+",IntLiteral(2),Id("x"))),BinaryOp("+",ArrayCell(Id("a"),ArrayCell(Id("b"),IntLiteral(2))),Id("c"))))))))
    assert(checkAst(input,expected,248))
  }
  test("249 _ main function decalaration great then or equal") {
    val input = """void main () {
      string a;
      a = "\n _12,3";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType)),List(BinaryOp("=",Id("a"),StringLiteral("""\n _12,3""")))))))
    assert(checkAst(input,expected,249))
  }
  test("250 _ another String leagal test values in main function") {
    val input = """void main () {
      string a ;
      a = "name of string \t \r + ok";
      show_of(a);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType)),List(BinaryOp("=",Id("a"),StringLiteral("""name of string \t \r + ok""")),CallExpr(Id("show_of"),List(Id("a"))))))))
    assert(checkAst(input,expected,250))
  }
  test("251 _ sub or plus expression in main function") {
    val input = """void main () {
      clear("string_here"!=null);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("clear"),List(BinaryOp("!=",StringLiteral("string_here"),Id("null")))))))))
    assert(checkAst(input,expected,251))
  }
  test("252 _ decalaration function main great then or equal") {
    val input = """
    float a, b[10];
    int foo_main (int a_) {
      if(a=="string"){
        return b[1];
      }
      else return b[2];
    }"""
    val expected = Program(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),ArrayType(IntLiteral(10), FloatType)),FuncDecl(Id("foo_main"),List(VarDecl(Id("a_"),IntType)),IntType,Block(List(),List(If(BinaryOp("==",Id("a"),StringLiteral("string")),Block(List(),List(Return(Some(ArrayCell(Id("b"),IntLiteral(1)))))),Some(Return(Some(ArrayCell(Id("b"),IntLiteral(2)))))))))))
    assert(checkAst(input,expected,252))
  }
  test("253 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      int a;
      a = "string \t a+b";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),StringLiteral("""string \t a+b""")))))))
    assert(checkAst(input,expected,253))
  }
  test("254 _ Express's current codes & discounts") {
    val input = """void main () {
      int fuck;
      fuck = foo("bash + d");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("fuck"),IntType)),List(BinaryOp("=",Id("fuck"),CallExpr(Id("foo"),List(StringLiteral("bash + d")))))))))
    assert(checkAst(input,expected,254))
  }
  test("255 _ involocation expression or String values in main function") {
    val input = """void main () {
      int A;
      A = 1.2E-31;
      A = (A+10)/2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("A"),IntType)),List(BinaryOp("=",Id("A"),FloatLiteral(1.2E-31f)),BinaryOp("=",Id("A"),BinaryOp("/",BinaryOp("+",Id("A"),IntLiteral(10)),IntLiteral(2))))))))
    assert(checkAst(input,expected,255))
  }
  test("256 _ String values in main function") {
    val input = """void main () {
      boolean a_, b, c;
      a_ = (b==c) >= (c+10);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a_"),BoolType),VarDecl(Id("b"),BoolType),VarDecl(Id("c"),BoolType)),List(BinaryOp("=",Id("a_"),BinaryOp(">=",BinaryOp("==",Id("b"),Id("c")),BinaryOp("+",Id("c"),IntLiteral(10)))))))))
    assert(checkAst(input,expected,256))
  }
  test("257 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      float set;
      set = 1.2E-10;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("set"),FloatType)),List(BinaryOp("=",Id("set"),FloatLiteral(1.2E-10.toFloat)))))))
    assert(checkAst(input,expected,257))
  }
  test("258 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      cast("string + a");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("cast"),List(StringLiteral("string + a"))))))))
    assert(checkAst(input,expected,258))
  }
  test("259 _ main function decalaration great then or equal") {
    val input = """void main () {
      int a, b, c;
      (a/2)+(b*2 || c);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("+",BinaryOp("/",Id("a"),IntLiteral(2)),BinaryOp("||",BinaryOp("*",Id("b"),IntLiteral(2)),Id("c"))))))))
    assert(checkAst(input,expected,259))
  }
  test("260 _ another String leagal test values in main function") {
    val input = """void main () {
      string a[4];
      a[1] = "{1, 2, 3, 4}";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(4),StringType))),List(BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),StringLiteral("{1, 2, 3, 4}")))))))
    assert(checkAst(input,expected,260))
  }
  test("261 _ sub or plus expression in main function") {
    val input = """void main () {
      int a, b;
      a>=b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,261))
  }
  test("262 _ decalaration function main great then or equal") {
    val input = """void main () {
      int a, b;
      a<= b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,262))
  }
  test("263 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      int a, b;
      a < b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,263))
  }
  test("264 _ Express's current codes & discounts") {
    val input = """void main () {
      int a, b;
      a > b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,264))
  }
  test("265 _ involocation expression or String values in main function") {
    val input = """void main () {
      int a, b;
      a == b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("==",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,265))
  }
  test("266 _ String values in main function") {
    val input = """void main () {
      int a, b;
      a != b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("!=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,266))
  }
  test("267 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      int a, b;
      a && b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("&&",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,267))
  }
  test("268 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      int a, b;
      a || b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("||",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,268))
  }
  test("269 _ main function decalaration great then or equal") {
    val input = """void main () {
      int a, b;
      a = b*b/2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("/",BinaryOp("*",Id("b"),Id("b")),IntLiteral(2))))))))
    assert(checkAst(input,expected,269))
  }
  test("270 _ another String leagal test values in main function") {
    val input = """void main () {
      int a, b;
      a = a+b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("a"),Id("b"))))))))
    assert(checkAst(input,expected,270))
  }
  test("271 _ sub or plus expression in main function") {
    val input = """void main () {
      int a, b;
      a = a-b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("-",Id("a"),Id("b"))))))))
    assert(checkAst(input,expected,271))
  }
  test("272 _ decalaration function main great then or equal") {
    val input = """void main () {
      float a, b;
      !a == !(b+2);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),FloatType)),List(BinaryOp("==",UnaryOp("!",Id("a")),UnaryOp("!",BinaryOp("+",Id("b"),IntLiteral(2)))))))))
    assert(checkAst(input,expected,272))
  }
  test("273 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      string a, b;
      a = b + "fuck, \n translations";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),StringType)),List(BinaryOp("=",Id("a"),BinaryOp("+",Id("b"),StringLiteral("""fuck, \n translations"""))))))))
    assert(checkAst(input,expected,273))
  }
  test("274 _ Express's current codes & discounts") {
    val input = """void main () {
      string a;
      int b;
      a = b*("string");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("a"),BinaryOp("*",Id("b"),StringLiteral("string"))))))))
    assert(checkAst(input,expected,274))
  }
  test("275 _ involocation expression or String values in main function") {
    val input = """void main () {
      //int x,a,b,c;
      foo(2)[2+x] = a[b[2]] + c;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(2))),BinaryOp("+",IntLiteral(2),Id("x"))),BinaryOp("+",ArrayCell(Id("a"),ArrayCell(Id("b"),IntLiteral(2))),Id("c"))))))))
    assert(checkAst(input,expected,275))
  }
  test("276 _ String values in main function") {
    val input = """void main () {
      string a;
      a = "\n _12,3";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType)),List(BinaryOp("=",Id("a"),StringLiteral("""\n _12,3""")))))))
    assert(checkAst(input,expected,276))
  }
  test("277 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      string a ;
      a = "name of string \t \r + ok";
      show_of(a);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),StringType)),List(BinaryOp("=",Id("a"),StringLiteral("""name of string \t \r + ok""")),CallExpr(Id("show_of"),List(Id("a"))))))))
    assert(checkAst(input,expected,277))
  }
  test("278 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      clear("string_here"!=null);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("clear"),List(BinaryOp("!=",StringLiteral("string_here"),Id("null")))))))))
    assert(checkAst(input,expected,278))
  }
  test("279 _ main function decalaration great then or equal") {
    val input = """
    float a, b[10];
    int foo_main (int a_) {
      if(a=="string"){
        return b[1];
      }
      else return b[2];
    }"""
    val expected = Program(List(VarDecl(Id("a"),FloatType),VarDecl(Id("b"),ArrayType(IntLiteral(10),FloatType)),FuncDecl(Id("foo_main"),List(VarDecl(Id("a_"),IntType)),IntType,Block(List(),List(If(BinaryOp("==",Id("a"),StringLiteral("string")),Block(List(),List(Return(Some(ArrayCell(Id("b"),IntLiteral(1)))))),Some(Return(Some(ArrayCell(Id("b"),IntLiteral(2)))))))))))
    assert(checkAst(input,expected,279))
  }
  test("280 _ another String leagal test values in main function") {
    val input = """void main () {
      int a;
      a = "string \t a+b";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List(BinaryOp("=",Id("a"),StringLiteral("""string \t a+b""")))))))
    assert(checkAst(input,expected,280))
  }
  test("281 _ sub or plus expression in main function") {
    val input = """void main () {
      int fuck;
      fuck = foo("bash + d");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("fuck"),IntType)),List(BinaryOp("=",Id("fuck"),CallExpr(Id("foo"),List(StringLiteral("bash + d")))))))))
    assert(checkAst(input,expected,281))
  }
  test("282 _ decalaration function main great then or equal") {
    val input = """void main () {
      int A;
      A = 1.2E-31;
      A = (A+10)/2;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("A"),IntType)),List(BinaryOp("=",Id("A"),FloatLiteral(1.2E-31f)),BinaryOp("=",Id("A"),BinaryOp("/",BinaryOp("+",Id("A"),IntLiteral(10)),IntLiteral(2))))))))
    assert(checkAst(input,expected,282))
  }
  test("283 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      boolean a_, b, c;
      a_ = (b==c) >= (c+10);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a_"),BoolType),VarDecl(Id("b"),BoolType),VarDecl(Id("c"),BoolType)),List(BinaryOp("=",Id("a_"),BinaryOp(">=",BinaryOp("==",Id("b"),Id("c")),BinaryOp("+",Id("c"),IntLiteral(10)))))))))
    assert(checkAst(input,expected,283))
  }
  test("284 _ Express's current codes & discounts") {
    val input = """void main () {
      float set;
      set = 1.2E-10;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("set"),FloatType)),List(BinaryOp("=",Id("set"),FloatLiteral(1.2E-10.toFloat)))))))
    assert(checkAst(input,expected,284))
  }
  test("285 _ involocation expression or String values in main function") {
    val input = """void main () {
      cast("string + a");
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("cast"),List(StringLiteral("string + a"))))))))
    assert(checkAst(input,expected,285))
  }
  test("286 _ String values in main function") {
    val input = """void main () {
      int a, b, c;
      (a/2)+(b*2 || c);
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType)),List(BinaryOp("+",BinaryOp("/",Id("a"),IntLiteral(2)),BinaryOp("||",BinaryOp("*",Id("b"),IntLiteral(2)),Id("c"))))))))
    assert(checkAst(input,expected,286))
  }
  test("287 _ String leagal \\ t \\ n values in main function") {
    val input = """void main () {
      string a[4];
      a[1] = "{1, 2, 3, 4}";
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),ArrayType(IntLiteral(4),StringType))),List(BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),StringLiteral("{1, 2, 3, 4}")))))))
    assert(checkAst(input,expected,287))
  }
  test("288 _ chuong trinh khai bao bien voi cac bieu thuc toan hoc") {
    val input = """void main () {
      int a, b;
      a>=b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,288))
  }
  test("289 _ main function decalaration great then or equal") {
    val input = """void main () {
      int a, b;
      a<= b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,289))
  }
  test("290 _ another String leagal test values in main function") {
    val input = """void main () {
      int a, b;
      a < b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("<",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,290))
  }
  test("291 _ sub or plus expression in main function") {
    val input = """void main () {
      int a, b;
      a > b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp(">",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,291))
  }
  test("292 _ decalaration function main great then or equal") {
    val input = """void main () {
      int a, b;
      a == b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("==",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,292))
  }
  test("293 _ function main declaration with expresion lessthen or great then or equal") {
    val input = """void main () {
      int a, b;
      a != b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("!=",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,293))
  }
  test("294 _ Express's current codes & discounts") {
    val input = """int main(){ g[f[2]] = 5;}"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(BinaryOp("=",ArrayCell(Id("g"),ArrayCell(Id("f"),IntLiteral(2))),IntLiteral(5)))))))
    assert(checkAst(input,expected,294))
  }
  test("295 _ involocation expression or String values in main function OR operator") {
    val input = """void main () {
      int a, b;
      a || b;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("||",Id("a"),Id("b")))))))
    assert(checkAst(input,expected,295))
  }

  test("simple program test break and for statement and funcalls") {
    val input = """
void main(){
  string s1[100], s2[100], i;
  clrscr();
  printf("Enter string s1: ");
  Nhap(s1);
  for(i=0; s1[i]!="0"; i=i+1)
  {
    s2[i]=s1[i];
    if(i==9) break;
  }
  s2[i]="0";
  printf("String s2: ");
}
"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("s1"),ArrayType(IntLiteral(100),StringType)),VarDecl(Id("s2"),ArrayType(IntLiteral(100),StringType)),VarDecl(Id("i"),StringType)),List(CallExpr(Id("clrscr"),List()),CallExpr(Id("printf"),List(StringLiteral("Enter string s1: "))),CallExpr(Id("Nhap"),List(Id("s1"))),For(BinaryOp("=",Id("i"),IntLiteral(0)),BinaryOp("!=",ArrayCell(Id("s1"),Id("i")),StringLiteral("0")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),Block(List(),List(BinaryOp("=",ArrayCell(Id("s2"),Id("i")),ArrayCell(Id("s1"),Id("i"))),If(BinaryOp("==",Id("i"),IntLiteral(9)),Break,None)))),BinaryOp("=",ArrayCell(Id("s2"),Id("i")),StringLiteral("0")),CallExpr(Id("printf"),List(StringLiteral("String s2: "))))))))
    assert(checkAst(input,expected,296))
  }
  test("simple program test statements and Index Expression ") {
    val input = """void main(){
      myid = foo(2,2)[a+b] == 123;
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",Id("myid"),BinaryOp("==",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(2),IntLiteral(2))),BinaryOp("+",Id("a"),Id("b"))),IntLiteral(123))))))))
    assert(checkAst(input,expected,297))
  }
 test("a simple program test funcall and expression ") {
    val input = """int main () {
      putFloatLn(3.5*(3+a));
    }"""
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(CallExpr(Id("putFloatLn"),List(BinaryOp("*",FloatLiteral(3.5f),BinaryOp("+",IntLiteral(3),Id("a"))))))))))
    assert(checkAst(input,expected,298))
  }
test("another program test if and continue statement and expressions ") {
    val input = """ void main() { 
     int b;
     b=9;
      do
     if(b>3) continue;
      b=b-1;
      while b>0;
     }
  
     """
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("b"),IntType)),List(BinaryOp("=",Id("b"),IntLiteral(9)),Dowhile(List(If(BinaryOp(">",Id("b"),IntLiteral(3)),Continue,None),BinaryOp("=",Id("b"),BinaryOp("-",Id("b"),IntLiteral(1)))),BinaryOp(">",Id("b"),IntLiteral(0))))))))
    assert(checkAst(input,expected,299))
  }
  
test("another program test if and Dowhile statement and BoolType and expressions ") {
    val input = """int   main() { 
     int b;
     boolean ok;
     b=9;
     ok=true;
     do
      if(b>3) ok=false;
      b=b-1;
      while b>0;
      }
  
     """
    val expected = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(VarDecl(Id("b"),IntType),VarDecl(Id("ok"),BoolType)),List(BinaryOp("=",Id("b"),IntLiteral(9)),BinaryOp("=",Id("ok"),BooleanLiteral(true)),Dowhile(List(If(BinaryOp(">",Id("b"),IntLiteral(3)),BinaryOp("=",Id("ok"),BooleanLiteral(false)),None),BinaryOp("=",Id("b"),BinaryOp("-",Id("b"),IntLiteral(1)))),BinaryOp(">",Id("b"),IntLiteral(0))))))))
    assert(checkAst(input,expected,300))
  }
}