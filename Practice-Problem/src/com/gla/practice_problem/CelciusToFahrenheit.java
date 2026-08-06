package com.gla.practice_problem;

import java.util.Scanner;
public class CelciusToFahrenheit{
    public static void main(String[] Args)
    {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter The Temperature in celcius");
        double Celcius=scanner.nextDouble();

        double Fahrenheit=(Celcius*9/5)+32;
        System.out.println("Temperature in Fahrenheit is "+Fahrenheit);
        scanner.close();
    }
}