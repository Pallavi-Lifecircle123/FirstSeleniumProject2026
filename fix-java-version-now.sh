#!/bin/bash

echo "=== Fixing Java Version Mismatch ==="
echo ""

# Step 1: Clean and recompile with Java 22
echo "Step 1: Cleaning and recompiling with Java 22..."
mvn clean compile test-compile
echo ""

# Step 2: Verify class file versions
echo "Step 2: Verifying class file versions..."
echo "Checking LoginPage.class..."
javap -verbose target/classes/com/qa/opencart/pages/LoginPage.class | grep "major version"
echo ""

# Step 3: Disable Make in all TestNG configurations
echo "Step 3: Disabling 'Make' in IntelliJ run configurations..."
sed -i '' 's/<option name="Make" enabled="true" \/>/<option name="Make" enabled="false" \/>/g' .idea/workspace.xml
echo "All 'Make' options disabled in workspace.xml"
echo ""

# Step 4: Check for any remaining enabled Make options
echo "Step 4: Checking for any remaining enabled Make options..."
if grep -q 'option name="Make" enabled="true"' .idea/workspace.xml; then
    echo "WARNING: Some Make options are still enabled!"
    grep 'option name="Make" enabled="true"' .idea/workspace.xml
else
    echo "✓ All Make options are disabled"
fi
echo ""

echo "=== Fix Complete ==="
echo ""
echo "Next steps:"
echo "1. In IntelliJ IDEA: File → Invalidate Caches... → Invalidate and Restart"
echo "2. After restart, run your test again"
echo ""
echo "Or run tests via Maven:"
echo "  mvn test -Dtest=ProductInfoTest#productInfoTest"








