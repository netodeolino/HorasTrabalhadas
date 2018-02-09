package com.horas.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horas.model.Hora;

@Repository
@Transactional
public interface HoraRepository extends JpaRepository<Hora, Long> {

}
