import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/screens/live_notice_screen.dart';
import 'package:prac2/states/user_provider.dart';
import 'package:provider/provider.dart';
// import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';

import 'package:prac2/screens/home_screen.dart';
import 'package:prac2/screens/splash_screen.dart';
import 'package:prac2/screens/login_screen.dart';
import 'package:prac2/screens/interest_screen.dart';
import 'package:prac2/screens/chatlist_screen.dart';
import 'package:prac2/screens/map_screen.dart';
import 'package:prac2/screens/mypage_screen.dart';
import 'package:prac2/screens/livelist_screen.dart';

import 'package:prac2/base/navbar.dart';
import 'package:prac2/detail/detailPage.dart';
import 'package:prac2/filter/filterPage1.dart';



void main() {
  // KakaoSdk.init(nativeAppKey: '{1964206af6e9ee272eb2e64260079bc2}');
  runApp(MyApp());
}

final _router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      pageBuilder: (context, state) =>
        MaterialPage(
          key: ValueKey('home'),
          child: HomeScreen(),
        ),
      ),
    GoRoute(
      path: '/chatlist',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('chatlist'),
            child: ChatList(),
          ),
    ),
    GoRoute(
      path: '/map',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('map'),
            child: MapScreen(),
          ),
    ),
    GoRoute(
      path: '/interest',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('interest'),
            child: Interest(),
          ),
    ),
    GoRoute(
      path: '/mypage',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('mypage'),
            child: MyPage(),
          ),
    ),
    // GoRoute(
    //   path: '/login',
    //   pageBuilder: (context, state) =>
    //       MaterialPage(
    //         key: ValueKey('login'),
    //         child: LoginScreen(),
    //       ),
    // ),
    GoRoute(
      path: '/livelist',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('live'),
            child: LiveList(),
          ),
    ),
    GoRoute(
      path: '/livenotice',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('livenoitce'),
            child: LiveNotice(),
          ),
    ),
    GoRoute(
      path: '/detailPage',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('detailPage'),
            child: Detail(),
          ),
    ),
    GoRoute(
      path: '/filterPage',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('filterPage'),
            child: Filter(),
          ),
    ),
  ],
);

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // future 함수로 로딩 구현
    // 1. 유저가 홈화면에 입장 시도
    // 2. 3초간 데이터를 받아오는 지연시간 발생
    // 3-1. 만약 데이터가 비정상적 : 에러 발생 결과
    // 3-2. 만약 데이터가 정상적 : 홈화면
    // 3-3. 에러도 아니고 데이터 완료도 아닐 때 : 스플래쉬 화면
    return FutureBuilder<Object>(
        future: Future.delayed(Duration(seconds: 3), () => 100),
        builder: (content, snapshot) {
          return AnimatedSwitcher(
            duration: Duration(milliseconds: 0), // 페이드인아웃 효과
            child: _splashLoadingWidget(snapshot), // 스냅샷실행 위젯
          );
        },
    );
  }
  // 스플래쉬로딩위젯 선언(인스턴스)
  Widget _splashLoadingWidget(AsyncSnapshot<Object> snapshot) {
    if(snapshot.hasError) {
      print(('에러가 발생하였습니다.'));
      return Text('Error');
    } // 에러발생
    else if(snapshot.hasData) {
      return WatsonApp();
    } // 정상
    else{
      return SplashScreen();
    } // 그외
  }
}

class WatsonApp extends StatefulWidget {
  @override
  _WatsonAppState createState() => _WatsonAppState();
}

class _WatsonAppState extends State<WatsonApp> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<UserProvider>(
      create: (BuildContext context) {
        return UserProvider();
      },
      child: MaterialApp.router(
        debugShowCheckedModeBanner: false,
        routerConfig: _router,
        theme: ThemeData(
          primaryColor: Color(0xFFDCBF97),
          elevatedButtonTheme: ElevatedButtonThemeData(
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all<Color>(Color(0xFFDCBF97)),
            ),
          ),
        ),

      ),
    );
  }
}
