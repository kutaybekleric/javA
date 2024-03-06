package com.aselmind.fsme.rest.utils;

import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class RandomUtils {

    public String getRandom(String prefix) {
        return getRandom(prefix, "");
    }

    public String getRandom(String prefix, String suffix) {
        return Objects.requireNonNullElse(prefix, "") +
                UUID.randomUUID() +
                Objects.requireNonNullElse(suffix, "");
    }
}
