import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () =>{
	return "video";
}

export const getDetails =() =>{
	return "You can use existing video using  this object \n Rplace 'source.mp4' by your video name. \n Your video must be added in the folder ";
}


export const getBackElementData =() =>{
	return 50;
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "video from source.mp4 ";
}

