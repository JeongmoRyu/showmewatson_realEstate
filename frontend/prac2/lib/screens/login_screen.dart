// import 'package:flutter/material.dart';
// import 'package:go_router/go_router.dart';
// import 'package:kakao_flutter_sdk/kakao_flutter_sdk.dart';
//
// // 로그인 화면
// class LoginScreen extends StatefulWidget {
//   @override
//   _KakaoLoginPageState createState() => _KakaoLoginPageState();
// }
//
// class _KakaoLoginPageState extends State<LoginScreen> {
//   Future<void> _loginButtonPressed() async {
//     try {
//       final authCode = await AuthCodeClient.instance.request();
//       print(authCode);
//     } catch (e) {
//       print("Error during login: $e");
//     }
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
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
//       // 소셜로그인 부분
//       body: Center(
//         child: InkWell(
//           onTap: _loginButtonPressed,
//           child: Image.asset('assets/images/kako_login.button.png'),
//         ),
//       ),
//     );
//   }
// }
//
