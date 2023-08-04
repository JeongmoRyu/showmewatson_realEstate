import 'package:flutter/material.dart';

class prac12 extends StatelessWidget {
  final Map<String, dynamic> houseList = {
    "contractCode": 1,
    "dongCode": 1141011600,
    "houseCode": 2,
    "squareMeter": 75.2,
    "floor": 3,
    "totalFloor": 4,
    "address": "서울시 역삼",
    "title": "고시원 원룸",
    "content": "월세 300000",
    "supplyAreaMeter": 80.0,
    "buildingUse": "주거용",
    "approvalBuildingDate": "2022-01-15",
    "bathroom": 2,
    "contractInfo": {
      "deposit": 300000,
      "monthlyRent": 300000,
      "maintenance": 90000,
      "maintenanceList": "에어컨 다있음"
    },
    "houseOption" : {
      "sink": true,
      "airConditioner": false,
      "shoeCloset": true,
      "washingMachine": true,
      "refrigerator": false,
      "closet": true,
      "induction": false,
      "desk": true,
      "elevator": true,
      "coldHeating": false,
      "parking": true
    },
    "houseFiles" : null
  };

  Map<String, dynamic> houseOption = {};

  List<bool> houseOptionList = [];

  prac12() {
    houseOption = houseList['houseOption'];
    houseOptionList = houseOption.values.cast<bool>().toList();
  }


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
    List<int> trueIndices = List.generate(houseOptionList.length, (index) => index)
        .where((index) => houseOptionList[index])
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
    bool option = houseOptionList[index];
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