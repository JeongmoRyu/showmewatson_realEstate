import 'package:flutter/material.dart';

class prac10 extends StatelessWidget {
  final List<bool> houseOption = [
    true, true, true, true, true, false, true, false, false, true, false
  ];

  final List<String> optionTextList = [
    '싱크대', '에어컨', '신발장', '세탁기', '냉장고', '옷장',
    '인덕션', '책상', '엘리베이터', '냉난방', '주차가능',
  ];
  final List<IconData> optionIconList = [
    Icons.wash, Icons.ac_unit, Icons.curtains_closed_outlined, Icons.local_laundry_service,
    Icons.kitchen, Icons.curtains_closed, Icons.local_fire_department, Icons.desktop_mac,
    Icons.elevator, Icons.hot_tub, Icons.local_parking,
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('박스 컬럼 리스트'),
      ),
      body: ListView.builder(
        itemCount: houseOption.length,
        itemBuilder: (context, index) {
          return _buildBoxColumn(index);
        },
      ),
    );
  }

  Widget _buildBoxColumn(int index) {
    bool option = houseOption[index];
    Color boxColor = option ? Colors.blue : Colors.red;
    String text = optionTextList[index];
    IconData icon = option ? optionIconList[index] : Icons.clear;

    return Container(
      margin: EdgeInsets.all(10),
      padding: EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: boxColor,
        borderRadius: BorderRadius.circular(10),
      ),
      child: Column(
        children: [
          Icon(
            icon,
            size: 30,
            color: Colors.white,
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
    home: prac10(),
  ));
}