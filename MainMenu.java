package PizzaPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainMenu 
{
	// Problems 2.Panel
	
	static JFrame frameOrder;
	static ArrayList <Orders> totalOrders = new ArrayList<Orders>();
	static String con = "";
	static String name = "";
	static int quan = 0;
	static int slice = -1;
	static int hr = 7;
	static int min = 0;
	static boolean paid = false;
	
	
	
	public static void main(String[] args) throws IOException
	{
		Stock stock = new Stock();
		Value value = new Value();
		GUI screen = new GUI(value);
		
		menu(value.click);
	}
	
	public static void menu(int click) throws IOException
	{
		Scanner kb = new Scanner(System.in);
		
		FileWriter writer = new FileWriter("Stock.txt");
		Scanner sc = new Scanner(new File("Stock.txt"));
		
		Scanner sr = new Scanner(new File("Data.txt"));
		
		
		//for (;;)
		{
			Stock.check();
			
			for (int i = 0; i < totalOrders.size(); i++)
			{
				Orders.changeStock(totalOrders.get(i).getContent(), totalOrders.get(i).getStatus());
			}
			
			
			// int choice = 0;
			
			System.out.println("\nChoose one of the following options: \n1. Order\n2. Stocks\n3. View Statistics\n4. Exit");
			//choice = kb.nextInt();
			
			
			if (click == 1)
			{
				takeOrder();
				// JList
			}
			else if (click == 2) //choice == 2)
			{
				JFrame stockFrame = new JFrame();
				JPanel stockPanel = new JPanel();
				stockFrame.setSize(300,500);
				stockFrame.setVisible(true);
				stockFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				stockFrame.add(stockPanel);
				String[] stocks = new String[Stock.ingredientName.size()];
				JLabel[] labels = new JLabel[Stock.ingredientName.size()];
				SpringLayout layout = new SpringLayout();
				stockPanel.setLayout(layout);
				int x = 30;
				
				while (sc.hasNext())
				{
					for (int i = 0; i < Stock.ingredient.size(); i++)
					{
						Stock.ingredientName.set(i, sc.next());
						Stock.ingredient.set(i, Integer.parseInt(sc.next()));
					}
				}
				
				for (int i = 0; i < Stock.ingredient.size(); i++)
				{
					stocks[i] = Stock.ingredientName.get(i) + " " + Stock.ingredient.get(i);
					labels[i] = new JLabel(stocks[i]);
					System.out.println(Stock.ingredientName.get(i) + " " + Stock.ingredient.get(i));
				}
				
				for (int i = 0; i < labels.length; i++)
				{
					stockPanel.add(labels[i]);
					layout.putConstraint(SpringLayout.BASELINE, labels[i], x, SpringLayout.BASELINE, stockPanel);
					x += 20;
				}
				

				// if () //Touch button to add ingredients
				{
					// Stock.addStock(type, num);
				}
				// else if () //Touch button to reduce ingredients
				{
					// Stock.reduceStock(type, num);
				}
				
			}
			else if (click == 3) //choice == 3)
			{
				
				if (!sr.hasNext())
				{
					for (int i = 0; i < Orders.getSoldLength(); i++)
					{
						System.out.print(Orders.getSoldItems(i) + " " + Orders.getSoldNum(i) + "\n");
					}
				}
				
				int i = 0;
				while (sr.hasNext())
				{
					if (i % 2 == 0)
					System.out.print(sr.next() + " ");
					else
					{
						System.out.println(sr.next());
					}
					i++;
				}
			}
			else if (click == 4)
			{
				for (int i = 0; i < Stock.ingredientName.size(); i++)
				{
					writer.write("\n" + Stock.ingredientName.get(i) + " " + Stock.ingredient.get(i));
				}
				writer.close();
				
				if (Orders.writer2 != null)
				{
					for (int i = 0; i < Orders.getSoldLength(); i++)
					{
						Orders.writer2.write("\n" + Orders.getSoldItems(i) + " " + Orders.getSoldNum(i));
					}
					Orders.writer2.close();
					
					System.out.println("Stocks: ");
					while (sc.hasNext())
					{
						System.out.print(sc.next() + "\n");
					}
					
					System.out.print("\nData:");
					while (sr.hasNext())
					{
						System.out.print(sr.next() + "\n");
					}
				}
				else 
				{
					System.out.println("Stocks: ");
					int j = 0;
					
					while (sc.hasNext())
					{
						if (j % 2 == 0)
						{
							System.out.print(sc.next() + " ");
						}
						else
						{
							System.out.print(sc.next()+ "\n");
						}
						j++;
					}
					
					System.out.print("\nData:");
					for (int i = 0; i < Orders.getSoldLength(); i++)
					{
						System.out.print("\n" + Orders.getSoldItems(i) + " " + Orders.getSoldNum(i));
					}
					
				}
				
				//exit
			}
		}
		// break;
	}
	
	
	public static void takeOrder() throws IOException 
	{	
		frameOrder = new JFrame();
		JPanel orderPan = new JPanel();
		JLabel quanLabel = new JLabel("Quantity");
		JLabel sliceLabel = new JLabel("Slices");
		JLabel timeHr = new JLabel("Time(hr)");
		JLabel timeMin = new JLabel("Time(min)");
		
		frameOrder.setSize(400,400);
		frameOrder.setVisible(true);
		frameOrder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout layout = new SpringLayout();
		orderPan.setLayout(layout);
		
		String[] pizzaChoice = {"Choose", "New Yorker", "Plain Cheese", "Meat Lovers", "Brie And Prosciutto", "Mediterraneo", "Loaded Hawaiian", "Sweet Stinger", "Black Truffle Mushroom", "Other"};
		JComboBox choiceList = new JComboBox(pizzaChoice);
		choiceList.setSelectedIndex(0);
		
		JTextField name = new JTextField("Customer Name", 20);
		JButton nameButton = new JButton("Complete");
		
		SpinnerModel quantity =  new SpinnerNumberModel(0, 0, 10, 1);
		JSpinner qSpinner =  new JSpinner(quantity);
		
		SpinnerModel slices =  new SpinnerNumberModel(0, 0, 24, 8);
		JSpinner sSpinner =  new JSpinner(slices);
		
		SpinnerModel hr =  new SpinnerNumberModel(7, 7, 23, 1);
		JSpinner hrSpinner =  new JSpinner(hr);
		
		SpinnerModel min =  new SpinnerNumberModel(0, 0, 60, 5);
		JSpinner minSpinner =  new JSpinner(min);
		
		JCheckBox paidCheck = new JCheckBox("Paid");
		paidCheck.setMnemonic(KeyEvent.VK_C);
		
		JButton finish = new JButton("Done");
		
		layout.putConstraint(SpringLayout.BASELINE, name, 60, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, nameButton, 60, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.WEST, nameButton, 300, SpringLayout.WEST, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, quanLabel, 100, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, qSpinner, 100, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.WEST, qSpinner, 60, SpringLayout.WEST, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, sliceLabel, 140, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, sSpinner, 140, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.WEST, sSpinner, 60, SpringLayout.WEST, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, timeHr, 180, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, hrSpinner, 180, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.WEST, hrSpinner, 60, SpringLayout.WEST, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, timeMin, 220, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, minSpinner, 220, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.WEST, minSpinner, 60, SpringLayout.WEST, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, paidCheck, 260, SpringLayout.BASELINE, orderPan);
		layout.putConstraint(SpringLayout.BASELINE, finish, 300, SpringLayout.BASELINE, orderPan);
		
		frameOrder.add(orderPan);
		orderPan.add(choiceList);
		orderPan.add(name);
		orderPan.add(nameButton);
		orderPan.add(quanLabel);
		orderPan.add(qSpinner);
		orderPan.add(sliceLabel);
		orderPan.add(sSpinner);
		orderPan.add(timeHr);
		orderPan.add(hrSpinner);
		orderPan.add(timeMin);
		orderPan.add(minSpinner);
		orderPan.add(paidCheck);
		orderPan.add(finish);
		
		
		choiceList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == choiceList)
				{
					JComboBox cb = (JComboBox)e.getSource();
					String c = (String)cb.getSelectedItem();
					ifQualify(c,"",0,-1,7,0,false);
				}	
			}
	      });
		
		/*
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == choiceList)
				{
					String newline = "\n";
					String n = name.getText();
					ifQualify("",n,0,0,-1,-1,false);
				}	
			}
	      });
	      */
		
		nameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = name.getText();
				ifQualify("",n,0,-1,7,0,false);
			}
	      });
		
		qSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int num = (int) ((JSpinner)e.getSource()).getValue();
				ifQualify("","",num,-1,7,0,false);
			}
	      });
		
		sSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int s = (int) ((JSpinner)e.getSource()).getValue();
				ifQualify("","",0,s,7,0,false);
			}
	      });
		
		hrSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int h = (int) ((JSpinner)e.getSource()).getValue();
				ifQualify("","",0,-1,h,0,false);
			}
	      });
		
		minSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int m = (int) ((JSpinner)e.getSource()).getValue();
				ifQualify("","",0,-1,7,m,false);
			}
	      });
		
		paidCheck.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object source = e.getItemSelectable();
				boolean p = false;
				if(source == paidCheck)
				{
					p = true;
					ifQualify("","",0,-1,7,0,p);
				}
			}
	      });
		
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createOrder();
					//frameOrder.dispatchEvent(new WindowEvent(frameOrder, WindowEvent.WINDOW_CLOSING));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	      });
		
		/*
		//Orders.Cancel(totalOrders, kb.next());
		Orders.printAllOrders(totalOrders);
		// System.out.println(numOfOrders);
		//menu();
		// frameOrder.dispatchEvent(new WindowEvent(frameOrder, WindowEvent.WINDOW_CLOSING));
		 */
	}
	
	public static void ifQualify(String c, String n, int num, int s, int h, int m, boolean p)
	{
		if (!c.equals(""))
		{
			con = c;
		}
		if (!n.equals(""))
		{
			name = n;
		}
		if (num != 0)
		{
			quan = num;
		}
		if (s != -1)
		{
			slice = s;
		}
		if (h != 7)
		{
			hr = h;
		}
		if (m != 0)
		{
			min = m;
		}
		if (p == true)
		{
			paid = p;
		}
	}
	
	public static void createOrder() throws IOException
	{
		if (!con.equals("") && !name.equals("") && quan != 0 && slice != -1 && hr >= 7 && min >= 0)
		{
			Orders order = new Orders(con, name, quan, slice, paid, hr, min);
			Orders.organize(totalOrders, order, hr, min);
			for (int i = 0; i < totalOrders.size(); i++)
			{
				if (order == totalOrders.get(i))
				{
					Orders.index = i;
				}
			}
			con = "";
			name = "";
			quan = 0;
			slice = -1;
			hr = 7;
			min = 0;
			paid = false;
			frameOrder.dispatchEvent(new WindowEvent(frameOrder, WindowEvent.WINDOW_CLOSING));
			System.out.print(totalOrders.size());
			
			Panel.refreshOrder(GUI.panel, Orders.index);
			Orders.index = 0;
		}
		else
		{
			JFrame error = new JFrame();
			error.setSize(100,100);
			error.setVisible(true);
			error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JLabel quanLabel = new JLabel("Missing Info");
			error.add(quanLabel);
		}
	}
}
