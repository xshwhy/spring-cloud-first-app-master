package com.fayfox.gateway.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class JWTUtil {
    @Value("${jwt.secret}")
    private String base64Key;

    public boolean checkJWT(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();

            String userId = (String) claims.get("user_id");
            if (userId != null && !userId.isEmpty()) {
                // Token解码正常且包含user_id
                return true;
            }
        } catch (ExpiredJwtException e) {
            // Token已过期
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            // 验签失败（伪造Token）
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(base64Key), "HmacSHA256");
    }
}
