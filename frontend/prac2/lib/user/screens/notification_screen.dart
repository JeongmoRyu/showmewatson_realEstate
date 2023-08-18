import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class NotificationScreen extends StatefulWidget {
  const NotificationScreen({super.key});

  @override
  State<NotificationScreen> createState() => _NotificationState();
}

class _NotificationState extends State<NotificationScreen> {
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
            width: 45, // 아이콘의 너비
            height: 45, // 아이콘의 높이
            fit: BoxFit.contain, // 이미지를 부모 위젯 크기에 맞게 조절
          ),
        ),
      ),
      body: Text('알림페이지창'),
    );
  }
}
