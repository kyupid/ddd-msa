## DDD-MSA
이 레포지토리는 DDD 로 MSA 를 구성하는 것이 주목적입니다

### 사용 기술
- Spring Boot
- Spring Data Jpa
- H2
- Springdoc
- Spring Cloud Netflix Eureka 
- Spring Cloud Loadbalancer
- Spring Cloud Gateway

### 서비스 아키텍쳐
각 서비스들은 2개 이상의 인스턴스를 사용하고 있다고 가정합니다.
<details>
  <summary>gateway 와 loadbalancer의 차이점은 무엇일까요?</summary>
  <img src="https://user-images.githubusercontent.com/59721293/186630267-acc8e24b-9994-4f57-96f5-b1a54c678a99.png">
<ul>
<li>
 게이트웨이 같은 경우 api 전처리 작업이 가능
</li>
<li>
 로드밸런서 같은 경우 기능 제공을 하는 것이 아니라 protocol or socket 레벨에서 트래픽을 분산작업을한다
</li>
</ul>
Reference: 
<a herf="https://stackoverflow.com/questions/61174839/load-balancer-and-api-gateway-confusion">https://stackoverflow.com/questions/61174839/load-balancer-and-api-gateway-confusion</a>

</details>

![2022-08-24_20-57-54](https://user-images.githubusercontent.com/59721293/186412746-961c76cc-813b-4832-a655-b5c76f772785.jpg)

### Eureka에 여러 인스턴스 등록

- 여러 인스턴스를 등록할때 랜덤포트

![image](https://user-images.githubusercontent.com/59721293/186801649-04cf33be-64f0-4c8a-839f-33266b5eb087.png)
![image](https://user-images.githubusercontent.com/59721293/186801663-409b1937-a2b5-4661-bb5c-d0b61fa6633a.png)

### Swagger 통합

- [Gateway 라우팅 주소 prefix 문제](https://github.com/kyupid/wt-3-week/issues/4)
- [Spring Cloud Gateway and Springdoc OpenAPI Integration](https://github.com/kyupid/wt-3-week/issues/5)

![image](https://user-images.githubusercontent.com/59721293/186801724-f49a3ab3-7399-4008-96f6-c3721056c88a.png)

### 각 Service 패키지 구조

- presentation (표현 영역)
- application (응용 영역)
  - domain을 활용하여 원하는 데이터를 조립하여 표현영역으로 리턴
- domain (도메인 영역) - 핵심 로직
- infra (인프라스트럭쳐 영역) - 실제 구현

### REST API
#### member
- 로그인
  - 이메일-패스워드 정상입력
  - 이메일-패스워드 불일치

#### order->product
- 토큰
  - 정상 토큰
  - 비정상 토큰
- 주문하기
  - 비정상 프로퍼티 
  - 정상 프로퍼티
    - 재고 있을 경우
    - 재고 없을 경우
      - 여러 프로덕트 주문 중에 하나는 재고가 없을 경우
- 주문 단건 조회
  - 내 주문 조회
  - (비정상) 다른 사람 주문 조회
- 전체 주문 조회
- 주문 (배송지) 수정 PUT
- 주문 취소 PATCH

#### product

- CRUD

### 트랜잭션 일관성 처리

주문할때 단순히 재고처리뿐만 아니라 배송, 결제 등의 도메인 서비스가 추가 된다면,   
트랜잭션 일관성에 대해서 생각을 해보아야할 것입니다.   
[여기서 확인 가능](https://github.com/kyupid/wt-2-week)

### References

#### Primary references to implement the project

- [DDD 시작하기 by 최범균님](http://www.yes24.com/Product/Goods/108431347)
- [분산 트랜잭션 구현 by 유영모님](https://www.popit.kr/rest-%EA%B8%B0%EB%B0%98%EC%9D%98-%EA%B0%84%EB%8B%A8%ED%95%9C-%EB%B6%84%EC%82%B0-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B5%AC%ED%98%84-1%ED%8E%B8/)

#### References that helped implement features in the project 
- [[Baeldung] Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)
- [[Spring Boot Doc] Using the @SpringBootApplication Annotation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.using-the-springbootapplication-annotation)
- [[StackOverFlow] What exactly is Field Injection and how to avoid it?](https://stackoverflow.com/questions/39890849/what-exactly-is-field-injection-and-how-to-avoid-it)
- [[인프런] 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-%ED%99%9C%EC%9A%A9-1/dashboard)
- [[인프런] 실전! 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-JPA-API%EA%B0%9C%EB%B0%9C-%EC%84%B1%EB%8A%A5%EC%B5%9C%EC%A0%81%ED%99%94/dashboard)
- [[인프런] 실전! 스프링 데이터 JPA](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%8D%B0%EC%9D%B4%ED%84%B0-JPA-%EC%8B%A4%EC%A0%84/dashboard)
- [Server vs Client-side load balancing](https://www.linkedin.com/pulse/server-vs-client-side-load-balancing-ramit-sharma/)
- [[MSA] Spring Cloud Ribbon 개념](https://sabarada.tistory.com/54)
- [Load balancer and API Gateway confusion](https://stackoverflow.com/questions/61174839/load-balancer-and-api-gateway-confusion)
- [Spring Cloud Loadbalancer Quick Guide](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
- [[Baeldung] Spring Cloud LoadBalncer Quick Guide](https://www.baeldung.com/spring-cloud-load-balancer)
- [Spring Cloud Gateway and Springdoc OpenAPi integration](https://stackoverflow.com/questions/66953605/spring-cloud-gateway-and-springdoc-openapi-integration)
- [Microservices API Documentation with Springdoc OpenAPI](https://piotrminkowski.com/2020/02/20/microservices-api-documentation-with-springdoc-openapi/)
- https://github.com/springdoc/springdoc-openapi/issues/1366
- [What is Two phase commit?](https://dongwooklee96.github.io/post/2021/03/26/two-phase-commit-%EC%9D%B4%EB%9E%80/)
- [[Baeldung] Spring RestTemplate Error Handling](https://www.baeldung.com/spring-rest-template-error-handling)
