import 'package:flutter/material.dart';
import 'estate_bottom_navigation_widget.dart';


class EstateDashboardScreen extends StatefulWidget {
  final Widget child;
  const EstateDashboardScreen({required this.child, Key? key}) : super(key: key);

  @override
  State<EstateDashboardScreen> createState() => _EstateDashboardScreenState();
}

class _EstateDashboardScreenState extends State<EstateDashboardScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: widget.child,
      bottomNavigationBar: const EstateBottomNavigationWidget(),
    );
  }
}
