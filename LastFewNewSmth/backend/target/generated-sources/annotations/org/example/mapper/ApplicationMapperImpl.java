package org.example.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.DTO.UserDTO.UserCreateDTO;
import org.example.DTO.UserDTO.UserItemDTO;
import org.example.DTO.order.OrderItemDTO;
import org.example.DTO.product.ProductItemDTO;
import org.example.entities.OrderEntity;
import org.example.entities.ProductEntity;
import org.example.entities.UserEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-13T17:44:01+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class ApplicationMapperImpl implements ApplicationMapper {

    @Override
    public UserItemDTO userItemDTO(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserItemDTO userItemDTO = new UserItemDTO();

        userItemDTO.setId( user.getId() );
        userItemDTO.setEmail( user.getEmail() );
        userItemDTO.setPhone( user.getPhone() );
        userItemDTO.setImage( user.getImage() );
        userItemDTO.setAge( user.getAge() );

        return userItemDTO;
    }

    @Override
    public List<UserItemDTO> usersItemDTO_List(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserItemDTO> list = new ArrayList<UserItemDTO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( userItemDTO( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity userCreateDtoToUserEntity(UserCreateDTO user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( user.getEmail() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setImage( user.getImage() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setAge( user.getAge() );

        return userEntity;
    }

    @Override
    public List<ProductItemDTO> productToProductItemDTO_List(List<ProductEntity> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductItemDTO> list = new ArrayList<ProductItemDTO>( products.size() );
        for ( ProductEntity productEntity : products ) {
            list.add( productEntityToProductItemDTO( productEntity ) );
        }

        return list;
    }

    @Override
    public List<OrderItemDTO> orderToOrderItemDTO_List(List<OrderEntity> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orders.size() );
        for ( OrderEntity orderEntity : orders ) {
            list.add( orderEntityToOrderItemDTO( orderEntity ) );
        }

        return list;
    }

    protected ProductItemDTO productEntityToProductItemDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductItemDTO productItemDTO = new ProductItemDTO();

        productItemDTO.setId( productEntity.getId() );
        productItemDTO.setName( productEntity.getName() );
        productItemDTO.setPrice( productEntity.getPrice() );

        return productItemDTO;
    }

    protected OrderItemDTO orderEntityToOrderItemDTO(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId( orderEntity.getId() );
        orderItemDTO.setDateCreated( orderEntity.getDateCreated() );

        return orderItemDTO;
    }
}
