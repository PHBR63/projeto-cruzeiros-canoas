package cruzeiro.cabines;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CabineController {
	@Value("${app.name}")
	String appName;

	@Autowired
	CabineDAO dao;
	
	@GetMapping("/obter")
	public ResponseEntity<Iterable<CabineBean>> obterCabines() {
		return new ResponseEntity<Iterable<CabineBean>>(dao.findAll(), HttpStatus.OK);

	}
	
	@GetMapping("/atualizar/{id}/{maxPessoas}/{precoPessoa}")
	public ResponseEntity<String> atualizarCabine(@PathVariable Integer idCabine, @PathVariable Integer maxPessoas, @PathVariable Integer precoPessoa) {
		dao.atualizarCabine(idCabine, maxPessoas, precoPessoa);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/excluir/{id}")
	public String excluirCabine(@RequestBody Integer idCabine) {
		//dao.deleteById(idCabine);
		System.out.println("entrei nessa bagaça, id: {idCabine}");
		return "entrei nessa bagaça, id: {idCabine}";
	}
	
	@PostMapping("/criar")
	public ResponseEntity<String> criarCabine(@Valid @RequestBody CabineBean cabine) {
		dao.save(cabine);
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
