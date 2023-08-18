import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:prac2/real_estate_agent/estate_basic/estate_appbar.dart';

import 'package:prac2/real_estate_agent/models/house.dart';
import 'package:prac2/real_estate_agent/models/detail.dart';

class MySale extends StatefulWidget {
  const MySale({super.key});

  @override
  State<MySale> createState() => _MySaleState();
}

class _MySaleState extends State<MySale> {
  List<House> houses = [];
  Map<int, Detail> details = {}; // houseId를 key로 사용하여 상세 정보 저장

  Future<void> fetchData() async {
    final String apiUrl = "http://I9A803.p.ssafy.io:8081/house";

    // 'Authorization' 헤더 추가 부분
    final headers = {
      "Content-Type": "application/json",
      "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTY3MDE4NjMxLCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA4LTE2VDAxOjQzOjUwWi",
    };

    final response = await http.get(Uri.parse(apiUrl), headers: headers);

    if (response.statusCode == 200) {
      String body1 = utf8.decode(response.bodyBytes); // UTF-8 디코딩 추가
      final houseDataList = json.decode(body1) as List;
      houses = houseDataList.map((data) => House.fromJson(data)).toList();

      // 여기서 병렬 요청을 시작합니다.
      final detailFutures = houses.map((house) async {
        final detailResponse = await http.get(
            Uri.parse('http://I9A803.p.ssafy.io:8081/house/${house.id}'), headers: headers);

        if (detailResponse.statusCode == 200) {
          String body2 = utf8.decode(detailResponse.bodyBytes); // UTF-8 디코딩 추가
          final detailData = json.decode(body2);
          details[house.id] = Detail.fromJson(detailData);
        } else {
          print("Error fetching detail for houseId: ${house.id}");
        }
      }).toList();

      await Future.wait(detailFutures); // 모든 상세 정보 요청이 완료될 때까지 기다립니다.

    } else {
      print("Error fetching notices");
    }
  }

  @override
  void initState() {
    super.initState();
    fetchData(); // 화면이 로드될 때 데이터 가져오기
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: EstateCustomAppBar(),
      body: FutureBuilder(
        future: fetchData(),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(
                child: CircularProgressIndicator()); // 데이터 로딩 중일 때 로딩 인디케이터 표시
          } else if (snapshot.hasError) {
            return Center(
                child: Text('데이터를 불러오는 중 오류가 발생했습니다.')); // 에러 발생 시 메시지 표시
          } else {
            return buildBody(context);
          }
        },
      ),
    );
  }

  Widget buildBody(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16.0),
      child: Stack(
        children: [
          const Positioned(
            top: 0,
            left: 0,
            right: 0,
            child: Column(
              children: [
                Center(
                  child: Padding(
                    padding: EdgeInsets.all(10.0),
                    child: Text(
                      '내가 올린 매물 리스트',
                      style: TextStyle(
                          fontSize: 25, fontWeight: FontWeight.bold),
                    ),
                  ),
                ),
                Divider(
                  color: Colors.black,
                  height: 1,
                  thickness: 2.0,
                ),
              ],
            ),
          ),
          Padding(
            padding: const EdgeInsets.only(top: 60.0),
            child: ListView.separated(
              padding: const EdgeInsets.all(0),
              itemCount: houses.length,
              itemBuilder: (BuildContext context, int index) {
                House currentHouse = houses[index];
                Detail? currentDetail = details[currentHouse.id];

                return Container(
                  height: 70,
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Expanded(
                        child: InkWell(
                          onTap: () {
                            context.go(
                                '/estate_detail/${currentHouse.id}');
                          },
                          child: Row(
                            children: [
                              if (currentDetail != null) ...[
                                Padding(
                                  padding: const EdgeInsets.only(
                                      left: 6.0, right: 6.0),
                                  child: Image.network(
                                    currentDetail.fileNames.first,
                                    width: 110,
                                    height: 120,
                                    fit: BoxFit.fill,
                                  ),
                                ),
                              ],

                              const SizedBox(width: 2.0),

                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                // mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  Text(
                                    '${currentDetail!.deposit} / ${currentDetail.monthlyRent}',
                                    style: TextStyle(
                                      fontSize: 14,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),

                                  SizedBox(height: 0.0),

                                  Text(
                                    "${currentHouse.sidoName} ${currentHouse.gunguName} ${currentHouse.dongleeName}",
                                    style: TextStyle(
                                      fontSize: 10,
                                      color: Colors.grey[700],
                                    ),
                                  ),

                                  SizedBox(height: 0.5),

                                  Text(
                                    "${currentHouse.squareMeter}m², ${currentHouse.floor}층",
                                    style: TextStyle(
                                      fontSize: 9,
                                      color: Colors.grey[700],
                                    ),
                                  ),

                                  SizedBox(height: 0.5),

                                  Text(
                                    "${currentHouse.title}",
                                    style: TextStyle(
                                      fontSize: 9,
                                      color: Colors.grey[700],
                                    ),
                                  ),

                                ],
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                );
              },
              separatorBuilder: (BuildContext context,
                  int index) => const Divider(height: 10),
            ),
          ),
        ],
      ),
    );
  }
}
