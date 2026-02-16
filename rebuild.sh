#!/bin/bash
# Script to rebuild project with Maven using Java 22
# Run this before executing tests to ensure correct Java version
# This prevents Java version mismatch errors

echo "=========================================="
echo "Rebuilding project with Maven (Java 22)..."
echo "=========================================="

# Ensure we're using Java 22
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-22.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java:"
java -version

echo ""
echo "Cleaning and compiling..."
mvn clean compile test-compile -Dmaven.compiler.source=22 -Dmaven.compiler.target=22 -Dmaven.compiler.release=22

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build successful! All classes compiled with Java 22"
    echo "You can now run your tests."
else
    echo ""
    echo "❌ Build failed! Please check the errors above."
    exit 1
fi

