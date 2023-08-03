import 'package:flutter/material.dart';
import 'package:contained_tab_bar_view/contained_tab_bar_view.dart';

class prac4 extends StatelessWidget {
  const prac4({Key? key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('보여줘왓슨'),
      ),
      body: Container(
        padding: const EdgeInsets.all(8.0),
        color: Colors.blue,
        child: Expanded(
          child: ContainedTabBarView(
            tabs: [
              Text('매매'),
              Text('월세'),
              Text('전세'),
            ],
            views: [
              Container(color: Colors.brown[500]),
              Container(color: Colors.brown[400]),
              Container(color: Colors.brown[300]),
            ],
            onChange: (index) => print(index),
          ),
        ),
      ),
    );
  }
}