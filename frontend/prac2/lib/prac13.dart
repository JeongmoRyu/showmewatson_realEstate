// import 'package:flutter/material.dart';
// import 'package:carousel_slider/carousel_slider.dart';
//
// class prac13 extends StatelessWidget {
//   final Map<String, dynamic> houseList = {
//     "contractCode": 1,
//     "dongCode": 1141011600,
//     "houseCode": 2,
//     "squareMeter": 75.2,
//     "floor": 3,
//     "totalFloor": 4,
//     "address": "서울시 역삼",
//     "title": "고시원 원룸",
//     "content": "월세 300000",
//     "supplyAreaMeter": 80.0,
//     "buildingUse": "주거용",
//     "approvalBuildingDate": "2022-01-15",
//     "bathroom": 2,
//     "contractInfo": {
//       "deposit": 300000,
//       "monthlyRent": 300000,
//       "maintenance": 90000,
//       "maintenanceList": "에어컨 다있음"
//     },
//     "houseOption" : {
//       "sink": true,
//       "airConditioner": false,
//       "shoeCloset": true,
//       "washingMachine": true,
//       "refrigerator": false,
//       "closet": true,
//       "induction": false,
//       "desk": true,
//       "elevator": true,
//       "coldHeating": false,
//       "parking": true
//     },
//     "houseFiles" : null
//   };
//
//   // final List<bool> houseOption = [
//   //   true, true, true, true, true, false, true, false, false, true, false
//   // ];
//
//   final List<String> optionTextList = [
//     '싱크대', '에어컨', '신발장', '세탁기', '냉장고', '옷장',
//     '인덕션', '책상', '엘리베이터', '냉난방', '주차가능',
//   ];
//   final List<IconData> optionIconList = [
//     Icons.wash, Icons.ac_unit, Icons.curtains_closed_outlined, Icons.local_laundry_service,
//     Icons.kitchen, Icons.curtains_closed, Icons.local_fire_department, Icons.desktop_mac,
//     Icons.elevator, Icons.hot_tub, Icons.local_parking,
//   ];
//
//   @override
//   Widget build(BuildContext context) {
//     List<int> trueIndices = List.generate(optionTextList.length, (index) => index)
//         .where((index) => houseList['houseOption'][optionTextList[index]])
//         .toList();
//
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('박스 컬럼 리스트'),
//       ),
//       body: Column(
//         children: [
//           CarouselSlider(
//             options: CarouselOptions(
//             enableInfiniteScroll: false, // 무한 스크롤 비활성화
//             aspectRatio: 16 / 9,
//             enlargeCenterPage: true,
//             viewportFraction: 1.0,
//           ),
//           items: [
//             Container(
//               decoration: BoxDecoration(
//                 image: DecorationImage(
//                   image: AssetImage('assets/img-11.png'),
//                   fit: BoxFit.cover,
//                 ),
//               ),
//             ),
//             Container(
//               decoration: BoxDecoration(
//                 image: DecorationImage(
//                   image: AssetImage('assets/img_0.png'),
//                   fit: BoxFit.cover,
//                 ),
//               ),
//             ),
//             Container(
//               decoration: BoxDecoration(
//                 image: DecorationImage(
//                   image: AssetImage('assets/img_2.png'),
//                   fit: BoxFit.cover,
//                 ),
//               ),
//             ),
//           ],
//         ),
//
//
//           // 매물 정보
//
//           Container(
//             alignment: Alignment.centerLeft,
//             padding: EdgeInsets.only(left: 20),
//             child: Text(
//               '매물정보',
//               style: TextStyle(
//                 color: Colors.black,
//                 letterSpacing: 2.0,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//           ),
//           Container(
//             alignment: Alignment.centerLeft,
//             padding: EdgeInsets.only(left: 20),
//             child: Text(
//               houseList['houseOption']['sink'].toString(),
//               style: TextStyle(
//                 color: Colors.black,
//                 letterSpacing: 2.0,
//                 fontWeight: FontWeight.bold,
//               ),
//             ),
//           ),
//
//           Divider(
//             height: 20.0,
//             color:Colors.grey[850],
//             thickness: 1.0,
//             //  굵기
//             endIndent: 30.0,
//             //  끝부분 띄우기 정도
//           ),
//
//
//
//           // 추가옵션(houseOption)
//           Container(
//             alignment: Alignment.centerLeft,
//             padding: EdgeInsets.only(left: 20),
//             child: Text(
//               '추가옵션',
//               style: TextStyle(
//               color: Colors.black,
//               letterSpacing: 2.0,
//               fontWeight: FontWeight.bold,
//               ),
//             ),
//           ),
//           Divider(
//             height: 20.0,
//             color:Colors.grey[850],
//             thickness: 1.0,
//             //  굵기
//             endIndent: 30.0,
//             //  끝부분 띄우기 정도
//           ),
//
//           Expanded(
//             child: GridView.builder(
//               gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
//                 crossAxisCount: 4,
//                 crossAxisSpacing: 10.0,
//                 mainAxisSpacing: 10.0,
//               ),
//               itemCount: trueIndices.length,
//               itemBuilder: (context, index) {
//                 int trueIndex = trueIndices[index];
//                 return _buildBoxColumn(trueIndex);
//               },
//             ),
//           ),
//         ],
//       ),
//     );
//   }
//
//   Widget _buildBoxColumn(int index) {
//     int trueIndex = trueIndices[index];
//     bool option = houseList['houseOption'][optionTextList[trueIndex]];
//     Color boxColor = option ? Colors.white : Colors.red;
//     String text = optionTextList[trueIndex];
//     IconData icon = option ? optionIconList[trueIndex] : Icons.clear;
//
//     return Container(
//       padding: EdgeInsets.all(10),
//       decoration: BoxDecoration(
//         color: boxColor,
//         borderRadius: BorderRadius.circular(10),
//       ),
//       child: Column(
//         mainAxisAlignment: MainAxisAlignment.center,
//         children: [
//           Icon(
//             icon,
//             size: 30,
//             color: Colors.black,
//           ),
//           SizedBox(height: 10),
//           Text(
//             text,
//             style: TextStyle(
//               fontSize: 14,
//               color: Colors.black,
//             ),
//           ),
//         ],
//       ),
//     );
//   }
// }
//
// void main() {
//   runApp(MaterialApp(
//     home: prac13(),
//   ));
// }