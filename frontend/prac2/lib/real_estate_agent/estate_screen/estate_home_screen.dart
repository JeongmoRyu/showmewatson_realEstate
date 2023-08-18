import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:carousel_slider/carousel_slider.dart';

import 'package:prac2/real_estate_agent/estate_basic/estate_appbar.dart';

import 'package:prac2/base/navbar/bottom_navigation_widget.dart';

// 중개인용 홈 화면
class EstateHomeScreen extends StatelessWidget {
  const EstateHomeScreen({super.key});

  // 이미지 리스트
  static const List<String> imageList = [
    'assets/images/estate_home_image_1.png',
    'assets/images/estate_home_image_2.png',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: EstateCustomAppBar(),


      body: Center(
        child: Column(
          // mainAxisAlignment: MainAxisAlignment.center, // 세로방향 정렬
          children: [
            SizedBox(height: 20), // 간격 추가

            CarouselSlider(
              options: CarouselOptions(
                height: 200, // 슬라이더 높이 조절
                autoPlay: true, // 자동 슬라이드
                enlargeCenterPage: true, // 현재 페이지 확대
                viewportFraction: 1.0, // 현재 화면에 표시될 페이지의 비율을 1로 설정
                aspectRatio: 16 / 9, // 이미지 비율
                autoPlayInterval: Duration(seconds: 7), // 자동 슬라이드 간격
              ),
              items: imageList.map((item) {
                return Builder(
                  builder: (BuildContext context) {
                    return Container(
                      width: MediaQuery.of(context).size.width,
                      margin: EdgeInsets.symmetric(horizontal: 5.0), // 여백 설정
                      decoration: BoxDecoration(
                        color: Colors.amber,
                        borderRadius: BorderRadius.circular(8.0),
                      ),
                      child: Image.asset(item, fit: BoxFit.fill),
                    );
                  },
                );
              }).toList(),
            ),

            SizedBox(height: 40), // 간격 추가

            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    context.go('/my_notice');
                  },
                  style: ButtonStyle(
                    minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
                    shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                      RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5),
                      ),// 모서리 처리의 지름 설정
                    ),
                  ),
                  child: Column(
                    children: [
                      Icon(FontAwesomeIcons.camera),

                      SizedBox(height: 4),

                      Text('Live 방송'),
                    ],
                  ),
                ),

                SizedBox(width: 20),

                ElevatedButton(
                  onPressed: () {
                    context.go('/upload_sale');
                  },
                  style: ButtonStyle(
                    minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
                    shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                      RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5),
                      ),// 모서리 처리의 지름 설정
                    ),
                  ),
                  child: Column(
                    children: [
                      Icon(FontAwesomeIcons.building),

                      SizedBox(height: 4),

                      Text('매물 등록'),
                    ],
                  ),
                ),

                SizedBox(width: 20),

                Row(
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        context.push('/estate_my_sale');
                      },
                      style: ButtonStyle(
                        minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
                        shape: MaterialStateProperty.all<RoundedRectangleBorder>(
                          RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(5),
                          ),// 모서리 처리의 지름 설정
                        ),
                      ),
                      child: Column(
                        children: [
                          Icon(FontAwesomeIcons.buildingUser),

                          SizedBox(height: 4),

                          Text('내 매물'),
                        ],
                      ),
                    ),
                  ],
                ),


              ],
            ),


          ],
        ),
      ),


    );
  }
}
