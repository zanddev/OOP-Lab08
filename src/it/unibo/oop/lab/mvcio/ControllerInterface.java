package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;

public interface ControllerInterface {

    /**
     * A method for setting a File as current file.
     * 
     * @param newFile new File to substitute the old one
     */
    void setCurrentFile(File newFile);

    /**
    * 2) A method for getting the current File.
    * 
    * @return the current File
    */
    File getCurrentFile();

    /**
    * 3) A method for getting the path (in form of String) of the current File.
    * 
    * @return path of the current file
    */
    String getCurrentFilePath();

    /**
    *  4) A method that gets a String as input and saves its content on the current
    * file. This method may throw an IOException.
    * 
    * @param line a new Line to be added into the file
    * @exception IOException
    */
    void addLine(String line) throws IOException;
}
