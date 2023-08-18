import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:carousel_slider/carousel_slider.dart';

import 'package:prac2/base/appbar.dart';



// 홈화면 클래스 생성(인스턴스)

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  // 이미지 리스트
  static const List<String> imageList = [
    'assets/images/home_image_1.png',
    'assets/images/home_image_2.png',
  ];

  @override
  Widget build(BuildContext context) {
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


      // 슬라이더 추가
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
                    context.go('/livePage');
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
                    context.go('/map');
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
                      Icon(FontAwesomeIcons.mapLocationDot),

                      SizedBox(height: 4),

                      Text('매물 검색'),
                    ],
                  ),
                ),

                SizedBox(width: 20),

                Row(
                  children: [
                    ElevatedButton(
                      onPressed: () {
                        context.go('/all_notice');
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
                          Icon(FontAwesomeIcons.bell),

                          SizedBox(height: 4),

                          Text('방송 공지'),
                        ],
                      ),
                    ),
                  ],
                ),



              ],
            ),
            SizedBox(height: 20,),

            // Row(
            //   children: [
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/detailPage');
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.bell),
            //
            //           SizedBox(height: 4),
            //
            //           Text('매물 정보'),
            //         ],
            //       ),
            //     ),
            //     SizedBox(width: 20,),
            //
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/agentDetail');
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.personChalkboard),
            //
            //           SizedBox(height: 4),
            //
            //           Text('중개사 정보'),
            //         ],
            //       ),
            //     ),
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/filterPage1');
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.arrowDownWideShort),
            //           SizedBox(height: 14),
            //
            //           Text('필터1'),
            //         ],
            //       ),
            //     ),
            //   ],
            // ),
            // SizedBox(height: 20,),

            // Row(
            //   children: [
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/detailPageTwo');
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.bell),
            //
            //           SizedBox(height: 4),
            //
            //           Text('매물 정보2'),
            //         ],
            //       ),
            //     ),
            //     SizedBox(width: 20,),
            //
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/prac7');
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.handsPraying),
            //
            //           SizedBox(height: 4),
            //
            //           Text('Live'),
            //         ],
            //       ),
            //     ),
            //
            //     ElevatedButton(
            //       onPressed: () {
            //         context.push('/directMessageLogin');
            //
            //       },
            //       style: ButtonStyle(
            //         minimumSize: MaterialStateProperty.all<Size>(Size(90, 70)), // 이 버튼의 크기 설정
            //         shape: MaterialStateProperty.all<RoundedRectangleBorder>(
            //           RoundedRectangleBorder(
            //             borderRadius: BorderRadius.circular(5),
            //           ),// 모서리 처리의 지름 설정
            //         ),
            //       ),
            //       child: Column(
            //         children: [
            //           Icon(FontAwesomeIcons.solidMessage),
            //
            //           SizedBox(height: 4),
            //
            //           Text('DM'),
            //         ],
            //       ),
            //     ),
            //
            //
            //   ],
            // ),


          ],
        ),
      ),
    );
  }
}
