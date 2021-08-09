package io.github.anantharajuc.sbat.core_backend.security.jwt;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.core_backend.infra.exception.OtherExceptions;
import io.github.anantharajuc.sbat.core_backend.service.impl.OtherServicesImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtProvider 
{
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	private KeyStore keyStore;
	
	@PostConstruct
    public void init() 
	{
		otherServicesImpl.loadApplicationSettings();
		
        try 
        {
            keyStore = KeyStore.getInstance("JKS");
            
            InputStream resourceAsStream = getClass().getResourceAsStream("/"+otherServicesImpl.getKeystoreFileName());
            
            keyStore.load(resourceAsStream, otherServicesImpl.getKeystorePassword().toCharArray());
        } 
        catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) 
        {
            throw new OtherExceptions("Exception occurred while loading keystore", e);
        }
    }
	
	private PrivateKey getPrivateKey() 
	{
        try 
        {
            return (PrivateKey) keyStore.getKey(otherServicesImpl.getKeystoreAlias(), otherServicesImpl.getKeystorePassword().toCharArray());
        } 
        catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) 
        {
            throw new OtherExceptions("Exception occured while retrieving public key from keystore", e);
        }
    }
	
	private PublicKey getPublickey() 
	{
        try 
        {
            return keyStore.getCertificate(otherServicesImpl.getKeystoreAlias()).getPublicKey();
        } 
        catch (KeyStoreException e) 
        {
            throw new OtherExceptions("Exception occured while retrieving public key from keystore", e);
        }
    }
	
	public String generateToken(Authentication authentication)
	{
		return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusSeconds(otherServicesImpl.getJwtExpirationTime()))) 
                .compact();
	}
	
	public String generateTokenWithUserName(String username) 
	{
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(from(Instant.now()))
				.signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusSeconds(otherServicesImpl.getJwtExpirationTime())))
                .compact();
				
		
	}
	
	public boolean validateToken(String jwt) 
	{
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        
        return true;
    }
	
	public String getUsernameFromJwt(String token) 
	{
        Claims claims = parser()
			                .setSigningKey(getPublickey())
			                .parseClaimsJws(token)
			                .getBody();

        return claims.getSubject();
    }
}
