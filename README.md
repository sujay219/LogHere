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
            compile 'com.github.sujay219:LogHere:1.30'
        }
    
- Step 3: Now, with the help of our LogHere library, you don't need to delete your logs, just initialize at the beginning (Better be in AppController(Application initialization))s

        onCreate(Bundle savedInstanceState) {
            ...
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

Do star the library if you like it :)

    Copyright 2018 Sujay Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
