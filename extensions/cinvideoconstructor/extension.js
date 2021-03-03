// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
const vscode = require('vscode');
const { exec } = require("child_process");
const pathToRunSh = '..\\..\\scripts';
// this method is called when your extension is activated
// your extension is activated the very first time the command is executed

/**
 * @param {vscode.ExtensionContext} context
 */
function activate(context) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	//console.log('Congratulations, your extension "cinvideoconstructor" is now active!');

	// The command has been defined in the package.json file
	// Now provide the implementation of the command with  registerCommand
	// The commandId parameter must match the command field in package.json
	let disposable = vscode.commands.registerCommand('cinvideoconstructor.generateVideo', function () {
		// The code you place here will be executed every time your command is executed
		let wp = vscode.window.activeTextEditor.document.uri.fsPath;
		let wf = vscode.window.activeTextEditor.document.uri.fsPath.split("\\") ;
		let value ='';
		for (let index = 0; index < wf.length-1; index++) {
			if (index===0) {
				value = value+wf[index] ;
			} else {
				value = value+ '\\' +wf[index] ;
			}			
		}
		// let f = vscode.workspace.workspaceFolders[0].uri.fsPath ; 
		// shell.series([
		// 	'cd '+ pathToRunSh,
		// 	'ls -l '
		// 	// 'git commit --verbose'
		// ], function(err){
		//    console.log('executed many commands in a row'); 
		// });
		cmd2 ='.\\run.sh '+ wp +' home';
		cmd = ' cd ../../scripts '   ; /*pathToRunSh*/
		var cd = exec('cd '+value+ ' & ' + cmd + ' & ' +cmd2,function(err,stdout,strerr)
		{
			console.log(strerr);
			console.log(stdout);
			vscode.window.showInformationMessage(stdout);//this would work
		});
	
		// Display a message box to the user
		vscode.window.showInformationMessage(cd);
	});

	context.subscriptions.push(disposable);
}

// this method is called when your extension is deactivated
function deactivate() {}

module.exports = {
	activate,
	deactivate
}
