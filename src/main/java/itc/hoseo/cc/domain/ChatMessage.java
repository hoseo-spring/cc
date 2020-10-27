package itc.hoseo.cc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chatId")
	private Long id;
	
	private String productId;
	private String senderId;
	private String receiverId;
	private Date sendDttm;
	private String content;
	private String ws;
}
