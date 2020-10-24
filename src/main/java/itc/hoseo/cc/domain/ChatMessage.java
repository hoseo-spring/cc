package itc.hoseo.cc.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ChatMessage {
	private Long productId;
	private String senderId;
	private Date sendDttm;
	private String content;
}
