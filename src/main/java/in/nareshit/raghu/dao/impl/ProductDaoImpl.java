package in.nareshit.raghu.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import in.nareshit.raghu.dao.IProductDao;
import in.nareshit.raghu.entity.Product;

@Repository
public class ProductDaoImpl implements IProductDao {

	@Autowired
	private HibernateTemplate ht;

	public Integer saveProduct(Product p) {
		return (Integer)ht.save(p);
	}

	public void deleteProduct(Product p) {
		ht.delete(p);
	}

	public void updateProduct(Product p) {
		ht.update(p);
	}

	public Product getOneProduct(Integer id) {
		return ht.get(Product.class, id);
	}

	public List<Product> getAllProducts() {
		return ht.loadAll(Product.class);
	}

	public Integer updateProductCode(String prodCode, Integer prodId) {
		return ht.execute(session -> {
			return session.createQuery(
					"UPDATE Product SET prodCode=:prodCode WHERE prodId=:prodId"
					)
					.setParameter("prodCode", prodCode)
					.setParameter("prodId", prodId)
					.executeUpdate();
		});
	}

}
