package itc.hoseo.cc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Locations {
	@Id
	private String id;
	
	// 위도
	private Double latitude;
	
	// 경도
	private Double longitude;
	
	@OneToOne
	private User user;
	
	// e.g. 시/도
	private String state;
	
	// e.g. 구/군
	private String city;
}
