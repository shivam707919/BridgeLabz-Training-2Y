package com.gla.practice_problem;

import java.util.Scanner;
public class PowerCalculation{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter the Base");
        double Base=scanner.nextDouble();

        System.out.println("Enter the Exponent");
        double Exponent=scanner.nextDouble();

        double Result=Math.pow(Base,Exponent);
        System.out.println("Result is : "+Result);
        scanner.close();
    }
}
