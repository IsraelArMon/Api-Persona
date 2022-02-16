package neoris.persona.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePersonaRequest {
	
	private String nombre;
	private String apellido;
	private int edad;
	private String direccion;
	private String empresa;
	private String direccionEmpresa;
}
