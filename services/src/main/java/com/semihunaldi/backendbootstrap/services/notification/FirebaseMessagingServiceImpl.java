package com.semihunaldi.backendbootstrap.services.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.semihunaldi.backendbootstrap.entitymodel.user.User;
import com.semihunaldi.backendbootstrap.services.BaseServiceImpl;
import com.semihunaldi.backendbootstrap.services.notification.model.DetailsDto;
import com.semihunaldi.backendbootstrap.services.notification.model.FcmMessage;
import com.semihunaldi.backendbootstrap.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Component
public class FirebaseMessagingServiceImpl extends BaseServiceImpl implements FirebaseMessagingService {

	@Autowired
	private UserService userService;

	@Override
	public void registerUser(String userFcmTokenId, String userId) {
		User user = userService.findUserById(userId);
		user.setFcmTokenId(userFcmTokenId);
		userService.saveUser(user);
	}

	@Override
	public void sendMessage(FcmMessage fcmMessage) {
		try{
			// This registration token comes from the client FCM SDKs.
			String registrationToken = fcmMessage.getToToken();
			DetailsDto detailsDto = fcmMessage.getDetailsDto();
			// See documentation on defining a message payload.
			Message message = Message.builder()
					.putData("operationType", detailsDto.getOperationType().name())
					.putData("message", detailsDto.getMessage())
					.putData("description", detailsDto.getDescription())
					.setToken(registrationToken)
					.setTopic(fcmMessage.getTopic())
					.build();
			// Send a message toToken the device corresponding toToken the provided
			// registration token.
			String response = FirebaseMessaging.getInstance().send(message);
			// Response is a message ID string.
			logger.info("fcm message sent with id : " + response + " To User with token : " + registrationToken);
		} catch(FirebaseMessagingException e){
			logger.error("FirebaseMessagingServiceImpl.sendMessage error",e);
		}
	}
}
