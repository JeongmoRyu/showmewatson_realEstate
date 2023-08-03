import 'package:flutter/widgets.dart';

class UserProvider extends ChangeNotifier{
  // 유저 로그인 여부 프라이빗 변수 선언
  bool _userLoggedIn = false;

  // 프라이빗 변수값 접근 및 변경함수
  // 외부에서의 접근을 막기 위해
  void SetUserAuth(bool authState) {
    _userLoggedIn = authState;
    notifyListeners();
  }

  // 변경함수에 연결될 외부변수 선언
  bool get userState => _userLoggedIn;
}