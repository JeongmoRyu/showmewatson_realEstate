import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:http/http.dart' as http;
import 'package:go_router/go_router.dart';

import 'package:prac2/real_estate_agent/models/notice.dart';

// 공지 글쓰기
class NoticeWrite extends StatefulWidget {
  final int houseId;
  NoticeWrite({this.houseId = 0});

  @override
  State<NoticeWrite> createState() => _NoticeWriteState();
}

class _NoticeWriteState extends State<NoticeWrite> {
  List<Notice> notices = [];

  List<int> months = List<int>.generate(12, (int index) => index + 1); // 월
  List<int> days = List<int>.generate(31, (int index) => index + 1); // 일
  List<int> hours = List<int>.generate(24, (int index) => index); // 시간
  List<int> minutes = <int>[0, 30]; // 분

  int selectedMonth = 1;
  int selectedDay = 1;
  int selectedHour = 0;
  int selectedMinute = 0;

  TextEditingController monthController = TextEditingController();
  TextEditingController dayController = TextEditingController();
  TextEditingController hourController = TextEditingController();
  TextEditingController minuteController = TextEditingController();

  TextEditingController textController = TextEditingController(); // 텍스트 입력 컨트롤러

  bool _isDataPresent = false; // 해당 houseId에 대한 데이터 유무를 확인하는 변수

  @override
  void initState() {
    super.initState();
    _fetchData();
  }

  Future<void> _fetchData() async {
    final String allNoticesUrl = "http://I9A803.p.ssafy.io:8081/schedule";

    // 'Authorization' 헤더 추가 부분
    final headers = {
      "Content-Type": "application/json",
      "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTY3MDE4NjMxLCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA4LTE2VDAxOjQzOjUwWi",
    };

    final responseAll = await http.get(Uri.parse(allNoticesUrl), headers: headers);

    if (responseAll.statusCode == 200) {
      final List<dynamic> allNotices = jsonDecode(utf8.decode(responseAll.bodyBytes));

      // 일치하는 houseId를 찾아서 해당 공지의 id를 가져옵니다.
      final noticeWithMatchingHouseId = allNotices.firstWhere(
            (notice) => notice['houseId'] == widget.houseId,
        orElse: () => null,
      );

      if (noticeWithMatchingHouseId != null) {
        final String specificNoticeUrl = "http://I9A803.p.ssafy.io:8081/schedule/${noticeWithMatchingHouseId['id']}";
        final responseSpecific = await http.get(Uri.parse(specificNoticeUrl), headers: headers);

        if (responseSpecific.statusCode == 200) {
          final data = jsonDecode(utf8.decode(responseSpecific.bodyBytes));
          _isDataPresent = true;
          setState(() {
            textController.text = data['content'].toString();

            if (data['liveDate'] != null) {
              DateTime parsedDate = DateTime.parse(data['liveDate']);
              selectedMonth = parsedDate.month;
              selectedDay = parsedDate.day;
              selectedHour = parsedDate.hour;
              selectedMinute = parsedDate.minute;
            }
          });
        }
      }
    }
  }


  void _resetState() {
    setState(() {
      selectedMonth = 1;
      selectedDay = 1;
      selectedHour = 0;
      selectedMinute = 0;

      textController.clear();

      monthController.clear();
      dayController.clear();
      hourController.clear();
      minuteController.clear();
    });
  }

  Future<void> _submitData() async {
    final String apiUrl = "http://I9A803.p.ssafy.io:8081/schedule";

    final data = {
      "realtorId": "realtor",
      "houseId": widget.houseId,
      "liveDate": "${DateTime.now().year}-${selectedMonth}-${selectedDay} ${selectedHour}:${selectedMinute}:00",
      "content": "${textController.text}",
    };

    if (_isDataPresent) {
      // PUT 요청을 사용하여 데이터 수정
      final response = await http.put(
        Uri.parse(apiUrl),
        headers: {
          "Content-Type": "application/json",
          "Authorization": "key",
        },
        body: jsonEncode(data),
      );

      if (response.statusCode == 200) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('데이터가 성공적으로 수정되었습니다!')),
        );
      } else {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('데이터 수정에 실패했습니다. 다시 시도해 주세요.')),
        );
      }

    } else {
      // POST 요청을 사용하여 데이터 추가
      final response = await http.post(
        Uri.parse(apiUrl),
        headers: {
          "Content-Type": "application/json",
          "Authorization": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJhdHRyaWJ1dGVzIjp7ImlkIjoyOTY3MDE4NjMxLCJjb25uZWN0ZWRfYXQiOiIyMDIzLTA4LTE2VDAxOjQzOjUwWi",
        },
        body: jsonEncode(data),
      );

      if (response.statusCode == 201) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('데이터가 성공적으로 추가되었습니다!')),
        );
      } else {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('데이터 추가에 실패했습니다. 다시 시도해 주세요.')),
        );
      }
    }

    context.go('/my_notice');
  }

  Widget _buildDropdown(String label, List<int> items, Function(int?) onChanged, int? value) {
    return DropdownButton<int?>(
      value: value,
      onChanged: onChanged,
      items: items.map((int item) {
        return DropdownMenuItem<int?>(
          value: item,
          child: Text('$item $label'),
        );
      }).toList(),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,

        leading: IconButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          icon: Icon(FontAwesomeIcons.angleLeft),
          color: Colors.black,
        ),
        centerTitle: true,
        title: const Text(
            '공지 글쓰기',
            style: TextStyle(
              color: Colors.black,
              fontSize: 20.0,
            ),
        ),
        actions: [
          TextButton(
            style: TextButton.styleFrom(
              backgroundColor: Colors.white,
            ),
            onPressed: _resetState,
            child: const Text(
              '초기화',
              style: TextStyle(
                color: Colors.black,
                fontWeight: FontWeight.w600,
                fontSize: 17.0,
              ),
            ),
          ),
        ],
      ),

      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween, // 여기서 변경했습니다.
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Text('Live 날짜 :'),
                    _buildDropdown('월', months, (value) => setState(() => selectedMonth = value!), selectedMonth),
                    _buildDropdown('일', days, (value) => setState(() => selectedDay = value!), selectedDay),
                  ],
                ),
              ],
            ),

            SizedBox(height: 10),

            Row(
              children: [
                Text('Live 시간 :'),
                _buildDropdown('시', hours, (value) => setState(() => selectedHour = value!), selectedHour),
                _buildDropdown('분', minutes, (value) => setState(() => selectedMinute = value!), selectedMinute),
              ],
            ),

            SizedBox(height: 20),

            TextField(
              controller: textController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                // labelText: '공지 내용 입력',
              ),
              maxLines: 7, // 여러 줄 입력 가능
              textInputAction: TextInputAction.done, // 완료 버튼으로 설정
              onSubmitted: (text) {
                // 키보드 닫기
                FocusScope.of(context).unfocus();
              },
              textAlignVertical: TextAlignVertical.top, // 텍스트 시작 위치를 상단으로 설정
            ),

            Spacer(),

            Center(
              child: Container(
                width: double.infinity, // 최대한 넓게
                margin: const EdgeInsets.only(top: 10.0),
                padding: const EdgeInsets.all(10.0), // 원하는 패딩 값
                decoration: BoxDecoration(
                  color: Colors.grey[300], // 회색으로 색칠
                ),
                child: ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    padding: const EdgeInsets.symmetric(vertical: 14.0), // 버튼의 수직 패딩 조절
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0), // 버튼의 모서리를 둥글게
                    ),
                  ),
                  onPressed: _submitData,
                  child: Text('등록하기'),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
