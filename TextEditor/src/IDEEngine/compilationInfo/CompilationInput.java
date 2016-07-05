/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine.compilationInfo;

import IDEEngine.ProgrammingLanguage;

/**
 *
 * @author Admin
 */
public class CompilationInput {
    private ProgrammingLanguage language;
    private String filePath;

    public CompilationInput(String filePath, ProgrammingLanguage language) {
        this.language = language;
        this.filePath = filePath;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public String getFilePath() {
        return filePath;
    }
    
}
