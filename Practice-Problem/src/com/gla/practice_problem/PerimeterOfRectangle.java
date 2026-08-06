package com.gla.practice_problem;

import java.util.Scanner;
public class PerimeterOfRectangle{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Length");
        double Length=scanner.nextDouble();

        System.out.println("Enter the Width");
        double Width=scanner.nextDouble();

        double Perimeter=2*(Length+Width);

        System.out.println("Perimeter of Rectangle of Length "+Length+" and of Width "+Width+" is : "+Perimeter);
        scanner.close();
    }
}
