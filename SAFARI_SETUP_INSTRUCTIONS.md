# Safari WebDriver Setup Instructions

## Issue
You're getting `SessionNotCreatedException` when trying to run tests with Safari browser. This is because Safari WebDriver requires additional setup on macOS.

## Solution

### Step 1: Enable Safari Remote Automation

1. **Open Safari**
2. **Enable the Develop menu:**
   - Go to Safari → Settings (or Preferences)
   - Click on the "Advanced" tab
   - Check the box "Show Develop menu in menu bar"

3. **Enable Remote Automation:**
   - Go to Safari → Develop menu
   - Check "Allow Remote Automation"

### Step 2: Enable SafariDriver (Command Line)

Run this command in Terminal (you may need to enter your password):

```bash
safaridriver --enable
```

**Note:** If you get a permission error, you might need to run:
```bash
sudo safaridriver --enable
```

### Step 3: Verify Setup

After enabling, verify that safaridriver is working:

```bash
safaridriver --version
```

You should see the Safari version.

### Step 4: Restart Safari

Close all Safari windows and restart Safari to apply the changes.

### Step 5: Run Your Tests

Now try running your `testng_regression1.xml` again. The Safari tests should work.

## Alternative: Skip Safari Tests

If you continue to have issues with Safari, you can:

1. **Remove Safari from your test suite** - Edit `testng_regression1.xml` and remove the Safari test
2. **Use Chrome or Edge instead** - These browsers are more reliable for automation

## Troubleshooting

### If safaridriver --enable doesn't work:

1. Make sure you're running the command as an administrator or with sudo
2. Check if Safari is completely closed before running the command
3. Try restarting your Mac if the issue persists

### If "Allow Remote Automation" is grayed out:

1. Make sure you've enabled the Develop menu first
2. Close all Safari windows and try again
3. Restart Safari

### If you still get errors:

Consider using Chrome or Edge browsers for testing, as they are more stable for automation.







