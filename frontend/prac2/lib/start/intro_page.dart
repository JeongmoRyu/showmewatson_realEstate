import 'package:flutter/material.dart';
import 'package:prac2/utils/logger.dart';
import 'package:provider/provider.dart';

import 'package:prac2/states/user_provider.dart';
// 인트로 화면
class IntroPage extends StatelessWidget {
  const IntroPage({super.key});

  @override
  Widget build(BuildContext context) {
    logger.d('current user state: ${context.read<UserProvider>().userState}');
    return Scaffold(
      backgroundColor: Color(0xFFDCBF97),
      body: Text('인트로창'),
    );
  }
}
