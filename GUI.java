package PizzaPack;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI 
{
	JFrame frame;
	static Panel panel;
	JButton button;
	Value value;
	
	GUI(Value value)
	{
		this.value = value;
		frame = new JFrame();
		panel = new Panel(value);
		frame.add(panel);
		//frame.add(Panel.orderP);
		frame.setSize(800,800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
