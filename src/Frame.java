import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Frame extends JFrame
{
  int frameHeight;
  int frameWidth;
  GridLayout grid;
  JPanel background;
  public Frame()
  {
    this("");
  }
  public Frame(String titlec)
  {
    super(titlec);
    frameWidth = 300;
    frameHeight = 450;
  }
  public void go()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(frameWidth,frameHeight);
    background = new JPanel(new BorderLayout());
    background.setFocusable(true);
    background.addMouseListener(new Clicks());
    getContentPane().add(background);
    JLabel title = new JLabel("Tip Calculator", SwingConstants.CENTER);
    title.setBackground(Color.white); title.setOpaque(true);
    grid = new GridLayout(2,1);
    grid.setVgap(20); grid.setHgap(10);
    background.add(BorderLayout.NORTH, title);
    background.add(BorderLayout.CENTER, new Work(grid));
    setResizable(false);
    setVisible(true);
  }
  public class Clicks implements MouseListener
  {
    public void mouseClicked(MouseEvent e)
    {
      background.grabFocus();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  }

}
