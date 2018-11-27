package com.semihunaldi.backendbootstrap.entitymodel.reactivemongo;

import com.semihunaldi.backendbootstrap.entitymodel.mongo.AbstractMongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by semihunaldi on 27.11.2018
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Document(value = "product")
public class Product extends AbstractMongoEntity {
	private String name;
	private String type;
	private String size;
}
