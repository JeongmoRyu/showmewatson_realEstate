# Figma MVP 1차 완성
- 유저 페이지 & 중개인 페이지 나눠서 작업
- 각자 만든 페이지 병합 작업

</br>

- 기록 사항
    - MVP 초안 미완료 및 추가
        - filter
        - 매물 등록 페이지(agent)
        - 매물 등록된 페이지(agent) 수정
    - MVP 초안 완료
        - Temp
        - Like
        - MyPage
            - user
            - agent
        - Live Page
            - Live Page map
                - direct distance
                - fastest way
        - Map
        - Index
        - SignUp
            - social login
        - SignIn(우선적 회원가입 위주)
            - social login
        - Licenses
        - payment
        - Details
            - 기본페이지
            - 전화, 메세지 연결하기
            - 구매 정보
            - 방 정보
            - 추가 옵션
        - Chatting
        - DM
        - 알림창
        - 공인중개사 사무소 페이지
            - 신뢰도?
            - 공인중개사 등록 매물 페이지
        - live 일정 공지 창
            - 게시판 형식?

</br>

- 화요일(7/24) 1차 MVP 병합물 완성
    - 수정사항
        - 일반 이용자 프로필 사진 전부 삭제

        - 공지 게시판에 필터 삽입

        - 매물 검색 페이지 수정

        - 체크리스트(상중하 → 1~5점) 변경

        - 로그인 시 중개사/일반인 선택

        - 중개사 프로필 등록 창

        - 방송 공지글에서만 방송 킬 수 있게

        - 매물 디테일 창에서만 공지를 쓸 수 있게

        - 매물 디테일 창에서 가격, 사진 수정 가능하도록

        - 매물 디테일 창에서 ‘공지 쓰기’ 버튼, ‘수정하기’ 버튼 삽입

        - 중개사용 알림 페이지 삭제

        - 중개사용 DM 프로필 전부 삭제, 닉네임으로 대체

</br>

## 보완사항 & 미완성 페이지
- 중개인 매물등록 페이지
    - 매물 입력 정보
        - 거래 유형
        - 지역
        - 방 유형
        - 전용 면적
        - 공급 면적
        - 건물 전체 층수
        - 층수
        - 상세 주소
        - 제목
        - 내용
        - 건축물 용도
        - 건축물 승인일
        - 욕실수
        - 옵션
            - 옵션 목록
                - 싱크대
                - 에어컨
                - 신발장
                - 세탁기
                - 냉장고
                - 옷장
                - 인덕션
                - 책상
                - 엘리베이터유무
                - 냉난방
                - 주차가능여부

- 일반 유저 마이페이지 수정
- Filter Page : 월세/전세/매매 中 택 1
    - 목표 : UI적 강제적 선택지 제한
        - 초안 : Switch Button & 각각 다른 페이지 보여주기
        - 대안 : 월세/전세/매매 선택지만 있는 페이지 별도 제작


</br>

## Figma MVP
[Figma Link] :
https://www.figma.com/file/SOoHTxgMwKQqBppPFFAvHY/%EB%B6%80%EB%8F%99%EC%82%B0-%EC%A4%91%EA%B0%9C-%ED%99%94%EC%83%81-%ED%94%8C%EB%9E%AB%ED%8F%BC-%EC%95%B1-MVP-(1%EC%B0%A8-%EC%99%84%EC%84%B1%EB%B3%B8)-(Copy)?type=design&node-id=111%3A2&mode=design&t=TVkbpvHyW6cywk2u-1

![Fimga_MVP_1](https://github.com/HJH13579/TIL/assets/58537329/5eb9e443-4814-4b5c-83f8-cb821dd9f734)

![Fimga_MVP_2](https://github.com/HJH13579/TIL/assets/58537329/64caca1e-531a-4287-ac6b-af70cc0e1245)

![Fimga_MVP_3](https://github.com/HJH13579/TIL/assets/58537329/d2c4c1dd-27c5-4db5-adea-901c344348b2)

</br>

# Prototype 

## Splash page
1. flutter_native_splash 패키지가 제대로 작동을 안 해서 문제였다.
2. 작동한 후에도 제작한 스플래시 페이지 외 자체적인 스플래시 페이지가 계속 떠서 내장 스플래시 페이지 수정 시도
3. Android 12 이상부터 내장 스플래시 페이지가 있으며, 수정/삭제가 용이하지 않아 다른 앱들은 하얀 빈 페이지나 아이콘만 중앙에 띄우고 제작한 스플래시 페이지를 띄운다는 것을 알아냄.
4. 내장 스플래시 페이지는 아이콘을 끌어서 중앙에 둔다는 것을 인지, 아이콘 수정 시도


## Flutter Launcher Icons
1. 아이콘 수정 시도
    - 기존 아이콘처럼 배경색을 하얀색이 아닌 고유컬러로 교체 시도 (실패)
    - 아이콘 변경 시도(성공) But 내장 스플래시 페이지에서 하얀 바탕에 로고만 떡하니 있는 대참사 발생
    - 수정 시도 예정 : 아이콘 배경 고유컬러 + 아이콘 변경 + 내장 스플래시 빈 페이지(하얀색 페이지)
