.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static arr [I

.method public static init()[I
Label0:
	iconst_0
	istore_0
Label2:
	iload_0
	iconst_5
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ifeq Label5
	getstatic MCClass.arr [I
	iload_0
	iload_0
	iconst_1
	iadd
	iastore
Label4:
	iload_0
	iconst_1
	iadd
	istore_0
	goto Label2
Label5:
Label3:
	getstatic MCClass.arr [I
	goto Label1
Label1:
	areturn
.limit stack 4
.limit locals 1
.end method

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	iconst_5
	newarray int
	astore_1
	invokestatic MCClass/init()[I
	astore_1
	iconst_0
	istore_2
Label2:
	iload_2
	iconst_5
	if_icmpge Label6
	iconst_1
	goto Label7
Label6:
	iconst_0
Label7:
	ifeq Label5
	aload_1
	iload_2
	iaload
	invokestatic io/putIntLn(I)V
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

.method public <init>()V
	iconst_5
	newarray int
	putstatic MCClass.arr [I
.var 0 is this LMCClass; from Label0 to Label1
Label0:
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return
.limit stack 1
.limit locals 1
.end method
