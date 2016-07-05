/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine.executionInfo;

import IDEEngine.ProgrammingLanguage;

/**
 *
 * @author Admin
 */
public class ExecutionInput {

    private String inputToTheProgram;

    private String filePath;
    private ProgrammingLanguage language;

    public ExecutionInput() {

    }

    public ExecutionInput(String inputToTheProgram, String filePath, ProgrammingLanguage language) {
        this.inputToTheProgram = inputToTheProgram;
        this.filePath = filePath;
        this.language = language;
    }

    public String getInputToTheProgram() {
        return inputToTheProgram;
    }

    public void setInputToTheProgram(String inputToTheProgram) {
        this.inputToTheProgram = inputToTheProgram;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public void setLanguage(ProgrammingLanguage language) {
        this.language = language;
    }
}
