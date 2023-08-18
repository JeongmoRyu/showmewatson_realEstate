import 'package:flutter/material.dart';
import 'package:carousel_slider/carousel_slider.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/base/appbar.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';


class DetailTwo extends StatefulWidget {
  final int houseId;

  DetailTwo({this.houseId = 0});

  @override
  _DetailTwoState createState() => _DetailTwoState();
}
class _DetailTwoState extends State<DetailTwo> {
  Map<String, dynamic> houseList = {};
  Map<String, dynamic> houseOption = {};
  List<bool> houseOptionList = [];

  @override
  void initState() {
    super.initState();
    fetchData();  // 데이터를 가져오는 함수 호출
  }

  Future<void> fetchData() async {
    final String apiUrl = "http://I9A803.p.ssafy.io:8081/house/${widget.houseId}";

    // 'Authorization' 헤더 추가 부분
    final headers = {
      "Content-Type": "application/json",
      "Authorization": "",

    };

    final response = await http.get(Uri.parse(apiUrl), headers: headers);

    if (response.statusCode == 200) {
      // 데이터를 정상적으로 가져온 경우
      String body = utf8.decode(response.bodyBytes); // UTF-8 디코딩 추가
      final Map<String, dynamic> data = json.decode(body);

      setState(() {
        houseList = data;  // houseList 업데이트
        houseOption = houseList['houseOption'];
        houseOptionList = houseList['houseOption'].values.cast<bool>().toList();
        // houseList['houseOption'] = data;
      });
      print('houseList: $houseList');
    } else {
      // 200 코드가 아닐 경우
    }
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
    final Object? houseId;

    // true인 값들의 인덱스를 가져옵니다.
    List<int> trueIndices = List.generate(houseOptionList.length, (index) => index)
        .where((index) => houseOptionList[index])
        .toList();

    return Scaffold(
        appBar: CustomAppBar(
          onLeadingPressed: () {
            context.go('/');
          },
          showEstateHomeButton: true,
          onEstateHomePressed: () {
            context.go('/estate_home');
          },
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
                  items: (houseList['fileNames'] as List<dynamic>?)?.map<Widget>((fileName) {
                    return Container(
                      decoration: BoxDecoration(
                        image: DecorationImage(
                          image: NetworkImage(fileName),
                          fit: BoxFit.cover,
                        ),
                      ),
                    );
                  }).toList(),
                ),

                Row(
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Container(
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
                          padding: EdgeInsets.only(left: 20, bottom: 10,),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Row(
                                children: [
                                  Text(
                                    _getContractInfo(), // contractcode에 따라 다른 내용을 반환하는 함수 호출
                                    style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      letterSpacing: 2.0,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                  SizedBox(width: 10), // 두 Text 위젯 사이에 간격을 줄 수 있습니다.

                                ],
                              ),
                              Row(
                                children: [
                                  Text(
                                    houseList['maintenance'] != null
                                        ? '관리비 ' + houseList['maintenance'].toString() + '만원'
                                        : '', // maintenance가 존재하면 해당 값을 출력, 그렇지 않으면 빈 문자열 출력
                                    style: TextStyle(
                                      color: Colors.grey[500],
                                      fontSize: 13,
                                      letterSpacing: 2.0,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  )
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
                      ],
                    ),



                  ],
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
                        child: houseList['maintenance'] != null
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
                        child: houseList['maintenance'] != null
                            ? Column(
                          children: [
                            Container(
                              alignment: Alignment.centerLeft,
                              child: Text(
                                '관리비 ' + houseList['maintenance'].toString() + '만원',
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
                                houseList['maintenanceList'].toString(),
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
                  padding: EdgeInsets.only(left: 20, bottom: 10),
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

                // Text(
                //   '${houseList}',
                //   style: TextStyle(
                //     color: Colors.grey[500],
                //     letterSpacing: 2.0,
                //     fontWeight: FontWeight.bold,
                //   ),
                // ),
              ]
          ),
        )
    );
  }

  String _getContractInfo() {
    if (houseList['contractCode'] != null) {


      int contractCode = houseList['contractCode'];
      if (contractCode == 3) {
        if (houseList['salePrice'] != null) {
          if (houseList['salePrice'] > 10000) {
            return '매매 ' + (houseList['salePrice'] / 10000).truncate().toString() + '억원';
          }
          return '매매 ' + houseList['salePrice'].toString() + '만원';
        }
      } else if (contractCode == 2) {
        if (houseList['deposit'] != null) {
          if (houseList['deposit'] >= 10000) {
            return '전세 ' + (houseList['deposit'] / 10000).truncate().toString() + '억원';
          }
          return '전세 ' + houseList['deposit'].toString() + '만원';
        }
      } else {
        if (houseList['deposit'] != null) {
          if (houseList['deposit'] >= 10000) {
            return '월세 ' + (houseList['deposit'] / 10000).truncate().toString() + '억원'
                + '/' + houseList['monthlyRent'].toString() + '만원';
          }
          return '월세 ' + houseList['deposit'].toString() + '만원'
              + '/' + houseList['monthlyRent'].toString() + '만원';
        }
      }
      return '정보없음';
    }
    return '정보없음';
  }


  String _getHouseInfo() {
    if (houseList['houseCode'] != null) {

      int houseCode = houseList['houseCode'];
      if (houseCode == 1) {
        return '아파트';
      } else if (houseCode == 2) {
        return '빌라';
      } else {
        return '오피스텔월세';
      }
    }
    return '정보없음';
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