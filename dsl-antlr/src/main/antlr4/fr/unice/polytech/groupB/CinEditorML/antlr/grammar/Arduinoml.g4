grammar Arduinoml;


/******************
 ** Parser rules **
 ******************/

root            : sequences export;

sequences       : sequence+;

sequence        : (textClip| video | image);
textClip        : 'textClip' value=IDENTIFIER 'lasting' time=TIME;
video           : 'video named' value=FILE_NAME ;
specificVideo           : 'video named' value=FILE_NAME 'from' start=TIMELINE 'to' end=TIMELINE;
image           : 'image' value= FILE_NAME 'lasting' time=TIME;

export          : 'export' name=FILE_NAME;
//
//tonality        :   'tonality' value=IDENTIFIER;
//
//interrupt        :   'interrupt' value=IDENTIFIER;
//
//declaration     :   'export' name=APPLLICATION;
//
//bricks          :   (sensor|actuator)+;
//    sensor      :    'sensor'   location ;
//    actuator    :   'actuator'  location ;
//    location    :   id=DEFINITION 'pin' port=PORT_NUMBER;
//
//states          :   state+;
//    state       :   ('tune' tune=IDENTIFIER)? 'state'  name=DEFINITION  'means' (action (operator= OPERATOR)?)+  ;
//    action      :   receiver=IDENTIFIER 'becomes' value=SIGNAL;
//
//
//initial :  'initial' starting=IDENTIFIER;
//
//
//
//transitions     :   (transition|temporal)+;
//    temporal    :   ('from'|'fromC')  begin=IDENTIFIER 'temporalTo' end=IDENTIFIER 'after' time=TIME 'ms';
//    transition  :   ('from'|'fromC') begin=IDENTIFIER 'to' end=IDENTIFIER  combinationAction (',' combination=OPERATOR combinationAction )?;
//    combinationAction:  'when' source=IDENTIFIER 'becomes' value=SIGNAL;
//



/*****************
 ** Lexer rules **
 *****************/
OPERATOR        :   'and' | 'or' ;
SIGNAL          :   'high' | 'low' ;
TIMELINE        :   NUMBER NUMBER? ':'NUMBER NUMBER ;
PORT_NUMBER     :   [1-9] | '10' |'11' | '12'| '13';
TIME          :   [1-9] [0-9]+;
DEFINITION      :   '"' LOWERCASE (LOWERCASE|UPPERCASE)+ NUMBER? '"';
FILE_NAME      :   LOWERCASE (LOWERCASE|UPPERCASE)+ '.' LOWERCASE (LOWERCASE|UPPERCASE)+;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+ NUMBER?;

APPLLICATION      : '"' (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|' ')+ '"';




/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
fragment NUMBER     : [0-9]+;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
