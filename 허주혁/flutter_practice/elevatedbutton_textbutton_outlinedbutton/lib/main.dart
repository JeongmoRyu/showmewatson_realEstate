import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.brown
      ),
      home: MyPage(),
    );
  }
}

class MyPage extends StatelessWidget {
  const MyPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Buttons'),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextButton(
              onPressed: () {
                print('text button');
              },
              onLongPress: () {
                print('text button');
              },
              child: Text(
                'Text button',
                style: TextStyle(
                  fontSize: 20.0
                ),
              ),
              style: TextButton.styleFrom(
                foregroundColor: Colors.red,
                backgroundColor: Colors.grey[300],
              ),
            ),
            ElevatedButton(
              onPressed: () {
                print('Elevated button');
              },
              child: Text('Elevated button'),
              style: ElevatedButton.styleFrom(
                foregroundColor: Colors.orangeAccent,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10.0)
                ),
                elevation: 0.0,
              ),
            ),

          ],
        ),
      ),
    );
  }
}
