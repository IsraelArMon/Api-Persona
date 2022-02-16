package neoris.persona.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import neoris.persona.api.entity.Persona;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaResponse {

	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	private String direccion;
	private String fullName;
	private String saludo;
	
	public PersonaResponse(Persona persona) {
		this.id = persona.getId();
		this.nombre = persona.getNombre();
		this.apellido = persona.getApellido();
		this.edad = persona.getEdad();
		this.direccion = persona.getDireccion();
		this.fullName = persona.getNombre() +  " " + persona.getApellido(); 
		this.saludo = persona.getSaludo();
	}
}
