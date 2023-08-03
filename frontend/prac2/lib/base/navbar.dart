import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';


import 'package:prac2/screens/home_screen.dart';
import 'package:prac2/screens/interest_screen.dart';
import 'package:prac2/screens/chatlist_screen.dart';
import 'package:prac2/screens/map_screen.dart';
import 'package:prac2/screens/mypage_screen.dart';

// Bottom Navigation Bar
// 동적으로 화면을 변화하므로 StatefulWidget 사용
class NavBar extends StatefulWidget {
  final GoRouter router;

  NavBar({required this.router}); // GoRouter 객체를 받아옵니다.

  @override
  _NavBarState createState() => _NavBarState();
}

class _NavBarState extends State<NavBar> {

  // 처음에 나올 화면 지정
  // 페이지(_pages)들을 리스트로 지정해서 저장, Index에 따라 페이지 호출
  int _selectedIndex = 0;

  // 각 탭의 경로를 리스트로 저장합니다.
  List<String> _paths = ['/', '/chatlist', '/map', '/interest', '/mypage'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(

        body: IndexedStack(
          index: _selectedIndex,
          children: [
            HomeScreen(),
            ChatList(),
            MapScreen(),
            Interest(),
            MyPage(),
          ],
        ),

        // BottomNavigationBar 위젯
        bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Theme.of(context).primaryColor, // primaryColor 호출
          selectedItemColor: Colors.white, // 선택된 아이템의 색상
          unselectedItemColor: Colors.black, // 선택되지 않은 아이템의 색상
          type: BottomNavigationBarType.fixed, // bottomNavigationBar item이 4개 이상일 경우

          // 클릭 이벤트
          onTap: _onItemTapped,

          currentIndex: _selectedIndex, // 현재 선택된 index

          // BottomNavigationBarItem 위젯
          items: <BottomNavigationBarItem>[
            BottomNavigationBarItem(
                icon: Icon(FontAwesomeIcons.house),
                label: 'Home'
            ),

            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.heart),
              label: '관심매물',
            ),

            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.mapLocationDot),
              label: '매물검색',
            ),

            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.comments),
              label: '채팅',
            ),

            BottomNavigationBarItem(
              icon: Icon(FontAwesomeIcons.user),
              label: 'MyPage',
            ),

          ],
        )
    );
  }

  void _onItemTapped(int index) {
    void _onItemTapped(int index) {
      // state 갱신
      setState(() {
        _selectedIndex = index; // index는 item 순서로 0, 1, 2, ...로 구성
      });
      widget.router.go(_paths[_selectedIndex]); // 선택된 경로로 이동
    }
  }
}
