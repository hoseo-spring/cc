package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Locations;
import itc.hoseo.cc.domain.User;

public interface LocationsRepository extends CrudRepository<Locations, String> {
	//
	List<Locations> findByUser(User user);
}
