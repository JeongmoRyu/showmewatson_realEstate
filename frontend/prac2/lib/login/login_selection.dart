import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/login/fcm_token.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';


class LoginSelection extends StatefulWidget {
  const LoginSelection({super.key});

  @override
  State<LoginSelection> createState() => _LoginSelectionState();
}

class _LoginSelectionState extends State<LoginSelection> {
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
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () {
                context.go('/signin_user');
                getFCMToken();
              },
              child: Text('일반 사용자'),
            ),

            SizedBox(height: 20.0),

            ElevatedButton(
              onPressed: () {
                context.go('/signin_realtor');
              },
              child: Text('공인중개사'),
            ),
          ],
        ),
      )
    );
  }
}
