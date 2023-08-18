import 'dart:convert';

import 'package:http_parser/http_parser.dart';
import 'package:image_picker/image_picker.dart';
import 'package:dio/dio.dart';

import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';

import 'package:prac2/real_estate_agent/models/detail.dart';
import 'package:provider/provider.dart';

// 매물 입력 화면
class UploadSale extends StatefulWidget {
  const UploadSale({super.key});

  @override
  State<UploadSale> createState() => _UploadSaleState();
}

class _UploadSaleState extends State<UploadSale> {

  final ImagePicker _picker = ImagePicker();

  int _selectedButton = 1; // 기본값은 1번 버튼

  int? _selectedValue;
  final List<Map<String, dynamic>> _items = [
    {"label": "월세", "value": 1},
    {"label": "전세", "value": 2},
    {"label": "매매", "value": 3},
  ];


  TextEditingController _sidoNameController = TextEditingController();
  TextEditingController _gunguNameController = TextEditingController();
  TextEditingController _dongleeNameController = TextEditingController();
  TextEditingController _houseCodeNameController = TextEditingController();
  TextEditingController _squareMeterNameController = TextEditingController();
  TextEditingController _supplyAreaMeterNameController = TextEditingController();
  TextEditingController _floorNameController = TextEditingController();
  TextEditingController _addressNameController = TextEditingController();
  TextEditingController _depositNameController = TextEditingController();
  TextEditingController _monthlyRentNameController = TextEditingController();
  TextEditingController _salePriceNameController = TextEditingController();
  TextEditingController _maintenanceNameController = TextEditingController();
  TextEditingController _titleNameController = TextEditingController();
  TextEditingController _maintenanceListNameController = TextEditingController();
  TextEditingController _totalFloorNameController = TextEditingController();
  TextEditingController _buildingUseNameController = TextEditingController();
  TextEditingController _approvalBuildingDateNameController = TextEditingController();
  TextEditingController _bathroomNameController = TextEditingController();
  TextEditingController _roomNameController = TextEditingController();
  TextEditingController _contentNameController = TextEditingController();

  List<String> _fileNames = [];


  bool _sink = false;
  bool _airConditioner = false;
  bool _shoeCloset = false;
  bool _washingMachine = false;
  bool _refrigerator = false;
  bool _closet = false;
  bool _induction = false;
  bool _desk = false;
  bool _elevator = false;
  bool _coldHeating = false;
  bool _parking = false;

  Future<void> _sendData() async {
    // 이미지 선택
    XFile? selectImage = await _picker.pickImage(
      source: ImageSource.gallery,
      maxHeight: 75,
      maxWidth: 75,
      imageQuality: 30,
    );

    if (selectImage != null) {
      dynamic sendData = selectImage.path;

        // 상세 데이터 구성
        final detailData = {
          'sidoName': _sidoNameController.text,
          'gunguName': _gunguNameController.text,
          'dongleeName': _dongleeNameController.text,
          'houseCodeName': int.parse(_houseCodeNameController.text),
          'squareMeterName': double.parse(_squareMeterNameController.text),
          'supplyAreaMeterName': double.parse(
              _supplyAreaMeterNameController.text),
          'floorName': int.parse(_floorNameController.text),
          'addressName': _addressNameController.text,
          'depositName': int.parse(_depositNameController.text),
          'monthlyRentName': int.parse(_monthlyRentNameController.text),
          'salePriceName': int.parse(_salePriceNameController.text),
          'maintenanceName': int.parse(_maintenanceNameController.text),
          'titleName': _titleNameController.text,
          'fileNames': _fileNames,
          'maintenanceListName': _maintenanceListNameController.text,
          'contractCodeName': _selectedValue,
          'totalFloorName': int.parse(_totalFloorNameController.text),
          'buildingUseName': _buildingUseNameController.text,
          'approvalBuildingDateName': _approvalBuildingDateNameController.text,
          'bathroomName': int.parse(_bathroomNameController.text),
          'roomName': int.parse(_roomNameController.text),
          'contentName': _contentNameController.text,
          'houseOption': {
            'sink': _sink,
            'airConditioner': _airConditioner,
            'shoeCloset': _shoeCloset,
            'washingMachine': _washingMachine,
            'refrigerator': _refrigerator,
            'closet': _closet,
            'induction': _induction,
            'desk': _desk,
            'elevator': _elevator,
            'coldHeating': _coldHeating,
            'parking': _parking,
          },
          'image': await MultipartFile.fromFile(sendData)
        };
      await patchUserProfileImage(detailData);
    } else {
      print("이미지 선택이 취소되었습니다.");
    }
  }

  Future<dynamic> patchUserProfileImage(Map<String, dynamic> detailData) async {
    print("데이터를 서버에 업로드 합니다.");
    var dio = Dio();

    FormData formData = FormData.fromMap(detailData);

    try {
      dio.options.contentType = 'multipart/form-data';
      dio.options.headers = {"Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTY3MDE4NjMxLCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA4LTE2VDAxOjQzOjUwWi"};

      var response = await dio.post(
        'http://I9A803.p.ssafy.io:8081/house', // 주소를 POST 주소로 변경했습니다.
        data: formData,
      );


      print('성공적으로 업로드했습니다');
      return response.data;
    } catch (e) {
      print(e);
    }
  }

  Widget _getSelectedContent() {
    switch (_selectedButton) {
      case 1:
        // 1. 위치 정보
        return SingleChildScrollView(
            child: Column(
              children: <Widget>[
                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("시/도 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _sidoNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '시/도',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("시/군/구 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _gunguNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '시/군/구',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("읍/면/동 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _dongleeNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '읍/면/동',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("상세주소 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller:_addressNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '상세주소',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("전용면적 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller:_squareMeterNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '전용면적',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("가용면적 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller:_supplyAreaMeterNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '가용면적',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),
              ],
            ),
          );
      case 2:
        // 2. 거래 정보
        return SingleChildScrollView(
            child: Column(
              children: <Widget>[
                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("계약형태 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                        child: DropdownButton<int>(
                          hint: Text('계약형태'),
                          value: _selectedValue,
                          onChanged: (int? newValue) {
                            setState(() {
                              _selectedValue = newValue;
                            });
                          },
                          items: _items.map((Map<String, dynamic> item) {
                            return DropdownMenuItem<int>(
                              value: item["value"],
                              child: Text(item["label"]),
                            );
                          }).toList(),
                        ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("보증금 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _depositNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '보증금',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("월세 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _monthlyRentNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '월세',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("관리비 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _maintenanceNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '관리비',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),
              ],
            ),
          );
      case 3:
        // 3. 방 정보
        return SingleChildScrollView(
            child: Column(
              children: <Widget>[
                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("매물번호 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _houseCodeNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '매물번호',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("건물용도 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _buildingUseNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '건물용도',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("방 개수 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _roomNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '방 개수',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("욕실 개수 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _bathroomNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '욕실 개수',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("전체 층 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _totalFloorNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '전체 층',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("해당 층 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: <TextInputFormatter>[
                            FilteringTextInputFormatter.digitsOnly
                          ],
                          controller: _floorNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '해당 층',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("건축물일자 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _approvalBuildingDateNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '건축물일자',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("제목 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _titleNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '제목',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("상세설명 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _maintenanceListNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '상세설명',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),

                Container(
                  alignment: Alignment(0.0, 0.0),
                  height: 45,
                  margin: EdgeInsets.only(left: 30, right: 30, top: 15),
                  padding: EdgeInsets.only(left: 20, right: 20),
                  decoration: new BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      border: Border.all(width: 1, color: Colors.black12)),
                  child: Row(children: <Widget>[
                    Container(
                      width: 80,
                      child: Text("내용 :",
                          style: TextStyle(fontSize: 16, color: Colors.black)),
                    ),
                    Expanded(
                      child: Container(
                        margin: EdgeInsets.only(right: 20),
                        child: TextField(
                          controller: _contentNameController,
                          style: TextStyle(color: Colors.black),
                          decoration: InputDecoration(
                              border: InputBorder.none,
                              hintText: '내용',
                              hintStyle: TextStyle(color: Colors.grey[300])),
                          cursorColor: Colors.blue,
                        ),
                      ),
                    ),
                  ]),
                ),
                CheckboxListTile(
                  title: Text('Sink'),
                  value: _sink,
                  onChanged: (value) {
                    setState(() {
                      _sink = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Air Conditioner'),
                  value: _airConditioner,
                  onChanged: (value) {
                    setState(() {
                      _airConditioner = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Shoe Closet'),
                  value: _shoeCloset,
                  onChanged: (value) {
                    setState(() {
                      _shoeCloset = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Washing Machine'),
                  value: _washingMachine,
                  onChanged: (value) {
                    setState(() {
                      _washingMachine = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Refrigerator'),
                  value: _refrigerator,
                  onChanged: (value) {
                    setState(() {
                      _refrigerator = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Closet'),
                  value: _closet,
                  onChanged: (value) {
                    setState(() {
                      _closet = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Induction'),
                  value: _induction,
                  onChanged: (value) {
                    setState(() {
                      _induction = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Desk'),
                  value: _desk,
                  onChanged: (value) {
                    setState(() {
                      _desk = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Elevator'),
                  value: _elevator,
                  onChanged: (value) {
                    setState(() {
                      _elevator = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Cold Heating'),
                  value: _coldHeating,
                  onChanged: (value) {
                    setState(() {
                      _coldHeating = value!;
                    });
                  },
                ),
                CheckboxListTile(
                  title: Text('Parking'),
                  value: _parking,
                  onChanged: (value) {
                    setState(() {
                      _parking = value!;
                    });
                  },
                ),

              ],
            ),
          );
      case 4:
        // 4. 사진 등록
        return ElevatedButton(
            onPressed: () => _sendData(),
            child: Icon(FontAwesomeIcons.camera),
        );
      default:
        return SizedBox.shrink(); // 기본적으로 아무것도 표시하지 않음
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        leading: IconButton(
          onPressed: () {
            context.go('/estate_home');
          },
          icon: Icon(FontAwesomeIcons.angleLeft),
          color: Colors.black,
        ),
        centerTitle: true,
        title: const Text(
          '매물 올리기',
          style: TextStyle(
            color: Colors.black,
            fontSize: 20.0,
          ),
        ),
        bottom: const PreferredSize(
          preferredSize: Size.fromHeight(1.0),  // 태두리 높이 설정
          child: Divider(
            height: 1,  // 태두리 높이 설정
            color: Colors.grey,  // 태두리 색상 설정
            thickness: 1,  // 태두리 두께 설정
          ),
        ),
      ),

      resizeToAvoidBottomInset: true,

      body: Column(
        children: <Widget>[
          // 고정된 버튼 행
          Container(
            height: 60.0, // 원하는 높이로 설정
            color: Colors.white, // 배경색 설정
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [

                // 1번 버튼
                SizedBox(
                  height: 60,
                  child: ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _selectedButton = 1; // 선택된 버튼 업데이트
                      });
                    },
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      backgroundColor: Colors.white,
                      side: const BorderSide(color: Colors.white),
                      shape: const RoundedRectangleBorder(
                        // 직사각형 모양으로 설정
                        borderRadius: BorderRadius.zero, // 둥근 모서리 없음
                      ),
                    ).copyWith(
                      elevation: MaterialStateProperty.all<double>(0),
                    ),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                            '01',
                          style: TextStyle(
                            color: _selectedButton == 1
                                ? Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        Text(
                            '위치정보',
                          style: TextStyle(
                            color: _selectedButton == 1
                                ? Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                      ],
                    ),
                  ),
                ),

                // 2번 버튼
                SizedBox(
                  height: 60,
                  child: ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _selectedButton = 2; // 선택된 버튼 업데이트
                      });
                    },
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      backgroundColor: Colors.white,
                      side: const BorderSide(color: Colors.white),
                      shape: const RoundedRectangleBorder(
                        // 직사각형 모양으로 설정
                        borderRadius: BorderRadius.zero, // 둥근 모서리 없음
                      ),
                    ).copyWith(
                      elevation: MaterialStateProperty.all<double>(0),
                    ),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          '02',
                          style: TextStyle(
                            color: _selectedButton == 2
                                ? Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        Text(
                          '거래 정보',
                          style: TextStyle(
                            color: _selectedButton == 2
                                ? Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        const Divider(
                          color: Colors.black,
                          height: 4,
                          thickness: 1,
                          indent: 0,
                          endIndent: 20, // 선의 길이를 조절하려면 이 값을 조절하십시오.
                        ),
                      ],
                    ),
                  ),
                ),

                // 3번 버튼
                SizedBox(
                  height: 60,
                  child: ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _selectedButton = 3; // 선택된 버튼 업데이트
                      });
                    },
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      backgroundColor: Colors.white,
                      side: const BorderSide(color: Colors.white),
                      shape: const RoundedRectangleBorder(
                        // 직사각형 모양으로 설정
                        borderRadius: BorderRadius.zero, // 둥근 모서리 없음
                      ),
                    ).copyWith(
                      elevation: MaterialStateProperty.all<double>(0),
                    ),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          '03',
                          style: TextStyle(
                            color: _selectedButton == 3
                                ? const Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        Text(
                          '방 정보',
                          style: TextStyle(
                            color: _selectedButton == 3
                                ? const Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        const Divider(
                          color: Colors.black,
                          height: 4,
                          thickness: 1,
                          indent: 0,
                          endIndent: 20, // 선의 길이를 조절하려면 이 값을 조절하십시오.
                        ),
                      ],
                    ),
                  ),
                ),

                // 4번 버튼
                SizedBox(
                  height: 60,
                  child: ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _selectedButton = 4; // 선택된 버튼 업데이트
                      });
                    },
                    style: ElevatedButton.styleFrom(
                      elevation: 0,
                      backgroundColor: Colors.white,
                      side: const BorderSide(color: Colors.white),
                      shape: const RoundedRectangleBorder(
                        // 직사각형 모양으로 설정
                        borderRadius: BorderRadius.zero, // 둥근 모서리 없음
                      ),
                    ).copyWith(
                      elevation: MaterialStateProperty.all<double>(0),
                    ),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          '04',
                          style: TextStyle(
                            color: _selectedButton == 4
                                ? const Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        Text(
                          '사진등록',
                          style: TextStyle(
                            color: _selectedButton == 4
                                ? const Color(0xFFDCBF97)
                                : Colors.black,
                          ),
                        ),
                        const Divider(
                          color: Colors.black,
                          height: 4,
                          thickness: 1,
                          indent: 0,
                          endIndent: 20, // 선의 길이를 조절하려면 이 값을 조절하십시오.
                        ),
                      ],
                    ),
                  ),
                ),
              ],
            ),
          ),

          Flexible(
              child: _getSelectedContent()
          ),
        ],
      ),

      bottomNavigationBar: ElevatedButton(
        style: ButtonStyle(
          minimumSize: MaterialStateProperty.all(Size(double.infinity, 50)),  // 50은 원하는 높이 값
        ),
        onPressed: () async {
          // await patchUserProfileImage();
        },
        child: Text('Submit'),
      ),
    );
  }
}
