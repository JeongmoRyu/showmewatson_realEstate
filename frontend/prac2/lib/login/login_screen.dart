import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:go_router/go_router.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';

import 'package:prac2/login/login_platform.dart';
import 'package:prac2/states/user_auth_provider.dart';

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  LoginPlatform _loginPlatform = LoginPlatform.none;

  Future<void> signInWithGoogle() async {
    try {
      final GoogleSignInAccount? googleUser = await GoogleSignIn().signIn();

      if (googleUser != null) { // null 체크 추가
        final GoogleSignInAuthentication googleAuth = await googleUser.authentication;
        final credential = GoogleAuthProvider.credential(
          accessToken: googleAuth.accessToken,
          idToken: googleAuth.idToken,
        );
        await FirebaseAuth.instance.signInWithCredential(credential);
        context.go('/home'); // 홈 화면으로 이동

        setState(() {
          _loginPlatform = LoginPlatform.google;
        });

        print('Google 로그인 성공!');
      } else {
        print('Google 로그인 취소');
      }
    } catch (error) {
      print('Google 로그인 실패 $error');
    }
  }

  void signInWithKakao() async {
    try {
      bool isInstalled = await isKakaoTalkInstalled();
      OAuthToken token = isInstalled
          ? await UserApi.instance.loginWithKakaoTalk()
          : await UserApi.instance.loginWithKakaoAccount();

      context.go('/home'); // 홈 화면으로 이동

      setState(() {
        _loginPlatform = LoginPlatform.kakao;
      });

      print('카카오톡으로 로그인 성공!');
    } catch (error) {
      print('카카오톡으로 로그인 실패 $error');
    }
  }

  void signOut() async {
    switch (_loginPlatform) {
      case LoginPlatform.google:
        await GoogleSignIn().signOut();
        break;
      case LoginPlatform.kakao:
        await UserApi.instance.logout();
        break;
      case LoginPlatform.none:
        break;
    }

    setState(() {
      _loginPlatform = LoginPlatform.none;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color(0xFFDCBF97),
        centerTitle: true,
        title: InkWell(
          onTap: () {
            context.go('/');
          },
          child: Image.asset(
            'assets/images/app_icon.png',
            width: 45,
            height: 45,
            fit: BoxFit.contain,
          ),
        ),
      ),
      body: Center(
        child: _loginPlatform != LoginPlatform.none
            ? ElevatedButton(
          onPressed: signOut,
          style: ButtonStyle(
            backgroundColor: MaterialStateProperty.all(
              const Color(0xff0165E1),
            ),
          ),
          child: const Text('로그아웃'),
        )
            : Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            _loginButton(
              'assets/images/google_login_button.png',
              signInWithGoogle,
            ),
            SizedBox(height: 20),
            _loginButton(
              'assets/images/kakao_login_button.png',
              signInWithKakao,
            ),
          ],
        ),
      ),
    );
  }

  Widget _loginButton(String imagePath, VoidCallback onTap) {
    return InkWell(
      onTap: onTap,
      child: Image.asset(
        imagePath,
        width: 300,
        height: 50,
        fit: BoxFit.fill,
      ),
    );
  }
}


// import 'dart:convert';
// import 'dart:io';
//
// import 'package:flutter/material.dart';
// import 'package:go_router/go_router.dart';
// import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
// import 'package:http/http.dart' as http;
// import 'package:provider/provider.dart';
//
// import 'package:prac2/login/login_platform.dart';
// import 'package:prac2/states/user_auth_provider.dart';
//
//
// // 로그인 화면
// class LoginScreen extends StatefulWidget {
//   const LoginScreen({Key? key}) : super(key: key);
//
//   @override
//   State<LoginScreen> createState() => _KakaoLoginPageState();
// }
//
// class _KakaoLoginPageState extends State<LoginScreen> {
//   LoginPlatform _loginPlatform = LoginPlatform.none;
//
//
//   void signInWithKakao() async {
//     try {
//       bool isInstalled = await isKakaoTalkInstalled();
//
//       OAuthToken token = isInstalled
//           ? await UserApi.instance.loginWithKakaoTalk()
//           : await UserApi.instance.loginWithKakaoAccount();
//
//       final url = Uri.https('kapi.kakao.com', '/v2/user/me');
//
//       // final response = await http.get(
//       //   url,
//       //   headers: {
//       //     HttpHeaders.authorizationHeader: 'Bearer ${token.accessToken}'
//       //   },
//       // );
//
//       context.go('/'); // 홈 화면으로 이동
//
//       setState(() {
//         _loginPlatform = LoginPlatform.kakao;
//       });
//
//       print('로그인 성공!');
//
//     } catch (error) {
//       print('카카오톡으로 로그인 실패 $error');
//     }
//   }
//
//   void signOut() async {
//     switch (_loginPlatform) {
//       case LoginPlatform.google:
//         break;
//       case LoginPlatform.kakao:
//         await UserApi.instance.logout();
//         break;
//       case LoginPlatform.none:
//         break;
//     }
//
//     setState(() {
//       _loginPlatform = LoginPlatform.none;
//     });
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//
//       // AppBar 부분
//       appBar: AppBar(
//         backgroundColor: Color(0xFFDCBF97),
//         centerTitle: true,
//         title: InkWell(
//           onTap: () {
//             context.go('/');
//           },
//           child: Image.asset(
//             'assets/images/app_icon.png',
//             width: 45, // 아이콘의 너비
//             height: 45, // 아이콘의 높이
//             fit: BoxFit.contain, // 이미지를 부모 위젯 크기에 맞게 조절
//           ),
//         ),
//       ),
//
//       body: Center(
//           child: _loginPlatform != LoginPlatform.none
//               ? _logoutButton()
//               : Row(
//             mainAxisAlignment: MainAxisAlignment.center,
//             children: [
//               _loginButton(
//                 'kakao_logo',
//                 signInWithKakao,
//               )
//             ],
//           )),
//     );
//   }
//
//   Widget _loginButton(String path, VoidCallback onTap) {
//     return InkWell(
//         onTap: onTap,
//         child: Image.asset(
//             'assets/images/kakao_login_button.png',
//           width: 300,
//           height: 50,
//           fit: BoxFit.fill, // 이미지를 부모 위젯 크기에 맞게 조절
//         ),
//     );
//   }
//
//   Widget _logoutButton() {
//     return ElevatedButton(
//       onPressed: signOut,
//       style: ButtonStyle(
//         backgroundColor: MaterialStateProperty.all(
//           const Color(0xff0165E1),
//         ),
//       ),
//       child: const Text('로그아웃'),
//     );
//   }
// }
//
//
