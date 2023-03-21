grammar Task1 ;


block: openCurlybraces (statement)* endCurlybraces;


statement :State # statementState
            |block # statementBlock;

WS: [\n\t\r]+ -> skip;
State: [a-zA-Z0-9];
openCurlybraces: '{';
endCurlybraces: '}';