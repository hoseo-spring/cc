package itc.hoseo.cc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class UploadFile {
	
	@Id	
	@GeneratedValue
	@Column(name="upload_file_id")
	private Long id;
	
	@Column
	private String fileName;
	
	@Column
	private String fileType;
	
	@Column
	private String storedFileName;

}
