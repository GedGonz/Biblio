package com.biblio.web.security;

import com.biblio.domain.model.UserDto;
import com.biblio.domain.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


// servicio para consultar al repositorio los datos del usuario, rol y permisos para  crear un objeto de tipo UserDetails
// que necesita spring security
@Service
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    public UserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        UserDto user = userService.getByUsername(username);

       user.getRoles().forEach(roleDto -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+roleDto.getRoleEnum().name()));
        });

       user.getRoles().stream().flatMap(role->role.getPermissions().stream()).forEach(permissionDto -> {
                authorityList.add(new SimpleGrantedAuthority(permissionDto.getName()));
            });


        return new User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isCredentialNoExpired(),
                user.isAccountNoLocked(),authorityList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
