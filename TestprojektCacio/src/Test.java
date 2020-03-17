import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.java.openjdk.cacio.provolone.PTPGraphicsEnvironment;
import net.java.openjdk.cacio.provolone.PTPToolkit;

/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class Test extends JPanel {

  static {
    System.setProperty("awt.toolkit", PTPToolkit.class.getName());
    System.setProperty("java.awt.graphicsenv", PTPGraphicsEnvironment.class.getName());
    System.setProperty("java.awt.headless", "false");
  }

  public final ArrayList<Point> points = new ArrayList<Point>();

  Test() {
    JButton btn = new JButton();
    btn.setName("Cooler Button 1");
    add(btn);
    btn = new JButton();
    btn.setName("Cooler Button 2");
    add(btn);
    btn = new JButton();
    btn.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Bla");
      }
    });
    btn.setName("Cooler Button 3");
    add(btn);
  }

  public static void main(String[] args) {

    // try {
    // UIManager.setLookAndFeel("javax.swing.plaf.custom.CustomLookAndFeel");
    // } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
    // UnsupportedLookAndFeelException e1) {
    // e1.printStackTrace();
    // }

    JFrame frame = new JFrame();
    frame.setTitle("EventQueueTest");
    frame.setSize(300, 200);
    frame.addWindowListener(new WindowAdapter(){
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    Container contentPane = frame.getContentPane();
    contentPane.add(new Test());
    frame.show();
  }
}
