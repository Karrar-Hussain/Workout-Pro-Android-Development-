package com.example.unique.myapplication.control;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.unique.myapplication.model.Diet_Tbl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unique on 4/28/2016.
 */
public class Diet_Scheduler {

    //Diet_Tbl diet_tbl;
    //Cursor cr;

    public ArrayList<String> makeSchedule(List<String> food_list) {
        List fruits, vegs, meets, drinks, fastfood;
        List foodname, foodtype, foodunit, foodcalory;
        foodname = new ArrayList();
        foodtype = new ArrayList();
        foodunit = new ArrayList();
        foodcalory = new ArrayList();

        fruits = new ArrayList();
        vegs = new ArrayList();
        meets = new ArrayList();
        drinks = new ArrayList();
        fastfood = new ArrayList();

        for (int i = 0; i < food_list.size(); i++) {
            String[] foods = food_list.get(i).split("\t");

            if (foods.length == 4) {
                foodname.add(foods[0]);
                foodtype.add(foods[1]);
                foodunit.add(foods[2]);
                foodcalory.add(foods[3]);
            }
            if (foods[1].equals("Fruits"))
                fruits.add(foods[0]);
            else if (foods[1].equals("Vegs"))
                vegs.add(foods[0]);
            else if (foods[1].equals("Meets"))
                meets.add(foods[0]);
            else if (foods[1].equals("FastFoods"))
                fastfood.add(foods[0]);
            else if (foods[1].equals("Drinks"))
                drinks.add(foods[0]);
        }

        double percentageScale = 0;
        int countNoFood = 5;
        if (!foodtype.contains("Fruits")) {
            percentageScale += 20;
            countNoFood--;//4
        }
        if (!foodtype.contains("Vegs")) {
            percentageScale += 25;
            countNoFood--;
        }
        if (!foodtype.contains("Meets")) {
            percentageScale += 25;
            countNoFood--;
        }
        if (!foodtype.contains("Drinks")) {
            percentageScale += 15;
            countNoFood--;
        }
        if (!foodtype.contains("FastFoods")) {
            percentageScale += 15;
            countNoFood--;
        }
        int NeededCalories = 2381;
        List<Double> CalFoodLists = new ArrayList<Double>();
        int divFruit = 1;
        double percentFruit = 0;
        double calFruit = 0;
        int foodUnit = 0;
        double calCalory = 0, calUnits = 0;
        List<String> calUnitsString = new ArrayList<String>();
        Log.d(">>>>>>>------------------------============================>>>>>>>>>>>>>>>>>>>","Persencetage Scale: "+percentageScale+" ; "+countNoFood);
        if (percentageScale > 0)
            percentageScale = percentageScale / countNoFood;
        for (int i = 0; i < food_list.size(); i++) {
            String[] units = foodunit.get(i).toString().split(" ");
            foodUnit = Integer.parseInt(units[0]);
            if (foodtype.get(i).equals("Fruits")) {
                divFruit = fruits.size();
                percentFruit = (20 + percentageScale) / divFruit;
                calFruit = NeededCalories * percentFruit / 100;
                calCalory = calFruit / Integer.parseInt(foodcalory.get(i).toString());
                CalFoodLists.add(calCalory);
                calUnits = foodUnit * calCalory;
            } else if (foodtype.get(i).equals("Drinks")) {
                divFruit = drinks.size();
                percentFruit = (15 + percentageScale) / divFruit;
                calFruit = NeededCalories * percentFruit / 100;
                calCalory = calFruit / Integer.parseInt(foodcalory.get(i).toString());
                CalFoodLists.add(calCalory);
                calUnits = foodUnit * calCalory;
            } else if (foodtype.get(i).equals("Vegs")) {
                divFruit = vegs.size();
                percentFruit = (25 + percentageScale) / divFruit;
                calFruit = NeededCalories * percentFruit / 100;
                calCalory = calFruit / Integer.parseInt(foodcalory.get(i).toString());
                CalFoodLists.add(calCalory);
                calUnits = foodUnit * calCalory;
                Log.d("---------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ","divFruit"+divFruit+" percentFruit "+percentFruit +" calFruit: "+calFruit+"CalCalory: "+calCalory+" Calcunits: "+calUnits);
            } else if (foodtype.get(i).equals("Meets")) {
                divFruit = meets.size();
                percentFruit = (25 + percentageScale) / divFruit;
                calFruit = NeededCalories * percentFruit / 100;
                calCalory = calFruit / Integer.parseInt(foodcalory.get(i).toString());
                CalFoodLists.add(calCalory);
                calUnits = foodUnit * calCalory;
            } else if (foodtype.get(i).equals("FastFoods")) {
                divFruit = fastfood.size();
                percentFruit = (15 + percentageScale) / divFruit;
                calFruit = NeededCalories * percentFruit / 100;
                calCalory = calFruit / Integer.parseInt(foodcalory.get(i).toString());
                CalFoodLists.add(calCalory);
                calUnits = foodUnit * calCalory;
            }
            String str=Math.round(calUnits)+" ";
            for(int j=1;j<units.length;j++)
            {
                str=str+ " "+units[j];
            }
            calUnitsString.add(str+" of "+foodname.get(i).toString());
        }
        return (ArrayList<String>) calUnitsString;
    }
}
