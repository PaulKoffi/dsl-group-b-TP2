grammar Arduinoml;


/******************
 ** Parser rules **
 ******************/

root            : sequences export;

sequences       : sequence+;

sequence        : (textClip| video | image| specificVideo);
textClip        : 'textClip' name=IDENTIFIER text=IDENTIFIER 'lasting' time=TIME 's'?;
video           : 'video named' name=IDENTIFIER path=FILE_NAME ;
specificVideo   : 'video named' name=IDENTIFIER path=FILE_NAME 'from' start=TIMELINE 'to' end=TIMELINE;
image           : 'image' name=IDENTIFIER path= FILE_NAME 'lasting' time=TIME 's';

export          : 'export' name=IDENTIFIER;
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

TIMELINE        :   NUMBER NUMBER? ':'NUMBER NUMBER ;
TIME            :   NUMBER+;
//IDENTIFIER      :     LOWERCASE (LOWERCASE|UPPERCASE)+ NUMBER? FILE_EXTENSION?;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+ NUMBER?;
FILE_NAME       :   (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|NUMBER)+ NUMBER? '.' (LOWERCASE|UPPERCASE|NUMBER)+;

FILE_EXTENSION  : '.mp4';


//DEFINITION      :   '"' LOWERCASE (LOWERCASE|UPPERCASE)+ NUMBER? '"';



//APPLLICATION      : '"' (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|' ')+ '"';




/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
fragment NUMBER     : [0-9]+;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
