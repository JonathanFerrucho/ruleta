package com.ruleta.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ruleta.app.models.Ruleta;

@Repository
public interface RuletaRepository extends CrudRepository<Ruleta, Long>{
	
	@Query("SELECT e FROM Ruleta")
	List<Ruleta> obtenerTodos();
}
