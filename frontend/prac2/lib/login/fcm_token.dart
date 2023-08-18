import 'package:firebase_messaging/firebase_messaging.dart';

final FirebaseMessaging _firebaseMessaging = FirebaseMessaging.instance;

void getFCMToken() async {
  String? FCMtoken = await _firebaseMessaging.getToken();
  print("FCM Token: $FCMtoken");
}