package com.aselmind.fsme.rest.utils;

import com.aselmind.fsme.rest.master.company.CompanyEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@UtilityClass
public class RequestUtils {

    public static Optional<ServletRequestAttributes> getRequestAttributes() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static Optional<CompanyEntity> getCompany() {
        return getRequestAttribute("company").map(o -> (CompanyEntity) o);
    }

    private static Optional<Object> getRequestAttribute(String attribute) {
        return getRequestAttributes()
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getAttribute(attribute));
    }

    public Optional<CurrentUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return Optional.empty();
        }
        CurrentUser currentUser = CurrentUser.builder()
                .userDetails((UserDetails) authentication.getPrincipal())
                .companyEntity(getCompany().orElse(null))
                .build();
        return Optional.of(currentUser);
    }
}
