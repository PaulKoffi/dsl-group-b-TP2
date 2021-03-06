import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () =>{
	return "specificPartOfVideo (BackgroundElement)";
}

export const getDetails =() =>{
	return "You can cut an existing video using this object \nReplace 'source.mp4' by your video name. \nYour video must be added in the folder script/input" +
	"\n 00 (minutes):00(seconds) is the current video timeline";
}


export const getBackElementData =() =>{
	return 50;
}

export const completionKind = () =>{
	return CompletionItemKind.Method;
}

export const getLabel = () =>{
	return "video from source.mp4 starting at 00:00 ending at 00:30";
}
