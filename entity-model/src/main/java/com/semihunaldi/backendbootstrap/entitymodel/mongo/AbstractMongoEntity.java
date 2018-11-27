package com.semihunaldi.backendbootstrap.entitymodel.mongo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Data
@EqualsAndHashCode(of = "id")
public abstract class AbstractMongoEntity implements Serializable {

	@Id
	private String id;
}
