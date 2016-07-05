/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine;

import IDEEngine.compilationInfo.StringHandlingUtilities;
import IDEEngine.executionInfo.ExecutionInput;
import IDEEngine.executionInfo.ExecutionResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.GUI;

/**
 *
 * @author Admin
 */
public class Runner {

    private static Runner instance;

    private Runner() {
    }

    public static Runner getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Runner();
        return instance;
    }

    public ExecutionResult run(ExecutionInput executionInput) {
        ExecutionResult runningResult = new ExecutionResult();
        try {
            String path = StringHandlingUtilities.replaceSlashes(executionInput.getFilePath());
            System.out.println(path);
            path = StringHandlingUtilities.replaceSlashes(path);
            System.out.println(path);
            StringBuilder pathBuilder = new StringBuilder(path);
//            pathBuilder.setCharAt(pathBuilder.lastIndexOf("/"), ' ');
            String ans = pathBuilder.substring(0, pathBuilder.lastIndexOf("."));
            System.out.println(ans);
            String command = executionInput.getLanguage().getExecutingCommand() + " " + ans;
            System.out.println(command);
            Process p = Runtime.getRuntime().exec(command);
            PrintStream ps = new PrintStream(p.getOutputStream());
            if (executionInput.getInputToTheProgram() != null && executionInput.getInputToTheProgram() != "") {
                StringTokenizer tknz = new StringTokenizer(executionInput.getInputToTheProgram(), ""); //hmmmm
                while (tknz.hasMoreTokens()) {
                    String tmpS = tknz.nextToken();
                    System.out.println(tmpS);
                    ps.println(tmpS);
                }
                ps.close();
            }

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String result = "";
            StringBuilder resultStringBuilder = new StringBuilder();
            while ((result = stdInput.readLine()) != null) {
                resultStringBuilder.append(result + "\n");
            }
            runningResult.setRunningInfo(resultStringBuilder.toString());
            resultStringBuilder = new StringBuilder();
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((result = stdError.readLine()) != null) {
                resultStringBuilder.append(result + "\n");
            }
            runningResult.setRunningErrors(resultStringBuilder.toString());
        } catch (IOException ex) {

        }
        return runningResult;
    }
}
