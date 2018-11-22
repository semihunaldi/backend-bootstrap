package com.semihunaldi.backendbootstrap.services.aspect;

import lombok.Data;

@Data
public class MethodCallLog {
    private String requestId;
    private String methodName;
    private String duration;
    private String additionalInformation;
    private String message;

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(", Duration: ");
        stringBuilder.append(getDuration());
        stringBuilder.append(" ms");
        stringBuilder.append(", Method Name: ");
        stringBuilder.append(getMethodName());
        stringBuilder.append(", Request ID: ");
        stringBuilder.append(getRequestId());
        stringBuilder.append(", Message: ");
        stringBuilder.append(getMessage());
        return stringBuilder.toString();
    }
}
