package com.gla.practice_problem;

import java.util.Scanner;
public class VolumeOfCylinder{
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter The Radius");
        double Radius=scanner.nextDouble();

        System.out.println("Enter The Height");
        double Height=scanner.nextDouble();

        double Volume=(22.0/7)*Radius*Radius*Height;
        System.out.println("Volume of Cyclinder is : "+Volume);
        scanner.close();
    }
}
