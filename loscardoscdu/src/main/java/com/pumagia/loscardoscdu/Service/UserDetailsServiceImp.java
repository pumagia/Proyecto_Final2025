package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.DTO.AuthLoginRequestDTO;
import com.pumagia.loscardoscdu.DTO.AuthResponseDTO;
import com.pumagia.loscardoscdu.Modelo.Usuario;
import com.pumagia.loscardoscdu.Repository.IUsuarioRepository;
import com.pumagia.loscardoscdu.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioRepository userRepo;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {

        //tenemos User sec y necesitamos devolver UserDetails
        //traemos el usuario de la bd
        Usuario userSec = userRepo.findUserEntityByEmail(username)
                .orElseThrow(()-> new   UsernameNotFoundException("El usuario " + username + "no fue encontrado"));

        //con GrantedAuthority Spring Security maneja permisos
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();


        //tomamos roles y los convertimos en SimpleGrantedAuthority para poder agregarlos a la authorityList
        userSec.getRolesList()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoles()))));


        //ahora tenemos que agregar los permisos
        userSec.getRolesList().stream()
                .flatMap(role -> role.getPermissionsList().stream()) //acá recorro los permisos de los roles
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

        //retornamos el usuario en formato Spring Security con los datos de nuestro userSec
        return new User(userSec.getNombreyapellido(),
                userSec.getClave(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorityList);
    }

    public AuthResponseDTO loginUser (AuthLoginRequestDTO authLoginRequest){

        //recuperamos nombre de usuario y contraseña
        String username = authLoginRequest.email();
        String password = authLoginRequest.clave();
        Optional<Usuario> userOptional = userRepo.findUserEntityByEmail(username);
        Usuario userEntity = userOptional.get();
        Long id = userEntity.getId_usuario();


        Authentication authentication = this.authenticate (username, password);
        //si todo sale bien
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken =jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO( id, username, "login ok", accessToken, true);
        return authResponseDTO;

    }

    public Authentication authenticate (String username, String password) {
        //con esto debo buscar el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails==null) {
            throw new BadCredentialsException("Ivalid username or password");
        }
        // si no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

}

