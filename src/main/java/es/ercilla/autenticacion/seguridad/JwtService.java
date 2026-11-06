package es.ercilla.autenticacion.seguridad;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HexFormat;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtService {
    public static final int DURACION_SEGUNDOS = 30 * 60;
    private final SecretKey clave;
    private final SecureRandom random = new SecureRandom();

    public JwtService(String claveBase64) {
        byte[] bytes = Base64.getDecoder().decode(claveBase64);
        if (bytes.length < 32) throw new IllegalArgumentException("JWT_SECRET_BASE64 debe contener >=32 bytes aleatorios");
        this.clave = Keys.hmacShaKeyFor(bytes);
    }

    public String generar(String usuario, String rol, String csrf) {
        Instant ahora = Instant.now();
        return Jwts.builder()
                .issuer("AutenticacionJWTOracle")
                .subject(usuario)
                .claim("rol", rol)
                .claim("csrf", csrf)
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(ahora.plusSeconds(DURACION_SEGUNDOS)))
                .signWith(clave, Jwts.SIG.HS256)
                .compact();
    }

    public Claims validar(String token) {
        Claims claims = Jwts.parser().verifyWith(clave).build().parseSignedClaims(token).getPayload();
        if (!"AutenticacionJWTOracle".equals(claims.getIssuer()) ||
                claims.getSubject() == null || claims.getSubject().isBlank() ||
                claims.get("rol", String.class) == null ||
                claims.get("csrf", String.class) == null) {
            throw new IllegalArgumentException("JWT sin claims requeridas");
        }
        return claims;
    }

    public String nuevoCsrf() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return HexFormat.of().formatHex(bytes);
    }
}
