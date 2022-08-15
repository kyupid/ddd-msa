package com.kyupid.kshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthService {

    private final MemberRepository memberRepository;
    private final JwtAuth jwtAuth;

    /**
     * 로그인 시도에 대한 유효성을 체크하여 JWT를 반환한다
     * @param requestData 로그인 정보를 담은 데이터
     * @return 접근 가능한 accessToken을 반환한다
     */
    public LoginResponseData login(LoginRequestData requestData) {

//        Member member = memberRepository.findById(requestData.getEmail())
//                .orElseThrow(() -> new RuntimeException("no id"));

        // 아래 helloMember 로 로그인 시도한다고 가정
        Member helloMember = new Member("hello@world.com", "1234");

        boolean hasSamePassword = helloMember.hasSamePassword(requestData.getPassword());

        if (!hasSamePassword) {
            throw new RuntimeException("wrong password");
        }

        String jwt = jwtAuth.encode(helloMember.getEmail());

        return new LoginResponseData(jwt);
    }
}
