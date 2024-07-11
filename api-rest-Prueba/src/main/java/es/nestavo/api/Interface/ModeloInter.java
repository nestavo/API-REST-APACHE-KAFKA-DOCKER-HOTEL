package es.nestavo.api.Interface;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.nestavo.api.modelo.Modelo;

public interface ModeloInter extends MongoRepository<Modelo, String> {

	List<Modelo> findBySearchId(String searchId);

}
