# LogHere

Logging Simplified. Printing line number and method names

[![](https://jitpack.io/v/sujay219/Logger.svg)](https://jitpack.io/#sujay219/Logger)

Ever spent time to delete ALL LOGS from your code for release??

### How to use

- Step 1: Add jitpack to root level gradle.

        allprojects {
            repositories {
                maven { url 'https://jitpack.io' }
            }
        }
    
- Step 2: Add dependency to app level gradle file

        dependencies {
            compile 'com.github.sujay219:LogHere:1.0.10'
        }
    
- Step 3: Now, with the help of our LogHere library, you don't need to delete your logs, just initialize at the beginning

        onCreate(Bundle savedInstanceState) {
            ...
            
            // Choose ONLY ONE of these ..
            Log.initialize(BuildConfig.DEBUG, "wildox");
            Log.initialize(BuildConfig.DEBUG, "wildox");
            Log.initialize(BuildConfig.DEBUG, "wildox");
            Log.initialize(BuildConfig.DEBUG, "wildox");
            Log.initialize(BuildConfig.DEBUG, "wildox");
        }
    
- Step 4: Whenever you want to log any particular message in a particular line of MainActivity.java.
        
        45  someFunction() {
        46      Log.e("This is message");
        47  }


It'll be logged as, (As seen in logcat)

    wildox: MainActivity.someFunction(46): This is message
    
- Step 5: Logging with stack trace up to 3 parents, 0, 1, 2
        
        44  someFunction() {
        45      Log.e("This is message");
        46      someOtherFunction();
        47  }
        48
        49 someOtherFunction() {
        50      Log.e("This is message", 1);
        51 }

It'll be logged as, (As seen in logcat)

    wildox: MainActivity.someOtherFunction(50) << MainActivity.someFunction(46)
        This is message
    
// wildox is package name set in initialization.

Thank you for using it.
