package net.java.openjdk.cacio.ctc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import net.java.openjdk.cacio.ctc.junit.CacioFESTRunner;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.timing.Pause;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(value = CacioFESTRunner.class)
public class MyFESTTest {

  private JFrame frame;

  private FrameFixture ff;

  private final Set menuClicks = new HashSet();

  @Before
  public void setUp() {
    GuiActionRunner.execute(new GuiTask(){

      @Override
      protected void executeInEDT() throws Throwable {
        MyFESTTest.this.frame = new JFrame();
        MyFESTTest.this.frame.setLayout(new FlowLayout());
        final JButton b = new JButton("TEST");
        b.addActionListener(new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent e) {
            if (b.getText().equals("TEST")) {
              b.setText("FLUFF");
            } else {
              b.setText("TEST");
            }
          }
        });
        JPopupMenu popupMenu = new JPopupMenu();
        JMenu fileMenu = new JMenu("File");
        MenuListener menuListener = new MenuListener(){
          @Override
          public void menuSelected(MenuEvent e) {
            MyFESTTest.this.menuClicks.add(((JMenu)e.getSource()).getText());
          }

          @Override
          public void menuDeselected(MenuEvent e) {}

          @Override
          public void menuCanceled(MenuEvent e) {}
        };
        ActionListener menuItemListener = new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
            MyFESTTest.this.menuClicks.add(((JMenuItem)e.getSource()).getText());
          }
        };
        MyFESTTest.this.menuClicks.clear();
        fileMenu.addMenuListener(menuListener);
        JMenuItem openMenu = new JMenuItem("Open");
        openMenu.addActionListener(menuItemListener);
        JMenuItem menu = new JMenuItem("Test");
        menu.addActionListener(menuItemListener);
        popupMenu.add(fileMenu);
        popupMenu.add(menu);
        fileMenu.add(openMenu);
        b.setName("button");
        MyFESTTest.this.frame.add(b);
        JTextField text = new JTextField("Hallo");
        text.setName("text");
        text
          .setText("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?");
        text.setComponentPopupMenu(popupMenu);
        MyFESTTest.this.frame.add(text);
        MyFESTTest.this.frame.pack();
        MyFESTTest.this.frame.setVisible(true);
      }
    });
    this.ff = new FrameFixture(this.frame);
    this.ff.robot.settings().delayBetweenEvents(0);
    this.ff.robot.settings().eventPostingDelay(0);
  }

  @After
  public void tearDown() throws Exception {
    this.ff.cleanUp();
    GuiActionRunner.execute(new GuiTask(){

      @Override
      protected void executeInEDT() throws Throwable {
        MyFESTTest.this.frame.dispose();
        MyFESTTest.this.frame = null;
      }
    });
  }

  @Test
  @GUITest
  public void testButton() {
    JButtonFixture b = this.ff.button("button");
    this.frame.setBounds(0, 0, 600, 600);
    Pause.pause(1000);
    b.requireText("TEST");
    b.click();
    b.requireText("FLUFF");
    b.click();
    b.requireText("TEST");
  }
}
