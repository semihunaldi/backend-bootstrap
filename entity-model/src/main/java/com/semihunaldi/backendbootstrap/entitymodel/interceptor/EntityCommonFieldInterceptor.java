package com.semihunaldi.backendbootstrap.entitymodel.interceptor;

import com.google.common.base.Optional;
import com.semihunaldi.backendbootstrap.entitymodel.AbstractEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by semihunaldi on 29.11.2018
 */

@Component
public class EntityCommonFieldInterceptor extends EmptyInterceptor {

    private final static Logger log = LoggerFactory.getLogger(EntityCommonFieldInterceptor.class);

    private Optional<MethodCallInformation> findMethodCallParameter() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodCallInformation methodCallInformation = MethodCallInformation.instanceOf();
        if(requestAttributes != null){
            HttpServletRequest request = requestAttributes.getRequest();
            if(request != null){
                if(request.getUserPrincipal() != null){
                    String userName = request.getUserPrincipal().getName();
                    methodCallInformation.setUsername(userName);
                }
                String remoteAddr = request.getRemoteAddr();
                methodCallInformation.setIp(remoteAddr);
            }
        }
        return Optional.of(methodCallInformation);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        updateCommonFieldsOnInsert(entity, state, propertyNames);
        return true;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        updateCommonFieldsOnUpdate(entity, currentState, propertyNames);
        return true;
    }

    private void updateCommonFieldsOnInsert(Object p_entity, Object[] p_state, String[] p_propertyNames) {
        try{
            Optional<MethodCallInformation> methodCallInformation = findMethodCallParameter();
            if(methodCallInformation.isPresent() && p_entity instanceof AbstractEntity){
                AbstractEntity abstractEntity = (AbstractEntity) p_entity;
                setCreateInfo(abstractEntity, p_state, p_propertyNames, methodCallInformation);
                setUpdateInfo(abstractEntity, p_state, p_propertyNames, methodCallInformation);
            }
        } catch(Exception e){
            log.error("Failed to set common fields.", e);
            throw new RuntimeException(e);
        }
    }

    private void updateCommonFieldsOnUpdate(Object p_entity, Object[] p_state, String[] p_propertyNames) {
        try{
            Optional<MethodCallInformation> methodCallInformation = findMethodCallParameter();
            if(methodCallInformation.isPresent() && p_entity instanceof AbstractEntity){
                AbstractEntity abstractEntity = (AbstractEntity) p_entity;
                setUpdateInfo(abstractEntity, p_state, p_propertyNames, methodCallInformation);
            }
        } catch(Exception e){
            log.error("Failed to set common fields.", e);
            throw new RuntimeException(e);
        }
    }

    private void setUpdateInfo(AbstractEntity abstractEntity, Object[] p_state, String[] p_propertyNames, Optional<MethodCallInformation> methodCallInformation) {
        abstractEntity.setUpdateTime(new Date());
        setPropertyState(p_state, p_propertyNames, "updateTime", abstractEntity.getUpdateTime());

        abstractEntity.setUpdateUser(methodCallInformation.get().getUsername());
        setPropertyState(p_state, p_propertyNames, "updateUser", abstractEntity.getUpdateUser());

        abstractEntity.setUpdateUserIP(methodCallInformation.get().getIp());
        setPropertyState(p_state, p_propertyNames, "updateUserIP", abstractEntity.getUpdateUserIP());
    }

    private void setCreateInfo(AbstractEntity abstractEntity, Object[] p_state, String[] p_propertyNames, Optional<MethodCallInformation> methodCallInformation) {
        abstractEntity.setCreateTime(new Date());
        setPropertyState(p_state, p_propertyNames, "createTime", abstractEntity.getCreateTime());

        if(StringUtils.isBlank(abstractEntity.getCreateUser())){
            abstractEntity.setCreateUser(methodCallInformation.get().getUsername());
            setPropertyState(p_state, p_propertyNames, "createUser", abstractEntity.getCreateUser());
        }

        if(StringUtils.isBlank(abstractEntity.getCreateUserIP())){
            abstractEntity.setCreateUserIP(methodCallInformation.get().getIp());
            setPropertyState(p_state, p_propertyNames, "createUserIP", abstractEntity.getCreateUserIP());
        }
    }

    private void setPropertyState(Object[] propertyStates, String[] propertyNames, String propertyName, Object propertyState) {
        for(int i = 0; i < propertyNames.length; i++){
            if(propertyName.equals(propertyNames[i])){
                propertyStates[i] = propertyState;
                return;
            }
        }
    }
}
