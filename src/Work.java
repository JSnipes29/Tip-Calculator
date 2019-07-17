import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Work extends JPanel
{
    JPanel[] firstArea;
    JPanel[] insideArea;
    String[] leftList;
    JLabel[] leftListLabel;
    Font font;
    JSlider[] sliders;
    JLabel[] slideNumbers;
    JPanel[] slidePanels;
    JLabel[] totals;
    JTextField billTotal;
    public Work(GridLayout layout)
    {
      //super(new GridLayout(2,1).setVgap(2).setHgap(2));
      super(layout);
      setBackground(Color.darkGray);
      setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      firstArea = new JPanel[2];
      insideArea = new JPanel[6];
      int x = 0;
      leftList = new String[] {"Bill Total:", "Tip%", "Split", "Tip:", "Split Total:", "Total:"};
      leftListLabel = new JLabel[6];
      font = new Font("Serif", Font.BOLD, 20);
      for(int i = 0; i < 2; i++)
      {

        firstArea[i] = new JPanel(new GridLayout(3,1));
        firstArea[i].setBackground(Color.darkGray);
        firstArea[i].setBorder(BorderFactory.createEmptyBorder());
        add(firstArea[i]);
        for(int j = 0 + x; j < x + 3; j++)
        {
          insideArea[j] = new JPanel(new BorderLayout());
          insideArea[j].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
          leftListLabel[j] = new JLabel(leftList[j]);
          leftListLabel[j].setFont(font);
          insideArea[j].add(BorderLayout.WEST,leftListLabel[j]);
          firstArea[i].add(insideArea[j]);
        }
        x+=3;
      }
      billTotal = new JTextField(5);
      billTotal.setFont(font);
      billTotal.addKeyListener(new billTotalListener());
      insideArea[0].add(BorderLayout.EAST,billTotal);
      sliders = new JSlider[2];
      slideNumbers = new JLabel[2];
      sliders[0] = new JSlider(2,20,10); sliders[1] = new JSlider(1,10,1);
      sliders[0].addChangeListener(new tipPercentLabel());
      sliders[1].addChangeListener(new splitLabel());
      slidePanels = new JPanel[2];
      for(int i = 0; i < 2; i++)
      {
        slidePanels[i] = new JPanel(new BorderLayout());
        slideNumbers[i] = new JLabel("" + sliders[i].getValue());
        slideNumbers[i].setFont(font);
        insideArea[i+1].add(BorderLayout.CENTER, slidePanels[i]);
        insideArea[i+1].add(BorderLayout.EAST, slideNumbers[i]);
        slidePanels[i].add(BorderLayout.CENTER, sliders[i]);
      }
      totals = new JLabel[3];
      for(int i = 0; i < 3; i++)
      {
          totals[i] = new JLabel();
          totals[i].setFont(font);
          insideArea[i + 3].add(BorderLayout.EAST, totals[i]);
      }
    }
    public class billTotalListener implements KeyListener
    {
      public void keyTyped(KeyEvent e)
      {
        char c = e.getKeyChar();
        if(notValid(c))
        {
          e.consume();
        }
      }
      public void keyPressed(KeyEvent e) {}
      public void keyReleased(KeyEvent e){
      update();
      }
      public boolean notValid(char key)
      {
        if(!Character.isDigit(key))
        {
          if(key == '.'){}
          else{return true;}
        }
        if(billTotal.getText().indexOf(".") < 0){return false;}
        if(key == '.'){return true;}
        if(billTotal.getText().substring(billTotal.getText().indexOf(".")).length() > 2)
        {
          return true;
        }
        return false;
      }
    }
    public class tipPercentLabel implements ChangeListener
    {
      public void stateChanged(ChangeEvent e)
      {
        slideNumbers[0].setText("" + sliders[0].getValue());
        update();

      }
    }
    public class splitLabel implements ChangeListener
    {
      public void stateChanged(ChangeEvent e)
      {
        slideNumbers[1].setText("" + sliders[1].getValue());
        update();

      }
    }
    public void update() {
      if(billTotal.getText().equals("")) {return;}
      double bill = Double.parseDouble(billTotal.getText());
      double tip = sliders[0].getValue() /100.0;
      tip = Math.round(tip * 100.0) / 100.0;
      int split = sliders[1].getValue();
      double total = bill * (1+tip);
      total = (total * 100.0) / 100.0;
      double splitTotal = total / split;
      splitTotal = Math.round(splitTotal * 100.0 + .49) / 100.0;
      total = splitTotal * split;
      total = Math.round(total * 100.0) / 100.0;
      double tipLabel = total - bill;
      tipLabel = Math.round(tipLabel * 100.0) / 100.0;
      totals[0].setText("" + (tipLabel));
      totals[1].setText("" + splitTotal);
      totals[2].setText("" + total);
    }
}
