package com.semihunaldi.backendbootstrap.services;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by semihunaldi on 9.11.2018
 */

@Transactional(readOnly = true)
public class BaseServiceImpl {

	protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void adjustEntityForDeletion(AbstractEntity entity) {
		checkNotNull(entity, "entity is null");
		checkArgument(StringUtils.isNotBlank(entity.getId()), "entry must have been saved before attempting to delete");
		entity.setDeleted(UUID.randomUUID().toString());
	}

	public <T> Set<ConstraintViolation<T>> executeValidations(T bean) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator.validate(bean);
	}

	public <T> void executeValidationsAndThrow(T bean) {
		Set<ConstraintViolation<T>> constraintViolations = executeValidations(bean);
		List<String> exceptionMessages = Lists.newArrayList();
		for(ConstraintViolation constraintViolation : constraintViolations){
			exceptionMessages.add(constraintViolation.getMessage());
		}
		String message = Joiner.on(", ").skipNulls().join(exceptionMessages);
		if(StringUtils.isNotBlank(message)){
			throw new ValidationException(message);
		}
	}
}
