package com.aselmind.fsme.rest.config.security;

import com.aselmind.fsme.rest.company.CompanyDbExecute;
import com.aselmind.fsme.rest.company.user.CompanyUserAccountManager;
import com.aselmind.fsme.rest.company.user.CompanyUserDetailManager;
import com.aselmind.fsme.rest.master.superuser.SuperUserDetailManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountManager implements UserDetailsService {
    private final SuperUserDetailManager superUserDetailManager;
    private final CompanyUserDetailManager companyUserDetailManager;
    private final CompanyDbExecute companyDbExecute;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return companyDbExecute.getCompanyEntity().isPresent() ?
                companyUserDetailManager.loadUserByUsername(username) :
                superUserDetailManager.loadUserByUsername(username);
    }
}
