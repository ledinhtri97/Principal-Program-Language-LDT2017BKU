java -jar jasmin.jar MCClass/MCClass.j
echo "================================"
echo "| 	    show             |"
echo "================================"
java -c MCClass/MCClass.class
javap -c MCClass/MCClass.class > MCClass.java
echo "================================"
echo "|             run              |"
echo "================================"
java MCClass

