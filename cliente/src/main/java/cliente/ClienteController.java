package cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.model.Response;

@RestController
public class ClienteController {
	@Value("${app.name}")
	String appName;

	@Autowired
	private CloudantClient cloudantClient;

	@GetMapping("/cliente/claudant/dbs")
	public List<String> getClaudantDbs() {
		return cloudantClient.getAllDbs();
	}

	@PostMapping("/cliente/cloudant")
	public ResponseEntity<String> inserir(@RequestBody ClienteBean cliente) {
		Response res = cloudantClient.database("cliente", false).save(cliente);
		return new ResponseEntity<String>(res.getId(), HttpStatus.OK);
	}
	
	@GetMapping("/cliente/cloudant/{id}")
	public ResponseEntity<ClienteBean> pesquisar(@PathVariable String id) {
		ClienteBean res = cloudantClient.database("cliente", false).find(ClienteBean.class, id);
		return new ResponseEntity<ClienteBean>(res, HttpStatus.OK);
	}
}
