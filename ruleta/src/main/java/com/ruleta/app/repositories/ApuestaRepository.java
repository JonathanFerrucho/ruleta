package com.ruleta.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ruleta.app.models.Apuesta;
import com.ruleta.app.models.Ruleta;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta, Long>{

}
