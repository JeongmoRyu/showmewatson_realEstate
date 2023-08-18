import 'package:flutter_riverpod/flutter_riverpod.dart';

final EstatedashboardControllerProvider = StateNotifierProvider<EstateDashboardController, int>((ref) {
  return EstateDashboardController(0);
});

class EstateDashboardController extends StateNotifier <int> {
  EstateDashboardController(super.data);

  void setPosition(int value) {
    state = value;
  }
}