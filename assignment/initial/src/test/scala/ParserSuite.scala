import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class ParserSuite  extends FunSuite with TestParser {

  test("101") {
    val input = """int low, high, i, flag;

                          void main()
                          {
                              printf("Enter two numbers(intervals): ");
                              scanf("%d %d", low, high);

                              printf("Prime numbers between %d and %d are: ", low, high);

                              do
                                  {flag = 0;
                                  for(i = 2; i <= low / 2; i = i + 1)
                                  {
                                      if(low / i == 0)
                                      {
                                          flag = 1;
                                          break;
                                      }
                                  }

                                  if (flag == 0)
                                      printf("%d ", low);

                                  low = low + 1;
                            } while (low < high);

                           }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,101))
  }
  test("102") {
    val input ="""int main(){
      if(a) 
        return a; 
      else 
        f(b+2);
    }"""
    val expect ="sucessful"
    assert(checkRec(input,expect,102))
  }
  test("103"){
    val input = """void main () {
                   // start declaration part
                   int a , b , c ;
                   float f[5] ;
                   //end declaration part

                   // start statement part
                   a = b = 2;
                   if (a = b) f[0]=1.0 ;
                   //end statement part
                   }"""
    val expect = """sucessful"""
    assert(checkRec(input,expect,103))
  }

  test("104"){
    val input = """int main()
{
  boolean check;
  check=false;
  if(check==false) return 9; else return 3;
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,104))
  }

  test("105") {
    val input ="""int main () }
        putLn();
    }"""
    val expect ="Error on line 1 col 13: }"
    assert(checkRec(input,expect,105))
  }
  test("106") {
    val input ="""int i,j,k[5];"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,106))
  }
  test("107") {
    val input ="""int foo() {}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,107))
  }
  test("108") {
    val input ="""float f = 1.0 ;"""
    val expect ="""Error on line 1 col 9: ="""
    assert(checkRec(input,expect,108))
  }
  test("109") {
    val input ="""float f ;"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,109))
  }
  test("110") {
    val input ="""int f[];"""
    val expect ="""Error on line 1 col 7: ]"""
    assert(checkRec(input,expect,110))
  }
  test("111") {
    val input ="""void foo ( int i ) {
          int child_of_foo ( float f ) {}
    }"""
    val expect ="""Error on line 2 col 28: ("""
    assert(checkRec(input,expect,111))
  }
  test("112") {
    val input ="""int foo(){
        boolean b ; 
        int i ;
        i =3;
        if (i >0) putInt(i);
        else putIntLn(0);

    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,112))
  }
  test("113") {
    val input ="""int foo(){
        int a , b , c ;
        float f [5] ;
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,113))
  }
  test("114") {
    val input ="""int foo(){
      //s
      int a , b , c ;
      float f [5] ;
      //end
      a=b=2;
      if (a=b) f [0] = 1.0 ;
      else a=3;
        //end statement part
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,114))
  }
  test("115") {
    val input ="""
      int i ;
      int f () {
        return 200;
      }
      void main () { 
        int main;
        main = f () ;
        putIntLn (i) ;
        {
          int i ;
          int main ;
          int f ;
          main = f = i = 100;
          putIntLn (i) ;
          putIntLn(main) ;
          putIntLn (f) ;
        }
        putIntLn (main) ;
      }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,115))
  }
  test("116") {
    val input ="""void f(int a[10]) { }"""
    val expect ="""Error on line 1 col 14: 10"""
    assert(checkRec(input,expect,116))
  }
  test("117") {
    val input ="""
    int a,b,c;
    int foo(){
      a=b=c=2;
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,117))
  }
  test("118") {
    val input ="""int foo(){ a = b == c = d == e ;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,118))
  }
  test("119") {
    val input ="""int foo(){ a = b == c = d ;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,119))
  }
  test("120") {
    val input ="""int foo(){ a == c + d ;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,120))
  }
  test("121") {
    val input ="""int foo(){ 1=2=3=4 ;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,121))
  }
  test("122") {
    val input ="""int foo(){3[a+1];}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,122))
  }
  test("123") {
    val input ="""int foo(){+;}"""
    val expect ="""Error on line 1 col 11: +"""
    assert(checkRec(input,expect,123))
  }
  test("124") {
    val input ="""int foo(){;}"""
    val expect ="""Error on line 1 col 11: ;"""
    assert(checkRec(input,expect,124))
  }
  test("125") {
    val input ="""int main(){
      int a,b;
      if (a==b) b<c ;
      else c=b;
       do {
        int a;
        a=2;
        c=3;
      }while(a==b);
    for (a[]; ; ){
      b=2;
      b=c;
      break;
    }
    return 2;
    }"""
    val expect ="""Error on line 10 col 12: ]"""
    assert(checkRec(input,expect,125))
  } 
  test("126") {
    val input = """int main(){
      int a,b;
      if (a==b) b<c ;
      else c=b;
       do {
        int a;
        a=2;
        c=3;
      }while(a==b);
    for (c; c<2; c=c+1){
      b=2;
      b=c;
      break;
    }
    return 2;
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,126))
  }
  test("127") {
    val input ="""int foo(){if (a==b) b<c ;
      else c=b;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,127))
  }
  test("128") {
    val input ="""int foo(){as12[12][12];}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,128))
  }
  test("129") {
    val input ="""void func(){
do 
string a;
while true;
}"""
    val expect ="""Error on line 3 col 9: ;"""
    assert(checkRec(input,expect,129))
  }
  test("130") {
    val input ="""int foo(){for (1;i<5;i=i+1){a=2+a;}}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,130))
  }
  test("131") {
    val input ="""int foo(){for (1.2e3;i<5;i=i+1){a=a+asd[12];}}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,131))
  }
  test("132") {
    val input ="""int foo(){for (i =1 ;i<5;i=i+1.2e3){int a=2+a;}}"""
    val expect ="""Error on line 1 col 42: ="""
    assert(checkRec(input,expect,132))
  }
  test("133") {
    val input ="""int a(){
      int a;
      int e[1];
      if(a){
        a=a+1;
      }
      else{
        try(something);
        {
          do{
            a=-1+a*a;
          }while a!=0;
        }
        catch(e);
        print(e);
      }
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,133))
  }
  test("134") {
    val input ="""int foo(){
      if(isString("A")){
        d=a+d;
      }
      else{
        return a;
      }
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,134))
  }
  test("135") {
    val input ="""boolean[3] func(){}"""
    val expect ="""Error on line 1 col 9: 3"""
    assert(checkRec(input,expect,135))
  }
  test("136") {
    val input ="""
    int a, b, c[3];
    boolean[] foo(){
      print("22abc");
      for(a=2; a<100; a=10+3.e-12){
        if(foo()){
          println("string is here!"); /*comment
          this line and newline*/
          //comment a line
          a=child_of_foo(a+a*2);
        }
        else{
          TestParser(a+a__);
        }
      }
    }

    int main(){    
        if(foo(foo())){
          exit();
        }
        else
          return foo();
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,136))
  }
  test("137") {
    val input ="""int a;
void add(int a){}
int b
int[] main () {}"""
    val expect ="""Error on line 4 col 1: int"""
    assert(checkRec(input,expect,137))
  }
  test("138") {
    val input = """ int main(){
int a,c;
do a= 0;
while ()
return 0;
}"""
    val expect ="""Error on line 4 col 8: )"""
    assert(checkRec(input,expect,138))
  }
  test("139") {
    val input =""" int main(){
int a;
if(true) return;;
}"""
    val expect ="""Error on line 3 col 17: ;"""
    assert(checkRec(input,expect,139))
  }
  test("140") {
    val input ="""int foo() {a[[];}"""
    val expect ="""Error on line 1 col 15: ]"""
    assert(checkRec(input,expect,140))
  }
  test("141") {
    val input ="""int foo() {a[[1]]}"""
    val expect ="""Error on line 1 col 18: }"""
    assert(checkRec(input,expect,141))
  }
  test("142") {
    val input ="""int f[] _Adb;"""
    val expect ="""Error on line 1 col 7: ]"""
    assert(checkRec(input,expect,142))
  }
  test("143") {
    val input ="""int foo(1,2,3,....){}"""
    val expect ="""Error on line 1 col 9: 1"""
    assert(checkRec(input,expect,143))
  }
  test("144") {
    val input ="""int foo() {a * b == (c - d == e);}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,144))
  }
  test("145") {
    val input ="""int foo() { a * b == c - d;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,145))
  }
  test("146") {
    val input ="""int foo() { (a * b == a ) + (c - d == e / 2);}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,146))
  }
  test("147") {
    val input ="""int foo() { a * b == ( c - d == e + 10 );}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,147))
  }
  test("148") {
    val input =""" int foo() { (a * b == a ); }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,148))
  }
  test("149") {
    val input ="""int foo() { a * b == a == a; }"""
    val expect ="""Error on line 1 col 24: =="""
    assert(checkRec(input,expect,149))
  }
  test("150") {
    val input ="""int foo() {a*b+c/(d+e);}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,150))
  }
  test("151") {
    val input ="""int foo() {a*b+c/d+e;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,151))
  }
  test("152") {
    val input ="""int foo() {a+b<c+d;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,152))
  }
  test("153") {
    val input ="""int foo() {a+(b<c)+d;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,153))
  }
  test("154") {
    val input ="""int foo() {a+(b==c<d)/2;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,154))
  }
  test("155") {
    val input ="""int foo() {b == c < d;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,155))
  }
  test("156") {
    val input ="""int foo() {(a+b)==(c+d)==e;}"""
    val expect ="""Error on line 1 col 24: =="""
    assert(checkRec(input,expect,156))
  }
  test("157") {
    val input ="""int foo() {a+b==(c+d)==e;}"""
    val expect ="""Error on line 1 col 22: =="""
    assert(checkRec(input,expect,157))
  }
  test("158") {
    val input = """int foo() {a + b == c + d == e;}"""
    val expect ="""Error on line 1 col 31: ;"""
    assert(checkRec(input,expect,158))
  }
  test("159") {
    val input ="""int foo() {a==c==d;}"""
    val expect ="""Error on line 1 col 16: =="""
    assert(checkRec(input,expect,159))
  }
  test("160") {
    val input ="""int foo() {a + b == c + (d == e);}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,160))
  }
  test("161") {
    val input ="""int foo() {a * b == c - d == e ;}"""
    val expect ="""Error on line 1 col 32: ;"""
    assert(checkRec(input,expect,161))
  }
  test("162") {
    val input ="""int foo() {a * b == c - d > a;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,162))
  }
  test("163") {
    val input ="""int foo() {a < b < c;}"""
    val expect ="""Error on line 1 col 18: <"""
    assert(checkRec(input,expect,163))
  }
  test("164") {
    val input ="""int foo() {a < b + c < e * f;}"""
    val expect ="""Error on line 1 col 29: ;"""
    assert(checkRec(input,expect,164))
  }
  test("165") {
    val input ="""int foo() {a < b + c;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,165))
  }
  test("166") {
    val input ="""int foo() {a + c < d * f;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,166))
  }
  test("167") {
    val input ="""int foo() {a+b*2;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,167))
  }
  test("168") {
    val input ="""int foo(){
    	a < c + d > a + 1.e-12*w+(h+a)*2;
    }"""
    val expect ="""Error on line 2 col 38: ;"""
    assert(checkRec(input,expect,168))
  }
  test("169") {
    val input ="""int foo() {a<c+d==a+1.e-12*w+(h+a)*2;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,169))
  }
  test("170") {
    val input ="""boolean[] train (boolean a[]) {return a;}"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,170))
  }
  test("171") {
    val input ="""void main () {
      int a[10];
      int i;
      for(i = 0; i <= length(a); i=i+1){
        show(a[i]);
        break;
      }
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,171))
  }
  test("172") {
    val input ="""void main () {
      int a[10];
      int i;
      for(i = 0; i <= length(a); i=i+1){
        show(a[i]);
        break;
      }
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,172))
  }
  test("173") {
    val input ="""void main () {
      //int x,a,b,c;
foo(2)[2+x] = a[b[2]] + c;
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,173))
  }
  test("174") {
    val input ="""void main () {
      string a ;
      a = "name of string \t \r + ok";
      show_of(a);
    }"""
    val expect ="""sucessful"""
    assert(checkRec(input,expect,174))
  }
// //   test("175") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,175))
// //   }
// //   test("176") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,176))
// //   }
// //   test("177") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,177))
// //   }
// //   test("178") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,178))
// //   }
// //   test("179") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,179))
// //   }
// //   test("180") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,180))
// //   }
// //   test("181") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,181))
// //   }
// //   test("182") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,182))
// //   }
// //   test("183") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,183))
// //   }
// //   test("184") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,184))
// //   }
// //   test("185") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,185))
// //   }
// //   test("186") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,186))
// //   }
// //   test("187") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,187))
// //   }
// //   test("188") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,188))
// //   }
// //   test("189") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,189))
// //   }
// //   test("190") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,190))
// //   }
// //   test("191") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,191))
// //   }
// //   test("192") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,192))
// //   }
// //   test("193") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,193))
// //   }
// //   test("194") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,194))
// //   }
// //   test("195") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,195))
// //   }
// //   test("196") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,196))
// //   }
// //   test("197") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,197))
// //   }
// //   test("198") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,198))
// //   }
// //   test("199") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,199))
// //   }
// //   test("200") {
// //     val input =""" """
// //     val expect =""" """
// //     assert(checkRec(input,expect,200))
// //   }
}












// import org.scalatest.FunSuite

// /**
//   * Created by nhphung on 4/28/17.
//   */
// class ParserSuite  extends FunSuite with TestParser {

//   test("101.A simple program") {
//     val input = """int main () {
//     int k[5],i,j;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,101))
//   }

//   test("102.More complex program") {
//     val input ="""int main () {
//   putIntLn(4);
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,102))
//   }

//   test("103.Wrong program"){
//     val input = "} int main {"
//     val expect = "Error on line 1 col 1: }"
//     assert(checkRec(input,expect,103))
//   }

//   test("104.Full program #1") {
//     val input = """
// float foo() {
//   return 0;
// }
// int main () {
//   main = f();
//   putIntLn (i);
//   {
//     int a ;
//     int main ;
//     int b ;
//     main = f = i = 5;
//   }
//   putIntLn ();
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,104))
//   }

//   test("105.Full program #2") {
//     val input = """int a ;
// int f ( ) {
//   return 1 ;
// }
// int b() {}
// int c() { return 0; }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,105))
//   }

//   test("106.Full program #3") {
//     val input = """int main()
// {
//     int a;
//     printf("Hello World\n");
//     return 0;
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,106))
//   }

//   test("107.Full program #4") {
//     val input = """int main()
// {
//    int number;
//    printf("Enter an integer\n");
//    scanf("%d",number);
//    printf("You enter: %d\n", number);
//    return 0;
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,107))
//   }

//   test("108.If statement test") {
//     val input = """int main()
// {
//    int a;
//    a = 1;
//    if ( a == 1 )
//       printf("a equal to one");
//    else
//       printf("assignment operator");
//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,108))
//   }

//   test("109.Value of Integer") {
//     val input = """void main()
// {
//    int value;

//    value = 1;

//    for (i=1; i<=n; i = i+1)
//    {
//       printf("Value is %d\n", value);
//       value = value + 1;
//    }

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,109))
//   }

//   test("110.Invocation Expression #1"){
//     val input =
//       """int main (){
//           Foo(a + b / 2);
//         }
//       """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,110))
//   }

//   /////////////////////////////////////////////////////////////////////////////////////
//   test("111.Test invo #2"){
//     val input =
//       """int main (){
//           Foo(c - d / 4);
//         }
//       """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,111))
//   }
//   test("112.Nested Invo expression"){
//     val input =
//       """int main (){
//           Foo(a + b, a - b, a == 2);
//         }
//       """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,112))
//   }
//   test("113.Equalizer"){
//     val input =
//       """int main (){
//           Foo(a == b == c);
//         }
//       """
//     val expect = "Error on line 2 col 22: =="
//     assert(checkRec(input,expect,113))
//   }
//   test("114. One more Invo expression"){
//     val input =
//       """int main (){
//           foo((x + y)/10);
//         }
//       """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,114))
//   }
//   test("115.Expression testing"){
//     val input =
//       """int main (){
//           goo(x + y + 10);
//         }
//       """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,115))
//   }

//   test("116.Complex Program Challenger #1"){
//     val input = """
//                      float main()
//                      {
//                            do{
//                                a=a+9;
//                                if (a==10000)
//                                    a=a/a;
//                                    break;
//                                else
//                                a=a+81;
//                                {
//                                    int b;
//                                    b=10;
//                                    a = a * b;
//                                    for (i = 0 ; i < 100; i = i+1)
//                                    {
//                                        b = b+10;
//                                    }
//                                }
//                             }
//                            while a < 1412643;
//                      }"""
//     val expect = "Error on line 9 col 32: else"
//     assert(checkRec(input,expect,116))
//   }

//   test("117.Return !!!"){
//     val input = """
// int a;
//  int f(){
// return 1;
// {
//  int x;
//  int y;
// if (a>b) return 2;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,117))
//   }

//   ///////////////////////////////////////////////////////////////////////////////////
//   test("118.Example Scala #1 #1") {
//     val input = """void main()
// {
//    int n, c;

//    printf("Enter a number\n");
//    scanf("%d", n);

//    if ( n == 2 )
//       printf("Prime number.\n");
//    else
//    {
//        for ( c = 2 ; c <= n - 1 ; c= c+1 )
//        {
//            if ( n % c == 0 )
//               break;
//        }
//        if ( c != n )
//           printf("Not prime.\n");
//        else
//           printf("Prime number.\n");
//    }
//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,118))
//   }

//   test("119.Example Scala #1 #2") {
//     val input = """void main(int argc, string argv[])
// {
//    int c;

//    printf("Number of command line arguments passed: %d\n", argc);

//    for ( c = 0 ; c < argc ; c = c+1 )
//       printf("%d. Command line argument passed is %s\n", c+1, argv[c]);

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,119))
//   }


//   test("120.PPL Array #1") {
//     val input = """void main()
// {
//     int array[100], n, c;

//     printf("Enter the number of elements in array\n");
//     scanf("%d", n);

//     printf("Enter %d elements\n", n);

//     for ( c = 0 ; c < n ; c = c+1 )
//         scanf("%d", array[c]);

//     printf("Array elements entered by you are:\n");

//     for ( c = 0 ; c < n ; c = c+1 )
//         printf("array[%d] = %d\n", c, array[c]);

//     return 0;
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,120))
//   }

//   test("121.Main function") {
//     val input = """void main()
// {
//    printf("Main function.\n");

//    my_function();

//    printf("Back in function main.\n");

//    return 0;
// }

// void my_function()
// {
//    printf("Welcome to my function. Feel at home.\n");
// }
//   """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,121))
//   }

//   test("122.Main function #2") {
//     val input = """void main()
// {

// }
// void my_function()
// {
//    printf("Welcome to my function. Feel at home.\n");
// }
//   """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,122))
//   }

//   test("123.Main function #3") {
//     val input = """void main()
// {
//    printf("Main function.\n");

//    my_function();

//    printf("Back in function main.\n");

//    return 0;
// }

// void my_function()
// {
//    printf("Welcome to my function. Feel at home.\n");
// }
//   """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,123))
//   }

//   test("124.Main function") {
//     val input = """void main()
// {
//    printf("Main function.\n");

//    my_function();

//    printf("Back in function main.\n");

// }

//   """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,124))
//   }

//   test("125.Line Comment #1") {
//     val input = """void main()
// {
//    // Single line comment in c source code

//    printf("Good luck c programmer.\n");

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,125))
//   }

//   test("126.Promt") {
//     val input = """void programming()
// {
//     float constant;
//     string pointer;
// }

// void main()
// {

//    string strings[52];


//    printf("%f\n", variable,constant);
//    printf("%s\n", variable,pointer);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,126))
//   }

//   test("127.Long Terms") {
//     val input = """void main()
// {

//    printf("First %d terms of Fibonacci series are :-\n",n);

//    for ( c = 0 ; c < n ; c = c + 1 )
//    {
//       if ( c <= 1 )
//          next = c;
//       printf("%d\n",next);
//    }

//    return 0;
// }
//  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,127))
//   }

//   test("128.Computer Graphics #1") {
//     val input = """void main()
// {
//     int gd, gm;

//     initgraph(gd, gm,"C:\\TC\\BGI");


//     getch();
//     closegraph( );
//     return 0;
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,128))
//   }

//   test("129.Date of System") {
//     val input = """int main()
// {
//    string d;

//    getdate(d);

//    printf("Current system date is %d/%d/%d",d,da_day,d,da_mon,d,da_year);
//    getch();
//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,129))
//   }

//   test("130.Shutdown computer !!!") {
//     val input = """int main()
// {
//    string ch;
//    printf("Do you want to shutdown your computer now (y/n)\n");
//    scanf("%c", ch);

//    if (ch == "y" || ch == "Y")
//       system("C:\\WINDOWS\\System32\\shutdown -s");

//    return 0;
// }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,130))
//   }

//   test("131.Ip computer") {
//     val input = """ int main()
// {
//    system("C:\\Windows\\System32\\ipconfig");
//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,131))
//   }

//   test("132.Example Scala #1") {
//     val input = """int main()
// {
//    int a, b, c;

//    printf("Enter a and b where a + ib is the first complex number.\n");
//    printf("a = ");
//    scanf("%d", a,real);
//    printf("b = ");
//    scanf("%d", a,img);
//    printf("Enter c and d where c + id is the second complex number.\n");
//    printf("c = ");
//    scanf("%d", b,real);
//    printf("d = ");
//    scanf("%d", b,img);

//    creal = areal + breal;
//    cimg = aimg + bimg;

//    if ( cimg >= 0 )
//       printf("Sum of two complex numbers = %d + %di\n", creal, cimg);
//    else
//       printf("Sum of two complex numbers = %d %di\n", creal, cimg);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,132))
//   }

//   test("133.Random #1") {
//     val input = """ int main() {
//   int c, n;

//   printf("Nhap so ngau nhien tu 1 den 100\n");

//   for (i = 1; i <= 10; i = i+1) {
//     n = rand() % 100 + 1;
//     printf("%d\n", n);
//   }
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,133))
//   }

//   test("134.Delete Files") {
//     val input = """int main()
// {
//    int status;
//    string file_name[10];

//    printf("Nhap ten file ban muon delete\n");
//    gets(file_name);

//    status = remove(file_name);

//    if( status == 0 )
//       printf("%s file deleted successfully.\n",file_name);
//    else
//    {
//       printf("Unable to delete the file\n");
//    }
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,134))
//   }

//   test("135.Any Backs") {
//     val input = """ int main()
// {
//    int done;
//    int a;

//    printf("Nhan bat ky phim nao !!!\n");

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,135))
//   }

//   test("136.Copy files") {
//     val input = """ int main()
// {
//    string fs1, fs2, ft;
//    string ch, file1[20], file2[20], file3[20];


//    printf("Ghep thanh cong);

//    fclose(fs1);
//    fclose(fs2);
//    fclose(ft);

//    return 0;
// } """
//     val expect = "Unclosed String: Ghep thanh cong);"
//     assert(checkRec(input,expect,136))
//   }


//   test("137.Error Column"){
//     val input =
//       """int a;
//     void add(int a){}
//     int b
//     """
//     val expect = "Error on line 4 col 5: <EOF>"

//     assert(checkRec(input,expect,137))
//   }
//   test("138.Some complicated"){
//     val input = """ int main(){
//               int a,b,c,d;
//               do a = 0;
//               while()
//               return 1;
//               }"""
//     val expect = "Error on line 4 col 21: )"
//     assert(checkRec(input,expect,138))
//   }
//   test("139.Some thing else"){
//     val input = """int main(){
//               int a,b,c,d;
//               if(2) return 10;
//               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,139))
//   }

//   test("140.PPL #1") {
//     val input = "int a () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,140))
//   }

//   test("141.PPL #2") {
//     val input = "float a () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,141))
//   }

//   test("142.PPL #3") {
//     val input = "boolean a () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,142))
//   }

//   test("143.Complex statement #1") {
//     val input = "int[1] a () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,143))
//   }

//   test("144.Complex statement #2") {
//     val input = "int a (int b) {1;}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,144))
//   }

//   test("145.Complex statement #3") {
//     val input = "float a (int b) {10;}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,145))
//   }

//   test("146.Example #1") {
//     val input = "int a (int b) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,146))
//   }

//   test("147.Example #2") {
//     val input = "int a (int b) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,147))
//   }

//   test("148.Example Scala #1") {
//     val input = "int[] a (int b) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,148))
//   }

//   test("149.Example Scala #2") {
//     val input = "int a (int b, int c, boolean e) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,149))
//   }

//   test("150.Example Scala #3") {
//     val input = "float a (int b, int c, boolean e) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,150))
//   }

//   test("151.Example Scala #4") {
//     val input = "void a (int b, int c, boolean e) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,151))
//   }

//   test("152.Example Scala #5") {
//     val input = "boolean a (int b, int c, boolean e) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,152))
//   }
//   test("153.Example Scala #6") {
//     val input = "int[] a (int b, int c, boolean e) {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,153))
//   }

//   test("154.Example Scala #7") {
//     val input = """
//     void goo(float x[]){
//     float y[10];
//      int z[10];
//      foo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,154))
//   }

//   test("155.Example Scala #8") {
//     val input = """
//     void goo(float x){
//     float y;
//      int z[10];
//      foo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,155))
//   }

//   test("156.Example Scala #9") {
//     val input = """
//     void goo(int x[]){
//     int y[10];
//      int z[10];
//      foo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,156))
//   }

//   test("157.Example Scala #10") {
//     val input = """
//     void goo(int x){
//     int y;
//      int z[10];
//      foo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,157))
//   }

//   test("158.Example Scala #11") {
//     val input = """
//     void goo(boolean x){
//     boolean y;
//      int z[10];
//     foo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,158))
//   }

//   test("159.Example Scala #12") {
//     val input = """
//     void goo(float x[], float y[]){
//     float y[10];
//      float z[10];
//      goo(x); //CORRECT
//     foo(y); //CORRECT
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,159))
//   }

//   test("160.Example Scala #13"){
//     val input =
//       """
//         int main(){1;}
//         """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,160))
//   }

//   test("161.Example Scala #14"){
//     val input = """
//       void a(){
//         if(true) return 1;
//         else return 2;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,161))
//   }

//   test("162.Example Scala #15"){
//     val input = """
//       void a(){
//         if(true) return 1;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,162))
//   }

//   test("163.Example Scala #16"){
//     val input = """
//       void a(){
//         if(true) return 1;
//         else return 2;
//         else return 3;
//       }
//     """
//     val expect = "Error on line 5 col 9: else"
//     assert(checkRec(input,expect,163))
//   }

//   test("164.Example Scala #17"){
//     val input = """
//       void a(){
//         if(true) return 1;
//         if return 2;
//         else return 3;
//       }
//     """
//     val expect = "Error on line 4 col 12: return"
//     assert(checkRec(input,expect,164))
//   }

//   test("165.Example Scala #18"){
//     val input = """
//       void a(){
//         if(true) return 1
//         if return 2;
//         else return 3;
//       }
//     """
//     val expect = "Error on line 4 col 9: if"
//     assert(checkRec(input,expect,165))
//   }

//   test("166.Example Scala #19"){
//     val input = """
//       void a(){
//         if(true) return 1;
//         if (false) return 2
//         else return 3;
//       }
//     """
//     val expect = "Error on line 5 col 9: else"
//     assert(checkRec(input,expect,166))
//   }

//   test("167.Example Scala #20"){
//     val input = """
//       void a(){
//         if(true) return 1;
//         if (FALSE) return 2;
//         else return 3;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,167))
//   }

//   test("168.Example Scala #21"){
//     val input = """
//       void a(){
//         if(TRUE) return 1;
//         if (FALSE) return 2;
//         else return 3;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,168))
//   }

//   test("169.Example Scala #22"){
//     val input = """
//       void a(){
//         if true return 1;
//         if (true) return 2;
//         else return 3;
//       }
//     """
//     val expect = "Error on line 3 col 12: true"
//     assert(checkRec(input,expect,169))
//   }

//   test("170.Example Scala #23"){
//     val input = """
//       void a(){
//         if (true)  1;
//         if (false) return 2;
//         else return 3;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,170))
//   }

//   test("171.Example Scala #24"){
//     val input = """
//       void a(){
//         if (true)  1;
//         if (FALSE)  2;
//         else  3;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,171))
//   }

//   test("172.Example Scala #25"){
//     val input = """
//       void a(){
//         if (true)  1;
//         if (false) return 2;
//       }
//     """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,172))
//   }

//   test("173.Example Scala #26"){
//     val input = """
//       void a(){
//         if (true) ;
//         if (FALSE);
//         else 3;
//       }
//     """
//     val expect = "Error on line 3 col 19: ;"
//     assert(checkRec(input,expect,173))
//   }

//   test("174.Example Scala #27") {
//     val input = """ int main()
// {
//    string ch, source_file[20], target_file[20];
//    int source, target;

//    printf("Enter name of file to copy\n");
//    gets(source_file);

//    source = fopen(source_file, "r");

//    if( source == NULL )
//    {
//       printf("Press any key to exit...\n");
//       exit(EXIT_FAILURE);
//    }

//    printf("Enter name of target file\n");
//    gets(target_file);

//    target = fopen(target_file, "w");

//    if( target == NULL )
//    {
//       fclose(source);
//       printf("Press any key to exit...\n");
//       exit(EXIT_FAILURE);
//    }



//    printf("File copied successfully.\n");

//    fclose(source);
//    fclose(target);

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,174))
//   }

//   test("175.Example Scala #28") {
//     val input = """int main()
// {
//    string ch, file_name[25];
//    int fp;

//    printf("Enter the name of file you wish to see\n");
//    gets(file_name);

//    fp = fopen(file_name,"r"); // read mode

//    if( fp == NULL )
//    {
//       perror("Error while opening the file.\n");
//       exit(EXIT_FAILURE);
//    }

//    printf("The contents of %s file are :\n", file_name);



//    fclose(fp);
//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,175))
//   }

//   test("176.Example Scala #29") {
//     val input = """
// int i;
//  int f(){
// return 200;
// {
//  int a;
//  int b;
// if (a>b) return 1;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,176))
//   }

//   test("177.Example Scala #30") {
//     val input = """
// int i;
//  int f(){
// return 200;
// {
//  int a;
//  int b;
// if (a>b) return 1;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,177))
//   }

//   test("178.Example Scala #31") {
//     val input = """ int main()
// {
//    string first[100], second[100], temp;

//    printf("Enter the first string\n");
//    gets(first);

//    printf("Enter the second string\n");
//    gets(second);

//    printf("\nBefore Swapping\n");
//    printf("First string: %s\n",first);
//    printf("Second string: %s\n\n",second);

//    temp = malloc(100);

//    strcpy(temp,first);
//    strcpy(first,second);
//    strcpy(second,temp);

//    printf("After Swapping\n");
//    printf("First string: %s\n",first);
//    printf("Second string: %s\n",second);

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,178))
//   }

//   test("179.Example Scala #32") {
//     val input = """ int main()
// {
//    string strings[1000];

//    printf("Input a string to convert to lower case\n");
//    gets(strings);

//    printf("Input string in lower case: \"%s\"\n",strlwr(strings));

//    return  0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,179))
//   }

//   test("180.Example Scala #33") {
//     val input = """int main()
// {
//    string strings[100];

//    printf("Enter a string to convert it into upper case\n");
//    gets(strings);

//    upper_string(strings);

//    printf("Entered string in upper case is \"%s\"\n", strings);

//    return 0;
// }

// void upper_string(string s[]) {
//    int c;

//    {
//       if (s[c] >= "a" && s[c] <= "z") {
//          s[c] = s[c] - 32;
//       }
//       c = c + 1;
//    }
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,180))
//   }

//   test("181.Example Scala #34") {
//     val input = """int main()
// {
//    string text[1000], blank[1000];
//    int c, d;

//    printf("Enter some text\n");
//    gets(text);

//    {
//       if (text[c] == " ") {
//          int temp;
//          if (text[temp] != "\n") {
//             {
//                if (text[temp] == " " ) {
//                   c=c+1;
//                }
//                temp=temp+1;
//             }
//          }
//       }
//       blank[d] = text[c];
//       c=c+1;
//       d=c+1;
//    }

//    blank[d] = "\n";

//    printf("Text after removing blanks\n%s\n", blank);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,181))
//   }

//   test("182.Example Scala #35") {
//     val input = """  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,182))
//   }

//   test("183.Example Scala #36") {
//     val input = """int main()
// {
//    int no[26], n, c, t, x;

//    printf("Enter some text\n");
//    scanf("%s", input);

//    n = strlen(input);

//    /** Store how many times characters (a to z)
//       appears in input string in array */

//    for (c = 0; c < n; c)
//    {
//       ch = input[c] - "a";
//       no[ch];
//    }

//    t = 0;

//    /** Insert characters a to z in output string
//        that many number of times as they appear
//        in input string */

//    for (ch = "a"; ch <= "z"; ch)
//    {
//       x = ch - "a";

//       for (c = 0; c < no[x]; c)
//       {
//          output[t] = ch;
//          t;
//       }
//    }
//    output[t] = "\r";

//    printf("%s\n", output);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,183))
//   }

//   test("184.Example Scala #37") {
//     val input = """
// int i;
//  int f(){
// return 200;
// {
//  int a;
//  int b;
// if (a>b) return 1;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,184))
//   }

//   test("185.Example Scala #38") {
//     val input = """
// int i;
//  int f(){
// return 200;
// {
//  int a;
//  int b;
// if (a>b) return 1;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,185))
//   }

//   test("186.Example Scala #39") {
//     val input = """
// int i;
//  int f(){
// return 200;
// {
//  int a;
//  int b;
// if (a>b) return 1;
// else a;
//  }
// }
// void main(){
// int var;
// var=f();
//  putIntLn(i);
// {
// int i;
// i=3;
// if(i>0) putInt(i);
// }
// putIntLn(var);
// }
// """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,186))
//   }

//   test("187.Example Scala #40") {
//     val input = """int main()
// {
//   string a[100];
//   int n;

//   printf("Input a valid string to convert to integer\n");
//   scanf("%s", a);

//   n = toString(a);

//   printf("String  = %s\nInteger = %d\n", a, n);

//   return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,187))
//   }

//   test("188.Example Scala #41") {
//     val input = """ int main()
// {
//    string a[100], b[100];

//    printf("Enter the string to check if it is a palindrome\n");
//    gets(a);

//    strcpy(b,a);
//    strrev(b);

//    if (strcmp(a,b) == 0)
//       printf("Entered string is a palindrome.\n");
//    else
//       printf("Entered string is not a palindrome.\n");

//    return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,188))
//   }

//   test("189.Example Scala #42") {
//     val input = """int main()
// {
//    string arr[100];

//    printf("Enter a string to reverse\n");
//    gets(arr);

//    strrev(arr);

//    printf("Reverse of entered string is \n%s\n",arr);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,189))
//   }

//   test("190.Example Scala #43") {
//     val input = """int main()
// {
//    string a[1000], b[1000];

//    printf("Enter the first string\n");
//    gets(a);

//    printf("Enter the second string\n");
//    gets(b);

//    strcat(a,b);

//    printf("String obtained on concatenation is %s\n",a);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,190))
//   }

//   test("191.Example Scala #44") {
//     val input =
//       """int main()
// {
//    string source[1000], destination[1000];

//    printf("Input a string\n");
//    gets(source);

//    strcpy(destination, source);

//    printf("Source string:      \"%s\"\n", source);
//    printf("Destination string: \"%s\"\n", destination);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input, expect, 191))
//   }

//   test("192.Example Scala #45") {
//     val input = """int main()
// {
//    string a[100], b[100];

//    printf("Enter the first string\n");
//    gets(a);

//    printf("Enter the second string\n");
//    gets(b);

//    if (strcmp(a,b) == 0)
//       printf("Entered strings are equal.\n");
//    else
//       printf("Entered strings are not equal.\n");

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,192))
//   }

//   test("193.Example Scala #46") {
//     val input = """int main()
// {
//    string a[100];
//    int length;

//    printf("Enter a string to calculate it's length\n");
//    gets(a);

//    length = strlen(a);

//    printf("Length of entered string is = %d\n",length);

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,193))
//   }

//   test("194.Example Scala #47") {
//     val input = """ int main()
// {
//     string array[100];

//     printf("Enter a string\n");
//     scanf("%s", array);

//     printf("You entered the string %s\n",array);
//     return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,194))
//   }

//   test("195.Example Scala #48") {
//     val input = """ int main()
// {

//   printf("Enter the number of rows and columns of second matrix\n");
//   scanf("%d%d", p, q);

//   if (n != p)
//     printf("Matrices with entered orders can't be multiplied with each other.\n");
//   else
//   {
//     printf("Enter the elements of second matrix\n");

//     for (c = 0; c < p; c)
//       for (d = 0; d < q; d)
//         scanf("%d", second[c]);

//     for (c = 0; c < m; c) {
//       for (d = 0; d < q; d) {
//         for (k = 0; k < p; k) {
//           sum = sum + first[c]*second[k];
//         }

//         multiply[c] = sum;
//         sum = 0;
//       }
//     }

//     printf("Product of entered matrices:-\n");

//     for (c = 0; c < m; c) {
//       for (d = 0; d < q; d)
//         printf("%d\t", multiply[c]);

//       printf("\n");
//     }
//   }

//   return 0;
// } """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,195))
//   }

//   test("196.Example Scala #49") {
//     val input = """int main()
// {
//    int m, n, c, d, matrix[10], transpose[10];

//    printf("Enter the number of rows and columns of matrix\n");
//    scanf("%d%d", m, n);

//    printf("Enter the elements of matrix\n");

//    for (c = 0; c < m; c)
//       for(d = 0; d < n; d)
//          scanf("%d",matrix[c]);

//    for (c = 0; c < m; c)
//       for( d = 0 ; d < n ; d )
//          transpose[d] = matrix[c];

//    printf("Transpose of entered matrix :-\n");

//    for (c = 0; c < n; c) {
//       for (d = 0; d < m; d)
//          printf("%d\t",transpose[c]);
//       printf("\n");
//    }

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,196))
//   }

//   test("197.Simple") {
//     val input = """int main()
// {
//    int m;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,197))
//   }

//   test("198.Matrix #198") {
//     val input = """int main()
// {
//    int matrix[10], Chuyen_vi[10];
//    printf("Nhap dong va cot\n");
//    scanf("%d%d", m, n);
//    printf("Phan tu ma tran: \n");

//    for (c = 1; c < n; c) {
//       for (d = 1; d < m; d)
//          printf("%d\t",Chuyen_vi[c]);
//       printf("\n");
//    }

//    return 0;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,198))
//   }

//   test("199.Position #1") {
//     val input = """int main()
// {
//    int array[100];
//    printf("Nhap n: \n", n);
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,199))
//   }

//   test("200.Matrix #n") {
//     val input = """int main()
// {
//    int m, n, c, d, matrix[10], transpose[10];

//    printf("So cot / So dong : \n");
//    scanf("%d%d", m, n);

//    printf("Nhap ma tran\n");


//    printf("success :-\n");


//    return 1;
// }  """
//     val expect = "sucessful"
//     assert(checkRec(input,expect,200))
//   }

// }

// import org.scalatest.FunSuite

// /**
//   * Created by nhphung on 4/28/17.
//   */
// class ParserSuite  extends FunSuite with TestParser {

//   test("a simple program") {
//     val input = "int main () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,101))
//   }
//   test("more complex program") {
//     val input ="""int main () {
//   putIntLn(4);
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,102))
//   }
//   test("more complex program with _") {
//     val input ="""int main () {
//   _putIntLn(4);
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,103))
//   }
//   test("more complex program with _ number") {
//     val input ="""int main () {
//   _putIntLn123(4);
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,104))
//   }
//   test("wrong program"){
//     val input = "} int main {"
//     val expect = "Error on line 1 col 1: }"
//     assert(checkRec(input,expect,105))
//   }
// test("a simple variable") {
//     val input = "int  a ;"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,106))
//   }
//   test("a complex variable") {
//     val input = "int  a,b;"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,107))
//   }
//   test("a complex variable with _") {
//     val input = "int  a,b,_c;"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,108))
//   }
//   test("a complex variable with LSB") {
//     val input = "float a[2],b[7];"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,109))
//   }
//   test("more complex variable with [] , ") {
//     val input = "int a,b,c[5],d,e;"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,110))
//   }
//   test("Wrong: wrong variable with no  ") {
//     val input = "int  a[];"
//     val expect = "Error on line 1 col 8: ]"
//     assert(checkRec(input,expect,111))
//   }
//   test("a function declaration") {
//     val input ="""int main (int a) {
//   int b;
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,112))
//   }
//   test("more complex function declaration") {
//     val input ="""int main (int a) {
//   int b,c,d;
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,113))
//   }
//   test("more complex function declaration COMMA") {
//     val input ="""int main (int a,int b) {
//   int b,c,d;
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,114))
//   }
//   test("more complex function declaration with LSB ") {
//     val input ="""int main (int a[]) {
//   int b,c,d;
//   int f[2];
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,115))
//   }
//   test("more complex function declaration with LSB COMMA ") {
//     val input ="""int main (int a[],int b[]) {
//   int b,c,d;
//   int f[2], g[11], k[100];
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,116))
//   }
//   test("more  function declaration") {
//     val input ="""int main (int a,int b, int c) {
//   int b;
// }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,117))
//   }
//    test("Wrong: function in function ") {
//     val input ="""int main (int a) {
// int child_of_foo ( int i, float f ) { }
//     }"""
//     val expect ="Error on line 2 col 18: ("
//     assert(checkRec(input,expect,118))
//   }
//    test("Wrong: function with wrong para list") {
//     val input ="""int main (int a[2],int b[]) {
//   int b,c,d;
//   int f[2];
// }"""
//     val expect ="Error on line 1 col 17: 2"
//     assert(checkRec(input,expect,119))
//   }
//   test("Wrong: function with wrong para in COMMA") {
//     val input ="""int main (int a;int b) {
//   int b,c,d;
//   int f[2];
// }"""
//     val expect ="Error on line 1 col 16: ;"
//     assert(checkRec(input,expect,120))
//   }
//   test("a simple ASSIGN") {
//     val input ="""int main () {
//                   a=b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,121))
//   }
//   test("more complex ASSIGN") {
//     val input ="""int main () {
//                   a=b=c=d=e=f  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,122))
//   }
//   test("Wrong: wrong ASSIGN") {
//     val input ="""int main () {
//                   a=b=  ;
//                               }"""
//     val expect = "Error on line 2 col 25: ;"
//     assert(checkRec(input,expect,123))
//   }
//   test("a simple OR") {
//     val input ="""int main () {
//                   a||b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,124))
//   }
//   test("more complex OR") {
//     val input ="""int main () {
//                   a||b||c  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,125))
//   }
//   test("Wrong: wrong OR") {
//     val input ="""int main () {
// a||b||  ;
//                               }"""
//     val expect = "Error on line 2 col 9: ;"
//     assert(checkRec(input,expect,126))
//   }
//   test("a simple AND") {
//     val input ="""int main () {
//                   a&&b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,127))
//   }
//   test("more complex AND") {
//     val input ="""int main () {
//                   a&&b&&c  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,128))
//   }
//   test("Wrong: wrong AND ") {
//     val input ="""int main () {
// a&b  ;
//                               }"""
//     val expect = "Error Token &"
//     assert(checkRec(input,expect,129))
//   }
//   test("a simple EQUAL") {
//     val input ="""int main () {
//                   a==b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,130))
//   }
//   test("more complex EQUAL") {
//     val input ="""int main () {
//                   a==b;
//                   b==c;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,131))
//   }
//   test("Wrong: wrong EQUAL expression") {
//     val input ="""int main () {
// a==b==c  ;
//                               }"""
//     val expect = "Error on line 2 col 5: =="
//     assert(checkRec(input,expect,132))
//   }
//   test("a simple NOT EQUAL") {
//     val input ="""int main () {
//                   a!=b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,133))
//   }
//   test("more complex NOT EQUAL") {
//     val input ="""int main () {
//                   a!=b;
//                   b!=c;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,134))
//   }
//   test("Wrong: wrong NOT EQUAL ") {
//     val input ="""int main () {
// a!=b!=c  ;
//                               }"""
//     val expect = "Error on line 2 col 5: !="
//     assert(checkRec(input,expect,135))
//   }
//   test("a simple LESSTHAN") {
//     val input ="""int main () {
//                   a<b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,136))
//   }
//   test("more complex LESSTHAN") {
//     val input ="""int main () {
//                   a<b;
//                   b<c;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,137))
//   }
//   test("Wrong: wrong LESSTHAN ") {
//     val input ="""int main () {
// a<b<c  ;
//                               }"""
//     val expect = "Error on line 2 col 4: <"
//     assert(checkRec(input,expect,138))
//   }
//   test("a simple LESSTHAN_EQUAL") {
//     val input ="""int main () {
//                   a<=b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,139))
//   }
//   test("more complex LESSTHAN_EQUAL") {
//     val input ="""int main () {
//                   a<=b;
//                   b<=c;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,140))
//   }
//   test("Wrong: wrong LESSTHAN_EQUAL ") {
//     val input ="""int main () {
// a<=b<=c  ;
//                               }"""
//     val expect = "Error on line 2 col 5: <="
//     assert(checkRec(input,expect,141))
//   }
//   test("a simple GREATER") {
//     val input ="""int main () {
//                   a>b  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,142))
//   }
//   test("more complex GREATER") {
//     val input ="""int main () {
//                   a>b;b>c  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,143))
//   }
//   test("Wrong: wrong GREATER ") {
//     val input ="""int main () {
// a>b>c  ;
//                               }"""
//     val expect = "Error on line 2 col 4: >"
//     assert(checkRec(input,expect,144))
//   }
//   test("a simple GREATER_EQUAL") {
//     val input ="""int main () {
//                   c>=d  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,145))
//   }
//   test("more complex GREATER_EQUAL") {
//     val input ="""int main () {
//                   a>=b;b>=c  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,146))
//   }
//   test("Wrong: wrong GREATER_EQUAL ") {
//     val input ="""int main () {
// x>=y>=z  ;
//                               }"""
//     val expect = "Error on line 2 col 5: >="
//     assert(checkRec(input,expect,147))
//   }
// test("a simple ADDITION") {
//     val input ="""int main () {
//                   x+y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,146))
//   }
//   test("more complex ADDITION") {
//     val input ="""int main () {
//                   x+y+z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,149))
//   }
//   test("Wrong: wrong ADDITION ") {
//     val input ="""int main () {
// x+y>+z+  ;
//                               }"""
//     val expect = "Error on line 2 col 5: +"
//     assert(checkRec(input,expect,150))
//   }
//   test("a simple SUBTRACTION") {
//     val input ="""int main () {
//                   x-y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,151))
//   }
//   test("more complex SUBTRACTION") {
//     val input ="""int main () {
//                   x-y-z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,152))
//   }
//   test("Wrong: wrong SUBTRACTION ") {
//     val input ="""int main () {
// x-y-z-;
//                               }"""
//     val expect = "Error on line 2 col 7: ;"
//     assert(checkRec(input,expect,153))

//   }
//    test("a simple MULTIPLICATION") {
//     val input ="""int main () {
//                   x*y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,154))
//   }
//   test("more complex MULTIPLICATION") {
//     val input ="""int main () {
//                   x*y*z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,155))
//   }
//   test("Wrong: wrong MULTIPLICATION ") {
//     val input ="""int main () {
// a*b*b**aaaaaaaaaaaaa;
//                               }"""
//     val expect = "Error on line 2 col 7: *"
//     assert(checkRec(input,expect,156))
    
//   }
//   test("a simple DIVISION") {
//     val input ="""int main () {
//                   x/y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,157))
//   }
//   test("more complex DIVISION") {
//     val input ="""int main () {
//                   x/y/z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,158))
//   }
//   test("Wrong: wrong DIVISION CHECK ERROR") {
//     val input ="""int main () {
// a/b/c/)------;
//                               }"""
//     val expect = "Error on line 2 col 7: )"
//     assert(checkRec(input,expect,159))
    
//   }
//   test("a simple MODULUS") {
//     val input ="""int main () {
//                   x%y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,160))
//   }
//   test("more complex MODULUS") {
//     val input ="""int main () {
//                   x%y%z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,161))
//   }
//   test("Wrong: wrong MODULUS ") {
//     val input ="""int main () {
// x%y%z%....... ;
//                               }"""
//     val expect = "Error Token ."
//     assert(checkRec(input,expect,162))
    
//   }
//   test("a simple NOT") {
//     val input ="""int main () {
//                   x!y  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,163))
//   }
//   test("more complex NOT") {
//     val input ="""int main () {
//                   x!y!z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,164))
//   }
//   test("Wrong: wrong NOT ") {
//     val input ="""int main () {
// x!y!z!!!!!!  ;
//                               }"""
//     val expect = "Error on line 2 col 7: !"
//     assert(checkRec(input,expect,165))
    
//   }
//   test("more complex  ADDITION SUBTRACTION") {
//     val input ="""int main () {
//                   x+y-z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,166))
//   }
//   test("more complex  ADDITION MULTIPLICATION") {
//     val input ="""int main () {
//                   x+y*z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,167))
//   }
// test("more complex  ADDITION DIVISION") {
//     val input ="""int main () {
//                   x+y/z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,168))
//   }
//   test("more complex  ADDITION MODULUS") {
//     val input ="""int main () {
//                   x+y%z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,169))
//   }
//   test("more complex  ADDITION AND") {
//     val input ="""int main () {
//                   x+y&&z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,170))
//   }
//   test("more complex  ADDITION OR") {
//     val input ="""int main () {
//                   x+y!z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,171))
//   }
// test("more complex  ADDITION SUBTRACTION MULTIPLICATION") {
//     val input ="""int main () {
//                   x+y-z*f  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,172))
//   }
//   test("more complex  ADDITION SUBTRACTION MULTIPLICATION DIVISION") {
//     val input ="""int main () {
//                   x+y-z*f/j  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,173))

//   }
//   test("more complex  ADDITION SUBTRACTION MULTIPLICATION DIVISION MODULUS") {
//     val input ="""int main () {
//                   x+y-z*f/j&&k  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,174))
    
//   }
//   test("more complex  ADDITION SUBTRACTION MULTIPLICATION DIVISION MODULUS NOT") {
//     val input ="""int main () {
//                   x+y-z*f/j&&k!l  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,175))
    
//   }
//   test("more complex  OR ASSIGNs") {
//     val input ="""int main () {
//                   x||y=z  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,176))
    
//   }
//   test("more complex  OR ASSIGNs EQUAL") {
//     val input ="""int main () {
//                   x||y=z==k  ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,177))
    
//   }
//    test("a simple LSB") {
//     val input ="""int main () {
//                   x[x] ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,178))
//   }
//   test("more complex LSB ADDITION") {
//     val input ="""int main () {
//                   y+x[x] ;
//                               }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,179))
//   }
// test("Wrong: wrong LSB ADDITION CHECK ERROR") {
//     val input ="""int main () {
// [x] ;
//                               }"""
//     val expect = "Error on line 2 col 1: ["
//     assert(checkRec(input,expect,180))
//   }
//  test("a simple program with float") {
//     val input = "float main () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,181))
//   }
//   test("a simple program with string") {
//     val input = "string main () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,182))
//   }
//   test("a simple program with boolean") {
//     val input = "boolean main () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,183))
//   }
//   test("a simple program with void") {
//     val input = "void main () {}"
//     val expect = "sucessful"
//     assert(checkRec(input,expect,184))
//   }
// test("a simple program with statement if-else ") {
//     val input = """void main () {
//           if(a=2) x=1;
//           else x=0 ;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,185))
//   }
//   test("a simple program with statement if-noelse ") {
//     val input = """void main () {
//           if(a=2) x=1;
          
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,186))
//   }
//   test("more complex program with statement if-else ") {
//     val input = """void main () {
//           if(a=2)  if(a=2)x=1;
//           else x=0 ;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,187))
//   }
//   test("more complex program with statement if-noelse ") {
//     val input = """void main () {
//           if(a=2)  if(a=2)x=1;
         
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,188))
//   }
//    test("a simple program with statement do while ") {
//     val input = """void main () {
//       do if(a=2) x=1;
//          while x=0; 
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,189))
//   }
//   test("more complex program with statement do while ") {
//     val input = """void main () {
//       do if(a=2) if(a=2)x=1;
//          while x=0; 
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,190))
//   }
//    test("a simple program with statement for ") {
//     val input = """void main () {
//       for(x=1;x=2;x=3)x=10;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,191))
//   }
//   test("a simple program with statement break ") {
//     val input = """void main () {
//       for(x=1;x=2;x=3)x=10;
//       break;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,192))
//   }
//   test("a simple program with statement continue ") {
//     val input = """void main () {
//       for(x=1;x=2;x=3)x=10;
//       continue;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,193))
//   }
//   test("a simple program with statement return ") {
//     val input = """void main () {
//       for(x=1;x=2;x=3)x=10;
//       return;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,194))
//   }
//   test("a simple program with statement return expression") {
//     val input = """void main () {
//       for(x=1;x=2;x=3)x=10;
//       return 1;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,195))
//   }
//   test("a simple variable global") {
//     val input =""" int a;
//                     int main () {
//                      int b,c,d;
//               }"""
//     val expect ="sucessful"
//     assert(checkRec(input,expect,196))
//   }
//   test("a simple program with statement block Declaration part ") {
//     val input = """void main () {
//       int a,b,c ;
//       }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,197))
//   }
//   test("a simple program with statement block Statement part: ") {
//     val input = """void main()  {
      
//       int a,b,c;
//       float f[5];
      
//       a = b = 2 ;
//       if (a=b) f[0] = 1.0;
//     }"""
//     val expect = "sucessful"
//     assert(checkRec(input,expect,198))
//   }
//   test("a simple program with invocation expression CHECK ERROR") {
//     val input = """void main () {
//       void f(int a[10]) { }
//       }"""
//     val expect = "Error on line 2 col 7: void"
//     assert(checkRec(input,expect,199))
//   }
//   test("a simple program with index expression CHECK ERROR") {
//     val input = """void main () {
//       abc[123][123];
//       }"""
//     val expect = "Error on line 2 col 15: ["
//     assert(checkRec(input,expect,200))
//   }
// }