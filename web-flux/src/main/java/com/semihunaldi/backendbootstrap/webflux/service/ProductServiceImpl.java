package com.semihunaldi.backendbootstrap.webflux.service;

import com.semihunaldi.backendbootstrap.entitymodel.reactivemongo.Product;
import com.semihunaldi.backendbootstrap.webflux.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Mono<Product> save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Mono<Product> findOne(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Flux<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Mono<Void> delete(String id) {
		return productRepository.deleteById(id);
	}
}
