/* --------------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 * ------------------------------------------------------------------------------------------ */
import {
	createConnection,
	TextDocuments,
	Diagnostic,
	DiagnosticSeverity,
	ProposedFeatures,
	InitializeParams,
	DidChangeConfigurationNotification,
	CompletionItem,
	CompletionItemTag,
	CompletionItemKind,
	TextDocumentPositionParams,
	TextDocumentSyncKind,
	InitializeResult,
	Command
} from 'vscode-languageserver/node';

import * as video from "./structure/video";
import * as textclip from "./structure/textclip";
import * as specificPartOfVideo from "./structure/specificPartOfVideo";
import * as subtitle from "./structure/subtitle";
import * as cuttingVideo from "./actions/cutVideo";
import * as createVideo from "./actions/createVideo";
import * as videoTitle from "./actions/videoTitle";
import * as subtitleRelativeToElement from "./structure/subtitleRelativeToElement";
import * as audio from "./structure/audio";

import {
	TextDocument
} from 'vscode-languageserver-textdocument';

// Create a connection for the server, using Node's IPC as a transport.
// Also include all preview / proposed LSP features.
let connection = createConnection(ProposedFeatures.all);

// Create a simple text document manager.
let documents: TextDocuments<TextDocument> = new TextDocuments(TextDocument);

let hasConfigurationCapability: boolean = false;
let hasWorkspaceFolderCapability: boolean = false;
let hasDiagnosticRelatedInformationCapability: boolean = false;

connection.onInitialize((params: InitializeParams) => {
	let capabilities = params.capabilities;

	// Does the client support the `workspace/configuration` request?
	// If not, we fall back using global settings.
	hasConfigurationCapability = !!(
		capabilities.workspace && !!capabilities.workspace.configuration
	);
	hasWorkspaceFolderCapability = !!(
		capabilities.workspace && !!capabilities.workspace.workspaceFolders
	);
	hasDiagnosticRelatedInformationCapability = !!(
		capabilities.textDocument &&
		capabilities.textDocument.publishDiagnostics &&
		capabilities.textDocument.publishDiagnostics.relatedInformation
	);

	const result: InitializeResult = {
		capabilities: {
			textDocumentSync: TextDocumentSyncKind.Incremental,
			// Tell the client that this server supports code completion.
			completionProvider: {
				resolveProvider: true
			}
		}
	};
	if (hasWorkspaceFolderCapability) {
		result.capabilities.workspace = {
			workspaceFolders: {
				supported: true
			}
		};
	}
	return result;
});

connection.onInitialized(() => {
	if (hasConfigurationCapability) {
		// Register for all configuration changes.
		connection.client.register(DidChangeConfigurationNotification.type, undefined);
	}
	if (hasWorkspaceFolderCapability) {
		connection.workspace.onDidChangeWorkspaceFolders(_event => {
			connection.console.log('Workspace folder change event received.');
		});
	}
});

// The example settings
interface ExampleSettings {
	maxNumberOfProblems: number;
}

// The global settings, used when the `workspace/configuration` request is not supported by the client.
// Please note that this is not the case when using this server with the client provided in this example
// but could happen with other clients.
const defaultSettings: ExampleSettings = { maxNumberOfProblems: 1000 };
let globalSettings: ExampleSettings = defaultSettings;

// Cache the settings of all open documents
let documentSettings: Map<string, Thenable<ExampleSettings>> = new Map();

connection.onDidChangeConfiguration(change => {
	if (hasConfigurationCapability) {
		// Reset all cached document settings
		documentSettings.clear();
	} else {
		globalSettings = <ExampleSettings>(
			(change.settings.languageServerExample || defaultSettings)
		);
	}

	// Revalidate all open text documents
	documents.all().forEach(validateTextDocument);
});

function getDocumentSettings(resource: string): Thenable<ExampleSettings> {
	if (!hasConfigurationCapability) {
		return Promise.resolve(globalSettings);
	}
	let result = documentSettings.get(resource);
	if (!result) {
		result = connection.workspace.getConfiguration({
			scopeUri: resource,
			section: 'languageServerExample'
		});
		documentSettings.set(resource, result);
	}
	return result;
}

// Only keep settings for open documents
documents.onDidClose(e => {
	documentSettings.delete(e.document.uri);
});

// The content of a text document has changed. This event is emitted
// when the text document first opened or when its content has changed.
documents.onDidChangeContent(change => {
	validateTextDocument(change.document);
});

async function validateTextDocument(textDocument: TextDocument): Promise<void> {
	// In this simple example we get the settings for every validate run.
	let settings = await getDocumentSettings(textDocument.uri);

	// The validator creates diagnostics for all uppercase words length 2 and more
	let text = textDocument.getText();
	let pattern = /\b[A-Z]{2,}\b/g;
	let m: RegExpExecArray | null;

	let problems = 0;
	let diagnostics: Diagnostic[] = [];
	while ((m = pattern.exec(text)) && problems < settings.maxNumberOfProblems) {
		problems++;
		let diagnostic: Diagnostic = {
			severity: DiagnosticSeverity.Warning,
			range: {
				start: textDocument.positionAt(m.index),
				end: textDocument.positionAt(m.index + m[0].length)
			},
			message: `${m[0]} is all uppercase.`,
			source: 'ex'
		};
		if (hasDiagnosticRelatedInformationCapability) {
			diagnostic.relatedInformation = [
				{
					location: {
						uri: textDocument.uri,
						range: Object.assign({}, diagnostic.range)
					},
					message: 'Spelling matters'
				},
				{
					location: {
						uri: textDocument.uri,
						range: Object.assign({}, diagnostic.range)
					},
					message: 'Particularly for names'
				}
			];
		}
		diagnostics.push(diagnostic);
	}

	// Send the computed diagnostics to VSCode.
	connection.sendDiagnostics({ uri: textDocument.uri, diagnostics });
}

connection.onDidChangeWatchedFiles(_change => {
	// Monitored files have change in VSCode
	connection.console.log('We received an file change event');
});

// This handler provides the initial list of the completion items.
connection.onCompletion(
	(_textDocumentPosition: TextDocumentPositionParams): CompletionItem[] => {
		// The pass parameter contains the position of the text document in
		// which code complete got requested. For the example we ignore this
		// info and always provide the same completion items.
		return [
			// {
			// 	label: backgroundElement.getLabel(),
			// 	kind: backgroundElement.completionKind(),
			// },
			{
				label: createVideo.getLabel(),
				kind: createVideo.completionKind(),
				detail: createVideo.getName(),
				documentation: createVideo.getDetails()
			},
			{
				label: videoTitle.getLabel(),
				kind: videoTitle.completionKind(),
				detail: videoTitle.getName(),
				documentation: videoTitle.getDetails()
			},
			{
				label: video.getLabel(),
				kind: video.completionKind(),
				detail: video.getName(),
				documentation: video.getDetails()
			},
			{
				label: textclip.getLabel(),
				kind: textclip.completionKind(),
				detail: textclip.getName(),
				documentation: textclip.getDetails() 
			},
			{
				label: specificPartOfVideo.getLabel(),
				kind: specificPartOfVideo.completionKind(),
				detail: specificPartOfVideo.getName(),
				documentation: specificPartOfVideo.getDetails()
			},
			{
				label: cuttingVideo.getLabel(),
				kind: cuttingVideo.completionKind(),
				detail: cuttingVideo.getName(),
				documentation: cuttingVideo.getDetails()
			},
			{
				label: subtitle.getLabel(),
				kind: subtitle.completionKind(),
				detail: subtitle.getName(),
				documentation: subtitle.getDetails()
			},
			{
				label: subtitleRelativeToElement.getLabel(),
				kind: subtitleRelativeToElement.completionKind(),
				detail: subtitleRelativeToElement.getName(),
				documentation: subtitleRelativeToElement.getDetails()
			},
			{
				label: audio.getLabel(),
				kind: audio.completionKind(),
				detail: audio.getName(),
				documentation: audio.getDetails()
			},
			{
				label: audio.getLabelWithVolume(),
				kind: audio.completionKind(),
				detail: audio.getName(),
				documentation: audio.getDetailsWithVolume()
			},
			{
				label: audio.getLabelForAudioWithRelative(),
				kind: audio.completionKind(),
				detail: audio.getName(),
				documentation: audio.getDetailsForAudioWithRelative()
			},
			{
				label: audio.getLabelForAudioWithRelativeWithVolume(),
				kind: audio.completionKind(),
				detail: audio.getName(),
				documentation: audio.getDetailsForAudioWithRelativeWithVolume()
			},
			{
				label: "afterBegining",
				kind: CompletionItemKind.Enum,
				detail: "Position"
			},
			
			{
				label: "afterEnding",
				kind: CompletionItemKind.Enum,
				detail: "Position"
			},
			{
				label: "beforeBeginning",
				kind: CompletionItemKind.Enum,
				detail: "Position"
			},
			{
				label: "beforeEnding",
				kind: CompletionItemKind.Enum,
				detail: "Position"
			},
			{
				label: "with",
				kind: CompletionItemKind.Keyword,
				detail: "Keyword"
			},
			{
				label: "starting",
				kind: CompletionItemKind.Keyword,
				detail: "Keyword"
			},
			{
				label: "text",
				kind: CompletionItemKind.Keyword,
				detail: "Keyword for textual values"
			},
			{
				label: "at",
				kind: CompletionItemKind.Keyword,
				detail: "Keyword for time values"
			}

			
		];
	} 
);


// This handler resolves additional information for the item selected in
// the completion list.
connection.onCompletionResolve(
	(item: CompletionItem): CompletionItem => {
		
		return item;
	}
);

// Make the text document manager listen on the connection
// for open, change and close text document events
documents.listen(connection);
// Listen on the connection
connection.listen();
