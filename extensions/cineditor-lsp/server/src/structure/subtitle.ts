import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () => {
	return "subtitle (FrontElement)";
}

export const getDetails =() => {
	return "You can create a textclip using a text \nRplace \" YourText \" by the actual text you want to show. \nRplace \" 10 \"  by the time you want this text to last on the screen"
	+  "\nReplace 'elementName' by a video you have created before.";
}


export const completionKind = () => {
	return CompletionItemKind.Method;
}

export const getLabel = () => {
	return "subtitle with text \" YourText \" starting at 00:30 of elementName during 10 s ";
}

