package org.example;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> askedEquations = new ArrayList<String>();
    public static int rate = 0;
    public static int questionsCount;

    public static void ClearValues()
    {
        askedEquations = new ArrayList<String>();
        rate = 0;
    }

    public static void GenerateAndCalculateMathematicalEquation(Random rnd, Scanner in, int index)
    {
        if (index == questionsCount + 1)
        {
            if (rate == questionsCount)
            {
                System.out.println("Congratulations you won! Your mark is A (" + rate + "/" + questionsCount +")!");
            }
            else {
                if (rate > questionsCount / 2)
                {
                    System.out.println("Your mark is B (" + rate + "/" + questionsCount +")! Try again.");
                }
                else if (rate == 0)
                {
                    System.out.println("Your mark is D (" + rate + "/" + questionsCount +")! Try again.");
                }
                else if (rate < questionsCount / 2)
                {
                    System.out.println("Your mark is C (" + rate + "/" + questionsCount +")! Try again.");
                }
            }

            ClearValues();
            return;
        }

        while (true)
        {
            int a = rnd.nextInt(2, 9);
            int b = rnd.nextInt(2, 9);
            int answer = a * b;

            String equation = String.valueOf(a) + " * " + String.valueOf(b) + " = ";
            String reverseEquation = String.valueOf(b) + " * " + String.valueOf(a) + " = ";
            if (askedEquations.contains(equation) || askedEquations.contains(reverseEquation))
            {
                continue;
            }

            askedEquations.add(equation);

            System.out.print(index + ". " + equation);
            int userAnswer = Integer.parseInt(in.nextLine());

            if (userAnswer == answer)
            {
                rate += 1;
            }
            break;
        }
    }

    public static void AskQuestionsCount(Scanner in)
    {
        System.out.print("Enter number of question you want : ");
        questionsCount = Integer.parseInt(in.nextLine());
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in, "UTF-8");

        Random rnd = new Random();
        while (true)
        {
            AskQuestionsCount(in);
            for (int i = 1; i <= questionsCount + 1; i++)
            {
                GenerateAndCalculateMathematicalEquation(rnd, in, i);
            }
        }
    }
}