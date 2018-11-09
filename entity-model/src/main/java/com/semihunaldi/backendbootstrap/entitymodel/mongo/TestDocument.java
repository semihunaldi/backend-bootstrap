package com.semihunaldi.backendbootstrap.entitymodel.mongo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true, of = {})
@Document
public class TestDocument extends AbstractMongoEntity {

	@NotNull(message = "'userId' field can not be null")
	@Indexed(unique = true)
	private String userId;


	@Temporal(TemporalType.DATE)
	private Date timestamp;

	@Transient
	private String testString;
}
