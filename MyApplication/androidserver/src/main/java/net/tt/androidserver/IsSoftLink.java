package net.tt.androidserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by admin on 2017/5/16.
 */

public class IsSoftLink {

    public static String resultExeCmd(String cmd) {
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            pro = runTime.exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                returnString = returnString + line + "\n";
            }
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnString;
    }

    public static String result(String msg) {
        char first = msg.charAt(0);
        String returnString = "";
        switch (first) {
            case 'l':
                returnString = "是软连接";
                break;
            case '-':
                returnString = "是普通文件";
                break;
            default:
                returnString = "是目录";
                break;
        }
        return returnString;
    }

    public static void main(String[] args) {

        String cmd = "adb shell ";
        String returnString = resultExeCmd(cmd);
        System.out.println( result(returnString));

        String file = "am start -n com.android.browser/com.android.browser.BrowserActivity";
        cmd = file;
        returnString = resultExeCmd(cmd);
        System.out.println( result(returnString));

    }
}
