package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Product;

public interface IProductService {

	Integer saveProduct(Product p);
	void deleteProduct(Integer id);
	void updateProduct(Product p);
	Product getOneProduct(Integer id);
	List<Product> getAllProducts();
}
