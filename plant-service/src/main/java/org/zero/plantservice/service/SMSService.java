package org.zero.plantservice.service;


import org.zero.plantservice.dto.SMSRequest;

import java.util.Map;

public interface SMSService {
    Map<String, Object> sendSMS(SMSRequest smsRequest) throws Exception;
}
