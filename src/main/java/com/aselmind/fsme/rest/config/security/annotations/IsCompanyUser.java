package com.aselmind.fsme.rest.config.security.annotations;

import com.aselmind.fsme.rest.config.security.SecurityRole;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see com.aselmind.fsme.rest.config.security.SecurityRole
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('OWNER','EMPLOYEE')")
public @interface IsCompanyUser {

}
