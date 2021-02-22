import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';



export const getName = () =>{
	return "cutting an existing video";
}

export const getDetails =() =>{
	return "You can cut a video you have created before \nReplace 'videoName' by a video you have created before.";
}


export const getBackElementData =() =>{
	return 50;
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "videoName from 00:00 ending at 00:30 ";
}

