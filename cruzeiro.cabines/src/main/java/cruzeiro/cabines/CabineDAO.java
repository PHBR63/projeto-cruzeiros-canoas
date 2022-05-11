package cruzeiro.cabines;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface CabineDAO extends CrudRepository<CabineBean, Integer>{
	
	@Query("UPDATE Cabine C SET maxPessoas = ?2, precoPessoa = ?3 WHERE C.idCabine = ?1")
	Iterable<CabineBean> atualizarCabine(int id, int maxPessoas, int precoPessoa);

}
