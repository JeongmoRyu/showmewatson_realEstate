import 'package:flutter/material.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:go_router/go_router.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

import 'package:prac2/models/user.dart';
import 'package:prac2/login/fcm_token.dart';

class UserSignIn extends StatefulWidget {
  const UserSignIn({super.key});

  @override
  State<UserSignIn> createState() => _UserSignInState();
}

class _UserSignInState extends State<UserSignIn> {

  TextEditingController nickNameController = TextEditingController();

  Future<void> _submitData() async {
    String? FCMtoken = await FirebaseMessaging.instance.getToken();

    final String apiUrl = "http://I9A803.p.ssafy.io:8081/user";

    final data = {
      "nickname": "${nickNametextController.text}",
      "authId": ,
      "fcmToken": FCMtoken,
    };


    // POST 요청을 사용하여 데이터 추가
    final response = await http.post(
      Uri.parse(apiUrl),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTQxOTI4MDA0LCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA3LTMxVDA1OjUzOjM3WiJ9fV0sImF1dGhJZCI6Mjk0MTkyODAwNCwic3ViIjoiMjk0MTkyODAwNCIsImlhdCI6MTY5MjE5MTMzMSwiZXhwIjoxNjkzNTkzMTYzfQ.9RPeItqxnvA3X7-dQ6WaEzfYBiWFKuX4zDcUHdcWOPw",
      },
      body: jsonEncode(data),
    );

    if (response.statusCode == 201) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('데이터가 성공적으로 추가되었습니다!')),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('데이터 추가에 실패했습니다. 다시 시도해 주세요.')),
      );
    }

    context.go('/home');
  }

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
