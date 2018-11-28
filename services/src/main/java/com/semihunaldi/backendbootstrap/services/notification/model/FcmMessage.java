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
public class FcmMessage {

	private DetailsDto detailsDto;
	private String toToken;
	private String topic;
}
