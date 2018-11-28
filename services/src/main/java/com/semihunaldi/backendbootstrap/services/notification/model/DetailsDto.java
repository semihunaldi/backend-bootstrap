package com.semihunaldi.backendbootstrap.services.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsDto {

	private OperationType operationType;

	private String message;

	private String description;
}
