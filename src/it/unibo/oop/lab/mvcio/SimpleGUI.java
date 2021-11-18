package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "I/O GUI application";
    private final JFrame frame = new JFrame(TITLE);

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 3) The graphical interface consists of a JTextArea with a button "Save" right
     * below (see "ex02.png" for the expected result). SUGGESTION: Use a JPanel with
     * BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The program asks the controller to save the file if the button "Save" gets
     * pressed.
     * 
     * Use "ex02.png" (in the res directory) to verify the expected aspect.
     */

    /**
     * 2) In its constructor, sets up the whole view
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

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
                    final Controller controller = new Controller();
                    controller.addLine(text);

                    /*
                    final String[] textLines = area.getText().split("\n");
                    final Controller controller = new Controller();
                    for (final String line : textLines) {
                        //System.out.println(line);
                        controller.addLine(line);
                    }*/
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex, "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
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
       new SimpleGUI();
    }
}
