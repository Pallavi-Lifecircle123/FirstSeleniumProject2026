# Permanent Fix for Java Version Mismatch (Java 22)

## Problem
IntelliJ IDEA was compiling classes with Java 23, but the runtime uses Java 22, causing `UnsupportedClassVersionError`.

## ✅ Permanent Solution Applied

### 1. Maven Configuration (pom.xml)
- ✅ Set `maven.compiler.source=22`
- ✅ Set `maven.compiler.target=22`
- ✅ Set `maven.compiler.release=22`
- ✅ Configured `maven-compiler-plugin` with Java 22

### 2. IntelliJ IDEA Configuration
- ✅ `.idea/compiler.xml` - Set bytecode target to 22
- ✅ `.idea/misc.xml` - Set project JDK to 22
- ✅ `.idea/maven.xml` - Configured Maven to use Java 22
- ✅ `.idea/workspace.xml` - **Disabled automatic "Make" before tests** (prevents Java 23 compilation)

### 3. Build Scripts
- ✅ Created `rebuild.sh` for easy rebuilding
- ✅ Created `fix-intellij-config.sh` to disable "Make" for all test configurations

## 🚀 How to Use (IMPORTANT)

### Before Running Tests:
**ALWAYS rebuild with Maven first:**

```bash
./rebuild.sh
```

Or manually:
```bash
mvn clean compile test-compile
```

### Why This is Necessary:
- IntelliJ's automatic compilation might use Java 23
- Maven always uses Java 22 (as configured)
- Running `rebuild.sh` ensures all classes are compiled with Java 22

## 🔧 IntelliJ IDEA Settings to Verify

1. **File → Project Structure (⌘;)**:
   - Project → SDK: **22** (Temurin-22.0.1)
   - Project → Language level: **22**

2. **File → Settings → Build, Execution, Deployment → Compiler → Java Compiler**:
   - Project bytecode version: **22**
   - Per-module bytecode version: **22**

3. **File → Settings → Build, Execution, Deployment → Build Tools → Maven → Runner**:
   - JRE: **22** (Temurin-22.0.1)

4. **Run → Edit Configurations**:
   - For your TestNG configuration, "Make" is now **disabled**
   - This prevents IntelliJ from recompiling with wrong Java version

## ✅ Verification

Check that classes are Java 22 (version 66.0):
```bash
javap -verbose target/classes/com/qa/opencart/pages/LoginPage.class | grep "major version"
# Should show: major version: 66
```

## ⚠️ Important Notes

- **Always run `./rebuild.sh` or `mvn clean compile test-compile` before running tests**
- IntelliJ's "Make" is disabled to prevent Java 23 compilation
- If you make code changes, rebuild with Maven before testing
- The `rebuild.sh` script ensures consistent Java 22 compilation

## 🔧 If You Create New Test Configurations

When IntelliJ IDEA creates a new test configuration, it may enable "Make" by default. If you get the Java version error again:

1. **Quick Fix**: Run `./fix-intellij-config.sh` to disable "Make" for all test configurations
2. **Manual Fix**: 
   - Run → Edit Configurations
   - Select your test configuration
   - Uncheck "Make" in the "Before launch" section
3. **Then rebuild**: Run `./rebuild.sh` to compile with Java 22

## 🔄 Workflow

1. Make code changes
2. Run `./rebuild.sh` (or `mvn clean compile test-compile`)
3. Run tests in IntelliJ IDEA

This ensures all classes are always compiled with Java 22!

