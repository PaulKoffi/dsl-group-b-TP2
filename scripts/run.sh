#!/bin/bash

java -jar ../dsl-antlr/target/CinEditorML.dsl-antlr-1.0-jar-with-dependencies.jar $1 > outputs/$2.py
$SHELL