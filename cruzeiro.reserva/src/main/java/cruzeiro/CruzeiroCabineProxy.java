package cruzeiro;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Canoas Cruzeiros - Cabine", url = "localhost:8081")
public interface CruzeiroCabineProxy {

	@GetMapping("/obter")
	public ResponseEntity<Iterable<CabineBean>> getCabines();

}
