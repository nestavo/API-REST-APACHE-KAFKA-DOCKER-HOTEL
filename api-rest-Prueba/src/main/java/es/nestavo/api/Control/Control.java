package es.nestavo.api.Control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import es.nestavo.api.Interface.Modelo2Interface;
import es.nestavo.api.Interface.ModeloInter;
import es.nestavo.api.modelo.Modelo;
import es.nestavo.api.modelo.Modelo2;

@RestController
@RequestMapping("/hotel")
public class Control {

	@Autowired
	private ModeloInter Irep;

	@Autowired
	private Modelo2Interface Irep2;

	@Autowired
	private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

	@PostMapping("/crearRes")
	public Modelo CrearReserva(@RequestBody Modelo res) {

		return Irep.save(res);
	}

	@GetMapping("/listarRese")
	@CrossOrigin(origins = "http://127.0.0.1:3000")
	public List<Modelo> listarResrva() {
		return Irep.findAll();

	}

	@GetMapping("/searchbyseid/{searchId}")
	public Map<String, Object> getModelosBySearchId(@PathVariable String searchId) {
		List<Modelo> modelos = Irep.findBySearchId(searchId);
		Map<String, Object> response = new HashMap<>();

		if (modelos.isEmpty()) {
			response.put("message", "No se encontraron modelos con el searchId proporcionado");
		} else {
			Modelo modeloUnico = modelos.get(0); // Tomar el primer modelo encontrado
			int vecesRepetido = modelos.size(); // Obtener la cantidad de veces que se repitió

			response.put("Petición", modeloUnico);
			response.put("count", vecesRepetido);
		}

		return response;
	}

	@GetMapping("/searchId/todos")
	public Map<String, Map<String, Object>> contarSearchIdRepetidos() {
		List<Modelo> modelos = Irep.findAll(); // Suponiendo que obtienes todos los modelos

		Map<String, Long> conteoSearchId = modelos.stream()
				.collect(Collectors.groupingBy(Modelo::getSearchId, Collectors.counting()));

		Map<String, Map<String, Object>> resultado = new HashMap<>();
		for (Modelo modelo : modelos) {
			if (conteoSearchId.containsKey(modelo.getSearchId())) {
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("ages", modelo.getAges());
				parametros.put("searchId", modelo.getSearchId());
				parametros.put("hotelId", modelo.getHotelId());
				parametros.put("checkIn", modelo.getCheckIn());
				parametros.put("checkOut", modelo.getCheckOut());

				parametros.put("count", conteoSearchId.get(modelo.getSearchId()));

				kafkaTemplate.send("mi-topico", modelo.getSearchId(), parametros);
				resultado.put(modelo.getSearchId(), parametros);
			}

		}

		return resultado;
	}

	@PostMapping("/api/modelo2")
	public Modelo2 createModelo2(@RequestBody Modelo2 modelo2) {
		return Irep2.save(modelo2);
	}

	@GetMapping("/listarRese2")
	@CrossOrigin(origins = "http://127.0.0.1:3000")
	public List<Modelo2> listarResrva2() {
		return Irep2.findAll();

	}
}
