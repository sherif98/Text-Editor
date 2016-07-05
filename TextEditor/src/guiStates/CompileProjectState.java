/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiStates;

import Controller.GuiState;
import IDEEngine.compilationInfo.CompilationInput;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.executionInfo.ExecutionInput;
import IDEEngine.executionInfo.ExecutionResult;
import IDEEngine.Compiler;
import filesHandling.FileSaver;
/**
 *
 * @author Admin
 */
public class CompileProjectState implements GuiState{

    @Override
    public CompilationResult compileProgram(CompilationInput compileInput) {
        return Compiler.getInstance().compile(compileInput);
    }

    @Override
    public ExecutionResult runProgram(ExecutionInput executionInput) {
        return null;
    }

      @Override
    public boolean requestCompiler() {
        return true;
    }

    @Override
    public boolean requestRunner() {
        return false;
    }

    @Override
    public boolean createProject(String path) {
         return FileSaver.getInstance().createDirectory(path);
    }

    @Override
    public boolean createClass(String className) {
        return FileSaver.getInstance().createFile(className);
    }
    
}
