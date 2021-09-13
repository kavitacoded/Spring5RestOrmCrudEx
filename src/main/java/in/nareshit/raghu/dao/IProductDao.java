package in.nareshit.raghu.dao;

import java.util.List;

import in.nareshit.raghu.entity.Product;

public interface IProductDao {

	Integer saveProduct(Product p);
	void deleteProduct(Product p);
	void updateProduct(Product p);
	Product getOneProduct(Integer id);
	List<Product> getAllProducts();
	Integer updateProductCode(String prodCode,Integer prodId);
}
