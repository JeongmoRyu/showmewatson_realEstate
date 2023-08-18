import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:prac2/base/navbar/dashboard_controller.dart';


class BottomNavigationWidget extends ConsumerStatefulWidget {
  const BottomNavigationWidget({super.key});

  @override
  ConsumerState<BottomNavigationWidget> createState() => _BottomNavigationWidgetState();
}

class _BottomNavigationWidgetState extends ConsumerState<BottomNavigationWidget> {
  @override
  Widget build(BuildContext context) {
    final position = ref.watch(dashboardControllerProvider);

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
    );
  }

  void _onTap(int index) {
    ref.read(dashboardControllerProvider.notifier).setPosition(index);

    switch (index) {
      case 0:
        context.go('/');
        break;
      case 1:
        context.go('/interest');
        break;
      case 2:
        context.go('/map');
        break;
      case 3:
        context.push('/directMessageLogin');
        break;
      case 4:
        context.go('/mypage');
        break;
      default:
    }
  }
}
