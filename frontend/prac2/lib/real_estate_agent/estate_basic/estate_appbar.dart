import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:provider/provider.dart';

import 'package:prac2/states/user_auth_provider.dart';
import 'package:prac2/login/login_platform.dart';

class EstateCustomAppBar extends StatelessWidget implements PreferredSizeWidget {


  EstateCustomAppBar({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Color(0xFFDCBF97),
      leading: IconButton(
        icon: Image.asset('assets/images/app_icon.png'),
        onPressed: () => context.go('/estate_home'),
      ),
      actions: [
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
