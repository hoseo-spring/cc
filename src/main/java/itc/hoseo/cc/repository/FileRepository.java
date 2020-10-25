package itc.hoseo.cc.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.UploadFile;

import java.util.List;

public interface FileRepository extends CrudRepository<UploadFile, Long>{
	List<UploadFile> findAll(Pageable page);
}
