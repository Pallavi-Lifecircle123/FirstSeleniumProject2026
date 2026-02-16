#!/bin/bash
# Comprehensive script to fix Java version mismatch issues
# This script ensures all classes are compiled with Java 22

echo "=========================================="
echo "Java Version Fix Script"
echo "=========================================="

# Set Java 22
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-22.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

echo "Using Java:"
java -version
echo ""

# Step 1: Fix IntelliJ configurations
echo "Step 1: Fixing IntelliJ IDEA configurations..."
if [ -f ".idea/workspace.xml" ]; then
    # Disable "Make" for all test configurations (multiple patterns)
    sed -i '' 's/<option name="Make" enabled="true" \/>/<option name="Make" enabled="false" \/>/g' .idea/workspace.xml
    sed -i '' 's/<option name="Make" enabled="true"/<option name="Make" enabled="false"/g' .idea/workspace.xml
    echo "✅ Disabled 'Make' for all test configurations"
    
    # Count how many were fixed
    MAKE_DISABLED=$(grep -c '<option name="Make" enabled="false"' .idea/workspace.xml 2>/dev/null || echo "0")
    MAKE_ENABLED=$(grep -c '<option name="Make" enabled="true"' .idea/workspace.xml 2>/dev/null || echo "0")
    echo "   Found $MAKE_DISABLED configurations with 'Make' disabled"
    if [ ! -z "$MAKE_ENABLED" ] && [ "$MAKE_ENABLED" != "0" ] && [ "$MAKE_ENABLED" -gt 0 ] 2>/dev/null; then
        echo "   ⚠️  Warning: $MAKE_ENABLED configurations still have 'Make' enabled"
    fi
else
    echo "⚠️  .idea/workspace.xml not found"
fi

# Step 2: Clean and rebuild with Maven
echo ""
echo "Step 2: Cleaning and rebuilding with Maven (Java 22)..."
mvn clean compile test-compile -Dmaven.compiler.source=22 -Dmaven.compiler.target=22 -Dmaven.compiler.release=22

if [ $? -ne 0 ]; then
    echo "❌ Build failed!"
    exit 1
fi

# Step 3: Verify class file versions
echo ""
echo "Step 3: Verifying class file versions..."
echo "Checking for Java 23+ classes (version 67+)..."

# Find all class files and check their versions
WRONG_VERSION_FOUND=false
for classfile in $(find target -name "*.class" 2>/dev/null); do
    version=$(javap -verbose "$classfile" 2>/dev/null | grep "major version" | awk '{print $3}')
    if [ ! -z "$version" ] && [ "$version" -ge 67 ]; then
        echo "❌ Found Java 23+ class: $classfile (version $version)"
        WRONG_VERSION_FOUND=true
    fi
done

if [ "$WRONG_VERSION_FOUND" = true ]; then
    echo ""
    echo "❌ ERROR: Some classes are still compiled with Java 23+"
    echo "Please check your IntelliJ IDEA settings:"
    echo "  1. File → Project Structure → Project → SDK: Should be Java 22"
    echo "  2. File → Settings → Build, Execution, Deployment → Compiler → Java Compiler"
    echo "     Project bytecode version: Should be 22"
    exit 1
else
    echo "✅ All classes are compiled with Java 22 (version 66)"
fi

echo ""
echo "=========================================="
echo "✅ Fix complete! All classes are Java 22"
echo "=========================================="
echo ""
echo "You can now run your tests."
echo ""
echo "IMPORTANT: If you make code changes, run this script again:"
echo "  ./fix-java-version.sh"
echo ""

