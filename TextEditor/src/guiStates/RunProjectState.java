/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiStates;

import Controller.GuiState;
import IDEEngine.Runner;
import IDEEngine.Compiler;
import IDEEngine.compilationInfo.CompilationInput;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.executionInfo.ExecutionInput;
import IDEEngine.executionInfo.ExecutionResult;
import filesHandling.FileSaver;

/**
 *
 * @author Admin
 */
public class RunProjectState implements GuiState {

    @Override
    public CompilationResult compileProgram(CompilationInput compileInput) {
        return Compiler.getInstance().compile(compileInput);
    }

    @Override
    public ExecutionResult runProgram(ExecutionInput executionInput) {
        return Runner.getInstance().run(executionInput);
    }

    @Override
    public boolean requestCompiler() {
        return true;
    }

    @Override
    public boolean requestRunner() {
        return true;
    }

    @Override
    public boolean createProject(String path) {//should be re handled (new close project state)
        return FileSaver.getInstance().createDirectory(path);
    }

    @Override
    public boolean createClass(String className) {
        return FileSaver.getInstance().createFile(className);
    }

}
