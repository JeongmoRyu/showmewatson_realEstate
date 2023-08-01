import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:flutter/cupertino.dart';

import 'package:prac2/screens/home_screen.dart';
import 'package:prac2/screens/splash_screen.dart';
import 'package:prac2/screens/login_screen.dart';


// import 'package:prac2/prac1.dart';
// import 'package:prac2/prac2.dart';
// import 'package:prac2/prac3.dart';
// import 'package:prac2/prac4.dart';
// import 'package:prac2/prac5.dart';
// import 'package:prac2/prac6.dart';
// import 'package:prac2/prac7.dart';
// import 'package:prac2/prac8.dart';
// import 'package:prac2/prac9.dart';
// import 'package:prac2/prac10.dart';
// import 'package:prac2/prac11.dart';
// import 'package:prac2/prac12.dart';
// import 'package:prac2/prac13.dart';

Page checkAuth(Page page) {
  if (!checkAuthentication()) {
    return MaterialPage(child: LoginScreen());
  }
  return page;
}


final goRouter = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      pageBuilder: (context, state) => checkAuth(
        MaterialPage(
          key: ValueKey('home'),
          child: HomeScreen(),
        ),
      ),
    ),
    GoRoute(
      path: '/login',
      pageBuilder: (context, state) =>
        MaterialPage(
          key: ValueKey('login'),
          child: LoginScreen(),
        ),
    ),
  ],
);


// 사용자 인증을 확인하는 함수입니다.
// 이 예시에서는 단순히 false를 반환하며, 실제 사용자 인증 로직으로 대체되어야 합니다.
bool checkAuthentication() {
  return false; // 인증 실패
}

void main() {
  runApp(MyApp());
}

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
        }
    );
  }
  // 스플래쉬로딩위젯 선언(인스턴스)
  StatelessWidget _splashLoadingWidget(AsyncSnapshot<Object> snapshot) {
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

    // return MaterialApp(
    //   initialRoute: '/',
    //   routes: {
    //     '/' : (context) => prac1(),
    //     '/a': (context) => prac2(),
    //     '/b': (context) => prac3(),
    //     '/c': (context) => prac4(),
    //     // '/d': (context) => prac5(),
    //     '/e': (context) => prac6(),
    //     '/f': (context) => prac7(),
    //     '/g': (context) => prac8(),
    //     '/h': (context) => prac9(),
    //     '/i': (context) => prac10(),
    //     '/j': (context) => prac11(),
    //     '/k': (context) => prac12(),
    //     // '/l': (context) => prac13(),
    //
    //
    //
    //   },
    // );
}

class WatsonApp extends StatelessWidget {
  WatsonApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      debugShowCheckedModeBanner: false,
      routerDelegate: goRouter.routerDelegate,
      routeInformationParser: goRouter.routeInformationParser,
    );
  }
}

