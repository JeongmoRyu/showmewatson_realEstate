import 'package:flutter/material.dart';

class prac16 extends StatefulWidget {
  const prac16({Key? key}) : super(key: key);

  @override
  _prac16State createState() => _prac16State();
}

class _prac16State extends State<prac16> {
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
                child: Text('Select All'),
              ),
              ElevatedButton(
                onPressed: () {
                  setState(() {
                    clickList.clear();
                  });
                },
                child: Text('Clear'),
              ),
            ],
          ),
          GridView.builder(
            shrinkWrap: true,
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 5,
              crossAxisSpacing: 8.0,
              mainAxisSpacing: 8.0,
            ),
            itemCount: allButtons.length,
            itemBuilder: (context, index) {
              final button = allButtons[index];
              final isSelected = clickList.contains(button);

              return ElevatedButton(
                onPressed: () {
                  if (clickList.isEmpty) {
                    setState(() {
                      clickList.add(button);
                    });
                  } else {
                    int startIndex = allButtons.indexOf(clickList.first);
                    int endIndex = allButtons.indexOf(clickList.last);
                    int currentIndex = allButtons.indexOf(button);

                    if (currentIndex >= startIndex && currentIndex <= endIndex) {
                      setState(() {
                        clickList.removeRange(1, clickList.length);
                      });
                    } else {
                      selectButtonsBetween(startIndex, currentIndex);
                    }
                  }
                },
                style: ElevatedButton.styleFrom(
                  primary: isSelected ? Colors.blue : Colors.grey,
                ),
                child: Text(button),
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
    home: prac16(),
  ));
}
