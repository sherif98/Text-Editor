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
public class StringHandlingUtilities {
    
    
    private StringHandlingUtilities(){}
    
    public static String replaceSlashes(String input){
         return input.replace("\\", "/");
    }
    
}
