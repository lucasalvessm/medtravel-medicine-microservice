package br.com.medtravel.microservice.medicine.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public final class ClassMapper {
    private ClassMapper() {
    }

    public static Object copyProperties(Object newObject, Object oldObject) {
        try {
            BeanUtils.copyProperties(newObject, oldObject);
            return newObject;
        } catch (IllegalAccessException | InvocationTargetException e) {
            return newObject;
        }
    }
}
