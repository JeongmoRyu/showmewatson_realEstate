import 'package:flutter/material.dart';

class prac12 extends StatelessWidget {
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
    // true인 값들의 인덱스를 가져옵니다.
    List<int> trueIndices = List.generate(houseOption.length, (index) => index)
        .where((index) => houseOption[index])
        .toList();

    return Scaffold(
      appBar: AppBar(
        title: Text('박스 컬럼 리스트'),
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 4,
          crossAxisSpacing: 10.0,
          mainAxisSpacing: 10.0,
        ),
        itemCount: trueIndices.length,
        itemBuilder: (context, index) {
          int trueIndex = trueIndices[index];
          return _buildBoxColumn(trueIndex);
        },
      ),
    );
  }

  Widget _buildBoxColumn(int index) {
    bool option = houseOption[index];
    Color boxColor = option ? Colors.white : Colors.red;
    String text = optionTextList[index];
    IconData icon = option ? optionIconList[index] : Icons.clear;

    return Container(
      padding: EdgeInsets.all(10),
      decoration: BoxDecoration(
        color: boxColor,
        borderRadius: BorderRadius.circular(10),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(
            icon,
            size: 30,
            color: Colors.black,
          ),
          SizedBox(height: 10),
          Text(
            text,
            style: TextStyle(
              fontSize: 14,
              color: Colors.black,
            ),
          ),
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: prac12(),
  ));
}