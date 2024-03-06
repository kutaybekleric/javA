package com.aselmind.fsme.rest.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

@UtilityClass
public class ControllerUtils {

    public String getPath(Class<?> clazz){
        return Optional.ofNullable(clazz.getAnnotation(RequestMapping.class))
                .map(RequestMapping::value)
                .flatMap(strings -> Arrays.stream(strings).findFirst())
                .orElseThrow();
    }
}
