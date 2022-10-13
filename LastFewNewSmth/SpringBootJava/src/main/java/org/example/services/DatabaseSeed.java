package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.constants.Roles;
import org.example.entities.*;
import org.example.interfaces.IDatabaseSeed;
import org.example.repositories.*;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DatabaseSeed implements IDatabaseSeed {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void Seed() {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword("123456");

        if (this.roleRepository.count() == 0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if (this.userRepository.count() == 0)
        {
            User user = new User();
            user.setEmail("semen@gmail.com");
            user.setPhoneNumber("096-620-7377");
            user.setPassword(encryptedPassword);
            user.setRoles(Arrays.asList(
                    roleRepository.findByName(Roles.Admin)));

            if (this.productRepository.count() == 0)
            {
                Product pants = new Product("Штани", 402, "Для чоловіків");
                Product shirt = new Product("Сорочка", 300, "Для жінок");
                Product hat = new Product("Шляпа", 200, "Для жінок та чловіків");

                if (this.orderRepository.count() == 0) {
                    Order order1 = new Order(new Date());
                    Order order2 = new Order(new Date());
                    Order order3 = new Order(new Date());

                    OrderStatus orderStatus1 = new OrderStatus("Finish");
                    OrderStatus orderStatus2 = new OrderStatus("Done");

                    CartItem cartItem1 = new CartItem(20);
                    CartItem cartItem2 = new CartItem(20);

                    CartItemId cartId1 = new CartItemId();
                    cartId1.setUser(user);
                    cartId1.setProduct(pants);

                    CartItemId cartId2 = new CartItemId();
                    cartId2.setUser(user);
                    cartId2.setProduct(pants);

                    cartItem1.setCartItemId(cartId1);
                    cartItem2.setCartItemId(cartId2);

                    Set<CartItem> cart = new HashSet<>();
                    cart.add(cartItem1);
                    cart.add(cartItem2);

                    OrderItem orderItem1 = new OrderItem(200, 20);
                    OrderItem orderItem2 = new OrderItem(400, 12);

                    productRepository.save(pants);
                    productRepository.save(shirt);
                    productRepository.save(hat);

                    productImageRepository.save(new ProductImage("1.jpg", 1, pants));
                    productImageRepository.save(new ProductImage("2.jpg", 2, pants));
                    productImageRepository.save(new ProductImage("3.jpg", 3, pants));

                    productImageRepository.save(new ProductImage("4.jpg", 1, shirt));
                    productImageRepository.save(new ProductImage("5.jpg", 2, shirt));

                    productImageRepository.save(new ProductImage("6.jpg", 1, hat));
                    productImageRepository.save(new ProductImage("7.jpg", 2, hat));

                    orderItemRepository.save(orderItem1);
                    orderItemRepository.save(orderItem2);

                    orderStatusRepository.save(orderStatus1);
                    orderStatusRepository.save(orderStatus2);

                    orderRepository.save(order1);
                    orderRepository.save(order2);
                    orderRepository.save(order3);

                    userRepository.save(user);

                    cartItemRepository.save(cartItem1);
                    cartItemRepository.save(cartItem2);
                }
            }
        }
    }
}
