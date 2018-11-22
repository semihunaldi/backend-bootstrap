package com.semihunaldi.backendbootstrap.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcmMessage {

	private DetailsDto detailsDto;
	private String toToken;
	private String topic;

	public FcmMessage(String toToken, String topic, String message, String description, OperationType operationType) {
		if(StringUtils.isNotBlank(message) || StringUtils.isNotBlank(description) || operationType != null){
			detailsDto = new DetailsDto();
			if(StringUtils.isNotBlank(message)){
				detailsDto.setMessage(message);
			}
			if(StringUtils.isNotBlank(description)){
				detailsDto.setDescription(description);
			}
			if(operationType != null){
				detailsDto.setOperationType(operationType);
			}
		}
		this.toToken = toToken;
	}
}
