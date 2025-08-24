package com.pumagia.loscardoscdu.Repository;

import com.pumagia.loscardoscdu.Modelo.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendaRepository extends JpaRepository<Agenda, Long> {
}