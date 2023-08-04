import 'package:flutter/material.dart';
import 'package:prac2/scroll_effect.dart';

class prac2 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primaryColor: Colors.brown[500]
      ),
      home: ScrollEffect(),
    );
  }
}
