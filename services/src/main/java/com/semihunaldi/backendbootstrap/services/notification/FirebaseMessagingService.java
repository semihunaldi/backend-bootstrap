package com.semihunaldi.backendbootstrap.services.notification;

import com.semihunaldi.backendbootstrap.services.BaseService;
import com.semihunaldi.backendbootstrap.services.notification.model.FcmMessage;

/**
 * Created by semihunaldi on 22.11.2018
 */
public interface FirebaseMessagingService extends BaseService {

	void registerUser(String userFcmTokenId, String userId);

	void sendMessage(FcmMessage fcmMessage);
}
