import 'package:flutter/material.dart';
import 'package:carousel_slider/carousel_slider.dart';

class Detail extends StatelessWidget {
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
    "room": 1,
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

  Detail() {
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
          title: Text('DetailPage'),
        //  가능하다면 매물 번호를 받고 싶습니다!!!!!!!!!!!!!
        ),
        body: SingleChildScrollView(
          child: Column(
              children: [
                CarouselSlider(
                  options: CarouselOptions(
                    enableInfiniteScroll: false,
                    aspectRatio: 16 / 9,
                    enlargeCenterPage: true,
                    viewportFraction: 1.0,
                  ),
                  items: [
                    Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage('assets/img_1.png'),
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                    Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage('assets/img_0.png'),
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                    Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: AssetImage('assets/img_2.png'),
                          fit: BoxFit.cover,
                        ),
                      ),
                    ),
                  ],
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20),
                  child: Text(
                    houseList['address'].toString(),
                    style: TextStyle(
                      color: Colors.grey[500],
                      letterSpacing: 2.0,
                      fontSize: 13,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Row(
                        children: [
                          Text(
                            _getContractInfo(), // contractcode에 따라 다른 내용을 반환하는 함수 호출
                            style: TextStyle(
                              color: Colors.black,
                              fontSize: 20,
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          SizedBox(width: 10), // 두 Text 위젯 사이에 간격을 줄 수 있습니다.
                          Text(
                            houseList['contractInfo']['maintenance'] != null
                                ? '관리비 ' + houseList['contractInfo']['maintenance'].toString()
                                : '', // maintenance가 존재하면 해당 값을 출력, 그렇지 않으면 빈 문자열 출력
                            style: TextStyle(
                              color: Colors.grey[500],
                              fontSize: 13,
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      Row(
                        children: [
                          Icon(
                            Icons.account_balance_outlined,
                            size: 30,
                            color: Colors.grey[500],
                          ),
                          Text(
                            houseList['content'].toString(),
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),

                    ],
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 20),
                  child: Divider(
                    height: 20.0,
                    color: Colors.grey[850],
                    thickness: 1.0,
                    endIndent: 20.0,
                  ),
                ),
                Center(
                  child: Padding(
                    padding: EdgeInsets.only(bottom: 20,),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [
                            Icon(
                              Icons.apartment,
                              size: 50,
                              color: Colors.black,
                            ),
                            Text(
                              _getHouseInfo(), // contractcode에 따라 다른 내용을 반환하는 함수 호출
                              style: TextStyle(
                                color: Colors.grey[600],
                                letterSpacing: 2.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,

                          children: [
                            Icon(
                              Icons.house_outlined,
                              size: 50,
                              color: Colors.black,
                            ),
                            Text(
                              '방 : ' + houseList['room'].toString(),
                              style: TextStyle(
                                color: Colors.grey[600],
                                letterSpacing: 2.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [
                            Icon(
                              Icons.photo_size_select_large_rounded,
                              size: 50,
                              color: Colors.black,
                            ),
                            Text(
                              houseList['supplyAreaMeter'].toString() + 'm²',
                              style: TextStyle(
                                color: Colors.grey[600],
                                letterSpacing: 2.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                        Column(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [

                          Row(
                            children: [
                            Stack(
                              children: [
                                Positioned(
                                  top: 0,
                                  right: 20,
                                  child: Icon(
                                    Icons.height,
                                    size: 50,
                                    color: Colors.black,
                                  ),
                                ),
                                Icon(
                                  Icons.stay_current_portrait,
                                  size: 50,
                                  color: Colors.black,
                                ),
                              ],
                            ),
                            ],
                        ),
                            Text(
                              houseList['floor'].toString() + '층',
                              style: TextStyle(
                                color: Colors.grey[600],
                                letterSpacing: 2.0,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),

                      ],
                    ),
                  ),
                ),

                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child: Text(
                    '거래 정보',
                    style: TextStyle(
                      color: Colors.black,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 5,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '거래 방식',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            _getContractInfo(), // contractcode에 따라 다른 내용을 반환하는 함수 호출
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),

                ),
                Container(
                    alignment: Alignment.topLeft,
                    padding: EdgeInsets.only(left: 20, bottom: 20),
                    child: Row(
                      children: [
                        Expanded(
                          child: houseList['contractInfo']['maintenance'] != null
                              ? Container(
                            alignment: Alignment.topLeft,
                                child: Text(
                                  '관리비',
                                  style: TextStyle(
                                    color: Colors.grey[500],
                                    letterSpacing: 2.0,
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                              )
                              : SizedBox.shrink(),
                        ),
                        Expanded(
                          child: houseList['contractInfo']['maintenance'] != null
                              ? Column(
                                children: [
                                  Container(
                                    alignment: Alignment.centerLeft,
                                    child: Text(
                                      '관리비 ' + houseList['contractInfo']['maintenance'].toString(),
                                      style: TextStyle(
                                        color: Colors.grey[500],
                                        letterSpacing: 2.0,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                  SizedBox(height: 5), // 두 Text 위젯 사이에 간격을 줄 수 있습니다.

                                  Container(
                                    alignment: Alignment.centerLeft,
                                    child: Text(
                                      houseList['contractInfo']['maintenanceList'].toString(),
                                      style: TextStyle(
                                        color: Colors.grey[500],
                                        letterSpacing: 2.0,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                ],
                              )
                              : SizedBox.shrink(),

                        ),
                          ],
                        ),
                  ),

                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '사용승인일',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['approvalBuildingDate'].toString(),
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),

                ),
                //거래 정보

                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, top: 10,),
                  child: Text(
                    '방 정보',
                    style: TextStyle(
                      color: Colors.black,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 20),
                  child: Divider(
                    height: 20.0,
                    color: Colors.grey[850],
                    thickness: 1.0,
                    endIndent: 20.0,
                  ),
                ),

                // 방 정보

                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '건물유형',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                              _getHouseInfo(),
                              style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '사용용도',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['buildingUse'].toString(),
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '해당층/전체층',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['floor'].toString() + '층' + '/' + houseList['totalFloor'].toString() + '층',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '방갯수/욕실갯수',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['room'].toString() + '개' + '/' + houseList['bathroom'].toString() + '개',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '사용면적/공급면적',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['squareMeter'].toString() + 'm²' + '/' + houseList['supplyAreaMeter'].toString() + 'm²',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, bottom: 10,),
                  child : Row(
                    children: [
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            '위치',
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                      Expanded(
                        child: Container(
                          alignment: Alignment.centerLeft,
                          child: Text(
                            houseList['address'].toString(),
                            style: TextStyle(
                              color: Colors.grey[500],
                              letterSpacing: 2.0,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),

                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.only(left: 20, top: 10,),
                  child: Text(
                    '추가옵션',
                    style: TextStyle(
                      color: Colors.black,
                      letterSpacing: 2.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(left: 20),
                  child: Divider(
                    height: 20.0,
                    color: Colors.grey[850],
                    thickness: 1.0,
                    endIndent: 20.0,
                  ),
                ),
                GridView.builder(
                  shrinkWrap: true,
                  physics: NeverScrollableScrollPhysics(),
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
              ]
          ),
        )
    );
  }

  String _getContractInfo() {
    int contractCode = houseList['contractCode'];
    if (contractCode == 3) {
      return '매매 ' + houseList['contractInfo']['salePrice'].toString();
    } else if (contractCode == 2) {
      return '전세 ' + houseList['contractInfo']['deposit'].toString();
    } else {
      return '월세 ' + houseList['contractInfo']['deposit'].toString() +
          '/' + houseList['contractInfo']['monthlyRent'].toString();
    }
  }

  String _getHouseInfo() {
    int houseCode = houseList['houseCode'];
    if (houseCode == 1) {
      return '아파트';
    } else if (houseCode == 2) {
      return '빌라';
    } else {
      return '오피스텔월세';
    }
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
    home: Detail(),
  ));
}