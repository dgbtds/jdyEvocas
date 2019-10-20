package cn.ihep.jdy.release.dao.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private String username;
    private String password;
    private Long userId;
    private List<GrantedAuthority> authorities;

    public UserPrincipal(String username,
                         String password,
                         Long userId,
                         List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.authorities = authorities;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }//凭证未过期

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }
}
