package itc.hoseo.cc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"nickname"})
})
@ToString(exclude = "products")
public class User {
	@Id
	@Column(name = "userId",length = 50)
	private String id;
	
	@Column(length = 50, nullable = false)
	private String password;
	
	@Column(length = 20, nullable = false)
	private String nickname;
	
	@OneToMany
	@JoinColumn(name="user_id")
	private List<UploadFile> images;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date registeredDate;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	List<Product> products = new ArrayList<>();
	
}
