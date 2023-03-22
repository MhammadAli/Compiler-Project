grammar Task1;

block: (lineStartAndEnd)* openCurlybraces (statement)* endCurlybraces (lineStartAndEnd)*;


statement :State #statement_state
            |block #statement_block;

lineStartAndEnd : State;

WS: [ \n\t\r]+ -> skip;
State: [a-zA-Z0-9];
openCurlybraces: '{';
endCurlybraces: '}';
