import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:prac2/base/appbar.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:geocoding/geocoding.dart';
import 'dart:ui' as ui;
import 'dart:typed_data';
import 'package:bottom_drawer/bottom_drawer.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:prac2/detail/detailPageTwo.dart';



class MapScreenCity extends StatefulWidget {
  const MapScreenCity({Key? key}) : super(key: key);

  @override
  _MapScreenCityState createState() => _MapScreenCityState();
}

class _MapScreenCityState extends State<MapScreenCity> {
  final LatLng _center = const LatLng(37.4966645, 127.0629804);
  bool isDrawerOpen = false;
  Map<MarkerId, Marker> markers = {};
  Map<MarkerId, List<Map<String, dynamic>>> markerData = {};
  List<Map<String, dynamic>>? filteredData;
  List<Map<String, dynamic>> houseyeoksam = [];



  Future<List<Map<String, dynamic>>> fetchHouseData() async {
    String url = "http://I9A803.p.ssafy.io:8081/house";
    final headers = {
      "Content-Type": "application/json",
      "Authorization": "",
    };

    try {
      final response = await http.get(Uri.parse(url), headers: headers);

      if (response.statusCode == 200) {
        String body = utf8.decode(response.bodyBytes);
        final housedata = json.decode(body);
        print(housedata);
        return housedata.map<Map<String, dynamic>>((item) => Map<String, dynamic>.from(item)).toList();
      } else {
        print("Failed to fetch data: ${response.statusCode}");
        return [];
      }
    } catch (e) {
      print("Error: $e");
      return [];
    }
  }

  Future<List<Map<String, dynamic>>> fetchHouseYeoksam() async {
    List<Map<String, dynamic>> housedata = await fetchHouseData();

    for (var data in housedata) {
      if (data['dongleeName'] == '역삼동') {
        houseyeoksam.add(data);
      }
    }

    print(houseyeoksam);
    return houseyeoksam;
  }


final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  final double _markerZoom = 13.0;

  Marker? tappedMarker;

  final double _markerTapTolerance = 24.0; // 마커 탭 허용 오차값

  @override
  void initState() {
    super.initState();
    _addMarkers();
    fetchHouseData();
    fetchHouseYeoksam().then((value) {
      setState(() {
        houseyeoksam = value; // Set houseyeoksam data after fetching
      });
    });
  }

  Future<Uint8List> getBytesFromCanvas(
      String courtCode, int width, int height) async {
    final ui.PictureRecorder pictureRecorderNum = ui.PictureRecorder();
    final ui.PictureRecorder pictureRecorder = ui.PictureRecorder();

    final Canvas canvasNum = Canvas(pictureRecorderNum);
    final Canvas canvas = Canvas(pictureRecorder);

    final Paint paintNum = Paint()..color = Colors.orangeAccent;
    final Paint paint = Paint()..color = Colors.deepPurpleAccent;
    final double radiusNum = 40.0;
    final double radius = 100.0;

    // 법원 코드 원을 그리고 칠하기
    canvas.drawCircle(
      Offset(width / 2, height / 2),
      radius,
      paint,
    );

    TextPainter painter = TextPainter(textDirection: TextDirection.ltr);
    painter.text = TextSpan(
      text: courtCode,
      style: TextStyle(fontSize: width / 4, color: Colors.white),
    );
    painter.layout();
    painter.paint(
      canvas,
      Offset((width * 0.5) - painter.width * 0.5,
          (height * 0.5) - painter.height * 0.5),
    );

    // 법원 코드 위에 숫자 원을 그리고 칠하기
    canvas.drawCircle(
      Offset((width / 2) + radius - 40, (height / 2) - radius + 40),
      radiusNum,
      paintNum,
    );

    TextPainter painterNum = TextPainter(textDirection: TextDirection.ltr);
    painterNum.text = TextSpan(
      text: '5',
      style: TextStyle(fontSize: width / 4, color: Colors.deepPurple),
    );
    painterNum.layout();
    painterNum.paint(
      canvas,
      Offset((width / 2) + 32, (height / 2) - 85),
    );

    final img = await pictureRecorder.endRecording().toImage(width, height);
    final data = await img.toByteData(format: ui.ImageByteFormat.png);
    return data!.buffer.asUint8List();
  }

  void _addMarkers() async {
    try {
      String url =
          "http://I9A803.p.ssafy.io:8081/region/court-code?gunguName=강남구";
      final response = await http.get(Uri.parse(url));
      if (response.statusCode == 200) {
        String body = utf8.decode(response.bodyBytes);
        final dongnames = json.decode(body);
        print('Response: $dongnames');

        List<Map<String, dynamic>> dongnameslist = [];
        for (int i = 0; i < dongnames.length; i++) {
          try {
            String courtCode = dongnames[i]['courtCode'];
            List<Location> locations = await locationFromAddress(
                courtCode + '강남구',
                localeIdentifier: 'ko_KR');
            if (locations.isNotEmpty) {
              double lat = locations.first.latitude;
              double lng = locations.first.longitude;

              // int matchingCount = await fetchMatchingPropertiesCount(courtCode);
              dongnameslist.add({
                'courtCode': courtCode,
                'lat': lat,
                'lng': lng,
                'matchingCount': '5',
              });
            } else {
              print('위치를 찾을 수 없습니다: $courtCode');
            }
          } catch (e) {
            print('오류 발생: $e');
          }
        }

        for (int i = 0; i < dongnameslist.length; i++) {
          // int matchingCount = dongnameslist[i]["matchingCount"];

          if (dongnameslist[i]["courtCode"] != null) {
            final Uint8List markerIcon =
            await getBytesFromCanvas(dongnameslist[i]["courtCode"], 200, 200);
            final Uint8List markerIconNum =
            await getBytesFromCanvas("5", 200, 200);

            var markerIdVal = dongnameslist[i]["courtCode"];
            final MarkerId markerId = MarkerId(markerIdVal);

            final Marker marker = Marker(
              markerId: markerId,
              position: LatLng(
                dongnameslist[i]["lat"],
                dongnameslist[i]["lng"],
              ),
              icon: BitmapDescriptor.fromBytes(markerIcon),
              infoWindow: InfoWindow(
                title: dongnameslist[i]["courtCode"],
                snippet: '',
              ),
              onTap: () {
                setState(() {
                  isDrawerOpen = true;
                });
                _drawercontroller.open();

              },
            );
            if (this.mounted) {
              setState(() {
                markers[markerId] = marker;
              });
            }
          }
        }
      }
    } catch (e) {
      print('Error: $e');
    }
  }


  Future<void> _onMarkerTapped(MarkerId markerId) async {
    setState(() {
      tappedMarker = markers[markerId];
      isDrawerOpen = true; // 바로 열림
    });
    _drawercontroller.open();
  }

  // void _onMapCreated(GoogleMapController controller) {
  //   mapController = controller;
  // }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: CustomAppBar(
          onLeadingPressed: () {
            context.go('/');
          },
          showEstateHomeButton: true,
          onEstateHomePressed: () {
            context.go('/estate_home');
          },
        ),
        body: GestureDetector(
          onTap: () {
            if (isDrawerOpen) {
              _drawercontroller.close();
              setState(() {
                isDrawerOpen = false;
              });
            }
          },
          child: Stack(
            children: [
              GoogleMap(
                // onMapCreated: _onMapCreated,
                initialCameraPosition: CameraPosition(
                  target: _center,
                  zoom: 13.0,
                ),
                minMaxZoomPreference: MinMaxZoomPreference(13.0, 15.0),
                onTap: (LatLng latLng) {
                  _drawercontroller.open();
                  setState(() {
                    isDrawerOpen = true;
                    _button = 'Open Drawer';
                  });

                },
                markers: Set<Marker>.of(markers.values),
              ),
              _buildBottomDrawer(context),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildBottomDrawer(BuildContext context) {
    return BottomDrawer(
      header: _buildBottomDrawerHead(context),
      body: _buildBottomDrawerBody(context, filteredData),
      headerHeight: 60.0,
      drawerHeight: 500.0,
      color: Colors.white,
      controller: _drawercontroller,
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.15),
          blurRadius: 60,
          spreadRadius: 5,
          offset: const Offset(2, -6),
        ),
      ],
    );
  }
//   onTap: () {
//   context.go(
//   '/estate_detail/${currentHouse.id}');
// },

  Widget _buildBottomDrawerHead(BuildContext context) {
    return Container(
      height: 50.0,
      child: Column(
        children: [
          Padding(
            padding: EdgeInsets.only(
              left: 10.0,
              right: 10.0,
              top: 10.0,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                GestureDetector(
                  onTap: () {
                    _drawercontroller.close();
                    setState(() {
                      isDrawerOpen = false;
                    });
                  },
                  child: FaIcon(
                    FontAwesomeIcons.caretDown,
                    color: Colors.grey[800],
                  ),
                ),
              ],
            ),
          ),
          Spacer(),
          Divider(
            height: 1.0,
            color: Colors.grey,
          ),
        ],
      ),
    );
  }





  Widget _buildBottomDrawerBody(BuildContext context, List<Map<String, dynamic>>? filteredData) {
    if (houseyeoksam == null) {
      return Text('No data available');
    }

    List<Widget> buttons = _buildButtons(houseyeoksam);

    return Container(
      width: double.infinity,
      height: 500.0,
      child: SingleChildScrollView(
        child: Column(
          children: buttons,
        ),
      ),
    );
  }

  List<Widget> _buildButtons(List<Map<String, dynamic>> houseyeoksam) {
    List<Widget> buttons = [];

    for (var item in houseyeoksam) {
      String title = item['title'];
      String fileNames = item['fileNames'][0];
      String squareMeter = item['squareMeter'].toString() + ' / ' + item['supplyAreaMeter'].toString();
      String money = item['deposit'].toString() + '만원' + ' / ' + item['monthlyRent'].toString() + '만원';
      String address = item['address']; // Assuming 'yyyy' is the key in your data
      int houseId = item['id'];
      // String addressbutton = 'Button Text: $address'; // Modify this line as needed
      print(fileNames);
      Widget button = ElevatedButton(
        onPressed: () {
          context.push('/detailPageTwo/${houseId}');
        },
        style: ElevatedButton.styleFrom(
          primary: Colors.deepPurple, // 버튼의 배경색을 보라색으로 변경
        ),

        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Image.network(fileNames, width: 80, height: 80, fit: BoxFit.cover,),
                SizedBox(width: 8),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(title),
                    Text(squareMeter),
                    Text(money),
                    Text(address),
                  ],
                ),
                Spacer(),
                Icon(Icons.heart_broken),
              ],
            ),
          ],
        ),

      );

      buttons.add(button);
      buttons.add(SizedBox(height: 10)); // 버튼 사이에 간격 추가

    }

    return buttons;
  }

  String _button = 'None';
  BottomDrawerController _drawercontroller = BottomDrawerController();
}

void main() {
  runApp(MaterialApp(
    home: MapScreenCity(),
  ));
}
