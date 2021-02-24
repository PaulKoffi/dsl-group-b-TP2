import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () =>{
	return "video (BackgroundElement)";
}

export const getDetails =() =>{
	return "You can use existing video using  this object \nReplace 'source.mp4' by your video name. \nYour video must be added in the folder script/input";
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "video from source.mp4 ";
}

