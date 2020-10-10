package itc.hoseo.cc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	private Float Latitude;
	
	// 경도
	private Float Hardness;
	
}
