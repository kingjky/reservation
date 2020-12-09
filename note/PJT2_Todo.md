# PJT2_Todo

## 1. 개발 과정

### 1) 프로젝트 초기화

1. Maven Project 
   
- maven-archetype-webapp 1.0
  
2. dependency 추가 (pom.xml)

   ```xml
   <dependencies>
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>5.1.45</version>
       </dependency>
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>3.8.1</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>javax.servlet-api</artifactId>
           <version>3.1.0</version>
           <scope>provided</scope>
       </dependency>
       <dependency>
           <groupId>javax.servlet</groupId>
           <artifactId>jstl</artifactId>
           <version>1.2</version>
       </dependency>
   </dependencies>
   ```

   

## 2. 알게 된 점

### 1) try with resources 와 close()

```java
public static void main(String[] args) {
    try(FileInputStream fis = new FileInputStream("")){
         
    }catch(IOException e){

    }
}
```

- 사용

  - Java 1.7 이상 환경에서 사용가능하다.

  - try 옆 괄호 내부에 1)<u>AutoCloseable</u> 구현체의 객체 생성부를 삽입한다.

    - 괄호 내에는 여러 줄이 삽입 가능하다.

  - 중첩된 AutoCloseable 객체: `try ( B b = new B(new A()); )` 

    - 자동으로 close가 호출되는 것은 `b.close()` 뿐이다.

    - 해결 방안

      1. 모든 인스턴스에 대해 레퍼런스 변수를 선언해준다.

         ```java
         try ( A a = new A();
         	  B b = new B(a); )
         ```

      2. close() Method를 override

         ```java
         class B implements AutoCloseable {
            private A a;
         
            public B(A a) {
               this.a = a;
            }
         
            @Override
            public void close() throws Exception {
               this.a.close();
            }
         }
         ```

- 효과

  - try catch 문이 종료됨과 동시에 자동으로 해당 객체에 대한 close()를 호출해준다.
  - 직접 close 호출로 인한 코드를 단축시킬 수 있다.
  
- 기타

  - isClosed() Method를 통해 close 상태를 확인할 수 있다

### 2) Form의 Submit 시 한글 인코딩 깨짐 해결



- Form data 수신하는 측 Servlet에서 request에 인코딩 설정 필요

  `request.setCharacterEncoding("UTF-8");`

- Spring 사용 시 filter에 추가하여 일괄 적용이 가능

### 3) Node.textContent vs Element.innerText

- textContent

  - 모든 Text Content를 반환한다.

- innerText

  - "human-readable" 한 Text만을 반환한다.
  - 읽는 것 만으로 Reflow 비용이 발생한다.

  =>  `visibility: hidden` Element는 Text Content를 innerText로 가져올 수 없고, textContent를 사용할 수 있다.

### 4) Element.insertAdjacentHTML() vs Node.appendChild()

- insertAdjacentHTML(**position**, **text**)
  - text를 parsing해 position 위치에 node로 추가한다.
  - position의 keyword
    -  **beforebegin**
      - element의 앞에 형제로 추가한다.
      - element는 부모가 존재하고 tree 안에 있어야 한다.
    - **afterbegin**
      - element의 첫번째 자식으로 추가한다.
    - **beforeend**
      - element의 마지막 자식으로 추가한다.
    - **afterend**
      - element의 뒤에 형제로 추가한다.
      - element는 부모가 존재하고 tree 안에 있어야 한다.
- appendChild(**node**)
  - 자식 노드 리스트 중 마지막 자식으로 추가한다.
    - insertAdjacentHTML의 beforeend와 같은 위치다.
  - 이미 DOM 내에 존재하는 node인 경우 이동시킨(기존 위치에서 삭제된)다.
    - 복제하려면 `Node.cloneNode()` 사용

### 5) Element.childNodes

- Element의 자식들을 NodeList 형태로 반환한다
  - childNodes[`n`]: n번째 자식 Node를 반환한다.

### 6) querySelector의 활용

- `document.querySelectorAll('input[value][type="checkbox"]:not([value=""])');`

  value 값을 가졌고, type이 checkbox이고, value가 blank가 아닌 모든 input 태그

### 7) DOMContentLoaded

- DOMContentLoaded는 초기 HTML 문서의 Loading과 Parsing이 끝난 후 발생하는 Event다.

- Page Load 즉시 Element나 Node 접근하려고 할 때 DOM tree가 구성되기를 기다리지 않으면, 검색 결과는 undefined 다.

  `document.addEventListener("DOMContentLoaded", function(){ }`

  - 접근하고자 하는 코드는 function 내부에서 구현한다.

### 8) Servlet에서 doGet() 과 service()

-  service()
  - Request의 HTTP Method에 따라 doGet, doPost 등을 호출하는 역할
  - 재정의 할 경우 doGet, doPost 등의 Method에 닿지 않는다.

### 9) enum

- enum의 장점

  1. 문자열과 비교해 IDE의 지원을 받을 수 있다. (자동완성, 오타 검증, 텍스트 리팩토링)

  2. 허용 가능한 값들을 제한

  3. 리팩토링 시 변경 범위가 최소화 된다. (내용 추가가 필요해도 Enum 코드 외에 수정할 필요가 없다.)
  4. 특히 Java에서, enum이 단순 int형이 아닌 완전한 Class이므로 다양한 Method를 지원한다.

#### enum - Java

1. enum 선언

   ```java
   public enum TodoType {
       TODO,
       DOING,
       DONE
   }
   ```

2. enum 사용

   ```java
   public static void main(String[] args) {
   		TodoType type1;
   		type1 = null;
   		type1 = TodoType.TODO;
   		type1 = TodoType.DOING;
   		type1 = TodoType.DONE;
   		
   		if(type1 == TodoType.DONE)
   			System.out.println("DONE"); // print
   }
   ```

3. enum Method 활용

   ```java
   public static void main(String[] args) {
   		TodoType type1 = TodoType.TODO;
   		TodoType type2 = TodoType.DONE;
   
   		System.out.println(type1);					// TODO
   		System.out.println(type1.name());			// TODO
   		System.out.println(type1.ordinal());		// 0
   		System.out.println(type2.ordinal());		// 2
   		System.out.println(type1.compareTo(type2));	// -2
   		
   		TodoType type3 = TodoType.valueOf("DONE");
   		System.out.println(type3.name());			// DONE
   		if(type2 == type3)
   			System.out.println("===");		// print
   		else
   			System.out.println("!==");
   		
   		for (TodoType type : TodoType.values()) {
   			System.out.println(type);		// TODO, DOING, DONE
   		}
   }
   ```

4. enum에 다른 String 값 부여하기

   ```java
   public enum TodoType {
   	// 상수("연결할 문자") 
   	TODO("할일"),
   	DOING("하는중"),
   	DONE("완료");
   
   	final private String name;
   
   	private TodoType(String name) { // enum에서 생성자 같은 역할 
   		this.name = name; }
   
   	public String getName() { // 문자를 받아오는 함수
   		return name;
   	}
   }
   
   public static void main(String[] args) {
   		TodoType type1 = TodoType.valueOf("DONE");
   		System.out.println(type1.getName());		// 완료
   		
   		for (TodoType type : TodoType.values()) {
   			System.out.println(type.getName());		// 할일, 하는중, 완료
   		}
   }
   ```



### 10) Builder Pattern(빌더 패턴)

#### Telescoping Constructor Pattern(점층적 생성자 패턴)

```java
public class Person {
    private final String name;  // 필수
    private final int age;  // 필수
    private final String phoneNumber;
    private final String email;

    public Person(String name, String age) {
        this(name, age, null);
    }

    public Person(String name, String age, String phoneNumber) {
        this(name, age, phoneNumber, null);
    }

    public Person(String name, String age, String phoneNumber, String email) {
        this.name = name;
        this.age = age;
        this.phoneNumber;
        this.email = email;
    }
}

// 객체 생성 시
public void someMethod() {
    Person person = new Person("탱", 29, "010-1234-5678", "itsmetaeng@gmail.com");
}
```

- 필수 매개변수만 받는 생성자와 함께 선택적 매개변수도 받는 생성자 여러개를 필요한 만큼 정의한다
- 멤버변수가 많아지고 생성자 종류가 다양해지면 코드가 길어지며, 매개변수의 타입과 순서를 지켜야 하는 번거로움이 있다. 

#### JavaBeans Pattern(자바빈 패턴)

```java
public class Person {
    private String name;  // 필수
    private int age;  // 필수
    private String phoneNumber;
    private String email;

    public Person() { }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // ... 생략
}

// 객체 생성 시
public void someMethod() {
    Person person = new Person();
    person.setName("탱");
    person.setAge(29);
    // ... 생략
}
```

- 기본 생성자만을 두고, setter를 호출해 멤버변수 값을 세팅한다
- 장점 
  - 점층적 생성자 패턴에 비해, 코드가 간소화되며
  - 순서를 신경쓸 필요가 없다
- 단점
  - 객체 생성에 많은 메서드 호출이 필요해져 Consistency가 무너진다
  - 생성 후 지속 수정이 필요하므로 immutable Class 구현이 불가능해진다

#### Builder Pattern (빌더 패턴)

```java
public class Person {
    private String name;  // 필수
    private int age;  // 필수
    private String phoneNumber;
    private String email;

    public static class Builder {
        private String name;
        private int age;
        private String phoneNumber;
        private String email;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder phoneNumber(String value) {
            phoneNumber = value;
            return this;
        }

        public Builder email(String value) {
            email = value;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        name = builder.name;
        age = builder.age;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
    }
}

// 객체 생성 시
public void someMethod() {
    Person person = new Person.Builder("탱", 29)
        .phoneNumber("010-1234-5678")
        .email("itsmetaeng@gmail.com")
        .build();
}
```

- Class 내부에 Builder라는 innerClass를 선언해 객체 생성시에 활용한다.
-  장점
  - 점층적 생성자 패턴에 비해, 코드가 간소화되며 순서를 신경쓸 필요가 없다
  - 자바빈 패턴에 비해, 객체 생성에 메서드를 단 한번 호출한다
  - 읽기 쉬우며 build Method 내부에서 유효성 검사 등 후속 작업이 가능하다.
  - 유연하다. 다양한 형태의 객체를 생성할 수 있으며 auto-increment, 가변 인자 등을 이용할 수도 있다.  
- 단점
  - 구현하기가 귀찮다. => Lombok Library를 사용할 수 있다.



## 3.  :question: 궁금한 점

### 1) Class.forName("com.mysql.jdbc.Driver");

- 동작하는 방식이 이해가 안 감. forName Method의 return type은 Class<T> 인데 return 값을 받아서 쓰지 않는다. 메모리에 적재한다고 설명되어 있는데 무슨 말인지 모르겠다.

- Project packaging 방식을 jar로 하면 이 구문에서 에러가 발생함. war로 해야 잘 동작함.



## 4. 코드 리뷰

- connection과 PreparedStatement 처리가 공통되게 반복 발생되는데,
   좀 더 나은 방안이 있을지 고민 한번 해보시면 좋을 것 같습니다.

```java
64 TodoDao.java
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}
	
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
```

: `DBConnection implements AutoCloseable` Class 생성	: TodoDao.java - 27



- enum에 대해서 공부해보시고 좀 더 나은 방안에 대해서 생각해보시면 좋을 것 같습니다.

```java
85 TodoDao.java
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			String newType = getNextType(todoDto.getType());
			ps.setString(1, newType);
			ps.setLong(2, todoDto.getId());

			updateCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateCount;
	}

	private String getNextType(String type) {
		switch (type) {
			case "todo":

```

: enum 적용 - TodoType.java, TodoColumn.java

-> TodoAddServlet, TodoFormServlet, TodoTypeServlet



- 매개변수가 많고 적음의 개수는 개인 기준이지만, 한번 해당 관련 내용을 공부해보시고 빌더 적용을 고려해보셔도 좋을 것 같습니다.

```java
7 TodoDto.java
	private int sequence;
	private String title;
	private String type;

	public TodoDto() {
		super();
	}

	public TodoDto(String name, int sequence, String title) {
		super();
		this.name = name;
		this.sequence = sequence;
		this.title = title;
	}

	public TodoDto(long id, String name, String regDate, int sequence, String title, String type) {

```

: Builder 적용 - TodoDto.java

TodoDao.java 51, 