package com.laluna.laluna.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUserDetails extends User {
    private String mphone;
    private String address;
    private String email;

    public MyUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         String mphone, String address, String email) {
        super(username, password, authorities);
        this.mphone = mphone;
        this.address = address;
        this.email = email;
    }
    public String getMphone() {
        return mphone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}