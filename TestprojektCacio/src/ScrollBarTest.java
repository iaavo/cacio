import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class ScrollBarTest {
  /** version number */
  @SuppressWarnings("hiding")
  public static final String VER = "$Revision$";

  private JFrame frame;

  static {
    try {
      // UIManager.setLookAndFeel("de.icongmbh.commons.swing.plaf.ilfe.DopeLookAndFeel");
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      // UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable(){
      @Override
      public void run() {
        try {
          ScrollBarTest window = new ScrollBarTest();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public ScrollBarTest() {
    // UIManager.put("ScrollBar.thumbHeight", 40);
    // UIManager.put("ScrollBar:\"ScrollBar.button\".size", 40);
    UIManager.put("ScrollBar.width", 40);
    // UIManager.put("JComponent.sizeVariant", "large");
    // UIManager.put("ScrollBar.decrementButtonGap", 40);
    // UIManager.put("ScrollBar.minimumThumbSize", new javax.swing.plaf.DimensionUIResource(40, 40));
    // UIManager.put("ScrollBar:ScrollBarThumb.contentMargins", new javax.swing.plaf.InsetsUIResource(40, 40, 40, 40));
    // UIManager.put("ScrollBar:\"ScrollBar.button\".contentMargins",
    // new javax.swing.plaf.InsetsUIResource(40, 40, 40, 40));
    initialize();
    // SwingUtilities.updateComponentTreeUI(this.frame);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    this.frame = new JFrame();
    // UIManager.put("ScrollBar.thumbHeight", 40);
    this.frame.setBounds(100, 100, 100, 100);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JScrollPane scrollPane = new JScrollPane();
    // UIManager.put("ScrollBar.thumbHeight", 40);
    // scrollPane.getHorizontalScrollBar().putClientProperty("JComponent.sizeVariant", "mini");
    // scrollPane.getHorizontalScrollBar().putClientProperty("ScrollBar.thumbHeight", 40);
    this.frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

    JTextArea textArea = new JTextArea();

    scrollPane.setViewportView(textArea);

  }

}
