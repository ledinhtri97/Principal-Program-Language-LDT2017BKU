/**
 * Student name: Le Dinh Tri
 * Student ID: 1513656
 */
grammar MC;

@lexer::header{
    package mc.parser;
}

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
    declaration+ EOF 
;

array_point_type: 
    INPUT_PARAMETER | OUTPUT_PARAMETER | TYPE_ARRAY
;

element_of_array: 
    ID LSB INTLIT RSB 
;

declaration: 
    variable_decl | function_decl 
;

//main_decl: VOIDTYPE MAIN LB RB block_stmt ;

variable_decl: 
    PRIMITIVE_TYPE many_variable SEMI 
;
  
many_variable: 
    variable (COMMA variable)* 
;

variable: 
    element_of_array | ID
;

function_decl:  
    type ID LB parameter_list? RB block_stmt 
|   block_stmt
;

parameter_list: 
    parameter_decl (COMMA parameter_decl)*
;

parameter_decl: 
    PRIMITIVE_TYPE ID (LSB RSB)? 
;

type: 
    PRIMITIVE_TYPE | VOIDTYPE | array_point_type 
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

//some

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
        ELSE 
            statement 
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
    RETURN operands SEMI 
;

expression_stmt: 
    expression (SEMI expression)* SEMI
;

expression: 
    LB expression RB
|   none_assoc_squarebracket_expression
|   <assoc=right> (SUBOP | NOTOP) expression
|   assoc_int_expression
|   none_assoc_bool_expression
|   <assoc=left> expression ANDOP expression
|   <assoc=left> expression OROP expression
|   <assoc=right> expression ASSIGN expression
|   operands
; 

none_assoc_squarebracket_expression: 
    (LSB | RSB) subsquarebracket_expression 
;

subsquarebracket_expression:
    LB subsquarebracket_expression RB
|   <assoc=right> (SUBOP | NOTOP) subsquarebracket_expression
|   <assoc=left> subsquarebracket_expression (MULOP | DIVOP | MODOP) subsquarebracket_expression
|   <assoc=left> subsquarebracket_expression (ADDOP | SUBOP) subsquarebracket_expression
|   none_assoc_bool_expression
|   <assoc=left> subsquarebracket_expression ANDOP subsquarebracket_expression
|   <assoc=left> subsquarebracket_expression OROP subsquarebracket_expression
|   <assoc=right> subsquarebracket_expression ASSIGN subsquarebracket_expression
|   operands
;

none_assoc_bool_expression: 
    assoc_int_expression (LTOP | LTOEQOP | GTOP | GTOEQOP) assoc_int_expression
|   assoc_int_expression (EQOP | NOTEQOP) assoc_int_expression
;

assoc_int_expression:
    LB assoc_int_expression RB
|   <assoc=right> (SUBOP | NOTOP) assoc_int_expression
|   <assoc=left> assoc_int_expression (MULOP | DIVOP | MODOP) assoc_int_expression
|   <assoc=left> assoc_int_expression (ADDOP | SUBOP) assoc_int_expression
|   operands
;

array_expression: 
    array_point_type | element_of_array | function_call
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
    literals | function_call | ID | element_of_array
;

literals: 
    INTLIT | FLOATLIT | BOOLLIT | STRINGLIT 
;

function_call: 
    ID LB list_expression RB
;


PRIMITIVE_TYPE: 
    INTTYPE | BOOLTYPE | FLOATTYPE | STRINGTYPE 
;

INPUT_PARAMETER: 
    PRIMITIVE_TYPE ID LSB RSB 
;

OUTPUT_PARAMETER: 
    PRIMITIVE_TYPE LSB RSB 
;

TYPE_ARRAY: 
    PRIMITIVE_TYPE LSB INTLIT RSB
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
TRUE: 'true';
FALSE: 'false';

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

//Comments

LINE_COMMENT: '//' ~[\r\n]* -> skip;

TRADITIONAL_COMMENT:  '/*'.*?'*/' -> skip ;

// skip spaces, tabs, newlines
WS : [ \t\r\n]+ -> skip ; 

//Indentifiers
ID: [a-zA-Z_]+[a-zA-Z_0-9]*;

//Literals
INTLIT: SUBOP?[0-9]+ ;

FLOATLIT: (WNP+'.'?FP* | WNP*'.'?FP+)EX? ;  

fragment WNP: SUBOP?[0-9] ;      //whole-number part
fragment FP: [0-9] ;             //fraction part
fragment EX: [eE]SUBOP?[0-9]+;   //exponent

BOOLLIT: TRUE | FALSE;

STRINGLIT: '"' ( '\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"] )* '"'{
    String str = getText();
    str = str.substring(1, str.length() - 1);
    setText(str);
};

//==============lexerphase1==================

ERROR_CHAR: . | FLOATLIT'.' ;

UNCLOSE_STRING: '"' ( ('\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"]) | '\n' )*;//'"' ~["\r\n]* (~'"' | '\n');

ILLEGAL_ESCAPE: '"' ( '\\' [btnfr"'\\] | ~[\b\t\f\r\n\\"] )* '\\' ~[btnfr"'\\];


// missing something antlr4 will catch error at sysbol next of sysbol we expected
// extraneous input just notice us to the expected it not return an error output
// mismatched input 'x' expecting {'y',...} will return an error at x;


//|   <assoc=right> (SUBOP | NOTOP) expression
//|   <assoc=left> expression (MULOP | DIVOP | MODOP) expression
//|   <assoc=left> expression (ADDOP | SUBOP) expression
//|   none_assoc_bool_expression
//|   <assoc=left> expression ANDOP expression
//|   <assoc=left> expression OROP expression
//|   <assoc=right> expression ASSIGN expression
//|   operands
//; 

//none_assoc_SB_expression: 
//subSB_expression (LSB | RSB)
//;
//
//subSB_expression:
//LB subSB_expression RB
//|   <assoc=right> (SUBOP | NOTOP) subSB_expression
//|   <assoc=left> subSB_expression (MULOP | DIVOP | MODOP) subSB_expression
//|   <assoc=left> subSB_expression (ADDOP | SUBOP) subSB_expression
//|   none_assoc_bool_expression
//|   <assoc=left> subSB_expression ANDOP subSB_expression
//|   <assoc=left> subSB_expression OROP subSB_expression
//|   <assoc=right> subSB_expression ASSIGN subSB_expression
//|   operands
//;
//
//none_assoc_bool_expression: 
//assoc_int_expression (LTOP | LTOEQOP | GTOP | GTOEQOP) assoc_int_expression
//|   assoc_int_expression (EQOP | NOTEQOP) assoc_int_expression
//;
//
//assoc_int_expression:
//LB assoc_int_expression RB
//|   none_assoc_SB_expression
//|   <assoc=right> (SUBOP | NOTOP) assoc_int_expression
//|   <assoc=left> assoc_int_expression (MULOP | DIVOP | MODOP) assoc_int_expression
//|   <assoc=left> assoc_int_expression (ADDOP | SUBOP) assoc_int_expression
//|   operands
//;