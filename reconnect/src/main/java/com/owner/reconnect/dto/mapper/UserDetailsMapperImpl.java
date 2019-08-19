package com.owner.reconnect.dto.mapper;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.entities.UserDetails;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

    @Override
    public UserDetailsDto mapToUserDetailsDto(UserDetails userDtls) {
        if ( userDtls == null ) {
            return null;
        }

        UserDetailsDto userDetailsDto = new UserDetailsDto();

        userDetailsDto.setConnected( userDtls.getConnected() );
        userDetailsDto.setFirstName( userDtls.getFirstName() );
        userDetailsDto.setLastName( userDtls.getLastName() );
        userDetailsDto.setRole( getRoles( userDtls.getRoles() ) );
        userDetailsDto.setId( userDtls.getId() );
        userDetailsDto.setUserName( userDtls.getUserName() );
        userDetailsDto.setEmail( userDtls.getEmailid() );

        return userDetailsDto;
    }

    @Override
    public List<UserDetailsDto> mapToUserDetailsDtos(List<UserDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDetailsDto> list1 = new ArrayList<UserDetailsDto>( list.size() );
        for ( UserDetails userDetails : list ) {
            list1.add( mapToUserDetailsDto( userDetails ) );
        }

        return list1;
    }

    @Override
    public UserDetails mapToUserDetails(UserDetailsDto userDetailsDto) {
        if ( userDetailsDto == null ) {
            return null;
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setFirstName( userDetailsDto.getFirstName() );
        userDetails.setLastName( userDetailsDto.getLastName() );
        userDetails.setPassword( generateBCryptPassword( userDetailsDto.getPassword() ) );
        userDetails.setEmailid( userDetailsDto.getEmail() );
        userDetails.setUserName( userDetailsDto.getUserName() );
        if ( userDetailsDto.getConnected() != null ) {
            userDetails.setConnected( userDetailsDto.getConnected() );
        }

        return userDetails;
    }
}
