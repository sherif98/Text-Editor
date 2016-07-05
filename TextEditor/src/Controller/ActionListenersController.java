/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javafx.event.ActionEvent;
import gui.GUI;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.executionInfo.ExecutionResult;

/**
 *
 * @author Admin
 */
public class ActionListenersController {

    private static final GuiStateHandler stateHandler = new GuiStateHandler();
    static String path;

    public static void getCompilationAction(ActionEvent event) {
        if (stateHandler.requestCompiler()) {

            CompilationResult result = stateHandler.compile(GUI.getInstance().getStringInIDEMainTxtArea(),
                    GUI.getInstance().getLanguageSelected());

            GUI.getInstance().setStringInOutputTextArea(result.getCompilerMassege() + "\n" + result.getErrorMassege());
            GUI.getInstance().enableRunProgramButton();
            //set run button on  ?  maybe
        }
    }

    public static void getRunningAction(ActionEvent event) {

        if (stateHandler.requestRunner()) {
            ExecutionResult result = stateHandler.run(GUI.getInstance().getStringInInputTextArea(),
                    GUI.getInstance().getLanguageSelected());

            GUI.getInstance().setStringInOutputTextArea(result.getRunningInfo() + "\n" + result.getRunningErrors());
        }

    }

    public static void getCreatingProjectAction(ActionEvent event) {
        boolean sucess = stateHandler.createProject(GUI.getInstance().getFileDestination());
        if (sucess) {
            GUI.getInstance().enableCreateClassButton();
            GUI.getInstance().setTextInMainTextArea("create file");
        } else {
            //tell user that he failed creating project
        }
    }

    public static void getCreatingClassAction(ActionEvent event) {
        boolean success = stateHandler.createClass(GUI.getInstance().getClassName());
        if (success) {
            GUI.getInstance().enableTextArea();
            GUI.getInstance().enableCompileProgramButton();
        } else {
            //failed
        }
    }

    public static void getSavingFileAction(ActionEvent event) {

    }

    public static void main(String[] args) {
        GUI.launch(args);
    }

}
