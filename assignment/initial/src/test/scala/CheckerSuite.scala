import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  * Student name: Le Dinh Tri
  * Student ID: 1513656
  */
class CheckerSuite extends FunSuite with TestChecker {

  test("401 _ good function") {
    val input = """void main () {
      int a, b;
      a = 5; b = 3;
      do {
        putIntLn(b);
        b = b - 1;
        a = b - 1;
      } while(a > 1);
    }"""
    val expected = ""
    assert(checkCkr(input,expected,401))
  }
  test("402 _ Type Mismatch In Expression: Binary") {
    val input = """
void main(){
  string s1[100], s2[100];int i;
  //clrscr();
  //printf("Enter string s1: ");
  Nhap(s1);
  i=0;
  for(i; s1[i]!="0"; i=i+1)
  {
    s2[i]=s1[i];
    if(i==9) break;
  }
  s2[i]="0";
  //printf("String s2: ");
}
void Nhap(string x[]){}
"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("!=",ArrayCell(Id("s1"),Id("i")),StringLiteral("0"))
    assert(checkCkr(input,expected,402))
  }
  test("403 _ Type Mismatch In Expression: putIntLn") {
    val input = """
    int a[10], b[3];
    int[] foo(int x){return a;}
    void main () {int x; foo(2)[3+x] = a[b[2]] +3;}"""
    val expected = ""
    assert(checkCkr(input,expected,403))
  }
  test("404 _ Type Mismatch In Expression: putLn") {
    val input = """void main () {putLn(3);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(IntLiteral(3)))
    assert(checkCkr(input,expected,404))
  }
  test("405 _ Type Mismatch In Expression sucessful program putLn Function") {
    val input = """void main () {putLn();}"""
    val expected = ""
    assert(checkCkr(input,expected,405))
  }
  test("406 _ Type Mismatch In Expression putInt with float") {
    val input = """void main () {putInt(3.3);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putInt"),List(FloatLiteral(3.3.toFloat)))
    assert(checkCkr(input,expected,406))
  }
  test("407 _ Type Mismatch In Expression putIntLn with string") {
    val input ="""void main () {putLn("string here");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(StringLiteral("string here")))
    assert(checkCkr(input,expected,407))
  }
  test("408 _ Type Mismatch In Expression getInt with float") {
    val input = """void main () {getInt(1.2e-3);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getInt"),List(FloatLiteral(0.0012.toFloat)))
    assert(checkCkr(input,expected,408))
  }
  test("409 _ Type Mismatch In Expression getInt with string") {
    val input = """void main () {getInt("f*ck");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getInt"),List(StringLiteral("f*ck")))
    assert(checkCkr(input,expected,409))
  }
  test("410 _ Type Mismatch In Expression getInt with boolean") {
    val input = """void main () {getInt(true);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getInt"),List(BooleanLiteral(true)))
    assert(checkCkr(input,expected,410))
  }
  test("411 _ return type mismatch") {
    val input = """
    float[] main(int a[]){
      return a;
    }"""
    val expected = "Type Mismatch In Statement: "+Return(Some(Id("a")))
    assert(checkCkr(input,expected,411))
  }
  test("412 _ FunctionNotReturn không?") {
    val input = """int main(){
int a;
do{
if(a==0) return a;
a = a-1;
}while(a>0);
}"""
    val expected = "Function Not Return: main"
    assert(checkCkr(input,expected,412))
  }
  test("413 _ Type Mismatch In Expression putInt with string") {
    val input = """void main () {putInt("hello everyone");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putInt"),List(StringLiteral("hello everyone")))
    assert(checkCkr(input,expected,413))
  }
  test("414 _ Type Mismatch In Expression getFloat with float") {
    val input = """void main () {getFloat(1.2E-12);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getFloat"),List(FloatLiteral(1.2E-12.toFloat)))
    assert(checkCkr(input,expected,414))
  }
  test("415 _ Type Mismatch In Expression getFloat with int") {
    val input = """void main () {getFloat(2);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getFloat"),List(IntLiteral(2)))
    assert(checkCkr(input,expected,415))
  }
  test("416 _ Type Mismatch In Expression getFloat with string") {
    val input = """void main () {getFloat("yolo caibo");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getFloat"),List(StringLiteral("yolo caibo")))
    assert(checkCkr(input,expected,416))
  }
  test("417 _ Type Mismatch In Expression putFloat with int") {
    val input = """void main () {putFloat(2);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putFloat"),List(IntLiteral(2)))
    assert(checkCkr(input,expected,417))
  }
  test("418 _ Type Mismatch In Expression putFloat with string") {
    val input = """void main () {putFloat("d c m");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putFloat"),List(StringLiteral("d c m")))
    assert(checkCkr(input,expected,418))
  }
  test("419 _ Type Mismatch In Expression putFloat with boolean") {
    val input = """void main () {putFloat(false);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putFloat"),List(BooleanLiteral(false)))
    assert(checkCkr(input,expected,419))
  }
  test("420 _ Type Mismatch In Expression getFloat with boolean") {
    val input = """void main () {getFloat(true);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getFloat"),List(BooleanLiteral(true)))
    assert(checkCkr(input,expected,420))
  }
  test("421 _ Type Mismatch In Expression putFloatLn with int") {
    val input = """
    int a;
    int foo(int a) {return a;}
    void main () {
      int a; 
      {
        float a;
        foo(a);
      }
    }
    """
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("a")))
    assert(checkCkr(input,expected,421))
  }
 test("new test"){
   val input =
     """
       int a;
       float b;
       string c[5];
       boolean d;
       float[] foo(int x, int y){
         float a[1];
         int b;
         return a;
       }
       int doo(int a, float b, string c){
        return 10;
       }
       int hoo(){
         doo(1,3.4,"ahihi");
         return 1;
       }
     """
   val expect = ""
   assert(checkCkr(input,expect,420))
 }
  test("422 _ Break Not In Loop") {
    val input = """
    void foo(int a){}
    void main () {
      int a; int b;
      do {
        foo(a);
        break;
      } while (b<1);
      break;
    }"""
    val expected = "Break Not In Loop"
    assert(checkCkr(input,expected,422))
  }
  test("423 _ Continue Not In Loop") {
    val input = """
    void foo(int a){}
    void main () {
      int a; int b;
      do {
        foo(a);
        break;
      } while (b<1);
      continue;
    }"""
    val expected = "Continue Not In Loop"
    assert(checkCkr(input,expected,423))
  }
  test("424 _ Continue Not In Loop") {
    val input = """
    void foo(int a){}
    void main () {
      int a; int b;
      do {
        foo(a);
        break;
      } while (b<1);
      if(a==1) continue;
    }"""
    val expected = "Continue Not In Loop"
    assert(checkCkr(input,expected,424))
  }
  test("425 _ Type Mismatch In Expression putBool with float") {
    val input = """void main () {
      int a; int b;
      do {
        foo();
        break;
      } while (b<1);
      {
        if(a==1) foo();
        else
            continue;
      }
    }
    void foo(){}
    """
    val expected = "Continue Not In Loop"
    assert(checkCkr(input,expected,425))
  }
  test("426 _ Type Mismatch In Expression putBool with string") {
    val input = """int foo(){int a[100]; return a[5];}"""
    val expected = ""
    assert(checkCkr(input,expected,426))
  }
  test("427 _ Type Mismatch In Expression putBoolLn with int") {
    val input = """float a[99];
              float main(){
                return a[-999];
              }"""
    val expected = ""
    assert(checkCkr(input,expected,427))
  }
  test("428 _ Type Mismatch In Expression putBoolLn with float") {
    val input = """int main(){
                return main;  
              }"""
    val expected = "Undeclared Identifier: main"
    assert(checkCkr(input,expected,428))
  }
  test("429 _ Type Mismatch In Expression putBoolLn with string") {
    val input = """void main () {
          if(false) {
            return;
          }
    }"""
    val expected = "Unreachable Statement: "+Block(List(),List(Return(None)))
    assert(checkCkr(input,expected,429))
  }
  test("430 _ Type Mismatch In Expression putString with int") {
    val input = """void main () {return ; putString("haha");}"""
    val expected = "Unreachable Statement: "+CallExpr(Id("putString"),List(StringLiteral("haha")))
    assert(checkCkr(input,expected,430))
  }
  test("431 _ Type Mismatch In Expression putString with float") {
    val input = """void main () {int a; do {a=a+1;}while(true);return;}"""
    val expected = "Unreachable Statement: "+Return(None)
    assert(checkCkr(input,expected,431))
  }
  test("432 _ Type Mismatch In Expression putString with boolean") {
    val input = """void main () {putString(true);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putString"),List(BooleanLiteral(true)))
    assert(checkCkr(input,expected,432))
  }
  test("433 _ Type Mismatch In Expression putStringLn with int") {
    val input = """void main () {putStringLn(3);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putStringLn"),List(IntLiteral(3)))
    assert(checkCkr(input,expected,433))
  }
  test("434 _ Type Mismatch In Expression putStringLn with float") {
    val input = """void main () {putStringLn(3.2E-12);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putStringLn"),List(FloatLiteral(3.2E-12.toFloat)))
    assert(checkCkr(input,expected,434))
  }
  test("435 _ Type Mismatch In Expression putStringLn with boolean") {
    val input = """void main () {putStringLn(true);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putStringLn"),List(BooleanLiteral(true)))
    assert(checkCkr(input,expected,435))
  }
  test("436 _ Type Mismatch In Expression putLn with int") {
    val input = """void main () {putLn(2);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(IntLiteral(2)))
    assert(checkCkr(input,expected,436))
  }
  test("437 _ Type Mismatch In Expression putLn with float") {
    val input = """void main () {putLn(2.3e-1);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(FloatLiteral(0.23.toFloat)))
    assert(checkCkr(input,expected,437))
  }
  test("438 _ Type Mismatch In Expression putLn with string") {
    val input = """void main () {putLn("maoimaoi");}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(StringLiteral("maoimaoi")))
    assert(checkCkr(input,expected,438))
  }
  test("439 _ Type Mismatch In Expression putLn with boolean") {
    val input = """void main () {putLn(false);}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putLn"),List(BooleanLiteral(false)))
    assert(checkCkr(input,expected,439))
  }
  test("440 _ Type Mismatch In Expression putInt no parameter") {
    val input = """void main () {putInt();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putInt"),List())
    assert(checkCkr(input,expected,440))
  }
  test("441 _ Type Mismatch In Expression putIntLn no parameter") {
    val input = """void main () {putIntLn();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putIntLn"),List())
    assert(checkCkr(input,expected,441))
  }
  test("442 _ Unreachable Statement: BinaryOp(>=,Id(i),BinaryOp(+,Id(i),IntLiteral(10)))") {
    val input = """void main () {int i;
      i = 10;
      for(i;true;i=i+2){putIntLn(i);}
      i>=i+10;
      }"""
    val expected = "Unreachable Statement: "+BinaryOp(">=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(10)))
    assert(checkCkr(input,expected,442))
  }
  test("443 _ Type Mismatch In Expression putFloatLn no parameter") {
    val input = """void main () {putFloatLn();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putFloatLn"),List())
    assert(checkCkr(input,expected,443))
  }
  test("444 _ Type Mismatch In Expression putBool no parameter") {
    val input = """void main () {putBool();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putBool"),List())
    assert(checkCkr(input,expected,444))
  }
  test("445 _ Type Mismatch In Expression putBoolLn no parameter") {
    val input = """void main () {putBoolLn();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putBoolLn"),List())
    assert(checkCkr(input,expected,445))
  }
  test("446 _ Type Mismatch In Expression putString no parameter") {
    val input = """void main () {putString();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putString"),List())
    assert(checkCkr(input,expected,446))
  }
  test("447 _ Type Mismatch In Expression putStringLn no parameter") {
    val input = """void main () {putStringLn();}"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putStringLn"),List())
    assert(checkCkr(input,expected,447))
  }
  test("448 _ Function was declaration") {
    val input = """
      void foo(){putInt(3);}
      void main () {foo();}
    """
    val expected = ""
    assert(checkCkr(input,expected,448))
  }
test("449 Undeclared Function") {
  val input = "void main () {writeIntLn(3);}"
  val expected = "Undeclared Function: writeIntLn"
  assert(checkCkr(input,expected,449))
}
  test("450 Type Mismatch In Expression: getInt") {
    val input = "void main () {getInt(3);}"
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("getInt"),List(IntLiteral(3))).toString
    assert(checkCkr(input,expected,450))
  }
  test("451 Type Mismatch In Expression: putIntLn") {

    val input = "void main () {putIntLn();}"
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("putIntLn"),List()).toString
    assert(checkCkr(input,expected,451))
  }
  test("454 _ ") {
    val input = """int a, b, c;
    void main() {}
    int b;
    float c;
    """
    val expected = "Redeclared Variable: b"
    assert(checkCkr(input,expected,456))
  }
  // test("455 _ ") {
  //   val lhs = ArrayType(IntLiteral(3), IntType)
  //   val rhs = ArrayPointerType(IntType)
  //   val expected = ""
  //   assert(typeCheck(lhs,rhs))
  // }
  test("good 456 _ Redeclared Variable: b ") {
    val input = """int a, b, c;
    void main() {}
    int b;
    float c;
    """
    val expected = "Redeclared Variable: b"
    assert(checkCkr(input,expected,456))
  }
  test("457 _ Redeclared Function: main ") {
    val input = """
    int main;
    void main () {}"""
    val expected = "Redeclared Function: main"
    assert(checkCkr(input,expected,457))
  }
  test("458 _ Redeclared Function: foo") {
    val input = """
    int foo() {return 1;}
    void main(){}
    float foo() {}
    """
    val expected = "Redeclared Function: foo"
    assert(checkCkr(input,expected,458))
  }
  test("459 _ Redeclared Parameter: xxx") {
    val input = """void main () {}
    int foo(int a, int b, int a) {
      return false;
    }
    """
    val expected = "Redeclared Parameter: a"
    assert(checkCkr(input,expected,459))
  }
  test("460 _ ") {
    val input = """void main () {}
    int foo(int a, float a){
      return a;
    }
    """
    val expected = "Redeclared Parameter: a"
    assert(checkCkr(input,expected,460))
  }
  test("461 _ Redeclared Var in function decl") {
    val input = """void main () {

    }
    int foo(int a, int b) {
      int c, d, d;
      return 1+a;
    }
    """
    val expected = "Redeclared Variable: d"
    assert(checkCkr(input,expected,461))
  }
  test("462 _ Redeclared Var in function decl with parameter") {
    val input = """void main () {

    }
    int foo(int a, int b) {
      int c, d, a;
      return c;
    }"""
    val expected = "Redeclared Variable: a"
    assert(checkCkr(input,expected,462))
  }
  test("463 _ Type Mismatch In Expression function foo(int a)") {
    val input = """
     int foo(int a){
        return 1;
    }
    void main () {
        float a;
        foo(a);
    }"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("a")))
    assert(checkCkr(input,expected,463))
  }
  test("464 _ ") {
    val input = """void main () {
        int a;
        foo(a);
    }"""
    val expected = "Undeclared Function: foo"
    assert(checkCkr(input,expected,464))
  }
  test("465 _ ") {
    val input = """
    int b;
    float foo(boolean t){return 1;}
    void main () {
      string a;
      foo(a);
    }"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("a")))
    assert(checkCkr(input,expected,465))
  }
  test("466 _ Undeclared Identifier: b") {
    val input = """
    void foo(int a){}
    void main () {foo(b);}"""
    val expected = "Undeclared Identifier: b"
    assert(checkCkr(input,expected,466))
  }
  test("467 _ ") {
    val input = """
    //void foo(int a){}
    void main () {int b;
      foo(b);
    }"""
    val expected = "Undeclared Function: foo"
    assert(checkCkr(input,expected,467))
  }
  test("468 _ the function and local variable have the same name") {
    val input = """
    int foo(int a){return a;}
    void main () {
      int foo;
      foo(foo);
    }"""
    val expected = "Undeclared Function: foo"
    assert(checkCkr(input,expected,468))
  }
  test("469 _ ") {
    val input = """
    int foo(){return 1;}
    void main () {
      boolean a;
      a = true;
      if(a)
        foo();
      else 
        getInt();
    }"""
    val expected = ""
    assert(checkCkr(input,expected,469))
  }
  test("470 _ Type Mismatch In Statement: ") {
    val input = """
    int foo(){return 1;}
    void main () {
      int a;
      a = 1;
      if(a)
        foo();
      else 
        getInt();
    }"""
    val expected = "Type Mismatch In Statement: "+If(Id("a"),CallExpr(Id("foo"),List()),Some(CallExpr(Id("getInt"),List())))
    assert(checkCkr(input,expected,470))
  }
  test("471 _ ") {
    val input = """
    int foo(int a) {
      return a+1;
    }
    void main () {
      return;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,471))
  }
  test("472 _ Function Not Return: ex1") {
    val input = """
    int foo(){int a;}
    void main () {}
    """
    val expected = "Function Not Return: foo"
    assert(checkCkr(input,expected,472))
  }
  test("473 _ Type Mismatch In Statement: boolean->int") {
    val input = """
    int foo(int a) {
      return true;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(BooleanLiteral(true)))
    assert(checkCkr(input,expected,473))
  }
  test("474 _ Type Mismatch In Statement: Return(Some(FloatLiteral(1.2)))") {
    val input = """
    int foo(int a) {
      return 1.2;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(FloatLiteral(1.2f)))
    assert(checkCkr(input,expected,474))
  }
  test("475 _ Type Mismatch In Statement: Return(Some(FloatLiteral(1.2)))") {
    val input = """
    boolean foo(int a) {
      return 1.2;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(FloatLiteral(1.2f)))
    assert(checkCkr(input,expected,475))
  }
  test("476 _ Type Mismatch In Statement: Return(Some(StringLiteral(string)))") {
    val input = """boolean foo(int a) {
      return "string";
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(StringLiteral("string")))
    assert(checkCkr(input,expected,476))
  }
  test("477 _ ") {
    val input = """int foo(int a) {
      return "string";
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(StringLiteral("string")))
    assert(checkCkr(input,expected,477))
  }
  test("478 _ Type Mismatch In Statement: Return(Some(IntLiteral(1)))") {
    val input = """boolean foo(int a) {
      return 1;
    }void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(IntLiteral(1)))
    assert(checkCkr(input,expected,478))
  }
  test("479 _ Type Mismatch In Statement: Return(Some(IntLiteral(1)))") {
    val input = """string foo(int a) {
      return 1;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(IntLiteral(1)))
    assert(checkCkr(input,expected,479))
  }
  test("480 _ Type Mismatch In Statement: Return(Some(BinaryOp(+,Id(a),FloatLiteral(1.0))))") {
    val input = """
    int foo(int a){
      return a+1.0;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(BinaryOp("+",Id("a"),FloatLiteral(1.0f))))
    assert(checkCkr(input,expected,480))
  }
  test("481 _ Type Mismatch In Statement: Return(Some(Id(a)))") {
    val input = """
    boolean foo(int a){
      return a;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(Id("a")))
    assert(checkCkr(input,expected,481))
  }
  test("482 _ Type Mismatch In Expression: BinaryOp(=,BinaryOp(+,Id(a),Id(b)),Id(c))") {
    val input = """void main () {
      int a;
      float b;
      string c;
      a+b=c;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",BinaryOp("+",Id("a"),Id("b")),Id("c"))
    assert(checkCkr(input,expected,482))
  }
  test("483 _ ") {
    val input = """void main () {
      int a;
      boolean b;
      if(a) b;
    }"""
    val expected = "Type Mismatch In Statement: "+If(Id("a"),Id("b"),None)
    assert(checkCkr(input,expected,483))
  }
  test("484 _ Type Mismatch In Expression: BinaryOp(=,Id(a),Id(b))") {
    val input = """void main () {
      int a;
      float b;
      a = b;
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),Id("b"))
    assert(checkCkr(input,expected,484))
  }
  test("485 _ Type Mismatch In Expression: BinaryOp(-,BinaryOp(+,Id(a),IntLiteral(1)),Id(c))") {
    val input = """void main () {int a;
      float b;
      boolean c;
      c = true;
      b = a+1-c;}"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("-",BinaryOp("+",Id("a"),IntLiteral(1)),Id("c"))
    assert(checkCkr(input,expected,485))
  }
  test("486 _ ") {
    val input = """void main () {
      boolean a;
      int b;
      a = true;
      b=1;
      if(!b) a;
    }"""
    val expected = "Type Mismatch In Expression: "+UnaryOp("!",Id("b"))
    assert(checkCkr(input,expected,486))
  }
  test("487 _ Type Mismatch In Expression: ArrayCell(Id(a),Id(c))") {
    val input = """void main () {
      int a[10], b;
      float c;
      b=1;
      a[b] = 4;
      a[c];
    }"""
    val expected = "Type Mismatch In Expression: "+ArrayCell(Id("a"),Id("c"))
    assert(checkCkr(input,expected,487))
  }
  test("488 _ Type Mismatch In Statement: Return(Some(Id(a)))") {
    val input = """
    int[] foo(int a){
      return a;
    }
    void main () {}"""
    val expected = "Type Mismatch In Statement: "+Return(Some(Id("a")))
    assert(checkCkr(input,expected,488))
  }
  test("489 _ ") {
    val input = """
    float[] foo(int a){
      float b[10];
      return b;
    }
    void main () {}"""
    val expected = ""
    assert(checkCkr(input,expected,489))
  }
  test("490 _ ") {
    val input = """int foo(){return 1;}
       int doo(int x){
          int foo;
          doo(foo);
          return foo;
       }
       void main () {}"""
    val expected = ""
    assert(checkCkr(input,expected,490))
  }
  test("491 _ Type Mismatch In Expression: CallExpr(Id(foo),List(IntLiteral(1),IntLiteral(2)))") {
    val input = """
    int foo(int a){
      return a;
    }
    void main () {
      foo(1,2);
    }"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2)))
    assert(checkCkr(input,expected,491))
  }
  test("492 _ ") {
    val input = """int foo(int a, int b){
      return a;
    }
    void main () {
      int b;
      float t;
      foo(b, t);
    }"""
    val expected = "Type Mismatch In Expression: "+CallExpr(Id("foo"),List(Id("b"),Id("t")))
    assert(checkCkr(input,expected,492))
  }
  test("493 _ Type Mismatch In Statement: Return(Some(ArrayCell(Id(c),IntLiteral(1))))") {
    val input = """
    int[]foo(){
      int c[2];
      return c[1];
    }
    void main () {
      int a[10];
      a = foo;
    }"""
    val expected = "Type Mismatch In Statement: "+Return(Some(ArrayCell(Id("c"),IntLiteral(1))))
    assert(checkCkr(input,expected,493))
  }
  test("494 _ well it can run totally") {
   val input = """
    int[]foo(){
      int b[1];
      return b;
    }
    void main () {
      int a[10];
      foo() = a;
    }"""
    val expected = ""
    assert(checkCkr(input,expected,494))
  }
  test("495 _ Type Mismatch In Expression: BinaryOp(=,Id(a),CallExpr(Id(foo),List()))") {
    val input = """
    string[]foo(){
      string c[2];
      return c;
    }
    void main () {
      int a[10];
      a = foo();
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",Id("a"),CallExpr(Id("foo"),List()))
    assert(checkCkr(input,expected,495))
  }
  test("496 _ Type Mismatch In Expression: BinaryOp(=,ArrayCell(Id(a),IntLiteral(1)),ArrayCell(Id(b),IntLiteral(1)))") {
    val input = """void main () {
      int a[1];
      float b[2];
      a[1] = b[1];
    }"""
    val expected = "Type Mismatch In Expression: "+BinaryOp("=",ArrayCell(Id("a"),IntLiteral(1)),ArrayCell(Id("b"),IntLiteral(1)))
    assert(checkCkr(input,expected,496))
  }
  test("497 _ this is good functions too") {
       val input =
     """
       int a;
       float b;
       string c[5];
       boolean d;
       float[] foo(int x, int y){
         float a[1];
         int b;
         return a;
       }
       int doo(int a, float b, string c){
        return 10;
       }
       int hoo(){
         doo(1,3.4,"ahihi");
         return a;
       }

     """
    val expected = ""
    assert(checkCkr(input,expected,497))
  }
 test("498 void sum boolean"){
   val input =
     """
       void sum (int a, int b) { if(a+b&&a>b) a+b;}
     """
   val expected ="Type Mismatch In Expression: "+BinaryOp("&&",BinaryOp("+",Id("a"),Id("b")),BinaryOp(">",Id("a"),Id("b")))
   assert(checkCkr(input,expected,498))
 }
 test("498 assign 2 difference side"){
   val input =
     """
       void sum (int a, int b) { 5=4; 10.3=2;}
     """
    val expected = ""
    assert(checkCkr(input,expected,498))
  }
  test("499 _ this is another good function") {
       val input =
     """
       int arr[5];
       int len(int a){return a;}
       int len1(int a){return a+1;}
       int main(int arr1[],int arr2[]){
         int num1,num2;
         arr1[10] = arr1[len(len1(arr2[3]))] + arr2[arr1[num1+num2]];
         return 0;
       }

       int[] foo(){
         return arr;
        }
     """
    val expected = ""
    assert(checkCkr(input,expected,499))
  }
  test("500 _ this a good function") {
       val input =
     """
   int a;
   int barr[5];
   int c;
   float d;
   int[] foo(int a){
     int arr[5];
     int b;
     a = 123;
      if((a+b) == 3) 3; else 5;
     return barr;
   }

   int[] doo(int a){
     barr = foo(1);
     return barr;
   }
     """
    val expected = ""
    assert(checkCkr(input,expected,500))
  }
}
