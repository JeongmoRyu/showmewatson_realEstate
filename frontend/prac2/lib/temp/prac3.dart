import 'package:flutter/material.dart';
import 'package:prac2/responsive/breakpoint.dart';
import 'package:prac2/responsive/responsive_center.dart';
import 'package:prac2/signin.dart';

class prac3 extends StatelessWidget {
  const prac3({Key? key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('enter responsive'),
      ),
      body: SingleChildScrollView(
        child: ResponsiveCenter(
          child: const Signin(),
          maxContentWidth: BreakPoint.tablet,
          padding: EdgeInsets.all(16),
        ),
      ),
    );
  }
}