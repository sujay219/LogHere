package com.wildox.loghere;

import android.util.Log;

import static java.lang.Thread.currentThread;

/**
 * Created by Sujay Kumar on 21/12/16.
 * Class that logs the details
 */

public class LogHere {

    private static boolean DEBUG_MODE = true;
    private static String PACKAGE_NAME;

    public static boolean getDebugMode() {
        return DEBUG_MODE;
    }

    /**
     * Setting the debug mode here
     * to manage printing logs if it's in release mode
     */
    public static void setDebugMode(boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    public static void setPackageName(String packageName) {
        int siz = packageName.length();
        if (siz > 20) {
            PACKAGE_NAME = packageName.substring(siz - 20);
        } else
            PACKAGE_NAME = packageName;
    }

    public static void e(String message) {

        if (!DEBUG_MODE)
            return;
        StackTraceElement[] stack = currentThread().getStackTrace();
        StackTraceElement element = stack[3];
        if (!element.isNativeMethod()) {

            String fileName = element.getFileName();
            if (fileName.contains("."))
                fileName = fileName.substring(0, fileName.indexOf("."));
            fileName = ((fileName.length() > 15) ? fileName.substring(0, 15) : fileName);

            int lineNumber = element.getLineNumber();
            String methodName = element.getMethodName();

            String tmp = fileName + "(" + String.valueOf(lineNumber) + ")";
            Log.e(tmp.substring(0, ((tmp.length() > 20) ? 20 : tmp.length())),
                    methodName + "()" + " : " + message);
        }
    }

    /**
     * @param message:     to show
     * @param parentCount: 0, 1, 2
     */
    public static void e(String message, int parentCount) {

        if (!DEBUG_MODE)
            return;
        Thread current = currentThread();
        // Log.e(".", current.getName()); // returns main()
        // Log.e(".", current.getState().name()); // returns main()
        StackTraceElement[] stack = current.getStackTrace();
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
                    Log.e(PACKAGE_NAME, "no stack trace available");
                    return;
                }
                fileStack = getFileStack(stack[5], stack[4], stack[3]);
                break;
            default:
                Log.e(PACKAGE_NAME, "parentCount param must be 0, 1 or 2");
                return;
        }
        Log.e(PACKAGE_NAME, fileStack + "\n" + message);
    }

    private static String getFileStack(StackTraceElement... stackTraceElement) {

        StringBuilder trace = new StringBuilder();
        for (int i = stackTraceElement.length - 1; i >= 0; i--) {
            StackTraceElement element = stackTraceElement[i];
            if (!element.isNativeMethod()) {

                String fileName = element.getFileName();
                if (fileName.length() > 20)
                    fileName = removeVowels(fileName);

                if (fileName.contains("."))
                    fileName = fileName.substring(0, fileName.indexOf("."));
                //fileName = ((fileName.length() > 15) ? fileName.substring(0, 15) : fileName);

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

    private static String removeVowels(String fileName) {
        return fileName.replaceAll("[aeiouAEIOU]", "");
    }
}
