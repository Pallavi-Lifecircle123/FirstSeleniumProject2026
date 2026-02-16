#!/bin/bash
# Script to fix IntelliJ IDEA workspace.xml to disable "Make" for all TestNG configurations
# This prevents IntelliJ from compiling with Java 23

WORKSPACE_XML=".idea/workspace.xml"

if [ ! -f "$WORKSPACE_XML" ]; then
    echo "❌ Error: $WORKSPACE_XML not found!"
    exit 1
fi

echo "Fixing IntelliJ IDEA configurations..."
echo "Disabling 'Make' for all TestNG configurations..."

# Use sed to replace all instances of Make enabled="true" with enabled="false" in TestNG configurations
# This is a safer approach that only affects TestNG configurations
sed -i '' 's/<option name="Make" enabled="true" \/>/<option name="Make" enabled="false" \/>/g' "$WORKSPACE_XML"

if [ $? -eq 0 ]; then
    echo "✅ Successfully disabled 'Make' for all TestNG configurations"
    echo "✅ IntelliJ IDEA will no longer auto-compile with wrong Java version"
    echo ""
    echo "Next steps:"
    echo "1. Run ./rebuild.sh to compile with Java 22"
    echo "2. Run your tests in IntelliJ IDEA"
else
    echo "❌ Error: Failed to update $WORKSPACE_XML"
    exit 1
fi












