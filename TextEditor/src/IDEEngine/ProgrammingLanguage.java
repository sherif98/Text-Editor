/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine;

/**
 *
 * @author Admin
 */
public enum ProgrammingLanguage {

    JAVA("javac", "java -cp");
    private final String compilationCommand;
    private final String executingCommand;

    private ProgrammingLanguage(String compilationCommand, String executingCommand) {
        this.compilationCommand = compilationCommand;
        this.executingCommand = executingCommand;
    }

    public String getCompilationCommand() {
        return compilationCommand;
    }

    public String getExecutingCommand() {
        return executingCommand;
    }

}
