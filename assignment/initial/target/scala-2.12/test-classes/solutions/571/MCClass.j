.source MCClass.java
.class public MCClass
.super java.lang.Object

.method public static isPrime(I)Z
.var 0 is n I from Label0 to Label1
Label0:
	iconst_2
	istore_1
Label2:
	iload_1
	iload_0
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ifeq Label5
	iload_0
	iload_1
	irem
	iconst_0
	if_icmpne Label8
	iconst_1
	goto Label9
Label8:
	iconst_0
Label9:
	ifeq Label10
	iconst_0
	goto Label3
Label10:
Label4:
	iload_1
	iconst_1
	iadd
	istore_1
	goto Label2
Label5:
Label3:
	iconst_1
	goto Label1
Label1:
	ireturn
.limit stack 3
.limit locals 2
.end method

.method public static printAllPrime([II)V
.var 0 is arr [I from Label0 to Label1
.var 1 is n I from Label0 to Label1
Label0:
	iconst_0
	istore_2
Label2:
	iload_2
	iload_1
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ifeq Label5
	aload_0
	iload_2
	iaload
	invokestatic MCClass/isPrime(I)Z
	ifeq Label8
	aload_0
	iload_2
	iaload
	invokestatic io/putIntLn(I)V
Label8:
Label4:
	iload_2
	iconst_1
	iadd
	istore_2
	goto Label2
Label5:
Label3:
Label1:
	return
.limit stack 2
.limit locals 3
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	bipush 10
	newarray int
	astore_1
	iconst_0
	istore_2
Label2:
	iload_2
	bipush 10
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ifeq Label5
	aload_1
	iload_2
	iload_2
	iconst_2
	iadd
	iastore
Label4:
	iload_2
	iconst_1
	iadd
	istore_2
	goto Label2
Label5:
Label3:
	aload_1
	bipush 10
	invokestatic MCClass/printAllPrime([II)V
Label1:
	return
.limit stack 4
.limit locals 3
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
