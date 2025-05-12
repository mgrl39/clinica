package puig.xeill.Clinic.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

public class JwtUtil {
    //@Value("#{aplication.security.jwt.secret-key}")
    private String keyOrigin = "+Ck0TDxFSrboNXUZxLZEDSMZK4glQ3EJJyeFAaQe4ghKXftfH2Hyi5eDjEwTg";

    public String generateToken(String name) throws NoSuchAlgorithmException, KeyStoreException {
        byte[] keyBytes = Decoders.BASE64URL.decode(keyOrigin);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();

        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + 86400000);
        System.out.println("name: "+name);
        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(creationDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getNameFromToken(String token) {
        System.out.println(token);
        if (token.startsWith("Bearer ") && !token.substring(7).equals("null")) {

            byte[] keyBytes = Decoders.BASE64URL.decode(keyOrigin);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            String tokenFinal = token.substring(7);

            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(tokenFinal)
                    .getBody();

            System.out.println(claims);
            System.out.println(claims.getSubject());
            return claims.getSubject();
        }
        System.out.println("skip if");
        return null;

    }
}

