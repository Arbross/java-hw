package org.example.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.dto.userdto.CreateUserItemDTO;
import org.example.dto.userdto.UserItemDTO;
import org.example.entities.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-27T12:57:59+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
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
}
