package com.aselmind.fsme.rest.config.security;

import com.aselmind.fsme.rest.company.CompanyDbExecute;
import com.aselmind.fsme.rest.master.company.CompanyEntity;
import com.aselmind.fsme.rest.master.company.CompanyManager;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AccountManager accountManager;
    private final CompanyDbExecute companyDbExecute;
    private final CompanyManager companyManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        final String companyCode;
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        Claims claims = JwtUtils.extractAllClaims(jwt);
        username = claims.getSubject();
        companyCode = claims.get(JwtUtils.COMPANY, String.class);
        if (StringUtils.isNotEmpty(username)
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            CompanyEntity companyEntity = null;
            if (!StringUtils.isEmpty(companyCode)) {
                companyEntity = companyManager.findByCode(companyCode).orElse(null);
            }

            companyDbExecute.run(companyEntity, () -> {
                UserDetails userDetails = accountManager.loadUserByUsername(username);

                if (JwtUtils.isTokenValid(claims, userDetails)) {
                    request.setAttribute("company", companyDbExecute.getCompanyEntity().orElse(null));
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    context.setAuthentication(authToken);
                }
            });

        }
        filterChain.doFilter(request, response);
    }
}