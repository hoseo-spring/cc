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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.domain.UploadFile;
import itc.hoseo.cc.repository.FileRepository;
import itc.hoseo.cc.repository.LocationsRepository;
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
	private LocationsRepository locaRepo;
	
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
		int count = 0;
		
		if(sort.equals("latest")) {
			if(showSold) {
				count = productRepo.findAll().size();
				mm.put("products", productRepo.findByOrderByIdDesc(PageRequest.of(page, 5)));
			} else {
				count = productRepo.findBySoldDateNull().size();
				System.out.println(count);
				mm.put("products", productRepo.findBySoldDateNullOrderByIdDesc(PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceAsc")) {
			if(showSold) {
				count = productRepo.findAll().size();
				mm.put("products", productRepo.findByOrderByPrice(PageRequest.of(page, 5)));
			} else {
				count = productRepo.findBySoldDateNull().size();
				mm.put("products", productRepo.findBySoldDateNullOrderByPrice(PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceDesc")) {
			if(showSold) {
				count = productRepo.findAll().size();
				mm.put("products", productRepo.findByOrderByPriceDesc(PageRequest.of(page, 5)));
			} else {
				count = productRepo.findBySoldDateNull().size();
				mm.put("products", productRepo.findBySoldDateNullOrderByPriceDesc(PageRequest.of(page, 5)));
			}
		}

		int wholePage = ((count % 5) == 0) ? (count / 5) : ((int)(count / 5)) + 1;
		int prev = (page < 5) ? 0 : (page - 5);
		int next = (page >= (wholePage - 5)) ? wholePage-1 : (page + 5);
		mm.put("prev", prev);
		mm.put("next", next);
		mm.put("wholePage", wholePage);
		mm.put("startPage", ((int)(page/5)*5));
		return "list";
	}
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public String listPost(ModelMap mm, int page, String condition, String sort, String soldCheck) {
		Boolean showSold = false;
		if(soldCheck.equals("true")) {
			showSold = false;
		} else {
			showSold = true;
		}
		int count = 0;
		
		if(sort.equals("latest")) {
			if(showSold) {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContains(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsOrderByIdDesc(condition, condition, condition, PageRequest.of(page, 5)));
			} else {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNull(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNullOrderByIdDesc(condition, condition, condition, PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceAsc")) {
			if(showSold) {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContains(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsOrderByPrice(condition, condition, condition, PageRequest.of(page, 5)));
			} else {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNull(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNullOrderByPrice(condition, condition, condition, PageRequest.of(page, 5)));
			}
		} else if(sort.equals("priceDesc")) {
			if(showSold) {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContains(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsOrderByPriceDesc(condition, condition, condition, PageRequest.of(page, 5)));
			} else {
				count = productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNull(condition, condition, condition).size();
				mm.put("products", productRepo.findByNameContainsOrLocationContainsOrCategoryContainsAndSoldDateNullOrderByPriceDesc(condition, condition, condition, PageRequest.of(page, 5)));
			}
		}
		int wholePage = ((count % 5) == 0) ? (count / 5) : ((int)(count / 5)) + 1;
		int prev = (page < 5) ? 0 : (page - 5);
		int next = (page >= (wholePage - 5)) ? wholePage-1 : (page + 5);
		mm.put("prev", prev);
		mm.put("next", next);
		mm.put("wholePage", wholePage);
		mm.put("startPage", ((int)(page/5)*5));
		return "list";
	}
	
	@RequestMapping(path = "/post", method = RequestMethod.GET) 
	public String postGet(ModelMap mm) {
		if(!locaRepo.findByUser(userContext.getCurrentUser()).isEmpty()) {
			mm.put("locations", locaRepo.findByUser(userContext.getCurrentUser()));
		} else {
			return "postNoLocations";
		}
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
		return "redirect:list?page=0&sort=latest&soldCheck=true";
	}
	
	@RequestMapping(path = "/content", method = RequestMethod.GET) 
	public String contentGet(ModelMap mm, Long product_id) {
		mm.put("product", productRepo.findById(product_id).get());
		return "content";
	}

	@RequestMapping(path = "/revise", method = RequestMethod.GET) 
	public String reviseGet(ModelMap mm, Long product_id) {
		mm.put("product", productRepo.findById(product_id).get());
		if(!locaRepo.findByUser(userContext.getCurrentUser()).isEmpty()) {
			mm.put("locations", locaRepo.findByUser(userContext.getCurrentUser()));
		} else {
			return "postNoLocations";
		}
		return "revise";
	}	
	
	@RequestMapping(path = "/revise", method = RequestMethod.POST) 
	public String revisePost(Product product, @RequestParam("img") List<MultipartFile> files) {
		Product curProduct = productRepo.findById(product.getId()).get();
		curProduct.setName(product.getName());
		curProduct.setCategory(product.getCategory());
		curProduct.setLocation(product.getLocation());
		curProduct.setPrice(product.getPrice());
		curProduct.setDescription(product.getDescription());
		
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
		
		curProduct.setImages(images);
		
		curProduct.setUpdateDate(new Date());
		productRepo.save(curProduct);
		return "redirect:content?product_id=" + product.getId();
	}
	
	@RequestMapping(path = "/remove", method = RequestMethod.GET) 
	public String removeGet(ModelMap mm, Long product_id) {
		productRepo.deleteById(product_id);
		return "redirect:list?page=0&sort=latest&soldCheck=true";
	}
	
}
