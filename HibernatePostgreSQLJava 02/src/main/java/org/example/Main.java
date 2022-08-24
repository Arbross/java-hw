package org.example;

import data.HibernateSession;
import entities.*;
import org.hibernate.Session;
import services.CategoryService;
import services.ProductService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in, "UTF-8");

        Session session = HibernateSession.getSessionFactory().openSession();

        CategoryService categoryService = new CategoryService(session);
        ProductService productService = new ProductService(session);

        while (true)
        {
            System.out.println("1 - Working Navigation Properties");
            System.out.println("2 - Working CRUD");
            int choose = Integer.parseInt(in.nextLine());

            if (choose == 1)
            {
                System.out.println("1 - Add Role To User");
                System.out.println("2 - Add Cart To User");
                int number = Integer.parseInt(in.nextLine());

                if (number == 1)
                {
                    Role role = new Role();
                    role.setName("Administrator");
                    session.save(role);

                    User user = new User();
                    user.setEmail("email");
                    user.setUserName("username");
                    user.setPasswordHash("password");

                    ArrayList<Role> listRoles = new ArrayList<Role>();
                    listRoles.add(role);
                    user.setRoles(listRoles);
                    session.save(user);

                    session.beginTransaction().commit();
                }
                else if (number == 2)
                {
                    Product product = new Product();
                    product.setName("sdf");
                    product.setPrice(34);

                    Category category = new Category();
                    category.setName("fgh");
                    category.setImage("gfh");
                    session.save(category);
                    product.setCategory(category);

                    session.save(product);

                    User user = new User();
                    user.setEmail("email");
                    user.setUserName("username");
                    user.setPasswordHash("password");
                    session.save(user);

                    CartItem cartItem = new CartItem();
                    cartItem.setQuantity(5);
                    cartItem.setProduct(product);
                    cartItem.setUser(user);
                    session.save(cartItem);

                    session.beginTransaction().commit();
                }
            }
            else if (choose == 2)
            {
                System.out.println("Category");
                System.out.println("1 - Get");
                System.out.println("2 - Create");
                System.out.println("3 - Delete");
                System.out.println("4 - Update");
                System.out.println("Product");
                System.out.println("5 - Get");
                System.out.println("6 - Create");
                System.out.println("7 - Delete");
                System.out.println("8 - Update");
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
                else if (number == 5)
                {
                    for (var item : productService.Get())
                    {
                        System.out.println("------------------\n" + "Id : " + item.getId() + "\nName : " + item.getName() + "\nPrice : " + item.getPrice() + "\nDescription : " + item.getDescription() + "\nCategory Name : " + item.getCategory().getName());
                    }
                }
                else if (number == 6)
                {
                    System.out.println("------------------");
                    System.out.print("Enter name : ");
                    String name = in.nextLine();
                    System.out.print("Enter price : ");
                    double price = Double.parseDouble(in.nextLine());
                    System.out.print("Enter description : ");
                    String description = in.nextLine();
                    System.out.print("Enter category id : ");
                    int categoryId = Integer.parseInt(in.nextLine());

                    productService.Create(new Product(name, price, description, categoryService.findRecordById(categoryId)));
                }
                else if (number == 7)
                {
                    System.out.println("------------------");
                    System.out.print("Enter id : ");
                    int id = Integer.parseInt(in.nextLine());

                    productService.Delete(id);
                }
                else if (number == 8)
                {
                    System.out.println("------------------");
                    System.out.print("Enter id : ");
                    int id = Integer.parseInt(in.nextLine());
                    System.out.print("Enter name : ");
                    String name = in.nextLine();
                    System.out.print("Enter price : ");
                    double price = Double.parseDouble(in.nextLine());
                    System.out.print("Enter description : ");
                    String description = in.nextLine();
                    System.out.print("Enter category id : ");
                    int categoryId = Integer.parseInt(in.nextLine());

                    productService.Update(new Product(id, name, price, description, categoryService.findRecordById(categoryId)));
                }
                else {
                    session.close();
                }
            }
        }
    }
}