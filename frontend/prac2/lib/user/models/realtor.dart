// 공인중개사 데이터 모델
class Realtor {
  final String license;
  final String realtorName;
  final int phoneNumber;
  final String authId;
  final int registId;
  final String address;
  final int? tel;

  Realtor({
    required this.license,
    required this.authId,
    required this.realtorName,
    required this.phoneNumber,
    required this.registId,
    required this.address,
    required this.tel,
  });

  factory Realtor.fromJson(Map<String, dynamic> json) {
    return Realtor(
      license: json['license'],
      authId: json['authId'],
      realtorName: json['realtorName'],
      phoneNumber: json['phoneNumber'],
      registId: json['registId'],
      address: json['address'],
      tel: json['tel'],
    );
  }
}