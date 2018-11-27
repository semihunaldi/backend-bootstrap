package com.semihunaldi.backendbootstrap.webflux.repositories;

import com.semihunaldi.backendbootstrap.entitymodel.reactivemongo.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by semihunaldi on 27.11.2018
 */

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product,String> {

}
