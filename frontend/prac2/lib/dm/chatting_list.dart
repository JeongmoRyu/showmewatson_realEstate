import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:prac2/dm/chat_screen.dart';
import 'package:provider/provider.dart';
import 'package:prac2/base/appbar.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';


class ChattingList extends StatefulWidget {
  const ChattingList({super.key});

  @override
  State<ChattingList> createState() => _ChattingListState();
}

class _ChattingListState extends State<ChattingList> {
  final FirebaseAuth _authentication = FirebaseAuth.instance;






  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Color(0xFFDCBF97),
          title: const Text('보여줘홈즈 채팅'),
            actions: [
              IconButton(
                icon: Icon(
                  Icons.exit_to_app_sharp,
                  color: Colors.white,
                ),
                onPressed: (){
                  _authentication.signOut();
                },
              )
            ],
        ),
          body: _buildUserList(),
    );
  }
}

Widget _buildUserList() {
  return StreamBuilder<QuerySnapshot<Map<String, dynamic>>>(
    stream: FirebaseFirestore.instance.collection('user').snapshots(),
    builder: (context, snapshot) {
      if (snapshot.hasError) {
        return const Text('error');
      }
      if (snapshot.connectionState == ConnectionState.waiting) {
        return const Text('loading...');
      }

      return ListView(
        children: snapshot.data!.docs
            .map<Widget>((doc) => _buildUserListItem(context, doc))
            .toList(),
      );
    },
  );
}

Widget _buildUserListItem(BuildContext context, DocumentSnapshot document) {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  Map<String, dynamic> data = document.data()! as Map<String, dynamic>;

  if (_auth.currentUser!.email != data['userName']) {
    return ListTile(
      title: Text(data['userName']),
      onTap: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => ChatScreen()),
        );
      },
    );
  } else {
    return const SizedBox.shrink();
  }
}
