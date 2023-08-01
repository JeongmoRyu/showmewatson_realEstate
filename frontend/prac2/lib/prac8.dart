import 'package:flutter/material.dart';
import 'package:flutter_phone_direct_caller/flutter_phone_direct_caller.dart';

class prac8 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('prac8'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () async {
            const number = '01000000000';
            bool? res = await FlutterPhoneDirectCaller.callNumber(number);
            if (res != null && res) {
              // 전화가 성공적으로 걸렸을 때 처리할 로직 추가
            } else {
              // 전화가 실패했을 때 처리할 로직 추가
            }
          },
          child: Icon(Icons.phone),
        ),
      ),
    );
  }
}