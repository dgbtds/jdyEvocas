package cn.ihep.jdy.release.service.security;

import cn.ihep.jdy.release.dao.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtToken {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public Long getUserFromToken(String token) {
        Long uid;

        try {
            final Claims claims = getClaimsFromToken(token);
            uid = Long.valueOf(claims.getSubject());
        } catch (Exception e) {
            uid = 0L;
        }

        return uid;
    }

    private Date getCreatedDateFromToken(String token) {
        Date created;

        try {
            Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }

        return created;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;

        try {
            Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }

        return expiration;
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        List<GrantedAuthority> authorities;

        try {
            Claims claims = getClaimsFromToken(token);
            String strAuthority = (String) claims.get("scope");
            authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList(strAuthority);
        } catch (Exception e) {
            authorities = null;
        }

        return authorities;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    public String generateToken(UserPrincipal principal) {
        String strAuthority = "";
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)principal.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            strAuthority += grantedAuthority.getAuthority() + ",";
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("scope",strAuthority);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getUserId() + "")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token) {
        Date created = getCreatedDateFromToken(token);
        return !isTokenExpired(token);
    }

}
