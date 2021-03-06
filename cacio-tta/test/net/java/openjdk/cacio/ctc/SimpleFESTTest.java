package net.java.openjdk.cacio.ctc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

import junit.framework.Assert;
import net.java.openjdk.cacio.ctc.junit.CacioFESTRunner;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JMenuItemFixture;
import org.fest.swing.fixture.JPopupMenuFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(value = CacioFESTRunner.class)
public class SimpleFESTTest {

  private JFrame frame;

  private FrameFixture ff;

  private final Set menuClicks = new HashSet();

  @Before
  public void setUp() {
    GuiActionRunner.execute(new GuiTask(){

      @Override
      protected void executeInEDT() throws Throwable {
        SimpleFESTTest.this.frame = new JFrame();
        SimpleFESTTest.this.frame.setLayout(new FlowLayout());
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
            SimpleFESTTest.this.menuClicks.add(((JMenu)e.getSource()).getText());
          }

          @Override
          public void menuDeselected(MenuEvent e) {}

          @Override
          public void menuCanceled(MenuEvent e) {}
        };
        ActionListener menuItemListener = new ActionListener(){
          @Override
          public void actionPerformed(ActionEvent e) {
            SimpleFESTTest.this.menuClicks.add(((JMenuItem)e.getSource()).getText());
          }
        };
        SimpleFESTTest.this.menuClicks.clear();
        fileMenu.addMenuListener(menuListener);
        JMenuItem openMenu = new JMenuItem("Open");
        openMenu.addActionListener(menuItemListener);
        JMenuItem menu = new JMenuItem("Test");
        menu.addActionListener(menuItemListener);
        popupMenu.add(fileMenu);
        popupMenu.add(menu);
        fileMenu.add(openMenu);
        b.setName("button");
        SimpleFESTTest.this.frame.add(b);
        JTextField text = new JTextField("Hallo");
        text.setName("text");
        text
          .setText("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?^??????????????????");
        text.setComponentPopupMenu(popupMenu);
        SimpleFESTTest.this.frame.add(text);
        SimpleFESTTest.this.frame.pack();
        SimpleFESTTest.this.frame.setVisible(true);
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
        SimpleFESTTest.this.frame.dispose();
        SimpleFESTTest.this.frame = null;
      }
    });
  }

  @Test
  @GUITest
  public void testButton() {
    JButtonFixture b = this.ff.button("button");
    b.requireText("TEST");
    b.click();
    b.requireText("FLUFF");
    b.click();
    b.requireText("TEST");
  }

  @Test
  @GUITest
  public void testButtonKeyActivate() {
    JButtonFixture b = this.ff.button("button");
    b.requireText("TEST");
    b.focus();
    b.pressAndReleaseKey(KeyPressInfo.keyCode(KeyEvent.VK_SPACE));
    b.requireText("FLUFF");
    b.pressAndReleaseKey(KeyPressInfo.keyCode(KeyEvent.VK_SPACE));
    b.requireText("TEST");
  }

  @Test
  @GUITest
  public void testFocus() {
    JButtonFixture b = this.ff.button("button");
    b.focus();
    b.requireFocused();
    JTextComponentFixture t = this.ff.textBox("text");
    t.focus();
    t.requireFocused();
  }

  @Test
  @GUITest
  public void clickTopLevelMenuItem() {
    JTextComponentFixture t = this.ff.textBox("text");
    JPopupMenuFixture fixture = t.showPopupMenu();
    JMenuItemFixture menuItem = fixture.menuItemWithPath("Test");
    menuItem.click();
    Assert.assertTrue(this.menuClicks.contains("Test"));
  }

  @Test
  @GUITest
  public void clickTopLevelMenu() {
    JTextComponentFixture t = this.ff.textBox("text");
    JPopupMenuFixture fixture = t.showPopupMenu();
    JMenuItemFixture menuItem = fixture.menuItemWithPath("File");
    menuItem.click();
    Assert.assertTrue(this.menuClicks.contains("File"));
  }

  @Test
  @GUITest
  public void clickItemOnSubMenu() {
    JTextComponentFixture t = this.ff.textBox("text");
    JPopupMenuFixture fixture = t.showPopupMenu();
    JMenuItemFixture menuItem = fixture.menuItemWithPath("File", "Open");
    menuItem.click();
    Assert.assertTrue(this.menuClicks.contains("File"));
    Assert.assertTrue(this.menuClicks.contains("Open"));
  }

  @Test
  @GUITest
  public void testEnterText() {
    JTextComponentFixture t = this.ff.textBox("text");
    t.deleteText();
    t.enterText("????????????");
    // t.enterText("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?^??????????????????");
    t.requireText("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%^&*()`~-_=+[{]}\\|;:'\",<.>/?^??????????????????");
  }
}
