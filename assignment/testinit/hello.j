;; Produced by JasminVisitor (BCEL)
;; http://bcel.sourceforge.net/
;; Wed Dec 13 20:06:49 ICT 2017

.source hello.java
.class  hello
.super java/lang/Object


.method  <init>()V
.limit stack 1
.limit locals 1
.var 0 is this Lhello; from Label0 to Label1

Label0:
.line 1
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return

.end method

.method static f2()F
.limit stack 1
.limit locals 0

.line 5
	invokestatic hello/f1()I
	i2f
	freturn

.end method

.method static f1()I
.limit stack 1
.limit locals 0

.line 9
	iconst_3
	ireturn

.end method

.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 2
.var 0 is arg0 [Ljava/lang/String; from Label0 to Label1

Label0:
.line 13
	iconst_3
	newarray float
	astore_1
.line 16
	aload_1
	iconst_2
	invokestatic hello/f2()F
	fastore
.line 18
	getstatic java.lang.System.out Ljava/io/PrintStream;
	aload_1
	iconst_2
	faload
	invokevirtual java/io/PrintStream/println(F)V
Label1:
.line 20
	return

.end method
