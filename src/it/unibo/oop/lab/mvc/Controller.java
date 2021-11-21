package it.unibo.oop.lab.mvc;

import java.util.List;

/**
 * This interface must model a simple controller responsible of I/O access. It
 * considers only the standard output, and it is able to print on it.
 */
public interface Controller {

    /**
     * 1) This method sets the next string to print. Null values are not
     * acceptable, and an exception should be produced.
     * 
     * @param nextLine next String to be printed
     * @throws NullPointerException if {@code nextLine} is null
     */
    void setNextLine(String nextLine);

    /**
     * 2) This method gets the next string to print.
     * @return a String that is going to be printed
     */
    String getNextLine();

    /**
     * 3) This method gets the history of the printed strings
     *    (in form of a List of Strings).
     * @return a List of String representing all printed strings
     */
    List<String> getPreviousLines();

    /**
     * 4) This method prints the current string.
     * @throws IllegalStateException if the current string is unset
     */
    void printCurrentLine();
}
