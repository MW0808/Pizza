package PizzaPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Panel extends JPanel
{
	// create new Frame for each choice, can add container but no paint components
	// If you want containers and paint component then a panel is needed
	
	JButton button;
	JButton button2;
	JButton button3;
	JButton button4;
//	static Panel orderP;
	JFrame frameOrder;
	static String[] allOrders = {};
	static JComboBox orderList = new JComboBox(allOrders);
	
	int p = 0;
	
	Value value;
	
	Panel()
	{
		
	}
	
	Panel(Value value)
	{	
		String[] orderNames = new String[MainMenu.totalOrders.size()];
		for (int i = 0; i < MainMenu.totalOrders.size(); i++)
		{
			orderNames[i] = MainMenu.totalOrders.get(i).getName();
		}
		
		/*
		JComboBox orderList = new JComboBox(orderNames);
		orderList.setSelectedIndex(orderNames.length);
		orderList.addActionListener((ActionListener) this)
		{
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String choiceName = (String)cb.getSelectedItem();
				updateLabel(choiceName);
			}
		}
		*/
		
		this.value = value;
		
	//	this.setLayout(new BorderLayout());
		button = new JButton("Order");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value.click = 1;
				
				try {
					MainMenu.menu(value.click);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				// auto close frame
			}
	      });
		this.add(button);
		
		button2 = new JButton("Stock");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value.click = 2;
				
				// ?
				try {
					MainMenu.menu(value.click);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	      });
		this.add(button2);
		
		button3 = new JButton("Statistics");
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value.click = 3;
				
				JFrame stats = new JFrame();
				Panel statsPanel = new Panel();
				stats.setSize(800,800);
				stats.setVisible(true);
				stats.add(statsPanel);
				// How do i make it paint on the new panel instead of the old one?
				statsPanel.repaint();
				stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				// ?
				try {
					MainMenu.menu(value.click);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	      });
		this.add(button3);
		
		button4 = new JButton("Exit");
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				value.click = 4;
				
				// ?
				try {
					MainMenu.menu(value.click);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	      });
		this.add(button4);
	}
	
	public static void refreshOrder(Panel panel, int index)
	{
		panel.add(orderList);
		
		if (MainMenu.totalOrders.size() > 0)
		{
			allOrders = new String[MainMenu.totalOrders.size()];
			allOrders[index] = MainMenu.totalOrders.get(index).getName();
			
			orderList.insertItemAt(allOrders[index], index);
			
			orderList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == orderList)
					{
						JComboBox cb = (JComboBox)e.getSource();
						String c = (String)cb.getSelectedItem();
						newFrame(c);
					}	
				}
		      });
		}
	}
	

	
	public static void newFrame(String c)
	{
		String infoC = "", infoN = "", infoQ = "", infoS = "", infoT = "", infoP = "";
		JFrame orderDetail = new JFrame();
		JPanel orderPanel = new Panel();
		
		orderDetail.add(orderPanel);
		orderDetail.setSize(500,500);
		orderDetail.setLocation(160, 200);
		orderDetail.setVisible(true);
		orderDetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		orderPanel.setLayout(layout);
		
		for (int i = 0; i < MainMenu.totalOrders.size(); i++)
		{
			if (c.equals(MainMenu.totalOrders.get(i).getName()))
			{
				infoN = "Name: " + MainMenu.totalOrders.get(i).getName();
				infoC = "Content: " + MainMenu.totalOrders.get(i).getContent();
				infoQ = "Quantity: " + MainMenu.totalOrders.get(i).getQuantity();
				infoS = "Slices: " + MainMenu.totalOrders.get(i).getSlices();
				infoT = "Time: " + MainMenu.totalOrders.get(i).getTimeHr() + ":" + MainMenu.totalOrders.get(i).getTimeMin();
				infoP =	"Paid: " + MainMenu.totalOrders.get(i).getPaid();
				infoS = "Status: " + MainMenu.totalOrders.get(i).getStatus(); 
				break;
			}
				
		}
		
		System.out.println(infoC);
		
		JLabel orderN = new JLabel(infoN);
		JLabel orderC = new JLabel(infoC);
		JLabel orderQ = new JLabel(infoQ);
		JLabel orderS = new JLabel(infoS);
		JLabel orderT = new JLabel(infoT);
		JLabel orderP = new JLabel(infoP);
		JButton changeStatus = new JButton("Change Status");
		
		layout.putConstraint(SpringLayout.BASELINE, orderN, 60, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, orderC, 90, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, orderQ, 120, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, orderS, 150, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, orderT, 180, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, orderP, 210, SpringLayout.BASELINE, orderPanel);
		layout.putConstraint(SpringLayout.BASELINE, changeStatus, 270, SpringLayout.BASELINE, orderPanel);
		
		orderPanel.add(orderN);
		orderPanel.add(orderC);
		orderPanel.add(orderQ);
		orderPanel.add(orderS);
		orderPanel.add(orderT);
		orderPanel.add(orderP);
		orderPanel.add(changeStatus);
		
		changeStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame statusFrame = new JFrame();
				JPanel statusP = new Panel();
				JButton done = new JButton("Done");
				
				statusFrame.setSize(400,400);
				statusFrame.setLocation(160, 200);
				statusFrame.setVisible(true);
				statusFrame.add(statusP);
				statusFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				SpringLayout layout1 = new SpringLayout();
				statusP.setLayout(layout1);
				layout1.putConstraint(SpringLayout.BASELINE, statusFrame, 60, SpringLayout.BASELINE, statusP);
				layout1.putConstraint(SpringLayout.BASELINE, done, 90, SpringLayout.BASELINE, statusP);
				
				JComboBox changeStatusList = new JComboBox(Orders.getStatusList());
				statusP.add(changeStatusList);
				statusP.add(done);
				
				changeStatusList.setSelectedIndex(0);
				changeStatusList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getSource() == changeStatusList)
						{
							JComboBox cb = (JComboBox)e.getSource();
							String c = (String)cb.getSelectedItem();
							
							for (int i = 0; i < Orders.getStatusList().length; i++)
							{
								if (c.equals(Orders.getStatusList()[i]))
								{
									MainMenu.totalOrders.get(Orders.index).setStatus(i);
									try {
										Orders.changeStock(MainMenu.totalOrders.get(Orders.index).getContent(),c);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									String infoSt = MainMenu.totalOrders.get(Orders.index).getStatus();
									orderPanel.remove(orderS);
									orderPanel.revalidate();
									orderPanel.repaint();
									JLabel newOrderSt = new JLabel(infoSt);
									orderPanel.add(newOrderSt);
									layout.putConstraint(SpringLayout.BASELINE, newOrderSt, 150, SpringLayout.BASELINE, orderPanel);
								}
							}
						}
					}
			      });
				
				
				done.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						statusFrame.dispatchEvent(new WindowEvent(statusFrame, WindowEvent.WINDOW_CLOSING));
					}
			      });
			}
	      });
		
	}
	
	/*
	public void paintComponent(Graphics g)
	{
		if (value.click == 3)
		{
			g.drawString("Stats", 600, 400);
		}
		
		if (p == 1)
		{
			g.drawString("hi", 400, 400);
		}
	}
	*/
	
}