import 'package:flutter/material.dart';

// 홈화면 클래스 생성(인스턴스)

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFFDCBF97),
      body: Text('홈페이지'),
    );
  }
}
