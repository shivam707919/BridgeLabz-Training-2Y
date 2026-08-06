package com.gla.practice_problem;

import java.util.Scanner;
public class KilometerToMiles{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter The Kilometer");
        double Kilometer=scanner.nextDouble();

        double Miles=Kilometer*0.621371;

        System.out.println("Conversion of "+Kilometer+"Kilometer in Miles is : "+Miles);

        scanner.close();
    }
}
