import 'package:flutter/material.dart';

class ChatBubble extends StatelessWidget {
  const ChatBubble(this.message, this.isMe, {Key? key}) : super(key: key);
  // const ChatBubble(this.message, this.userName, this.isMe, {Key? key}) : super(key: key);

  final String message;
  // final String userName;
  final bool isMe;

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: isMe ? MainAxisAlignment.end : MainAxisAlignment.start,
      children: [
        Container(
          decoration: BoxDecoration(
            color: isMe? Colors.blue[500] : Colors.grey[300],
            borderRadius: BorderRadius.only(
              topRight: Radius.circular(15),
              topLeft: Radius.circular(15),
              bottomRight: isMe ? Radius.circular(0) : Radius.circular(15),
              bottomLeft: isMe ? Radius.circular(15) : Radius.circular(0),
            ),

          ),
          width: 150,
          padding: EdgeInsets.symmetric(vertical: 10, horizontal: 15),
          margin: EdgeInsets.symmetric(vertical: 5, horizontal: 8),
          child: Column(
            crossAxisAlignment: isMe ? CrossAxisAlignment.end : CrossAxisAlignment.start,
            children: [
              // Text(
              //   userName,
              //   style: TextStyle(
              //       color: isMe ? Colors.white : Colors.black
              //   ),
              // ),
              Text(
                message,
                style: TextStyle(
                    color: isMe ? Colors.white : Colors.black
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
