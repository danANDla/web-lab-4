package danandla.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;


@Stateless
public class PasswordKitchen {
    private final String pepper;

    public PasswordKitchen(){
       pepper = getPepper();
    }

    public byte[] doHash(String password, byte[] salt) {
        if(pepper.equals("")) return null;
        byte[] hash = null;
        try{
            password += pepper;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e){
            System.out.println("can't do hash");
        }
        return hash;
    }

    public byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private String getPepper(){
        Properties prop = new Properties();
        String foundPepper = "";
        String fileName = "/home/danandla/BOTAY/web-programming/labs/web-4/web-lab-4/back/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            foundPepper = prop.getProperty("app.pepper");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find config file");
        } catch (IOException ex) {
            System.out.println("Unknown error while finding pepper");
        }
        return foundPepper;
    }

    private String getKey(){
        Properties prop = new Properties();
        String foundKey = "";
        String fileName = "/home/danandla/BOTAY/web-programming/labs/web-4/web-lab-4/back/app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
            foundKey = prop.getProperty("app.key");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find config file");
        } catch (IOException ex) {
            System.out.println("Unknown error while finding key");
        }
        return foundKey;
    }

    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(getKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(getKey()))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

//    public String createJWT(String id, String issuer, String subject, long ttlMillis){
//        try{
//            JwtBuilder jwtBuilder = JwtBuilder.create("builderSample");
//
//            jwtBuilder = jwtBuilder.issuer(issuer);
//            jwtBuilder = jwtBuilder.audience(Arrays.asList(new String[]{"one", "two", "three"}));
//            jwtBuilder = jwtBuilder.signWith("HS256", "shared secret");
//            jwtBuilder = jwtBuilder.claim("custom claim", "custom value");
//
//            long nowMillis = System.currentTimeMillis();
//            long expMillis = nowMillis + ttlMillis;
//            jwtBuilder = jwtBuilder.expirationTime(expMillis);
//            JwtToken token = jwtBuilder.buildJwt();
//            System.out.println(token);
//            return token.toString();
//        } catch (KeyException | InvalidClaimException | InvalidBuilderException | JwtException e){
//            System.out.println("bad jwtbuilder");
//        }
//        return null;
//    }

//    public static <JwtBuilder> String createJWT(String id, String issuer, String subject, long ttlMillis) {
//
//        JwtBuilder
//
//        //The JWT signature algorithm we will be using to sign the token
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        //We will sign our JWT with our ApiKey secret
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //Let's set the JWT Claims
//        JwtBuilder builder = Jwts.builder().setId(id)
//                .setIssuedAt(now)
//                .setSubject(subject)
//                .setIssuer(issuer)
//                .signWith(signatureAlgorithm, signingKey);
//
//        //if it has been specified, let's add the expiration
//        if (ttlMillis > 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//
//        //Builds the JWT and serializes it to a compact, URL-safe string
//        return builder.compact();
//    }
}
