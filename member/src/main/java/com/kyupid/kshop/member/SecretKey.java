package com.kyupid.kshop.member;

import java.security.Key;

public interface SecretKey {

    /**
     * 암호화 알고리즘이 적용된 비밀키를 반환한다
     * @return Key 타입의 암호화된 비밀키
     */
    Key keyEncrypted();
}
