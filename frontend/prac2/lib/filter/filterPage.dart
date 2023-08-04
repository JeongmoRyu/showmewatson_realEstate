import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';


class Filter extends StatefulWidget {
  const Filter({Key? key}) : super(key: key);

  @override
  _FilterState createState() => _FilterState();
}

class _FilterState extends State<Filter> {
  int? startIndex;
  int? endIndex;
  List<String> clickList = [];
  List<String> allButtons = List.generate(20, (index) => 'Button ${index + 1}');
  List<String> allButtonTwos = List.generate(20, (index) => 'Button2 ${index + 1}');
  List<String> clickList_2 = [];
  int? fromValue;
  int? toValue;
  TextEditingController _fromController = TextEditingController();
  TextEditingController _toController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _fromController.text = '${startIndex ?? ''}';
    _toController.text = '${endIndex ?? ''}';
  }

  void selectButtonsBetween(int startIndex, int endIndex) {
    setState(() {
      clickList.clear();
      clickList.addAll(allButtons.sublist(startIndex, endIndex + 1));
    });
  }

  void selectallButtonTwosBetween(int startIndex, int endIndex) {
    setState(() {
      clickList_2.clear();
      clickList_2.addAll(allButtonTwos.sublist(startIndex, endIndex + 1));
    });
  }

  void selectAllButtons() {
    setState(() {
      clickList.clear();
      clickList.addAll(allButtons);
    });
  }

  void selectAllTwoButtons() {
    setState(() {
      clickList_2.clear();
      clickList_2.addAll(allButtonTwos);
    });
  }

  void resetSelection() {
    setState(() {
      clickList.clear();
      startIndex = null;
      endIndex = null;
    });
  }

  void resetSelectionTwo() {
    setState(() {
      clickList_2.clear();
      startIndex = null;
      endIndex = null;
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


          // 저장된 필터 - 필터 불러오기는 미구현
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Expanded(
                child: ElevatedButton(
                  onPressed: () => print('Button 1 is pressed'),
                  child: Text('필터 1'),
                ),
              ),
              Expanded(
                child: ElevatedButton(
                  onPressed: () => print('Button 2 is pressed'),
                  child: Text('필터 2'),
                ),
              ),
              Expanded(
                child: ElevatedButton(
                  onPressed: () => print('Button 3 is pressed'),
                  child: Text('필터 3'),
                ),
              ),
            ],
          ),


          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              Icon(FontAwesomeIcons.arrowDownWideShort),
              TextButton(
                onPressed: () {
                  if (clickList.isNotEmpty) {
                    setState(() {
                      clickList.removeRange(1, clickList.length - 1);
                    });
                  }
                },
                child: Column(
                  children: [
                    Text(
                      clickList.isEmpty ? '버튼 1' : clickList.length == 1 ? clickList.first : '${clickList.first}',
                      textAlign: TextAlign.center,
                    ),
                    if (clickList.length > 1)
                      Text(
                        '~ ${clickList.last}',
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.white),
                      ),
                  ],
                ),
                style: TextButton.styleFrom(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                ),
              ),
              TextButton(
                onPressed: () {
                  if (clickList_2.isNotEmpty) {
                    setState(() {
                      clickList_2.removeRange(1, clickList_2.length - 1);
                    });
                  }
                },
                child: Column(
                  children: [
                    Text(
                      clickList_2.isEmpty ? '버튼 2' : clickList_2.length == 1 ? clickList_2.first : '${clickList_2.first}',
                      textAlign: TextAlign.center,
                    ),
                    if (clickList_2.length > 1)
                      Text(
                        '~ ${clickList_2.last}',
                        textAlign: TextAlign.center,
                      ),
                  ],
                ),
                style: TextButton.styleFrom(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                ),
              ),
              ElevatedButton(
                onPressed: () => print('Button 3 is pressed'),
                child: Text('버튼 3'),
                style: ElevatedButton.styleFrom(
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                ),
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.only(
              left: 20,
            ),
            child: Divider(
              height: 20.0,
              color: Colors.grey[100],
              thickness: 0.0,
              endIndent: 20.0,
            ),
          ),





          // 첫번째 필터
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                onPressed: selectAllButtons,
                child: Text('전체 선택'),
              ),
              ElevatedButton(
                onPressed: resetSelection,
                child: Text('초기화'),
              ),
            ],
          ),


          Expanded(
            child: Container(
              child: GridView.builder(
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
                    height: 10,
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
                        primary: isSelected ? Colors.brown[300] : Colors.grey[300],
                        textStyle: TextStyle(
                          fontSize: 10,
                        ),
                      ),
                      child: Text(button),
                    ),
                  );
                },
              ),
            ),
          ),
          Text(
            '${clickList}',
            style: TextStyle(
              color: Colors.black,
              letterSpacing: 2.0,
              fontSize: 10,
              fontWeight: FontWeight.bold,
            ),
          ),
          SizedBox(height: 20),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                onPressed: selectAllTwoButtons,
                child: Text('two 전체 선택'),
              ),
              ElevatedButton(
                onPressed: resetSelectionTwo,
                child: Text('two 초기화'),
              ),
            ],
          ),
          Expanded(
            child: Container(
              child: GridView.builder(
                shrinkWrap: true,
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 5,
                  crossAxisSpacing: 5.0,
                  mainAxisSpacing: 5.0,
                ),
                itemCount: allButtonTwos.length,
                itemBuilder: (context, index) {
                  final button = allButtonTwos[index];
                  final isSelected = clickList_2.contains(button);

                  return Container(
                    height: 10,
                    child: ElevatedButton(
                      onPressed: () {
                        if (clickList_2.isEmpty) {
                          setState(() {
                            clickList_2.add(button);
                            startIndex = index;
                            endIndex = index;
                          });
                        } else {
                          if (startIndex != null && endIndex != null) {
                            int currentIndex = allButtonTwos.indexOf(button);

                            if (currentIndex < startIndex!) {
                              setState(() {
                                startIndex = currentIndex;
                                clickList_2.clear();
                                clickList_2.addAll(allButtonTwos.sublist(currentIndex, endIndex! + 1));
                              });
                            } else if (currentIndex > endIndex!) {
                              setState(() {
                                endIndex = currentIndex;
                                clickList_2.clear();
                                clickList_2.addAll(allButtonTwos.sublist(startIndex!, currentIndex + 1));
                              });
                            } else {
                              selectallButtonTwosBetween(startIndex!, endIndex!);
                            }
                          }
                        }
                      },
                      style: ElevatedButton.styleFrom(
                        primary: isSelected ? Colors.brown[300] : Colors.grey[300],
                        textStyle: TextStyle(
                          fontSize: 10,
                        ),
                      ),
                      child: Text(button),
                    ),
                  );
                },
              ),
            ),
          ),
          SizedBox(height: 20),
          Text(
            '${clickList_2}',
            style: TextStyle(
              color: Colors.black,
              letterSpacing: 2.0,
              fontSize: 10,
              fontWeight: FontWeight.bold,
            ),
          ),
          Center(
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Expanded(
                    child: TextField(
                      controller: _fromController,
                      keyboardType: TextInputType.number,
                      onChanged: (value) {
                        setState(() {
                          fromValue = int.tryParse(value);
                          startIndex = fromValue! - 1;
                          _toController.text = '${startIndex! + 1 ?? ''}';
                        });
                      },
                      decoration: InputDecoration(
                        hintText: '${(startIndex ?? 0) + 1}',
                        border: OutlineInputBorder(),
                      ),
                    ),
                  ),
                  Text(
                    '~',
                    style: TextStyle(
                      color: Colors.black,
                      letterSpacing: 2.0,
                      fontSize: 10,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Expanded(
                    child: TextField(
                      controller: _toController,
                      keyboardType: TextInputType.number,
                      onChanged: (value) {
                        setState(() {
                          toValue = int.tryParse(value);
                          endIndex = toValue! - 1;
                          _toController.text = '${endIndex! + 1 ?? ''}';
                        });
                      },
                      decoration: InputDecoration(
                        hintText: '${(endIndex ?? 0) + 1}',
                        border: OutlineInputBorder(),
                      ),
                    ),
                  ),
                  FloatingActionButton(
                    onPressed: () {
                      if (startIndex != null && endIndex != null) {
                        selectallButtonTwosBetween(startIndex!, endIndex!);
                      }
                    },
                    child: Icon(Icons.check),
                  ),
                ],
              ),
            ),
          ),

          SizedBox(height: 20),
        ],
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    home: Filter(),
  ));
}
