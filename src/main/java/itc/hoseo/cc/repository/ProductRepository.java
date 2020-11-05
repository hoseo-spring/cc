package itc.hoseo.cc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import itc.hoseo.cc.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	//페이징, 정렬
	List<Product> findAll(Pageable page);
	List<Product> findByOrderByPrice(Pageable page);
	List<Product> findByOrderByPriceDesc(Pageable page);
	
	List<Product> findBySoldDateNull(Pageable page);
	List<Product> findBySoldDateNullOrderByPrice(Pageable page);
	List<Product> findBySoldDateNullOrderByPriceDesc(Pageable page);
	
	List<Product> findByCategory(String category, Pageable page);
	List<Product> findByUserId(String userId, Pageable page);
	List<Product> findByLocation(String location, Pageable page);
	
	//Like 검색
	List<Product> findByNameContains(String name, Pageable page);
	List<Product> findByNameContainsOrLocationContainsOrCategoryContains(String name, String location, String category, Pageable page);
	List<Product> findByNameContainsOrLocationContainsOrCategoryContains(String name, String location, String category);
}
