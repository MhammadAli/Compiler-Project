grammar Task1;

block: experssion ((openCurlybraces (statement)*)* endCurlybraces)* (block)*;


statement :State #statement_state
            |block #statement_block;


WS: [ \n\r]+ -> skip;
State: [a-zA-Z0-9];
INT : [0-9];

variable : State | INT;
openCurlybraces: '{';
endCurlybraces: '}';


operater : '==' | '>' | '<' | '>=' | '<=' | '!=';

openRoundbraces: '(';
endRoundbraces: ')';
semiColon : ';';

experssion : (State)+ (openRoundbraces ((variable operater variable)|(variable))* endRoundbraces)* (semiColon)*;
