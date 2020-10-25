package itc.hoseo.cc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itc.hoseo.cc.domain.Locations;
import itc.hoseo.cc.domain.User;
import itc.hoseo.cc.repository.LocationsRepository;
import itc.hoseo.cc.repository.UserRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationsRepository locationRepo;
	
	public boolean saveLocation(String state, String city, User user, String currentLocation) {
		if(currentLocation.contains(state)) {
			if(currentLocation.contains(city)) {
				Locations location = 
						Locations
						.builder()
						.state(state)
						.city(city)
						.user(user)
						.build();
				locationRepo.save(location);
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
	}

}
