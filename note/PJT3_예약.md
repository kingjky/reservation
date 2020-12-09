# PJT3_예약서비스



## **1. 프로젝트 개요**

이번 프로젝트에서는 인터넷 예약관리 시스템을 만들어보겠습니다.

**실제 네이버에서 운영하는 예약 시스템과 유사한 서비스입니다.**

**전시, 연극, 뮤지컬 등의 콘텐츠가 보이고, 각 아이템을 예약할 수 있어야 합니다.**

 

물론 네이버 예약 서비스는 이 순간에도 계속 변경되고 있습니다.

따라서 여러분들이 지금 만들 예정인 서비스와 실제 네이버 예약서비스와는 약간 차이가 있을 겁니다. ^^

여러분들은 저희가 제공한 기획서 내용과, 데모 사이트를 살펴보시면서 개발하시면 됩니다.

 

이번 파트부터 마지막 파트까지는 총 4단계로 이어지는 규모가 큰 프로젝트입니다.   

그만큼 완성도 있는 결과를 만들 수 있고, 더불어 개발의 복잡도가 올라갈 겁니다.

**이번 파트에서는 전체 예약서비스 중에 '메인화면'을 구성합니다.**

서비스 기능 면으로는 일부분이지만, 개발환경 구축 등을 해야 하므로 가장 많은 시간이 들어갈 수 있는 파트입니다.

그만큼 많은 시간 투자가 예상됩니다.



### 기획서

- https://www.edwith.org/downloadFile/fileDownload?attachmentId=74212&autoClose=true



## 2. 프로젝트의 개발스펙

**웹프론트엔드 기술요구사항**

- DOMContentloaded 이후에 모든 자바스크립트 로직이 동작하게 합니다.
- 상단영역의 애니메이션은 CSS3의 transition과 transform 속성을 활용해서 구현해야 합니다.
- TABUI로 구성되는 카테고리 아이템이 노출되는 영역의 HTML은 카테고리별로 각각 만들지 않고 하나로 만들어 재사용합니다.
- 카테고리 탭을 선택할 때마다, Ajax 요청을 해서 데이터를 가져와야 합니다.
- 탭 메뉴 (전시/뮤지컬/콘서트 등)는 Event delegation으로 구현합니다.
- Template 코드를 javascript 함수 안에 보관하지 않고, 별도 분리시켜서 사용해야 합니다. (HTML에 script태그 안에 보관한다던가)
- 함수 하나에 여러 개의 기능을 넣지 않고, 함수를 여러 개로 분리합니다.

 

**웹백엔드 기술요구사항**

- 제공된 SQL을 이용해서 테이블을 생성하고, 샘플데이터를 입력합니다.
- maven을 이용해서 웹 어플리케이션 프로젝트를 작성합니다.
- 학습했던 것처럼 controller,service,dao로 레이어드 아키텍쳐를 구성합니다.
- spring JDBC를 이용하여 주어진 테이블로부터 입력, 수정, 삭제, 조회하는 DAO와 DTO를 작성합니다.
- 서비스 인터페이스를 작성하고 해당 서비스 인터페이스에 비지니스 메소드를 작성합니다.
- 서비스 인터페이스를 구현하는 클래스를 작성합니다.
- 해당 구현 클래스의 메소드에 적절한 트랜잭션에 관련된 애노테이션을 사용합니다.
- 클라이언트에게 Web API를 제공하기 위해 RestController 를 작성합니다.



## 3. 프로젝트 개발과정 참고

**1. maven 프로젝트를 생성합니다.**

groupId 와 artifactId 는 임의로 지정합니다.

 

**2. MySQL 에서 프로젝트에 사용할 database 와 사용자 계정을 생성합니다.**

생성한 데이터베이스와 계정정보는 src/main/resources/application.properties 파일에 다음과 같이 설정합니다.

```java
spring.datasource.driver-class-name=com.mysql.jdbc.Driver 
spring.datasource.url=jdbc:mysql://domain:port/dbName?useUnicode=true&characterEncoding=utf8 
spring.datasource.username=dbUserName
spring.datasource.password=dbPasswd
```

 

**3. 프로젝트 루트 폴더에 .gitignore파일을 생성합니다. ([gitignore 참고자료](https://git-scm.com/docs/gitignore))**

해당 파일에는 다음의 내용을 입력합니다.

```java
/src/main/resources/application.properties
target
.classpath
.project
.settings
```

 

**4. 생성한 데이터베이스에 접속하여 주어진 sql을 실행합니다.**

먼저 ddl.sql의 내용을 실행하여 테이블을 생성하고, dml.sql의 내용을 실행하여 샘플 데이터를 추가합니다. [코드 바로가기](https://drive.google.com/drive/folders/12eorsQAS1tV4C4X7hkSDHW8uJ4RJHviR)

 

**5. sample이미지가 있는 압축파일인 img.zip을 webapp 폴더에 압축 해제합니다.**

webapp폴더에 img와 img_map 폴더 아래에 샘플 이미지가 위치합니다.

 

**6. Spring MVC, Spring JDBC를 사용하기 위한 Spring설정 파일들을 작성합니다.**

 

**7. 샘플 데이터를 읽어 들여 메인화면을 출력하기 위한 DTO, Controller, Service, Repository를 알맞게 작성합니다.**

 

**8. web API 스펙은 아래와 같습니다.**

**카테고리 목록 구하기 [API 스펙 & test](http://49.236.147.192:9090/swagger-ui.html)**

- GET (카테고리 목록 구하기): /api/categories

**상품 목록 구하기 [API 스펙 & test](http://49.236.147.192:9090/swagger-ui.html)**

- GET (상품 목록 구하기): /api/products

**프로모션 정보 구하기 [API 스펙 & test](http://49.236.147.192:9090/swagger-ui.html)**

- GET (프로모션 목록 구하기): /api/promotions

 



**9. image type 설명**

- th: thumbnail (썸네일 사진 - 상품리스트 혹은 프로모션 정보에서 보여주는 이미지)
- ma: main (메인 사진 - 상품 상세정보에서 보여주는 이미지)
- et: etc (기타 사진 - 상품 상세정보에서 추가적으로 보여주는 기타 이미지)



## 참고자료

**reservation** [ZIP](https://www.edwith.org/downloadFile/fileDownload?attachmentId=128353&autoClose=true)

이 파일들은 이번 프로젝트인 프로젝트3부터 프로젝트6까지 사용합니다. 다운로드 받은 파일을 압축 해제하면 resevation-html-base 디렉토리가 있는데 여기에서 제공하는 파일들을 이용하여 FE에 필요한 코드를 작성합니다. 필요에 따라 임의로 수정하셔도 괜찮습니다. 

[![img](https://cphinf.pstatic.net/mooc/20181226_300/15457880508041YuRw_PNG/reservation_ERD_3.png?type=w760)](https://www.edwith.org/boostcourse-web/project/8/content/7#)

[**(참고) SQL Joins Explained**http://www.sql-join.comDefinitions of SQL and SQL join, as well as an example of a SQL join using tables from a relational database.](http://www.sql-join.com/)

[**(참고) SQL Joins**https://www.w3schools.comWell organized and easy to understand Web building tutorials with lots of examples of how to use HTML, CSS, JavaScript, SQL, PHP, and XML.](https://www.w3schools.com/sql/sql_join.asp)

[**(참고) 테이블 JOIN - SQL 프로그래밍 배우기 (Learn SQL Programming)**http://www.sqlprogram.com](http://www.sqlprogram.com/Basics/sql-join.aspx)



# 평가기준표

## Backend

### 기능

| 기준                   | 세부항목                                                     |
| :--------------------- | ------------------------------------------------------------ |
| 카테고리노출영역(탭UI) | - 전체리스트가 Ajax를 통해서 화면에 4개의 아이템이 노출된다. - 탭별로 전체갯수가 상단에 노출되야 한다. - 각 아이템(전시상품)은 이미지/제목/장소/설명이 노출되야 한다. - 탭을 누르면 다른 카테고리 콘텐츠 4개가 다시 노출된다. - 더보기를 누르면 4개씩 노출되야 한다. 4개보다 적으면 적은 만큼 노출되야 한다. - 더보여줄 데이터가 없다면 더보기는 사라진다. - TOP영역이 선택되면, 화면 맨 위로 이동한다 |

### 소스코드

| 기준                                   | 세부항목                                                     |
| :------------------------------------- | ------------------------------------------------------------ |
| JAVA - 이름규칙                        | - JAVA Naming Conventions 을 지킨다.[(참고 1)](http://www.oracle.com/technetwork/java/codeconventions-135099.html) [(참고 2)](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/variables.html) - 클래스의 이름과 메소드의 이름은 직관적으로 작성하도록 한다. 클래스의 이름과 메소드의 이름만 보아도,어떤 기능을 가지고 있을지 어떤 내용이 구현되어 있을지 짐작할 수 있어야 한다. - 코드를 읽는 사람이 개념을 쉽게 파악할 수 있도록 읽기 쉬운 코드를 작성하도록 한다. 예를 들어 변수 이름을 구체적으로 작성하도록 한다. |
| JAVA - 중복된 코드 제거 및 코드 구조화 | - 중복된 코드가 있다면, 별도의 메소드나 클래스로 분리하도록 한다. - 하나의 메소드가 너무 많은 코드를 담지 않는다. 코드의 양이 많을 경우 private한 메소드를 이용해서 메소드를 분리하거나 별도의 객체를 만들어 사용하도록 한다. - 클래스의 코드 길이가 너무 길어진다면, 해당 클래스가 몇개의 클래스로 분리될 수 있는지 고민한다. - 변수는 최대한 덜 사용하고, 최대한 가볍게 만들어 가독성을 높이도록 한다. |
| JAVA - 가독성                          | - 조건문의 경우 긍정적이고 흥미로운 (주 흐름에 해당하는) 경우가 앞쪽에 위치하도록 한다. - 삼항연산자, do-while문장은 코드 가독성을 떨어트리니 되도록 사용하지 않는다. - 블럭이 너무 많이 중첩되면 코드를 읽기 어려워진다. 블록을 private메소드로 추출할 수 있는지 고민한다. -코드는 빈줄을 이용해 커다란 블록을 논리적인 문단으로 구분한다. - 코드는 들여쓰기를 잘 지키도록 한다. - 필요하지 않은 코드는 제거한다. |
| JAVA - 프로젝트 생성                   | - pom.xml 파일에 알맞은 의존성을 설정할 수 있어야 한다. - .gitignore파일이 주어진 요구사항대로 알맞게 작성되어 있어야 한다. |
| JAVA - 프로젝트 구조                   | - Controller, Service, Repository를 사용하여 구현되어 있어야 한다. - Controller에서 Service를 Service에서 Repository의 기능을 호출할 수 있지만, 그 반대는 허용되지 않는다. |
| JAVA - Web API                         | - 전체, 카테고리별 상품정보를 제공하는 Web Api를 제공한다. 해당 Web API는 카테고리 id를 파라미터로 받을 수 있다. 카테고리 id를 받지 않을 경우엔 전체 상품정보를 제공한다. 해당 Web API는 순번을 파라미터로 받을 수 있다. 파라미터로 순번을 못 받을 경우 첫번째 데이터부터 4번째 데이터까지 제공한다. 순번을 받으면 순번 이후의 상품정보를 최대 4건을 제공한다. - 전체, 카테고리별 상품 수를 제공하는 Web Api를 제공한다. - 프로모션 정보를 알맞게 제공하는 Web Api를 제공한다. - SQL 문을 표준에 맞추어 작성하며 각 DBMS 별로 구현된 문법 사용은 최소화한다. - 전체, 카테고리별 상품 수는 GROUP BY 구문을 이용한다. - 데이터 수를 조회하는 경우 COUNT(*) 를 사용한다. |

## Frontend

### 화면 레이아웃

| 기준                | 세부항목                                                     |
| :------------------ | ------------------------------------------------------------ |
| 메인페이지 레이아웃 | - HTML/CSS가 제공되는 것을 기본으로 작성해야 하며, 메인페이지의 화면구성은 아래 기획서대로 보여야 한다.  [기획서 보기](https://docs.google.com/presentation/d/1i2IC1yIH5ACFCvCH4EMVv_3Zw2oltRvHK94amyNEKbs/edit#slide=id.p6) - GNB영역, 프로모션영역, 카테고리영역(6개의 메뉴구성). 상품리스트(기본 4개), 더보기 화면이 노출되야 한다. |

### 기능

| 기준                   | 세부항목                                                     |
| :--------------------- | ------------------------------------------------------------ |
| 프로모션영역           | - 프로모션영역의 이미지는 1개보다 많으며, 자동으로 슬라이딩되어 넘어간다. - 슬라이딩 이미지는 애니메이션이 되면서 좌측으로 이동하는 것이 보여야한다. - 마지막 이미지에 다다르면 처음것이 그 다음으로 노출되야 한다. 마지막것에서 처음내용이 다시 보이는 부분은 끊겨서 노출되도 상관 없으며, 중요한 건 다시 처음부터 슬라이딩이 계속 되야 한다는 것이다. (엄격하진 않지만 최대한 자연스럽게 동작하려고 해야 한다) - 이동되는 속도는 적절하게 처리되면 된다. - 슬라이딩 이미지는 마우스나 터치에 반응하지는 않는다. |
| 카테고리노출영역(탭UI) | - 전체리스트가 Ajax를 통해서 화면에 4개의 아이템이 노출된다. - 탭별로 전체갯수가 상단에 노출되야 한다. - 각 아이템(전시상품)은 이미지/제목/장소/설명이 노출되야 한다. - 탭을 누르면 다른 카테고리 콘텐츠 4개가 다시 노출된다. - 더보기를 누르면 4개씩 노출되야 한다. 4개보다 적으면 적은 만큼 노출되야 한다. - 더보여줄 데이터가 없다면 더보기는 사라진다. - TOP영역이 선택되면, 화면 맨 위로 이동한다 |

### 소스코드

| 기준                                 | 세부항목                                                     |
| :----------------------------------- | ------------------------------------------------------------ |
| HTML,CSS                             | - 제공되는 HTML/CSS를 수정할 수 있으며, 제공되는 코드의 형태의 구조를 지나치게 수정하지 않도록 한다. - 기존에 제공되는 class와 id규칙에 따라서 새로운 내용을 추가할 수 있다. - TABUI로 구성되는 카테고리 아이템이 노출 되는 영역의 HTML은 카테고리별로 각각 만들지 않고 하나로 만들어 재사용한다. |
| CSS3 를 사용한 애니메이션 구현       | - 상단영역의 애니메이션은 CSS3의 transition과 transform 속성을 JavaScript로 제어하면서 구현해야 한다. |
| 효과적인 브라우저 기반 개발 방식사용 | - DOMContentloaded 이후에 모든 자바스크립트 로직이 동작되게 한다. - 카테고리 탭을 선택할 때마다, Ajax요청을 해서 데이터를 가져와야 한다. - 탭메뉴 (전시/뮤지컬/콘서트 등)는 Event delegation으로 구현한다. |
| JavaScript 코드 개선하기             | - Template 코드를 javascript 함수안에 보관하지 않고, 별도 분리시켜서 사용해야 한다. (HTML에 script태그 안에 보관한다던가) - 함수 하나에 여러개의 기능을 넣지 않고,함수를 여러개로 분리한다. |



# 알게된 점

## UTF-8 Encoding 실패 시

- web.xml 에 filter를 적용해준다

```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>
        org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



## Bezier 곡선

cubic-bezier(1, .01, .32, 1)

## Exception

### Checked Exception

- Compile 단계에서 검사하는 예외
- try/catch, throws를 이용해 처리해야 실행이 가능

- ex) IOException, SQLException

### Unchecked Exception (= RuntimeException)

- Runtime 중에 검사하는 예외
- 실행 중 발생 가능
- 모든 Unchecked Exception은 RuntimeException이며, 모든 RuntimeException은 Unchecked Exception이다.
- ex) NullPointException, IllegalArgumentException, IndexOutOfBoundException

### Exception Handling (예외 처리)

- Handling 되지 않은 Exception을 만나면 프로그램은 비정상적 종료를 하게 된다. 따라서 예상되는 Exception에 대해 미리 Handling을 추가하고 프로그램 실행이 유지되도록 한다.

- 예외 From "이펙티브 자바"

  - 아이템 69. 예외는 진짜 예외 상황에만 사용하라
  - 아이템 70. 복구할 수 있는 상황에는 검사 예외를, 프로그래밍 오류에는 런타임 예외를 사용하라
  - 아이템 71. 필요 없는 검사 예외 사용은 피하라
  - 아이템 72. 표준 예외를 사용하라
  - 아이템 73. 추상화 수준에 맞는 예외를 던져라
  - 아이템 74. 메서드가 던지는 모든 예외를 문서화하라
  - 아이템 75. 예외의 상세 메시지에 실패 관련 정보를 담으라
  - 아이템 75. 가능한 한 실패 원자적으로 만들라
  - 아이템 76. 예외를 무시하지 말라

- 예외 From "Thinking in Java"

  - 예외 지침
    1. 적합한 수준에서 문제를 처리한다(어떻게 할지 모르는 경우에는 예외를 잡지 않도록 한다).
    2. 문제를 수정하고 예외를 발생시켰던 메소드를 다시 호출한다.
    3. 수정 부분을 교정하고 메소드를 재실행하지 않고 계속 진행한다.
    4. 메소드에서 산출하기로 되어 있었던 것을 대신할 수 있는 대안 결과를 추정한다.
    5. 현재의 컨텍스트에서 할 수 있는 것을 하고, 상위 컨텍스트에 대해 동일한 예외를 다시 던진다.
    6. 현재의 컨텍스트에서 할 수 있는 것을 하고, 상위 컨텍스트에 대해 다른 예외를 던진다.
    7. 프로그램을 종료한다.
    8. 단순화한다(예외 설계가 좀더 복잡해지면, 사용하기가 힘들고 성가시게 된다).
    9. 라이브러리와 프로그램을 안전하게 한다(이는 디버깅을 위한 단기적인 투자이며, 견고한 애플리케이션을 위한 장기적 투자이다).

- 예외 From "토비의 스프링"

  - 예외 처리 핵심 원칙

    - 예외를 처리할 때 반드시 지켜야 할 핵심 원칙은 한 가지다. 모든 예외는 적절하게 복구되든지 아니면 작업을 중단시키고 운영자 또는 개발자에게 분명하게 통보돼야 한다.

  - 예외처리 방법

    - **예외 복구**

      예외 상황을 파악하고 문제를 해결해서 정상 상태로 돌려놓는 방법

      > 예외로 인해 기본 작업 흐름이 불가능하면 다른 작업 흐름으로 자연스럽게 유도해주는 것이다. 이런 경우 예외상황은 다시 정상으로 돌아오고 예외를 복구했다고 볼 수 있다. 단, `IOException` 에러 메시지가 사용자에게 그냥 던져지는 것은 예외 복구라고 볼 수 없다. 예외가 처리됐으면 비록 기능적으로는 사용자에게 예외상황으로 비쳐도 애플리케이션에서는 정상적으로 설계된 흐름을 따라 진행돼야 한다.

    - **예외처리 회피**

      예외를 자신이 처리하지 않고 호출한 쪽에 던지는 것으로, 두 가지 방법이 있다.

      1. `throws`를 선언하여 예외가 발생하면 알아서 던지게 하는 방법.

         ```
         // 방법 1
         public void add() throws SQLException {
             // JDBC API
         }
         ```

      2. `catch`로 일단 예외를 잡은 다음 다시 예외를 던지는 방법.

         ```
         // 방법 2
         public void add() throws SQLException {
           try {
             // JDBC API
           } catch(SQLException e) {
             // 로그 출력
             throw e;
           }
         }
         ```

      > 예외를 회피하는 것은 예외를 복구하는 것처럼 의도가 분명해야 한다. 콜백/템플릿처럼 긴밀한 관계에 있는 다른 오브젝트에게 예외처리 책임을 분명히 지게 하거나, 자신을 사용하는 쪽에서 예외를 다루는 게 최선의 방법이라는 분명한 확신이 있어야 한다.

    - **예외 전환 (Exception Translation)**

      발생한 예외를 상황에 맞는 적절한 예외로 전환해 던지는 방법

      1. 내부에서 발생한 예외를 그대로 던지는 것이 상황에 대한 적절한 의미를 부여하지 못하는 경우

         -  의미를 분명하게 해줄 수 있는 예외로 바꿔줌

         - API가 발생하는 기술적인 로우레벨을 상황에 적합한 의미를 가진 예외로 변경
         - 전환하는 예외에 원래 발생한 예외를 담아서 중첩 예외(nested exception)로 만든다
         - 중첩 예외는 `getCause()` 메소드를 이용해서 처음 발생한 예외 확인 가능

      2. 예외를 처리하기 쉽고 단순하게 만들기 위해 포장(wrap)하는 것

         - 중첩 예외를 이용해 새로운 예외를 만들고 원인(cause)이 되는 예외를 내부에 담아서 던지는 방식은 같다
         - 의미를 명확하게 하려고 다른 예외로 전환하는 것이 아니다
         - 주로 예외처리를 강제하는 **체크 예외를 언체크 예외인 런타임 예외로 바꾸는 경우에 사용**
         - 어차피 복구가 불가능한 예외라면 가능한 한 빨리 런타임 예외로 포장해 던지게 해서 다른 계층의 메소드를 작성할 때 불필요한 `throws` 선언이 들어가지 않도록 해줘야 한다

- 정리하자면

  Exception 처리에서 가장 중요한 것은 먼저 예외가 발생해도 프로그램이 정상적으로 돌아갈 수 있게끔 하는 것이다. 해당 예외가 복구 가능한 것인지 불가능한 것인지 판단하여 전환이 필요한 경우 전환한다. 예외로 인해 현재 Context에서 처리할 것이 있다면 catch 하여 처리하고, 또한 호출한 상위 Context에서 처리할 것이 있다면 throws로 던져 예외 발생을 알린다. 그리고 Exception 발생 원인을 Log로 잘 정리해서 남긴다.

## Dependency Injection

- Spring의 Bean Container가 Bean의 Life Cycle 관리하며, 객체가 필요한 경우 개발자가 생성하는 것이 아니라 Container로부터 주입받는 것

- 장점

  1. **Dependency Reduction** : 객체 상호 간 의존성 관계를 줄여준다.

  2. **Reusable Structure** : 코드의 재사용과 조합이 용이하다.

  3. **Readability** : 코드들이 분리되다보니 가독성이 뛰어나진다.

  4. **Loose Coupling & Easy to change** : 구조는 변화에 민감하지 않을 수 있다.

- 방법

  1. Field Injection

     가장 흔히 볼 수 있는 Injection 방법으로 사용하기도 간편하고 코드도 읽기 쉽다.

     ```java
     public class Sample {
         
         @Autowired
         private Example example;
         
     }
     ```

     - 단점

       너무 추상적인 기법으로 의존성 주입이 쉽기 때문에 Dependency 관계가 복잡해질 우려가 있다

       - **Single Responsibility Principle Violation** 

          : 너무나 쉬운 의존성의 주입은 하나의 클래스가 지나치게 많은 기능을 하게됨으로써 초기 설계의 목적성이자 "객체는 그에 맞는 동작만을 한다." 는 원칙에 위배되기 쉽다.

       - **Dependency Hiding**

          : 추상화된 의존관계는 의존성을 검증하기 힘들게 만든다. 

       - **DI Container Coupling**

          : Field Injection 을 사용하면 해당 클래스를 곧바로 Instance화 시킬 수 없다. 가령 Container 밖의 환경에서 해당 클래스의 객체를 참조할 때, Dependency 를 정의해두는 Reflection 을 사용하는 방법 외에는 참조할 수 있는 방법이 없다. DI Framework 는 Field Injection 된 클래스의 Instance 화에 대해서 Null Pointer Exception 을 만들어낼 것이다.

       - **Immutability**

          : Field Injection 된 객체는 final 을 선언할 수 없으므로 가변적(Mutable)이다. 객체는 변경될 수 있으며 이에 대한 대응에는 큰 비용이 든다.

  2. Setter Injection

      선택적인 의존성을 주입할 경우 유용하며, Spring 3.x 대까지 가장 권장되던 방식이다.

     ```java
     public class Sample {
         private Example example;
         
         @Autowired
         public void setExample(Example example) {
             this.example = example;
         }
     }
     ```

      Field Injection 으로 인한 패턴적 위험성을 상당 부분 해소한다. Optional Injection 의 경우 권장되는 방식이다.

      @Required 어노테이션을 이용하면 의존성이 필요한 Setter 를 만들 수 있다.

     

  3. Constructor Injection

     Spring 4.x 이상부터 권장되는 방식이다.

     ```java
     public class Sample {
         private final Example example;
     
         @Autowired
         public Sample(Example example) {
             this.example = example;
         }
     }
     ```

     - 장점
       - final 선언이 가능하며 Immutability 에 대한 해소가 가능하며 의존성의 순환 참조(Circular Dependency) 에 대한 예방이 가능하다. 순환 참조 시 위의 방법을 이용한 코드는 BeanCurrentlyCreationExeption 을 발생시킨다.
       - Container Coupling 문제도 해결이 되는데, 생성자를 통한 Injection 이므로 즉각적인 Instance 화 등에 대한 문제도 해결된다.



# 프로젝트 진행

1. Spring MVC 설정

   - web.xml

     ```xml
     <context-param>
         <param-name>contextClass</param-name>
         <param-value>
     org.springframework.web.context.support.AnnotationConfigWebApplicationContext
         </param-value>
     </context-param>
     <context-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>
             kr.or.connect.guestbook.config.ApplicationConfig
         </param-value>
     </context-param>
     <listener>
         <listener-class>
             org.springframework.web.context.ContextLoaderListener
         </listener-class>
     </listener>
     ```

     - configuration을 계층(Presentation, Service, Repository) 별로 분리했을 때, Context 로딩 시점에서 모두 Load해서 하나처럼 쓰기 위한 구문

   - WebMvcContextConfiguration

     - configureDefaultServletHandling()
       - 정적인 자원을 처리하는 Default Servlet을 사용하게 한다
     - addResourceHandlers()
       - Resource 요청에 대한 응답을 위한 리소스 폴더 매핑
     - addViewControllers()
       - Controller의 추가없이 간단히 Url Mapping 가능
       - '/'로 들어오는 요청을 '/main' 으로 보낸다
     - InternalResourceViewResolver()
       - ViewResolver
       - Controller에서 String으로 반환되는 경우 prefix, suffix를 사용해서
         '/WEB-INF/views/' + str + '.jsp' 를 찾아 Client에 전송한다

   - ApplicationConfig

   - DBConfig

     -  implements TransactionManagementConfigurer

       ```java
       @Override
       public PlatformTransactionManager annotationDrivenTransactionManager() {
           return transactionManager();
       }
       @Bean
       public PlatformTransactionManager transactionManager() {
           return new DataSourceTransactionManager(dataSource());
       }
       ```

       - Transaction 관리 Manager 사용

     - dataSource()

       ```java
       @Bean
       public DataSource dataSource() {
           BasicDataSource dataSource = new BasicDataSource();
           dataSource.setDriverClassName(driverClassName);
           dataSource.setUrl(url);
           dataSource.setUsername(username);
           dataSource.setPassword(password);
           return dataSource;
       }
       ```

       - Connection Pool 관리




# 온라인 코드 리뷰

## 1. Injection

```
Injection 방법이 여러개가 존재하는데 이에 대하여 학습을 해보시면 좋을 것 같습니다.
```

## 2. Transactional

```
getProductsUsingCategory에는 Transactional 설정을 하셨는데, 여기에는 하시고 getProducts에는 안하신 이유가 따로 있으실까요??
Transactional에 대해서 좀 더 학습해보시면 좋으실 것 같습니다.
```

## 3. LIMIT

```
이와 같이 LIMIT을 관리 또는 처리하신 이유가 있으실까요??
```



# 오프라인 코드 리뷰

## 1. Optional<Map<String, Object>>

## 2. Log 남길때 통째로 기록하기 보다는 정제시키자

## 3. Injection 3가지 방식(생성자 주입, ...)과 차이

## 4. ResponseMessage?

## 5. Return Null 하지말자

 ## 6. SQL에서 모든 Column을 가져오는 경우에도 쓰이지 않는 Column은 사실상 안 가져온다고 봐도 무방할 정도로 성능에 영향을 주지 않는다

## 7. 외부 라이브러리 사용은 신중해야 한다

	- 왜냐면 JDK 버전업 등으로 인해 지원이 안될 수도 있다

## 8. 메소드 브라우저 호환성을 체크하라

## 9. Transactional ReadOnly 옵션에 따른 결과를 확인







수정사항

1. paging을 위한 list counting을 button의 value로 넘겼다.

   - google paging 참조

2. DaoTest, ServiceTest src/test/java 로 이동

3. EntityController 추가

   - 각 Controller의 Response에 ResponseEntity 적용

4. ExceptionManager 추가

   - ReservationException extends 

   - @ExceptionHandler 사용

   - MethodArgumentTypeMismatchException를 유발하는 NumberFormatException에 대한 예외 처리

5. ProductDao의 SELECT 실행하는 함수에서 @Transactional 제거

   - 단순 SELECT query 자체는 COMMIT이나 ROLLBACK의 대상이 아니므로 트랜잭션 처리의 대상이 되지 않으므로 제거

6. log4j Dependency 추가

7. Dependency Injection 수정

   - Field Injection -> Constructor Injection