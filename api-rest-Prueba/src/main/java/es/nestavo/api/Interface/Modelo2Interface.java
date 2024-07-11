package es.nestavo.api.Interface;

import org.springframework.data.mongodb.repository.MongoRepository;


import es.nestavo.api.modelo.Modelo2;

public interface Modelo2Interface extends MongoRepository<Modelo2, String>   {

	void save(String string);

}
