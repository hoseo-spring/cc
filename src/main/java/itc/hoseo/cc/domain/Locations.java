package itc.hoseo.cc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "locationsId")
	private Long id;
	
	@OneToOne
	private User user;
	
	// e.g. 시/도
	private String state;
	
	// e.g. 구/군
	private String city;
}
