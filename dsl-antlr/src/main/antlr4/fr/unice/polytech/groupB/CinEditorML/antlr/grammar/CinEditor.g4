grammar CinEditor;


/******************
 ** Parser rules **
 ******************/

root            : sequences createVideo export ;

sequences       : sequence+;

createVideo     : 'createVideo' list=FINAL_VIDEO;

sequence        : (backgroundElement |frontElement |action);

backgroundElement : (textClip| video |specificPartOfVideo);
textClip        : name=IDENTIFIER '=''textClip('text=TEXT')''->''during('time=TIME')' /*time=TIME 's'?*/;
specificPartOfVideo   : name=IDENTIFIER '=''video('path=FILE_NAME')''->''start('start=TIMELINE')''.end('end=TIMELINE')';
video           : name=IDENTIFIER '=''video('path=FILE_NAME')' ;



action          : cutVideo;
cutVideo        : name=IDENTIFIER '=' source=IDENTIFIER '->from('start=TIMELINE')''->to('end=TIMELINE')' ;

frontElement    : (subtitle| audio | specificPartOfAudio);
audio           : name=IDENTIFIER '=''audio''('path=FILE_NAME')' ( '.' position=POSITION '(' time=TIMELINE?  ')' ('->''of(' element=IDENTIFIER ')')? )?;
specificPartOfAudio   : name=IDENTIFIER '=' 'audio(' path=FILE_NAME ')''->''from(' start=TIMELINE ')' '->''to(' end=TIMELINE')';
subtitle        : name=IDENTIFIER '=' 'subtitle(' value =TEXT')' '->'position=POSITION '(' time=TIMELINE? ')' ('->''of(' element=IDENTIFIER ')')?;








//setAudio        :
//chainage
//Plusieurs videos extraites de la meme vido ?

//setTextClipTime : name=IDENTIFIER '.setTime(' time=TIME ')';

export          : 'export' name=IDENTIFIER;

/*****************
 ** Lexer rules **
 *****************/
FILE_EXTENSION  : '.mp4';
POSITION        :   ('afterBegining'|'beforeBegining'|'afterEnding'|'beforeEnding'|'at');
TIMELINE        :   (NUMBER NUMBER? ':'NUMBER NUMBER) ;
TIME            :   NUMBER+;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+ ;
TEXT            : '"' (LOWERCASE|UPPERCASE|','|' '|NUMBER)+ '"';
FINAL_VIDEO     : '(' (LOWERCASE|UPPERCASE|','|' '|NUMBER)+ ')';

FILE_NAME       :   (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|NUMBER)+ NUMBER? '.' (LOWERCASE|UPPERCASE|NUMBER)+;




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
