import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () => {
	return "createfinal videoClip using backgroundElements";
}

export const getDetails =() => {
	return "Add into the brackets the backgroundElements you want to see in the order you want to see them";
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "createVideo with (v1 ,v2 ... ,v3) ";
}
