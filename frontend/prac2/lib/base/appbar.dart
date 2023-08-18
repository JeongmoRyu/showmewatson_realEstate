import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';
import 'package:badges/badges.dart' as badges;

import 'package:prac2/states/user_auth_provider.dart';
import 'package:prac2/login/login_platform.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  final VoidCallback onLeadingPressed;
  final VoidCallback? onEstateHomePressed;
  final bool showEstateHomeButton;
  final List<Widget>? actions;

  CustomAppBar({
    Key? key,
    required this.onLeadingPressed,
    this.onEstateHomePressed,
    this.showEstateHomeButton = false,
    this.actions,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Color(0xFFDCBF97),
      leading: IconButton(
        icon: Image.asset('assets/images/app_icon.png'),
        onPressed: onLeadingPressed,
      ),
      actions: [
        // Badges
        Center(
          child: badges.Badge(
            position: badges.BadgePosition.topEnd(top: 5, end: 5),
            badgeAnimation: const badges.BadgeAnimation.rotation(
              animationDuration: Duration(milliseconds: 300),
              colorChangeAnimationDuration: Duration(seconds: 1),
              loopAnimation: false,
              curve: Curves.fastOutSlowIn,
              colorChangeAnimationCurve: Curves.easeInCubic,
            ),
            badgeContent: Text(
              '7',
              style: TextStyle(color: Colors.white),
            ),
            child: IconButton(
                icon: Icon(FontAwesomeIcons.bell),
                onPressed: () => context.push('/notification')),
          ),
        ),


        if (showEstateHomeButton) // 조건부로 버튼을 추가합니다.
          TextButton(
            onPressed: onEstateHomePressed ?? () {},
            child: Text('중개인용', style: TextStyle(color: Colors.white)),
          ),

        Consumer<UserAuthProvider>(
          builder: (context, authProvider, _) {
            if (authProvider.loginPlatform == LoginPlatform.none) {
              return TextButton(
                onPressed: () {
                  // Here you should call your login logic
                  // After successful login, call authProvider.login()
                  // Example: authProvider.login(LoginPlatform.google);
                  context.push('/login');
                },
                child: Text('Login', style: TextStyle(color: Colors.white)),
              );
            } else {
              return TextButton(
                onPressed: () {
                  authProvider.logout();
                },
                child: Text('Logout', style: TextStyle(color: Colors.white)),
              );
            }
          },
        ),
      ],
    );
  }

  @override
  Size get preferredSize => Size.fromHeight(kToolbarHeight);
}
