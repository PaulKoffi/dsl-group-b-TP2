import {

	CompletionItemKind,
	
} from 'vscode-languageserver/node';


export const getName = () => {
	return "audio (FrontElement)";
}

export const completionKind = () => {
	return CompletionItemKind.Method;
}

export const getDetails =() => {
	return "You can add an audio on a specific part of you video \nReplace 'audio.mp3' by your video name. \nYour video must be added in the folder script/input";
}

export const getLabel = () => {
	return "audio audio.mp3 starting at 00:30 ";
}

export const getLabelForAudioWithRelative = () => {
	return "audio audio.mp3 starting at 00:30 of elementName";
}

export const getDetailsForAudioWithRelative =() => {
	return "You can add an audio on a specific part of you video \nReplace 'audio.mp3' by your video name. \nYour video must be added in the folder script/input"
	
	+  "\nReplace 'elementName' by a video you have created before.";
}

export const getDetailsWithVolume =() => {
	return "You can add an audio on a specific part of you video \nReplace 'audio.mp3' by your video name. \nYour video must be added in the folder script/input"

	+ "\nFor molume, choose any float number between 0.0f(No sound) and 2.0f(2x the actual video sound) ";
}

export const getLabelWithVolume = () => {
	return "audio audio.mp3 starting at 00:30 volume [0.0f - 2.0f]";
}

export const getLabelForAudioWithRelativeWithVolume = () => {
	return "audio audio.mp3 starting at 00:30 of elementName volume [0.0f - 2.0f]";
}

export const getDetailsForAudioWithRelativeWithVolume=() => {
	return "You can add an audio on a specific part of you video \nReplace 'audio.mp3' by your video name. \nYour video must be added in the folder script/input"
	
	+  "\nReplace 'elementName' by a video you have created before."
	
	+ "\nFor molume, choose any float number between 0.0f(No sound) and 2.0f(2x the actual video sound) ";
}

