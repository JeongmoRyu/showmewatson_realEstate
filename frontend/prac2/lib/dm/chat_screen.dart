import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:prac2/dm/chat/message.dart';
import 'package:prac2/dm/chat/new_message.dart';

import 'package:prac2/base/appbar.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';


class ChatScreen extends StatefulWidget {
  const ChatScreen({Key? key}) : super(key: key);

  @override
  _ChatScreenState createState() => _ChatScreenState();
}

class _ChatScreenState extends State<ChatScreen> {
  final _authentication = FirebaseAuth.instance;
  User? loggedUser;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getCurrentUser();
  }

  void getCurrentUser(){
    try {
      final user = _authentication.currentUser;
      if (user != null) {
        loggedUser = user;
        print(loggedUser!.email);
      }
    }catch(e){
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        onLeadingPressed: () {
          context.go('/');
        },
        showEstateHomeButton: true,
        onEstateHomePressed: () {
          context.go('/estate_home');
        },
      ),
      body: Container(
          child: Column(
            children: [
              Expanded(
                  child: Messages()
              ),
              NewMessage(),
            ],
          )
      ),
    );
  }
}

