package com.semihunaldi.backendbootstrap.entitymodel.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Data
public abstract class AbstractMongoEntity implements Serializable {

	@Id
	private String id;
}
