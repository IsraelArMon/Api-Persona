package neoris.persona.api.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neoris.persona.api.entity.Empresa;
import neoris.persona.api.entity.Persona;
import neoris.persona.api.exception.ListaVaciaException;
import neoris.persona.api.repository.EmpresaRepository;
import neoris.persona.api.repository.PersonaRepository;
import neoris.persona.api.request.CreatePersonaRequest;

@Service
public class PersonaService {
	@Autowired
	PersonaRepository personaRepo;
	@Autowired
	EmpresaRepository empresaRepo;
	
	public List<Persona> getAllPersonas(){
		List<Persona> personas = personaRepo.findAll();
		if(personas.isEmpty()) {
			throw new ListaVaciaException("la lista esta vacia");
		}
		return personas;
	}
	
	public Persona getByName(String nombre) {
		Persona getByName = personaRepo.getByName(nombre);
		if(Objects.isNull(getByName)) {
			throw new NullPointerException("El nombre que buscas no existe");
		}
		return getByName;
	}
	
	
	
	public Persona createPersona(CreatePersonaRequest createPersonaRequest) {
		Persona persona = new Persona(createPersonaRequest);
		Empresa empresa = new Empresa();
		boolean existe = empresaRepo.getByName(createPersonaRequest.getEmpresa())!= null;
		if(!existe) {	
			empresa.setNombre(createPersonaRequest.getEmpresa());
			empresa.setDireccion(createPersonaRequest.getDireccionEmpresa());
			empresa = empresaRepo.save(empresa);
		}else {
			empresa = empresaRepo.getByName(createPersonaRequest.getEmpresa());
		}
		persona.setEmpresa(empresa);
		persona = personaRepo.save(persona);
		return persona;
	}
	
}
