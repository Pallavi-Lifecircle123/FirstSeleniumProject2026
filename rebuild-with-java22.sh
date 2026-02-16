#!/bin/bash
# Script to rebuild project with Java 22
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-22.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java:"
java -version

echo ""
echo "Cleaning and rebuilding with Java 22..."
mvn clean compile test-compile -Dmaven.compiler.source=22 -Dmaven.compiler.target=22 -Dmaven.compiler.release=22

echo ""
echo "Verifying class file versions..."
find target -name "*.class" -exec file {} \; | grep -E "version (67|68|69)" && echo "⚠️  WARNING: Some classes are still Java 23+" || echo "✓ All classes are Java 22"
