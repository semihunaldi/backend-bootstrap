package com.semihunaldi.backendbootstrap.entitymodel.util;

import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
import com.semihunaldi.backendbootstrap.entitymodel.SimpleAbstractEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

/**
 * Created by semihunaldi on 9.11.2018
 */
public class UpperCaseUUIDGenerator extends UUIDGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if(object instanceof AbstractEntity){
			AbstractEntity abstractEntity = (AbstractEntity) object;
			if(abstractEntity.getId() != null){
				return abstractEntity.getId();
			}
		}
		if(object instanceof SimpleAbstractEntity){
			SimpleAbstractEntity abstractEntity = (SimpleAbstractEntity) object;
			if(abstractEntity.getId() != null){
				return abstractEntity.getId();
			}
		}
		Serializable generated = super.generate(session, object);
		return StringUtils.upperCase((String) generated);
	}
}

