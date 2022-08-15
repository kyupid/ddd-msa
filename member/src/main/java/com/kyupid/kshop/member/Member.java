package com.kyupid.kshop.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    private Long memberId;

    private String email;

    private String password;

    protected Member() {
    }

    public Member(Long memberId, String email, String password) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
    }

    public boolean hasSamePassword(String inputPw) {
        return password.equals(inputPw);
    }
}
