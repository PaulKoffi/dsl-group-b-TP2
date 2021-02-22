/***
 * Get background Element informations
 */
import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () =>{
	return "BackgroundElement";
}

export const getDetails =() =>{
	return "You can use those elements to create back elements like videos , or textClips";
}


export const getBackElementData =() =>{
	return 50;
}

export const completionKind = () =>{
	return CompletionItemKind.Interface;
}

export const getLabel = () =>{
	return "backgroundElelemt";
}