import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:prac2/base/appbar.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:geocoding/geocoding.dart';
// import 'package:prac2/screens/map_screen_city.dart';

class MapScreen extends StatefulWidget {
  const MapScreen({Key? key}) : super(key: key);

  @override
  _MapScreenState createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  late GoogleMapController mapController;
  late Marker? tappedMarker;

  final LatLng _center = const LatLng(37.50125, 127.03969);
  final double _initialZoom = 9.0;
  final double _markerZoom = 11.0;
  final double _dongMarkerZoom = 13.0;

  final List<Map<String, dynamic>> cityCoordinates = [
    {'name': '서울특별시', 'lat': 37.5665, 'lng': 126.9780},
    {'name': '부산광역시', 'lat': 35.1796, 'lng': 129.0756},
    {'name': '대구광역시', 'lat': 35.8714, 'lng': 128.6014},
    {'name': '인천광역시', 'lat': 37.4563, 'lng': 126.7052},
    {'name': '광주광역시', 'lat': 35.1595, 'lng': 126.8526},
    {'name': '대전광역시', 'lat': 36.3504, 'lng': 127.3845},
    {'name': '울산광역시', 'lat': 35.5384, 'lng': 129.3114},
    {'name': '세종특별자치시', 'lat': 36.4808, 'lng': 127.2884},
    {'name': '경기도', 'lat': 37.4138, 'lng': 127.5183},
    {'name': '북부출장소', 'lat': 37.13260, 'lng': 128.19135}, // Add actual coordinates
    {'name': '충청북도', 'lat': 36.6350, 'lng': 127.4912},
    {'name': '충청남도', 'lat': 36.6588, 'lng': 126.6728},
    {'name': '전라북도', 'lat': 35.7167, 'lng': 127.1442},
    {'name': '전라남도', 'lat': 34.8679, 'lng': 126.9910},
    {'name': '경상북도', 'lat': 36.5765, 'lng': 128.5056},
    {'name': '경상남도', 'lat': 35.4606, 'lng': 128.2132},
    {'name': '제주특별자치도', 'lat': 33.4996, 'lng': 126.5312},
    {'name': '강원도', 'lat': 37.8228, 'lng': 128.1555},
    {'name': '동해출장소', 'lat': 37.52451, 'lng': 129.11463}, // Add actual coordinates
  ];


  late List<Marker> markers;
  late List<Marker> newmarkers;

  // final GoRouter _router = GoRouter( );

  void _onMapCreated(GoogleMapController controller) {
    mapController = controller;
  }

  void _onMarkerTapped(MarkerId markerId) async {
    tappedMarker = markers.firstWhere((marker) => marker.markerId == markerId);
    double targetZoom = _markerZoom;
    int markerIndex = markers.indexWhere((marker) => marker.markerId == markerId);


    if (markerIndex >= 19) {
      context.go('/map_screen_city');
      // _router.go('/map_screen_city');
      print('blue icon');
      targetZoom = _dongMarkerZoom;
    } else {
      print('red icon');
      // print('${markers[41]}');
      targetZoom = _markerZoom;
    }

    mapController.animateCamera(CameraUpdate.newLatLngZoom(tappedMarker!.position, targetZoom));


      if (_markerZoom < 11.0) {
      List<Map<String, dynamic>> seoulCoordinates = [];

    }

    if (13 > _markerZoom && _markerZoom >= 11.0) {
      String cityName = markerId.value;
      String url = "http://I9A803.p.ssafy.io:8081/region?sido=$cityName";

      try {
        final response = await http.get(Uri.parse(url));
        if (response.statusCode == 200) {
          String body = utf8.decode(response.bodyBytes);
          final sigungu = json.decode(body);
          print('Response: $sigungu');

          List<Map<String, dynamic>> seoulCoordinates = [];
          for (int i = 0; i < sigungu.length; i++) {
            try {
              List<Location> locations = await locationFromAddress(sigungu[i] + ' 서울', localeIdentifier: 'ko_KR');
              if (locations.isNotEmpty) {
                double lat = locations.first.latitude;
                double lng = locations.first.longitude;
                seoulCoordinates.add({
                  'name': sigungu[i],
                  'lat': lat,
                  'lng': lng,
                });
              } else {
                print('위치를 찾을 수 없습니다: ${sigungu[i]}');
              }
            } catch (e) {
              print('오류 발생: $e');
            }
          }
          // print('$seoulCoordinates');

          List<Marker> newMarkers = seoulCoordinates.map<Marker>((coordinate) {
            return Marker(
              markerId: MarkerId(coordinate['name']),
              position: LatLng(coordinate['lat'], coordinate['lng']),
              icon: BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueBlue),
              onTap: () {
                  context.push('/map_screen_city');
              },
            );
          }).toList();

          setState(() {
            markers.addAll(newMarkers);

          });

        } else {
          print('Request failed with status: ${response.statusCode}');
        }
      } catch (e) {
        print('Error: $e');
      }
    }
  //
  //   if (_markerZoom >= 13.0) {
  //     // 파란색 마커 클릭 시 처리
  //     String dongName = markerId.value;
  //     String guurl = "http://I9A803.p.ssafy.io:8081/region/court-code?gunguName=$dongName";
  //     try {
  //       final guResponse = await http.get(Uri.parse(guurl));
  //       if (guResponse.statusCode == 200) {
  //         String gubody = utf8.decode(guResponse.bodyBytes);
  //         final courtCode = json.decode(gubody);
  //         print('$courtCode');
  //
  //         mapController.animateCamera(CameraUpdate.newLatLngZoom(marker.position, _markerZoom));
  //
  //
  //         // 새로운 마커들 생성
  //         List<Marker> newMarkers = courtCode.map<Marker>((data) {
  //           double lat = data['lat'];
  //           double lng = data['lng'];
  //           return Marker(
  //             markerId: MarkerId(data['name']),
  //             position: LatLng(lat, lng),
  //             icon: BitmapDescriptor.defaultMarkerWithHue(BitmapDescriptor.hueOrange),
  //           );
  //         }).toList();
  //
  //         setState(() {
  //           newmarkers.addAll(newMarkers);
  //         });
  //
  //       } else {
  //         print('Court Code Request failed with status: ${guResponse.statusCode}');
  //       }
  //     } catch (e) {
  //       print('Court Code Error: $e');
  //     }
  //   }
  }

  void resetTappedMarker(MarkerId resetMarkerId) {
    _onMarkerTapped(resetMarkerId);
  }

  @override
  void initState() {
    super.initState();
    markers = cityCoordinates.map((city) {
      final markerId = MarkerId(city['name']);
      return Marker(
        markerId: markerId,
        position: LatLng(city['lat'], city['lng']),
        onTap: () => _onMarkerTapped(markerId),
      );
    }).toList();

    // _router.setInitialLocation('/map');

  }

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
        body: GoogleMap(
          onMapCreated: _onMapCreated,
          initialCameraPosition: CameraPosition(
            target: _center,
            zoom: _initialZoom,
          ),
          minMaxZoomPreference: MinMaxZoomPreference(9.0, 15.0),
          markers: Set<Marker>.from(markers),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: MapScreen(),
  ));
}