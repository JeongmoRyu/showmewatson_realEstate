import 'package:flutter/material.dart';

class prac17 extends StatefulWidget {
  const prac17({Key? key}) : super(key: key);

  @override
  _prac17State createState() => _prac17State();
}

class _prac17State extends State<prac17> {
  int? startIndex;
  int? endIndex;
  List<String> clickList = [];
  List<String> allButtons = List.generate(20, (index) => 'Button ${index + 1}');

  void selectButtonsBetween(int startIndex, int endIndex) {
    setState(() {
      clickList.clear();
      clickList.addAll(allButtons.sublist(startIndex, endIndex + 1));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('보여줘왓슨'),
      ),
      body: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                onPressed: () {
                  setState(() {
                    clickList.clear();
                    clickList.addAll(allButtons);
                  });
                },
                child: Text('전체 선택'),
              ),
              ElevatedButton(
                onPressed: () {
                  setState(() {
                    clickList.clear();
                    startIndex = null; // 시작점 초기화
                    endIndex = null; // 끝점 초기화
                  });
                },
                child: Text('초기화'),
              ),
            ],
          ),
          GridView.builder(
            shrinkWrap: true,
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 5,
              crossAxisSpacing: 5.0,
              mainAxisSpacing: 5.0,
            ),
            itemCount: allButtons.length,
            itemBuilder: (context, index) {
              final button = allButtons[index];
              final isSelected = clickList.contains(button);

              return Container(
                child: ElevatedButton(
                  onPressed: () {
                    if (clickList.isEmpty) {
                      setState(() {
                        clickList.add(button);
                        startIndex = index;
                        endIndex = index;
                      });
                    } else {
                      if (startIndex != null && endIndex != null) {
                        int currentIndex = allButtons.indexOf(button);

                        if (currentIndex < startIndex!) {
                          setState(() {
                            startIndex = currentIndex;
                            clickList.clear();
                            clickList.addAll(allButtons.sublist(currentIndex, endIndex! + 1));
                          });
                        } else if (currentIndex > endIndex!) {
                          setState(() {
                            endIndex = currentIndex;
                            clickList.clear();
                            clickList.addAll(allButtons.sublist(startIndex!, currentIndex + 1));
                          });
                        } else {
                          selectButtonsBetween(startIndex!, endIndex!);
                        }
                      }
                    }
                  },
                  style: ElevatedButton.styleFrom(
                    primary: isSelected ? Colors.blue : Colors.grey,
                  ),
                  child: Text(button),
                ),
              );
            },
          ),
        ],
      ),
    );
  }
}


void main() {
  runApp(MaterialApp(
    home: prac17(),
  ));
}
