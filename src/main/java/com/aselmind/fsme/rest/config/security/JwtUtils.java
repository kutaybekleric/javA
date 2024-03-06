package com.aselmind.fsme.rest.config.security;

import com.aselmind.fsme.rest.master.company.CompanyEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class JwtUtils {
    public static final String COMPANY = "company";
    private static final String SECRET = "ASEL51SendustriyelElk";
    private static final long EXPIRATION_TIME_1D = 86_400_000; // 1 day
    private static final long EXPIRATION_TIME_10D = 864_000_000; // 10 days

    public static String generateToken(CompanyEntity company, String username, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        if (company != null) {
            claims.put(COMPANY, company.getCode());
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (rememberMe ? EXPIRATION_TIME_10D : EXPIRATION_TIME_1D)))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenValid(Claims claims, UserDetails userDetails) {
        final String userName = claims.getSubject();
        final boolean usernameMatches = StringUtils.equals(userName, userDetails.getUsername());

        final Date expiration = claims.getExpiration();
        final boolean isExpired = expiration.before(new Date());

        return usernameMatches && !isExpired;
    }
}
