package com.semihunaldi.backendbootstrap.webflux.rest;

import com.semihunaldi.backendbootstrap.entitymodel.reactivemongo.Product;
import com.semihunaldi.backendbootstrap.webflux.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by semihunaldi on 27.11.2018
 */

@RestController
public class ProductWebServiceController implements ProductWebService {

	@Autowired
	private ProductService productService;

	@Override
	public Mono<ResponseEntity<Product>> create(@RequestBody Product product) {
		return productService.save(product).map(ResponseEntity::ok);
	}

	@Override
	public Mono<ResponseEntity<Product>> update(@RequestBody Product product) {
		if(product.getId() == null){
			return create(product);
		}
		return productService.save(product).map(ResponseEntity::ok);
	}

	@Override
	public Flux<ResponseEntity<Product>> list() {
		return productService.findAll().map(ResponseEntity::ok);
	}

	@Override
	public Mono<ResponseEntity<Product>> findById(@PathVariable String id) {
		return productService.findOne(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.status(404).body(null));
	}

	@Override
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
		return productService.delete(id).map(ResponseEntity::ok);
	}
}
