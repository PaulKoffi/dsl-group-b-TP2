grammar CinEditor;


/******************
 ** Parser rules **
 ******************/

root            : sequences createVideo export ;

sequences       : sequence+;

createVideo     : 'createVideo' '(' list=TEXT ')';

sequence        : (backgroundElement |frontElement |action);

backgroundElement : (textClip| video |specificPartOfVideo);
textClip        : name=IDENTIFIER '= textClip('text=IDENTIFIER ','time=TIME')' /*time=TIME 's'?*/;
video           : name=IDENTIFIER '= video(' path=FILE_NAME ')' ;
specificPartOfVideo   : name=IDENTIFIER '= video(' path=FILE_NAME ').from(' start=TIMELINE ').to(' end=TIMELINE')';

frontElement    : (subtitle| audio | specificPartOfAudio);
audio           : name=IDENTIFIER '= audio(' path=FILE_NAME ')' ;
specificPartOfAudio   : name=IDENTIFIER '= audio(' path=FILE_NAME ').from(' start=TIMELINE ').to(' end=TIMELINE')';





subtitle        : name=IDENTIFIER '= subtitle(' element=IDENTIFIER ('.afterBegining'|'.beforeBegining'|'.afterEnding'|'.beforeEnding');


action          : cutVideo;
cutVideo        : name=IDENTIFIER '=' source=IDENTIFIER '.from('start=TIMELINE').to('end=TIMELINE')';
//chainage
//Plusieurs videos extraites de la meme vido ?

//setTextClipTime : name=IDENTIFIER '.setTime(' time=TIME ')';

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

LINE_TERMINAISON:  ';';
TIMELINE        :   NUMBER NUMBER? ':'NUMBER NUMBER ;
TIME            :   NUMBER+;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+ ;
TEXT            : (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|','|' '|NUMBER)+;
FILE_NAME       :   (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|NUMBER)+ NUMBER? '.' (LOWERCASE|UPPERCASE|NUMBER)+;

FILE_EXTENSION  : '.mp4';


//DEFINITION      :   '"' LOWERCASE (LOWERCASE|UPPERCASE)+ NUMBER? '"';








/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
fragment NUMBER     : [0-9]+;
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
