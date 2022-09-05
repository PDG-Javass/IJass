#!/bin/bash
find * -iname "*.java" | xargs google-java-format -i
