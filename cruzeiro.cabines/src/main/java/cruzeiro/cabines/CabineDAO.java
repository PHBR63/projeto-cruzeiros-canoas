package cruzeiro.cabines;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CabineDAO extends CrudRepository<CabineBean, Integer>{
	
	@Modifying
	@Query("update Cabine C set C.idCabine = ?1, C.maxPessoas = ?2, C.precoPessoa = ?3 where C.idCabine = ?1")
	Iterable<CabineBean> atualizarCabine(Integer id, Integer maxPessoas, Double precoPessoa);

}
