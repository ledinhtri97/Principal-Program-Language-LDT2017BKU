import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class LexerSuite extends FunSuite with TestLexer {

  test("a simple identifier") {
    val input = """abc"""
    val expect = "abc,<EOF>"
    assert(checkLex(input,expect,1))
  }
  test("half function declare") {
    val input = "main int {"
    val expect = """main,int,{,<EOF>"""
    assert(checkLex(input,expect,2))
  }
  test("open and close parentheses"){
    val input = "} int main {"
    val expect = """},int,main,{,<EOF>"""
    assert(checkLex(input,expect,3))
  }
  test("test 1.a") {
    val input = "abc10"
    val expect = """abc10,<EOF>"""
    assert(checkLex(input, expect, 4))
  }
  test("Line Comment") {
    val input = "abc ok//this is a comment"
    val expect = """abc,ok,<EOF>"""
    assert(checkLex(input, expect, 5))
  }
  test("Traditional Comment") {
    val input = "/*this is a comment*/ a10 this is not a comment"
    val expect = """a10,this,is,not,a,comment,<EOF>"""
    assert(checkLex(input, expect, 6))
  }
  test("Comments do not nest") {
    val input = "/*comment ne /*nest ne*/ end */ abcxyz"
    val expect =  """end,*,/,abcxyz,<EOF>"""
    assert(checkLex(input, expect, 7))
  }
  test("Identifier 1") {
    val input = "abcxyz"
    val expect =  """abcxyz,<EOF>"""
    assert(checkLex(input, expect, 8))
  }
  test("Identifier 2") {
    val input = "abcxyz12"
    val expect =  """abcxyz12,<EOF>"""
    assert(checkLex(input, expect, 9))
  }
  test("Identifier 3") {
    val input = "abc32xyz"
    val expect =  """abc32xyz,<EOF>"""
    assert(checkLex(input, expect, 10))
  }
  test("Identifier 4") {
    val input = "_abcxyz"
    val expect =  """_abcxyz,<EOF>"""
    assert(checkLex(input, expect, 11))
  }
  test("Identifier 5") {
    val input = "_"
    val expect =  """_,<EOF>"""
    assert(checkLex(input, expect, 12))
  }
  test("non Identifier 6") {
    val input = "12"
    val expect =  """12,<EOF>"""
    assert(checkLex(input, expect, 13))
  }
  test("Identifier 7") {
    val input = "_123 a_123 1_2adv"
    val expect =  """_123,a_123,1,_2adv,<EOF>"""
    assert(checkLex(input, expect, 14))
  }
  test("FLOAT LITERALS 1") {
    val input = "2.32.23"
    val expect =  """ErrorToken ."""
    assert(checkLex(input, expect, 15))
  }
  test("STRING LITERALS 1") {
    val input = """ "STRING IS HERE" """
    val expect =  """STRING IS HERE,<EOF>"""
    assert(checkLex(input, expect, 16))
  }
  test("FLOAT LITERALS 2") {
    val input = "e-41"
    val expect =  """e,-,41,<EOF>"""
    assert(checkLex(input, expect, 17))
  }
  test("function") {
    val input = "putIntLn(4)"
    val expect =  """putIntLn,(,4,),<EOF>"""
    assert(checkLex(input, expect, 18))
  }
  test("variable declare"){
    val input = "int a,b,c;"
    val expect = """int,a,,,b,,,c,;,<EOF>"""
    assert(checkLex(input, expect, 19))
  }
  test("flit 1"){
    val input = "1.e-1"
    val expect = """1.e-1,<EOF>"""
    assert(checkLex(input, expect, 20))
  }
  test("flit 2"){
    val input = "1.2"
    val expect = """1.2,<EOF>"""
    assert(checkLex(input, expect, 21))
  }
  test("flit 3"){
    val input = "1."
    val expect = """1.,<EOF>"""
    assert(checkLex(input, expect, 22))
  }
  test("flit 4"){
    val input = ".1"
    val expect = """.1,<EOF>"""
    assert(checkLex(input, expect, 23))
  }
  test("flit 5"){
    val input = "1e2"
    val expect = """1e2,<EOF>"""
    assert(checkLex(input, expect, 24))
  }
  test("flit 6"){
    val input = "1.2E-2"
    val expect = """1.2E-2,<EOF>"""
    assert(checkLex(input, expect, 25))
  }
  test("flit 7"){
    val input = ".1E2"
    val expect = """.1E2,<EOF>"""
    assert(checkLex(input, expect, 26))
  }
  test("flit 8"){
    val input = "9.0"
    val expect = """9.0,<EOF>"""
    assert(checkLex(input, expect, 27))
  }
  test("flit 9"){
    val input = "12e8"
    val expect = """12e8,<EOF>"""
    assert(checkLex(input, expect, 28))
  }
  test("flit 10"){
    val input = "0.33E-3"
    val expect = """0.33E-3,<EOF>"""
    assert(checkLex(input, expect, 29))
  }
  test("flit 11"){
    val input = "128e-42"
    val expect = """128e-42,<EOF>"""
    assert(checkLex(input, expect, 30))
  }
  test("Unclose String 1"){
    val input = """"String doesn't close"""
    val expect = """Unclosed string: String doesn't close"""
    assert(checkLex(input, expect, 31))
  }
  test("String Literal") {
    val input =""""\" abc \n \"""""
    val expect ="""\" abc \n \",<EOF>"""
    assert(checkLex(input,expect,32))
  }
  test("Illegal Escape") {
    val input =""""\" abc \k \" """"
    val expect ="""Illegal escape in string: \" abc \k"""
    assert(checkLex(input,expect,33))
  }
  test("non FLOAT") {
    val input ="e-12"
    val expect ="e,-,12,<EOF>"
    assert(checkLex(input,expect,34))
  }
  test("String C1") {
    val input =""""abc
    """
    val expect ="""Unclosed string: abc"""
    assert(checkLex(input,expect,35))
  }
  test("String C2") {
    val input =""" "\t
    """"
    val expect ="""Unclosed string: \t"""
    assert(checkLex(input,expect,36))
  }
  test("Comment C1") {
    val input ="""/*
    ***
    */"""
    val expect ="""<EOF>"""
    assert(checkLex(input,expect,37))
  }
  test("program") {
    val input ="""void main () {}"""
    val expect ="""void,main,(,),{,},<EOF>"""
    assert(checkLex(input,expect,38))
  }
  test("identifier 001") {
    val input ="""1ax"""
    val expect ="""1,ax,<EOF>"""
    assert(checkLex(input,expect,39))
  }
  test("identifier 002") {
    val input ="""_da21s"""
    val expect ="""_da21s,<EOF>"""
    assert(checkLex(input,expect,40))
  }
  test("non FLOAT 2") {
    val input ="12E"
    val expect ="12,E,<EOF>"
    assert(checkLex(input,expect,41))
  }
  test("42") {
    val input =""""\n ""'"""
    val expect ="""\n ,Unclosed string: '"""
    assert(checkLex(input,expect,42))
  }
  test("43") {
    val input ="""string s = "abc"""
    val expect ="""string,s,=,Unclosed string: abc"""
    assert(checkLex(input,expect,43))
  }
  test("44") {
    val input ="""k[5]"""
    val expect ="""k,[,5,],<EOF>"""
    assert(checkLex(input,expect,44))
  }
  test("45") {
    val input =""""\\"abc""""
    val expect ="""\\,abc,ErrorToken """"
    assert(checkLex(input,expect,45))
  }
  test("46") {
    val input = """"\'abc""""
    val expect ="""\'abc,<EOF>"""
    assert(checkLex(input,expect,46))
  }
  test("47") {
    val input =""""\\"abc" """
    val expect ="""\\,abc,Unclosed string:  """
    assert(checkLex(input,expect,47))
  }
  test("48") {
     val input = """"!""""
     val expect = """!,<EOF>"""
    assert(checkLex(input,expect,48))
  }
  test("49") {
    val input ="""12.ed \\comment"""
    val expect ="""12.,ed,ErrorToken \"""
    assert(checkLex(input,expect,49))
  }
  test("50") {
    val input =""""abcxyz\d asdj" """"
    val expect ="""Illegal escape in string: abcxyz\d"""
    assert(checkLex(input,expect,50))
  }
  test("51") {
    val input =""""\"illegal escape in string\n\"legal string\"""""
    val expect ="""\"illegal escape in string\n\"legal string\",<EOF>"""
    assert(checkLex(input,expect,51))
  }
  test("52") {
    val input ="\"illegal escape in string\n\"legal string\""
    val expect ="""Unclosed string: illegal escape in string"""
    assert(checkLex(input,expect,52))
  }
  test("53") {
    val input ="\"legal string\"\"illegal string\n"
    val expect ="""legal string,Unclosed string: illegal string"""
    assert(checkLex(input,expect,53))
  }
  test("54") {
    val input =""" "\" """
    val expect ="""Unclosed string: \" """
    assert(checkLex(input,expect,54))
  }
  test("55") {
    val input ="""c=c+1"""
    val expect ="""c,=,c,+,1,<EOF>"""
    assert(checkLex(input,expect,55))
  }
  test("56") {
    val input ="\"abc\r\""
    val expect ="""Unclosed string: abc"""
    assert(checkLex(input,expect,56))
  }
  test("57") {
    val input ="\"abc\r\n\""
    val expect ="""Unclosed string: abc"""
    assert(checkLex(input,expect,57))
  }
  test("58") {
    val input =""""dada\r aksjd\b \a """"
    val expect ="""Illegal escape in string: dada\r aksjd\b \a"""
    assert(checkLex(input,expect,58))
  }
  test("59") {
    val input ="""2.3 + s.a-2*2"""
    val expect ="""2.3,+,s,ErrorToken ."""
    assert(checkLex(input,expect,59))
  }
  test("60") {
    val input ="""a[2][foo("\r string \a")]"""
    val expect ="""a,[,2,],[,foo,(,Illegal escape in string: \r string \a"""
    assert(checkLex(input,expect,60))
  }
  test("61") {
    val input ="""s("\t string")|d+2"""
    val expect ="""s,(,\t string,),ErrorToken |"""
    assert(checkLex(input,expect,61))
  }
  test("62") {
    val input ="""boolean a; a = true; ~a = false"""
    val expect ="""boolean,a,;,a,=,true,;,ErrorToken ~"""
    assert(checkLex(input,expect,62))
  }
  test("63") {
    val input ="""a+b==c?print(a):print(b)"""
    val expect ="""a,+,b,==,c,ErrorToken ?"""
    assert(checkLex(input,expect,63))
  }
  test("64") {
    val input ="""<h1>"Ngay dau tien di hoc"</<h1>"""
    val expect ="""<,h1,>,Ngay dau tien di hoc,<,/,<,h1,>,<EOF>"""
    assert(checkLex(input,expect,64))
  }
  test("65") {
    val input ="""shif__+__boo"""
    val expect ="""shif__,+,__boo,<EOF>"""
    assert(checkLex(input,expect,65))
  }
  test("66") {
    val input ="""\/^0^\/"""
    val expect ="""ErrorToken \"""
    assert(checkLex(input,expect,66))
  }
  test("67") {
    val input =""" "\t + my money: = 1000" + $dolla"""
    val expect ="""\t + my money: = 1000,+,ErrorToken $"""
    assert(checkLex(input,expect,67))
  }
  test("68") {
    val input =""" "t33n c0d4 4 y0u ne` he\/ he/" """
    val expect ="""Illegal escape in string: t33n c0d4 4 y0u ne` he\/"""
    assert(checkLex(input,expect,68))
  }
  test("69") {
    val input =""""day la Unclosed string \n
    haha
    """
    val expect ="""Unclosed string: day la Unclosed string \n"""
    assert(checkLex(input,expect,69))
  }
  test("70") {
    val input ="""0000"""
    val expect ="""0000,<EOF>"""
    assert(checkLex(input,expect,70))
  }
  // test("71") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,71))
  // }
  // test("72") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,72))
  // }
  // test("73") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,73))
  // }
  // test("74") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,74))
  // }
  // test("75") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,75))
  // }
  // test("76") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,76))
  // }

  // test("77") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,77))
  // }
  // test("78") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,78))
  // }
  // test("79") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,79))
  // }
  // test("80") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,81))
  // }
  // test("82") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,82))
  // }
  // test("83") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,83))
  // }
  // test("84") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,84))
  // }
  // test("85") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,85))
  // }
  // test("86") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,86))
  // }
  // test("87") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,87))
  // }
  // test("88") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,88))
  // }
  // test("89") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,89))
  // }
  // test("90") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,90))
  // }
  // test("91") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,91))
  // }
  // test("90") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,90))
  // }
  // test("91") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,91))
  // }
  // test("92") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,92))
  // }
  // test("93") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,93))
  // }
  // test("94") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,94))
  // }
  // test("95") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,95))
  // }
  // test("96") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,96))
  // }
  // test("97") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,97))
  // }
  // test("98") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,98))
  // }
  // test("99") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,99))
  // }
  // test("100") {
  //   val input =""" """
  //   val expect =""" """
  //   assert(checkLex(input,expect,100))
  // }
}
// import org.scalatest.FunSuite

// /**
//   * Created by nhphung on 4/28/17.
//   */
// class LexerSuite extends FunSuite with TestLexer {

//   test("a simple identifier") {
//     val input = "abc"
//     val expect = "abc,<EOF>"
//     assert(checkLex(input,expect,1))
//   }
//   test("half function declare") {
//     val input = "main int {"
//     val expect = "main,int,{,<EOF>"
//     assert(checkLex(input,expect,2))
//   }
//   test("open and close parentheses"){
//     val input = "} int main {"
//     val expect = "},int,main,{,<EOF>"
//     assert(checkLex(input,expect,3))
//   }
//   test("float number"){
//     val input = "002."
//     val expect = "002.,<EOF>"
//     assert(checkLex(input,expect,4))
//   }
//   test("float number 2"){
//     val input = ".100"
//     val expect = ".100,<EOF>"
//     assert(checkLex(input,expect,5))
//   }
//   test("float number 3"){
//     val input = "2.1"
//     val expect = "2.1,<EOF>"
//     assert(checkLex(input,expect,6))
//   }
//   test("float number 4"){
//     val input = "001e20"
//     val expect = "001e20,<EOF>"
//     assert(checkLex(input,expect,7))
//   }
//   test("float number 5"){
//     val input = "2.2E-1"
//     val expect = "2.2E-1,<EOF>"
//     assert(checkLex(input,expect,8))
//   }
//   test("float number 6"){
//     val input = ".1e-2"
//     val expect = ".1e-2,<EOF>"
//     assert(checkLex(input,expect,9))
//   }
//   test("float number 7"){
//     val input = "21e4"
//     val expect = "21e4,<EOF>"
//     assert(checkLex(input,expect,10))
//   }
//   test("float number 8"){
//     val input = "0.33e-4"
//     val expect = "0.33e-4,<EOF>"
//     assert(checkLex(input,expect,11))
//   }
//   test("float number 9"){
//     val input = "."
//     val expect = "ErrorToken ."
//     assert(checkLex(input,expect,12))
//   }
//   test("string"){
//     val input = """"a""""
//     val expect = """a,<EOF>"""
//     assert(checkLex(input,expect,13))
//   }
//   test("comment"){
//     val input = """//a"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,14))
//   }
//   test("block comment"){
//     val input = """/*a*/"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,15))
//   }
//   test("unclose string"){
//     val input = """"aaaaa"""
//     val expect = """Unclosed string: aaaaa"""
//     assert(checkLex(input,expect,16))
//   }
//   test("illegal escape"){
//     val input = """"\a""""
//     val expect = """Illegal escape in string: \a"""
//     assert(checkLex(input,expect,17))
//   }
//   test("a lot of token, 1 error token"){
//     val input = """1.2
// 1.
// .1 
// 1e1 
// true
// false
// float
// continue
// break
// _
// """
//     val expect = """1.2,1.,.1,1e1,true,false,float,continue,break,_,<EOF>"""
//     assert(checkLex(input,expect,18))
//   }
//    test("1 error token"){
//     val input = """_"""
//     val expect = """_"""
//     assert(checkLex(input,expect,19))
//   }
//   test("1 token + 1 error token"){
//     val input = """a _"""
//     val expect = """a,_"""
//     assert(checkLex(input,expect,20))
//   }
//   test("1 token id"){
//     val input = """a_"""
//     val expect = """a_,<EOF>"""
//     assert(checkLex(input,expect,21))
//   }
//   test("1 error token id"){
//     val input = """___"""
//     val expect = """ErrorToken _"""
//     assert(checkLex(input,expect,22))
//   }
//   test("a lot of token, 1 unclose string"){
//     val input = """+
// *
// &&
// "abcd ""da a"
// "as as"
// "asd a.a"a
// "ad a@
// "
// """
//     val expect = """+,*,&&,abcd ,da a,as as,asd a.a,a,Unclosed string: ad a@"""
//     assert(checkLex(input,expect,23))
//   }
//   test("unclose string 2"){
//     val input = """"ad a@
// "
// """
//     val expect = """Unclosed string: ad a@"""
//     assert(checkLex(input,expect,24))
//   }
//    test("unclose string 3"){
//     val input = """a"ad a@
// "
// """
//     val expect = """a,Unclosed string: ad a@"""
//     assert(checkLex(input,expect,25))
//   }
//   test("unclose string 4"){
//     val input = """a
// "ad a@
// "
// """
//     val expect = """a,Unclosed string: ad a@"""
//     assert(checkLex(input,expect,26))
//   }
//   test("a lot of token, 1 unclose string 2"){
//     val input = """"da a"
// "ad a@
// "
// """
//     val expect = """da a,Unclosed string: ad a@"""
//     assert(checkLex(input,expect,27))
//   }
//   test("block comment 2"){
//     val input = """/*
// ***
// */"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,28))
//   }
//   test("block comment 3"){
//     val input = """/**/
// */"""
//     val expect = """*,/,<EOF>"""
//     assert(checkLex(input,expect,29))
//   }
//   test("1 token"){
//     val input = """*"""
//     val expect = """*,<EOF>"""
//     assert(checkLex(input,expect,30))
//   }
//    test("a lot of token"){
//     val input = """+
// *
// &&
// "abcd ""da a"
// "as as"
// "asd a.a"a
// "ad a@"
// """""
//     val expect = """+,*,&&,abcd ,da a,as as,asd a.a,a,ad a@,,<EOF>"""
//     assert(checkLex(input,expect,31))
//   }
//   test("a lot of token 2"){
//     val input = """1.2
// 1.
// .1 
// 1e1 
// true
// false
// float
// continue
// break
// _a
// +
// *
// &&
// "abcd ""da a"
// "as as"
// "asd a.a"a
// "ad a@"
// """""
//     val expect = """1.2,1.,.1,1e1,true,false,float,continue,break,_a,+,*,&&,abcd ,da a,as as,asd a.a,a,ad a@,,<EOF>"""
//     assert(checkLex(input,expect,32))
//   }
//   test("1 token id 2"){
//     val input = """a_123"""
//     val expect = """a_123,<EOF>"""
//     assert(checkLex(input,expect,33))
//   }
//   test("2 token id"){
//     val input = """a_ _a"""
//     val expect = """a_,_a,<EOF>"""
//     assert(checkLex(input,expect,34))
//   }
//   test("2 token id, 1 number"){
//     val input = """a_1 1_a"""
//     val expect = """a_1,1,_a,<EOF>"""
//     assert(checkLex(input,expect,35))
//   }
//   test("1 token id 3"){
//     val input = """a_123_"""
//     val expect = """a_123_,<EOF>"""
//     assert(checkLex(input,expect,36))
//   }
//   test("1 token id 4"){
//     val input = """a_123_11"""
//     val expect = """a_123_11,<EOF>"""
//     assert(checkLex(input,expect,37))
//   }
//   test("comment 2"){
//     val input = """//a//"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,38))
//   }
//   test("comment 3"){
//     val input = """//a/*a*/"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,39))
//   }
//   test("block comment 4"){
//     val input = """/*//a
// */"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,40))
//   }
//   test("array type"){
//     val input = """a[]"""
//     val expect = """a,[,],<EOF>"""
//     assert(checkLex(input,expect,41))
//   }
//   test("array pointer type"){
//     val input = """int[3]"""
//     val expect = """int,[,3,],<EOF>"""
//     assert(checkLex(input,expect,42))
//   }
//   test("math string"){
//     val input = """1+2-3*4/5=6int[3]"""
//     val expect = """1,+,2,-,3,*,4,/,5,=,6,int,[,3,],<EOF>"""
//     assert(checkLex(input,expect,43))
//   }
//   test("math string 2"){
//     val input = """1+a-abc*_q/a_2=6int[3]"""
//     val expect = """1,+,a,-,abc,*,_q,/,a_2,=,6,int,[,3,],<EOF>"""
//     assert(checkLex(input,expect,44))
//   }
//   test("math string unclose"){
//     val input = """"1+a-abc*_q/a_2=6int[3]"""
//     val expect = """Unclosed string: 1+a-abc*_q/a_2=6int[3]"""
//     assert(checkLex(input,expect,45))
//   }
//   test("math string 3"){
//     val input = """"1+a-abc*_q/a_2=6int[3]""""
//     val expect = """1+a-abc*_q/a_2=6int[3],<EOF>"""
//     assert(checkLex(input,expect,46))
//   }
//   test("a lot of token 3"){
//     val input = """/* aa//a@ */
// //aff6@
// //affa!
// 3.4
// 1.1E+2 
// 1.2e-2 
// .1E2
// }
// {
// ;
// ,
// abc[]
// //aaa"""
//     val expect = """3.4,1.1E+2,1.2e-2,.1E2,},{,;,,,abc,[,],<EOF>"""
//     assert(checkLex(input,expect,47))
//   }
//   test("small program"){
//     val input = """int i;
// int f() {
// return 200;
// }"""
//     val expect = """int,i,;,int,f,(,),{,return,200,;,},<EOF>"""
//     assert(checkLex(input,expect,48))
//   }
//   test("small program 2"){
//     val input = """int main() {
// int main;
// main = f();
// putIntLn(i);"""
//     val expect = """int,main,(,),{,int,main,;,main,=,f,(,),;,putIntLn,(,i,),;,<EOF>"""
//     assert(checkLex(input,expect,49))
//   }
//   test("small program 3"){
//     val input = """main = f = 100;
// putIntLn(i);
// putIntLn(main);
// putIntLn(f);"""
//     val expect = """main,=,f,=,100,;,putIntLn,(,i,),;,putIntLn,(,main,),;,putIntLn,(,f,),;,<EOF>"""
//     assert(checkLex(input,expect,50))
//   }
//   test("long program"){
//     val input = """int i;
// int f() {
// return 200;
// }
// int main() {
// int main;
// main = f();
// putIntLn(i);
// {
// int i = 2;
// int main;
// int f;
// main = f = 100;
// putIntLn(i);
// putIntLn(main);
// putIntLn(f);
// }
// putIntLn(main);
// }"""
//     val expect = """int,i,;,int,f,(,),{,return,200,;,},int,main,(,),{,int,main,;,main,=,f,(,),;,putIntLn,(,i,),;,{,int,i,=,2,;,int,main,;,int,f,;,main,=,f,=,100,;,putIntLn,(,i,),;,putIntLn,(,main,),;,putIntLn,(,f,),;,},putIntLn,(,main,),;,},<EOF>"""
//     assert(checkLex(input,expect,51))
//   }
//   test("if stmt"){
//     val input = """if (a>b) {a=b;}"""
//     val expect = """if,(,a,>,b,),{,a,=,b,;,},<EOF>"""
//     assert(checkLex(input,expect,52))
//   }
//    test("if stmt 2"){
//     val input = """if (a>b) {a=b;} else {a=0;}"""
//     val expect = """if,(,a,>,b,),{,a,=,b,;,},else,{,a,=,0,;,},<EOF>"""
//     assert(checkLex(input,expect,53))
//   }
//   test("do while stmt"){
//     val input = """do a=b while a>b;"""
//     val expect = """do,a,=,b,while,a,>,b,;,<EOF>"""
//     assert(checkLex(input,expect,54))
//   }
//   test("for stmt"){
//     val input = """for (a=1;a<=3;a++) b=b+1;"""
//     val expect = """for,(,a,=,1,;,a,<=,3,;,a,+,+,),b,=,b,+,1,;,<EOF>"""
//     assert(checkLex(input,expect,55))
//   }
//   test("string of number, wrong float"){
//     val input = """1 .1 2. 2e3 3e .e2"""
//     val expect = """1,.1,2.,2e3,3,e,ErrorToken ."""
//     assert(checkLex(input,expect,56))
//   }
//   test("string of number"){
//     val input = """1 .1 2. 2e3 3e .1e2"""
//     val expect = """1,.1,2.,2e3,3,e,.1e2,<EOF>"""
//     assert(checkLex(input,expect,57))
//   }
//   test("string of operator"){
//     val input = """&& || % != == >= <="""
//     val expect = """&&,||,%,!=,==,>=,<=,<EOF>"""
//     assert(checkLex(input,expect,58))
//   }
//   test("string of operator in line comment"){
//     val input = """//&& || % != == >= <="""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,59))
//   }
//   test("string of operator in block comment"){
//     val input = """/*&& || % != == >= <=*/"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,60))
//   }
//   test("string of operator in block comment 2"){
//     val input = """/*&& || % != == >= <=
// */"""
//     val expect = """<EOF>"""
//     assert(checkLex(input,expect,61))
//   }
//   test("semi colon"){
//     val input = """;"""
//     val expect = """;,<EOF>"""
//     assert(checkLex(input,expect,62))
//   }
//   test("semi colon 2"){
//     val input = """a;"""
//     val expect = """a,;,<EOF>"""
//     assert(checkLex(input,expect,63))
//   }
//   test("array type 2"){
//     val input = """a[1]"""
//     val expect = """a,[,1,],<EOF>"""
//     assert(checkLex(input,expect,64))
//   }
//   test("test program"){
//     val input = """int test (int a) {
// }
// int main () {
// int a;
// float abc, b[1];
// string s;
// boolean ss;
// }"""
//     val expect = """int,test,(,int,a,),{,},int,main,(,),{,int,a,;,float,abc,,,b,[,1,],;,string,s,;,boolean,ss,;,},<EOF>"""
//     assert(checkLex(input,expect,65))
//   }
//   test("test program 2"){
//     val input = """int main () {
// if (a > b) {if (a > b) {a = b;}} else {if (a > b) {a = b;}}
// }"""
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,if,(,a,>,b,),{,a,=,b,;,},},else,{,if,(,a,>,b,),{,a,=,b,;,},},},<EOF>"""
//     assert(checkLex(input,expect,66))
//   }
//   test("test program 3"){
//     val input = """int main () {
// int a;
// float abc, b[1];
// string s;
// }"""
//     val expect = """int,main,(,),{,int,a,;,float,abc,,,b,[,1,],;,string,s,;,},<EOF>"""
//     assert(checkLex(input,expect,67))
//   }
//   test("main func with 1 if stmt 7"){
//     val input = """int main () {
// if (a > b) {
//   if (a > b) {
//     a = b;
//   } 
//   else {
//     if (a > b) {
//       a = b;
//     }
//   }
// }
// else {
//   if (a > b) {
//     a = b;
//   }
//   else {
//     if (a > b) {
//       a = b;
//     }
//   }
// }
// }"""
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,if,(,a,>,b,),{,a,=,b,;,},else,{,if,(,a,>,b,),{,a,=,b,;,},},},else,{,if,(,a,>,b,),{,a,=,b,;,},else,{,if,(,a,>,b,),{,a,=,b,;,},},},},<EOF>"""
//     assert(checkLex(input,expect,68))
//   }
//    test("main func with 1 do while stmt"){
//     val input = """int main () {
// do a = a + 1; while a < 10;
// }"""
//     val expect = """int,main,(,),{,do,a,=,a,+,1,;,while,a,<,10,;,},<EOF>"""
//     assert(checkLex(input,expect,69))
//   }
//   test("main func with 1 do while stmt 2"){
//     val input = """int main () {
// do a = a + 1; while (a < 10);
// }"""
//     val expect = """int,main,(,),{,do,a,=,a,+,1,;,while,(,a,<,10,),;,},<EOF>"""
//     assert(checkLex(input,expect,70))
//   }
//   test("main func with 1 do while stmt 3"){
//     val input = """int main () {
// do if (a > b) {a = b;} while (a < 10);
// }"""
//     val expect = """int,main,(,),{,do,if,(,a,>,b,),{,a,=,b,;,},while,(,a,<,10,),;,},<EOF>"""
//     assert(checkLex(input,expect,71))
//   }
//   test("main func with 1 do while stmt with block stmt"){
//     val input = """int main () {
// do {
//   if (a > b) {a = b;}
//   } while (a < 10);
// }"""
//     val expect = """int,main,(,),{,do,{,if,(,a,>,b,),{,a,=,b,;,},},while,(,a,<,10,),;,},<EOF>"""
//     assert(checkLex(input,expect,72))
//   }
//   test("main func with 1 do while stmt with block stmt 2"){
//     val input = """int main () {
// do {
//   int a;
//   if (a > b) {a = b;}
//   } while (a < 10);
// }"""
//     val expect = """int,main,(,),{,do,{,int,a,;,if,(,a,>,b,),{,a,=,b,;,},},while,(,a,<,10,),;,},<EOF>"""
//     assert(checkLex(input,expect,73))
//   }
//   test("main func with 1 do while stmt with block stmt 3"){
//     val input = """int main () {
// do {
//   int a;
//   if (a > b) {a = b;}
//   do {
//     int a;
//     if (a > b) {a = b;}
//     } while (a < 10);
//   } while (a < 10);
// }"""
//     val expect = """int,main,(,),{,do,{,int,a,;,if,(,a,>,b,),{,a,=,b,;,},do,{,int,a,;,if,(,a,>,b,),{,a,=,b,;,},},while,(,a,<,10,),;,},while,(,a,<,10,),;,},<EOF>"""
//     assert(checkLex(input,expect,74))
//   }
//    test("main func with 1 for stmt"){
//     val input = """int main () {
// for (i=0;i<=n;i=i+1) a = a + 1;
// }"""
//     val expect = """int,main,(,),{,for,(,i,=,0,;,i,<=,n,;,i,=,i,+,1,),a,=,a,+,1,;,},<EOF>"""
//     assert(checkLex(input,expect,75))
//   }
//   test("main func with 1 for stmt 2"){
//     val input = """int main () {
// for (i=0;i<=n;i=i+1) {a = a + 1;}
// }"""
//     val expect = """int,main,(,),{,for,(,i,=,0,;,i,<=,n,;,i,=,i,+,1,),{,a,=,a,+,1,;,},},<EOF>"""
//     assert(checkLex(input,expect,76))
//   }
//   test("main func with 1 for stmt with block stmt"){
//     val input = """int main () {
// for (i=0;i<=n;i=i+1) {
//   int a;
//   a = a + 1;
// }
// }"""
//     val expect = """int,main,(,),{,for,(,i,=,0,;,i,<=,n,;,i,=,i,+,1,),{,int,a,;,a,=,a,+,1,;,},},<EOF>"""
//     assert(checkLex(input,expect,77))
//   }
//   test("main func with 1 for stmt with block stmt 2"){
//     val input = """int main () {
// for (i=0;i<=n;i=i+1) {
//   int a;
//   a = a + 1;
//   if (a > b) {
//     if (a > b) {
//       a = b;
//     } 
//     else {
//       if (a > b) {
//         a = b;
//       }
//     }
//   }
//   else {
//     if (a > b) {
//       a = b;
//     }
//     else {
//       if (a > b) {
//         a = b;
//       }
//     }
//   }
// }
// }"""
//     val expect = """int,main,(,),{,for,(,i,=,0,;,i,<=,n,;,i,=,i,+,1,),{,int,a,;,a,=,a,+,1,;,if,(,a,>,b,),{,if,(,a,>,b,),{,a,=,b,;,},else,{,if,(,a,>,b,),{,a,=,b,;,},},},else,{,if,(,a,>,b,),{,a,=,b,;,},else,{,if,(,a,>,b,),{,a,=,b,;,},},},},},<EOF>"""
//     assert(checkLex(input,expect,78))
//   }
//   test("main func with 1 for stmt with block stmt 3"){
//     val input = """int main () {
// for (i=0;i<=n;i=i+1) {
//   int a;
//   a = a + 1;
//   do {
//     int a;
//     if (a > b) {a = b;}
//     do {
//       int a;
//       if (a > b) {a = b;}
//       } while (a < 10);
//   } while (a < 10);
// }
// }"""
//     val expect = """int,main,(,),{,for,(,i,=,0,;,i,<=,n,;,i,=,i,+,1,),{,int,a,;,a,=,a,+,1,;,do,{,int,a,;,if,(,a,>,b,),{,a,=,b,;,},do,{,int,a,;,if,(,a,>,b,),{,a,=,b,;,},},while,(,a,<,10,),;,},while,(,a,<,10,),;,},},<EOF>"""
//     assert(checkLex(input,expect,79))
//   }
//   test("main func with 1 break stmt"){
//     val input = """int main () {
// break;
// }"""
//     val expect = """int,main,(,),{,break,;,},<EOF>"""
//     assert(checkLex(input,expect,80))
//   }
//    test("main func with 1 continue stmt"){
//     val input = """int main () {
// continue;
// }"""
//     val expect = """int,main,(,),{,continue,;,},<EOF>"""
//     assert(checkLex(input,expect,81))
//   }
//   test("main func with 1 return stmt"){
//     val input = """int main () {
// return;
// }"""
//     val expect = """int,main,(,),{,return,;,},<EOF>"""
//     assert(checkLex(input,expect,82))
//   }
//   test("main func with 1 return stmt 2"){
//     val input = """int main () {
// return a;
// }"""
//     val expect = """int,main,(,),{,return,a,;,},<EOF>"""
//     assert(checkLex(input,expect,83))
//   }
//   test("main func with 1 return stmt 3"){
//     val input = """int main () {
// return (a);
// }"""
//     val expect = """int,main,(,),{,return,(,a,),;,},<EOF>"""
//     assert(checkLex(input,expect,84))
//   }
//   test("main func with 1 return stmt 4"){
//     val input = """int main () {
// return (a + b);
// }"""
//     val expect = """int,main,(,),{,return,(,a,+,b,),;,},<EOF>"""
//     assert(checkLex(input,expect,85))
//   }
//   test("main func with 1 return stmt 5"){
//     val input = """int main () {
// return (a + b /c * d);
// }"""
//     val expect = """int,main,(,),{,return,(,a,+,b,/,c,*,d,),;,},<EOF>"""
//     assert(checkLex(input,expect,86))
//   }
//   test("main func with 1 return stmt 6"){
//     val input = """int main () {
// return (a + b /(c * d));
// }"""
//     val expect = """int,main,(,),{,return,(,a,+,b,/,(,c,*,d,),),;,},<EOF>"""
//     assert(checkLex(input,expect,87))
//   }
//   test("main func with 1 return stmt 7"){
//     val input = """int main () {
// return (a + b /(c * d) > 1.2e12);
// }"""
//     val expect = """int,main,(,),{,return,(,a,+,b,/,(,c,*,d,),>,1.2e12,),;,},<EOF>"""
//     assert(checkLex(input,expect,88))
//   }
//   test("main func with 1 if stmt 8"){
//     val input = """int main () {
// if (a >= b)
//   if (a > b)
//     a > b;
// else
//   a = b;
// }"""
//     val expect = """int,main,(,),{,if,(,a,>=,b,),if,(,a,>,b,),a,>,b,;,else,a,=,b,;,},<EOF>"""
//     assert(checkLex(input,expect,89))
//   }
//   test("main func with 1 call func stmt"){
//     val input = """int main () {
// abc(xyz);
// }"""
//     val expect = """int,main,(,),{,abc,(,xyz,),;,},<EOF>"""
//     assert(checkLex(input,expect,90))
//   }
//    test("main func with 1 call func stmt 2"){
//     val input = """int main () {
// abc(xyz, 1+1, a[2]);
// }"""
//     val expect = """int,main,(,),{,abc,(,xyz,,,1,+,1,,,a,[,2,],),;,},<EOF>"""
//     assert(checkLex(input,expect,91))
//   }
//   test("main func with 1 call func stmt 3"){
//     val input = """int main () {
// abc(xyz, (1+1) + a[2]);
// }"""
//     val expect = """int,main,(,),{,abc,(,xyz,,,(,1,+,1,),+,a,[,2,],),;,},<EOF>"""
//     assert(checkLex(input,expect,92))
//   }
//   test("main func with 1 call func stmt 4"){
//     val input = """int main () {
// abc(xyz, (1+1) + a[2] && b || x);
// }"""
//     val expect = """int,main,(,),{,abc,(,xyz,,,(,1,+,1,),+,a,[,2,],&&,b,||,x,),;,},<EOF>"""
//     assert(checkLex(input,expect,93))
//   }
//   test("main func multi expr"){
//     val input = """int main () {
// a+b+c = 1*2*3 == 5/a+r;
// }"""
//     val expect = "int,main,(,),{,a,+,b,+,c,=,1,*,2,*,3,==,5,/,a,+,r,;,},<EOF>"
//     assert(checkLex(input,expect,94))
//   }
//   test("main func with 1 if stmt with error"){
//     val input = """int main () {
// if (a > b) {a = b}
// }"""
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,a,=,b,},},<EOF>"""
//     assert(checkLex(input,expect,95))
//   }
//    test("main func with 1 if stmt 2 with error"){
//     val input = """int main () {
// if (a > b) {a = b} else {a = b + 1 ;}
// }"""
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,a,=,b,},else,{,a,=,b,+,1,;,},},<EOF>"""
//     assert(checkLex(input,expect,96))
//   }
//    test("main func with 1 if stmt 3 with error"){
//     val input = """int main () {
// if (a > b) {if (a > b) {a = b;}} else {a = b + 1 ;}
// """
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,if,(,a,>,b,),{,a,=,b,;,},},else,{,a,=,b,+,1,;,},<EOF>"""
//     assert(checkLex(input,expect,97))
//   }
//    test("main func with 1 if stmt 4 with error"){
//     val input = """int main () {
// if (a > b) {if (a > b) {a = b;}} else {if (a > b) {a = b;}};
// }"""
//     val expect = """int,main,(,),{,if,(,a,>,b,),{,if,(,a,>,b,),{,a,=,b,;,},},else,{,if,(,a,>,b,),{,a,=,b,;,},},;,},<EOF>"""
//     assert(checkLex(input,expect,98))
//   }
//    test("func with 2 id"){
//     val input = """int main abc () {
// }"""
//     val expect = """int,main,abc,(,),{,},<EOF>"""
//     assert(checkLex(input,expect,99))
//   }
//   test("func with 2 id no bracket"){
//     val input = """int main abc {
// }"""
//     val expect = """int,main,abc,{,},<EOF>"""
//     assert(checkLex(input,expect,100))
//   }
// }

