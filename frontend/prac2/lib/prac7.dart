import 'package:bottom_drawer/bottom_drawer.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';



class prac7 extends StatefulWidget {
  @override
  _prac7State createState() => _prac7State();
}

class _prac7State extends State<prac7> {
  bool isDrawerOpen = false;
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        key: _scaffoldKey,
        appBar: AppBar(
          title: const Text('prac7'),
        ),
        body: GestureDetector(
            onTap: () {
              if (isDrawerOpen) {
                setState(() {
                isDrawerOpen = false;
                _button = 'Close Drawer';
                });
              }
          },
        child: Stack(
          children: [
            Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Text('Pressed $_button'),
                Padding(
                  padding: EdgeInsets.only(top: 20.0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      GestureDetector(
                        onTap: () {
                          _controller.open();
                          setState(() {
                            isDrawerOpen = true;
                            _button = 'Open Drawer';
                          });
                        },
                        child: CircleAvatar(
                          backgroundImage: AssetImage('assets/img_1.png'),
                          backgroundColor: Colors.white,
                        ),
                      ),
                      Divider(
                        height: 10.0,
                      ),
                    ],
                  ),
                ),
              ],
            ),
            _buildBottomDrawer(context),
          ],
        ),
      ),
    )
    );
  }

  Widget _buildBottomDrawer(BuildContext context) {
    return BottomDrawer(
      header: _buildBottomDrawerHead(context),
      body: _buildBottomDrawerBody(context),
      headerHeight: _headerHeight,
      drawerHeight: _bodyHeight,
      color: Colors.lightBlue,
      controller: _controller,
      boxShadow: [
        BoxShadow(
          color: Colors.black.withOpacity(0.15),
          blurRadius: 60,
          spreadRadius: 5,
          offset: const Offset(2, -6), // changes position of shadow
        ),
      ],
    );
  }

  Widget _buildBottomDrawerHead(BuildContext context) {
    return Container(
      height: _headerHeight,
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
                    _controller.close();
                    setState(() {
                      _button = 'Close Drawer';
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


  Widget _buildBottomDrawerBody(BuildContext context) {
    return Container(
      width: double.infinity,
      height: _bodyHeight,
      child: SingleChildScrollView(
        child: Column(
          children: _buildButtons('Body', 1, 25),
        ),
      ),
    );
  }

  List<Widget> _buildButtons(String prefix, int start, int end) {
    List<Widget> buttons = [];
    for (int i = start; i <= end; i++)
      buttons.add(TextButton(
        child: Text(
          '$prefix Button $i',
          style: TextStyle(
            fontSize: 15.0,
            color: Colors.black,
          ),
        ),
        onPressed: () {
          setState(() {
            _button = '$prefix Button $i';
          });
        },
      ));
    return buttons;
  }

  String _button = 'None';
  double _headerHeight = 60.0;
  double _bodyHeight = 500.0;
  BottomDrawerController _controller = BottomDrawerController();
}