import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () => {
	return "subtitleRefeeringToElement (FrontElement)";
}

export const getDetails =() => {
	return "You can create a textclip using a text \nReplace \" YourText \" by the actual text you want to show. \nReplace \" 10 \"  by the time you want this text to last on the screen"
	
	+ "\nReplace \"10\"  by the time you want this text to last on the screen"
	
	+ "\nReplace \"15\"  a relative time to an element"

	+  "\nReplace 'elementName' by a video you have created before.";
}


export const completionKind = () => {
	return CompletionItemKind.Method;
}

export const getLabel = () => {
	return "subtitle with text \" YourText \" starting at 15 s Position of ElementName during 10 s ";
}
