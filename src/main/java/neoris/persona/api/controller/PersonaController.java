package neoris.persona.api.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import neoris.persona.api.entity.Persona;
import neoris.persona.api.request.CreatePersonaRequest;
import neoris.persona.api.response.PersonaResponse;
import neoris.persona.api.service.PersonaService;

@RestController
@RequestMapping("/api/persona/")
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	
	@GetMapping("/getAllPersonas")
	public List<PersonaResponse> getAllPersonas(){
		List<Persona> personaList = personaService.getAllPersonas();
		List<PersonaResponse> personaResponseList = new ArrayList<PersonaResponse>();
		personaList.stream().forEach(persona -> {
			personaResponseList.add(new PersonaResponse(persona));
		});
		return personaResponseList;
	}
	
	@GetMapping("/getByName/{nombre}")
	public Persona getByName(@PathVariable String nombre){
		return personaService.getByName(nombre);
	}
	
	
	@PostMapping("/createPersona")
	public PersonaResponse createPersona (@RequestBody CreatePersonaRequest createPersonaRequest) {
		Persona persona = personaService.createPersona(createPersonaRequest);
		LocalTime hora = LocalTime.now();
		String url = null;
		if(hora.getHour()>=5 && (hora.getHour()<11 && hora.getMinute()<=59)) {
			url="http://localhost:8090/api/saludo/findByTipo/mañana";
		}else if(hora.getHour()>=12 && (hora.getHour()< 18 && hora.getMinute()<=59)){
			url="http://localhost:8090/api/saludo/findByTipo/tarde"; 
		}else if(hora.getHour()>=19 && (hora.getHour()<4 && hora.getMinute()<=59)) {
			url ="http://localhost:8090/api/saludo/findByTipo/noche";
		}
		RestTemplate restTemplate = new RestTemplate();
		String saludo = restTemplate.getForObject(url, String.class);
		PersonaResponse persRes = new PersonaResponse(persona);
		persRes.setSaludo(saludo);
		return persRes;
	}

}
