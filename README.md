## 사용 기술
- Spring Boot
- Spring Data Jpa
- H2
- Springdoc

---
## 프레젠테이션
### 설명
![2022-08-19_01-14-20](https://user-images.githubusercontent.com/59721293/185443908-f2e7cd20-71d8-46b9-bb82-f33fdeb36e11.jpg)
- DDD 개념을 적용한 MSA 적용

![image](https://user-images.githubusercontent.com/59721293/185558132-f466bd9c-64b1-4f03-9122-3c638561114b.png)
- 요청 흐름

![image](https://user-images.githubusercontent.com/59721293/185558880-521fac3f-913a-4d2d-affe-f53894c0f48f.png)
- Stock을 예약한다, [Why?](https://github.com/kyupid/wt-2-week/issues/1#issuecomment-1220311205)

![image](https://user-images.githubusercontent.com/59721293/185560047-1610b395-54f0-43bd-8479-89d298b2b510.png)
- 다른 서버에서 실패를 하면 다시 Product로...


![2022-08-19_01-16-42](https://user-images.githubusercontent.com/59721293/185444364-9a1e521c-3c2f-422d-8843-5a4ce7626d70.jpg)
![2022-08-19_01-17-38](https://user-images.githubusercontent.com/59721293/185444549-d29a1c7c-918c-4e6a-8203-7aec001c0380.jpg)
- 패키지 구조
    - presentation (표현 영역)
    - application (응용 영역)
      - domain을 활용하여 원하는 데이터를 조립하여 표현영역으로 리턴
    - domain (도메인 영역) - 핵심 로직
    - infra (인프라스트럭쳐 영역) - 실제 구현

### 시현
#### member
- 로그인
  - 이메일-패스워드 정상입력
  - 이메일-패스워드 불일치

#### order
- 토큰
  - 정상 토큰
  - 비정상 토큰
- 주문
  - 비정상 프로퍼티 
  - 정상 프로퍼티
    - 재고 있을 경우
    - 재고 없을 경우
      - 여러 프로덕트 주문 중에 하나는 재고가 없을 경우
