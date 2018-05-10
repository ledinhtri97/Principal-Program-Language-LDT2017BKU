.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static fibonacci(I)I
.var 0 is n I from Label0 to Label1
Label0:
	iload_0
	iconst_1
	if_icmpne Label4
	iconst_1
	goto Label5
Label4:
	iconst_0
Label5:
	ifne Label7
	iload_0
	iconst_2
	if_icmpne Label2
	iconst_1
	goto Label3
Label2:
	iconst_0
Label3:
	ifeq Label6
Label7:
	iconst_1
	goto Label8
Label6:
	iconst_0
Label8:
	ifeq Label9
	iconst_1
	goto Label1
	goto Label10
Label9:
	iload_0
	iconst_1
	isub
	invokestatic MCClass/fibonacci(I)I
	iload_0
	iconst_2
	isub
	invokestatic MCClass/fibonacci(I)I
	iadd
	goto Label1
Label10:
Label1:
	ireturn
.limit stack 4
.limit locals 1
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_5
	invokestatic MCClass/fibonacci(I)I
	invokestatic io/putInt(I)V
Label1:
	return
.limit stack 1
.limit locals 1
.end method

.method public <init>()V
.var 0 is this LMCClass; from Label0 to Label1
Label0:
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return
.limit stack 1
.limit locals 1
.end method
