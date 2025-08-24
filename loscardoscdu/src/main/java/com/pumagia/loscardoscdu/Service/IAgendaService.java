package com.pumagia.loscardoscdu.Service;

import com.pumagia.loscardoscdu.Modelo.Agenda;
import java.util.List;

public interface IAgendaService {
    List<Agenda> buscarTodos();
    Agenda buscarPorId(Long id);
    void guardar(Agenda agenda);
    void eliminarPorId(Long id);
}
