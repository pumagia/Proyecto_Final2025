package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Agenda;
import com.pumagia.loscardoscdu.Repository.IAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaService implements IAgendaService {
    @Autowired
    private IAgendaRepository agendaRepository;

    @Override
    public List<Agenda> buscarTodos() {
        return agendaRepository.findAll();
    }

    @Override
    public Agenda buscarPorId(Long id) {
        return agendaRepository.findById(id).orElse(null);
    }

    @Override
    public void guardar(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    @Override
    public void eliminarPorId(Long id) {
        agendaRepository.deleteById(id);
    }
}
