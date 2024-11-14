# ⚡️일정 관리 앱 서버 (JPA)⚡️
## 1. 기능 구현
- JPA를 사용하여 사용자와 일정을 등록, 조회(전체/선택), 수정, 삭제 할 수 있는 CRUD 기능
    - 사용자(유저) : 등록(회원가입), 조회, 삭제
    - 일정 : 등록, 전체 조회, 단건 조회, 단건 수정, 단건 삭제
- 로그인을 하여 세션을 생성하고 세션이 있는 경우에만 다른 기능에 접근가능하도록 Filter를 적용
  
## 2. API 명세서
https://www.notion.so/13e3bd8753e48033a69dc7a9c9b9a60c?v=bdb9a9f182df4a79a821a21c9e42de92&pvs=4

## 3. ERD
<img width="563" alt="스크린샷 2024-11-14 오후 4 31 50" src="https://github.com/user-attachments/assets/45a4f2b0-ebd6-46a6-93f4-e11b195d48c1">

## 4. 어려웠던 문제

<details>
<summary> 로그인 시 예외 처리 오류 </summary>
<div markdown="1">
  <br>
  로그인을 위해 이메일과 비밀번호를 입력했을 때 기존 정보와 일치하지 않을 경우 401 UNAUTHORIZED가 뜨지 않고 500 INTERNAL SERVER ERROR이 뜸
</div>
</details>

<details>
<summary> 필터 적용 시 예외 처리 오류 </summary>
<div markdown="1">
  <br>
  로그인 후 세션생성이 되지 않은 상태에서 다른 기능들을 사용하려고 할 때 401 UNAUTHORIZED가 뜨지 않고 500 INTERNAL SERVER ERROR이 뜸
</div>
</details>





## 5. 개선사항
body에 입력하는 값들이 많기 때문에 더 많은 예외 처리가 가능하다. (예. email을 받기때문에 email 형식을 맞추지 않았을 경우의 예외처리 등)
토큰을 이용하거나 비밀번호 암호화를 통해 보안적인 측면을 강화할 수 있다.
## 6. 소감
이번 프로젝트를 진행하며 JPA로 데이터베이스에 접근하고 CRUD 기능을 구현하는 것은 많이 익숙해 졌다.
확실히 JPA를 사용하는 것이 JDBC에 비해 쿼리문을 직접 작성할 필요도 적고 콜렉션을 다루듯 여러 함수를 적용하기도 쉬워 작업이 용이했다.
또 이번 기회에 DB를 작성하며 다대일 연관관계와 외래키도 다루어볼 수 있어 좋았다.
다만, session과 filter의 개념은 아직 어려워서 복습이 더 필요할 듯 싶다. 
Session의 경우 session이 제대로 만들어졌는지, 만들어진 session의 정보에 대해 잘 해석할 수 없는 점이 아쉽고  
filter의 경우에는 filter가 작동되는 방식은 얼추 이해가 가는데 생성하는 과정(여러가지 설정이나 다운그레이딩 등)을 잘 이해하지 못해 추가적인 공부가 더 필요할 듯 하다.

