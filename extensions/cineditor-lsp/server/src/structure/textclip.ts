import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () =>{
	return "textclip (BackgroundElement)";
}

export const getDetails =() =>{
	return "You can create a video using a text \nRplace \" YourText \" by the actual text you want to show. \nRplace \" 10 \"  by the time you want this text to last on the screen";
}


export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "textclip with text \" YourText \" during 10 s ";
}


