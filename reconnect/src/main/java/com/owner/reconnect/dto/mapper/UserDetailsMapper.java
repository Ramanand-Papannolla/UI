package com.owner.reconnect.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.owner.reconnect.dto.UserDetailsDto;
import com.owner.reconnect.entities.Roles;
import com.owner.reconnect.entities.UserDetails;

@Mapper
public interface UserDetailsMapper {

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "userName", source = "userName"),
			@Mapping(target = "firstName", source = "firstName"),
			@Mapping(target = "lastName", source = "lastName"),
			@Mapping(target = "email", source = "emailid"),
			@Mapping(target = "role", source = "roles", qualifiedByName = "rolesDto"),
			@Mapping(target = "connected", source = "connected"),
			@Mapping(target = "password", ignore = true) })
	UserDetailsDto mapToUserDetailsDto(UserDetails userDtls);

	@Named("rolesDto")
	default List<String> getRoles(Set<Roles> authorities) {
		List<String> roles = new ArrayList<>();
		for (Roles grantedAuthority : authorities) {
			roles.add(grantedAuthority.getRoleName());
		}
		return roles;
	}

	List<UserDetailsDto> mapToUserDetailsDtos(List<UserDetails> list);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(target = "userName", source = "userName"),
			@Mapping(target = "firstName", source = "firstName"),
			@Mapping(target = "lastName", source = "lastName"),
			@Mapping(target = "emailid", source = "email"),
			@Mapping(target = "password", source = "password", qualifiedByName = "passwordencod") })
	UserDetails mapToUserDetails(UserDetailsDto userDetailsDto);

	@Named("passwordencod")
	default String generateBCryptPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

}