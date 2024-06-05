package com.example.token1.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Member implements UserDetails {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    private String address; // 도로명 주소

    private String phone;

    private String profileImg;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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

//@Builder
//@Getter
//@Setter
//@Data
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Table(name="Member")
//public class Member implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @JsonProperty("username")
//    private String username;
//
//    @JsonProperty("password")
//    private String password;
//
//    @JsonProperty("userImgUrl")
//    private String userImgUrl;
//
//    @JsonProperty("email")
//    private String email;
//
//    @JsonProperty("role")
//    private String role;
//
//    @JsonProperty("provider")
//    private String provider;
//
//    @JsonProperty("providerId")
//    private String providerId;
//
//    @CreationTimestamp
//    private Timestamp createDate;
//
//    @Builder
//    public Member(String username, String password, String userImgUrl, String email, String role, String provider, String providerId, Timestamp createDate) {
//        this.username = username;
//        this.password = password;
//        this.userImgUrl = userImgUrl;
//        this.email = email;
//        this.role = role;
//        this.provider = provider;
//        this.providerId = providerId;
//        this.createDate = createDate;
//    }
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
