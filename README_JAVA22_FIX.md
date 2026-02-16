# 🔴 PERMANENT FIX - Java Version Mismatch Error

## The Problem
IntelliJ IDEA keeps compiling classes with Java 23, but your runtime uses Java 22, causing:
```
UnsupportedClassVersionError: class file version 67.0, this version only recognizes up to 66.0
```

## ✅ The Permanent Solution

### Quick Fix (Run This First)
```bash
./fix-java-version.sh
```

This will:
- Fix all IntelliJ configurations
- Rebuild with Java 22
- Verify everything is correct

### Permanent Fix (Do This Once)

**You MUST configure IntelliJ IDEA manually for a permanent fix:**

#### 1. Delegate Builds to Maven (MOST IMPORTANT!)
1. **File → Settings** (`⌘,`)
2. **Build, Execution, Deployment → Build Tools → Maven → Runner**
3. ✅ **Check "Delegate IDE build/run actions to Maven"**
4. Set **JRE** to **22**
5. Click **Apply** and **OK**

#### 2. Disable Automatic Compilation
1. **File → Settings** (`⌘,`)
2. **Build, Execution, Deployment → Compiler**
3. ❌ **Uncheck "Build project automatically"**
4. Click **Apply** and **OK**

#### 3. Set Project SDK
1. **File → Project Structure** (`⌘;`)
2. **Project → SDK**: Select **22**
3. **Project → Language level**: Select **22**
4. Click **Apply** and **OK**

#### 4. Set Compiler Bytecode Version
1. **File → Settings** (`⌘,`)
2. **Build, Execution, Deployment → Compiler → Java Compiler**
3. **Project bytecode version**: **22**
4. **Per-module bytecode version**: Set **FirstSeleniumProject** to **22**
5. Click **Apply** and **OK**

## 🚀 Workflow (Always Follow This!)

1. **Make code changes**
2. **Run**: `./fix-java-version.sh` or `./rebuild.sh`
3. **Wait for build to complete** (you'll see "✅ Build successful!")
4. **Run tests in IntelliJ**

**⚠️ NEVER run tests without rebuilding first!**

## 🔧 Scripts Available

- `./fix-java-version.sh` - Comprehensive fix (recommended)
- `./rebuild.sh` - Quick rebuild with Java 22
- `./setup-permanent-fix.sh` - One-time setup

## ❓ Why Does This Keep Happening?

IntelliJ IDEA has its own compiler that runs automatically. Even with "Make" disabled, IntelliJ may compile when:
- You save files
- You run tests  
- Background compilation runs

**Solution**: Delegate ALL builds to Maven (Step 1 above). This makes Maven handle ALL compilation, not IntelliJ.

## ✅ Verification

Check if classes are Java 22:
```bash
javap -verbose target/test-classes/com/qa/opencart/test/AccountPageTest.class | grep "major version"
# Should show: major version: 66
```

If it shows **67 or higher**, IntelliJ is still compiling. Follow Step 1 again.

## 📝 Summary

**The key is Step 1**: Delegate builds to Maven. Once you do that, IntelliJ will NEVER compile with the wrong Java version because Maven handles everything.

See `PERMANENT_FIX_INSTRUCTIONS.md` for detailed step-by-step instructions.












