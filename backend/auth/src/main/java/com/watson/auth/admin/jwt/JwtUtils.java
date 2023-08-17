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

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 604800000 * 400; // Access Token 만료 시간 (30분)
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 604800000 * 400; // Refresh Token 만료 시간 (7일)

    /* AccessToken 생성 : authId, authorities로 생성 */
    public String generateAccessToken(String authId, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("authId", authId);
        log.info("authId : " + authId);
        claims.put("role", role);
        log.info("role : " + role);

        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        log.info("issuedAt : " + new Date());
        log.info("Expiration : " + expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(authId)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

//    public String generateAccessToken(OAuth2AuthenticationToken oauthToken) {
//
//        DefaultOAuth2User oauthUser = (DefaultOAuth2User) oauthToken.getPrincipal();
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("authId", oauthUser.getAttribute("id"));
//        log.info("authId : " + oauthUser.getAttribute("id"));
//        claims.put("role", oauthToken.getAuthorities());
//        log.info("role : " + oauthToken.getAuthorities());
//
//        Date expirationDate = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME);
//
//        Map<String, Object> header = new HashMap<>();
//        header.put("typ", "JWT");
//        header.put("alg", "HS256");
//
//        log.info("oauthUser.getName() : " + oauthUser.getName());
//
//        log.info("issuedAt : " + new Date());
//        log.info("Expiration : " + expirationDate);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setHeader(header)
//                .setSubject(oauthUser.getName())
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }

    /* RefreshToken 생성 */
    public String generateRefreshToken(String authId) {

        Map<String, Object> claims = new HashMap<>();

        Date expirationDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return Jwts.builder()
                .setClaims(claims)
                .setHeader(header)
                .setSubject(authId)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /* AccessToken 검증 */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("유효하지 않은 JWT Token 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT Token 입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT Token 입니다.", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string이 비어있습니다.", e);
        }
        return false;
    }

    /* 토큰에서 authId 추출 */
    public String getUsernameFromToken(String token) {
        JwtParserBuilder parserBuilder = Jwts.parserBuilder().setSigningKey(secretKey);
        Jws<Claims> jws = parserBuilder.build().parseClaimsJws(token);
        Claims claims = jws.getBody();

        return claims.getSubject();
    }

    /* Access Token, Refresh Token 생성 */
    public JwtTokens generateTokens(String authId, String role) {

        String accessToken = generateAccessToken(authId, role);
        String refreshToken = generateRefreshToken(authId);

        return new JwtTokens(accessToken, refreshToken);
    }

//    public JwtTokens generateTokens(OAuth2AuthenticationToken oauthToken) {
//        DefaultOAuth2User oauthUser = (DefaultOAuth2User) oauthToken.getPrincipal();
//
//        String accessToken = generateAccessToken(oauthToken);
//        String refreshToken = generateRefreshToken(oauthUser);
//
//        return new JwtTokens(accessToken, refreshToken);
//    }

    public String getAuthIdByAccessToken(String accessToken) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody();
        String providerId = claims.getSubject();
        log.info("claims.getSubject() : " + claims.getSubject()); // providerId
        String authId = "kakao_" + providerId; // authentication에서 provider name 받아올 수 있는지 확인
        log.info("authId : " + authId);

        return authId;
    }

}