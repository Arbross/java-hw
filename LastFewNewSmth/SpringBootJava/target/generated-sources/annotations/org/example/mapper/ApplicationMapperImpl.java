package org.example.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.dto.productdto.CreateProductDTO;
import org.example.dto.productdto.ProductDTO;
import org.example.dto.productitemdto.ProductItemDTO;
import org.example.dto.userdto.CreateUserItemDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.Product;
import org.example.entities.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T17:35:58+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public UserItemDTO userToUserItemDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserItemDTO userItemDTO = new UserItemDTO();

        userItemDTO.setId( user.getId() );
        userItemDTO.setEmail( user.getEmail() );
        userItemDTO.setImage( user.getImage() );
        userItemDTO.setPassword( user.getPassword() );
        userItemDTO.setPhoneNumber( user.getPhoneNumber() );
        userItemDTO.setAge( user.getAge() );

        return userItemDTO;
    }

    @Override
    public User userItemDTOToUser(CreateUserItemDTO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setEmail( user.getEmail() );
        user1.setImage( user.getImage() );
        user1.setPassword( user.getPassword() );
        user1.setPhoneNumber( user.getPhoneNumber() );
        user1.setAge( user.getAge() );

        return user1;
    }

    @Override
    public List<UserItemDTO> usersToUserItemDTO_List(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserItemDTO> list = new ArrayList<UserItemDTO>( users.size() );
        for ( User user : users ) {
            list.add( userToUserItemDTO( user ) );
        }

        return list;
    }

    @Override
    public ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( (int) product.getPrice() );
        productDTO.setDescription( product.getDescription() );

        return productDTO;
    }

    @Override
    public Product createProductDTOToProduct(CreateProductDTO product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setName( product.getName() );
        product1.setPrice( product.getPrice() );
        product1.setDescription( product.getDescription() );

        return product1;
    }

    @Override
    public Product productDTOToProduct(ProductDTO product) {
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setId( product.getId() );
        product1.setName( product.getName() );
        product1.setPrice( product.getPrice() );
        product1.setDescription( product.getDescription() );

        return product1;
    }

    @Override
    public List<ProductDTO> productsToProductDTO_List(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductDTO( product ) );
        }

        return list;
    }

    @Override
    public List<ProductItemDTO> productToProductItemDTO_List(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductItemDTO> list = new ArrayList<ProductItemDTO>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductItemDTO( product ) );
        }

        return list;
    }

    protected ProductItemDTO productToProductItemDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductItemDTO productItemDTO = new ProductItemDTO();

        productItemDTO.setId( product.getId() );
        productItemDTO.setName( product.getName() );
        productItemDTO.setPrice( product.getPrice() );

        return productItemDTO;
    }
}
