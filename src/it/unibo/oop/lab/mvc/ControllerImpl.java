package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

/**
 * A controller that prints strings and has memory of the strings it printed.
 * This class implements {@link Controller}
 */
public class ControllerImpl implements Controller {

    // List of previous printed Strings
    private final List<String> list;
    // Buffer String
    private String currentLine;

    /**
     * This constructor initialise {@link ControllerImpl}.
     */
    public ControllerImpl() {
        this.currentLine = null;
        this.list = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    public void setNextLine(final String nextLine) {
        /*
        if (nextLine == null) {
            throw new NullPointerException();
        } else {
        }
        */
        this.currentLine = nextLine;
    }

    /**
     * {@inheritDoc}
     */
    public String getNextLine() {
        return this.currentLine;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getPreviousLines() {
        return list;
    }

    /**
     * {@inheritDoc}
     */
    public void printCurrentLine() {
        list.add(this.currentLine);
        this.currentLine = null;
    }
}
