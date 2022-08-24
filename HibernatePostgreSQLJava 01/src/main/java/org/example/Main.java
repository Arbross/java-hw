package org.example;

import data.HibernateSession;
import entities.Category;
import org.hibernate.Session;
import services.CategoryService;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in, "UTF-8");

        Session session = HibernateSession.getSessionFactory().openSession();

        CategoryService categoryService = new CategoryService(session);

        while (true)
        {
            System.out.println("1 - Get");
            System.out.println("2 - Create");
            System.out.println("3 - Delete");
            System.out.println("4 - Update");
            int number = Integer.parseInt(in.nextLine());

            if (number == 1)
            {
                for (var item : categoryService.Get())
                {
                    System.out.println("------------------\n" + "Id : " + item.getId() + "\nName : " + item.getName() + "\nImage : " + item.getImage() + "\n------------------");
                }
            }
            else if (number == 2)
            {
                System.out.println("------------------");
                System.out.print("Enter name : ");
                String name = in.nextLine();
                System.out.print("Enter image : ");
                String image = in.nextLine();

                categoryService.Create(new Category(name, image));
                System.out.println("------------------");
            }
            else if (number == 3)
            {
                System.out.println("------------------");
                System.out.print("Enter id : ");
                int id = Integer.parseInt(in.nextLine());

                categoryService.Delete(id);
                System.out.println("------------------");
            }
            else if (number == 4)
            {
                System.out.println("------------------");
                System.out.print("Enter id : ");
                int id = Integer.parseInt(in.nextLine());
                System.out.print("Enter name : ");
                String name = in.nextLine();
                System.out.print("Enter image : ");
                String image = in.nextLine();

                categoryService.Update(new Category(id, name, image));
                System.out.println("------------------");
            }
            else {
                session.close();
            }
        }
    }
}