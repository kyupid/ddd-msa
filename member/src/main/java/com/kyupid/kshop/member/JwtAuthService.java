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

        Member member = memberRepository.findByEmail(requestData.getEmail())
                .orElseThrow(() -> new RuntimeException("no id"));


        boolean hasSamePassword = member.hasSamePassword(requestData.getPassword());

        if (!hasSamePassword) {
            throw new RuntimeException("wrong password");
        }

        String jwt = jwtAuth.encode(member.getMemberId());

        return new LoginResponseData(jwt);
    }
}
