package com.pumagia.loscardoscdu.Controller;

import com.pumagia.loscardoscdu.Modelo.Role;
import com.pumagia.loscardoscdu.Modelo.Usuario;
import com.pumagia.loscardoscdu.Service.IRoleService;
import com.pumagia.loscardoscdu.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
   // @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = usuarioService.findALL();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
   // @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> user = usuarioService.findByid(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
  //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {

        Set<Role> roleList = new HashSet<Role>();
        Role readRole;

        //encriptamos contraseña
        usuario.setClave(usuarioService.encriptPassword(usuario.getClave()));

        // Recuperar la Permission/s por su ID
        for (Role role : usuario.getRolesList()){
            readRole = roleService.findById(role.getRole_id()).orElse(null);
            if (readRole != null) {

                //si encuentro, guardo en la lista
                roleList.add(readRole);
            }
        }

        if (!roleList.isEmpty()) {
            usuario.setRolesList(roleList);

            Usuario newUser = usuarioService.save(usuario);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }


}