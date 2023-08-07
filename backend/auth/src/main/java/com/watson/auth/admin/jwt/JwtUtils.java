package com.watson.auth.admin.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 1800000; // Access Token 만료 시간 (30분)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000; // Refresh Token 만료 시간 (7일)

    /* AccessToken 생성 : authId, authorities로 생성 */
    public String generateAccessToken(OAuth2AuthenticationToken oauthToken) {

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) oauthToken.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("authId", oauthUser.getAttribute("id"));
        log.info("authId : " + oauthUser.getAttribute("id"));
        claims.put("role", oauthToken.getAuthorities());
        log.info("role : " + oauthToken.getAuthorities());

        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        log.info("oauthUser.getName() : " + oauthUser.getName());

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(oauthUser.getName())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /* RefreshToken 생성 */
    public String generateRefreshToken(DefaultOAuth2User oauthUser) {

        Map<String, Object> claims = new HashMap<>();

        Date expirationDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(oauthUser.getName())
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
    public JwtTokens generateTokens(OAuth2AuthenticationToken oauthToken) {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) oauthToken.getPrincipal();

        String accessToken = generateAccessToken(oauthToken);
        String refreshToken = generateRefreshToken(oauthUser);

        return new JwtTokens(accessToken, refreshToken);
    }

}