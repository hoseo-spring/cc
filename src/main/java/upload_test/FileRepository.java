package upload_test;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import itc.hoseo.cc.domain.Files; 

public interface FileRepository extends CrudRepository<Files, Long>{
	List<Files> findAll(Pageable page);
	
	List<Files> findById(Long id, Pageable page);
}
