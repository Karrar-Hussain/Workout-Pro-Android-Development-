package com.example.unique.myapplication.control;

/**
 * Created by Unique on 4/27/2016.
 */
public class Calculators {
    public double BMR_Cal(int A,double W,double H,boolean gender)
    {
        // FORMULA FOR BMR:
        //    W = weight in kilograms (weight (lbs)/2.2) =weight in kg
        //    H = height in centimeters (inches x 2.54) =height in cm
        //    A = age in years

        //    gender male=true and female=false
        //Reference
        /*
        
        
Men: BMR=66.47+ (13.75 x W) + (5.0 x H) - (6.75 x A)
Women: BMR=665.09 + (9.56 x W) + (1.84 x H) - (4.67 x A)

         */
        //This Calorie Calculator is based on the Mifflin - St Jeor equation. With this equation, the Basal Metabolic Rate (BMR) is calculated by using the following formula:
        //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
        //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)
        if(gender)
        {
            return 66.47+ (13.75 * W) + (5.0 * H) - (6.75 * A);
        }else
        {
            return 665.09 + (9.56 * W) + (1.84 * H) - (4.67 * A);
        }
    }
    public double BMI_Cal(double W,double H)
    {
        //    W = weight in kilograms (weight (lbs)/2.2) =weight in kg
        //    H/100 = height in centimeters (inches x 2.54) =height in cm/100
        if(H ==0 || W==0)
            return 0;
        H/=100;
        return W / (H * H);
    }

    public Double IBW_Cal(Double bmi, Double H)
    {
        if(H==0 || bmi==0)
            return 0.0;
        //    H/100 = height in centimeters (inches x 2.54) =height in cm/100
        H/=100;
        //returns ideal weight in Kilograms
        return (0.5*bmi+11.5)*H*H ;
    }
}
