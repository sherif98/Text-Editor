/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import IDEEngine.compilationInfo.CompilationInput;
import IDEEngine.compilationInfo.CompilationResult;
import IDEEngine.executionInfo.ExecutionInput;
import IDEEngine.executionInfo.ExecutionResult;

/**
 *
 * @author Admin
 */
public interface GuiState {

    /**
     *
     * @param compileInput
     * @return
     */
    public abstract CompilationResult compileProgram(CompilationInput compileInput);
    public abstract ExecutionResult runProgram(ExecutionInput executionInput);
    public abstract boolean createProject(String path);
    public abstract boolean createClass(String className);
    public abstract boolean requestCompiler();
    public abstract boolean requestRunner();
}
