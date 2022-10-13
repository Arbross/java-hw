package org.example.mapper;

import org.example.dto.productdto.CreateProductDTO;
import org.example.dto.productdto.ProductDTO;
import org.example.dto.productitemdto.ProductItemDTO;
import org.example.dto.userdto.CreateUserItemDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.Product;
import org.example.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    UserItemDTO userToUserItemDTO(User user);
    User userItemDTOToUser(CreateUserItemDTO user);
    List<UserItemDTO> usersToUserItemDTO_List(List<User> users);

    ProductDTO productToProductDTO(Product product);
    Product createProductDTOToProduct(CreateProductDTO product);
    Product productDTOToProduct(ProductDTO product);
    List<ProductDTO> productsToProductDTO_List(List<Product> products);

    List<ProductItemDTO> productToProductItemDTO_List(List<Product> products);
}
