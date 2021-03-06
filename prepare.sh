#!/bin/bash

# command to install vsce to package extensions (npm needed)
# npm install -g vsce

# command to package an extension (at the root directory of the extension)
# vsce package

# prepare the project
echo "---------------------------------------------------------------------------------------"
echo "PROJET EN COURS DE PREPARATION"
echo "---------------------------------------------------------------------------------------"
mvn clean install
cd dsl-antlr
mvn clean compile assembly:single
cd ..

# install video generation extension
echo "---------------------------------------------------------------------------------------"
echo "INSTALLATION DES EXTENSIONS"
echo "---------------------------------------------------------------------------------------"
echo ">>> Génération de vidéo"
cd extensions/packages
code --install-extension cinvideoconstructor-0.0.1.vsix
echo ">>> Auto-complétion et coloration syntaxique"
cd ../cineditor-lsp
npm install
cd ../..

# install python requirements
echo "---------------------------------------------------------------------------------------"
echo "INSTALLATION DES LIBRAIRIES PYTHON REQUISES"
echo "---------------------------------------------------------------------------------------"
pip install -r requirements.txt

echo "---------------------------------------------------------------------------------------"
echo "PEPARATION TERMINEE"
echo "---------------------------------------------------------------------------------------"
read -n 1 -s -r -p "APPUYEZ SUR N'IMPORTE QUELLE TOUCHE POUR QUITTER..."