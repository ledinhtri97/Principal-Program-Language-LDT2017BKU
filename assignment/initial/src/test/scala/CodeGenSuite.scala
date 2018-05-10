import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/30/17.
  * Student: Le Dinh Tri
  * MSSV: 1513656
  */
class CodeGenSuite extends FunSuite with TestCodeGen {
//   test("simple program => print 11") {
//     val input = "void main () {putIntLn(11);}"
//     val expected = "11"
//     assert(checkCode(input,expected,501))
//   }
//   test("another simple program => print 125") {
//     val input = "void main () {putIntLn(125);}"
//     val expected = "125"
//     assert(checkCode(input,expected,502))
//   }
//   test("special program => print 0") {
//     val input = "void main () {putIntLn(000);}"
//     val expected = "0"
//     assert(checkCode(input,expected,503))
//   }
//   test("special program => print /string abc") {
//     val input ="""void main () {putStringLn("abc");}"""
//     val expected = "abc"
//     assert(checkCode(input,expected,504))
//   }
//   test("special program => print 2.1") {
//     val input ="""void main () {putFloatLn(2.1);}"""
//     val expected = "2.1"
//     assert(checkCode(input,expected,505))
//   }
//   test("special program => print 12 and 2.1") {
//     val input ="""void main () {putIntLn(12);
//      putFloatLn(2.1);}"""
//     val expected = "122.1"
//     assert(checkCode(input,expected,506))
//   }
//   test("special program => print boolean true") {
//     val input ="""void main () {putBoolLn(true);}"""
//     val expected = "true"
//     assert(checkCode(input,expected,507))
//   }
//   test("special program => print boolean false") {
//     val input ="""void main () {putBoolLn(false);}"""
//     val expected = "false"
//     assert(checkCode(input,expected,508))
//   }
//   test("special program 509 => print 12 and 2.1 without newline") {
//     val input ="""void main () {putInt(12);
//      putFloatLn(2.1);}"""
//     val expected = "122.1"
//     assert(checkCode(input,expected,509))
//   }
//   test("special program 510 => ") {
//     val input ="""void main () {
//         int a;
//         a = 2;
//         putIntLn(a);
//     }"""
//     val expected = "2"
//     assert(checkCode(input,expected,510))
//   }
//   test("special program 511 => ") {
//     val input ="""
//     int abc;
//     void foo (int a, boolean c) {
//       putIntLn(a);
//     }
//     void main(){foo(1,true);}"""
//     val expected = "1"
//     assert(checkCode(input,expected,511))
//   }
//   test("special program 512 => ") {
//     val input ="""int abc;
//     void foo (int a, boolean c) {
//       putIntLn(a);
//     }
//     void main(){
//       abc = 10;
//       foo(abc,true);
//       //putIntLn(abc);
//     }"""
//     val expected = "10"
//     assert(checkCode(input,expected,512))
//   }
//   test("special program 513 => ") {
//     val input ="""void main () {
//       int a,b;
//       b = 1;
//       a = b+5;
//       putIntLn(a);
//     }"""
//     val expected = "6"
//     assert(checkCode(input,expected,513))
//   }
//   test("special program 514 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       a = a+1;
//       putIntLn(a);
//     }"""
//     val expected = "2"
//     assert(checkCode(input,expected,514))
//   }
//   test("special program 515 => ") {
//     val input ="""void main () {
//         float x;
//         x = 1.2;
//         putFloatLn(x);
//     }"""
//     val expected = "1.2"
//     assert(checkCode(input,expected,515))
//   }
//   test("special program 516 => ") {
//     val input ="""void main () {
//       putIntLn(2+2);
//     }"""
//     val expected = "4"
//     assert(checkCode(input,expected,516))
//   }
//   test("special program 517 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 2;
//       b = 3;
//       putFloatLn(a+b/a-a*2);
//     }"""
//     val expected = "-1.0"
//     assert(checkCode(input,expected,517))
//   }
//   test("special program 518 => ") {
//     val input ="""void main () {
//       int a;
//       float b;
//       a=1;
//       b=1.3;
//       putFloatLn(a+b);
//     }"""
//     val expected = "2.3"
//     assert(checkCode(input,expected,518))
//   }
//   test("special program 519 => ") {
//     val input ="""void main () {
//       int a;
//       float b;
//       a=2;
//       b=1.3;
//       putFloatLn(b/a);
//     }"""
//     val expected = "0.65"
//     assert(checkCode(input,expected,519))
//   }
//   test("special program 520 => ") {
//     val input ="""void main () {
//       int a;
//       float b;
//       a=2;
//       b=1.3;
//       putFloatLn(b%a);
//     }"""
//     val expected = "1.3"
//     assert(checkCode(input,expected,520))
//   }
//   test("special program 521 => ") {
//     val input ="""void main () {
//       boolean a,b;
//       a = true;
//       b = false;
//       putBoolLn(a&&b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,521))
//   }
//   test("special program 522 => ") {
//     val input ="""void main () {
//       boolean a,b;
//       a = true;
//       b = false;
//       putBoolLn(a||b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,522))
//   }
//   test("special program 523 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a<b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,523))
//   }
//   test("special program 524 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a>b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,524))
//   }
//   test("special program 525 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a>=b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,525))
//   }
//   test("special program 526 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a<=b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,526))
//   }
//   test("special program 527 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a==b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,527))
//   }
//   test("special program 528 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = 2;
//       putBoolLn(a!=b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,528))
//   }
//   test("special program 529 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a < b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,529))
//   }
//   test("special program 530 => ") {
//     val input ="""void main () {
//       float a;
//       float b;
//       a = 1.2;
//       b = 1.1;
//       putBoolLn(a < b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,530))
//   }
//   test("special program 531 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a > b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,531))
//   }
//   test("special program 532 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a <= b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,532))
//   }
//   test("special program 533 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a >= b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,533))
//   }
//   test("special program 534 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a == b);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,534))
//   }
//   test("special program 535 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       putBoolLn(a != b);
//     }"""
//     val expected = "true"
//     assert(checkCode(input,expected,535))
//   }
//   test("special program 536 => ") {
//     val input ="""void main () {
//       float a;
//       int b;
//       a = 1.2;
//       b = 1;
//       //putBoolLn(a && b);
//     }"""
//     val expected = ""
//     assert(checkCode(input,expected,536))
//   }
//   test("special program 537 => ") {
//     val input ="""void main () {
//       float a;
//       a = 1.2;
//       putFloatLn(-a);
//     }"""
//     val expected = "-1.2"
//     assert(checkCode(input,expected,537))
//   }
//   test("special program 538 => ") {
//     val input ="""void main () {
//       boolean a;
//       a = true;
//       putBoolLn(!a);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,538))
//   }
//   test("special program 539 => ") {
//     val input ="""void main () {
//       int a[3];
      
//     }"""
//     val expected = ""
//     assert(checkCode(input,expected,539))
//   }
//   test("special program 540 => ") {
//     val input ="""
//     int a[3];
//     void main () {
//       //todo. init 'a' in init<>
//     }"""
//     val expected = ""
//     assert(checkCode(input,expected,540))
//   }
//   test("special program 541 => ") {
//     val input ="""void main () {
//       int a[3];
      
//       a[0] = 3;
//       putIntLn(a[0]);
//     }"""
//     val expected = "3"
//     assert(checkCode(input,expected,541))
//   }
//   test("special program 542 => ") {
//     val input ="""void main () {
//       int a[3],b[2];
  
//       b[0] = 0;
//       a[b[0]] = 5;
//       putIntLn(a[0]);
//     }"""
//     val expected = "5"
//     assert(checkCode(input,expected,542))
//   }
//   test("special program 543 => ") {
//     val input ="""void main () {
//       float a[10];int b;
//       b = 9;
      
//       a[b] = 10.0;

//       putFloatLn(a[9]);
      
//     }"""
//     val expected = "10.0"
//     assert(checkCode(input,expected,543))
//   }
//   test("special program 544 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       if(a<2){
//         putIntLn(a);
//       }
//     }"""
//     val expected = "1"
//     assert(checkCode(input,expected,544))
//   }
//   test("special program 545 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       if(a<2){
//         putIntLn(a);
//       }
//       else {
//         putIntLn(2);
//       }
//       //putIntLn(3);
//     }"""
//     val expected = "1"
//     assert(checkCode(input,expected,545))
//   }
//   test("special program 546 => ") {
//     val input ="""void main () {
//       boolean a;
//       a=true;
//       if(a) {
//         putInt(1);
//       }
//       else {
//         putInt(2);
//       }
//     }"""
//     val expected = "1"
//     assert(checkCode(input,expected,546))
//   }
//   test("special program 547 => ") {
//     val input ="""void main () {
//       boolean a;
//       a=true;
//       if(a) {
//         putInt(1);
//       }
//     }"""
//     val expected = "1"
//     assert(checkCode(input,expected,547))
//   }
//   test("special program 548 => ") {
//     val input ="""void main () {
//       boolean a;
//       a=true;
//       if(!a) {
//         putInt(1);
//       }
//       putInt(2);
//     }"""
//     val expected = "2"
//     assert(checkCode(input,expected,548))
//   }
//   test("special program 549 => ") {
//     val input ="""void main () {
//       boolean a;
//       a=true;
//       if(!a) {
//         putInt(1);
//       }
//       else{
//         putInt(2);
//       }
//     }"""
//     val expected = "2"
//     assert(checkCode(input,expected,549))
//   }
//   test("special program 550 => ") {
//     val input ="""void main () {
//       int a;
//       for(a=1;a<3;a=a+1){
//         putIntLn(a);
//       }
//     }"""
//     val expected = "12"
//     assert(checkCode(input,expected,550))
//   }
//   test("special program 551 => ") {
//     val input ="""void main () {
//       int a; boolean b;
//       a = 1; b = true;
//       for(a;b;a=a+1){
//         putIntLn(a);
//         if(a==3){
//           b = false;
//         }
//       }
//     }"""
//     val expected = "123"
//     assert(checkCode(input,expected,551))
//   }
//   test("special program 552 => ") {
//     val input ="""void main () {
//       int a; boolean b;
//       a = 1; b = true;
//       for(a;b;a=a+1){
//         putIntLn(a);
//         if(a==3){
//           break;
//         }
//       }
//     }"""
//     val expected = "123"
//     assert(checkCode(input,expected,552))
//   }
//   test("special program 553 => ") {
//     val input ="""void main () {
//       int a; boolean b;
//       a = 1; b = true;
//       for(a;b;a=a+1){
//         if(a==2) {
//           continue;
//         }
//         putIntLn(a);
//         if(a==3){
//           b = false;
//         }

//       }
//     }"""
//     val expected = "13"
//     assert(checkCode(input,expected,553))
//   }
//   test("special program 554 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       do {
//         putIntLn(a);
//         a = a+1;
//       }
//       while (a<5);
//     }"""
//     val expected = "1234"
//     assert(checkCode(input,expected,554))
//   }
//   test("special program 555 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       do {
//         putIntLn(a);
//         a = a+1;
//         if(a==3) {
//           break;
//         }
//       }
//       while (a<5);
//     }"""
//     val expected = "12"
//     assert(checkCode(input,expected,555))
//   }
//   test("special program 556 => ") {
//     val input ="""void main () {
//       int a;
//       a = 1;
//       do {
//         a = a+1;
//         if(a == 2) {
//           continue;
//         }
//         putIntLn(a);
//         if(a==4) {
//           break;
//         }
//       }
//       while (a<5);

//     }"""
//     val expected = "34"
//     assert(checkCode(input,expected,556))
//   }
//   test("special program 557 => ") {
//     val input ="""
//     int foo() {
//       return 5;
//     }
//     void main () {
//       putIntLn(foo());
//     }
    
//     """
//     val expected = "5"
//     assert(checkCode(input,expected,557))
//   }
//   test("special program 558 => ") {
//     val input ="""
//     string foo() {
//         return "foo";
//       }
//     void main () {
//       putStringLn(foo());
//     }"""
//     val expected = "foo"
//     assert(checkCode(input,expected,558))
//   }
//   test("special program 559 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = -2;
//       if(a>0 && b<0){
//         putStringLn("ok");
//       }

//     }"""
//     val expected = "ok"
//     assert(checkCode(input,expected,559))
//   }
//   test("special program 560 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = -2;
//       if(a>0 && b>0){
//         putStringLn("ok");
//       }
//       else {
//         putStringLn("no");
//       }
//     }"""
//     val expected = "no"
//     assert(checkCode(input,expected,560))
//   }
//   test("special program 561 => ") {
//     val input ="""
//     void main () {
//       putStringLn(foo());
//     }
//     string foo() {
//         return "foo";
//     }
//     """
//     val expected = "foo"
//     assert(checkCode(input,expected,561))
//   }
//   test("special program 562 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = -2;
//       if(a>0 || b>0){
//         putStringLn("ok");
//       }
//       else {
//         putStringLn("no");
//       }
//     }"""
//     val expected = "ok"
//     assert(checkCode(input,expected,562))
//   }
//   test("special program 563 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 1;
//       b = -2;
//       if(a<=0 && b>0){
//         putStringLn("ok");
//       }
//       else {
//         putStringLn("no");
//       }
//     }"""
//     val expected = "no"
//     assert(checkCode(input,expected,563))
//   }
//   test("special program 564 => ") {
//     val input ="""void main () {
//       int a, b;
//       a = 0;
//       b = -2;
//       if(a<=0 || b>0){
//         putStringLn("ok");
//       }
//       else {
//         putStringLn("no");
//       }
//     }"""
//     val expected = "ok"
//     assert(checkCode(input,expected,564))
//   }
//   test("special program 565 => ") {
//     val input ="""
//     int foo(int a){
//       if(a==1){
//         return 1;
//       }
//       else {
//         return 2;
//       }
//       return 3;
//     }
//     void main () {
//       putIntLn(foo(2));
//     }
    
//     """
//     val expected = "2"
//     assert(checkCode(input,expected,565))
//   }
//   test("special program 566 => ") {
//     val input ="""
//       int foo(int a){
//       if(a==1){
//         return 1;
//       }
//       else {
//         return 2;
//       }
//       //return 3;
//     }
//     void main () {
//       putIntLn(foo(2));
//     }"""
//     val expected = "2"
//     assert(checkCode(input,expected,566))
//   }
//   test("special program 567 => ") {
//     val input ="""void main () {
//         int a;
//         int b;
//         a=1;
//         b=2;
//         {
//           int c;
//           {
//             float d;
//             c = 3;
//             d = 4.3;
//             if(a>c) {
//                putBoolLn(b<d);
//             }
//             else {
//               putBoolLn(b>d);
//             } 
//           }
//         }
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,567))
//   }
//   test("special program 568 => ") {
//     val input ="""void main () {
//       putIntLn(foo(4));
//     }
//     int foo(int a){
//       if(a==0) return 1;
//       return a+foo(a-1);
//     }
//     """
//     val expected = "11" //4+3+2+1+1
//     assert(checkCode(input,expected,568))
//   }
//   test("special program 569 => ") {
//     val input ="""
//     int a;
//     void main () {
//       int b;
//       b = 3;
//       a = b;
//       putIntLn(a+1);
//     }
//     """
//     val expected = "4"
//     assert(checkCode(input,expected,569))
//   }
//   test("special program 570 => ") {
//     val input ="""
//     int a;
//     void main () {
//       a = 4;
//       putIntLn(foo(a));
//     }
//     int foo(int a){
//       if(a==0) return 1;
//       return a+foo(a-1);
//     }
//     """
//     val expected = "11"
//     assert(checkCode(input,expected,570))
//   }
//   test("special program 571 => ") {
//     val input ="""void main () {

//     }"""
//     val expected = ""
//     assert(checkCode(input,expected,571))
//   }


//   test("special program 572 => ") {
//     val input ="""
//     float a[5];
//     float c[5]; 
//     void main () {
//       int i;
//       for(i=0;i<5;i=i+1){
//         a[i]=i+1;
//       }
//       c = a;
//       for(i=0;i<5;i=i+1){
//         putFloatLn(c[i]);
//       }
//     }
//     """
//     val expected = "1.02.03.04.05.0"
//     assert(checkCode(input,expected,572))
//   }
//   test("special program 573 => ") {
//     val input ="""
//     int c;
//     float d[4];
//     // boolean c[2];

//     // void foo(int c){
//     //   int e;
//     //   e=1;
//     //   d[1];
//     //   putFloatLn(d[1]+3);
//     // }
//     void main () {
//       int a[5];
//       a[3]=3;
//       c=1;
//       d[0] = a[3];
//       putIntLn(a[3]-c);
//       putFloatLn(d[a[3]-c]);
//     }"""
//     val expected = "20.0"
//     assert(checkCode(input,expected,573))
//   }
//   test("special program 574 => ") {
//     val input ="""
//     float a[4];
//     void main () {
//       int a[4];
//       string c[3];
//       init();
//       c=x;
//       a[0]=3;
//       a[3]=a[0]+1;
//       putStringLn(c[2]);
//       putStringLn("decl");
//       putIntLn(a[3]);
//     }
//     string x[3];
//     void init(){
//       x[2]="def";
//       x[0]="declare";
//     }
//     """
//     val expected = "defdecl4"
//     assert(checkCode(input,expected,574))
//   }
//   test("special program 575 => ") {
//     val input ="""void main () {
//       string a[3];
//       a = init(a);
//       s=a;
//       putStringLn(s[1]);
//     }
//     string [] init(string a[]){
//       a[0]="1";
//       a[1]="2";
//       a[2]="3";
//       return a;
//     }
//     string s[3];
//     """
//     val expected = "2"
//     assert(checkCode(input,expected,575))
//   }
//   test("special program 576 => ") {
//     val input ="""void main () {
//       //int k =10;
//       //a[0]=3;
//       //a[1]=4;
//       //a[0]=1;
//       //int c = k;
//       a[0]=a[1]=4;
//       putIntLn(a[0]);
//       putIntLn(a[1]);
//     }
//     int a[3];
//     """
//     val expected = "44"
//     assert(checkCode(input,expected,576))
//   }
//   test("special program 577 => ") {
//     val input ="""void main () {
//       int i;
//       i=0;
//       do {
//         a[i] = i = i+1;
        
//       }while(i<3);
//       putFloatLn(a[1]);
//     }
//     float a[3];"""
//     val expected = "2.0"
//     assert(checkCode(input,expected,577))
//   }
//   test("special program 578 => ") {
//     val input ="""
//     void main () {
//       int i;
//       i=0;
//       do {
//         a[i] = a[i+1] = i = i+1;
//       }while(a[i-1]<2);
//       putFloatLn(a[1]);
//     }
//     float a[3];
//     """
//     val expected = "2.0"
//     assert(checkCode(input,expected,578))
//   }

//   test("special program 579 => ") {
//     val input ="""
//     int a[10];
//     float b;
//     boolean c;
//     void main () {
//       int i;
//       a[5]=8;
//       a[1]=2;
//       b=15.6;
//       for(i = a[0];i<a[5];i=i+a[1]) {
//         if(c || b-a[1] <= i+2)
//           break;
//         else {
//           putIntLn(i);
//           a[i] = 3;
//           a[i+1] = 4;
//         }
//       }
//       putIntLn(i);
//     }"""
//     val expected = "048"
//     assert(checkCode(input,expected,579))
//   }

//   test("special program 580 => ") {
//     val input ="""
//     string a[10];
//     int i;
//     void main () {
//       float k2[3];
//       i=0;
//       k2=init();
//       putStringLn(a[2]);
//       putFloatLn(k2[2]);
//       putIntLn(i);
//     }
//     float []init(){
//       float k[3];
//       do {
//         a[i] = "x";
//         k[i] = i*2.2/3;
//         i = i+1;
//         putIntLn(i);
//       } while(i != 3);
//       return k;
//     } 
//     """
//     val expected = "123x1.46666673"
//     assert(checkCode(input,expected,580))
//   }
//   test("special program 581 => ") {
//     val input ="""
//     string a[10];
//     int i;
//     void main () {
//       float k2[3];
//       i=0;
//       k2=init();
//       putStringLn(a[3]);
//       putFloatLn(k2[2]);
//       putIntLn(i);
//     }
//     float []init(){
//       float k[3];
//       do {
//         int o;
        
//         if(i<3)
//           k[i] = i*2.2/3;
//         //i = i+1;
//         for(o=0; o<3;i=i+1) {
//           putInt(o);
//           o=o+1;
//         }
//         a[i] = "x";
//         putIntLn(i);
//       } while(i != 3);
//       return k;
//     } 
//     """
//     val expected = ""
//     assert(checkCode(input,expected,581))
//   }
//   test("special program 582 => ") {
//     val input ="""void main () {
//         float k[3];
//         int i;
//         i = 1;
//         if(i>0)
//           k[i] = i*2.2/3;
//         putFloatLn(k[1]);
//     }"""
//     val expected = ""
//     assert(checkCode(input,expected,582))
//   }
//   test("special program 583 => ") {
//     val input ="""void main () {
//         float k[3];
//         int i;
//         i = 1;
//         k[2] = 3;
//         if(k[i+1]>0)
//           k[i] = i*2.2/3;
//         putFloatLn(k[1]);
//     }"""
//     val expected = "0.73333335"
//     assert(checkCode(input,expected,583))
//   }
//   test("special program 584 => ") {
//     val input ="""void main () {
//       boolean a[2];
//       if(a[1] = true)
//         putBoolLn(!a[1]);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,584))
//   }
//   test("special program 585 => ") {
//     val input ="""
//     boolean a[2];
//     void main () {
//     if(!a[1])
//         putBoolLn(a[1]);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,585))
//   }
//   test("special program 586 => ") {
//     val input ="""
//     boolean a[2];
//     void main () {
//     if(a[1]=true != a[0])
//         putBoolLn(a[0]);
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,586))
//   }
//   test("special program 587 => ") {
//     val input ="""
//     int a[10];
//     void main () {
//       int i;
//       i = 0;
//       do{
//         a[i]=i+2;//2 3 4
//         i=i+1;//0 1 2 3 4 5
//       }while(i!=5);
//       putIntLn(a[2]);
//     }"""
//     val expected = "4"
//     assert(checkCode(input,expected,587))
//   }
//   test("special program 588 => ") {
//     val input ="""void main () {
//       putFloatLn(.1E2);
//     }"""
//     val expected = "10.0"
//     assert(checkCode(input,expected,588))
//   }
//   test("special program 589 => ") {
//     val input ="""void main () {
//       putFloatLn(2.6 - 3.0);
//     }"""
//     val expected = "-0.4000001"
//     assert(checkCode(input,expected,589))
//   }
//   test("special program 590 => ") {
//     val input ="""string foo(string str){
// return goo();
// }
// string goo(){
// return "abc";
// }
// void main () {
// putString(foo("bcd"));
// }"""
//     val expected = "abc"
//     assert(checkCode(input,expected,590))
//   }
//   test("special program 591 => ") {
//     val input ="""
    
//     void main () {
//       int a ;
//       a= 1;
//        do 
//         putInt(a);
//       while(a<0);
//     }
//     """
//     val expected = "1"
//     assert(checkCode(input,expected,591))
//   }
//   test("special program 592 => ") {
//     val input ="""
//     int a[3];
//     void main () {
//       int a[3];
//       a[1] = 3;
//       init();
//       putInt(a[1]);
      
//     }
//     void init(){
//       a[1] = 2;
//       putInt(a[1]);
//     }"""
//     val expected = "23"
//     assert(checkCode(input,expected,592))
//   }
//   test("special program 593 => ") {
//     val input ="""
//     int a[3];
//     void main () {
//       int a[3];
//       a[1] = 3;
      
//       putInt(a[1]);
//       init();
//     }
//     void init(){
//       a[1] = 2;
//       putInt(a[1]);
//     }"""
//     val expected = "32"
//     assert(checkCode(input,expected,593))
//   }
//   test("special program 594 => ") {
//     val input ="""
//     int a[3];
//     float b[4];
//     void main () {
//       a[1] = 3;
//       b[1]=b[2] =a[1]=a[2]=1;
//       putFloat(b[2]);
//       putInt(a[1]);
//       putInt(a[2]);
//     }
    
//     """
//     val expected = "1.011"
//     assert(checkCode(input,expected,594))
//   }
//   test("special program 595 => ") {
//     val input ="""
//     int a[3];
//     float b[4];
//     void main () {
//       a[0] = 1;
//       if(a[0]>0) {
//         {
//           a[0] = 2;
//           {
//             putFloat(b[0]+a[0]);
//             b[0] = a[0] + 1.1;
//           }
//           do
//             putFloat(b[0]);
//           while(b[0]==1);
//         }
//       }
//     }"""
//     val expected = "2.03.1"
//     assert(checkCode(input,expected,595))
//   }
//   test("special program 596 => ") {
//     val input ="""
//     int a[3];
//     float b[4];
//     void main () {
//       a[0] = 1;
//       a[1] = 2;
//       b[0]=1;
//       b[1]=3;
//       do
//         putFloat(b[0]);
//       while(b[0]!=1);
//     }
//     """
//     val expected = "1.0"
//     assert(checkCode(input,expected,596))
//   }
//   test("special program 597 => ") {
//     val input ="""void main () {
//       putBool((1>2)||(1+2>3)||(1/2>1));
//     }"""
//     val expected = "false"
//     assert(checkCode(input,expected,597))
//   }
//   test("special program 598 => ") {
//     val input ="""
//     int a[3];
//     int [] foo(){
//       a[0] = 3;
//       return a;
//     }
//     void main () {
//       int i,x[3];
//       putInt(foo()[0]);
//       x[0] = 2;
//       putInt(x[0]);
//       x = foo();
//       putInt(foo()[0]);
//       putInt(x[0]);
//       // for(i=0;foo()[0]-1>0;i=i+1)
//       // {
//       //   
//       //   foo()[0]=0;
//       //   putInt(foo()[0];
//       // }
//     }"""
//     val expected = "3233"
//     assert(checkCode(input,expected,598))
//   }
//   test("special program 599 => ") {
//     val input ="""
//     int b[3];
//     void main () {
//       int i ;
//       i=1;
//       if(b[1] >= i)
//           {
//             i=i+2; 
//           }
//           else {
//             putIntLn(b[1]);
//             i=i+1;
//           }
//     }"""
//     val expected = "0"
//     assert(checkCode(input,expected,599))
//   }
//   test("special program 600 => ") {
//      val input = """
//     int a,b[5];
//     void main(){
//       // int i;
//       // i=0;
//       //  do
//       //  {
//       //   putInt(i);
//       //   {
//       //     int b[2];
//       //     b[1] = 3-i;
//       //     if(b[1] >= i)
//       //     {
//       //       i=i+2; 
//       //     }
//       //     else {
//       //       putIntLn(b[1]);
//       //       i=i+1;
//       //     }
//       //     i=i+1;
//       //   }  
//       //   putIntLn(b[1]);
//       //  }
//       //  while (i<5);
//       // b[3] = 1;
//       // putIntLn(b[3]);
//       string a;
//       a = "abc\tabc";
//       putString(a);
//     }
//     """
//     val expected = "1"
//     assert(checkCode(input,expected,600))
//   }




//   test("simple program => print 5") {
//     val input = "void main () {putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 501))
//   }
//   test("another simple program => print 125") {
//     val input = "void main () {putIntLn(125);}"
//     val expected = "125"
//     assert(checkCode(input, expected, 502))
//   }
//   test("special program => print 0") {
//     val input = "void main () {putIntLn(000);}"
//     val expected = "0"
//     assert(checkCode(input, expected, 503))
//   }

//   test("decl variable in function") {
//     val input = "void main(){int x;putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 504))
//   }

//   test("decl simple function") {
//     val input = "void main(){} void foo(){putIntLn(5);}"
//     val expected = ""
//     assert(checkCode(input, expected, 505))
//   }

//   test("simple user-defined function") {
//     val input = "void main(){foo();} void foo(){putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 506))
//   }
//   test("print result of simple binary + operator") {
//     val input = "void main(){putIntLn(1+3);}";
//     val expected = "4"
//     assert(checkCode(input, expected, 507))
//   }

//   test("simple program => print float 12.5") {
//     val input = "void main () {putFloatLn(12.5);}"
//     val expected = "12.5"
//     assert(checkCode(input, expected, 508))
//   }

//   test("another program => print float 12.5") {
//     val input = "void main () {putFloatLn(12.500000);}"
//     val expected = "12.5"
//     assert(checkCode(input, expected, 509))
//   }

//   test("special program => print float 0.0") {
//     val input = "void main () {putFloatLn(00000.00000);}"
//     val expected = "0.0"
//     assert(checkCode(input, expected, 510))
//   }

//   test("print result of binary addop with float literal") {
//     val input = "void main(){putFloatLn(1.0+3.0);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 511))
//   }
//   test("print result of binary addop with float and int") {
//     val input = "void main(){putFloatLn(1.0+3);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 512))
//   }

//   test("print result of binary addop with int and float") {
//     val input = "void main(){putFloatLn(5-1.0);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 513))
//   }

//   test("print result of funtion return a interger") {
//     val input =
//       """
//         |int foo()
//         |{
//         | return 5;
//         |}
//         |void main(){
//         | putIntLn(foo());
//         |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 514))
//   }
//   test("declare global variable") {
//     val input =
//       """
//         |int a;
//         |void main()
//         |{}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 515))
//   }
//   test("declare global int array") {
//     val input =
//       """
//         |int arr[5];
//         |void main(){}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 516))
//   }

//   test("assign value to local variable and print it") {
//     val input =
//       """|void main()
//          |{
//          | int x;
//          | x=5;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 517))
//   }

//   test("assign binary op + to local variable and print it") {
//     val input =
//       """|void main()
//          |{
//          | int x,y;
//          | x=5;
//          | y=x+5;
//          | putIntLn(y);
//          |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 518))
//   }

//   test("assign value to global variable and print it") {
//     val input =
//       """|int x;
//          |void main()
//          |{
//          | x=5;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 519))
//   }

//   test("assign binary add op to global variable and print it") {
//     val input =
//       """|int x,y;
//          |void main()
//          |{
//          | y=10;
//          | x=6;
//          | x=y-x;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "4"
//     assert(checkCode(input, expected, 520))
//   }
//   test("simple logic operator > with literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |boolean x;
//         |x=1>2;
//         |putBool(x);
//         |}
//       """.stripMargin
//     val expected = "false"
//     assert(checkCode(input, expected, 521))
//   }

//   test("simple logic operator < with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=100;
//         |y=10;
//         |x=y<z;
//         |putBool(x);
//         |putBool(z<y);
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 522))
//   }


//   test("simple logic operator <= with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=10;
//         |y=10;
//         |x=y<=z;
//         |putBool(x);
//         |putBool(10<=1);
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 523))
//   }

//   test("simple logic operator >= with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=10;
//         |y=10;
//         |x=y>=z;
//         |putBool(x);
//         |}
//       """.stripMargin
//     val expected = "true"
//     assert(checkCode(input, expected, 524))
//   }

//   test("simple logic operator == with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=100;
//         |y=10;
//         |x=y==z;
//         |putBoolLn(x);
//         |z=10;
//         |x=y==z;
//         |putBoolLn(x);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 525))
//   }

//   test("declare local array") {
//     val input =
//       """
//         |void main(){
//         |int x[4];
//         |}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 526))
//   }
//   test("declare and use local array") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[5];
//         |x[4]=4;
//         |putInt(x[4]);
//         |}
//       """.stripMargin
//     val expected = "4"
//     assert(checkCode(input, expected, 527))
//   }

//   test("use local array in binary add op") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[4];
//         |x[0]=5;
//         |x[1]=10;
//         |putInt(x[0]+5);
//         |putInt(x[0]-5);
//         |putInt(x[0]+x[1]);
//         |}
//       """.stripMargin
//     val expected = "10015"
//     assert(checkCode(input, expected, 528))
//   }

//   test("use local array in binary mul op") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[4];
//         |x[0]=5;
//         |x[1]=10;
//         |putInt(x[0]*5);
//         |putInt(x[0]/5);
//         |putInt(x[0]*x[1]);
//         |}
//       """.stripMargin
//     val expected = "25150"
//     assert(checkCode(input, expected, 529))
//   }
//   test("use binary % op with int literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |putInt(10%5);
//         |}
//       """.stripMargin
//     val expected = "0"
//     assert(checkCode(input, expected, 530))
//   }

//   test("declare and use variable in child scope") {
//     val input =
//       """
//         |void main()
//         |{
//         |{
//         | int x;
//         | x=10;
//         | putInt(x);
//         |}
//         |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 531))
//   }
//   test("declare and use variable in child scope and parent scope") {
//     val input =
//       """
//         |void main()
//         |{
//         |int y;
//         |y=10;
//         |{
//         | int x;
//         | x=10+y;
//         | putInt(x);
//         |}
//         |y=100;
//         |putInt(y);
//         |}
//       """.stripMargin
//     val expected = "20100"
//     assert(checkCode(input, expected, 532))
//   }
//   test("print elem in a global array") {
//     val input =
//       """
//         |int arr[4];
//         |void main()
//         |{
//         | arr[0]=10;
//         | putInt(arr[0]);
//         |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 533))
//   }

//   test("use global array in binary op") {
//     val input =
//       """
//         |int arr[4];
//         |void main()
//         |{
//         | arr[0]=10;
//         | arr[1]=4;
//         | putInt(arr[0]+arr[1]);
//         | putInt(arr[0]*arr[1]);
//         | putInt(arr[0]/arr[1]);
//         |}
//       """.stripMargin
//     val expected = "14402"
//     assert(checkCode(input, expected, 534))
//   }

//   test("use simple user-defined function") {
//     val input =
//       """
//         |int sum(int a,int b){
//         | return a+b;
//         |}
//         |void main()
//         |{
//         | putInt(sum(3,4));
//         |}
//       """.stripMargin
//     val expected = "7"
//     assert(checkCode(input, expected, 535))
//   }

//   test("use local array with user-defined function") {
//     val input =
//       """
//     |int arr[3];

//         |int sum(int a,int b){
//         | return a+b;
//         |}
//                 |void main()
//         |{
//         |arr[0]=10;
//         |arr[1]=2;
//         |putInt(sum(arr[0],arr[1]));
//         |}
//       """.stripMargin
//     val expected = "12"
//     assert(checkCode(input, expected, 536))
//   }

//   test("simple if condition") {
//     val input =
//       """
//         |void main()
//         |{
//         | if(1>2)
//         | {
//         | putBool(true);
//         | }
//         | else
//         | {
//         | putBool(false);
//         | }
//         |}
//       """.stripMargin
//     val expected = "false"
//     assert(checkCode(input, expected, 537))
//   }
//   test("another simple if statement") {
//     val input =
//       """
//         |void main()
//         |{
//         | if(1<2)
//         | {
//         | putBool(true);
//         | }
//         | else
//         | {
//         | putBool(false);
//         | }
//         |}
//       """.stripMargin
//     val expected = "true"
//     assert(checkCode(input, expected, 538))
//   }
//   test("print a string") {
//     val input =
//       """
//         |void main()
//         |{
//         |putString("hello");
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 539))
//   }
//   test("if statement with complex condition") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x,y;
//         |x=2000;
//         //x%8==0 && x%20==0;
//          if(x%8==0 && x%20==0)
//         |{
//         | putStringLn("x chia het cho 20 va 8");
//         |}
//         |}
//       """.stripMargin
//     val expected = "x chia het cho 20 va 8"
//     assert(checkCode(input, expected, 540))
//   }
//   test("assign string into a local variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |string str;
//         |str="hello";
//         |putString(str);
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 541))
//   }

//   test("assign string into a global variable") {
//     val input =
//       """
//         |string str;
//         |void main()
//         |{
//         |str="hello";
//         |putString(str);
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 542))
//   }

//   test("recursive function with if stmt") {
//     val input =
//       """
//         |int factor(int n)
//         |{
//         | if(n==0)
//         |  return 1;
//         |  else
//         |   return n*factor(n-1);
//         |}
//         |void main()
//         |{
//         |putInt(factor(5));
//         |}
//       """.stripMargin
//     val expected = "120"
//     assert(checkCode(input, expected, 543))
//   }

//   test("simple for") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "01234"
//     assert(checkCode(input, expected, 544))
//   }
//   test("simple do-while") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |   putInt(i);
//         |   i=i+1;
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "01234"
//     assert(checkCode(input, expected, 545))
//   }
//   test("simple for and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i%2==0)
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 546))
//   }
//   test("simple for with continue and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i%2==1)
//         |   continue;
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 547))
//   }
//   test("simple for with break and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i>3)
//         |   break;
//         | putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "0123"
//     assert(checkCode(input, expected, 548))
//   }
//   test("simple dowhile and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |
//         |  if(i%2==0)
//         |   putInt(i);
//         |   i=i+1;
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 549))
//   }

//   test("simple dowhile with continue and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |  i=i+1;
//         |  if(i%2==1)
//         |   continue;
//         |   putInt(i);
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "24"
//     assert(checkCode(input, expected, 550))
//   }

//   test("simple dowhile with break and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |  i=i+1;
//         |  if(i==3)
//         |   break;
//         |   putInt(i);
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "12"
//     assert(checkCode(input, expected, 551))
//   }

//   test("print negative int") {
//     val input =
//       """
//         |void main()
//         |{
//         | putInt(-5);
//         |}
//       """.stripMargin
//     val expected = "-5"
//     assert(checkCode(input, expected, 552))
//   }

//   test("print negative float ") {
//     val input =
//       """
//         |void main()
//         |{
//         | putFloat(-5.0);
//         |}
//       """.stripMargin
//     val expected = "-5.0"
//     assert(checkCode(input, expected, 553))
//   }

//   test("use not operator") {
//     val input =
//       """
//         |void main()
//         |{  
//         | // !true;
//         | putBool(!true);
//         | putBool(!false);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 554))
//   }

//   test("use negative op in binary operator") {
//     val input =
//       """
//         |void main()
//         |{
//         | int x;
//         | x=10--10+10;
//         | putInt(x);
//         |}
//       """.stripMargin
//     val expected = "30"
//     assert(checkCode(input, expected, 555))
//   }

//   test("use negative in array element") {
//     val input =
//       """
//         |void main()
//         |{
//         | int x[5];
//         | x[0]=100;
//         | putInt(-x[0]);
//         |}
//       """.stripMargin
//     val expected = "-100"
//     assert(checkCode(input, expected, 556))
//   }
//   test("assign negative literal into variable") {
//     val input =
//       """
//         |float y;
//         |void main()
//         |{
//         |int x;
//         |x=-100;
//         |y=-100.0;
//         |putIntLn(x);
//         |putFloatLn(y);
//         |}
//       """.stripMargin
//     val expected = "-100-100.0"
//     assert(checkCode(input, expected, 557))
//   }

//   test("use mul op with float literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |float x,y;
//         |x=10.0;
//         |y=2;
//         |putFloatLn(x+y);
//         |putFloatLn(x/y);
//         |putFloatLn(x*y+x/y);
//         |}
//       """.stripMargin
//     val expected = "12.05.025.0"
//     assert(checkCode(input, expected, 558))
//   }

//   test("compile add op,mul op, int,float") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x;
//         |float y;
//         |x=2;
//         |y=1.0;
//         |putFloatLn((x+y)/x);
//         |putFloatLn((x*y)/(x-y));
//         |putFloatLn((x+y)/x-(x*y)/(x-y));
//         |}
//       """.stripMargin
//     val expected = "1.52.0-0.5"
//     assert(checkCode(input, expected, 559))
//   }

//   test("use many logic operator") {
//     val input =
//       """
//         |void main()
//         |{
//         |boolean x,y;
//         |x=true;
//         |y=false;
//         |putBoolLn(false || false || false || x);
//         |putBoolLn(true && true && y);
//         |putBoolLn(1!=2);
//         |}
//       """.stripMargin
//     val expected = "truefalsetrue"
//     assert(checkCode(input, expected, 560))
//   }
//   test("use local array in for loop") {
//     val input =
//       """
//         |void main()
//         |{
//         |int arr[4];
//         |int i;
//         |//i=0;
//         |//arr[1]=i+1;
//         |for(i=0;i<4;i=i+1)
//         | arr[i]=i+1;
//         |for(i=0;i<4;i=i+1)
//         | putIntLn(arr[i]);
//         |}
//       """.stripMargin
//     val expected = "1234"
//     assert(checkCode(input, expected, 561))
//   }

//   test("use global array in for loop") {
//     val input =
//       """
//         |int arr[5];
//         |void main()
//         |{
//         |int i;
//         |for(i=0;i<5;i=i+1)
//         | arr[i]=i+1;
//         |for(i=0;i<5;i=i+1)
//         | putIntLn(arr[i]);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 562))
//   }
//   test("use local array int dowhile") {
//     val input =
//       """
//         |void main()
//         |{
//         | int arr[5];
//         | int i;
//         | i=0;
//         | do
//         | {
//         |   arr[i]=i+1;
//         |   i=i+1;
//         | } while (i<5);
//         | i=0;
//         | do
//         | {
//         |   putInt(arr[i]);
//         |   i=i+1;
//         | } while(i<5);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 563))
//   }
//   test("pass array to function") {
//     val input =
//       """
//         |void print(int arr[],int n)
//         |{
//         |int i;
//         |for(i=0;i<n;i=i+1)
//         |{
//         |putInt(arr[i]);
//         |}
//         |}
//         |void main()
//         |{
//         |int arr[5];
//         |int i;
//         |for(i=0;i<5;i=i+1)
//         | arr[i]=i+1;
//         |print(arr,5);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 564))
//   }

//   test("use for nested for") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i,j;
//         | int arr[5];
//         | for(i=1;i<=5;i=i+1)
//         | {
//         |   int s;
//         |   s=1;
//         |   for(j=1;j<=i;j=j+1)
//         |   {
//         |     s=s*j;
//         |   }
//         |   arr[i-1]=s;
//         | }
//         | for(i=0;i<5;i=i+1)
//         | {
//         |   putIntLn(arr[i]);
//         | }
//         |}
//       """.stripMargin
//     val expected = "12624120"
//     assert(checkCode(input, expected, 565))
//   }

//   test("function return a string") {
//     val input =
//       """
//         |string getText(int n){
//         | if(n%2==0)
//         |   return "so chan";
//         | else
//         |   return "so le";
//         |}
//         |void main()
//         |{
//         |putStringLn(getText(2));
//         |putStringLn(getText(1));
//         |}
//       """.stripMargin
//     val expected = "so chanso le"
//     assert(checkCode(input, expected, 566))
//   }

//   test("function return a float") {
//     val input =
//       """
//         |float increase(float val)
//         |{
//         | return val+1.0;
//         |}
//         |void main()
//         |{
//         | putFloatLn(increase(5.0));
//         |}
//       """.stripMargin
//     val expected = "6.0"
//     assert(checkCode(input, expected, 567))
//   }

//   test("function return a boolean") {
//     val input =
//       """
//         |boolean isOdd(int val)
//         |{
//         | return val % 2 ==1;
//         |}
//         |void main()
//         |{
//         |putBoolLn(isOdd(3));
//         |putBoolLn(isOdd(2));
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 568))
//   }
// //chu y
//   test("function return an global array") {
//     val input =
//       """
//         |int arr[5];
//         |int[] init()
//         |{
//         | int i;
//         | for (i=0;i<5;i=i+1)
//         |   arr[i]=i+1;
//         | return arr;
//         |}
//         |void main()
//         |{
//         |int x[5],i;
//         |//init();
//         |x=init();
//          |for(i=0;i<5;i=i+1)
//          |   putIntLn(x[i]);
//          |
//       }
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 569))
//   }

//   test("n-th fibonacci number function") {
//     val input =
//       """
//         |int fibonacci(int n)
//         |{
//         | if(n==1|| n==2)
//         |   return 1;
//         | else return fibonacci(n-1)+fibonacci(n-2);
//         |}
//         |void main()
//         |{
//         | putInt(fibonacci(5));
//         |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 570))
//   }

  test("call some diff user-defined function") {
//    val input =
//      """
//        |boolean isPrime(int n)
//        |{
//        |int i;//1
//        |for (i=2;i<n;i=i+1)
//        |{
//        | if(n%i==0)
//        |   return false;
//        |}
//        |return true;
//        |}
//        |void printAllPrime(int arr[],int n)
//        |{
//        | int i;
//        | for(i=0;i<n;i=i+1)
//        | {
//        |   if(isPrime(arr[i]))
//        |     putIntLn(arr[i]);
//        | }
//        |
//        |}
//        |void main()
//        |{
//        | int arr[10];
//        | int i;
//        | for(i=0;i<10;i=i+1)
//        |   arr[i]=i+2;
//        | printAllPrime(arr,10);
//        |}
//      """.stripMargin
//    val expected = "235711"
//    assert(checkCode(input, expected, 571))
//  }

  // test("implicit ordered add op") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |putIntLn(2-2-4);
  //       |}
  //     """.stripMargin
  //   val expected = "-4"
  //   assert(checkCode(input, expected, 572))
  // }

   test("operator ordered with () in add op") {
     val input =
       """
         |void main()
         |{
         |putIntLn(2-(2-4));
         |}
       """.stripMargin
     val expected = "4"
     assert(checkCode(input, expected, 573))
   }
  // test("implicit ordered mul operator") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       | putFloat(2.0/2.0/4.0);
  //       |}
  //     """.stripMargin
  //   val expected = "0.25"
  //   assert(checkCode(input, expected, 574))
  // }

  //   test("ordered mul operator with ()") {
  //     val input =
  //       """
  //         |void main()
  //         |{
  //         | putFloat(2.0/(2.0/4.0));
  //         |}
  //       """.stripMargin
  //     val expected = "4.0"
  //     assert(checkCode(input, expected, 575))
  //   }

  // test("assign local string var to local var") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       | string s1,s2;
  //       | s1="Hello";
  //       | s2=s1;
  //       | putString(s2);
  //       |}
  //     """.stripMargin
  //   val expected = "Hello"
  //   assert(checkCode(input, expected, 576))
  // }

  // test("assign global string var to local var") {
  //   val input =
  //     """
  //       |string s1;
  //       |void main()
  //       |{
  //       | string s2;
  //       | s1="Hello";
  //       | s2=s1;
  //       | putString(s2);
  //       |}
  //     """.stripMargin
  //   val expected = "Hello"
  //   assert(checkCode(input, expected, 577))
  // }

  // test("assign local string var to global var") {
  //   val input =
  //     """
        
  //       void main(){
  //         putStringLn(a[1]);
  //       }
  //       string a[3];
  //       """
  //   val expected = "null"
  //   assert(checkCode(input, expected, 578))
  // }
  // test("two assign operator in one stmt") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |int x,y;
  //       |x=y=1;
  //       |putInt(x);
  //       |putInt(y);
  //       |}
  //     """.stripMargin
  //   val expected = "11"
  //   assert(checkCode(input, expected, 579))
  // }
  // test("assign int var to var") {
  //   val input =
  //     """
  //       |int a,b;
  //       |void main()
  //       |{
  //       | int x,y,z;
  //       | x=a=1;
  //       | y=x;
  //       | z=a;
  //       | b=x;
  //       | putInt(y);
  //       | putInt(z);
  //       | putInt(b);
  //       |}
  //     """.stripMargin
  //   val expected = "111"
  //   assert(checkCode(input, expected, 580))
  // }
  // test("many assign operator in one stmt") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |int x,y,z,t;
  //       |x=y=z=t=1;
  //       |putInt(x);
  //       |putInt(y);
  //       |putInt(t);
  //       |putInt(z);
  //       |}
  //     """.stripMargin
  //   val expected = "1111"
  //   assert(checkCode(input, expected, 581))
  // }
  // test("two assign operator with float in one stmt") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |float x,y;
  //       |x=y=1.0;
  //       |putFloat(x);
  //       |putFloat(y);
  //       |}
  //     """.stripMargin
  //   val expected = "1.01.0"
  //   assert(checkCode(input, expected, 582))
  // }
  // test("two assign operator with string in one stmt") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |string x,y;
  //       |x=y="hello";
  //       |putString(x);
  //       |putString(y);
  //       |}
  //     """.stripMargin
  //   val expected = "hellohello"
  //   assert(checkCode(input, expected, 583))
  // }
  // test("test hidden variable of parent scope") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       | int x;
  //       | x=10;
  //       | {
  //       |   int x;
  //       |   x=100;
  //       |   putInt(x);
  //       | }
  //       | putInt(x);
  //       |}
  //     """.stripMargin
  //   val expected = "10010"
  //   assert(checkCode(input, expected, 584))
  // }
  // test("sample program in mc spec") {
  //   val input =
  //     """
  //       | int i ;
  //       | int f ( ) {
  //       | return 200;
  //       | }
  //       | void main ( ) {
  //       | int main ;
  //       | main = f ( ) ;
  //       | putIntLn ( main ) ;
  //       | {
  //       | int i ;
  //       | int main ;
  //       | int f ;
  //       | main = f = i = 100;
  //       | putIntLn ( i ) ;
  //       | putIntLn ( main ) ;
  //       | putIntLn ( f ) ;
  //       | }
  //       | putIntLn ( main ) ;
  //       | }
  //     """.stripMargin
  //   val expected = "200100100100200"
  //   assert(checkCode(input, expected, 585))
  // }
  // test("test boolean array") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |boolean arr[2];
  //       |arr[1]=true;
  //       |arr[0]=false;
  //       |putBool(arr[0]);
  //       |putBool(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "falsetrue"
  //   assert(checkCode(input, expected, 586))
  // }
  // test("test float array") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |float arr[2];
  //       |arr[1]=1.0;
  //       |arr[0]=2.0;
  //       |putFloat(arr[0]);
  //       |putFloat(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "2.01.0"
  //   assert(checkCode(input, expected, 587))
  // }

  // test("test string array") {
  //   val input =
  //     """
  //       |void main()
  //       |{
  //       |string arr[2];
  //       |arr[1]="1";
  //       |arr[0]="0";
  //       |putString(arr[0]);
  //       |putString(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "01"
  //   assert(checkCode(input, expected, 588))
  // }
  // test("global string array"){
  //   val input =
  //     """|string arr[2];void main()
  //       |{
  //       |arr[1]="1";
  //       |arr[0]="0";
  //       |putString(arr[0]);
  //       |putString(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "01"
  //   assert(checkCode(input, expected, 589))
  // }

  // test("test global boolean array") {
  //   val input =
  //     """
  //       |boolean arr[2];
  //       |void main()
  //       |{
  //       |arr[1]=true;
  //       |arr[0]=false;
  //       |putBool(arr[0]);
  //       |putBool(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "falsetrue"
  //   assert(checkCode(input, expected, 590))
  // }
  // test("test global float array") {
  //   val input =
  //     """
  //       |float arr[2];
  //       |void main()
  //       |{
  //       |arr[1]=1.0;
  //       |arr[0]=2.0;
  //       |putFloat(arr[0]);
  //       |putFloat(arr[1]);
  //       |}
  //     """.stripMargin
  //   val expected = "2.01.0"
  //   assert(checkCode(input, expected, 591))
  // }





// test("simple program => print 5") {
//     val input = "void main () {putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 501))
//   }
//   test("another simple program => print 125") {
//     val input = "void main () {putIntLn(125);}"
//     val expected = "125"
//     assert(checkCode(input, expected, 502))
//   }
//   test("special program => print 0") {
//     val input = "void main () {putIntLn(000);}"
//     val expected = "0"
//     assert(checkCode(input, expected, 503))
//   }

//   test("decl variable in function") {
//     val input = "void main(){int x;putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 504))
//   }

//   test("decl simple function") {
//     val input = "void main(){} void foo(){putIntLn(5);}"
//     val expected = ""
//     assert(checkCode(input, expected, 505))
//   }

//   test("simple user-defined function") {
//     val input = "void main(){foo();} void foo(){putIntLn(5);}"
//     val expected = "5"
//     assert(checkCode(input, expected, 506))
//   }
//   test("print result of simple binary + operator") {
//     val input = "void main(){putIntLn(1+3);}";
//     val expected = "4"
//     assert(checkCode(input, expected, 507))
//   }

//   test("simple program => print float 12.5") {
//     val input = "void main () {putFloatLn(12.5);}"
//     val expected = "12.5"
//     assert(checkCode(input, expected, 508))
//   }

//   test("another program => print float 12.5") {
//     val input = "void main () {putFloatLn(12.500000);}"
//     val expected = "12.5"
//     assert(checkCode(input, expected, 509))
//   }

//   test("special program => print float 0.0") {
//     val input = "void main () {putFloatLn(00000.00000);}"
//     val expected = "0.0"
//     assert(checkCode(input, expected, 510))
//   }

//   test("print result of binary addop with float literal") {
//     val input = "void main(){putFloatLn(1.0+3.0);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 511))
//   }
//   test("print result of binary addop with float and int") {
//     val input = "void main(){putFloatLn(1.0+3);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 512))
//   }

//   test("print result of binary addop with int and float") {
//     val input = "void main(){putFloatLn(5-1.0);}"
//     val expected = "4.0"
//     assert(checkCode(input, expected, 513))
//   }

//   test("print result of funtion return a interger") {
//     val input =
//       """
//         |int foo()
//         |{
//         | return 5;
//         |}
//         |void main(){
//         | putIntLn(foo());
//         |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 514))
//   }
//   test("declare global variable") {
//     val input =
//       """
//         |int a;
//         |void main()
//         |{}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 515))
//   }
//   test("declare global int array") {
//     val input =
//       """
//         |int arr[5];
//         |void main(){}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 516))
//   }

//   test("assign value to local variable and print it") {
//     val input =
//       """|void main()
//          |{
//          | int x;
//          | x=5;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 517))
//   }

//   test("assign binary op + to local variable and print it") {
//     val input =
//       """|void main()
//          |{
//          | int x,y;
//          | x=5;
//          | y=x+5;
//          | putIntLn(y);
//          |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 518))
//   }

//   test("assign value to global variable and print it") {
//     val input =
//       """|int x;
//          |void main()
//          |{
//          | x=5;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 519))
//   }

//   test("assign binary add op to global variable and print it") {
//     val input =
//       """|int x,y;
//          |void main()
//          |{
//          | y=10;
//          | x=6;
//          | x=y-x;
//          | putIntLn(x);
//          |}
//       """.stripMargin
//     val expected = "4"
//     assert(checkCode(input, expected, 520))
//   }
//   test("simple logic operator > with literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |boolean x;
//         |x=1>2;
//         |putBool(x);
//         |}
//       """.stripMargin
//     val expected = "false"
//     assert(checkCode(input, expected, 521))
//   }

//   test("simple logic operator < with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=100;
//         |y=10;
//         |x=y<z;
//         |putBool(x);
//         |putBool(z<y);
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 522))
//   }


//   test("simple logic operator <= with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=10;
//         |y=10;
//         |x=y<=z;
//         |putBool(x);
//         |putBool(10<=1);
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 523))
//   }

//   test("simple logic operator >= with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=10;
//         |y=10;
//         |x=y>=z;
//         |putBool(x);
//         |}
//       """.stripMargin
//     val expected = "true"
//     assert(checkCode(input, expected, 524))
//   }

//   test("simple logic operator == with variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |int z;
//         |int y;
//         |boolean x;
//         |z=100;
//         |y=10;
//         |x=y==z;
//         |putBoolLn(x);
//         |z=10;
//         |x=y==z;
//         |putBoolLn(x);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 525))
//   }

//   test("declare local array") {
//     val input =
//       """
//         |void main(){
//         |int x[4];
//         |}
//       """.stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 526))
//   }
//   test("declare and use local array") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[4];
//         |x[0]=5;
//         |putInt(x[0]);
//         |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 527))
//   }

//   test("use local array in binary add op") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[4];
//         |x[0]=5;
//         |x[1]=10;
//         |putInt(x[0]+5);
//         |putInt(x[0]-5);
//         |putInt(x[0]+x[1]);
//         |}
//       """.stripMargin
//     val expected = "10015"
//     assert(checkCode(input, expected, 528))
//   }

//   test("use local array in binary mul op") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x[4];
//         |x[0]=5;
//         |x[1]=10;
//         |putInt(x[0]*5);
//         |putInt(x[0]/5);
//         |putInt(x[0]*x[1]);
//         |}
//       """.stripMargin
//     val expected = "25150"
//     assert(checkCode(input, expected, 529))
//   }
//   test("use binary % op with int literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |putInt(10%5);
//         |}
//       """.stripMargin
//     val expected = "0"
//     assert(checkCode(input, expected, 530))
//   }

//   test("declare and use variable in child scope") {
//     val input =
//       """
//         |void main()
//         |{
//         |{
//         | int x;
//         | x=10;
//         | putInt(x);
//         |}
//         |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 531))
//   }
//   test("declare and use variable in child scope and parent scope") {
//     val input =
//       """
//         |void main()
//         |{
//         |int y;
//         |y=10;
//         |{
//         | int x;
//         | x=10+y;
//         | putInt(x);
//         |}
//         |y=100;
//         |putInt(y);
//         |}
//       """.stripMargin
//     val expected = "20100"
//     assert(checkCode(input, expected, 532))
//   }
//   test("print elem in a global array") {
//     val input =
//       """
//         |int arr[4];
//         |void main()
//         |{
//         | arr[0]=10;
//         | putInt(arr[0]);
//         |}
//       """.stripMargin
//     val expected = "10"
//     assert(checkCode(input, expected, 533))
//   }

//   test("use global array in binary op") {
//     val input =
//       """
//         |int arr[4];
//         |void main()
//         |{
//         | arr[0]=10;
//         | arr[1]=4;
//         | putInt(arr[0]+arr[1]);
//         | putInt(arr[0]*arr[1]);
//         | putInt(arr[0]/arr[1]);
//         |}
//       """.stripMargin
//     val expected = "14402"
//     assert(checkCode(input, expected, 534))
//   }

//   test("use simple user-defined function") {
//     val input =
//       """
//         |int sum(int a,int b){
//         | return a+b;
//         |}
//         |void main()
//         |{
//         | putInt(sum(3,4));
//         |}
//       """.stripMargin
//     val expected = "7"
//     assert(checkCode(input, expected, 535))
//   }

//   test("use local array with user-defined function") {
//     val input =
//       """
//         |int sum(int a,int b){
//         | return a+b;
//         |}
//         |int arr[3];
//         |void main()
//         |{
//         |arr[0]=10;
//         |arr[1]=2;
//         |putInt(sum(arr[0],arr[1]));
//         |}
//       """.stripMargin
//     val expected = "12"
//     assert(checkCode(input, expected, 536))
//   }

//   test("simple if condition") {
//     val input =
//       """
//         |void main()
//         |{
//         | if(1>2)
//         | {
//         | putBool(true);
//         | }
//         | else
//         | {
//         | putBool(false);
//         | }
//         |}
//       """.stripMargin
//     val expected = "false"
//     assert(checkCode(input, expected, 537))
//   }
//   test("another simple if statement") {
//     val input =
//       """
//         |void main()
//         |{
//         | if(1<2)
//         | {
//         | putBool(true);
//         | }
//         | else
//         | {
//         | putBool(false);
//         | }
//         |}
//       """.stripMargin
//     val expected = "true"
//     assert(checkCode(input, expected, 538))
//   }
//   test("print a string") {
//     val input =
//       """
//         |void main()
//         |{
//         |putString("hello");
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 539))
//   }
//   test("if statement with complex condition") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x,y;
//         |x=2000;
//         |if(x%8==0 && x%20==0)
//         |{
//         | putStringLn("x chia het cho 20 va 8");
//         |}
//         |}
//       """.stripMargin
//     val expected = "x chia het cho 20 va 8"
//     assert(checkCode(input, expected, 540))
//   }
//   test("assign string into a local variable") {
//     val input =
//       """
//         |void main()
//         |{
//         |string str;
//         |str="hello";
//         |putString(str);
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 541))
//   }

//   test("assign string into a global variable") {
//     val input =
//       """
//         |string str;
//         |void main()
//         |{
//         |str="hello";
//         |putString(str);
//         |}
//       """.stripMargin
//     val expected = "hello"
//     assert(checkCode(input, expected, 542))
//   }

//   test("recursive function with if stmt") {
//     val input =
//       """
//         |int factor(int n)
//         |{
//         | if(n==0)
//         |  return 1;
//         |  else
//         |   return n*factor(n-1);
//         |}
//         |void main()
//         |{
//         |putInt(factor(5));
//         |}
//       """.stripMargin
//     val expected = "120"
//     assert(checkCode(input, expected, 543))
//   }

//   test("simple for") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "01234"
//     assert(checkCode(input, expected, 544))
//   }
//   test("simple do-while") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |   putInt(i);
//         |   i=i+1;
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "01234"
//     assert(checkCode(input, expected, 545))
//   }
//   test("simple for and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i%2==0)
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 546))
//   }
//   test("simple for with continue and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i%2==1)
//         |   continue;
//         |   putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 547))
//   }
//   test("simple for with break and if stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | for(i=0;i<5;i=i+1)
//         | {
//         | if(i>3)
//         |   break;
//         | putIntLn(i);
//         | }
//         |}
//       """.stripMargin
//     val expected = "0123"
//     assert(checkCode(input, expected, 548))
//   }
//   test("simple dowhile and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |
//         |  if(i%2==0)
//         |   putInt(i);
//         |   i=i+1;
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "024"
//     assert(checkCode(input, expected, 549))
//   }

//   test("simple dowhile with continue and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |  i=i+1;
//         |  if(i%2==1)
//         |   continue;
//         |   putInt(i);
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "24"
//     assert(checkCode(input, expected, 550))
//   }

//   test("simple dowhile with break and if") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         |  do
//         |  {
//         |  i=i+1;
//         |  if(i==3)
//         |   break;
//         |   putInt(i);
//         |  }
//         |  while (i<5);
//         |
//         |}
//       """.stripMargin
//     val expected = "12"
//     assert(checkCode(input, expected, 551))
//   }

//   test("print negative int") {
//     val input =
//       """
//         |void main()
//         |{
//         | putInt(-5);
//         |}
//       """.stripMargin
//     val expected = "-5"
//     assert(checkCode(input, expected, 552))
//   }

//   test("print negative float ") {
//     val input =
//       """
//         |void main()
//         |{
//         | putFloat(-5.0);
//         |}
//       """.stripMargin
//     val expected = "-5.0"
//     assert(checkCode(input, expected, 553))
//   }

//   test("use not operator") {
//     val input =
//       """
//         |void main()
//         |{
//         | putBool(!true);
//         | putBool(!false);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 554))
//   }

//   test("use negative op in binary operator") {
//     val input =
//       """
//         |void main()
//         |{
//         | int x;
//         | x=10--10+10;
//         | putInt(x);
//         |}
//       """.stripMargin
//     val expected = "30"
//     assert(checkCode(input, expected, 555))
//   }

//   test("use negative in array element") {
//     val input =
//       """
//         |void main()
//         |{
//         | int x[5];
//         | x[0]=100;
//         | putInt(-x[0]);
//         |}
//       """.stripMargin
//     val expected = "-100"
//     assert(checkCode(input, expected, 556))
//   }
//   test("assign negative literal into variable") {
//     val input =
//       """
//         |float y;
//         |void main()
//         |{
//         |int x;
//         |x=-100;
//         |y=-100.0;
//         |putIntLn(x);
//         |putFloatLn(y);
//         |}
//       """.stripMargin
//     val expected = "-100-100.0"
//     assert(checkCode(input, expected, 557))
//   }

//   test("use mul op with float literal") {
//     val input =
//       """
//         |void main()
//         |{
//         |float x,y;
//         |x=10.0;
//         |y=2;
//         |putFloatLn(x+y);
//         |putFloatLn(x/y);
//         |putFloatLn(x*y+x/y);
//         |}
//       """.stripMargin
//     val expected = "12.05.025.0"
//     assert(checkCode(input, expected, 558))
//   }

//   test("compile add op,mul op, int,float") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x;
//         |float y;
//         |x=2;
//         |y=1.0;
//         |putFloatLn((x+y)/x);
//         |putFloatLn((x*y)/(x-y));
//         |putFloatLn((x+y)/x-(x*y)/(x-y));
//         |}
//       """.stripMargin
//     val expected = "1.52.0-0.5"
//     assert(checkCode(input, expected, 559))
//   }

//   test("use many logic operator") {
//     val input =
//       """
//         |void main()
//         |{
//         |boolean x,y;
//         |x=true;
//         |y=false;
//         |putBoolLn(false || false || false || x);
//         |putBoolLn(true && true && y);
//         |putBoolLn(1!=2);
//         |}
//       """.stripMargin
//     val expected = "truefalsetrue"
//     assert(checkCode(input, expected, 560))
//   }
//   test("use local array in for loop") {
//     val input =
//       """
//         |void main()
//         |{
//         |int arr[5];
//         |int i;
//         |for(i=0;i<5;i=i+1)
//         | arr[i]=i+1;
//         |for(i=0;i<5;i=i+1)
//         | putIntLn(arr[i]);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 561))
//   }

//   test("use global array in for loop") {
//     val input =
//       """
//         |int arr[5];
//         |void main()
//         |{
//         |int i;
//         |for(i=0;i<5;i=i+1)
//         | arr[i]=i+1;
//         |for(i=0;i<5;i=i+1)
//         | putIntLn(arr[i]);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 562))
//   }
//   test("use local array int dowhile") {
//     val input =
//       """
//         |void main()
//         |{
//         | int arr[5];
//         | int i;
//         | i=0;
//         | do
//         | {
//         |   arr[i]=i+1;
//         |   i=i+1;
//         | } while (i<5);
//         | i=0;
//         | do
//         | {
//         |   putInt(arr[i]);
//         |   i=i+1;
//         | } while(i<5);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 563))
//   }
//   test("pass array to function") {
//     val input =
//       """
//         |void print(int arr[],int n)
//         |{
//         |int i;
//         |for(i=0;i<n;i=i+1)
//         |{
//         |putInt(arr[i]);
//         |}
//         |}
//         |void main()
//         |{
//         |int arr[5];
//         |int i;
//         |for(i=0;i<5;i=i+1)
//         | arr[i]=i+1;
//         |print(arr,5);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 564))
//   }

//   test("use for nested for") {
//     val input =
//       """
//         |void main()
//         |{
//         | int i,j;
//         | int arr[5];
//         | for(i=1;i<=5;i=i+1)
//         | {
//         |   int s;
//         |   s=1;
//         |   for(j=1;j<=i;j=j+1)
//         |   {
//         |     s=s*j;
//         |   }
//         |   arr[i-1]=s;
//         | }
//         | for(i=0;i<5;i=i+1)
//         | {
//         |   putIntLn(arr[i]);
//         | }
//         |}
//       """.stripMargin
//     val expected = "12624120"
//     assert(checkCode(input, expected, 565))
//   }

//   test("function return a string") {
//     val input =
//       """
//         |string getText(int n){
//         | if(n%2==0)
//         |   return "so chan";
//         | else
//         |   return "so le";
//         |}
//         |void main()
//         |{
//         |putStringLn(getText(2));
//         |putStringLn(getText(1));
//         |}
//       """.stripMargin
//     val expected = "so chanso le"
//     assert(checkCode(input, expected, 566))
//   }

//   test("function return a float") {
//     val input =
//       """
//         |float increase(float val)
//         |{
//         | return val+1.0;
//         |}
//         |void main()
//         |{
//         | putFloatLn(increase(5.0));
//         |}
//       """.stripMargin
//     val expected = "6.0"
//     assert(checkCode(input, expected, 567))
//   }

//   test("function return a boolean") {
//     val input =
//       """
//         |boolean isOdd(int val)
//         |{
//         | return val % 2 ==1;
//         |}
//         |void main()
//         |{
//         |putBoolLn(isOdd(3));
//         |putBoolLn(isOdd(2));
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 568))
//   }

//   test("function return an global array") {
//     val input =
//       """
//         |int arr[5];
//         |int[] init()
//         |{
//         | int i;
//         | for (i=0;i<5;i=i+1)
//         |   arr[i]=i+1;
//         | return arr;
//         |}
//         |void main()
//         |{
//         |int x[5],i;
//         |init();
//         |x=arr;
//         |for(i=0;i<5;i=i+1)
//         |   putIntLn(x[i]);
//         |}
//       """.stripMargin
//     val expected = "12345"
//     assert(checkCode(input, expected, 569))
//   }

//   test("n-th fibonacci number function") {
//     val input =
//       """
//         |int fibonacci(int n)
//         |{
//         | if(n==1|| n==2)
//         |   return 1;
//         | else return fibonacci(n-1)+fibonacci(n-2);
//         |}
//         |void main()
//         |{
//         | putInt(fibonacci(5));
//         |}
//       """.stripMargin
//     val expected = "5"
//     assert(checkCode(input, expected, 570))
//   }

//   test("call some diff user-defined function") {
//     val input =
//       """
//         |boolean isPrime(int n)
//         |{
//         |int i;
//         |for (i=2;i<n;i=i+1)
//         |{
//         | if(n%i==0)
//         |   return false;
//         |}
//         |return true;
//         |}
//         |void printAllPrime(int arr[],int n)
//         |{
//         | int i;
//         | for(i=0;i<n;i=i+1)
//         | {
//         |   if(isPrime(arr[i]))
//         |     putIntLn(arr[i]);
//         | }
//         |}
//         |void main()
//         |{
//         | int arr[10];
//         | int i;
//         | for(i=0;i<10;i=i+1)
//         |   arr[i]=i+2;
//         | printAllPrime(arr,10);
//         |}
//       """.stripMargin
//     val expected = "235711"
//     assert(checkCode(input, expected, 571))
//   }

//   test("implicit ordered add op") {
//     val input =
//       """
//         |void main()
//         |{
//         |putIntLn(2-2-4);
//         |}
//       """.stripMargin
//     val expected = "-4"
//     assert(checkCode(input, expected, 572))
//   }

//   test("operator ordered with () in add op") {
//     val input =
//       """
//         |void main()
//         |{
//         |putIntLn(2-(2-4));
//         |}
//       """.stripMargin
//     val expected = "4"
//     assert(checkCode(input, expected, 573))
//   }
//   test("implicit ordered mul operator") {
//     val input =
//       """
//         |void main()
//         |{
//         | putFloat(2.0/2.0/4.0);
//         |}
//       """.stripMargin
//     val expected = "0.25"
//     assert(checkCode(input, expected, 574))
//   }

//   test("ordered mul operator with ()") {
//     val input =
//       """
//         |void main()
//         |{
//         | putFloat(2.0/(2.0/4.0));
//         |}
//       """.stripMargin
//     val expected = "4.0"
//     assert(checkCode(input, expected, 575))
//   }

//   test("assign local string var to local var") {
//     val input =
//       """
//         |void main()
//         |{
//         | string s1,s2;
//         | s1="Hello";
//         | s2=s1;
//         | putString(s2);
//         |}
//       """.stripMargin
//     val expected = "Hello"
//     assert(checkCode(input, expected, 576))
//   }

//   test("assign global string var to local var") {
//     val input =
//       """
//         |string s1;
//         |void main()
//         |{
//         | string s2;
//         | s1="Hello";
//         | s2=s1;
//         | putString(s2);
//         |}
//       """.stripMargin
//     val expected = "Hello"
//     assert(checkCode(input, expected, 577))
//   }

//   test("assign local string var to global var") {
//     val input =
//       """
//         |string s2;
//         |void main()
//         |{
//         | string s1;
//         | s1="Hello";
//         | s2=s1;
//         | putString(s2);
//         |}
//       """.stripMargin
//     val expected = "Hello"
//     assert(checkCode(input, expected, 578))
//   }
//   test("two assign operator in one stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x,y;
//         |x=y=1;
//         |putInt(x);
//         |putInt(y);
//         |}
//       """.stripMargin
//     val expected = "11"
//     assert(checkCode(input, expected, 579))
//   }
//   test("assign int var to var") {
//     val input =
//       """
//         |int a,b;
//         |void main()
//         |{
//         | int x,y,z;
//         | x=a=1;
//         | y=x;
//         | z=a;
//         | b=x;
//         | putInt(y);
//         | putInt(z);
//         | putInt(b);
//         |}
//       """.stripMargin
//     val expected = "111"
//     assert(checkCode(input, expected, 580))
//   }
//   test("many assign operator in one stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         |int x,y,z,t;
//         |x=y=z=t=1;
//         |putInt(x);
//         |putInt(y);
//         |putInt(t);
//         |putInt(z);
//         |}
//       """.stripMargin
//     val expected = "1111"
//     assert(checkCode(input, expected, 581))
//   }
//   test("two assign operator with float in one stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         |float x,y;
//         |x=y=1.0;
//         |putFloat(x);
//         |putFloat(y);
//         |}
//       """.stripMargin
//     val expected = "1.01.0"
//     assert(checkCode(input, expected, 582))
//   }
//   test("two assign operator with string in one stmt") {
//     val input =
//       """
//         |void main()
//         |{
//         |string x,y;
//         |x=y="hello";
//         |putString(x);
//         |putString(y);
//         |}
//       """.stripMargin
//     val expected = "hellohello"
//     assert(checkCode(input, expected, 583))
//   }
//   test("test hidden variable of parent scope") {
//     val input =
//       """
//         |void main()
//         |{
//         | int x;
//         | x=10;
//         | {
//         |   int x;
//         |   x=100;
//         |   putInt(x);
//         | }
//         | putInt(x);
//         |}
//       """.stripMargin
//     val expected = "10010"
//     assert(checkCode(input, expected, 584))
//   }
//   test("sample program in mc spec") {
//     val input =
//       """
//         | int i ;
//         | int f ( ) {
//         | return 200;
//         | }
//         | void main ( ) {
//         | int main ;
//         | main = f ( ) ;
//         | putIntLn ( main ) ;
//         | {
//         | int i ;
//         | int main ;
//         | int f ;
//         | main = f = i = 100;
//         | putIntLn ( i ) ;
//         | putIntLn ( main ) ;
//         | putIntLn ( f ) ;
//         | }
//         | putIntLn ( main ) ;
//         | }
//       """.stripMargin
//     val expected = "200100100100200"
//     assert(checkCode(input, expected, 585))
//   }
//   test("test boolean array") {
//     val input =
//       """
//         |void main()
//         |{
//         |boolean arr[2];
//         |arr[1]=true;
//         |arr[0]=false;
//         |putBool(arr[0]);
//         |putBool(arr[1]);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 586))
//   }
//   test("test float array") {
//     val input =
//       """
//         |void main()
//         |{
//         |float arr[2];
//         |arr[1]=1.0;
//         |arr[0]=2.0;
//         |putFloat(arr[0]);
//         |putFloat(arr[1]);
//         |}
//       """.stripMargin
//     val expected = "2.01.0"
//     assert(checkCode(input, expected, 587))
//   }

//   test("test string array") {
//     val input =
//       """
//         |void main()
//         |{
//         |string arr[2];
//         |arr[1]="1";
//         |arr[0]="0";
//         |putString(arr[0]);
//         |putString(arr[1]);
//         |}
//       """.stripMargin
//     val expected = "01"
//     assert(checkCode(input, expected, 588))
//   }
//   test("global string array") {
//     val input =
//       """|string arr[2];void main()
//          |{
//          |arr[1]="1";
//          |arr[0]="0";
//          |putString(arr[0]);
//          |putString(arr[1]);
//          |}
//       """.stripMargin
//     val expected = "01"
//     assert(checkCode(input, expected, 589))
//   }

//   test("test global boolean array") {
//     val input =
//       """
//         |boolean arr[2];
//         |void main()
//         |{
//         |arr[1]=true;
//         |arr[0]=false;
//         |putBool(arr[0]);
//         |putBool(arr[1]);
//         |}
//       """.stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 590))
//   }
//   test("test global float array") {
//     val input =
//       """
//         |float arr[2];
//         |void main()
//         |{
//         |arr[1]=1.0;
//         |arr[0]=2.0;
//         |putFloat(arr[0]);
//         |putFloat(arr[1]);
//         |}
//       """.stripMargin
//     val expected = "2.01.0"
//     assert(checkCode(input, expected, 591))
//   }

//   test("use negative op with float") {
//     val input =
//       """
//         |void main()
//         |{
//         | float x;
//         | x=-5.0;
//         | putFloat(x);
//         | putFloat(-x);
//         |}
//       """.stripMargin
//     val expected = "-5.05.0"
//     assert(checkCode(input, expected, 592))
//   }
//   test("use == and != with boolean") {
//     val input =
//       """
//         |void main()
//         |{
//         | boolean x,y;
//         | x=y=true;
//         | putBool(x==y);
//         | putBool(x!=y);
//         |}
//       """.stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 593))
//   }
//   test("large static array"){
//     val input=
//       """
//         |int arr[100];
//         |void main()
//         |{
//         |
//         |}
//       """.stripMargin
//     val expected=""
//     assert(checkCode(input,expected,594))
//   }
//   test("do-while with many stmt"){
//     val input=
//       """
//         |void main()
//         |{
//         | int i;
//         | i=0;
//         | do
//         | putInt(i);
//         | i=i+1;
//         | while (i<5);
//         |}
//       """.stripMargin
//     val expected="01234"
//     assert(checkCode(input,expected,595))
//   }
//   test("initialized implicitly global varialbe"){
//     val input=
//       """
//         |int x;
//         |float y;
//         |boolean z;
//         |void main()
//         |{
//         | putInt(x);
//         | putFloat(y);
//         | putBool(z);
//         |}
//       """.stripMargin
//     val expected="00.0false"
//     assert(checkCode(input,expected,596))
//   }
//   test("function return float") {
//   val input=
//     """
//       |float foo()
//       |{
//       | return 5;
//       |}
//       |void main()
//       |{
//       | putFloat(foo());
//       |}
//     """.stripMargin
//     val expected="5.0"
//     assert(checkCode(input,expected,597))
//   }

//   test("use string array"){
//     val input=
//       """
//         |void main()
//         |{
//         |  string str,str2,arr[2];
//         |  arr[0]=str="hello";
//         |  str2=arr[1]="world";
//         |  putString(str);
//         |  putString(str2);
//         |}
//       """.stripMargin
//     val expected="helloworld"
//     assert(checkCode(input,expected,598))
//   }
//   test("binary op in float"){
//     val input=
//       """
//         |float x[2];
//         |void main()
//         |{
//         |x[0]=10;
//         |putFloat(x[0]);
//         |}
//       """.stripMargin
//     val expected="10.0"
//     assert(checkCode(input,expected,599))
//   }




// test("null program") {
//     val input = "void main () {}"
//     val expected = ""
//     assert(checkCode(input, expected, 501))
//   }
//   test("program with block statements only") {
//     val input = "void main () {{}{{}}}"
//     val expected = ""
//     assert(checkCode(input, expected, 502))
//   }
//   test("three void null programs") {
//     val input =
//       """void foo(){}
//         |void main(){}
//         |void bar(){}""".stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 503))
//   }
//   test("void function with return") {
//     val input =
//       """void main(){
//         |return;
//         |}""".stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 504))
//   }
//   test("void function print integer") {
//     val input =
//       """void main(){
//         |putInt(123);
//         |return;
//         |}""".stripMargin
//     val expected = "123"
//     assert(checkCode(input, expected, 505))
//   }
//   test("void function print float") {
//     val input =
//       """void main(){
//         |putFloat(123.456);
//         |return;
//         |}""".stripMargin
//     val expected = "123.456"
//     assert(checkCode(input, expected, 506))
//   }
//   test("void function print boolean") {
//     val input =
//       """void main(){
//         |putBool(true);
//         |putBool(false);
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 507))
//   }
//   test("Hello World!") {
//     val input =
//       """void main(){
//         |putString("Hello World!");
//         |return;
//         |}""".stripMargin
//     val expected = "Hello World!"
//     assert(checkCode(input, expected, 508))
//   }
//   test("test default value for local variables without array") {
//     val input =
//       """void main(){
//         |int a;
//         |float b;
//         |putInt(a);
//         |{
//         |boolean b;
//         |string d;
//         |putBool(b);
//         |putString(d);
//         |}
//         |putFloat(b);
//         |}""".stripMargin
//     val expected =
//       """0falsenull0.0""".stripMargin
//     assert(checkCode(input, expected, 509))
//   }
//   test("test default value for local variables with array") {
//     val input =
//       """void main(){
//         |int a[1];
//         |float b[2];
//         |putInt(a[0]);
//         |{
//         |boolean b[1];
//         |string d[5];
//         |putBool(b[0]);
//         |putString(d[3]);
//         |}
//         |putFloat(b[1]);
//         |return;
//         |}""".stripMargin
//     val expected =
//       """0falsenull0.0""".stripMargin
//     assert(checkCode(input, expected, 510))
//   }
//   test("test default value for global variables without array") {
//     val input =
//       """int a;
//         |float b;
//         |void main(){
//         |putInt(a);
//         |putFloat(b);
//         |putBool(c);
//         |putString(d);
//         |return;
//         |}
//         |boolean c;
//         |string d;""".stripMargin
//     val expected =
//       """00.0falsenull""".stripMargin
//     assert(checkCode(input, expected, 511))
//   }
//   test("test default value for global variables with array") {
//     val input =
//       """int a,x[1];
//         |float b,y[1];
//         |void main(){
//         |putInt(a);
//         |{
//         |putInt(x[0]);
//         |}
//         |{{
//         |putFloat(y[0]);
//         |}
//         |putFloat(b);
//         |}
//         |{}
//         |putBool(c);
//         |{putBool(z[0]);}
//         |{
//         |putString(d);
//         |{
//         |putString(t[0]);
//         |}}
//         |return;
//         |}
//         |boolean c,z[1];
//         |string d,t[1];""".stripMargin
//     val expected =
//       """000.00.0falsefalsenullnull""".stripMargin
//     assert(checkCode(input, expected, 512))
//   }
//   test("Print a integer value typed") {
//     val input =
//       """void main(){
//         |putInt(777); // enter 777
//         |return;
//         |}""".stripMargin
//     val expected = "777"
//     assert(checkCode(input, expected, 513))
//   }
//   test("Print a float value typed") {
//     val input =
//       """void main(){
//         |putFloat(1.25); // enter 1.25
//         |return;
//         |}""".stripMargin
//     val expected = "1.25"
//     assert(checkCode(input, expected, 514))
//   }
//   test("Simple global assignments") {
//     val input =
//       """int a,x[1];
//         |float b,y[1];
//         |void main(){
//         |a = 5;
//         |b = 2.5;
//         |c = true;
//         |d = "str";
//         |x[0] = 8;
//         |y[0] = 7.25;
//         |z[0] = true;
//         |t[0] = "ing";
//         |putInt(a);
//         |putInt(x[0]);
//         |putFloat(b);
//         |putFloat(y[0]);
//         |putBool(c);
//         |putBool(z[0]);
//         |putString(d);
//         |putString(t[0]);
//         |return;
//         |}
//         |boolean c,z[1];
//         |string d,t[1];""".stripMargin
//     val expected = "582.57.25truetruestring"
//     assert(checkCode(input, expected, 515))
//   }
//   test("Simple local assignments") {
//     val input =
//       """void main(){
//         |int a,x[1];
//         |float b,y[1];
//         |boolean c,z[1];
//         |string d,t[1];
//         |a = 5;
//         |b = 2.5;
//         |c = true;
//         |d = "str";
//         |x[0] = 8;
//         |y[0] = 7.25;
//         |z[0] = true;
//         |t[0] = "ing";
//         |putInt(a);
//         |putInt(x[0]);
//         |putFloat(b);
//         |putFloat(y[0]);
//         |putBool(c);
//         |putBool(z[0]);
//         |putString(d);
//         |putString(t[0]);
//         |return;
//         |}""".stripMargin
//     val expected = "582.57.25truetruestring"
//     assert(checkCode(input, expected, 516))
//   }
//   test("Multi simple global assignments without type coercions") {
//     val input =
//       """int a,x[5];
//         |float b,y[5];
//         |boolean c,z[5];
//         |string d,t[5];
//         |void main(){
//         |x[3] = a = x[0] = x[1] = 15;
//         |b = y[1] = y[0] = 2.6;
//         |z[3] = c = z[0] = z[1] = true;
//         |d = t[4] = t[3] = t[2] = t[1] = t[0] = "love never die";
//         |putInt(x[1]);
//         |putInt(x[3]);
//         |putFloat(y[0]);
//         |putFloat(b);
//         |putBool(z[0]);
//         |putBool(z[3]);
//         |putString(d);
//         |}""".stripMargin
//     val expected = "15152.62.6truetruelove never die"
//     assert(checkCode(input, expected, 517))
//   }
//   test("Multi simple local assignments without type coercions") {
//     val input =
//       """void main(){
//         |int a,x[5];
//         |float b,y[5];
//         |boolean c,z[5];
//         |string d,t[5];
//         |x[3] = a = x[0] = x[1] = 15;
//         |b = y[1] = y[0] = 2.6;
//         |z[3] = c = z[0] = z[1] = true;
//         |d = t[4] = t[3] = t[2] = t[1] = t[0] = " legend";
//         |putInt(x[1]);
//         |putInt(x[3]);
//         |putFloat(y[0]);
//         |putFloat(b);
//         |putBool(z[0]);
//         |putBool(z[3]);
//         |putString(d);
//         |}""".stripMargin
//     val expected = "15152.62.6truetrue legend"
//     assert(checkCode(input, expected, 518))
//   }
//   test("Multi complex assignments with type coercions") {
//     val input =
//       """int a,b[5];
//         |float c,d[5];
//         |void main(){
//         |int x,y[5];
//         |float z,t[5];
//         |a = 2;
//         |c = 12;
//         |x = 99;
//         |y[0] = 13;
//         |b[0] = 3;
//         |b[1] = 2;
//         |b[3] = 1;
//         |b[2] = 4;
//         |d[0] = 9;
//         |d[1] = x;
//         |d[2] = y[0];
//         |t[a] = z = y[b[4]] = a = 4; // t[2] = 4.0
//         |putFloat(c);
//         |putFloat(d[0]);
//         |putFloat(d[1]);
//         |putFloat(d[2]);
//         |putFloat(t[2]);
//         |putFloat(t[a]);
//         |}""".stripMargin
//     val expected = "12.09.099.013.04.00.0"
//     assert(checkCode(input, expected, 519))
//   }
//   test("simple addition integer") {
//     val input =
//       """void main(){
//         |putInt(1+1);
//         |return;
//         |}""".stripMargin
//     val expected = "2"
//     assert(checkCode(input, expected, 520))
//   }
//   test("simple subtraction integer") {
//     val input =
//       """void main(){
//         |putInt(3-1);
//         |return;
//         |}""".stripMargin
//     val expected = "2"
//     assert(checkCode(input, expected, 521))
//   }
//   test("simple multiplication integer") {
//     val input =
//       """void main(){
//         |putInt(3*2);
//         |return;
//         |}""".stripMargin
//     val expected = "6"
//     assert(checkCode(input, expected, 522))
//   }
//   test("simple division integer") {
//     val input =
//       """void main(){
//         |putInt(8/3);
//         |return;
//         |}""".stripMargin
//     val expected = "2"
//     assert(checkCode(input, expected, 523))
//   }
//   test("simple remain integer") {
//     val input =
//       """void main(){
//         |putInt(9%7);
//         |return;
//         |}""".stripMargin
//     val expected = "2"
//     assert(checkCode(input, expected, 524))
//   }
//   test("simple addition float") {
//     val input =
//       """void main(){
//         |putFloat(1.5+3.2);
//         |return;
//         |}""".stripMargin
//     val expected = "4.7"
//     assert(checkCode(input, expected, 525))
//   }
//   test("simple subtraction float") {
//     val input =
//       """void main(){
//         |putFloat(1.5-3.2);
//         |return;
//         |}""".stripMargin
//     val expected = "-1.7"
//     assert(checkCode(input, expected, 526))
//   }
//   test("simple multiplication float") {
//     val input =
//       """void main(){
//         |putFloat(0.25*0.5);
//         |return;
//         |}""".stripMargin
//     val expected = "0.125"
//     assert(checkCode(input, expected, 527))
//   }
//   test("simple division float") {
//     val input =
//       """void main(){
//         |putFloat(8.6/2.0);
//         |return;
//         |}""".stripMargin
//     val expected = "4.3"
//     assert(checkCode(input, expected, 528))
//   }
//   test("integer and float addition") {
//     val input =
//       """void main(){
//         |putFloat(1+3.25+2);
//         |return;
//         |}""".stripMargin
//     val expected = "6.25"
//     assert(checkCode(input, expected, 529))
//   }
//   test("integer and float subtraction") {
//     val input =
//       """void main(){
//         |putFloat(10-3.25-1.75);
//         |return;
//         |}""".stripMargin
//     val expected = "5.0"
//     assert(checkCode(input, expected, 530))
//   }
//   test("integer and float multiplication") {
//     val input =
//       """void main(){
//         |putFloat(2*3.5*3);
//         |return;
//         |}""".stripMargin
//     val expected = "21.0"
//     assert(checkCode(input, expected, 531))
//   }
//   test("integer and float division") {
//     val input =
//       """void main(){
//         |putFloat(45/5.0/9);
//         |return;
//         |}""".stripMargin
//     val expected = "1.0"
//     assert(checkCode(input, expected, 532))
//   }
//   test("negative integer") {
//     val input =
//       """void main(){
//         |putInt(-(13+1));
//         |return;
//         |}""".stripMargin
//     val expected = "-14"
//     assert(checkCode(input, expected, 533))
//   }
//   test("negative float") {
//     val input =
//       """void main(){
//         |putFloat(-(62.5-2));
//         |return;
//         |}""".stripMargin
//     val expected = "-60.5"
//     assert(checkCode(input, expected, 534))
//   }
//   test("integer compare integer greater than") {
//     val input =
//       """void main(){
//         |putBool(1>2);
//         |putBool(2>2);
//         |putBool(2>1);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetrue"
//     assert(checkCode(input, expected, 535))
//   }
//   test("integer compare integer greater than or equal") {
//     val input =
//       """void main(){
//         |putBool(1>=2);
//         |putBool(1>=1);
//         |putBool(1>=-1);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetrue"
//     assert(checkCode(input, expected, 536))
//   }
//   test("integer compare integer less than or equal") {
//     val input =
//       """void main(){
//         |putBool(6<=5);
//         |putBool(6<=6);
//         |putBool(6<=7);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetrue"
//     assert(checkCode(input, expected, 537))
//   }
//   test("integer compare integer less than") {
//     val input =
//       """void main(){
//         |putBool(9<2);
//         |putBool(9<9);
//         |putBool(9<14);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetrue"
//     assert(checkCode(input, expected, 538))
//   }
//   test("float compare float greater than") {
//     val input =
//       """void main(){
//         |putBool(1.0>2.0);
//         |putBool(2.4>2.4);
//         |putBool(2.2>1.6);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetrue"
//     assert(checkCode(input, expected, 539))
//   }
//   test("float compare float greater than or equal") {
//     val input =
//       """void main(){
//         |putBool(1.5>=2.9);
//         |putBool(1.5>=1.5);
//         |putBool(3.14>=-3.3);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetrue"
//     assert(checkCode(input, expected, 540))
//   }
//   test("float compare float less than or equal") {
//     val input =
//       """void main(){
//         |putBool(3.14159<=3.14);
//         |putBool(6.66<=6.66);
//         |putBool(12.3<=13.2);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetrue"
//     assert(checkCode(input, expected, 541))
//   }
//   test("float compare float less than") {
//     val input =
//       """void main(){
//         |putBool(9.9<2.5);
//         |putBool(9.99<9.99);
//         |putBool(9.0<14.8);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetrue"
//     assert(checkCode(input, expected, 542))
//   }
//   test("integer compare float greater than") {
//     val input =
//       """void main(){
//         |putBool(1.0>2);
//         |putBool(2.0>2);
//         |putBool(2.0>1);
//         |putBool(1>2.0);
//         |putBool(2>2.0);
//         |putBool(2>1.0);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetruefalsefalsetrue"
//     assert(checkCode(input, expected, 543))
//   }
//   test("integer compare float greater than or equal") {
//     val input =
//       """void main(){
//         |putBool(1.0>=2);
//         |putBool(1.0>=1);
//         |putBool(1.0>=-1);
//         |putBool(1>=2.0);
//         |putBool(1>=1.0);
//         |putBool(1>=-1.0);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetruefalsetruetrue"
//     assert(checkCode(input, expected, 544))
//   }
//   test("integer compare float less than or equal") {
//     val input =
//       """void main(){
//         |putBool(6.0<=5);
//         |putBool(6.0<=6);
//         |putBool(6.0<=7);
//         |putBool(6<=5.0);
//         |putBool(6<=6.0);
//         |putBool(6<=7.0);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetruetruefalsetruetrue"
//     assert(checkCode(input, expected, 545))
//   }
//   test("integer compare float less than") {
//     val input =
//       """void main(){
//         |putBool(9.0<2);
//         |putBool(9.0<9);
//         |putBool(9.0<14);
//         |putBool(9<2.0);
//         |putBool(9<9.0);
//         |putBool(9<14.0);
//         |return;
//         |}""".stripMargin
//     val expected = "falsefalsetruefalsefalsetrue"
//     assert(checkCode(input, expected, 546))
//   }
//   test("integer compare integer equal") {
//     val input =
//       """void main(){
//         |putBool(3==2);
//         |putBool(0==0);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 547))
//   }
//   test("integer compare integer not equal") {
//     val input =
//       """void main(){
//         |putBool(3!=2);
//         |putBool(0!=0);
//         |return;
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 548))
//   }
//   test("boolean compare boolean equal") {
//     val input =
//       """void main(){
//         |putBool(true==true);
//         |putBool(false==true);
//         |return;
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 549))
//   }
//   test("integer compare boolean not equal") {
//     val input =
//       """void main(){
//         |putBool(false!=true);
//         |putBool(false!=false);
//         |return;
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 550))
//   }
//   test("not boolean") {
//     val input =
//       """void main(){
//         |putBool(!true);
//         |putBool(!false);
//         |return;
//         |}""".stripMargin
//     val expected = "falsetrue"
//     assert(checkCode(input, expected, 551))
//   }
//   test("AND instruction") {
//     val input =
//       """void main(){
//         |putBool(true&&true);
//         |putBool(true&&false);
//         |putBool(false&&true);
//         |putBool(false&&false);
//         |return;
//         |}""".stripMargin
//     val expected = "truefalsefalsefalse"
//     assert(checkCode(input, expected, 552))
//   }
//   test("OR instruction") {
//     val input =
//       """void main(){
//         |putBool(true||true);
//         |putBool(true||false);
//         |putBool(false||true);
//         |putBool(false||false);
//         |return;
//         |}""".stripMargin
//     val expected = "truetruetruefalse"
//     assert(checkCode(input, expected, 553))
//   }
//   test("simple if statement true") {
//     val input =
//       """void main(){
//         |if (true) putString("this if statement is true");
//         |return;
//         |}""".stripMargin
//     val expected = "this if statement is true"
//     assert(checkCode(input, expected, 554))
//   }
//   test("simple if statement false") {
//     val input =
//       """void main(){
//         |if (false) putString("this if statement is true");
//         |return;
//         |}""".stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 555))
//   }
//   test("complete if statement true") {
//     val input =
//       """void main(){
//         |if (true) putString("this if statement is true"); else putString("this if statement is false");
//         |return;
//         |}""".stripMargin
//     val expected = "this if statement is true"
//     assert(checkCode(input, expected, 556))
//   }
//   test("complete if statement false") {
//     val input =
//       """void main(){
//         |if (false) putString("this if statement is true"); else putString("this if statement is false");
//         |return;
//         |}""".stripMargin
//     val expected = "this if statement is false"
//     assert(checkCode(input, expected, 557))
//   }
//   test("dangling else") {
//     val input =
//       """void main(){
//         |if (true) if(false) putString("1"); else putString("2");
//         |return;
//         |}""".stripMargin
//     val expected = "2"
//     assert(checkCode(input, expected, 558))
//   }
//   test("nest if else statement") {
//     val input =
//       """void main(){
//         |if (false) if (false) putString("1"); else putString("2"); else if (true) putString("3"); else putString("4");
//         |return;
//         |}""".stripMargin
//     val expected = "3"
//     assert(checkCode(input, expected, 559))
//   }
//   test("parameter integer") {
//     val input =
//       """void func(int a){
//         |putInt(a);
//         |return;
//         |}
//         |void main(){
//         |func(55);
//         |return;
//         |}""".stripMargin
//     val expected = "55"
//     assert(checkCode(input, expected, 560))
//   }
//   test("parameter float without type coercions") {
//     val input =
//       """void func(float a){
//         |putFloat(a);
//         |return;
//         |}
//         |void main(){
//         |func(55.5);
//         |return;
//         |}""".stripMargin
//     val expected = "55.5"
//     assert(checkCode(input, expected, 561))
//   }
//   test("parameter float with type coercions") {
//     val input =
//       """void func(float a){
//         |putFloat(a);
//         |return;
//         |}
//         |void main(){
//         |func(55);
//         |return;
//         |}""".stripMargin
//     val expected = "55.0"
//     assert(checkCode(input, expected, 562))
//   }
//   test("parameter boolean") {
//     val input =
//       """void func(boolean a){
//         |putBool(a);
//         |return;
//         |}
//         |void main(){
//         |func(true);
//         |func(false);
//         |return;
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 563))
//   }
//   test("parameter string") {
//     val input =
//       """void func(string a){
//         |putString(a);
//         |return;
//         |}
//         |void main(){
//         |func("params");
//         |return;
//         |}""".stripMargin
//     val expected = "params"
//     assert(checkCode(input, expected, 564))
//   }
//   test("parameter array T") {
//     val input =
//       """void func1(int a[]){
//         |putInt(a[1]);
//         |return;
//         |}
//         |void func2(float a[]){
//         |putFloat(a[1]);
//         |return;
//         |}
//         |void func3(boolean a[]){
//         |putBool(a[1]);
//         |return;
//         |}
//         |void func4(string a[]){
//         |putString(a[1]);
//         |return;
//         |}
//         |void main(){
//         |int a[2];
//         |float b[2];
//         |boolean c[2];
//         |string d[2];
//         |a[1] = 5;
//         |b[1] = 5;
//         |c[1] = true;
//         |d[1] = "!";
//         |func1(a);
//         |func2(b);
//         |func3(c);
//         |func4(d);
//         |return;
//         |}""".stripMargin
//     val expected = "55.0true!"
//     assert(checkCode(input, expected, 565))
//   }
//   test("return integer") {
//     val input =
//       """int foo(){
//         |return 11;
//         |}
//         |void main(){
//         |putInt(foo());
//         |return;
//         |}""".stripMargin
//     val expected = "11"
//     assert(checkCode(input, expected, 566))
//   }
//   test("return float without type coercion") {
//     val input =
//       """float foo(){
//         |return 11.5;
//         |}
//         |void main(){
//         |putFloat(foo());
//         |return;
//         |}""".stripMargin
//     val expected = "11.5"
//     assert(checkCode(input, expected, 567))
//   }
//   test("return float with type coercion") {
//     val input =
//       """float foo(){
//         |return 11;
//         |}
//         |void main(){
//         |putFloat(foo());
//         |return;
//         |}""".stripMargin
//     val expected = "11.0"
//     assert(checkCode(input, expected, 568))
//   }
//   test("return boolean") {
//     val input =
//       """boolean foo(){
//         |return true;
//         |}
//         |boolean bar(){
//         |return false;
//         |}
//         |void main(){
//         |putBool(foo());
//         |putBool(bar());
//         |return;
//         |}""".stripMargin
//     val expected = "truefalse"
//     assert(checkCode(input, expected, 569))
//   }
//   test("return string") {
//     val input =
//       """string foo(){
//         |return "array";
//         |}
//         |void main(){
//         |putString(foo());
//         |return;
//         |}""".stripMargin
//     val expected = "array"
//     assert(checkCode(input, expected, 570))
//   }
//   test("return array T") {
//     val input =
//       """int[] func1(){
//         |int a[4];
//         |a[2] = 5;
//         |return a;
//         |}
//         |float[] func2(){
//         |float a[4];
//         |a[2] = 5;
//         |return a;
//         |}
//         |boolean[] func3(){
//         |boolean a[4];
//         |a[2] = true;
//         |return a;
//         |}
//         |string[] func4(){
//         |string a[4];
//         |a[2] = "guru";
//         |return a;
//         |}
//         |void main(){
//         |putInt(func1()[2]);
//         |putFloat(func2()[2]);
//         |putBool(func3()[2]);
//         |putString(func4()[2]);
//         |return;
//         |}""".stripMargin
//     val expected = "55.0trueguru"
//     assert(checkCode(input, expected, 571))
//   }
//   test("parameter array pointer T") {
//     val input =
//       """int[] foo1(){
//         |int a[4];
//         |a[2] = 5;
//         |return a;
//         |}
//         |void bar1(int a[]){
//         |putInt(a[2]);
//         |return;
//         |}
//         |float[] foo2(){
//         |float a[4];
//         |a[2] = 5;
//         |return a;
//         |}
//         |void bar2(float a[]){
//         |putFloat(a[2]);
//         |return;
//         |}
//         |boolean[] foo3(){
//         |boolean a[4];
//         |a[2] = true;
//         |return a;
//         |}
//         |void bar3(boolean a[]){
//         |putBool(a[2]);
//         |return;
//         |}
//         |string[] foo4(){
//         |string a[4];
//         |a[2] = "basa";
//         |return a;
//         |}
//         |void bar4(string a[]){
//         |putString(a[2]);
//         |return;
//         |}
//         |void main(){
//         |bar1(foo1());
//         |bar2(foo2());
//         |bar3(foo3());
//         |bar4(foo4());
//         |return;
//         |}""".stripMargin
//     val expected = "55.0truebasa"
//     assert(checkCode(input, expected, 572))
//   }
//   test("return array pointer T") {
//     val input =
//       """int[] foo1(){
//         |int a[4];
//         |a[2] = 7;
//         |return a;
//         |}
//         |float[] foo2(){
//         |float a[4];
//         |a[2] = 7;
//         |return a;
//         |}
//         |boolean[] foo3(){
//         |boolean a[4];
//         |a[2] = true;
//         |return a;
//         |}
//         |string[] foo4(){
//         |string a[4];
//         |a[2] = "chomp";
//         |return a;
//         |}
//         |void main(){
//         |putInt(foo1()[2]);
//         |putFloat(foo2()[2]);
//         |putBool(foo3()[2]);
//         |putString(foo4()[2]);
//         |return;
//         |}
//       """.stripMargin
//     val expected = "77.0truechomp"
//     assert(checkCode(input, expected, 573))
//   }
//   test("return in if statement") {
//     val input =
//       """void main(){
//         |if (true) return; else return;
//         |}""".stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 574))
//   }
//   test("test short-circuit AND operator") {
//     val input =
//       """boolean infinity_loop(){
//         |return infinity_loop();
//         |}
//         |void main(){
//         |boolean a;
//         |a = false && infinity_loop();
//         |putString("This AND operator is short-circuit");
//         |return;
//         |}""".stripMargin
//     val expected = "This AND operator is short-circuit"
//     assert(checkCode(input, expected, 575))
//   }
//   test("test short-circuit OR operator") {
//     val input =
//       """boolean infinity_loop(){
//         |return infinity_loop();
//         |}
//         |void main(){
//         |boolean a;
//         |a = true || infinity_loop();
//         |putString("This AND operator is short-circuit");
//         |return;
//         |}""".stripMargin
//     val expected = "This AND operator is short-circuit"
//     assert(checkCode(input, expected, 576))
//   }
//   test("the left-hand op must be evaluated first") {
//     val input =
//       """void main(){
//         |int x;
//         |x = 6;
//         |putInt(2*x-(x=3)+x*(x=1)/x);
//         |return;
//         |}""".stripMargin
//     val expected = "12"
//     assert(checkCode(input, expected, 577))
//   }
//   test("the left-hand param must be evaluated first") {
//     val input =
//       """int x;
//         |int leftHand(){
//         |x = 1;
//         |return 1;
//         |}
//         |int rightHand(){
//         |x = 5;
//         |return 1;
//         |}
//         |void leftRight(int a,int b, int c, int d,int e){
//         |putInt(a);
//         |putInt(b);
//         |putInt(c);
//         |putInt(d);
//         |putInt(e);
//         |}
//         |void main(){
//         |x=3;
//         |leftRight(x,leftHand(),x,rightHand(),x);
//         |return;
//         |}""".stripMargin
//     val expected = "31115"
//     assert(checkCode(input, expected, 578))
//   }
//   test("simple do while statement") {
//     val input =
//       """int a;
//         |void main(){
//         |do putInt(a); a=a+1; while (a<6);
//         |return;
//         |}""".stripMargin
//     val expected = "012345"
//     assert(checkCode(input, expected, 579))
//   }
//   test("break success in do while statement one") {
//     val input =
//       """int a;
//         |void main(){
//         |do putInt(a); a=a+1; break; while (a<5);
//         |return;
//         |}""".stripMargin
//     val expected = "0"
//     assert(checkCode(input, expected, 580))
//   }
//   test("break success in do while statement two") {
//     val input =
//       """int a;
//         |void main(){
//         |do putInt(a); a=a+1; if (a==3) break; while (a<6);
//         |return;
//         |}""".stripMargin
//     val expected = "012"
//     assert(checkCode(input, expected, 581))
//   }
//   test("break fail in do while statement") {
//     val input =
//       """int a;
//         |void main(){
//         |do putInt(a); a=a+1; if (a==-1) break; while (a<6);
//         |return;
//         |}""".stripMargin
//     val expected = "012345"
//     assert(checkCode(input, expected, 582))
//   }
//   test("return in do while statement") {
//     val input =
//       """void main(){
//         |do putString("final"); return; while (true);
//         |}""".stripMargin
//     val expected = "final"
//     assert(checkCode(input, expected, 583))
//   }
//   test("continue success in do while statement") {
//     val input =
//       """int a;
//         |void main(){
//         |do a=a+1; if (a==4) continue; putInt(a); while (a<6);
//         |return;
//         |}""".stripMargin
//     val expected = "12356"
//     assert(checkCode(input, expected, 584))
//   }
//   test("simple for statement one") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=3;a<=11;a=a+3) putInt(a);
//         |return;
//         |}""".stripMargin
//     val expected = "369"
//     assert(checkCode(input, expected, 585))
//   }
//   test("simple for statement two") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=3;a<=12;a=a+3) putInt(a);
//         |return;
//         |}""".stripMargin
//     val expected = "36912"
//     assert(checkCode(input, expected, 586))
//   }
//   test("simple for statement three") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=7;a<7;a=a+3) putInt(a);
//         |return;
//         |}""".stripMargin
//     val expected = ""
//     assert(checkCode(input, expected, 587))
//   }
//   test("break success in for statement one") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=1;a<7;a=a+1) {putInt(a);break;}
//         |return;
//         |}""".stripMargin
//     val expected = "1"
//     assert(checkCode(input, expected, 588))
//   }
//   test("break success in for statement two") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=1;a<7;a=a+1) {putInt(a); if (a==4) break;}
//         |return;
//         |}""".stripMargin
//     val expected = "1234"
//     assert(checkCode(input, expected, 589))
//   }
//   test("break fail in for statement") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=1;a<7;a=a+1) {putInt(a); if (a==7) break;}
//         |return;
//         |}""".stripMargin
//     val expected = "123456"
//     assert(checkCode(input, expected, 590))
//   }
//   test("continue success in for statement") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=1;a<7;a=a+1) {putInt(a); if (a==3) {a = a+2 ;continue;} }
//         |return;
//         |}""".stripMargin
//     val expected = "1236"
//     assert(checkCode(input, expected, 591))
//   }
//   test("continue fail in for statement") {
//     val input =
//       """int a;
//         |void main(){
//         |for (a=1;a<7;a=a+1) {putInt(a); if (a==0) continue;}
//         |return;
//         |}""".stripMargin
//     val expected = "123456"
//     assert(checkCode(input, expected, 592))
//   }
//   test("break in do while in for statement") {
//     val input =
//       """int a,b;
//         |void main(){
//         |for (a=1;a<30;a=a+b) {
//         |do b = b+2; if (b >=a/2) {putInt(b); break;} while (a>b);
//         |}
//         |return;
//         |}""".stripMargin
//     val expected = "246810"
//     assert(checkCode(input, expected, 593))
//   }
//   test("the fibonacci sequence using for") {
//     val input =
//       """void main(){
//         |int x, fibo_0,fibo_1,fibo;
//         |x = 50;
//         |fibo = fibo_1 = 1;
//         |putInt(fibo_0);
//         |putInt(fibo_1);
//         |putInt(fibo);
//         |do
//         |fibo_0 = fibo_1 + fibo;
//         |fibo_1 = fibo + fibo_0;
//         |fibo = fibo_0 + fibo_1;
//         |putInt(fibo_0);
//         |putInt(fibo_1);
//         |putInt(fibo);
//         |while (fibo<x);
//         |return;
//         |}""".stripMargin
//     val expected = "01123581321345589"
//     assert(checkCode(input, expected, 594))
//   }
//   test("the golden ratio using for") {
//     val input =
//       """float goldenRatio(int deep_level){
//         |float fibo_0,fibo_1,fibo;
//         |fibo = fibo_1 = 1;
//         |do
//         |fibo_0 = fibo_1 + fibo;
//         |fibo_1 = fibo + fibo_0;
//         |fibo = fibo_0 + fibo_1;
//         |while (fibo<deep_level);
//         |return fibo/fibo_1;
//         |}
//         |void main(){
//         |putFloat(goldenRatio(1000));
//         |return;
//         |}""".stripMargin
//     val expected = "1.6180345"
//     assert(checkCode(input, expected, 595))
//   }
//   test("factorial function use for") {
//     val input =
//       """int factorial(int a){
//         |int count,result;
//         |if (a==0 || a==1) return 1;
//         |result = 1 ;
//         |for (count=2;count<=a;count=count+1) result = result * count;
//         |return result;
//         |}
//         |void main(){
//         |int x;
//         |x = 5; //getInt();
//         |putInt(factorial(x));
//         |return;
//         |}""".stripMargin
//     val expected = "120"
//     assert(checkCode(input, expected, 596))
//   }
//   test("factorial function use recursive") {
//     val input =
//       """int factorial(int a){
//         |if (a==0 || a==1) return 1; else return a*factorial(a-1);
//         |}
//         |void main(){
//         |int x;
//         |x = 5; // enter x = 5;
//         |putInt(factorial(x));
//         |return;
//         |}""".stripMargin
//     val expected = "120"
//     assert(checkCode(input, expected, 597))
//   }
//   test("square root function") {
//     val input =
//       """float EPSILON;
//         |float squareRoot(float x){
//         |if (x<0) return 0; else return testSQ(x,1);
//         |}
//         |float testSQ(float a, float b){
//         |if (closeEnough(a/b,b)) return b; else return testSQ(a,betterGuess(a,b));
//         |}
//         |boolean closeEnough(float a, float b){
//         |if (a-b>=0) return a-b < EPSILON; else return b-a < EPSILON;
//         |}
//         |float betterGuess(float a, float b){
//         |return (b+a/b)/2;
//         |}
//         |void main(){
//         |float f;
//         |EPSILON = 0.001;
//         |f = 10; // enter f = 10;
//         |putFloat(squareRoot(f));
//         |return;
//         |}""".stripMargin
//     val expected = "3.1624556"
//     assert(checkCode(input, expected, 598))
//   }
//   test("sine function") {
//     val input =
//       """int factorial(int a){
//         |if (a==0 || a==1) return 1; else return a*factorial(a-1);
//         |}
//         |float power(float a,int n){
//         |if (n==0) return 1;
//         |return a*power(a,n-1);
//         |}
//         |float sine(float x){
//         |int deep_level;
//         |float result;
//         |result = 0.0;
//         |for (deep_level = 1; deep_level < 14; deep_level = deep_level +4){
//         |result = result + power(x,deep_level)/factorial(deep_level) - power(x,deep_level+2)/factorial(deep_level+2);
//         |}
//         |return result;
//         |}
//         |void main(){
//         |float PI;
//         |PI = 3.1416;
//         |putFloat(sine(PI));
//         |return;
//         |}""".stripMargin
//     val expected = "-0.013248067"
//     assert(checkCode(input, expected, 599))
//   }
//   test("solve quadratic equation") {
//     val input =
//       """float EPSILON;
//         |float squareRoot(float x){
//         |return testSQ(x,1);
//         |}
//         |float testSQ(float a, float b){
//         |if (closeEnough(a/b,b)) return b; else return testSQ(a,betterGuess(a,b));
//         |}
//         |boolean closeEnough(float a, float b){
//         |if (a-b>=0) return a-b<EPSILON; else return b-a<EPSILON;
//         |}
//         |float betterGuess(float a, float b){
//         |return (b+a/b)/2;
//         |}
//         |void main(){ // solve equation: a*x^2 + b*x + c = 0
//         |
//         |float a,b,c,delta,x_1,x_2;
//         |//putString("enter a = "); 
//         |EPSILON = 0.001;
//         |
//         |a = getFloat(); // enter a = 2;
//         | 
//         |b = getFloat(); // enter b = 5;
//         |
//         |c = getFloat(); // enter c = 2;
//         |delta = b*b - 4*a*c; // delta = 9;
//         |if (delta < 0) {
//         |putString("The equation has no solution");
//         |return;
//         |} else {
//         |x_1 = (-b - squareRoot(delta))/(2*a); // x_1 = -2.0
//         |x_2 = (-b + squareRoot(delta))/(2*a); // x_2 = -1.0
//         |putString("x_1 = ");
//         |putFloat(x_1);
//         |putString("; x_2 = ");
//         |putFloat(x_2);
//         |}
//         |return;
//         |}""".stripMargin
//     val expected = "x_1 = -2.000023; x_2 = -0.4999771"
//     assert(checkCode(input, expected, 600))
//   }
}
