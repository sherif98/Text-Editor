/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesHandling;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FileSaver {

    private static FileSaver instance;

    private FileSaver() {
    }

    public static FileSaver getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new FileSaver();
        return instance;
    }

    public boolean createDirectory(String path) {
        System.out.println(path);
        File dir = new File(path);
        dir.mkdirs();
        return true;
    }

    public boolean createFile(String path) {

        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean saveFile(String textToSave, String path) {

        try {
            File file = new File(path);
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.write(textToSave);
            pw.flush();
            pw.close();

//        file.mkdirs();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
