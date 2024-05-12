package PizzaPack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Orders
{
	// display all orders on main menu as list, once you click into each order you can see more detail
	
	// Where to change status and where to display all orders
	
	private String content; //ComboBox
	private String name; //Text
	private int quantity; //Spinner
	private int cutIntoSlices; //Spinner
	private int[] time = new int[2]; // Spinner
	private boolean paid; //Checkbox
	private static String[] statusList = {"Unstarted", "Making", "Baking(unflipped)", "Baking(flipped)", "Finished"};
	private String status;
	static int index = 0;
	
	
	private static String[] soldItems = {"soldPep", "soldCheese", "soldMeatLovers", "soldBrie", "soldMed", "soldHawaiian", "soldSweetStinger", "soldBlackTruffle", "soldOther"};
	// Turn below into list
	private static int[] soldNum = {0,0,0,0,0,0,0,0,0};
	static int bestSeller; 
	static String bestSellerName; 
	static FileWriter writer2;
	
	Orders(String content, String name, int numOfOrder, int cutIntoSlices, boolean paid, int hr, int min) throws IOException
	{
		writer2 = new FileWriter("Data.txt");
		
		this.content = content;
		this.name = name;
		this.quantity = numOfOrder;
		this.cutIntoSlices = cutIntoSlices;
		this.paid = paid;
		this.time[0] = hr;
		this.time[1] = min;
		this.status = statusList[0];
	}
	
	public String toString()
	{
		return ("Content: " + this.content + "\nCustomer name: " + this.name + "\nQuantity: " + 
				this.quantity + "\nSlices: " + cutIntoSlices + "\nTime: " + time[0] + ":" + time[1] + "\nPaid: " + this.paid + "\n");
	}
	
	// Get + set methods
	public static int getSoldLength()
	{
		return soldItems.length;
	}
	
	public static int getSoldNum(int index)
	{
		return soldNum[index];
	}
	
	public static String getSoldItems(int index)
	{
		return soldItems[index];
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		 this.content = content;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		 this.name = name;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	public int getSlices()
	{
		return cutIntoSlices;
	}
	
	public void setSlices(int slices)
	{
		this.cutIntoSlices = slices;
	}
	
	public int getTimeHr()
	{
		return time[0];
	}
	
	public void setTimeHr(int hr)
	{
		this.time[0] = hr;
	}
	
	public int getTimeMin()
	{
		return time[1];
	}
	
	public void setTimeMin(int min)
	{
		this.time[1] = min;
	}
	
	public boolean getPaid()
	{
		return paid;
	}
	
	public void setPaid(boolean paid)
	{
		this.paid = paid;
	}
	
	public String getStatus()
	{
		return this.status;
	}
	
	public void setStatus (int status)
	{
		 this.status = statusList[status];
	}
	public static String[] getStatusList()
	{
		return statusList;
	}
	
	
	// Behaviors 
	public static void printAllOrders(ArrayList<Orders> totalOrders)
	{
		System.out.print(totalOrders);
	}
	
	/*
	public static void Add(ArrayList<Orders> totalOrders, Orders order)
	{
		totalOrders.add(order);
	}
	
	public static void Cancel(ArrayList<Orders> totalOrders, String name)
	{
		for (int i = 0; i < totalOrders.size(); i++)
		{	
			if (totalOrders.get(i).getName().equalsIgnoreCase(name))
			{
				totalOrders.remove(i);
			}
		}
	}
	*/
	
	public static void organize(ArrayList<Orders> totalOrders, Orders order, int hr, int min)
	{
		double timeInMin = hr * 60 + min;
		boolean flag = false;
		
		for (int i = 0; i < totalOrders.size(); i++)
		{
			if (timeInMin < totalOrders.get(i).getTimeHr() * 60 + totalOrders.get(i).getTimeMin())
			{
				totalOrders.add(i, order);
				index = i;
				flag = true;
				break;
			}
		}
		
		if (!flag)
		{
			totalOrders.add(order);
		}
		
	}
	
	public static void changeStock(String content, String status) throws IOException
	{
		if (status.equals("Baking(unflipped)"))
		{
			switch (content)
			{
			case "New Yorker":
				Stock.redSauce --;
				Stock.pepperoni --;
				Stock.cheese --;
				break;
			case "Plain Cheese":
				Stock.redSauce --;
				Stock.cheese --;
				break;
			case "Meat Lovers":
				Stock.redSauce --;
				Stock.pepperoni --;
				Stock.cheese --;
				Stock.sausage --;
				Stock.bacon --;
				Stock.cacciatore --;
				break;
			case "Brie And Prosciutto":
				Stock.cheese --;
				Stock.prosciutto --;
				Stock.brie --;
				Stock.carmOnion --;
				break;
			case "Mediterraneo":
				Stock.cheese --;
				Stock.grilledVeg --;
				Stock.sunDriedToma --;
				Stock.goatCheese --;
				break;
			case "Loaded Hawaiian":
				Stock.redSauce --;
				Stock.pepperoni --;
				Stock.cheese --;
				Stock.bacon --;
				Stock.pineapples--;
				break;
			case "Sweet Stinger":
				Stock.redSauce --;
				Stock.cheese --;
				Stock.miniPepperoni --;
				Stock.hotPepper --;
				break;
			case "Black Truffle Mushroom":
				Stock.whiteSauce --;
				Stock.cheese --;
				Stock.mushroom --;
				break;
			case "Other":
				// Buttons showing all ingredients and the worker can choose which ones are needed
				break;
			}
		}
		
		if (status.equals("Finished"))
		{
			switch (content)
			{
			case "New Yorker":
				soldNum[0]++;
				break;
			case "Plain Cheese":
				soldNum[1]++;
				break;
			case "Meat Lovers":
				soldNum[2]++;
				break;
			case "Brie And Prosciutto":
				soldNum[3]++;
				break;
			case "Mediterraneo":
				soldNum[4]++;
				break;
			case "Loaded Hawaiian":
				soldNum[5]++;
				break;
			case "Sweet Stinger":
				soldNum[6]++;
				break;
			case "Black Truffle Mushroom":
				soldNum[7]++;
				break;
			case "Other":
				soldNum[8]++;
				break;
			}
			recordStats(soldItems, soldNum, writer2);
		}
		
		Stock.ingredient.set(0, Stock.redSauce);
		Stock.ingredient.set(1, Stock.whiteSauce);
		Stock.ingredient.set(2, Stock.cheese);
		Stock.ingredient.set(3, Stock.goatCheese);
		Stock.ingredient.set(4, Stock.pepperoni);
		Stock.ingredient.set(5, Stock.miniPepperoni);
		Stock.ingredient.set(6, Stock.sausage);
		Stock.ingredient.set(7, Stock.cacciatore);
		Stock.ingredient.set(8, Stock.bacon);
		Stock.ingredient.set(9, Stock.pineapples);
		Stock.ingredient.set(10, Stock.prosciutto);
		Stock.ingredient.set(11, Stock.brie);
		Stock.ingredient.set(12, Stock.carmOnion);
		Stock.ingredient.set(13, Stock.mushroom);
		Stock.ingredient.set(14, Stock.grilledVeg);
		Stock.ingredient.set(15, Stock.sunDriedToma);
		Stock.ingredient.set(16, Stock.hotPepper);
	}
	
	public static void recordStats(String[] soldItems, int[] soldNum, FileWriter writer2) throws IOException
	{
		for (int i = 0; i < soldItems.length; i++)
		{
			writer2.write(soldItems[i] + " " + soldNum[i] + "\n");
			if (soldNum[i] > bestSeller)
			{
				bestSeller = soldNum[i];
				bestSellerName = soldItems[i];
			}
		}
		writer2.write(bestSellerName + " " + bestSeller);
		writer2.close();
	}

	
	public static void main (String[] args) 
	{
		
	}
}
