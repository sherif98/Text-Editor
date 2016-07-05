/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDEEngine.executionInfo;

/**
 *
 * @author Admin
 */
public class ExecutionResult {

    private String runningInfo;
    private String runningErrors;

    public ExecutionResult() {
    }

    public ExecutionResult(String runningInfo, String runningErrors) {
        this.runningInfo = runningInfo;
        this.runningErrors = runningErrors;
    }

    public String getRunningInfo() {
        return runningInfo;
    }

    public void setRunningInfo(String runningInfo) {
        this.runningInfo = runningInfo;
    }

    public String getRunningErrors() {
        return runningErrors;
    }

    public void setRunningErrors(String runningErrors) {
        this.runningErrors = runningErrors;
    }

}
