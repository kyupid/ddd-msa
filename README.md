### 사용 기술
- Spring Boot
- Spring Data Jpa
- H2
- Springdoc
- Spring Cloud Netflix Eureka 
- Spring Cloud Loadbalancer
- Spring Cloud Gateway

### 서비스 아키텍쳐

<details>
  <summary>gateway 와 loadbalancer</summary>
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

### REST API

- 배송지 수정 -> PUT
- 주문 취소 -> PATCH
