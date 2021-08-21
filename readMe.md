---


---

<img src="https://sat02pap001files.storage.live.com/y4myv7fEoTTnjl4QbIqN7XdAiI27S8fFNMc8sh6wbSYs_M4ZRGVhWf7N-82VTPpTEGTBK17v8FWz3QBWl-Svb8GBgqZzkY67FUkf6OmKbltX2BbE5kHNFrIauHVUZvB7G2hQRF9ArLBJKGiEghqX_e6-Q9bYBiVTZM2QIPOiFQ-N8ZMa106aZDoLB3MwluhHByU?width=600&height=384&cropmode=none" width="600" height="384" />



<h2 id="협력하는-객체들의-공동체">1. 협력하는 객체들의 공동체</h2>
<h3 id="객체지향이란">객체지향이란</h3>
<p>객체 지향이란, 시스템을 <strong>1)상호작용하는 2)자율적인 객체들의 공동체</strong>로 바라보고, 객체를 이용해 시스템을 분할하는 방법론을 의미한다.</p>
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
<h3 id="요약--객체지향의-오해">요약 : 객체지향의 오해</h3>
<p>객체지향은 현실의 <strong>모방</strong>이 아니라 <strong>은유</strong>이다.</p>
<p>객체지향 세계에서 은유된 개체 혹은 사물들은 의인화되어 다른 개체 혹은 사물에게 메세지를 전송하며 요청하고</p>
<p>메세지를 수신하고 이에 응답해 행동하며 그 행동은 자율적으로 개체 혹은 사물 자신의 상태를 변화시키고 다시 다른 개체 혹은 사물에게 메세지를 전송한다.</p>
<p>응답과 요청, 메시지의 수신과 송신, 행동과 상태변화의 무수한 반복을 통해 하나의 큰 협력을 이루고 협력은 시스템이 되어 우리가 만드는 앱이된다.</p>
<p>객체 지향의 설계는 이러한 의인화된 시스템을 창조하는 과정이다.</p>
<h2 id="section"></h2>

