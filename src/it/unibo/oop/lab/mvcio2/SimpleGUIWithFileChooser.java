package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "I/O GUI application";
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 2) In its constructor, sets up the whole view
     * builds a new {@link SimpleGUIWithFileChooser}.
     */
    public SimpleGUIWithFileChooser() {
        /*
         * Starting from the application in mvcio:
         * 
         * 1) Add a JTextField and a button "Browse..." on the upper part of the
         * graphical interface.
         * Suggestion: use a second JPanel with a second BorderLayout, put the panel
         * in the North of the main panel, put the text field in the center of the
         * new panel and put the button in the line_end of the new panel.
         * 
         * 2) The JTextField should be non modifiable. And, should display the
         * current selected file.
         * 
         * 3) On press, the button should open a JFileChooser. The program should
         * use the method showSaveDialog() to display the file chooser, and if the
         * result is equal to JFileChooser.APPROVE_OPTION the program should set as
         * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
         * then the program should do nothing. Otherwise, a message dialog should be
         * shown telling the user that an error has occurred (use
         * JOptionPane.showMessageDialog()).
         * 
         * 4) When in the controller a new File is set, also the graphical interface
         * must reflect such change. Suggestion: do not force the controller to
         * update the UI: in this example the UI knows when should be updated, so
         * try to keep things separated.
         *
         * 5) Use "ex03.png" (in the res directory) to verify the expected aspect.
         */

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JPanel bar = new JPanel();
        bar.setLayout(new BorderLayout());
        panel.add(bar, BorderLayout.NORTH);

        final JTextField file = new JTextField();
        file.setEditable(false);
        file.setText(Controller.DEFAULT_FILE);
        bar.add(file);

        final JButton browse = new JButton("Browse");
        bar.add(browse, BorderLayout.LINE_END);

        final JTextArea area = new JTextArea();
        panel.add(area);

        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                /*
                 * This would be VERY BAD in a real application.
                 * 
                 * This makes the Event Dispatch Thread (EDT) work on an I/O
                 * operation. I/O operations may take a long time, during which
                 * your UI becomes completely unresponsive.
                 */
                try {
                    final String text = area.getText();
                    final Controller controller = new Controller(file.getText());
                    controller.addLine(text);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

                final JFileChooser selector = new JFileChooser();
                /*
                 * parameter 'null' make the FileChooser be put by OS as it wants
                 * parameter 'frame' make the FileChooser be attached to parent 'frame'
                 */
                final int returnVal = selector.showSaveDialog(frame);

                switch (returnVal) {
                    case JFileChooser.APPROVE_OPTION:
                        final String chosenFile = selector.getSelectedFile().getName();
                        file.setText(chosenFile);
                        /*
                         * Press the save button for 0 milliseconds (no visually pressed)
                         */
                        save.doClick(0);
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        final String errorMessage = "Error while choosing a file to save!";
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });

        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * 1) Main method that starts the graphical application.
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUIWithFileChooser();
    }
}
