package com.pumagia.loscardoscdu.Controller;

import com.pumagia.loscardoscdu.Modelo.Permission;
import com.pumagia.loscardoscdu.Modelo.Role;
import com.pumagia.loscardoscdu.Service.IPermissionService;
import com.pumagia.loscardoscdu.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permiService;

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
 //   @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping

//    @PreAuthorize("hasRole('ADMIN' and hasPermission('CREATE'))")

    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permiList = new HashSet<Permission>();
        Permission readPermission;
        for (Permission per:role.getPermissionsList()) {
            readPermission = permiService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                permiList.add(readPermission);
            }
        }
        role.setPermissionsList(permiList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }

    //Agregamos end-point de UPDATE
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {

        Role rol = roleService.findById(id).orElse(null);
        if (rol!=null) {
            rol = role;
        }

        roleService.update(rol);
        return ResponseEntity.ok(rol);

    }


}
