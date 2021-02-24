import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () => {
	return "video title";
}

export const getDetails =() => {
	return "Give a title to you final video \nReplace vidoName by the actualName you want to set ";
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "videoTitle is vidoName ";
}