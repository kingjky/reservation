# 1. 프로젝트 개요

이번 파트에서는 예약 서비스의 상세보기 페이지를 만들어보겠습니다.

**상세보기 페이지는 메인화면에서 각 아이템을 선택한 경우 이동하는 페이지입니다.**

**해당 페이지에서는 각 아이템의 상세한 정보를 노출해야합니다.**

 

이번 파트에서는 백엔드의 경우 기존에 배웠던 내용을 토대로 구현을 이어나가시면 됩니다.

프론트엔드에서는 이번 파트에서 새롭게 배운 기술을 포함해서 추가로 구현해야 합니다.

# **2. 프로젝트의 개발스펙**

**웹프론트엔드 기술요구사항**

- 전체 코드는 객체리터럴 패턴으로 구현해야 하고, 더불어 전역변수를 최소화해야 합니다.
- 한 개 이상 객체리터럴을 사용할 수 있습니다.
- 상단 타이틀 이미지 영역의 애니메이션은 CSS3의 transition과 transform을 활용해서 구현해야 합니다.
- 상단에 추가로 노출해야 하는 기타 이미지는 Ajax요청을 통해 가져옵니다.
- DOMContentloaded 이후에 모든 자바스크립트 로직이 동작하게 합니다.
- Event delegation으로 처리할 수 있는 영역은 최대한 delegation방법으로 처리합니다.
- Templating 작업은 handlebar 라이브러리를 사용해서 구현해야 합니다. 
- 함수 하나에 여러 개의 기능을 넣지 않고, 함수를 여러 개로 분리합니다.
- 중복코드를 제거하고 공통부분은 공통함수로 만듭니다.
- 변수와 함수 이름은 본인이 정한 코딩컨벤션을 최대한 지킵니다.
- 접기/펼치기 기능은 jQuery 라이브러리를 사용할 수 있습니다.

 

**웹백엔드 기술요구사항**

- controller, service, dao로 레이어드 아키텍쳐를 구성합니다.
- spring JDBC를 이용하여 주어진 테이블로부터 입력, 수정, 삭제, 조회하는 DAO와 DTO를 작성합니다.
- 서비스 인터페이스를 작성하고 해당 인터페이스에 비지니스 메소드를 추가합니다.
- 서비스 인터페이스를 구현하는 클래스를 작성하고 클래스 내 메소드에 적절한 트랜잭션에 관련된 애노테이션을 사용합니다.
- 클라이언트에게 Web API를 제공하기 위해 RestController 를 작성합니다.



# 3. 알게된 점

## Java

### Interface의 modifier(제어자)

추상화를 위해 존재하는 Interface는 역할에 맞게 사용 가능한 접근 제어자가 한정적이다.

-  Field

  - public (default)
  - static (default)
  - final (default)

  모든 Field는 암묵적으로 public static final의 접근 제어자를 지니며 생략 가능하다..
  다른 제어자를 사용할 경우 compile error가 발생한다.

- Method

  - public (default)

    모든 Method는 암묵적으로 public의 접근 제한자를 지닌다.

  - abstract / default / static

    - abstract : 추상 메소드를 정의할 경우 사용하며 생략 가능하다.
    - default : Interface 내에서 Method body를 정의할 수 있고, 구현 Class에서 재정의가 가능하다.
    - static: Interface 내에서 Method body를 정의할 수 있고, static method 이므로 재정의가 불가능하다.


## JavaScript

### Template literals

```js
var value = "apple";
var str = `My favorite fruit is ${value}`
```

### JavaScript modules

- 하위 모듈(square.js)

  ```js
  ...
  function draw(ctx, length, x, y, color) {
    ctx.fillStyle = color;
    ctx.fillRect(x, y, length, length);
  
    return {
      length: length,
      x: x,
      y: y,
      color: color
    };
  }
  ...
  
  export { name, draw, reportArea, reportPerimeter };
  ```

- 상위 모듈(main.js)

  ```js
  import { name, draw, reportArea, reportPerimeter } from './modules/square.js';
  
  let myCanvas = create('myCanvas', document.body, 480, 320);
  let reportList = createReportList(myCanvas.id);
  
  let square1 = draw(myCanvas.ctx, 50, 50, 100, 'blue');
  reportArea(square1.length, reportList);
  reportPerimeter(square1.length, reportList);
  ```

- 적용 문서(.html)

  ```php+HTML
  <script type="module" src="main.js"></script>
  ```

  



# 4. 코드 리뷰

## 2020-11-16 (월)

- Autowired 일관되게
- ResponseEntity.notFound() 같은거
  - 내부 소스 들여다 보는 습관이 좋다
- Optional을 쓰는 곳과 안 쓰는 곳 구별



## 2020-11-19 (화)

- 김기현
  - Optional 명확하게 사용
    - Optional을 단순 예외 체크를 위해 사용하는 것 보다는 Null을 return 하지 않도록 Exception을 처리하는 경우 필요
  - Builder를 사용할 경우 지나치게 코드가 늘어지지 않도록 사용
  - 
- 이경선
- 전경윤
  - Packaging 구조 배치 = Naming