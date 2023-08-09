import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart' as firebase;
import 'package:google_sign_in/google_sign_in.dart';
import 'package:go_router/go_router.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart' as kakao;
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
        final credential = firebase.GoogleAuthProvider.credential(
          accessToken: googleAuth.accessToken,
          idToken: googleAuth.idToken,
        );
        await firebase.FirebaseAuth.instance.signInWithCredential(credential);

        context.go('/home'); // 홈 화면으로 이동

        setState(() {
          _loginPlatform = LoginPlatform.google;
        });

        final UserAuthProvider userAuthProvider = Provider.of<UserAuthProvider>(context, listen: false);
        userAuthProvider.login(LoginPlatform.google);

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
      bool isInstalled = await kakao.isKakaoTalkInstalled();
      kakao.OAuthToken token = isInstalled
          ? await kakao.UserApi.instance.loginWithKakaoTalk()
          : await kakao.UserApi.instance.loginWithKakaoAccount();

      kakao.User user = await kakao.UserApi.instance.me();

      context.go('/home'); // 홈 화면으로 이동

      setState(() {
        _loginPlatform = LoginPlatform.kakao;
      });

      final UserAuthProvider userAuthProvider = Provider.of<UserAuthProvider>(context, listen: false);
      userAuthProvider.login(LoginPlatform.kakao);

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