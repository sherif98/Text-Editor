/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine;

import IDEEngine.compilationInfo.CompilationInput;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.compilationInfo.StringHandlingUtilities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import gui.GUI;

/**
 *
 * @author Admin
 */
public class Compiler {

    private static Compiler instance;

    private Compiler() {
    }

    public static Compiler getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Compiler();
        return instance;
    }

    public CompilationResult compile(CompilationInput compileInput) {
        CompilationResult compilationResult = new CompilationResult();
        try {
            String correctPath = compileInput.getFilePath();
            String command = compileInput.getLanguage().getCompilationCommand()
                    + " " + StringHandlingUtilities.replaceSlashes(correctPath);

            System.out.println(command);
            Process p = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder resultStringBuilder = new StringBuilder();
            String tmp = "";
            while ((tmp = stdInput.readLine()) != null) {
                resultStringBuilder.append(tmp + "\n");
            }
            compilationResult.setCompilerMassege(resultStringBuilder.toString());
            resultStringBuilder = new StringBuilder();
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            boolean success = true;
            while ((tmp = stdError.readLine()) != null) {
                success = false;
                resultStringBuilder.append(tmp + "\n");
            }
            compilationResult.setErrorMassege(resultStringBuilder.toString());
            compilationResult.setCompiledSuccessfully(success);
        } catch (IOException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compilationResult;
    }

}
