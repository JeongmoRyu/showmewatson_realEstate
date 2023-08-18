import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'dart:convert';
import 'package:http/http.dart' as http;

import 'package:prac2/real_estate_agent/estate_basic/estate_appbar.dart';

import 'package:prac2/real_estate_agent/models/notice.dart';
import 'package:prac2/real_estate_agent/models/detail.dart';

class MyNotice extends StatefulWidget {
  const MyNotice({super.key});

  @override
  State<MyNotice> createState() => _MyNoticeState();
}

class _MyNoticeState extends State<MyNotice> {
  List<Notice> notices = [];
  Map<int, Detail> details = {}; // houseId를 key로 사용하여 상세 정보 저장

  Future<void> fetchData() async {

    // 'Authorization' 헤더 추가 부분
    final headers = {
      "Content-Type": "application/json",
      "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTY3MDE4NjMxLCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA4LTE2VDAxOjQzOjUwWi",
    };

    final response = await http.get(
        Uri.parse('http://I9A803.p.ssafy.io:8081/schedule'), headers: headers);

    if (response.statusCode == 200) {
      String body1 = utf8.decode(response.bodyBytes); // UTF-8 디코딩 추가
      final noticeDataList = json.decode(body1) as List;
      notices = noticeDataList.map((data) => Notice.fromJson(data)).toList();

      // 여기서 병렬 요청을 시작합니다.
      final detailFutures = notices.map((notice) async {
        final detailResponse = await http.get(
            Uri.parse('http://I9A803.p.ssafy.io:8081/house/${notice.houseId}'), headers: headers);

        if (detailResponse.statusCode == 200) {
          String body2 = utf8.decode(detailResponse.bodyBytes); // UTF-8 디코딩 추가
          final detailData = json.decode(body2);
          details[notice.houseId] = Detail.fromJson(detailData);
        } else {
          print("Error fetching detail for houseId: ${notice.houseId}");
        }
      }).toList();

      await Future.wait(detailFutures); // 모든 상세 정보 요청이 완료될 때까지 기다립니다.

    } else {
      print("Error fetching notices");
    }

    // // 병렬 요청 없이 처리

    //   for (var notice in notices) {
    //     final detailResponse = await http.get(Uri.parse('http://I9A803.p.ssafy.io:8081/house/${notice.houseId}'));
    //
    //     if (detailResponse.statusCode == 200) {
    //       String body2 = utf8.decode(detailResponse.bodyBytes);  // UTF-8 디코딩 추가
    //       final detailData = json.decode(body2);
    //       details[notice.houseId] = Detail.fromJson(detailData);
    //     } else {
    //       print("Error fetching detail for houseId: ${notice.houseId}");
    //       continue;
    //     }
    //   }
    // } else {
    //   print("Error fetching notices");
    // }
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
                      '방송 공지글',
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
              itemCount: notices.length,
              itemBuilder: (BuildContext context, int index) {
                Notice currentNotice = notices[index];
                Detail? currentDetail = details[currentNotice.houseId];
                String formattedLiveDate = formatLiveDate(
                    currentNotice.liveDate);

                return Container(
                  height: 70,
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Expanded(
                        child: InkWell(
                          onTap: () {
                            context.push(
                                '/estate_notice_write/${currentNotice.houseId}');
                          },
                          child: Row(
                            children: [
                              if (currentDetail != null) ...[
                                Padding(
                                  padding: const EdgeInsets.only(
                                      left: 6.0, right: 6.0),
                                  child: Image.network(
                                    currentDetail.fileNames.first,
                                    width: 70,
                                    height: 70,
                                    fit: BoxFit.contain,
                                  ),
                                ),
                              ],
                              const SizedBox(width: 2.0),
                              Expanded(
                                child: Text(
                                  '${currentDetail?.address} ${currentDetail
                                      ?.deposit}/${currentDetail
                                      ?.monthlyRent} 매물번호 ${currentDetail
                                      ?.houseCode} ${formattedLiveDate}에 Live 할게요.',
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                      IconButton(
                        onPressed: () => context.push('/livePage'),
                        icon: Icon(FontAwesomeIcons.camera),
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
