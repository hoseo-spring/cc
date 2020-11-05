package itc.hoseo.cc.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.domain.UploadFile;
import itc.hoseo.cc.repository.FileRepository;
import itc.hoseo.cc.repository.ProductRepository;
import itc.hoseo.cc.service.SpringSecurityUserContext;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private SpringSecurityUserContext userContext;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(path = "/list", method = RequestMethod.GET) 
	public String listGet(ModelMap mm, int page, String sort, String soldCheck) {
		Boolean showSold = false;
		if(soldCheck.equals("true")) {
			showSold = false;
		} else {
			showSold = true;
		}
		
		if(sort.equals("latest")) {
			if(showSold) {
				mm.put("products", productRepo.findAll(PageRequest.of(page, 5)));
			} else {
				mm.put("products", productRepo.findBySoldDateNull(PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceAsc")) {
			if(showSold) {
				mm.put("product", productRepo.findByOrderByPrice(PageRequest.of(page, 5)));
			} else {
				mm.put("products", productRepo.findBySoldDateNullOrderByPrice(PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceDesc")) {
			if(showSold) {
				mm.put("product", productRepo.findByOrderByPriceDesc(PageRequest.of(page, 5)));
			} else {
				mm.put("products", productRepo.findBySoldDateNullOrderByPriceDesc(PageRequest.of(page, 5)));
			}
		}
		
		int wholePage = (int)(productRepo.count()/5);
		int prev = (page < 5) ? 0 : (page - 5);
		int next = (page > (wholePage - 5)) ? wholePage-1 : (page + 5);
		mm.put("prev", prev);
		mm.put("next", next);
		mm.put("wholePage", wholePage);
		mm.put("startPage", ((int)(page/5)*5));
		return "list";
	}
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public String listPost(ModelMap mm, int page, String condition) {
		mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContains(condition, condition, condition, PageRequest.of(page, 5)));
		int wholePage = (int)(productRepo.findByNameContainsOrLocationContainsOrCategoryContains(condition, condition, condition).size()/5);
		int prev = (page < 5) ? 0 : (page - 5);
		int next = (page > (wholePage - 5)) ? wholePage-1 : (page + 5);
		mm.put("prev", prev);
		mm.put("next", next);
		mm.put("wholePage", wholePage);
		mm.put("startPage", ((int)(page/5)*5));
		return "list";
	}
	
	@RequestMapping(path = "/post", method = RequestMethod.GET) 
	public String postGet(ModelMap mm) {
		return "post";
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST) 
	public String postPost(Product product,@RequestParam("img") List<MultipartFile> files) {
		
		final String uploadDir = env.getProperty("cc.uploaddir.product");
		
		List<UploadFile> images = files.stream()
				.map(f -> {
					UploadFile uf = new UploadFile();
					uf.setFileName(f.getOriginalFilename());
					
					String storedFileName = UUID.randomUUID().toString();
									
					while(Files.exists(Paths.get(uploadDir, storedFileName))) {
						storedFileName = UUID.randomUUID().toString();
					}
					
					uf.setStoredFileName(storedFileName);
					
					try {
						f.transferTo(Paths.get(uploadDir, storedFileName));
					} catch (IllegalStateException | IOException e) {
						throw new RuntimeException("파일업로드중 예외 발생", e);
					}
					
					return uf;
				}).collect(Collectors.toList());
		
		fileRepo.saveAll(images);
				
		product.setImages(images);
		product.setUploadDate(new Date());
		product.setUser(userContext.getCurrentUser());
		productRepo.save(product);
		
//		if(log.isDebugEnabled()) {
//			log.debug("{}", product);
//			log.debug("{}",files);
//		}
//		productRepo.save(
//					Product.builder()
//						.name(name)
//						.category(category)
//						.price(price)
//						.location(location)
//						.description(description)
//						.uploadDate(new Date())
//						.user(userContext.getCurrentUser())
//						.build());
		return "redirect:list?page=0&sort=latest&soldCheck=false";
	}
	
	@RequestMapping(path = "/content", method = RequestMethod.GET) 
	public String contentGet(ModelMap mm, Long product_id) {
		mm.put("product", productRepo.findById(product_id).get());
		return "content";
	}
	
}
