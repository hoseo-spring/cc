package itc.hoseo.cc.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Rating {
	@Id
	@GeneratedValue
	@Column(name = "ratingId")
	private Long id;
	
	@Column(nullable = false)
	private double rating;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date reatedDate;
	
	// 평점을 매기는 유저
	@OneToOne
	private User rateUser;
	
	
	// 평점을 받는 유저
	//@ManyToOne
	//@JoinColumn(name = "userId")
	@OneToOne
	private User receiveUser;
	
	// 평점 대상 물품
	@OneToOne
	private Product rateProduct;
	
	@Column(length = 100)
	private String review;
}
