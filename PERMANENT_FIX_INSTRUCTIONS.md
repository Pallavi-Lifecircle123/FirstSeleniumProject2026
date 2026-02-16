# PERMANENT FIX - Java Version Mismatch

## 🔴 CRITICAL: Follow These Steps in IntelliJ IDEA

### Step 1: Configure IntelliJ to Use Maven for Building

1. **File → Settings** (or `⌘,`)
2. Go to **Build, Execution, Deployment → Build Tools → Maven → Runner**
3. Check ✅ **"Delegate IDE build/run actions to Maven"**
4. Set **JRE** to **22** (Temurin-22.0.1)
5. Click **Apply** and **OK**

### Step 2: Disable Automatic Compilation

1. **File → Settings** (or `⌘,`)
2. Go to **Build, Execution, Deployment → Compiler**
3. **Uncheck** ✅ **"Build project automatically"**
4. **Uncheck** ✅ **"Compile independent modules in parallel"** (optional)
5. Click **Apply** and **OK**

### Step 3: Set Project SDK to Java 22

1. **File → Project Structure** (or `⌘;`)
2. Under **Project Settings → Project**:
   - **SDK**: Select **22** (Temurin-22.0.1)
   - **Language level**: Select **22**
3. Click **Apply** and **OK**

### Step 4: Set Compiler Bytecode Version

1. **File → Settings** (or `⌘,`)
2. Go to **Build, Execution, Deployment → Compiler → Java Compiler**
3. **Project bytecode version**: Select **22**
4. Under **Per-module bytecode version**:
   - Find **FirstSeleniumProject** module
   - Set it to **22**
5. Click **Apply** and **OK**

### Step 5: Disable "Make" for ALL Test Configurations

1. **Run → Edit Configurations**
2. For **EACH** TestNG configuration:
   - Select the configuration
   - In **"Before launch"** section
   - **Remove** or **Uncheck** "Make"
3. Click **Apply** and **OK**

## 🚀 Workflow (IMPORTANT!)

**ALWAYS follow this workflow:**

1. Make code changes
2. **Run**: `./fix-java-version.sh` (or `./rebuild.sh`)
3. Wait for build to complete
4. Run tests in IntelliJ

**NEVER run tests without rebuilding first!**

## 🔧 Quick Fix Script

If you get the error again, run:
```bash
./fix-java-version.sh
```

This script will:
- Fix all IntelliJ configurations
- Rebuild with Java 22
- Verify all classes are Java 22

## ⚠️ Why This Keeps Happening

IntelliJ IDEA has its own compiler that runs automatically. Even with "Make" disabled, IntelliJ may still compile files when:
- You save files
- You run tests
- IntelliJ does background compilation

**Solution**: Delegate ALL builds to Maven (Step 1 above).

## ✅ Verification

After following all steps, verify:
```bash
javap -verbose target/test-classes/com/qa/opencart/test/AccountPageTest.class | grep "major version"
# Should show: major version: 66
```

If it shows 67 or higher, IntelliJ is still compiling. Follow Step 1 again.












