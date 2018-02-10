package com.horas.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horas.model.Hora;

@Repository
@Transactional
public interface HoraRepository extends JpaRepository<Hora, Long> {

	List<Hora> findByDataBetweenAndUsuarioId(Date date1, Date date2, Long id);
}
