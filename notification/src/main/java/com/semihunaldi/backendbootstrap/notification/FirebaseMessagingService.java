package com.semihunaldi.backendbootstrap.notification;

import com.semihunaldi.backendbootstrap.notification.model.FcmMessage;
import com.semihunaldi.backendbootstrap.services.BaseService;

/**
 * Created by semihunaldi on 22.11.2018
 */
public interface FirebaseMessagingService extends BaseService {

	void registerUser(String userFcmTokenId, String userId);

	void sendMessage(FcmMessage fcmMessage);
}
