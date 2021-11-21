package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "I/O GUI application";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new ControllerImpl();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the centre and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behaviour of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * 2) In its constructor, sets up the whole view
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JTextField field = new JTextField();
        panel.add(field, BorderLayout.NORTH);

        final JTextArea area = new JTextArea();
        area.setEditable(false);
        panel.add(area, BorderLayout.CENTER);

        final JPanel bar = new JPanel();
        bar.setLayout(new BorderLayout());
        panel.add(bar, BorderLayout.SOUTH);

        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show history");
        bar.add(print, BorderLayout.WEST);
        bar.add(history);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String text = field.getText();
                controller.setNextLine(text);
                controller.printCurrentLine();
                area.setText(text);
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> lines = controller.getPreviousLines();
                //String prevs = "";
                final StringBuilder prev = new StringBuilder();
                for (final String line : lines) {
                   //prevs += line + "\n";
                   prev.append(line);
                   prev.append('\n');
                }
                //System.out.println(prevs);
                area.setText(prev.toString());
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
