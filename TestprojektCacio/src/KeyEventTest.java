import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class KeyEventTest extends JFrame {

  /** version number */
  @SuppressWarnings("hiding")
  public static final String VER = "$Revision$";

  private KeyEvent keyCode;

  public KeyEventTest() {
    addKeyListener(new KeyListener(){

      @Override
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyPressed(KeyEvent e) {
        KeyEventTest.this.keyCode = e;

        KeyEventTest.this.repaint();
      }
    });
    JPanel panel = new JPanel(){
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(KeyEventTest.this.keyCode.getKeyCode() + " ( " + KeyEventTest.this.keyCode.getKeyLocation()
                     + " ) " + KeyEvent.getKeyText(KeyEventTest.this.keyCode.getKeyCode()), 50, 50);
      }
    };
    panel.setPreferredSize(new Dimension(100, 100));
    add(panel);
    setSize(100, 100);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // Locale.setDefault(new Locale("en"));
    SwingUtilities.invokeLater(new Runnable(){

      @Override
      public void run() {
        JFrame frame = new KeyEventTest();
        frame.setTitle("Test");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }
    });
  }

}
