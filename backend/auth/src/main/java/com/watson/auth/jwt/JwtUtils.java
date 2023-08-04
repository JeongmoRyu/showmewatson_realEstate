package com.watson.auth.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1800000; // Access Token 만료 시간 (30분)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000; // Refresh Token 만료 시간 (7일)

    /* AccessToken 생성 : authId, authorities로 생성 */
    public String generateAccessToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername()); // authId
        claims.put("roles", userDetails.getAuthorities());

        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /* RefreshToken 생성 */
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        Date expirationDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    /* SecretKey로 토큰 검증 */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* 토큰에서 authId 추출 */
    public String getUsernameFromToken(String token) {
        JwtParserBuilder parserBuilder = Jwts.parserBuilder().setSigningKey(secretKey);
        Jws<Claims> jws = parserBuilder.build().parseClaimsJws(token);
        Claims claims = jws.getBody();

        return claims.getSubject();
    }

    /* Access Token, Refresh Token 생성 */
    public JwtTokens generateTokens(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = generateAccessToken(authentication);
        String refreshToken = generateRefreshToken(userDetails);

        return new JwtTokens(accessToken, refreshToken);
    }

}