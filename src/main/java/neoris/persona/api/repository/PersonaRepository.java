package neoris.persona.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import neoris.persona.api.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
	
	@Query("FROM Persona WHERE nombre = :nombre")
	Persona getByName(@Param("nombre") String nombre);
}
