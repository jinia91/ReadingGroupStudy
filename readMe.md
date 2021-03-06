---


---

<h2 id="스터디-일지8.169.26-종료">스터디 일지(8.16~9.26 종료)</h2>
<p>1주차 : 발표자 Albireo3754 (2021.08.16~2021.08.22)</p>
<ul>
<li>발표자료: <a href="https://albi-tech.tistory.com/5"></a><a href="https://albi-tech.tistory.com/5">https://albi-tech.tistory.com/5</a></li>
<li>과제 코드: <a href="https://albi-tech.tistory.com/6"></a><a href="https://albi-tech.tistory.com/6">https://albi-tech.tistory.com/6</a></li>
</ul>
<p>2주차 : 3챕터 발표자 jinia91(나)<br>
(2021.08.22~2021.08.29)</p>
<ul>
<li>발표자료 / 과제코드 : 본 지면으로 사용</li>
</ul>
<p>2주차 : 4챕터 발표자 자스어린이<br>
(2021.08.22~2021.08.29)</p>
<ul>
<li><a href="https://material-hurricane-4fa.notion.site/7a82c47919d142feb2af24ba2e7c78e3">발표자료 링크</a></li>
</ul>
<p>3주차 : 5챕터 발표자 캐프붕<br>
(2021.08.29~2021.09.05)</p>
<ul>
<li>발표자료 미기입</li>
</ul>
<p>3주차 : 6챕터 발표자 신입전향<br>
(2021.08.29~2021.09.05)</p>
<ul>
<li>발표자료 미기입</li>
</ul>
<p>4주차 : 7챕터 발표자 : InseobJeon<br>
(백신접종과 추석연휴로 연기~2021.09.26)</p>
<ul>
<li>발표자료 : <a href="https://github.com/InseobJeon/facts-and-myths-about-oo/tree/master/chapter-7">https://github.com/InseobJeon/facts-and-myths-about-oo/tree/master/chapter-7</a></li>
</ul>
<h2 id="section"></h2>
<p><img src="https://user-images.githubusercontent.com/85499582/130334275-c14f1ae5-3953-4f29-8274-447b8e7df7bf.gif" alt="calculatorver_0 1"></p>
<h2 id="스터디-과제-계산기-만들기">0. 스터디 과제 계산기 만들기</h2>
<h3 id="러프하게-기능만-구현하기">1: 러프하게 기능만 구현하기</h3>
<h4 id="현재-코드의-문제점">현재 코드의 문제점</h4>
<ol>
<li>
<p>기능구현을 최우선으로 한만큼 절차적으로 코드를 작성하였으며 하나의 클래스에 모든 기능이 들어가 있음</p>
</li>
<li>
<p>메인 로직이라고 할 수 있는 사칙연산과 초기화, 등호연산(=)에 대한 분리가 안 이루어져있고 하나의 메소드에서 모두 수행하여 협업시 코드 리딩과 변경이 매우 힘들것으로 예상됨.</p>
</li>
<li>
<p>메소드 분리를 하지않아 코드의 중복이 다수 발생</p>
</li>
<li>
<p>위와같은 이유로 기능 확장시에도 많은 코드 수정이 따를것으로 예상됨.</p>
</li>
</ol>
<h4 id="추후-개선사항">추후 개선사항</h4>
<ul>
<li>
<p>단일책임원칙을 생각하여 역할과 책임을 객체에게 적절히 분배하고 시스템을 분할하여 재설계하기</p>
</li>
<li>
<p>사칙연산과 같은 로직들은 if-else if방식이 아닌 전략패턴을 적용하여 캡슐화하고 추상화를 통해 기능확장의 가능성을 열어두도록 설계해보기</p>
</li>
</ul>
<h3 id="책임-주도-설계와-mvc패턴">2: 책임-주도 설계와 MVC패턴</h3>
<p>앞의 계산기의 코드를 잠깐 살펴보면</p>
<p>Application)</p>
<pre><code>public class Application {  
   public static void main(String[] args) {  
      Calculator calculator = new Calculator();  
   }  
}
</code></pre>
<p>Calculator)</p>
<pre><code>class Calculator extends JFrame implements ActionListener {  
  ...................................중략
  xtField(30);  
  display.setText("");  

  Font bigFont = display.getFont().deriveFont(Font.PLAIN, 25f);  
  display.setFont(bigFont);      
  }  
  add(display, BorderLayout.NORTH);  
  add(panel, BorderLayout.CENTER);  
  setVisible(true);  
  pack();  
  
   }  
 .....................................중략
   public void actionPerformed(ActionEvent button) {  


  if (button.getActionCommand().equals("C")) {  

     display.setText("");  
     result = 0;  
     firstFlag = true;  
     return;  
  }  
  .......................................중략
</code></pre>
<p>사용자의 구동 메시지를 수신하고 행동하는 Application과<br>
구동과 계산 모두를 담당하는 Calculator</p>
<p>단 두개의 객체만으로 이루어져 절차적 설계로 만들어진 앱이지, 전혀 객체지향적이지 않은 모습을 보여준다.</p>
<p>만약 위의 코드를 누군가가 인수인계하여 수정하고 기능을 확장하거나, 유지보수해야한다고 상상해보자.</p>
<p>인수자는 코드의전체 문맥을 파악하기위해 몇번을 읽고 이해하기위해 노력해야할것이다.</p>
<p>그럼 이제부터 책임주도 설계와 MVC 패턴의 적용을 통해 객체지향의 시각으로 위의 코드를 리팩토링해보겠다.</p>
<h4 id="시스템의-책임-이해하고-쪼개기---책임주도-설계">시스템의 책임 이해하고 쪼개기 - 책임주도 설계</h4>
<p><strong>책임-주도 설계</strong>란 사용자의 메시지에 대한 시스템의 책임을 명확히 파악하고, 이 책임을 잘게 분할해가며 적절한 객체 혹은 역할에게 할당해나가는 <strong>분할정복과 비슷한</strong> 설계 방식이다.</p>
<p>계산기 애플리케이션을 책임 주소 설계로 바라보면 시스템의 메인 행동(책임)은 사용자의 계산 요청에 대한 적절한 응답이다. 이를 다시 분할해보면</p>
<ol>
<li>사용자의 프로그램 구동 요청에 대한 책임</li>
<li>사용자의 Input 을 받을 책임</li>
<li>Input값을 적절한 계산객체(서비스로직)에게 전달할 책임</li>
<li>계산할(서비스로직을 수행할) 책임</li>
<li>계산된 Output을 사용자에게 전달할 책임</li>
</ol>
<p>정도로 크게 5가지로 나눠볼 수 있다.</p>
<p>여기서 보다 원할한 설계를 위해 어느정도 친숙한 패턴인 MVC 패턴을 적용해보자.</p>
<p>MVC패턴은 주로 웹 애플리케이션에 사용되는 패턴이므로 실제 실무에서 일반 앱에 적용하기에는 적절하지 않으나 구현의 편의와 공부목적으로 시행하였다.</p>
<p>MVC 패턴은 애플리케이션을 모델, 뷰, 컨트롤러 세가지 관점으로 나누어 설계하는 방식으로 간략하게 요약하면</p>
<p><strong>VIEW 	: 사용자가 보게될 결과물을 생성하는 역할<br>
MODEL : 데이터를 관할하는 역할<br>
CONTROLLER: 요청이 들어올때, 해당 요청을 수행할 비지니스 로직을 제어하고, 흐름을 제어하는 역할</strong></p>
<p>로 구분된다.</p>
<p>여기서 비지니스 로직은 사람에 따라 model영역에 혹은 controller 영역에 두기도 하고 따로 SERVICE 영역으로 구분짓기도 한다.</p>
<p>위의 책임주도 설계를 통해 분할한 5개의 책임을 MVC 패턴에 맞춰</p>
<p>책임을 분할해 개발해야될 객체들을 다음 그림과 같이 판단해 보았다.</p>
<p><img src="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/Untitled%20Diagram.jpg?raw=true" alt="enter image description here"></p>
<ol>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/main/Application.java">유저의 구동 메시지호출을 받는 객체/Application</a></li>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/main/Calculator.java">화면을 렌더링하고, I/O 책임을 담당하는 객체/Calculator</a></li>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/Controller/FrontController.java">클라이언트의 요청을 받아 서비스로직의 흐름을 제어하는 객체/FrontController</a></li>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/tree/master/src/service">계산 로직들</a></li>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/tree/master/src/dao">계산 로직에 필요한 메모리형 객체 DAO/MemoryResultStore</a></li>
<li><a href="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/model/CalDto.java">데이터의 운반과 변경, 호출을 담당하는 객체/DTO</a></li>
</ol>
<h3 id="차후-개선사항">차후 개선사항</h3>
<p><img src="https://github.com/jinia91/ReadingGroupStudy/blob/master/src/Untitled%20Diagram2.jpg?raw=true" alt="enter image description here"></p>
<ul>
<li>전략패턴에 대한 이해도가 부족해서 공부 후 구현해보기</li>
<li>어댑터패턴 적용여부 판단</li>
<li>프론트 컨트롤러의 책임이 과한것 아닌가? 매퍼가 필요한지 판단</li>
<li>의존성 주입에 대해 공부하고 구현해보기</li>
</ul>

> 본 게시물은 2011.8.16~9.26까지 진행했던 '**객체지향의 사실과 오해 독서 스터디**' 에서 공부하고 발표했던 내용을 재정리한 글이다.<br>

## 0. 스터디 일지(8.16~9.26 종료)
1주차 : 발표자 Albireo3754 (2021.08.16~2021.08.22)

- 발표자료: [](https://albi-tech.tistory.com/5)[https://albi-tech.tistory.com/5](https://albi-tech.tistory.com/5)

2주차 : 3챕터 발표자 jinia91(나)
(2021.08.22~2021.08.29)
- 발표자료 / 과제코드 : 본 지면으로 사용

2주차 : 4챕터 발표자 자스어린이
(2021.08.22~2021.08.29)
- [발표자료 링크](https://material-hurricane-4fa.notion.site/7a82c47919d142feb2af24ba2e7c78e3)

3주차 : 5챕터 발표자 캐프붕
(2021.08.29~2021.09.05)

- 발표자료 미기입

3주차 : 6챕터 발표자 신입전향
(2021.08.29~2021.09.05)

- 발표자료 미기입

4주차 : 7챕터 발표자 : InseobJeon
(백신접종과 추석연휴로 연기~2021.09.26)

- 발표자료 : [https://github.com/InseobJeon/facts-and-myths-about-oo/tree/master/chapter-7](https://github.com/InseobJeon/facts-and-myths-about-oo/tree/master/chapter-7)



## 1. 협력하는 객체들의 공동체

### 객체지향이란

>객체 지향이란, 애플리케이션을 **1)상호작용하는 2)자율적인 객체들의 집합**으로 보고, 객체를 이용해 **3)시스템을 분할**하는 방법론을 의미한다.

1) 객체들의 상호작용은 **요청과 응답**으로 구성되며, 요청은 메시지를 통해서 전달되고 응답은 구체화된 메소드를 통해서 행해진다.

-  요청(Request)
   객체가 다른 객체에게 특정 행동을 요구하는 행위
   클라이언트인 객체가 서버인 객체에게로 메시지(ex:`**service.save(data)`, 오퍼레이션 명칭과 인자)를 전송(메서드 호출)함으로써 요청은 일어난다.
   이때 메서드 !=메시지 임을 알아두자
   메서드는 오퍼레이션 명과 그 구체적 행동(구현)을 모두 포함하는 말이므로 메시지와는 구분되어야 한다.
   <br>
- 응답(Response)
  메세지에 대응하는 서버 객체의 행동으로 메서드, 좀더 구체적으로는 메서드의 코드블록에 해당한다. 즉 오퍼레이션의 구현이다.
  인터페이스와 구현의 분리를 생각해보면 메세지에 대해 객체별로 **메서드가 다를수 있음**(메서드명은 동일해도 구현은 다를수 있다)을 유념하자





2\) 자율적인 객체란 **상태**와 **행위**를 가지며, **스스로 자기 자신을 책임**지는 객체를 의미한다.
<br>
- 상태(state)
  객체가 지니고 있는 속성, 특성으로 **필드, 멤버변수**에 해당한다.
  <br>
- 행위(behavior)
  객체가 행동할수 있는 능력으로 메서드의 존재로 파악할 수 있다.
  <br>
- 스스로 책임
  객체는 특정 행위를 요청 받으면(인자를 포함한 메시지 요청), 이 시점부터 메서드의 코드블록을 통해 스스로 행위를 판단하고 진행할 수 있다.
  또한 해당 행위를 위해 사용되어질 자신의 상태또한 보유하고 있으므로 상태와 행위를 하나로 묶는 자율적인 존재라 정의할 수 있다.
  <br>




3\)  커다란 시스템의 행위를 보다 쉽게 구현하기위해 수많은 **책임**으로 **분할**하고 그중 응집도가 높은 **책임**들을 묶어 **역할**로 정의한다. 그리고 이 역할들을 수행하기 위한 객체들을 만들고 서로 **협력**하게 함으로써 어플리케이션은 작동하게 된다.



## 2. 이상한 나라의 객체

> 객체란 **상태**와 **행동**을 가지며 **식별가능한** 개체(Entity) 혹은 사물(Object)이다.

### 상태

**1. 왜 상태가 필요할까?**

객체가 만약 행위만을 가진 존재라면 현재 객체의 행동 결과를 과거의 행위 이력을 통해 추론해야 한다. 이런 방식은 복잡하고 번거로우며 이해하기도 어렵다.

상태라는 개념을 이용하면 과거에 얽메이지 않고도 현재를 기반으로 객체의 행동방식을 파악할수 있으며 보다 직관적이다.

**2. 상태와 프로퍼티**
객체는 자신의 상태로 자료타입의 값과 참조타입의 객체, 두가지 종류를 가질 수 있다. 이 모든것을 통칭해 **프로퍼티** 라고 부르며, 자료타입의 값을 **프로퍼티 값**, 프로퍼티로의 객체를 **동적 프로퍼티** 라고 부른다.

> 상태는 특정 시점에 객체가 가지고 있는 정보의 집합으로 객체의 구조적 특징을 표현한다. 객체의 상태는 객체에 존재하는 정적 프로퍼티와 동적프로퍼티값으로 구성된다.


### 행동
>행동이란 외부의 요청 또는 수신된 메시지에 응답하기 위해 동작하고 반응하는 활동이다. 행동의 결과로 객체는 자신의 상태를 변경하거나 다른 객체에게 메시지를 전달할 수 있다. <br>객체는 행동을 통해 다른 객체와의 협력에 참여하므로 행동은 외부에 가시적이어야 한다.

- 객체의 상태를 변경시키는 유일한 방법이다. 만약 **외부**에서 객체의 상태를 **직접 변경**시킬 수 있다면 **캡슐화**가 깨졌기 때문이고 제대로된 객체지향 설계라 할 수 없다.

- 객체는 홀로 존재하지 않으며 항상 협력(요구된 서비스)을 전제로 한다.

### 객체의 식별

프로그래밍에서 객체를 구분하는데는 **동등성(equality)** 과 **동일성(identical)** 두가지 기준을 사용한다.

**동등성**이란 값(value)가 같은지 다른지의 여부를 통해 객체의 일치 여부를 판단하는 기준이다.

> 자바에서 String 클래스의 equals()메소드가 동등성의 원리로 재정의한 대표적인 예시. <br>
> 클래스를 만들때 객체의 동등성을 표현하고 싶다면 equals()메소드를 오버라이딩하여 값을 비교하면 된다.

**동일성**이란 객체가 완전히 일치하는지의 여부로, 객체는 식별자를 통해 동일성을 판단할 수 있다.

> 자바에서 == 은 객체의 참조주소값을 비교하게 되는데 이를 통해 참조변수가 지목하는 객체가 동일한지 여부를 확인가능하다.


### 객체지향의 오해

흔히 객체지향, 객체지향 설계를 현실세계에 존재하는 개체를 프로그래밍으로 비슷하게 구현하는 것, 설계하는것이라고 말하지만 이는 제대로된 설명이라고 할 수 없다.

객체지향은 현실의 **모방**이 아니라 **은유**이다.

객체지향 세계에서 은유된 개체 혹은 사물들은 의인화되어 다른 개체 혹은 사물에게 메세지를 전송하며 요청하고

메세지를 수신하고 이에 응답해 행동하며 그 행동은 자율적으로 개체 혹은 사물 자신의 상태를 변화시키고 다시 다른 개체 혹은 사물에게 메세지를 전송한다.

응답과 요청, 메시지의 수신과 송신, 행동과 상태 변화의 무수한 반복을 통해 하나의 큰 협력을 이루고 협력은 시스템이 되어 우리가 만드는 앱이 된다.

즉, **객체 지향의 설계는  의인화된 시스템을 창조하는 과정**인 것이다.


> 본 게시물은 2011.8.16~9.26까지 진행했던 '**객체지향의 사실과 오해 독서 스터디**' 에서 공부하고 발표했던 내용을 재정리한 글이다.<br>

## 3. 타입과 추상화

###  현실을 추상화해라 -> 객체

>추상화란?<br>
> 어떤 양상, 세부사항, 구조를 좀더 명확하게 이해하기 위해 특정 절차나 물체를 의도적으로 생략하거나 감춤으로써 복잡도를 극복하는 방법

앞서 객체지향 패러다임, 객체 지향 설계는 **의인화된 시스템을 창조하는 과정**, **현실의 은유**라고 파악했다.

이 시각으로 구동중인 애플리케이션을 바라보면,  수많은 **객체들**이 **동적**으로 바쁘게 움직이며 메세지를 주고받고  서로 **협력**하는 세상으로 볼 수 있다.

단순히 코드의 진행으로 애플리케이션을 바라보는것보다 훨씬 이해하기 쉽고 작동하는 모습도 머릿속에 상상하기 더 편할 것이다.

하지만 개발자의 시선으로 생각하면 어떨까?

시간의 흐름에 따라 객체들은 시시각각 생겨나고 사라지며 특정 행동을 하고 상태가 변화한다. 객체가 몇개 안된다면 괜찮을지도 모르겠지만, 만약 객체가 1000여개가 각기 다르게 행동한다면? 회원정보를 다루는 객체 만개가 동시에 생성되는 상황이라면?

이 모든상황을, 이 모든 객체를 하나하나 코드로 작성하며 통제하는 것이 가능할까?

### 객체를 추상화 해라 -> 타입

사실 위의 예시에서도 이미 객체는 한단계 더 추상화되어 있다. `회원정보를 다루는 객체 만개가 동시에 생성되는 상황` 이라는 문장을 곱씹어보자.

만개의 객체는 분명 서로가 개별적으로 다른 객체지만 `회원정보를 다루는` 이라는 개념 아래에 묶어서 분류하고 있다.

이처럼 추상화란 결코 어려운 개념이 아니라 의도를 가지고 목적을 정한 뒤, 목적에 불필요한 부분을 제거하여 단순화하는것으로 정의할 수 있다.

특정 목적이라는 필터링으로 객체를 바라보게 되면 객체들은 훨씬더 단순해진다. 이 객체는 회원정보를 다루는 객체, 저객체는 어떤서비스를 담당하는 객체, 등등

그리고 유사한 역할의 객체들을 **특정 목적, 특정 개념**으로 묶어서 분류할 수 있게된다.

방금까지 만개가 넘는 객체들이 움직이던 세상이였지만 **회원정보를 다루는 개념**의 객체 만개와 특**정 역할을 하는 개념**의 객체들,** 또 다른 역할을 하는 개념**의 객체들...

이렇게 만개가 넘던 객체들이 **수십개의 개념**정도로 분류되어 볼 수 있게되는것이다.

위의 사례를 조금 더 컴퓨터 공학적 용어로 다듬게 되면 개념이란 단어를  **타입** 으로 치환할 수 있다.

### 객체와 타입, 정적 모델화

객체지향에서 타입을 사용하는 또다른 이유는 **시간**이란 개념의 복잡성 때문이다. 객체는 상태를 가지고 있으며 이 상태는 동적 행동에 따라 시시각각 변화한다.

이 변화하는 객체를 같은 객체로서 인지하기 위해선 어떻게 해야할까?

객체가 어떤 목적으로 존재하는지 그 **개념**을 정의하여 **타입**으로 이름 붙이게 되면 객체에 시간이라는 개념을 동반하지 않고도 **정적인 관점**에서 묘사가 가능해진다.

### 객체를 타입화하는 올바른 방법

그렇다면 여기서 우리는 객체들을 **어떻게** 타입으로 분류해야할까?

객체에서 가장 파악하기 쉬운것은 아무래도 **상태값**일것이다.

`이 객체는 이런 이런 상태를 가지고 있는 객체니 이런 상태값을 다루는 타입으로 정의하자`

라고 생각하기 쉽다.

그러나 앞서 말했던것처럼 객체의 상태는 **행동에 의해 변화**될 수 있고 객체의 행동에 의존한다.

>객체 지향의 관점에서 보면 회원정보 객체의 회원 아이디 상태는 POJO형식으로 작성되었다고 생각할 때, 캡슐화되어있으며 메시지를 받으면 객체 스스로가 메시지속 인자를 set으로 행동해 바꾸며, 이를 조회할때도 get으로 행동해 반환한다. <br>따라서 상태는 어디까지나 행동에 의해 설정되고 조회되고 변화되는 부산물이며   행동으로 사용되어지지 않는 상태는 의미 없는 값이다.

따라서 **상태를 통해** 타입을 분류하는 것은 적절하지 못하며 객체들의 **공통된 행동**을 중심으로 타입을 분류하는 것이 적절하다.

객체를 공통된 행동으로 한데 묶고, 시간에 따른 **상태 변화**를 덜어냄으로서 추상화를 하여 분류하면 여러 객체들은 특정 행동을 하는 하나의 추상적 타입(컨셉)으로 분류화될것이고

이를 통해 어플리케이션의 세계를 보다 간략하게 파악하고 손쉽게 다룰 수 있게 되는 것이다.


> 자바에서는 개별 객체들을 추상화를 통한 개념으로 묶어 **Class**로 표현하고, 여기서 또다시 추상화를 하여 계층으로 나누고 상위 계층의 타입은 **부모 클래스** 혹은 **인터페이스**로 표현할 수 있다. <br>




## 4. 역할, 책임, 협력

### 협력

객체 지향의 시각에서 가장 중요한 것은 **객체의 행동**이다.
하지만 객체는 스스로 행동을 **시작**하지 않으며 **다른 객체의 요청**과 **그에 대한 응답으로** 행동이 이루어 진다.

즉 객체는 혼자서 고립된 섬이 아니라 여러 객체들이 서로 **상호작용**하는 **협력체, 협력 시스템**인 것이다.

>협력 : 요청과 응답을 통한 객체들간의 상호작용

### 책임

어떤 객체가 요청(메시지)을 하면 그 요청을 들은(메시지를 받은, 메시지 호출을 받은) 객체는 **응답(행동)해야할 책임**이 있다.

여기서 객체는 **여러가지** (행동해야할)책임집합을 갖기도 하는데 이 책임은 반드시 응집도 있는 행위들의 집합이여야한다.

여러 책임들이 응집도있다면 이는 **하나의 큰 책임 집합**으로 여겨질 수 있다.

만약 하나의 객체가 지나치게 많은 책임이나, 응집도가 떨어지는 책임집합을 갖고있다면

이는 너무 많은 책임을 갖고 있다는 것이며(SRP 위배), 객체지향설계가 적절하게 이루어 지지 않았다는 의미다.

> 책임 : 메시지를 받은 객체가 응답해야 할 의무

### 역할

협력과정에서 객체는 응집도 있는 **책임 집합 혹은 하나의 큰 책임**을 갖는다고 확인했다. 이것은 객체가 협력속에서 특정 **역할**(Role)을 하고 있음을 암시한다.

만약 같은 메시지를 수신하고 행동가능한, 즉 **같은 책임**을 질 수 있는 객체가 또 존재한다면, 협력관계에서 해당 객체는 **대체**가 가능하다는 의미이다.

> 역할 : 협력과정에서 특정 책임 집합을 수행하는 자리, 역할에 해당하는 객체는 책임을 수행할 능력이 요구되며, 능력이 있는 객체는 해당 역할을 언제든 대체할 수 있다.

역할의 가장 큰 가치는 하나의 협력안에 여러종류의 객체가 참여할수 있도록, **협력을 추상화** 할 수 있다는것이다.

추상화를 통해 **보다 일반화 되어진 역할**은 더 많은 객체들이 협력에 참여할 수 있게 만들며, 이는 객체 지향 설계의 단순성, 유연성, 재사용성을 뒷받침하는 핵심 개념이다.


### 역할,책임,협력을 고려하는 객체지향 설계 기법

**시스템의 책임을 이해하고 쪼개기 - 책임주도 설계**
**책임-주도 설계**란 사용자의 메시지에 대한 시스템의 책임을 명확히 파악하고, 이 책임을 잘게 분할해가며 적절한 객체 혹은 역할에게 할당해나가는 **분할정복과 비슷한** 설계 방식이다.

**테스트 주도 개발**

- 테스트를 먼저 작성한 후 해당 테스트를 통과하도록 코드를 작성하며 개발하는 방식을 말한다.
- 이름과 다르게 테스트가 중요한게 아니다. 역할, 책임, 협력이 적합한지를 피드백으로 얻는게 중요하고 테스트는 덤일 뿐이다.
- 테스트-주도 개발은 다양한 설계 경험과 객체지향에 대한 깊이 있는 지식이 없다면 효과적이지 못하다. 그래서 역할, 책임, 협력에 집중하고 객체지향의 원칙을 적용하려는 깊이 있는 고민과 노력을 통해서만 테스트-주도 개발의 혜택을 누릴 수 있다.

**디자인 패턴**

- 디자인 패턴은 책임-주도 설계의 결과를 표현한다.
- 반복적으로 발생하는 문제에 대한 솔루션이며 객체의 역할,책임,협력을 고안한 객체 지향적 설계 패턴이므로 사용자는 해당 패턴이 해결하련느 문제가 무엇인지 명확히 파악하고, 패턴을 사용하면 왜 더 효과적인지 알아야 한다.
- 디자인 패턴을 통해 책임-주도 설계의 절차를 순차적으로 거치지 않고도 빠르게 좋은 객체지향 설계 산출이 가능해진다!


<h2 id="책임과-메세지">5. 책임과 메세지</h2>
<p>지난 4장까지 저자의 말을 간략하게 요약하면, 객체지향 관점에서 가장 중요한 것은 객체의 <strong>행동</strong>이며, 행동을 유발하는 것은 다른 객체가 보낸 <strong>메시지</strong>와 메시지에 대해 응답할 <strong>책임</strong>이다. 따라서 적절한 <strong>책임</strong>을 객체에게 할당하는 것이 설계에서 가장 중요한 것이라고 할 수 있다.</p>
<p>그렇다면 적절한 책임은 대체 무엇일까?</p>
<h3 id="자율적인-책임">자율적인 책임</h3>
<p>좋은 객체지향 설계를 위한 SOLID 원칙과 결합도를 생각해보면, 객체의 책임은 너무 커선 안되고, 객체간의 결합은 최대한 느슨해야한다고 한다. 즉 객체들은 서로간에 구속을 최소화하여 스스로가 최대한 자유롭게 판단할수 있을때, 유연한 설계가 이루어지는 것이다.</p>
<p>그렇다면 자유로운 객체가 되기 위해서는 할당된 책임이 자율적이여야 될 것이다.</p>
<p>이를 보다 구체화해보면, 자율적인 책임은 추상적인 메시지를 통해 주어지게 될 것이다.</p>
<p>만약 선생이란 객체가 학생이란 객체에게 학생이 오늘 공부한것을 알고 싶어서,</p>
<blockquote>
<p>“오늘 1교시에 필기한 노트랑 2교시에 필기한노트, 3교시에 필기한노트, 4교시에 필기한노트를 가져와”</p>
</blockquote>
<p>라고 메시지를 보낸다면, 학생객체는 그 4가지 노트를 반환하는수밖에없다.</p>
<p>즉 매개변수로 1교시공부량,2교시공부량,3교시공부량,4교시 공부량을 모두 입력받아 그만큼 구체적인 반환값을 요구한 메시지(메소드 호출)이며,</p>
<p>학생 객체는 이에대해 딱 정해진 반환값만 반환할 수 있을것이다.</p>
<p>애플리케이션에서 위와같은 설계를할 경우, 만약 기획자가 바뀌어 선생객체나 학생객체를 바꿀일이 생긴다면?</p>
<p>바뀐 학생객체는 공부를 5교시부터 열심히한다던가하는 등 다른 특성이있다면?</p>
<p>그럼 <strong>메시지를 다시 수정</strong>(메소드를 다시 수정)해야할일이 생기며 이는 유연성이 떨어지는 설계가 된다.</p>
<p>하지만 만약 처음부터 메시지가</p>
<blockquote>
<p>“오늘공부한거 가져와”</p>
</blockquote>
<p>라고 적당히 추상화되었다면, 선생과 학생이 아무리 바뀌어도 학생은 메시지의 책임에 스스로 행동을 결정할수 있게된다.</p>
<p>이처럼 <strong>적절히 추상화된 메시지</strong>가 <strong>적절한 책임</strong>을 부여하기 위한 핵심이다.</p>
<h3 id="다형성">다형성</h3>
<p>객체가 스스로 행동을 결정하는 것은 객체지향에서 다형성으로 구현된다.</p>
<p>위의 예시처럼 책임을 수행할 역할이 있는 객체들은 구체적인 메소드를 통해 행동할 수 있으며, 학생이라는 역할은 추상화된 메시지 덕분에 다른 학생, 심지어 학생이 아닌 다른 타입의 객체들로도 얼마든지 대체할 수 있게됬다.</p>
<p>메시지 송신자와 수신자간의 낮은 결합도가 생긴것이며, 이는 유연하고 확장 가능하고 재사용성이 높은 설계가 됨을 의미한다.</p>
<p>그리고 이 모든 결과는 <strong>적절하게 추상화된 메시지</strong>에서부터 시작한다.</p>
<h3 id="메시지와-인터페이스">메시지와 인터페이스</h3>
<p>추상화된 메시지를 수신할 수 있는 여러 개체들은 타입으로 추상화될 수 있고, 이를 보다 추상화시키면 상위클래스 또는 인터페이스라는 방식으로 구현될 수 있다.</p>
<p>즉 인터페이스를 정의하는 것은 메시지이며, 인터페이스는 수신할 수 있는 메시지 목록이라고 볼 수 있다.</p>
<blockquote>
<p>특정 개체가 인터페이스를 구현했다면 이 개체는 해당 메시지들을 수신할수 있는 역할이라는 의미</p>
</blockquote>
<p>여기서 중요한 사실은 인터페이스라는 개념을 작은 단위에도 적용가능하단 점이다.</p>
<p>즉 현재 설계시점에서 해당 역할을 수행하는 객체가 하나만 존재할 지라도, <strong>역할</strong>이 존재하는 이상, 하나의 객체도 역할에 해당하는 인터페이스와 그 인터페이스의 구현으로 쪼개서 생각할 수 있다.</p>
<p><img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&amp;fname=https://t1.daumcdn.net/cfile/tistory/99396B4B5C6CA36E01" alt="enter image description here"></p>
<blockquote>
<p>Service interface 와 service impl 관계처럼 모든 역할은 메시지를 수신할 껍데기인 인터페이스와 메시지의 책임을 수행할 구현체로 분리가능하다! 이때 컨트롤러는 서비스 인터페이스를 바라보며, SOLID 원칙에서 의존의 역전 원칙(DIP)을 준수하게된다. 이를 구현체를 캡슐화한다고 표현하기도한다.</p>
</blockquote>
<h2 id="객체-지도">6. 객체 지도</h2>
<p>소프트웨어 분야에서 예외가 없는 유일한 규칙은 요구사항이 항상 변경된다는 것이다. 변경에 발맞춰 빠르게 요구사항을 충족시키기 위해서는 좋은 설계가 뒷받침 되야 하고, 좋은 설계는 좋은 구조를 통해 이루어 진다.</p>
<p>객체지향에서 설계를 할때 가장 우선시되는것은 안정적인 객체 구조를 만들고 그 구조하에서 시스템 기능을 책임으로 나누어 객체에 분배하는 방식이다. 따라서 객체지향 설계를 제대로 해내기 위해서는 객체지향 설계의 대표적인 구조(모델)들을 알아보고 이해할 필요가 있다.</p>
<h3 id="도메인-모델--도메인-주도-설계ddd">도메인 모델 / 도메인 주도 설계(DDD)</h3>
<p>도메인 모델이란 사용자가 프로그램을 통해 <strong>이용하고자하는 분야/ 해결하고자 하는 영역(도메인)</strong> 을  추상화, 구조화해놓은 형태를 의미한다.<br>
예를 들어 은행업무 애플리케이션은 은행 업무를 이용하고자하는 클라이언트들이 사용하고, 은행업무를 수행하고자 하는 서버들이 구동할 것이다.</p>
<p>이때 <strong>은행 업무</strong> 라는 개념을 중심으로 애플리케이션을 구조화하는 것을 도메인 모델이라고 말한다.</p>
<p>도메인 모델을 사용함으로서 애플리케이션의 실사용자들도 개발되고 있는 앱의 구조와 흐름을 쉽게 파악하게 되고,</p>
<p>이를 바탕으로 이해관계자들의 요구사항과 개발자들의 구현간 일치율을 높여 변경사항을 줄이고 보다 요구사항에 적합한 개발을 빠르게 해낼수 있게 된다.</p>
<p><img src="https://mblogthumb-phinf.pstatic.net/MjAyMDA1MDdfMTMw/MDAxNTg4ODM2NTcwODUy.MjPDpg5CRWuDPjiXEb3iX2u9bCARNF3YCQDu8ABoE3Ag.Hcqx_FY76_8uzPfWonWdpxFrwvmzEPBWvsR8Wnu8TZsg.PNG.bernardokang/image.png?type=w800" alt="도메인 모델 예시"></p>
<h3 id="유스케이스">유스케이스</h3>
<p>도메인 모델이 클라이언트의 <strong>사용 영역</strong>을 중심으로 애플리케이션을 구조화해봤다면,</p>
<p>유스케이스 모델은 사용자가 애플리케이션을 <strong>실제 사용하는 시나리오</strong>에 초점을 맞추어 사용자 목표를 표현하는 방식이다.</p>
<p>다만 유스케이스 모델에서는 단지 사용자가 바라보는 시스템의 외부 관점만을 표현하며 내부의 설계에 대한 정보는 담지 않는다. 단지 시나리오의 집합일 뿐이며 설계 기법도 객체지향 기법도 아니므로</p>
<p>유스케이스에 집착하여 객체지향을 구현하는 접근법은 옳지 못하다.</p>
<h3 id="모든-도구를-활용하여-설계하기">모든 도구를 활용하여 설계하기</h3>
<p>애플리케이션을 만드는데 있어서 가장먼저 애플리케이션의 실사용 분야를 파악하고 이를 토대로 안정적인 구조를 만들기 위한 <strong>도메인 모델</strong></p>
<p>그리고 애플리케이션이 실제로 어떤 방식으로 클라이언트와 소통하고 어떤 기능이 필요한지를 서술하는 <strong>유스 케이스</strong></p>
<p>위의 두가지를 바탕으로 유스케이스에 기술된 기능들의 책임을 분할하여 적절한 객체를 찾아 할당하는 <strong>책임 주도 설계</strong></p>
<p>책의 저자는 위의 세가지를 중심으로 객체지향 설계방법을  다음과 같이 기술하고있다.</p>
<ul>
<li>요구사항을 식별하고 도출하는데 유스케이스를 사용하기</li>
<li>거대한 책임을 가진 시스템을 분할하여 객체에게 할당할때, 할당할 객체는 도메인모델에서 찾을것</li>
<li>객체의 이름은 도메인 모델에 포함된 개념부터 차용</li>
<li>책임은 도메인 모델에 정의한 개념에 부합하도록 할당</li>
<li>안정적인 도메인 모델을 기반으로 시스템 기능을 구현하며 도메인모델과 코드를 밀접하게 연관시키도록 노력하라</li>
</ul>
<h2 id="함께모으기">7. 함께모으기</h2>
<p>마틴 파울러는 소프트웨어의 객체지향 설계가 크게 세가지 관점에서 진행된다고 말했다.</p>
<p><strong>개념관점</strong> : 설계는 도메인 안에 존재하는 개념과 개념들 사이의 관계로 표현</p>
<p><strong>명세관점</strong> : 도메인영역을 벗어나 실제 객체들의 책임에 초점을 맞추고 객체의 인터페이스에 책임 부여를 설계</p>
<p><strong>구현관점</strong> 인터페이스를 구현한 객체가 실제로 어떻게 책임을 수행할것인지를 설계하는 관점으로 속성과 메서드 작성이 핵심</p>
<p>위의 과정은 순서대로가 아니라 어디까지나 관점의 영역이며 세가지 관점이 (자바의 경우) 클래스 안에서 잘 드러났을때 좋은 설계라 할 수 있을 것이다.</p>
<p>이제 책에서 나온 예시를 가지고 위의 관점을 적용하여 설계해보자</p>
<h3 id="커피-전문점-도메인도메인-분석-설계">커피 전문점 도메인(도메인 분석, 설계)</h3>
<ol>
<li>도메인 분석</li>
</ol>
<ul>
<li>
<p>커피 전문점의 핵심 비지니스 로직은 커피 판매이다. 따라서 커피 판매에 필요한 요구사항을 검토해보고 해당 요구사항에 필요한 객체들을 추려보자.</p>
</li>
<li>
<p>커피 메뉴는 총 4가지</p>
<ul>
<li>(아메리카노 1500원</li>
<li>카푸치노 2000원</li>
<li>카라멜 마키아또 2500원</li>
<li>에스프레소 2500원</li>
</ul>
</li>
<li>
<p>판매 비지니스 로직( <strong>유즈 케이스</strong> )</p>
<ul>
<li>손님은 커피메뉴에서 커피를 고를수 있고</li>
<li>고른 커피를 바리스타에게 제출하면</li>
<li>바리스타는 제출된 커피를 제조해서 손님에게 전달</li>
</ul>
</li>
<li>
<p>이제 커피 전문점에서 객체가 될 수 있는 후보들을 최대한 추려보자.<br>
우선 <strong>1. 손님들</strong>이 있을 것이고 <strong>2. 커피</strong> 와  <strong>3. 커피를 파는 사람</strong>이 존재할 것이다. 그리고 커피 구매 정보전달을 위한 <strong>4. 메뉴판</strong>과 <strong>5. 메뉴들</strong>을 생각해 볼 수 있다.</p>
</li>
<li>
<p>여기서 추가로 실무단계라면 여러 주문들을 별도로 다루기 위해 <strong>주문객체</strong>를 추가로 생각해 볼 수도 있다. 예제는 편의를 위해 일회성의 주문만을 고려하자</p>
</li>
<li>
<p>위의 객체 후보들을 추상화하여 클래스단위(타입)로 다뤄보면</p>
<ul>
<li>Customer</li>
<li>Barista</li>
<li>TotalMenu</li>
<li>CoffeeMenu</li>
<li>Coffee<br>
다음과 같이 추상화해볼 수 있을것이다.<br>
여기서 CoffeeMenu는 TotalMenu에 포함된 <strong>합성관계</strong> 또는 <strong>포함관계</strong> 이며, 이를 UML 다이어그램으로 도식화하면 아래와 같다.<br>
<img src="https://raw.githubusercontent.com/jinia91/ReadingGroupStudy/master/src/domain.png" alt="enter image description here"></li>
</ul>
</li>
</ul>
<p>이제 간단한 도메인 모델을 만들었고 여기에 커피 주문이라는 비지니스 로직상의 메시지 흐름들을 생각해보자</p>
<ol start="2">
<li>
<p>메시지와 책임</p>
<p>커피 주문에 필요한 메시지는 어떤것들이 있을까 순차적으로 생각해보면 다음과 같이 4가지 메시지로 축약해볼 수 있다.</p>
<ul>
<li>커피를 주문해라(String coffeeRequest)</li>
<li>메뉴항목에서 커피메뉴를 찾아라(String coffeeRequest)
<ul>
<li>CoffeeMenu 타입으로 반환</li>
</ul>
</li>
<li>커피를 주문해라(CoffeeMenu coffeeMenu)
<ul>
<li>Coffee 타입으로 반환</li>
</ul>
</li>
<li>커피를 만들어라(CoffeeMenu coffeeMenu)
<ul>
<li>Coffee 타입으로 반환</li>
</ul>
</li>
</ul>
<p>이제 다음 메시지들을 객체에 할당해주면 아래와 같은 시퀸스 다이어그램(축약형)이 완성된다.</p>
</li>
</ol>
<p><img src="https://raw.githubusercontent.com/jinia91/ReadingGroupStudy/master/src/sequence.png" alt="enter image description here"></p>
<ol start="3">
<li>인터페이스 정리하기</li>
</ol>
<p>완성된 도메인 모델을 바탕으로 실제 애플리케이션상에서 메시지를 송수신할 인터페이스를 만들어보자</p>
<pre><code>package coffee;

public class InterfaceGruop {

	public interface Customer {
		public void order(String coffee);
	}

	public interface CoffeeMenu {
	}

	public interface TotalMenu {
		public CoffeeMenu choice(String coffee);
	}

	public interface Barista {
		public Coffee makeCoffee(CoffeeMenu coffeeMenu);
	}

	public interface Coffee{
	
	}
</code></pre>
<p>}</p>
<p>처음 설계한 대로 5가지 인터페이스가 완성되고 3가지 메세지 호출과 Coffee 생성자를 통해 나중에 구현할 커피 만들어라 메세지호출까지 총 4가지 메세지 호출이 완성될 수 있다.</p>
<ol start="4">
<li>구현하기</li>
</ol>
<p>이제 위의 인터페이스를 구현하는 실제 객체들을 작성하면 모든 개발이 끝난다!</p>
<p>손님 구현 클래스</p>
<pre><code>package coffee;

import coffee.InterfaceGruop.Barista;
import coffee.InterfaceGruop.Coffee;
import coffee.InterfaceGruop.CoffeeMenu;
import coffee.InterfaceGruop.Customer;
import coffee.InterfaceGruop.TotalMenu;

class CustomerImple implements Customer{

	@Override
	public void order(String coffee, TotalMenu totalMenu, Barista barista) {
	    CoffeeMenu coffeeMenu = totalMenu.choice(coffee);
	    Coffee cof = barista.makeCoffee(coffeeMenu);

	}
}
</code></pre>
<p>메뉴판 구현 클래스</p>
<pre><code>package coffee;
import java.util.List;
import coffee.InterfaceGruop.CoffeeMenu;
import coffee.InterfaceGruop.TotalMenu;

class TotalMenuImpl implements TotalMenu {

	private List&lt;CoffeeMenu&gt; coffeeMenuList;

	public TotalMenuImpl(List&lt;CoffeeMenu&gt; coffeeMenuList) {
	this.coffeeMenuList = coffeeMenuList;
}

	public CoffeeMenu choice(String coffee) {
		for (CoffeeMenu each : coffeeMenuList) {
			if (each.getName().equals(coffee)) {
				return each;
			}
		}
	return null;
	}
}	
</code></pre>
<p>세부 메뉴 구현 클래스</p>
<pre><code>package coffee;

import coffee.InterfaceGruop.CoffeeMenu;

public class CoffeeMenuImpl implements CoffeeMenu {

	private String name;
	private int price;

	public CoffeeMenuImpl(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getPrice() {
		return price;
	}
}
</code></pre>
<p>커피 구현 클래스</p>
<pre><code>package coffee;

import coffee.InterfaceGruop.Coffee;
import coffee.InterfaceGruop.CoffeeMenu;

public class CoffeeImpl implements Coffee {

	private String name;
	private int price;

	public CoffeeImpl(CoffeeMenu coffeeMenu) {
		this.name = coffeeMenu.getName();
		this.price = coffeeMenu.getPrice();
	}
}
</code></pre>
<p>바리스타 구현 클래스</p>
<pre><code>package coffee;

import coffee.InterfaceGruop.Barista;
import coffee.InterfaceGruop.Coffee;
import coffee.InterfaceGruop.CoffeeMenu;

public class BaristaImpl implements Barista {

	@Override
	public Coffee makeCoffee(CoffeeMenu coffeeMenu) {
		Coffee coffee = new CoffeeImpl(coffeeMenu);
		return coffee;
	}
}
</code></pre>
<p>위와같이 구현된다. 여기서 인터페이스에 어느정도 수정이 있었는데</p>
<p>package coffee;</p>
<p>class InterfaceGruop {</p>
<pre><code>public interface Customer {
	public void order(String coffee,TotalMenu totalMenu, Barista barista);
}

public interface CoffeeMenu {
	public String getName();
	public int getPrice();
}

public interface TotalMenu {
	public CoffeeMenu choice(String coffee);
}

public interface Barista {
	public Coffee makeCoffee(CoffeeMenu coffeeMenu);
}

public interface Coffee{
	
}
</code></pre>
<p>}</p>
<p>손님 클래스의 객체가 주문하는 과정에서 바리스타와 메뉴판을 알아야함을 뒤늦게 파악하여 의존성을 주입해주기 위해 메시지에 인자를 추가하였다.</p>
<p>이처럼 최초의 설계는 구현과정에서 얼마든지 변경될 수 있으며, 최초의 설계는 어디까지나 대략적인 얼개에 불과하다는것을 잊지말자.</p>
<h3 id="※-인터페이스와-구현-분리는-언제나-중요함">※ 인터페이스와 구현 분리는 언제나 중요함!</h3>
<p>명세관점에서 객체의 인터페이스간 소통이 실제 훌륭한 설계를 결정하는것이다!</p>
<h3 id="todo">TODO</h3>

- <p>계산기의 서비스 로직이 너무 초보적인데 후위표기법을 사용하여 설계구조는 그대로 유지하면서 제대로된 계산기 로직으로 변경해보기</p>

- mvp 아키텍쳐 고민해보기


