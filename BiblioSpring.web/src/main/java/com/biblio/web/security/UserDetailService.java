package com.biblio.web.security;

import com.biblio.domain.model.RoleDto;
import com.biblio.domain.model.UserDto;
import com.biblio.domain.repository.RoleRepository;
import com.biblio.domain.service.UserService;
import com.biblio.web.model.AuthCreateUser;
import com.biblio.web.model.AuthLoginRequest;
import com.biblio.web.model.AuthResponse;
import com.biblio.web.security.util.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// servicio para consultar al repositorio los datos del usuario, rol y permisos para crear un objeto de tipo UserDetails
// que necesita spring security
@Service
public class UserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserDetailService(UserService userService,RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository=roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder=passwordEncoder;
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

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = authenticate(username,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(username,"User loged successfully",token,true);

    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails==null) throw  new BadCredentialsException("Invalid username or password");

        if(!passwordEncoder.matches(password, userDetails.getPassword())) throw  new BadCredentialsException("Invalid username or password");

        return new UsernamePasswordAuthenticationToken(username,userDetails.getPassword(),userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUser authCreateUser){

        String username = authCreateUser.username();
        String password = authCreateUser.password();
        List<String> roles = authCreateUser.authCreateRoleRequest().roleListName();

        Set<RoleDto> rolesSet= new HashSet<>(roleRepository.findRoleByRoleEnumIn(roles));

        if(rolesSet.isEmpty())
            throw  new IllegalArgumentException("roles not exist!");

        UserDto user = UserDto.builder().
                username(username).
                password(passwordEncoder.encode(password)).
                roles(rolesSet).
                enabled(true).
                accountNoLocked(true).
                accountNoExpired(true).
                credentialNoExpired(true).
                build();

        UserDto userCreated = userService.save(user);


        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(roleDto -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+roleDto.getRoleEnum().name()));
        });

        userCreated.getRoles().stream().flatMap(role->role.getPermissions().stream()).forEach(permissionDto -> {
            authorityList.add(new SimpleGrantedAuthority(permissionDto.getName()));
        });

        Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,authorityList);

        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(userCreated.getUsername(), "User created successfully",token,true);
    }
}
