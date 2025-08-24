package com.pumagia.loscardoscdu.Controller;

import com.pumagia.loscardoscdu.Modelo.Comentario;
import com.pumagia.loscardoscdu.Modelo.Usuario;
import com.pumagia.loscardoscdu.Service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {
    @Autowired
    private IComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> obtenerTodosLosComentarios() {
        List<Comentario> comentarios = comentarioService.buscarTodos();
        // Verifica si la lista de comentarios no es nula
        if (comentarios != null) {
            return new ResponseEntity<>(comentarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        Comentario comentario = comentarioService.buscarPorId(id);
        if (comentario != null) {
            return new ResponseEntity<>(comentario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    // Permite a los usuarios con rol ADMIN o USER crear un comentario
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        comentarioService.guardar(comentario);
        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}