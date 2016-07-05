/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine.compilationInfo;

/**
 *
 * @author Admin
 */
public class CompilationResult {

    private String compilerMassege;
    private String errorMassege;
    private boolean compiledSuccessfully;

    public CompilationResult() {

    }

    public CompilationResult(String compilerMassege, String errorMassege, boolean compiledSuccessfully) {
        this.compilerMassege = compilerMassege;
        this.errorMassege = errorMassege;
        this.compiledSuccessfully = compiledSuccessfully;
    }

    public void setCompilerMassege(String compilerMassege) {
        this.compilerMassege = compilerMassege;
    }

    public void setErrorMassege(String errorMassege) {
        this.errorMassege = errorMassege;
    }

    public void setCompiledSuccessfully(boolean compiledSuccessfully) {
        this.compiledSuccessfully = compiledSuccessfully;
    }

    public String getCompilerMassege() {
        return compilerMassege;
    }

    public String getErrorMassege() {
        return errorMassege;
    }

    public boolean isCompiledSuccessfully() {
        return compiledSuccessfully;
    }

}
