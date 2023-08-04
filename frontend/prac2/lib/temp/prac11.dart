import 'package:flutter/material.dart';

class prac11 extends StatelessWidget {
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

  List<String> getHouseOptions() {
    List<String> houseOptions = [];
    for (int i = 0; i < optionTextList.length; i++) {
      bool option = houseList['houseOption'][optionTextList[i]] ?? false;
      if (option) {
        houseOptions.add(optionTextList[i]);
      }
    }
    return houseOptions;
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
    List<String> houseOptions = getHouseOptions();

    return Scaffold(
      appBar: AppBar(
        title: Text('박스 컬럼 리스트'),
      ),
      body: ListView.builder(
        itemCount: houseOptions.length,
        itemBuilder: (context, index) {
          String option = houseOptions[index];
          Color boxColor = Colors.blue;
          IconData icon = optionIconList[optionTextList.indexOf(option)];

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
                  option,
                  style: TextStyle(
                    fontSize: 14,
                    color: Colors.white,
                  ),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: prac11(),
  ));
}
