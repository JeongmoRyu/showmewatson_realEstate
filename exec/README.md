# 1. 빌드
### 1) 버전	
- Java : 11
- Gradle : 7.6.1
- SpringBoot : 2.7.14
- Flutter: 3.10.6
- MySQL: 10.3.38-MariaDB
- Redis: 7.0.12
- kafka: confluentinc/cp-kafka:7.0.0
- zookeeper: zookeeper:3.7
- docker:
    - engine: 24.0.5
    - container: 1.6.22
- mongoDB: 4.4.23
- openvidu-server: openvidu-server:2.20.1
- kurento: kurento-media-server:7.0.1
- openvidu-coturn: openvidu-coturn:2.28.0
- nginx: openvidu-proxy:2.28.0
	
----
##### IDE
- IntelliJ IDEA: IntelliJ IDEA 2023.1.4 (Ultimate Edition)
- MySQL Workbench: 8.0.27
- Android Studio: version 2022.2
- Android SDK version: 34.0.0
- Emulator
    - Pixel 3a API 31
    - SM-G981N
----


		
### 2) 빌드 시 사용되는 환경 변수

#### - auth
DB_PASSWORD=watson;DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306;DB_USER=watson;JWT_SECRET=showmewatsonshowmewatsonshowmewatsonshowmewatson;JWT_TOKEN_VALIDITY_IN_SECONDS=32400;K-REDIRECT=http://localhost:8080/login/oauth2/code/kakao;KCLIENT-ID=3bbb345ff4f2ab8c8abac4f51a378293;KCLIENT-SECRET=sl1r7uema7WmBUN9tPHZrvD195z2rexJ;S3_ACCESS_KEY=AKIA5M56SVVXIXYJLBFZ;S3_SECRET_KEY=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;SECURITY_CODE=showmewatsonwatson;REDIS-PASSWORD=watson1234
#### - business
DB_PASSWORD=watson;DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306;DB_USER=watson;MONGODB_HOST=I9A803.p.ssafy.io;MONGODB_PORT=27017;MONGODB_PW=watson1234;MONGODB_USER=watson;S3_ACCESS_KEY=AKIA5M56SVVXIXYJLBFZ;S3_SECRET_KEY=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
#### - notice-producer
DB_PASSWORD=watson;DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306;DB_USER=watson;MONGODB_HOST=I9A803.p.ssafy.io;MONGODB_PORT=27017; MONGODB_PW=watson1234;MONGODB_USER=watson
#### - notice-consumer
FCM_SCOPE=https://www.googleapis.com/auth/cloud-platform;FCM_PATH=/firebase/firebase-admins
#### - view-calculate
DB_URL=jdbc:mysql://I9A803.p.ssafy.io:3306;DB_USER=watson;DB_PASSWORD=watson;REDIS-PASSWORD=watson1234


### 3) 배포 특이사항	
- deploy 폴더 내부의 스크립트와 docker-compose.yml파일을 통해 빌드
		
		
		
		
### 4) DB 접속 정보 등 프로젝트에 활용되는 주요 계정 및 프로퍼티	
- 환경 변수에 포함	
		
		
		

		
# 2. 프로젝트에서 사용하는 외부 서비스를 정보를 정리	
- AWS S3 : 매물 사진, 사용자의 프로필에 대한 이미지 저장		
- FireStore : 채팅데이터 저장		
- Firebase Cloud Messaging : 관심 매물, 라이브 공지에 대한 알림 서비스		
- Kakao Login : 소셜 로그인		


		
# 3. DB 덤프 파일 최신본		
docs 내부의 sql 파일 참고	
		
		
		

# 4. 시연 시나리오		
- User : 어플실행 - 스플래쉬 화면 - 지도를 통해 특정 지역(강남구 역삼동 멀티 캠퍼스 근처의 매물을 확인 매물들의 차이도 보여주기) - 매물 detail page들 보여주기 - 나와서 공인중개사와 연락하는 채팅 -  채팅 - 방송공지 확인		
- Agent : Agent 로그인 - 방송 공지들 확인 -  내가 올린 매물들 정보 제공 - 들어가서 공지쓰기 및 데이터들 보여주기 -  매물 등록하기 - 각 매물들의 옵션 클릭 - 방송하기		
