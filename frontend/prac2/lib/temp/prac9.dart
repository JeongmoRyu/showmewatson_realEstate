import 'package:flutter/material.dart';

class prac9 extends StatelessWidget {
  final List<bool> optionList = [
    true, true, true, true, true, false, true, false, false, true, false
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('박스 컬럼 리스트'),
      ),
      body: ListView.builder(
        itemCount: optionList.length,
        itemBuilder: (context, index) {
          return _buildBoxColumn(index);
        },
      ),
    );
  }

  Widget _buildBoxColumn(int index) {
    bool option = optionList[index];
    Color boxColor = option ? Colors.blue : Colors.red;
    String text = option ? '옵션은 참입니다' : '옵션은 거짓입니다';

    return Container(
      margin: EdgeInsets.all(10),
      padding: EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: boxColor,
        borderRadius: BorderRadius.circular(10),
      ),
      child: Column(
        children: [
          Text(
            '박스 ${index + 1}',
            style: TextStyle(
              fontSize: 18,
              color: Colors.white,
            ),
          ),
          SizedBox(height: 10),
          Text(
            text,
            style: TextStyle(
              fontSize: 14,
              color: Colors.white,
            ),
          ),
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: prac9(),
  ));
}