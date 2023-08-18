# 사장님! 7인분 같은 6인분 주세요

----
# 보여줘 왓슨

- 개발기간: 2023.07.04 - 2023.08.18
- 주제: 매물 실시간 방송 서비스 제공 어플

#### 기술 스택

<div align="center">
<br>
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">
<img src="https://img.shields.io/badge/firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white">
</br>
<br>
<img src="https://img.shields.io/badge/flutter-02569B?style=for-the-badge&logo=flutter&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/mongodb-47A248?style=for-the-badge&logo=mongodb&logoColor=white">
<img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
</br>

<br>
<img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white">
</br>
<div>
<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jira&logoColor=white">
<img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
</div>
</div>

## 사전 개발 작업


### 공통
    - 서비스 아이콘 제작
    - 기술 스택 선정
    - 정보구조도 
![정보구조도](/uploads/a789c3a4597dd16b9642b5002b7873cb/image.png)
![중개사구조도](/uploads/e3f8510d1b700b495ebf14a8bcf52757/image.png)

### Backend
- ERD 제작  

![erd](readme_assets/erd.PNG)

- 아키텍쳐

![arch](readme_assets/architecture.png)

- 기능명세서

![re](readme_assets/re.PNG)

- sequence diagram



### Frontend

![mvp](readme_assets/figma_user.PNG)
![mvp](readme_assets/figma_agent.PNG)


- MVP 제작
    - Figma MVP 제작
        - MVP (Flutter) 초안 미완성
            - filter
            - 매물 등록 페이지(agent)
            - Like
            - MyPage
            - user
            - agent
            - Live Page
            - Live Page map
            - direct distance
            - Map
            - Index
            - SignUp
            - social login
            - SignIn(우선적 회원가입 위주)
            - social login
            - Licenses
            - payment
            - Details
                - 전화, 메세지 연결하기
                - 구매 정보
                - 방 정보
                - 추가 옵션
            - Chatting
            - DM
            - 알림창
            - 공인중개사 사무소 페이지
            - 공인중개사 등록 매물 페이지
            - live 일정 공지 창
            - 게시판 형식?
            - splash


## 개발 시작 및 진행 중

### Frontend

![splash](readme_assets/splash.PNG)
![main](readme_assets/main.PNG)
![detail](readme_assets/detail.PNG)
![filter](readme_assets/filter.PNG)

- Flutter MVP 제작
    - detail Page
        - 전화 연결
        - 구매정보
        - 방정보
        - 추가 옵션
        - carousel
    - filter
        - list
    - appbar
    - splash
    - navbar
    - router
    - 카카오 소셜 로그인(front)




### Backend

auth swagger(추후 외부 포트 닫을 예정)
http://i9a803.p.ssafy.io:8080/swagger-ui/index.html


business swagger
http://i9a803.p.ssafy.io:8081/swagger-ui/index.html

notice swagger
http://i9a803.p.ssafy.io:8082/swagger-ui/index.html


![api](readme_assets/api.PNG)

- CI/CD
- Docker를 사용한 배포 및 스크립트 파일 작성
- 카카오 소셜 로그인 및 회원가입(back)
- 라이브공지 CRUD
- 매물관리 CRUD
- Openvidu(Java) 구현
- 알림(1차, 2차, DM)
- Kafka를 통한 FCM 알림메시지 및 리스트 통신
- S3를 통한 매물 사진 관리
