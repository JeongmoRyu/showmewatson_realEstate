// 공지 데이터 모델
class Notice {
  final int id;
  final String? realtorId;
  final int houseId;
  final String liveDate;
  final String? content;
  final String? regDate;

  Notice({
    required this.id,
    required this.realtorId,
    required this.houseId,
    required this.liveDate,
    required this.content,
    required this.regDate,
  });

  factory Notice.fromJson(Map<String, dynamic> json) {
    return Notice(
      id: json['id'],
      realtorId: json['realtorId'],
      houseId: json['houseId'],
      liveDate: json['liveDate'],
      content: json['content'],
      regDate: json['regDate'],
    );
  }
}

String formatLiveDate(String liveDateStr) {
  DateTime liveDate = DateTime.parse(liveDateStr);
  String month = liveDate.month.toString().padLeft(2);
  String day = liveDate.day.toString().padLeft(2);
  String hour = liveDate.hour.toString().padLeft(2);
  String minute = liveDate.minute.toString().padLeft(2);
  return '$month월 $day일 $hour시 $minute분';
}