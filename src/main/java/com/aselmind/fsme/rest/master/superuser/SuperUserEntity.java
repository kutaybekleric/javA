package com.aselmind.fsme.rest.master.superuser;

import com.aselmind.fsme.rest.common.BaseEntity;
import com.aselmind.fsme.rest.config.security.SecurityAuthority;
import com.aselmind.fsme.rest.config.security.SecurityRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PACKAGE)
public class SuperUserEntity extends BaseEntity implements UserDetails {
    @Column
    private String username;
    @Column
    private String userPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SecurityAuthority(SecurityRole.ROLE_SUPERUSER));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
