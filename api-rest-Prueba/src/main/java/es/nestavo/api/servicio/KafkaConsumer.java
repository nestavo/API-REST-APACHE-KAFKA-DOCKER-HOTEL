package es.nestavo.api.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.nestavo.api.modelo.Modelo2;

@Service
public class KafkaConsumer {

	@Autowired
	private RestTemplate restTemplate;

	@KafkaListener(topics = "mi-topico", groupId = "group-id")
	public void consumeMessage(String message) {
		System.out.println("Mensaje recibido: " + message);

		try {
			// Convert the JSON message to Modelo2 object
			ObjectMapper objectMapper = new ObjectMapper();
			Modelo2 modelo2 = objectMapper.readValue(message, Modelo2.class);

			// Post the Modelo2 object to the REST endpoint
			String url = "http://localhost:9061/hotel/api/modelo2";
			Modelo2 response = restTemplate.postForObject(url, modelo2, Modelo2.class);
			System.out.println("Response from POST: " + response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	}

}
