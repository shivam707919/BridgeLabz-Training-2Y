package com.gla.practice_problem;

import java.util.Scanner;
public class SimpleInterest{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter The Principle");
        double Principle=scanner.nextDouble();

        System.out.println("Enter The Interest Rate");
        double Rate=scanner.nextDouble();

        System.out.println("Enter The Time");
        double Time=scanner.nextDouble();

        double SimpleInterest=(Principle*Rate*Time)/100;

        System.out.println("Simple Interest is : "+SimpleInterest);

        scanner.close();
    }
}
