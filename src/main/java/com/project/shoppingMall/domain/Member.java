package com.project.shoppingMall.domain;

import com.project.shoppingMall.constant.Role;
import com.project.shoppingMall.dto.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "member")
@ToString
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(String name, String email, String password, String address, Role role) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.role = role;
        this.password = password;
    }

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {

        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(Role.ADMIN)
                .build();
    }
}
