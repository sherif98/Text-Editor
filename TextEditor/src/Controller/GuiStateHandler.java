/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import IDEEngine.ProgrammingLanguage;
import IDEEngine.compilationInfo.CompilationInput;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.executionInfo.ExecutionInput;
import IDEEngine.executionInfo.ExecutionResult;
import filesHandling.FileSaver;
import guiStates.CompileProjectState;
import guiStates.CreateClassState;
import guiStates.CreateProjectState;
import guiStates.RunProjectState;

/**
 *
 * @author Admin
 */
public class GuiStateHandler {

    private GuiState currentState;
    private String filePath;
    private String className;

    public GuiStateHandler() {
        currentState = new CreateProjectState();
    }

    public boolean createProject(String path) {

        boolean sucess = currentState.createProject(path);
        if (sucess) {
            this.filePath = path;
            setState(new CreateClassState());
        }
        return sucess;
    }

    public boolean createClass(String className) {
        boolean sucess = currentState.createClass(filePath + "\\" + className);
        if (sucess) {
            this.className = className;
            setState(new CompileProjectState());
        }
        return sucess;
    }

    public boolean requestCompiler() {
        return currentState.requestCompiler();
    }

    public boolean requestRunner() {
        return currentState.requestRunner();
    }

    public CompilationResult compile(String codeToBeCompiled, ProgrammingLanguage language) {
        FileSaver.getInstance().saveFile(codeToBeCompiled, filePath + "\\" + className);
        CompilationInput input = new CompilationInput(filePath + "\\" + className, language);
        CompilationResult result = currentState.compileProgram(input);
        if (result.isCompiledSuccessfully()) {
            setState(new RunProjectState());
        }
        return result;
    }

    public ExecutionResult run(String inputToTheProgram, ProgrammingLanguage language) {
        ExecutionInput input = new ExecutionInput(inputToTheProgram, filePath + " " + className, language);
        return currentState.runProgram(input);
    }

    private void setState(GuiState state) {
        this.currentState = state;
    }

}
