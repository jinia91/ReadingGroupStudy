---


---

<h2 id="스터디-일지">스터디 일지</h2>
<p>1주차 : 발표자 Albireo3754 (2021.08.16~2021.08.22)</p>
<ul>
<li>발표자료: <a href="https://albi-tech.tistory.com/5"></a><a href="https://albi-tech.tistory.com/5">https://albi-tech.tistory.com/5</a></li>
<li>과제 코드: <a href="https://albi-tech.tistory.com/6"></a><a href="https://albi-tech.tistory.com/6">https://albi-tech.tistory.com/6</a></li>
</ul>
<h2 id="section"></h2>
<p><img src="https://user-images.githubusercontent.com/85499582/130334275-c14f1ae5-3953-4f29-8274-447b8e7df7bf.gif" alt="calculatorver_0 1"></p>
<h2 id="스터디-과제-계산기-만들기">0. 스터디 과제 계산기 만들기</h2>
<h3 id="주차--러프하게-기능만-구현하기">1주차 : 러프하게 기능만 구현하기</h3>
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
<h3 id="주차-책임-주도-설계와-mvc패턴">2주차: 책임-주도 설계와 MVC패턴</h3>
<p>1주차에 계산기의 코드를 잠깐 살펴보면</p>
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
<h2 id="협력하는-객체들의-공동체">1. 협력하는 객체들의 공동체</h2>
<h3 id="객체지향이란">객체지향이란</h3>
<p>객체 지향이란, 애플리케이션을 <strong>1)상호작용하는 2)자율적인 객체들의 집합</strong>으로 보고, 객체를 이용해 <strong>3)시스템을 분할</strong>하는 방법론을 의미한다.</p>
<ul>
<li>
<p>1)객체들의 상호작용은 <strong>요청과 응답</strong>으로 구성되며, 요청은 추상화된 메시지를 통해서 전달되고 응답은 구체화된 메소드를 통해서 행해진다.</p>
</li>
<li>
<p>2)자율적인 객체란 <strong>상태</strong>와 <strong>행위</strong>를 가지며, 스스로 자기 자신을 책임지는 객체를 의미한다.</p>
</li>
<li>
<p>3)커다란 시스템의 행위를 구현하기 위해 내부의 객체들은 요청과 응답을 상호작용하는데 이를 <strong>협력</strong>으로 정의할 수 있다.</p>
</li>
<li>
<p>4)각 객체는 협력속에서 정해진 <strong>역할</strong>과 역할에 해당하는 <strong>책임</strong>이 존재한다.</p>
</li>
</ul>
<h2 id="이상한-나라의-객체">2. 이상한 나라의 객체</h2>
<p>객체란 <strong>상태</strong>와 <strong>행동</strong>을 가지며 <strong>식별가능한</strong> 개체(Entity) 혹은 사물(Object)이다.</p>
<p>객체의 상태는 변경가능하며(mutable) 변경시키는 것은 오로지  <strong>자신의  행동</strong>이여야하고(캡슐화), 외부에서는 자신의 상태를 접근할 수 없다. -&gt; <em>자율적인 객체</em><br>
이는 행동의 결과가 상태에 <strong>의존적</strong>이다라고 표현할 수 있다.</p>
<blockquote>
<p>프로그래밍에서 <strong>의존</strong>이란 의존하는 대상을 <strong>알고</strong>있음을 의미</p>
</blockquote>
<p>또한 행동은 행동 내에서 협력하는 다른 객체에 대해 메세지를 전송하기도 한다.</p>
<p>프로그래밍에서 객체를 구분하는데는 <strong>동등성(equality)</strong> 과 <strong>동일성(identical)</strong> 두가지 기준을 사용한다.</p>
<p><strong>동등성</strong>이란 값(value)가 같은지 다른지의 여부를 통해 객체의 일치 여부를 판단하는 기준이다.</p>
<blockquote>
<p>자바에서 String 클래스의 equals()메소드가 동등성의 원리로 재정의한 대표적인 예시.<br>
클래스를 만들때 객체의 동등성을 표현하고 싶다면 equals()메소드를 오버라이딩하여 값을 비교하면 된다.</p>
</blockquote>
<p><strong>동일성</strong>이란 객체가 완전히 일치하는지의 여부로, 객체는 식별자를 통해 동일성을 판단할 수 있다.</p>
<blockquote>
<p>자바에서 == 은 객체의 참조주소값을 비교하게 되는데 이를 통해 참조변수가 지목하는 객체가 동일한지 여부를 확인가능하다.</p>
</blockquote>
<h3 id="객체지향의-오해">객체지향의 오해</h3>
<p>객체지향은 현실의 <strong>모방</strong>이 아니라 <strong>은유</strong>이다.</p>
<p>객체지향 세계에서 은유된 개체 혹은 사물들은 의인화되어 다른 개체 혹은 사물에게 메세지를 전송하며 요청하고</p>
<p>메세지를 수신하고 이에 응답해 행동하며 그 행동은 자율적으로 개체 혹은 사물 자신의 상태를 변화시키고 다시 다른 개체 혹은 사물에게 메세지를 전송한다.</p>
<p>응답과 요청, 메시지의 수신과 송신, 행동과 상태변화의 무수한 반복을 통해 하나의 큰 협력을 이루고 협력은 시스템이 되어 우리가 만드는 앱이된다.</p>
<p>객체 지향의 설계는 이러한 의인화된 시스템을 창조하는 과정이다.</p>
<h2 id="타입과-추상화">3. 타입과 추상화</h2>
<p>객체지향의 시각에서 의인화된 시스템을 바라볼 때 구동중인 애플리케이션은</p>
<p>수많은 <strong>객체들이</strong> 바쁘게 <strong>행동</strong>하고 있는 <strong>협력</strong>활동, 혹은 협력하는 세상으로 볼 수 있다.</p>
<p>개발자로서 위와 같은 세상을 코드로 구현하려한다고 상상해보자.</p>
<p>모든 객체들을 손수 코드로 작성하려면 너무나 많은 코드가 필요하고 중복되는 코드도 많아 비효율적일 것이다.</p>
<p>이를 극복하기 위해 우리는 추상화라는 도구를 사용할 수 있다.</p>
<blockquote>
<p>객체지향에서 추상화란 의도를 가지고 목적을 정한뒤, 목적에 불필요한 부분을 제거하여 단순화하는것으로 정의할 수 있다.</p>
</blockquote>
<p>구체적인 방법으로 살펴보면 개별적인 객체들을 특정 목적을 가지고 그에 부합한 공통점을 추출해낸 뒤, 불필요한 부분을 덜어내어</p>
<p><strong>특정 목적에 해당하는 공통점</strong>을 가진 객체들의 집합, 묶음을 만들어낼 수 있다는 것이다.</p>
<p>여기서 <strong>특정 목적에 해당하는 공통점</strong>을 쉽게 말해 컨셉(Concept), 개념으로 축약할 수 있다.</p>
<p>즉, 하나의 <strong>컨셉(개념) 안에 속하는 객체들을 선별</strong>해 낼 수 있다면, 위에서 살펴본 객체들의 세상을 훨씬 단순하게 바라볼 수 있게되고 보다 쉽게 개발할 수 있을 것이다.</p>
<h3 id="타입type">타입(Type)</h3>
<p>객체지향에서는 이 컨셉이라는 단어를 보다 다듬어 <strong>타입(Type)</strong> 으로 부른다. 자바에서 항상 사용하는 데이터 타입(int, char…)이나 참조타입(String, Math, 그리고 우리가 정의한 클래스들)이 바로 여기에 해당하는 것이다.</p>
<p>그렇다면 우리는 객체들을 어떻게 타입으로 분류해야할까?</p>
<p>객체의 상태는 행동에 의해 변화될 수 있으므로 상태를 통해 타입을 분류하는것은 적절하지 못하며</p>
<p>객체들의 <strong>공통된 행동</strong>을 중심으로 타입으로 분류하는것이 적절하다.</p>
<p>객체를 공통된 행동으로 한데 묶고, 시간에 따른 <strong>상태 변화</strong>를 덜어냄으로서 추상화를 하여 분류하면</p>
<p>여러 객체들은 특정 행동을 하는 하나의 추상적 타입(컨셉)으로 분류화될것이고</p>
<p>이를 통해 어플리케이션의 세계를 보다 간략하게 파악하고 손쉽게 다룰 수 있게되는 것이다.</p>
<blockquote>
<p>자바에서는 개별 객체들을 추상화를 통한 개념으로 묶어 Class로 표현하고, 상위 계층의 타입은 부모 클래스 혹은 인터페이스로 표현할 수 있다. 즉 추상화(일반화)/특수화를 통해 상속이라는 방법론이 발명된것이다.</p>
</blockquote>
<h2 id="역할-책임-협력">4. 역할, 책임, 협력</h2>
<p>객체 지향의 시각에서 가장 중요한 것은 <strong>객체의 행동</strong>이다.<br>
하지만 객체는 스스로 행동을 시작하지 않으며 <strong>다른 객체의 요청</strong>과 <strong>그에 대한 응답으로</strong> 행동이 이루어 진다.</p>
<p>즉 객체는 혼자서 고립된 섬이 아니라 여러 객체들이 서로 <strong>상호작용</strong>하는 <strong>협력체, 협력 시스템</strong>인 것이다.</p>
<blockquote>
<p>협력 : 요청과 응답을 통한 객체들간의 상호작용</p>
</blockquote>
<p>그리고 어떤 객체가 요청(메시지)을 하면 그 요청을 들은(메시지를 받은, 메시지 호출을 받은) 객체는 <strong>응답(행동)해야할 책임</strong>이 있다.</p>
<p>여기서 객체는 <strong>여러가지</strong> (행동해야할)책임집합을 갖기도 하는데 이 책임은 반드시 응집도 있는 행위들의 집합이여야한다.</p>
<blockquote>
<p>여러 책임들이 응집도있다면 이는 <strong>하나의 큰 책임 집합</strong>으로 여겨질 수 있다</p>
</blockquote>
<p>만약 하나의 객체가 지나치게 많은 책임이나, 응집도가 떨어지는 책임집합을 갖고있다면</p>
<p>이는 너무 많은 책임을 갖고 있다는 것이며(SRP 위배), 객체지향설계가 적절하게 이루어 지지 않았다는 의미다.</p>
<blockquote>
<p>책임 : 메시지를 받은 객체가 응답해야 할 의무</p>
</blockquote>
<p>마지막으로, 역할을 이해하기에 앞서 3챕터의 내용을 잠깐 되새겨보자.</p>
<p>3챕터에서는 객체들의 공통된 행동을 묶어 추상화를 통해 타입으로 분류할수 있다고 했다.</p>
<p>그런데 행동은 메시지에 대한 응답할 의무(책임)의 발현이다. 따라서 하나의 메시지에 대한 여러 객체들의 동일한 행동이 가능하다면, 이 객체들은 하나의 **역할(Role)**을 수행한다고도 말할 수 있을것이다.</p>
<blockquote>
<p>객체지향에서 역할이란 <strong>메세지를 수신하고 이를 책임질수 있는</strong>(행동할 수 있는) 능력을 의미한다.</p>
</blockquote>
<p>즉 역할이 가능한 객체들은 하나의 분류로 추상화될 수 있고 이를 방법론적으로 자바에서는 클래스/인터페이스로 구현할 수 있다.</p>
<h3 id="책임주도-설계">책임주도 설계</h3>
<p>2주차 계산기 과제로.</p>

