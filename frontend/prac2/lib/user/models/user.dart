// 유저 데이터 모델
class User {
  final String nickname;
  final String authId;
  final String FCMToken;

  User({
    required this.nickname,
    required this.authId,
    required this.FCMToken,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      nickname: json['nickname'],
      authId: json['authId'],
      FCMToken: json['FCMToken'],
    );
  }
}