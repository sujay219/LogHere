package com.wildox.loghere;

import android.util.Log;

/**
 * Created by Sujay Kumar on 21/12/16.
 * Class that logs the details
 */

public class LogHere {

    public static final int VERBOSE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    private static boolean DEBUG_MODE = false;
    private static String UNIQUE_CODE;
    private static int LOG_TYPE;

    public static boolean getDebugMode() {
        return DEBUG_MODE;
    }

    /**
     * Setting the debug mode here
     * to manage printing logs if it's in release mode
     *
     * @param debugMode: BuildConfig.DEBUG should be provided here, ideally
     * @param logType: LogHere.ERROR / LogHere.DEBUG / etc etc..
     * @param uniqueCode: To identify or filter out your logs
     */
    public static void initialize(boolean debugMode, int logType, String uniqueCode) {

        if (uniqueCode.length() > 20) {
            Log.e(uniqueCode.substring(0, uniqueCode.length()), "Unique code must be less than 20 characters");
            return;
        }
        DEBUG_MODE = debugMode;
        LOG_TYPE = logType;
        UNIQUE_CODE = uniqueCode;
    }

    public static void e(String message) {

        if (!DEBUG_MODE)
            return;
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement element = stack[3];
        if (!element.isNativeMethod()) {

            String fileName = element.getFileName();
            if (fileName.contains("."))
                fileName = fileName.substring(0, fileName.indexOf("."));
            fileName = ((fileName.length() > 15) ? fileName.substring(0, 15) : fileName);

            int lineNumber = element.getLineNumber();
            String methodName = element.getMethodName();

            String tmp = fileName + "." + methodName + "(" + String.valueOf(lineNumber) + ")";
            Log.e(UNIQUE_CODE, tmp + ": " + message);
        }
    }

    /**
     * @param message: message string to show
     * @param parentCount: 0, 1, 2
     */
    public static void e(String message, int parentCount) {

        if (!DEBUG_MODE)
            return;
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        String fileStack;
        switch (parentCount) {
            case 0:
                fileStack = getFileStack(stack[3]);
                break;
            case 1:
                fileStack = getFileStack(stack[4], stack[3]);
                break;
            case 2:
                if (stack.length < 6) {
                    Log.e(UNIQUE_CODE, "no stack trace available as there are no more parents");
                    return;
                }
                fileStack = getFileStack(stack[5], stack[4], stack[3]);
                break;
            default:
                printThis("parentCount param must be 0, 1 or 2");
                return;
        }
        printThis(fileStack + "\n" + message);
    }

    private static void printThis(String m) {

        switch (LOG_TYPE) {
            case VERBOSE:
                Log.v(UNIQUE_CODE, m);
                break;
            case DEBUG:
                Log.d(UNIQUE_CODE, m);
                break;
            case INFO:
                Log.i(UNIQUE_CODE, m);
                break;
            case WARNING:
                Log.w(UNIQUE_CODE, m);
                break;
            case ERROR:
                Log.e(UNIQUE_CODE, m);
                break;
        }
    }

    private static String getFileStack(StackTraceElement... stackTraceElement) {

        StringBuilder trace = new StringBuilder();
        for (int i = stackTraceElement.length - 1; i >= 0; i--) {
            StackTraceElement element = stackTraceElement[i];
            if (!element.isNativeMethod()) {

                String fileName = element.getFileName();
                if (fileName.contains("."))
                    fileName = fileName.substring(0, fileName.indexOf("."));

                int lineNumber = element.getLineNumber();
                String methodName = element.getMethodName();

                trace.append(fileName);
                trace.append(".");
                trace.append(methodName);
                trace.append("(");
                trace.append(lineNumber);
                trace.append(")");
                if (i != 0)
                    trace.append(" << ");
            }
        }
        return trace.toString();
    }
}
