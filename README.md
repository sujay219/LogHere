# Logger

Logging Simplified.

[![](https://jitpack.io/v/sujay219/Logger.svg)](https://jitpack.io/#sujay219/Logger)

Ever spent time to delete ALL LOGS from your code for release??

## How to use

- Step 1: Add jitpack to root level gradle.

      allprojects {
		       repositories {
			      		maven { url 'https://jitpack.io' }
		       }
	    }
    
- Step 2: Add dependency to app level gradle file

      dependencies {
          compile 'com.github.sujay219:LogHere:1.0.9'
      }
    
- Step 3: Now, with the help of our LogHere library, you don't need to delete your logs, just initialize at the beginning

      onCreate(Bundle savedInstanceState) {
          ...
          LogHere.setDebugMode(BuildConfig.DEBUG);
      }
    
- Result: Whenever you want to log any particular message in a particular line of MainActivity.java.

      45    someFunction() {
      46        LogHere.e("This is message");
      47    }

It'll be logged as, (As seen in logcat)

    MainActivity(46). someFunction(): This is message
    
Thank you for using it.
