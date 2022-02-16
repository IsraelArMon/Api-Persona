package neoris.persona.api.exception;

import org.postgresql.util.PSQLException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import neoris.persona.api.response.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(PSQLException.class)
	public ResponseEntity<ErrorDetails> nombreDuplicado(PSQLException nomDuplicado){
		ErrorDetails response = new ErrorDetails("800","Nombre existente, Guarda otro nombre",
				nomDuplicado.getMessage());
		return new ResponseEntity<ErrorDetails>(response,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ListaVaciaException.class)
	public ResponseEntity<ErrorDetails> nombreDuplicado(ListaVaciaException dostExist){
		ErrorDetails response = new ErrorDetails("1500","No hay personas en la base de datos",
				dostExist.getMessage());
		return new ResponseEntity<ErrorDetails>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDetails> nombreDuplicado(NullPointerException dostExist){
		ErrorDetails response = new ErrorDetails("1000","El elemento no existe en la base de datos",
				dostExist.getMessage());
		return new ResponseEntity<ErrorDetails>(response,HttpStatus.NOT_FOUND);
	}
	
}
