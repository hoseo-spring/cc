package itc.hoseo.cc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ConfigurationProperties(prefix = "file")
public class Files {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="file_id")
	private Long Id;
	
	@Column
	private String fileName;
	
	@Column
	private String fileType;
	
	@Column
	private Long size;

}
