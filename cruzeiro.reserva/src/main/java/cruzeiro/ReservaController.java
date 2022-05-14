package cruzeiro;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

public class ReservaController {

	@Value("${app.name}")
	String appName;

	@Autowired
	ReservaDAO dao;

	@GetMapping("/reserva/{totalPessoas}/{data}")
	public ResponseEntity<String> obterReserva(@PathVariable Integer totalPessoas,@PathVariable String data) {
		return new ResponseEntity<String>("Funcionou POrra", HttpStatus.OK);
		
	}
	
	@GetMapping("/atualizar/{id}/{maxPessoas}/{precoPessoa}")
	public ResponseEntity<String> atualizarCabine(@PathVariable Integer id, @PathVariable Integer maxPessoas, @PathVariable Double precoPessoa) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		
		errors.put("codigo", "BAD");
		
		ex.getBindingResult().getFieldErrors().forEach(error ->
		errors.put(error.getField(), error.getDefaultMessage()));
		
		return errors;
	}
}
