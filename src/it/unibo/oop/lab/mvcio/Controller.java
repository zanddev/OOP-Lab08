package it.unibo.oop.lab.mvcio;

import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintStream;

/**
 * This class must implement a simple controller responsible of I/O access. It
 * considers a single file at a time, and it is able to serialise objects in it.
 * 
 */
public class Controller implements ControllerInterface {

    private File file;
    /*
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    private static final String PATH = System.getProperty("user.home")
        + System.getProperty("file.separator");
    /**
     * This variable defines the standard name of save file.
     */
    public static final String DEFAULT_FILE = "output.txt";

    public Controller() {
        this(new File(Controller.PATH + Controller.DEFAULT_FILE));
    }

    public Controller(final String nameFile) {
        this(new File(Controller.PATH + nameFile));
    }

    public Controller(final File file) {
        this.file = file;
    }

    /**
     * {@inheritDoc}
     */
    public void setCurrentFile(final File file) {
        this.file = file;
    }

    /**
     * {@inheritDoc}
     */
    public File getCurrentFile() {
        return this.file;
    }

    /**
     * {@inheritDoc}
     */
    public String getCurrentFilePath() {
        return this.file.getPath();
    }

    /**
     * {@inheritDoc}
     */
    public void addLine(final String line) throws IOException {
        try (FileWriter fw = new FileWriter(file)) {
            System.out.println(line);
            fw.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try (PrintStream ps = new PrintStream(this.getCurrentFilePath())) {
            ps.print(newLine);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }*/
    }
}
