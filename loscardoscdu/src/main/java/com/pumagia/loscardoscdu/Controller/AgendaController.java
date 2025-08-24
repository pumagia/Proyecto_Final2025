package com.pumagia.loscardoscdu.Controller;

import com.pumagia.loscardoscdu.Modelo.Agenda;
import com.pumagia.loscardoscdu.Service.IAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agendas")
@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
public class AgendaController {
    @Autowired
    private IAgendaService agendaService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public ResponseEntity<List<Agenda>> obtenerTodasLasAgendas() {
        List<Agenda> agendas = agendaService.buscarTodos();
        if (agendas != null) {
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public ResponseEntity<Agenda> obtenerAgendaPorId(@PathVariable Long id) {
        Agenda agenda = agendaService.buscarPorId(id);
        if (agenda != null) {
            return new ResponseEntity<>(agenda, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public ResponseEntity<Agenda> crearAgenda(@RequestBody Agenda agenda) {
        agendaService.guardar(agenda);
        return new ResponseEntity<>(agenda, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public ResponseEntity<Void> eliminarAgenda(@PathVariable Long id) {
        agendaService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}