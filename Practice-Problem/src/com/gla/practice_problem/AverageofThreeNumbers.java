package com.gla.practice_problem;

import java.util.Scanner;
class AverageofThreeNumbers{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Number1");
        double Number1=scanner.nextDouble();

        System.out.println("Enter Number2");
        double Number2=scanner.nextDouble();

        System.out.println("Enter Number3");
        double Number3=scanner.nextDouble();

        double Average=(Number1+Number2+Number3)/3;
        System.out.println("Average of Three Numbers is : "+Average);

        scanner.close();
    }
}
