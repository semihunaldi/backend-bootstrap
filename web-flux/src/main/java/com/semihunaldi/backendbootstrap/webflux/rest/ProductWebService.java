package com.semihunaldi.backendbootstrap.webflux.rest;

import com.semihunaldi.backendbootstrap.entitymodel.reactivemongo.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by semihunaldi on 27.11.2018
 */


@RequestMapping("/api/product")
public interface ProductWebService {

	/**
	 * To create an product
	 *
	 * @param product
	 * @return
	 */
	@PostMapping
	Mono<ResponseEntity<Product>> create(@RequestBody Product product);

	/**
	 * To update an product
	 *
	 * @param product
	 * @return
	 */
	@PutMapping
	Mono<ResponseEntity<Product>> update(@RequestBody Product product);

	/**
	 * Get the list of all products
	 *
	 * @return
	 */
	@GetMapping
	Flux<ResponseEntity<Product>> list();

	/**
	 * We asynchronously find an product by his Id, return it with a success status if its found
	 * and with the 404 and null object if not
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	Mono<ResponseEntity<Product>> findById(@PathVariable String id);

	/**
	 * Delete an product by its id
	 *
	 * @param id
	 */
	@DeleteMapping("{id}")
	Mono<ResponseEntity<Void>> deleteById(@PathVariable String id);
}
