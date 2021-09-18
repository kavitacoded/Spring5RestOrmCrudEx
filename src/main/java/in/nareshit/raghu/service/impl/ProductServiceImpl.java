package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.dao.IProductDao;
import in.nareshit.raghu.entity.Product;
import in.nareshit.raghu.exception.ProductNotFoundException;
import in.nareshit.raghu.service.IProductService;

@Service
//@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao dao;

	@Transactional
	public Integer saveProduct(Product p) {
		return dao.saveProduct(p);
	}

	@Transactional
	public void deleteProduct(Integer id) {
		Product p = getOneProduct(id);
		dao.deleteProduct(p);
	}

	@Transactional
	public void updateProduct(Product p) {
		//getOneProduct(p.getProdId());
		dao.updateProduct(p);
	}

	@Transactional(readOnly = true)
	public Product getOneProduct(Integer id) {
		Product p = dao.getOneProduct(id);
		if(p==null) 
			throw new ProductNotFoundException("Product ("+id+") Not exist");
		else 
			return p;
	}

	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}
	
	@Transactional
	public Integer updateProductCode(String prodCode, Integer prodId) {
		return dao.updateProductCode(prodCode, prodId);
	}

}
