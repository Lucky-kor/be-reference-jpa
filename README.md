# JPA 레퍼런스 코드

### Description
JPA 레퍼런스 코드는 학습 컨텐츠에서 사용되는 예제 코드 + 라이브 세션에서 사용되는 예제 코드로 구성이 되어 있습니다.
* 학습 컨텐츠용 예제 코드
  * [JPA 동작 방식 예제 코드](#jpa-동작-방식-예제-코드)
  * [엔티티 매핑 예제 코드](#엔티티-매핑-예제-코드)
* 라이브 세션용 예제 코드
  * [엔티티 매핑 테스트](#엔티티-매핑-테스트)
  * [Fetch 전략]()
  * [N + 1 문제](#n--1-문제에-대한-예제-코드)
  
> 예제 코드에 대한 더 구체적인 정보는 아래에서 확인하세요.

---

### JPA 동작 방식 예제 코드
JPA의 전체적인 동작 방식과 사용법을 이해하기 위한 예제 코드이며, 영속성 컨텍스트와 식별자 전략을 이해하기 위한 예제 코드로 구성되어 있습니다.

* 소스 코드 경로
  * [src/main/java/com/springboot/basic](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/basic)

---

### 엔티티 매핑 예제 코드
엔티티 매핑에 대한 예제 코드이며, 컬럼 매핑과 엔티티 간의 연관관계 매핑 예제 코드로 구성되어 있습니다.

* 소스 코드 경로
  * 컬럼 매핑 예제 코드
    * [src/main/java/com/springboot/entity_mapping/single_mapping](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/entity_mapping/single_mapping)
  * 엔티티 간의 연관관계 매핑 예제 코드
    * [src/main/java/com/springboot/entity_mapping](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/entity_mapping)

---

### 엔티티 매핑 테스트
엔티티 간의 연관관계 매핑이 정상적으로 이루어졌는지 테스트하는 예제 코드입니다.

* 소스 코드 경로
  * [src/main/java/com/springboot/entity_mapping_test](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/entity_mapping_test)

---

### Fetch 전략
Fetch 전략(LAZY/EAGER)을 테스트하는 예제 코드입니다.

* 소스 코드 경로
  * [src/main/java/com/springboot/fetch_strategy](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/fetch_strategy)

---

### N + 1 문제에 대한 예제 코드
JPA에서의 N + 1 문제를 해결하기 위한 예제 코드입니다.
* 소스 코드 경로
  * **Fetch Join을 사용한 예제 코드**
    * [src/main/java/com/springboot/n_plus_one_problem](https://github.com/codestates-seb/be-reference-jpa/tree/main/src/main/java/com/codestates/n_plus_one_problem)
