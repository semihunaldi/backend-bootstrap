package com.semihunaldi.backendbootstrap.services.notification.model;

import lombok.Data;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Data
public class DetailsDto {

	private OperationType operationType;

	private String message;

	private String description;
}
