grammar CinEditor;


/******************
 ** Parser rules **
 ******************/

root            : sequences createVideo export ;

sequences       : sequence+;

createVideo     : 'createVideo' 'with' list=FINAL_VIDEO;

sequence        : (backgroundElement |frontElement |action);

backgroundElement : (textClip| video |specificPartOfVideo);
textClip        : name=IDENTIFIER '=''textClip' 'with' 'text' text=TEXT 'during' /*(minutes=TIMELINE 'min')?*/  time=TIMELINE 's'?
                                                ('with''animation' animation=ANIMATION ('speed' speed=FLOAT)?)? ;
specificPartOfVideo   : name=IDENTIFIER '=' 'video' 'from'path=FILE_NAME 'starting''at' start=TIMELINE 'ending' 'at'end=TIMELINE;
video           : name=IDENTIFIER '=''video'  path=FILE_NAME ;



action          : cutVideo;
cutVideo        : name=IDENTIFIER '=' source=IDENTIFIER 'from' start=TIMELINE 'to' end=TIMELINE ;

frontElement    : (subtitle| audio /*| specificPartOfAudio*/);
audio           : name=IDENTIFIER '=' 'audio' path=FILE_NAME 'starting' 'at' time=TIMELINE 's'?  (position=POSITION)? ('of' element=IDENTIFIER)? /*('videoVolume' backGroundSound=FLOAT )?*/ ( 'volume' audioSound=FLOAT )?;
//specificPartOfAudio   : name=IDENTIFIER '=' 'audio(' path=FILE_NAME ')''->''from(' start=TIMELINE ')' '->''to(' end=TIMELINE')';
subtitle        : name=IDENTIFIER '=' 'subtitle' 'with''text' value =TEXT 'starting' 'at' time=TIMELINE 's'?  position=POSITION   'of' element=IDENTIFIER  'during' duration=TIMELINE 's'?;

export          : 'video' 'title' '=' name=IDENTIFIER;






//setAudio        :
//chainage
//Plusieurs videos extraites de la meme vido ?

//setTextClipTime : name=IDENTIFIER '.setTime(' time=TIME ')';



/*****************
 ** Lexer rules **
 *****************/
POSITION        :   ('afterBeginning'|'beforeBeginning'|'afterEnding'|'beforeEnding');
ANIMATION       : ('vortex'|'cascade'| 'arrive' |'vortexout' | 'scroll');
//TIME          :   NUMBER+;

TIMELINE        :   NUMBER+  (':'  NUMBER NUMBER)?;
FLOAT           :   NUMBER+ (('.'|',') NUMBER+)? 'f';
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
