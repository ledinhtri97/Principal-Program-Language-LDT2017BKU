/**
 * Student name: Le Dinh Tri
 * Student ID: 1513656
 */
grammar MC;
@lexer::header{
    package mc.parser;
}
//ass 3 làm static check
//ass 4 làm code gen
@lexer::members{
@Override
public Token emit() {
    String s;
    switch (getType()) {
    case UNCLOSE_STRING:       
        Token result = super.emit();
        s = result.getText();
        int indexOfNewLine = s.indexOf("\n");
        String unclString;
        if(indexOfNewLine==-1)
          unclString = s.substring(1);
        else
          unclString = s.substring(1, indexOfNewLine);
        throw new UncloseString(unclString);
        
    case ILLEGAL_ESCAPE:
        result = super.emit();
        s = result.getText();
        String illegaEscape = s.substring(1);
        throw new IllegalEscape(illegaEscape);
    case ERROR_CHAR:
        result = super.emit();
        s = result.getText();
        String errChar = s.substring(s.length() - 1);
        throw new ErrorToken(errChar); 
    default:
        return super.emit();
    }
}
}
@parser::header{
    package mc.parser;
}
options{
    language=Java;
}

program: 
    declaration* EOF 
;
array_point_type: 
    input_parameter | output_parameter
;
element_of_array: 
    ID LSB (INTLIT|ID) RSB 
|   array_point_type
;
input_parameter: 
    primitive_type ID LSB RSB 
;
output_parameter: 
    primitive_type LSB RSB 
;
array_type: 
    primitive_type LSB INTLIT RSB
;
declaration: 
    variable_decl | function_decl 
;
//main_decl: VOIDTYPE MAIN LB RB block_stmt ;
variable_decl: 
    primitive_type many_variable SEMI 
;
  
many_variable: 
    variable (COMMA variable)* 
;
variable: 
    ID LSB INTLIT RSB | ID
;
function_decl:  
    (typeMC|output_parameter) ID LB parameter_list? RB block_stmt 
;
parameter_list: 
    parameter_decl (COMMA parameter_decl)*
;
parameter_decl: 
    primitive_type ID 
|   primitive_type ID LSB RSB 
;
typeMC: 
    primitive_type | VOIDTYPE
;
statement: 
    if_stmt 
|   dowhile_stmt 
|   for_stmt 
|   break_stmt 
|   continue_stmt
|   return_stmt
|   expression_stmt
|   block_stmt
|   index_expression SEMI
;
  
declaration_part: 
    variable_decl* 
;
statement_part: 
    statement*
;
if_stmt: 
        IF 
          LB 
            expression 
          RB 
            statement 
        (
            ELSE 
                statement 
        )?
; 
block_stmt: 
          LP 
            declaration_part 
            statement_part 
          RP 
;
dowhile_stmt: 
          DO 
            statement_part 
          WHILE 
            expression 
          SEMI 
;
for_stmt: 
        FOR 
          LB 
            expression SEMI 
            expression SEMI 
            expression 
          RB 
            statement 
;
break_stmt: 
    BREAK SEMI 
;
continue_stmt: 
    CONTINUE SEMI 
;
return_stmt: 
    RETURN expression? SEMI 
;
expression_stmt: 
    expression SEMI
;
expression: 
    LB expression RB 
|   expression LSB expression RSB 
|   expression_SB (LSB | RSB)  
|   assoc_expression
|   relational_expression (LTOP | LTOEQOP | GTOP | GTOEQOP) relational_expression
|   equality_expression (EQOP | NOTEQOP) equality_expression
|   <assoc=left> expression ANDOP expression
|   <assoc=left> expression OROP expression
|   <assoc=right> expression ASSIGN expression
|   operands
;
assoc_expression:
    LB expression RB
|   assoc_expression LSB assoc_expression RSB
|   <assoc=right> (SUBOP | NOTOP) assoc_expression
|   <assoc=left> assoc_expression (MULOP | DIVOP | MODOP) assoc_expression
|   <assoc=left> assoc_expression (ADDOP | SUBOP) assoc_expression
|   operands
;
expression_SB:
    LB expression RB
|   expression_SB LSB expression_SB RSB
|   <assoc=right> (SUBOP | NOTOP) expression_SB
|   <assoc=left> expression_SB (MULOP | DIVOP | MODOP) expression_SB
|   <assoc=left> expression_SB (ADDOP | SUBOP) expression_SB
|   relational_expression (LTOP | LTOEQOP | GTOP | GTOEQOP) relational_expression
|   equality_expression (EQOP | NOTEQOP) equality_expression
|   <assoc=left> expression_SB ANDOP expression_SB
|   <assoc=left> expression_SB OROP expression_SB
|   <assoc=right> expression_SB ASSIGN expression_SB
|   operands
;
relational_expression:
    LB expression RB
|   relational_expression LSB relational_expression RSB
|   <assoc=right> (SUBOP | NOTOP) relational_expression
|   <assoc=left> relational_expression (MULOP | DIVOP | MODOP) relational_expression
|   <assoc=left> relational_expression (ADDOP | SUBOP) relational_expression
|   operands 
;
equality_expression:
    LB expression RB
|   equality_expression LSB equality_expression RSB
|   <assoc=right> (SUBOP | NOTOP) equality_expression
|   <assoc=left> equality_expression (MULOP | DIVOP | MODOP) equality_expression
|   <assoc=left> equality_expression (ADDOP | SUBOP) equality_expression
|   relational_expression (LTOP | LTOEQOP | GTOP | GTOEQOP) relational_expression
|   operands
;
index_expression: 
    expression LSB expression RSB         
;
invocation_expression: 
    function_call
;
list_expression: 
    (expression (COMMA expression)*)?
;
operands: 
    function_call | ID | literals | element_of_array
;
literals: 
    INTLIT | FLOATLIT | BOOLLIT | STRINGLIT 
;
function_call: 
    ID LB list_expression RB
;
primitive_type: 
    INTTYPE | BOOLTYPE | FLOATTYPE | STRINGTYPE 
;

//===============================================================//
VOIDTYPE: 'void';
INTTYPE: 'int';
FLOATTYPE: 'float';
BOOLTYPE: 'boolean';
STRINGTYPE: 'string';
BREAK: 'break';
CONTINUE: 'continue';
ELSE: 'else';
FOR: 'for';
IF: 'if';
RETURN: 'return';
DO: 'do';
WHILE: 'while';
//Operators
ADDOP: '+';
SUBOP: '-';
DIVOP: '/';
MULOP: '*';
MODOP: '%';
ANDOP: '&&';
NOTOP: '!';
OROP: '||';
EQOP: '==';
NOTEQOP: '!=';
LTOP: '<';
LTOEQOP:'<=';
GTOP: '>';
GTOEQOP: '>=';
ASSIGN: '=';
//Separators
LSB: '[';
RSB: ']';
LP: '{';
RP: '}';
LB: '(';
RB: ')';
SEMI: ';';
COMMA: ',';
BOOLLIT: 'true' | 'false';

//Comments
LINE_COMMENT: '//' ~[\r\n]* -> skip;
TRADITIONAL_COMMENT:  '/*'.*?'*/' -> skip ;
// skip spaces, tabs, newlines
WS : [ \t\r\n]+ -> skip ; 

//Indentifiers
ID: [a-zA-Z_]+[a-zA-Z_0-9]*;
//Literals
INTLIT: [0-9]+ ;
FLOATLIT: (WNP+'.'?FP* | WNP*'.'?FP+)EX? ;  
fragment WNP: [0-9] ;      //whole-number part
fragment FP: [0-9] ;             //fraction part
fragment EX: [eE](SUBOP|ADDOP)?[0-9]+;   //exponent
STRINGLIT: '"' ( '\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"] )* '"'{
    String str = getText();
    str = str.substring(1, str.length() - 1);
    setText(str);
};
//==============lexerphase1==================
ERROR_CHAR: . | FLOATLIT'.' ;
UNCLOSE_STRING: '"' ( ('\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"]) | '\n' )*;//'"' ~["\r\n]* (~'"' | '\n');
ILLEGAL_ESCAPE: '"' ( '\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"] )* '\\' ~[btnfr"'\\];
