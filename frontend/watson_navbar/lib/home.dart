import'package:flutter/material.dart';

// StatelessWidget은 변화지 않는 화면을 작업할 때 사용.
// 변화는 화면을 작업 하고싶을 경우에는 StatefulWidget을 사용.
class Home extends StatelessWidget {

  // MaterialApp = 앱으로서 기능을 할 수 있도록 도와주는 뼈대
  @override
  Widget build(BuildContext context) {

    // return MaterialApp() -> Material 디자인 테마를 사용
    return MaterialApp(
      title: "MyApp", // 앱 이름
      debugShowCheckedModeBanner: false, // 타이틀 바 우측 띠 제거

      // 앱의 기본적인 테마를 지정
      theme: ThemeData(
        primarySwatch: Colors.blue, // priamrySwatch 기본적인 앱의 색상을 지정
      ),

      home: MyWidget(), // 앱이 실행될 때 표시할 화면의 함수를 호출
    );
  }
}

// 앱이 실행 될때 표시할 화면의 함수
class MyWidget extends StatelessWidget {

  // scaffold = 구성된 앱에서 디자인적인 부분을 도와주는 뼈대

  // 화면 구성
  @override
  Widget build(BuildContext context) {
    return Scaffold(

      // appBar에 AppBar 위젯을 가져온다.
      appBar: AppBar(title: Text("Flutter study"), // 타이틀 이름 지정
        centerTitle: true, // 타이틀 이름을 가운데 정렬
        elevation: 0.0, //elevation 속성을 통해 그림자 효과 제어

        // appBar 높이
        toolbarHeight: 70,

        // 좌측 아이콘 버튼
        leading: IconButton(onPressed: () {}, icon: const Icon(Icons.menu),),
      ),

      // 앱의 body 부분
      body: Row( // Row 위젯을 가져온다.

        // Row 위젯이므로 가로축 기준으로 가운데 정렬
        mainAxisAlignment: MainAxisAlignment.center,

        // Row의 하위 위젯
        children: <Widget>[
          cntState(), // 함수를 불러온다.
        ],
      ),

    );
  }
}

// 버튼을 눌렀을 때 숫자를 카운트 하기위해서는 화면을 변하게끔 작업해야한다.
// 즉, StatefulWidget 선언해야 한다.
class cntState extends StatefulWidget {
  @override
  _cntState createState() => _cntState(); // StatefulWidget은 상태를 생성하는 createState() 메서드로 구현한다.
}

int _cnt = 0;

class _cntState extends State<cntState> {
  @override
  Widget build(BuildContext context) {
    return Center(
      // Elevated Button 위젯
      child: ElevatedButton(
        // 버튼에 Text를 입힌다.
        child: Text('현재 숫자 : $_cnt'),

        // 클릭 이벤트
        onPressed: () {

          // setState() 메서드를 수행시 다시 build() 메서드가 실행되며 동적 화면이 구현된다.
          setState(() {
            _cnt++;
            print("$_cnt");
          });
        },
      ),
    );
  }
}