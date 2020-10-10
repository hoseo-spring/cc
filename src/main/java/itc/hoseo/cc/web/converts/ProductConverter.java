package itc.hoseo.cc.web.converts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import itc.hoseo.cc.domain.Product;
import itc.hoseo.cc.repository.ProductRepository;

@Component
public class ProductConverter implements Converter<Long, Product> {
	
	@Autowired
	private ProductRepository repo;

	@Override
	public Product convert(Long value) {
		return repo.findById(value).get();
	}

}
