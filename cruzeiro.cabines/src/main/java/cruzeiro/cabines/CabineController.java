package cruzeiro.cabines;

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

@RestController
public class CabineController {
	@Value("${app.name}")
	String appName;

	@Autowired
	CabineDAO dao;

	@GetMapping("/obter")
	public ResponseEntity<Iterable<CabineBean>> obterCabines(Integer id) {
		return new ResponseEntity<Iterable<CabineBean>>(dao.findAll(), HttpStatus.OK);

	}

	@GetMapping("/obter/{id}")
	public ResponseEntity<CabineBean> obterCabineId(@PathVariable Integer id) {

		Optional<CabineBean> ret = dao.findById(id);

		if (ret.isPresent())
			return new ResponseEntity<CabineBean>(ret.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/atualizar/{id}/{maxPessoas}/{precoPessoa}")
	public ResponseEntity<CabineBean> atualizarCabine(@PathVariable Integer id, @PathVariable Integer maxPessoas,
			@PathVariable Double precoPessoa) {
		ResponseEntity<CabineBean> response = obterCabineId(id);

		CabineBean cabine = response.getBody();
		cabine.setMaxPessoas(maxPessoas);
		cabine.setPrecoPessoa(precoPessoa);

		dao.save(cabine);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluirCabineId(@PathVariable Integer id) {
		dao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
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

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}

}
