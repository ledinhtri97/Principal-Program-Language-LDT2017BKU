.source MCClass.java
.class public MCClass
.super java.lang.Object
.field static arr [F

.method public static main([Ljava/lang/String;)V
.var 0 is args [Ljava/lang/String; from Label0 to Label1
Label0:
	getstatic MCClass.arr [F
	iconst_1
	fconst_1
	fastore
	getstatic MCClass.arr [F
	iconst_0
	fconst_2
	fastore
	getstatic MCClass.arr [F
	iconst_0
	faload
	invokestatic io/putFloat(F)V
	getstatic MCClass.arr [F
	iconst_1
	faload
	invokestatic io/putFloat(F)V
Label1:
	return
.limit stack 3
.limit locals 1
.end method

.method public <init>()V
	iconst_2
	newarray float
	putstatic MCClass.arr [F
.var 0 is this LMCClass; from Label0 to Label1
Label0:
	aload_0
	invokespecial java/lang/Object/<init>()V
Label1:
	return
.limit stack 1
.limit locals 1
.end method
