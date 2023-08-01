import 'package:flutter/material.dart';

class prac1 extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('prac1'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/a');
              },
              child: Text('Go to prac2'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[700],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/b');
              },
              child: Text('Go to prac3'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[600],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/c');
              },
              child: Text('Go to prac4'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[500],
              ),
            ),
              ElevatedButton(
                onPressed: (){
                  Navigator.pushNamed(context, '/e');
                },
                child: Text('Go to prac6'),
                style: ElevatedButton.styleFrom(
                  primary: Colors.brown[400],
                ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/f');
              },
              child: Text('Go to prac7'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[300],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/g');
              },
              child: Text('Go to prac8'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[200],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/h');
              },
              child: Text('Go to prac9'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[100],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/i');
              },
              child: Text('Go to prac10'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[200],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/j');
              },
              child: Text('Go to prac11'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[300],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/k');
              },
              child: Text('Go to prac12'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[400],
              ),
            ),
            ElevatedButton(
              onPressed: (){
                Navigator.pushNamed(context, '/l');
              },
              child: Text('Go to prac13'),
              style: ElevatedButton.styleFrom(
                primary: Colors.brown[500],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
