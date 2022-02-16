package neoris.persona.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import neoris.persona.api.request.CreatePersonaRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "nombre", length = 45, unique =true)
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "direccion")
	private String direccion;
	@Transient
	private String fullName;
	private String saludo;
	@OneToOne
	@JoinColumn(name  = "idEmpresa")
	private Empresa empresa;
	
	public Persona(CreatePersonaRequest createPersonaRequest) {
		this.nombre = createPersonaRequest.getNombre();
		this.apellido = createPersonaRequest.getApellido();
		this.edad = createPersonaRequest.getEdad();
		this.direccion = createPersonaRequest.getDireccion();
		this.fullName = createPersonaRequest.getNombre() + " " + createPersonaRequest.getApellido();
		
	}

}
