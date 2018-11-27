package com.semihunaldi.backendbootstrap.webflux.service;

import com.semihunaldi.backendbootstrap.entitymodel.reactivemongo.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by semihunaldi on 27.11.2018
 */
public interface ProductService {

	/**
	 * To create/update product
	 * @param product
	 * @return saved/updated product
	 */
	Mono<Product> save(Product product);

	/**
	 * Find a single product by id
	 * @param id
	 * @return
	 */
	Mono<Product> findOne(String id);

	/**
	 * Find all saved products so fare
	 * @return
	 */
	Flux<Product> findAll();

	/**
	 * Delete a single product by id
	 * @param id
	 */
	Mono<Void> delete(String id);

}
