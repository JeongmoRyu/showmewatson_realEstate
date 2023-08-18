import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import 'estate_dashboard_controller.dart';


class EstateBottomNavigationWidget extends ConsumerStatefulWidget {
  const EstateBottomNavigationWidget({super.key});

  @override
  ConsumerState<EstateBottomNavigationWidget> createState() => _EstateBottomNavigationWidgetState();
}

class _EstateBottomNavigationWidgetState extends ConsumerState<EstateBottomNavigationWidget> {
  @override
  Widget build(BuildContext context) {
    final position = ref.watch(EstatedashboardControllerProvider);

    return BottomNavigationBar(
      type: BottomNavigationBarType.fixed,
      backgroundColor: Color(0xFFDCBF97),
      selectedItemColor: Colors.white, // 선택된 아이템의 색상
      unselectedItemColor: Colors.black, // 선택되지 않은 아이템의 색상

      currentIndex : position,

      onTap: (value) => _onTap(value),

      items: const [
        BottomNavigationBarItem(
            icon: Icon(FontAwesomeIcons.house),
            label: 'Home'
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
    );
  }

  void _onTap(int index) {
    ref.read(EstatedashboardControllerProvider.notifier).setPosition(index);

    switch (index) {
      case 0:
        context.go('/estate_home');
        break;
      case 1:
        context.go('/estate_chatlist');
        break;
      case 2:
        context.go('/estate_mypage');
        break;
      default:
    }
  }
}
