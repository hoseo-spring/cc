package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Locations;

public interface LocationsRepository extends CrudRepository<Locations, String> {
	//
	List<Locations> findByUser(String userId);
}
