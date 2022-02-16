package neoris.persona.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import neoris.persona.api.entity.Empresa;
import neoris.persona.api.entity.Persona;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	@Query("FROM Empresa WHERE empresa = :empresa")
	Empresa getByName(@Param("empresa") String empresa);
}
