import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:go_router/go_router.dart';
import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart' as kakao;
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

import 'package:prac2/login/login_platform.dart';
import 'package:prac2/states/user_auth_provider.dart';

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  LoginPlatform _loginPlatform = LoginPlatform.none;

  String id = "";

  void signInWithKakao() async {
    try {
      bool isInstalled = await kakao.isKakaoTalkInstalled();
      kakao.OAuthToken token = isInstalled
          ? await kakao.UserApi.instance.loginWithKakaoTalk()
          : await kakao.UserApi.instance.loginWithKakaoAccount();

      kakao.User user = await kakao.UserApi.instance.me();

      Navigator.pop(context);

      if(token != null) {
        print('사용자 정보 요청 성공'
            '\n회원번호: ${user.id}');
        id = "kakao_" + (user.id).toString();

        Future<String> role = _fetchData();

        if (role != null) {
          setState(() {
            _loginPlatform = LoginPlatform.kakao;
          });

          final UserAuthProvider userAuthProvider = Provider.of<UserAuthProvider>(context, listen: false);
          userAuthProvider.login(LoginPlatform.kakao);

          if (role == 'user') {
            // 사용자 페이지로 연결
            context.go('/home');
          } else {
            // 중개사 페이지로 연결
            context.go('/estate_home');
          }
        }

      } else {
        // 회원가입(중개사/사용자) 선택 페이지로 이동
        context.go('/login_selection');
      }

      print('카카오톡으로 로그인 성공!');
    } catch (error) {
      print('카카오톡으로 로그인 실패 $error');
    }
  }

  void signOut() async {
    switch (_loginPlatform) {
      case LoginPlatform.kakao:
        break;
      case LoginPlatform.none:
        break;
    }

    // 로그아웃 시에 토큰 제거
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove('access_token');

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

  Future<String> _fetchData() async {
    print("axios get");
    final String loginUrl = "http://I9A803.p.ssafy.io:8080/auth/admin/check-regist/${id}";
    final loginResponse = await http.get(Uri.parse(loginUrl));
    print("body : " + loginResponse.body);

    if (loginResponse.statusCode == 200) {
      print("statusCode 200");
      final data = jsonDecode(utf8.decode(loginResponse.bodyBytes));

      print(data);
      print("accessToken : " + data['accessToken']);
      print("role : " + data['role']);

      print("accesstoken을 디바이스에 저장합니다.");
      const String _tokenKey = 'access_token';
      final token = data['accessToken'];
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString(_tokenKey, token);

      return data['role'];
    } else {
      print(loginResponse.statusCode);
      return "";
    }
  }

}