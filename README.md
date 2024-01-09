<h2> 
  
 🔐 [Udemy Spring Security 6 초보에서 마스터 되기 최신강의!](https://www.udemy.com/course/spring-security-6-jwt-oauth2-korean/) 🔐</h2>
  공부 코드입니다.

<br>

## Spring Security Framework를 사용하는 이유

- 보안을 프레임워크에 맡겨 개발자들이 웹 애플리케이션을 향상시키는 비즈니스 로직에 집중할 수 있다.
- 모든 보안 시나리오를 참고한 실력있는 개발자들이 제작한 프레임워크이다.
- 오픈소스 프레임워크로, 무료이다.
- CSRPF나 CORS와 같은 보안 취약저들에 대한 지속적인 업데이트가 이루어진다.
  - 새로운 보안 취약점에 대응 가능하다.
- REST API, 마이크로 서비스 등 우리의 프로젝트를 보호 가능 / 권한 부여 규칙 시해 가능
- 메소드 레벨 보안을 두번째 보안으로 보장
- 다양한 보안 기능 제공
- 인증 및 권한 부여를 구현가능 
  - JWT, Open ID 등을 구현 가능하다.

<br>
=> 최소 구성으로 이러한 것들을 모두 보안 가능하다.
<br>
<br>

## [서블릿과 필터]
![img_3.png](img_3.png)
- 요청을 HTTP 프로토콜로 전송
- Java와 요청 사이의 중재자 : '서블릿 컨테이너' or '웹 서버'
  - 브라우저로 부터 받은 HTTP메세지를 ServletRequest Object로 변환
  - 동일한 object를 웹 애플리케이션에서 사용하고 있는 자바 프레임워크에도 제고오딤
  - 다시 요청을 받으면 해당 object를 브라우저가 이해할 수 있는 HTTP메세지로 변환


- 이를 간단히 하기 위해 spring과 spring 프레임워크가 등장했다.
  - REST서비스 MVC paths와 웹 페이지를 생성
  - Spring boot와 spring프레임워크가 서블릿을 생성, 관련 로직을 담당하여 실행하게 됨.
<br><br>
- 필터 : 특별한 종류의 서블릿, 웹 애플리케이션을 향해 들어오는 모든 요청을 가로챌 수 있다.
  - 실질적 비즈니스 로직이 실행되기 전에 일어났으면 하는 프리 로직 / 프리 워크를 명시 가능
  - 웹 애플리케이션에 관한 모든 요청을 가로채는 역할을 한다.
  - 필터에 정의 내린 로직을 실행하고, 화면해서 실행된다.

<b>서블릿과 필터에 대해 잘 이해하는 것이 좋을 것이다.</b>
<br><br>

## [Spring Secyrity internal Flow]
![img_4.png](img_4.png)
유저는 백엔드 서버에 요청을 전송한다. <br>
-> spring security의 필터들이 유저가 접근하고자 하는 경로를 확인하다.<br>
-> 자원의 보호 여부를 판단하고 웹 페이지 접근을 관리한다.
<br><br>
인증이 성공적이었을 경우, 필터는 유저가 로그인 했는지 여부를 판단한다.<br>
<br>
스프링 시큐리티에는 20여개의 필터가 존재하고, 해당 필터는 각기 다른 역할을 갖는다.<br>
인증객체가 형성되면 요청을 인증 관리자에게 넘긴다.
<br>
<b>인증관리자</b> : 실질적 인증을 관리하는 인터페이스 혹은 클래스<br>
  - 인증관리자는 웹 애플리 케이션 안의 인증제공자를 확인한다. => 유효한 인증 제공자를 확인

<br>
<b>인증 제공자</b> 안에서 로직을 작성하거나,
Spring Security에서 제공하는 인터페이스나 클래스를 활용 가능하다.

  - 어떤 데이터베이스나 권리부여 서버 등에서 유저 자격을 증명할 수 있을지 등의 인증에 관련된 비즈니스 로직을 내부에 작성 가능
  - 애플리케이션 안에 다수의 인증 제공자가 존재 가능하다.

<br>
9단계 보안 컨텍스트에서
인증이 성공적이었는지 여부를 확인한다.
<br>
인증 객체와 함께 성공적인 인증 정보가 보안 컨텍스트에 저장되었다면
<br> 성공적인 응답이 엔드유저에게 전송된다.
<br>

## [Sequence Flow]
![img.png](img.png)
전체적인 흐름을 각 필터들과 구조의 역할을 바탕으로 이해하면 좋을 것이다!
![img_1.png](img_1.png)
<br><br>
