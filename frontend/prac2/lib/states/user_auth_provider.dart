import 'package:flutter/widgets.dart';
import 'package:prac2/login/login_platform.dart';


class UserAuthProvider extends ChangeNotifier{
  LoginPlatform _loginPlatform = LoginPlatform.none; // 기본 로그인 상태 : 비로그인

  LoginPlatform get loginPlatform => _loginPlatform;

  // 유저 로그인 여부 프라이빗 변수 선언
  void login(LoginPlatform platform) {
    _loginPlatform = platform;
    notifyListeners();
  }

  void logout() {
    _loginPlatform = LoginPlatform.none;
    notifyListeners();
  }
}