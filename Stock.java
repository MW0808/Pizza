package PizzaPack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stock {
	// GUI: combo box, After you click the desire ingredient, the user can choose to either add or reduce the quantity
	
	// !When ordering and one of its ingredients is out of stock, a warning pops up
	
	// Count how much ingredients each pizza will use and deduct the corresponding amount and type of ingredients
	// when the status changes to "finished". When the amount of one pack/box is used up, the stock -1
	
	// If the pizza require this ingredient, it keeps count of the amount of pizza that can be made
	static ArrayList<Integer> ingredient = new ArrayList<Integer>();
	static ArrayList<String> ingredientName = new ArrayList<String>();
	FileWriter writer = new FileWriter("Stock.txt");
	Scanner sc = new Scanner(new File("Stock.txt"));
	
	static int redSauce = 30; // 20
	static int whiteSauce = 15; // 10
	static int cheese = 16; // 30
	static int goatCheese = 25; // 10
	static int pepperoni = 50; // 10
	static int miniPepperoni = 50; // 30
	static int sausage = 30; // 15
	static int cacciatore = 25; // 8 
	static int bacon = 25; // 10
	static int pineapples = 15; // 3
	static int prosciutto = 20; // 7
	static int brie = 11; // 5
	static int carmOnion = 16; // 15
	static int mushroom = 20; // 7
	static int grilledVeg = 18; // 10
	static int sunDriedToma = 18; // 15
	static int hotPepper = 50; // 1
	
	//Has buttons for the user to press and increase each according ingredients' stock + quantity
	
	Stock() throws IOException
	{
		ingredientName.add("redSauce");
		ingredientName.add("whiteSauce");
		ingredientName.add("cheese");
		ingredientName.add("goatCheese");
		ingredientName.add("pepperoni");
		ingredientName.add("miniPepperoni");
		ingredientName.add("sausage");
		ingredientName.add("cacciatore");
		ingredientName.add("bacon");
		ingredientName.add("pineapples");
		ingredientName.add("prosciutto");
		ingredientName.add("brie");
		ingredientName.add("carmOnion");
		ingredientName.add("mushroom");
		ingredientName.add("grilledVeg");
		ingredientName.add("sunDriedToma");
		ingredientName.add("hotPepper");
		
		
		ingredient.add(redSauce);
		ingredient.add(whiteSauce);
		ingredient.add(cheese);
		ingredient.add(goatCheese);
		ingredient.add(pepperoni);
		ingredient.add(miniPepperoni);
		ingredient.add(sausage);
		ingredient.add(cacciatore);
		ingredient.add(bacon);
		ingredient.add(pineapples);
		ingredient.add(prosciutto);
		ingredient.add(brie);
		ingredient.add(carmOnion);
		ingredient.add(mushroom);
		ingredient.add(grilledVeg);
		ingredient.add(sunDriedToma);
		ingredient.add(hotPepper);
		
		for (int i = 0; i < ingredientName.size(); i++)
		{
			writer.write("\n" + ingredientName.get(i) + " " + ingredient.get(i));
		}
		writer.close();
	}
	
	public static void check()
	{
		for (int i = 0; i < ingredient.size(); i++)
		{
			if (ingredient.get(i) <= 10)
			{
				System.out.print(ingredient.get(i) + " almost out of stock warning! (10 left)");
			}
		}
	}
	
	public static void addStock(String type, int num)
	{
			
		switch (type)
		{
		case "redSauce":
			num *= 20;
			redSauce += num;
			break;
		case "whiteSauce":
			num *= 10;
			whiteSauce += num;
			break;
		case "cheese":
			num *= 30;
			cheese += num;
			break;
		case "goatCheese":
			num *= 10;
			goatCheese += num;
			break;
		case "pepperoni":
			num *= 10;
			pepperoni += num;
			break;
		case "miniPepperoni":
			num *= 30;
			miniPepperoni += num;
			break;
		case "sausage":
			num *= 15;
			sausage += num;
			break;
		case "cacciatore":
			num *= 8;
			cacciatore += num;
			break;
		case "bacon":
			num *= 10;
			bacon += num;
			break;
		case "pineapples":
			num *= 3;
			pineapples += num;
			break;
		case "prosciutto":
			num *= 7;
			prosciutto += num;
			break;
		case "brie":
			num *= 5;
			brie += num;
			break;
		case "carmOnion":
			num *= 15;
			carmOnion += num;
			break;
		case "mushroom":
			num *= 7;
			mushroom += num;
			break;
		case "grilledVeg":
			num *= 10;
			grilledVeg += num;
			break;
		case "sunDriedToma":
			num *= 15;
			sunDriedToma += num;
			break;
		case "hotPepper":
			num *= 1;
			hotPepper += num;
			break;
		}
		
		ingredient.set(0, redSauce);
		ingredient.set(1, whiteSauce);
		ingredient.set(2, cheese);
		ingredient.set(3, goatCheese);
		ingredient.set(4, pepperoni);
		ingredient.set(5, miniPepperoni);
		ingredient.set(6, sausage);
		ingredient.set(7, cacciatore);
		ingredient.set(8, bacon);
		ingredient.set(9, pineapples);
		ingredient.set(10, prosciutto);
		ingredient.set(11, brie);
		ingredient.set(12, carmOnion);
		ingredient.set(13, mushroom);
		ingredient.set(14, grilledVeg);
		ingredient.set(15, sunDriedToma);
		ingredient.set(16, hotPepper);
	}
	
	
	public static void reduceStock(String type, int num) 
	{
		switch (type)
		
		{
		case "redSauce":
			num *= 20;
			redSauce -= num;
			break;
		case "whiteSauce":
			num *= 10;
			whiteSauce -= num;
			break;
		case "cheese":
			num *= 30;
			cheese -= num;
			break;
		case "goatCheese":
			num *= 10;
			goatCheese -= num;
			break;
		case "pepperoni":
			num *= 10;
			pepperoni -= num;
			break;
		case "miniPepperoni":
			num *= 30;
			miniPepperoni -= num;
			break;
		case "sausage":
			num *= 15;
			sausage -= num;
			break;
		case "cacciatore":
			num *= 8;
			cacciatore -= num;
			break;
		case "bacon":
			num *= 10;
			bacon -= num;
			break;
		case "pineapples":
			num *= 3;
			pineapples -= num;
			break;
		case "prosciutto":
			num *= 7;
			prosciutto -= num;
			break;
		case "brie":
			num *= 5;
			brie -= num;
			break;
		case "carmOnion":
			num *= 15;
			carmOnion -= num;
			break;
		case "mushroom":
			num *= 7;
			mushroom -= num;
			break;
		case "grilledVeg":
			num *= 10;
			grilledVeg -= num;
			break;
		case "sunDriedToma":
			num *= 15;
			sunDriedToma -= num;
			break;
		case "hotPepper":
			num *= 1;
			hotPepper -= num;
			break;
		}
		
		ingredient.set(0, redSauce);
		ingredient.set(1, whiteSauce);
		ingredient.set(2, cheese);
		ingredient.set(3, goatCheese);
		ingredient.set(4, pepperoni);
		ingredient.set(5, miniPepperoni);
		ingredient.set(6, sausage);
		ingredient.set(7, cacciatore);
		ingredient.set(8, bacon);
		ingredient.set(9, pineapples);
		ingredient.set(10, prosciutto);
		ingredient.set(11, brie);
		ingredient.set(12, carmOnion);
		ingredient.set(13, mushroom);
		ingredient.set(14, grilledVeg);
		ingredient.set(15, sunDriedToma);
		ingredient.set(16, hotPepper);
	}
	
}
