package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.raghu.entity.Product;
import in.nareshit.raghu.exception.ProductNotFoundException;
import in.nareshit.raghu.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired	
	private IProductService service;

	/**
	 * 1. create product using JSON input
	 */
	@PostMapping("/save")
	public ResponseEntity<String> createProduct(
			@RequestBody Product product) 
	{
		ResponseEntity<String> resp = null;
		Integer id = service.saveProduct(product);
		String message = "Product '"+id+"' is created!";
		resp = new ResponseEntity<String>(message,HttpStatus.CREATED);//201
		return resp;
	}

	/**
	 * 2. Fetch all products as JSON
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Product>> fetchAllProduct() 
	{
		ResponseEntity<List<Product>> resp = null;
		List<Product> list = service.getAllProducts();
		resp = new ResponseEntity<List<Product>>(list,HttpStatus.OK);//200
		return resp;
	}

	/**
	 * 3. fetch one product by id
	 */
	@GetMapping("/fetch/{id}")
	public ResponseEntity<?> getOneProduct(
			@PathVariable Integer id) 
	{
		ResponseEntity<?> resp = null;
		try {
			Product p = service.getOneProduct(id);
			resp = new ResponseEntity<Product>(p,HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		return resp;
	}


	/**
	 * 4. delete one product by id
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteProduct(
			@PathVariable Integer id) 
	{
		ResponseEntity<String> resp = null;
		try {
			service.deleteProduct(id);
			resp = new ResponseEntity<String>(
					"Product Deleted",
					HttpStatus.OK); //200
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		return resp;
	}

	/**
	 * 5. update product
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(
			@RequestBody Product product) 
	{
		ResponseEntity<String> resp = null;
		try {

			service.updateProduct(product);
			String message = "Product is Updated!";
			resp = new ResponseEntity<String>(
					message,
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Product Not exist for Update",
					HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		return resp;
	}
	
	@PatchMapping("/update/{id}/{code}")
	public ResponseEntity<String> updateProductCode(
			@PathVariable Integer id,
			@PathVariable String code
			) 
	{
		ResponseEntity<String> resp = null;
		try {

			service.updateProductCode(code, id);
			String message = "Product is Updated!";
			resp = new ResponseEntity<String>(
					message,
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(
					"Product Not exist for Update",
					HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		return resp;
	}
}
