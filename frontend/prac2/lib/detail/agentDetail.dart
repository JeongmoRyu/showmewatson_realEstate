import 'package:flutter/material.dart';
import 'package:carousel_slider/carousel_slider.dart';

class Agent extends StatelessWidget {
  final Map<String, dynamic> realtor_id = {
    'agency_id': '12345-6789-01234',
    'agency_name': '자차카',
    'address': '서울시 강남구 xxxxxxxxXXXXXXXxxxxxxxXXXXXxxxxxXXX',
    'tel': '02-xxxx-xxxx',
    'realtor_name': '마바사아 공인중개사무소',
    'phone_number': '010-0000-0000'
  };

  @override
  Widget build(BuildContext context) {
    // true인 값들의 인덱스를 가져옵니다.

    return Scaffold(
      appBar: AppBar(
        title: Text('Agent detail'),
        //  가능하다면 매물 번호를 받고 싶습니다!!!!!!!!!!!!!
      ),
      body: SingleChildScrollView(
        child: Column(children: [
          Container(
            alignment: Alignment.centerLeft,
            padding: EdgeInsets.only(left: 20, top: 20,),
            child: Text(
              realtor_id['realtor_name'].toString(),
              style: TextStyle(
                color: Colors.grey[800],
                letterSpacing: 2.0,
                fontSize: 20,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 20,
            ),
            child: Divider(
              height: 20.0,
              color: Colors.grey[850],
              thickness: 1.0,
              endIndent: 20.0,
            ),
          ),
        Row(
          children: [
            Expanded(
              flex: 2,
              child: Padding(
                padding: EdgeInsets.only(left: 20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      realtor_id['agency_name'].toString(),
                      style: TextStyle(
                        color: Colors.grey[800],
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      realtor_id['address'].toString(),
                      style: TextStyle(
                        color: Colors.grey[500],
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      realtor_id['phone_number'].toString(),
                      style: TextStyle(
                        color: Colors.grey[500],
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      realtor_id['tel'].toString(),
                      style: TextStyle(
                        color: Colors.grey[500],
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      '매물 등록 : ' + '202' + '건',
                      style: TextStyle(
                        color: Colors.black,
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      '중개사 등록번호 : ' + realtor_id['agency_id'].toString(),
                      style: TextStyle(
                        color: Colors.grey[500],
                        letterSpacing: 2.0,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              flex: 1,
              child: CircleAvatar(
                radius: 60,
                backgroundImage: AssetImage('assets/images/img_7.png'),
                backgroundColor: Colors.white,
              ),
            ),
          ],
        ),

          Padding(
            padding: EdgeInsets.only(
              left: 20,
              bottom: 20,
            ),
            child: Divider(
              height: 20.0,
              color: Colors.grey[850],
              thickness: 1.0,
              endIndent: 20.0,
            ),
          ),
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
                    image: AssetImage('assets/images/img_1.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage('assets/images/img_0.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage('assets/images/img_2.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 20,
              bottom: 20,
            ),
            child: Divider(
              height: 20.0,
              color: Colors.grey[850],
              thickness: 1.0,
              endIndent: 20.0,
            ),
          ),
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
                    image: AssetImage('assets/images/img_1.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage('assets/images/img_0.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
              Container(
                decoration: BoxDecoration(
                  image: DecorationImage(
                    image: AssetImage('assets/images/img_2.png'),
                    fit: BoxFit.cover,
                  ),
                ),
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 20,
              top: 10,
              bottom: 20,
            ),
            child: Divider(
              height: 20.0,
              color: Colors.grey[850],
              thickness: 1.0,
              endIndent: 20.0,
            ),
          ),
          ClipRRect(
            borderRadius: BorderRadius.circular(10),
            child: Image.asset(
              'assets/images/img_5.png',
              width: 300,
              height: 400,
              fit: BoxFit.cover,
            ),
          ),
        ],
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Agent(),
  ));
}
