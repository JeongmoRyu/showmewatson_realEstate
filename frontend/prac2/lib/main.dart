import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/login/login_selection.dart';
import 'package:prac2/screens/all_notice.dart';


import 'package:provider/provider.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart' hide ChangeNotifierProvider, Provider;
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_auth/firebase_auth.dart';

import 'package:prac2/states/user_auth_provider.dart';

import 'package:prac2/screens/home_screen.dart';
import 'package:prac2/screens/splash_screen.dart';
import 'package:prac2/screens/interest_screen.dart';
// import 'package:prac2/screens/chatlist_screen.dart';
import 'package:prac2/screens/map_screen.dart';
import 'package:prac2/screens/mypage_screen.dart';
import 'package:prac2/screens/livelist_screen.dart';
import 'package:prac2/screens/live_notice_screen.dart';
import 'package:prac2/screens/notification_screen.dart';
import 'package:prac2/screens/all_notice.dart';

import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_my_notice.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_upload_sale.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_detailPage.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_notice_write.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_my_sale.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_chatlist_screen.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_mypage_screen.dart';

import 'package:prac2/login/login_screen.dart';
import 'package:prac2/login/login_platform.dart';
import 'package:prac2/login/login_selection.dart';

import 'package:prac2/base/navbar/named_route.dart';
import 'package:prac2/base/navbar/dashboard_screen.dart';

import 'package:prac2/real_estate_agent/estate_basic/estate_navbar/estate_named_route.dart';
import 'package:prac2/real_estate_agent/estate_basic/estate_navbar/estate_dashboard_screen.dart';


import 'package:prac2/detail/detailPage.dart';
import 'package:prac2/detail/agentDetail.dart';
import 'package:prac2/filter/filterPage.dart';
import 'package:prac2/filter/filterPage1.dart';
import 'package:prac2/detail/detailPageTwo.dart';
import 'package:prac2/live/livePage.dart';
import 'package:prac2/dm/directMessageLogin.dart';

import 'package:prac2/dm/chat_screen.dart';
import 'package:modal_progress_hud_nsn/modal_progress_hud_nsn.dart';
import 'package:prac2/dm/chatting_list.dart';

import 'package:prac2/screens/map_screen_city.dart';
import 'package:prac2/temp/prac7.dart';




void main() async {
  // 구글 소셜 로그인 : SDK 초기화
  WidgetsFlutterBinding.ensureInitialized();
  // await Firebase.initializeApp();
  Firebase.initializeApp();
  
  // 카카오톡 소셜 로그인 : SDK 초기화
  KakaoSdk.init(nativeAppKey: '1964206af6e9ee272eb2e64260079bc2');
  
  
  runApp(
      const ProviderScope(child: MyApp())
  );
}

final GlobalKey<NavigatorState> _rootNavigator = GlobalKey(debugLabel: 'root');
final GlobalKey<NavigatorState> _shellNavigator = GlobalKey(debugLabel: 'shell');

String? redirectIfNotAuthenticated(BuildContext context, GoRouterState state) {
  final authProvider = Provider.of<UserAuthProvider>(context, listen: false);
  final isAuthenticated = authProvider.loginPlatform != LoginPlatform.none;
  if (!isAuthenticated) {
    return '/login';
  } else {
    return null;
  }
}

final _router = GoRouter(
  navigatorKey: _rootNavigator,


  routes: [
    GoRoute(
      path: '/home',
      name: root,
      pageBuilder: (context, state) =>
        MaterialPage(
          key: state.pageKey,
          child: DashboardScreen(child: HomeScreen()),
        ),
      ),
    // GoRoute(
    //   path: '/chatlist',
    //   pageBuilder: (context, state) =>
    //       MaterialPage(
    //         key: ValueKey('chatlist'),
    //         child: DashboardScreen(child: ChatList()),
    //       ),
    // ),
    GoRoute(
      path: '/map',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('map'),
            child: DashboardScreen(child: MapScreen()),
          ),
    ),
    GoRoute(
      path: '/interest',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('interest'),
            child: DashboardScreen(child: Interest()),
          ),
    ),
    GoRoute(
      path: '/mypage',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('mypage'),
            child: DashboardScreen(child: MyPage()),
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
    GoRoute(
      path: '/login_select',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('login_select'),
            child: LoginSelection(),
          ),
    ),
    GoRoute(
      path: '/livelist',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('live'),
            child: DashboardScreen(child: LiveList()),
          ),
    ),
    GoRoute(
      path: '/livenotice',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('livenoitce'),
            child: DashboardScreen(child: LiveNotice()),
          ),
    ),
    GoRoute(
      redirect: redirectIfNotAuthenticated,
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
    GoRoute(
      path: '/agentDetail',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('agentDetail'),
            child: Agent(),
          ),
    ),
    GoRoute(
      path: '/filterPage1',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('filterPage1'),
            child: FilterOne(),
          ),
    ),
    GoRoute(
      path: '/notification',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('notification'),
            child: NotificationScreen(),
          ),
    ),
    GoRoute(
      path: '/all_notice',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('all_notice'),
            child: AllNotice(),
          ),
    ),

    // 중개인 Route
    GoRoute(
      path: '/estate_home',
      name: estate_root,
      pageBuilder: (context, state) =>
          MaterialPage(
            key: state.pageKey,
            child: EstateDashboardScreen(child: EstateHomeScreen()),
          ),
    ),
    GoRoute(
      path: '/my_notice',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('my_notice'),
            child: EstateDashboardScreen(child: MyNotice()),
          ),
    ),
    GoRoute(
      path: '/upload_sale',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('upload'),
            child: UploadSale(),
          ),
    ),
    GoRoute(
      path: '/estate_detail/:houseId',
      pageBuilder: (context, state) {
        final houseId = int.parse(state.pathParameters['houseId'] ?? '0');
        return MaterialPage(child: EstateDashboardScreen(child:EstateDetail(houseId: houseId)));
      }
    ),
    GoRoute(
      path: '/estate_notice_write/:houseId',
        pageBuilder: (context, state) {
          final houseId = int.parse(state.pathParameters['houseId'] ?? '0');
          return MaterialPage(child: NoticeWrite(houseId: houseId));
        }
    ),
    GoRoute(
      path: '/estate_my_sale',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('estate_my_sale'),
            child: EstateDashboardScreen(child: MySale()),
          ),
    ),
    GoRoute(
      path: '/map_screen_city',
      pageBuilder: (context, state) =>
          MaterialPage(
            key: ValueKey('map_screen_city'),
            child: EstateDashboardScreen(child: MapScreenCity()),
          ),
    ),

    ShellRoute(
        navigatorKey: _shellNavigator,
        builder: (context, state, child) => DashboardScreen(key: state.pageKey, child: child),

        routes: [
          GoRoute(
              path: '/',
              name: home,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: HomeScreen(
                        key: state.pageKey
                    )
                );
              }
          ),

          // GoRoute(
          //     path: '/chatlist',
          //     name: chatlist,
          //     pageBuilder: (context, state) {
          //       return NoTransitionPage(
          //           child: ChatList(
          //               key: state.pageKey
          //           )
          //       );
          //     }
          // ),

          GoRoute(
              path: '/map',
              name: map,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: MapScreen(
                        key: state.pageKey
                    )
                );
              }
          ),

          GoRoute(
              path: '/interest',
              name: interest,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: Interest(
                        key: state.pageKey
                    )
                );
              }
          ),

          GoRoute(
              path: '/mypage',
              name: mypage,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: MyPage(
                        key: state.pageKey
                    )
                );
              }
          ),
          GoRoute(
            path: '/detailPageTwo/:houseId',
            pageBuilder: (context, state) {
                final houseId = int.parse(state.pathParameters['houseId'] ?? '0');
                return MaterialPage(child: DashboardScreen(child:DetailTwo(houseId: houseId)));
                }
          ),

          GoRoute(
            path: '/livePage',
            pageBuilder: (context, state) =>
                MaterialPage(
                  key: ValueKey('livePage'),
                  child: Live(),
                ),
          ),
          GoRoute(
            path: '/directMessageLogin',
            pageBuilder: (context, state) =>
                MaterialPage(
                  key: ValueKey('directMessageLogin'),
                  child: StreamBuilder(
                    stream: FirebaseAuth.instance.authStateChanges(),
                    builder: (context, snapshot){
                      if (snapshot.hasData){
                        return ChattingList();
                      }
                      return directMessageLogin();
                    }
                  ),
                ),
          ),
          GoRoute(
            path: '/prac7',
            pageBuilder: (context, state) =>
                MaterialPage(
                  key: ValueKey('prac7'),
                  child: prac7(),
                ),
          )

        ]
    ),

    ShellRoute(
        navigatorKey: _shellNavigator,
        builder: (context, state, child) => EstateDashboardScreen(key: state.pageKey, child: child),

        routes: [
          GoRoute(
              path: '/estate_home',
              name: estate_home,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: EstateHomeScreen(
                        key: state.pageKey
                    )
                );
              }
          ),

          GoRoute(
              path: '/estate_chatlist',
              name: estate_chatlist,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: EstateChatListScreen(
                        key: state.pageKey
                    )
                );
              }
          ),

          GoRoute(
              path: '/estate_mypage',
              name: estate_mypage,
              pageBuilder: (context, state) {
                return NoTransitionPage(
                    child: EstateMyPageScreen(
                        key: state.pageKey
                    )
                );
              }
          ),
        ]
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
    return ChangeNotifierProvider<UserAuthProvider>(
      create: (BuildContext context) {
        return UserAuthProvider();
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
          textButtonTheme: TextButtonThemeData(
            style: ButtonStyle(
              backgroundColor: MaterialStateProperty.all<Color>(Color(0xFFDCBF97)),
              foregroundColor: MaterialStateProperty.all<Color>(Colors.black), // Set the default text color to white
            ),
          ),
        ),

      ),
    );
  }
}
