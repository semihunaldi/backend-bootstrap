package com.semihunaldi.backendbootstrap.authserver.config.jwt.security;

import com.semihunaldi.backendbootstrap.authserver.config.jwt.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("jwt")
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Autowired
	private JWTProperties appProperties;

	public String generateToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getJwtExpirationInMs());

		return Jwts.builder()
				.setSubject(userPrincipal.getId())
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, appProperties.getJwtSecret())
				.compact();
	}

	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(appProperties.getJwtSecret())
				.parseClaimsJws(token)
				.getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try{
			Jwts.parser().setSigningKey(appProperties.getJwtSecret()).parseClaimsJws(authToken);
			return true;
		} catch(SignatureException ex){
			logger.error("Invalid JWT signature");
		} catch(MalformedJwtException ex){
			logger.error("Invalid JWT token");
		} catch(ExpiredJwtException ex){
			logger.error("Expired JWT token");
		} catch(UnsupportedJwtException ex){
			logger.error("Unsupported JWT token");
		} catch(IllegalArgumentException ex){
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}
