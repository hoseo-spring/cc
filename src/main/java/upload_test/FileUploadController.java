package upload_test;

import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@Controller
public class FileUploadController {
	
	@Autowired
	FileRepository fileRepo;

	@Autowired
	FileService fileservice;
	
	// 파일업로드 임시파일
	@RequestMapping(path = "/fileadd", method = RequestMethod.GET) 
	public String fileGet(ModelMap mm) {
		return "fileadd";
	}
	
	@RequestMapping(path = "/fileadd", method = RequestMethod.POST)
    public String filPost(HttpServletRequest request, @RequestParam("file") MultipartFile mFile,
    		@ModelAttribute File files) throws Exception{
		
		String filename = null; // 파일명 초기화
		if(!mFile.isEmpty()) {
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/upload");
			
			filename = mFile.getName(); // 파일명가져오고
			// 엣지 브라우저 요청 파일이름 처리?
			int index = filename.lastIndexOf("\\");
			filename = filename.substring(index + 1);
			File file = new File(realPath, filename);
			
			if (file.exists()) { // 해당 경로에 동일한 파일명이 이미 존재하는 경우
	        	// 파일명 앞에 업로드 시간 밀리초 붙여서 파일명 중복을 방지
	        	filename = System.currentTimeMillis() + "_" + filename;
	        	file = new File(realPath, filename);
	        }

			System.out.println("업로드 경로: " + realPath);
	        System.out.println("업로드 파일명: " + filename);

	     // 업로드 수행
	        IOUtils.copy(mFile.getInputStream(), new FileOutputStream(file));
		} else {
			System.out.println("파일이 존재하지 않거나 파일크기가 0 입니다.");
		}

		 // DB insert
//		files.setFilenam(filename);
//		
//		// reg_date  ip  set메소드로 저장
//        board.setReg_date(new Timestamp(System.currentTimeMillis()));
//        board.setIp(request.getRemoteAddr());
//
//        boardService.add(board);  // 주글 등록

        return "/fileview";
    }
	/*
	 * @RequestMapping(value = "/upload", method = RequestMethod.POST) public String
	 * fileupload(HttpServletRequest request, @RequestParam("filename")
	 * MultipartFile mFile) { try { mFile.transferTo(new
	 * File("c:/test/"+mFile.getOriginalFilename())); }catch (IllegalStateException
	 * | IOException e) { e.printStackTrace(); } return "/fileview"; }
	 */

}
