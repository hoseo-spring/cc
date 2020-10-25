package itc.hoseo.cc.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileUploadController {
	
	// 파일업로드 임시파일
	@RequestMapping(path = "/fileadd", method = RequestMethod.GET) 
	public String fileupload(ModelMap mm) {
		return "fileadd";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String fileupload(HttpServletRequest request, @RequestParam("filename") MultipartFile mFile) {
		try { 
			mFile.transferTo(new File("c:/test/"+mFile.getOriginalFilename()));
		}catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "/fileview";
	}
//https://blog.naver.com/PostView.nhn?blogId=poem1979&logNo=221663648794&proxyReferer=https:%2F%2Fwww.google.com%2F

//	@RequestMapping(value = "upload", method = RequestMethod.POST)
//	public String upload(@RequestParam("file") MultipartFile multipartFile) {
//		log.info("### upload");
//		File targetFile = new File("/home1/irteam/" + multipartFile.getOriginalFilename());
//		try {
//			InputStream fileStrim = multipartFile.getInputStream();
//			FileUtils.copyInputStreamToFile(fileStrim, targetFile);
//		} catch (IOException e) {
//			FileUtils.deletQuietly(targetFile);
//			e.printStackTrace();
//		}
//		return "redirect:/fileadd";
//	}
	
}
