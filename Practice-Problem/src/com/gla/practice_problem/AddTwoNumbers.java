package com.gla.practice_problem;

import java.util.Scanner;
public class AddTwoNumbers{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter The First Number");
        int number1=scanner.nextInt();

        System.out.println("Enter The second Number");
        int number2=scanner.nextInt();

        int sum=number1+number2;

        System.out.println("sum of digit "+number1+" and "+number2+" is : "+sum);
        scanner.close();
    }
}
