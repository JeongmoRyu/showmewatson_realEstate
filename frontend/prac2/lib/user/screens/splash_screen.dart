import 'package:flutter/material.dart';
import 'package:extended_image/extended_image.dart';

// 스플래쉬스크린 클래스 생성
class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xFFDCBF97),
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ExtendedImage.asset(
                'assets/images/splash_logo.png',
              width: 300,
              height: 300,
              // fit: BoxFit.cover,
            )
          ],
        ),
      ),
    );
  }
}
