class House {
  final int id;
  final int houseCode;
  final double? squareMeter;
  final double? supplyAreaMeter;
  final int? floor;
  final String? address;
  final int? deposit;
  final int? monthlyRent;
  final int? salePrice; // nullable type because the value can be null
  final int maintenance;
  final String? title;
  final String? status;
  final List<String>? fileNames;
  final String? sidoName;
  final String? gunguName;
  final String? dongleeName;
  final bool wished;

  House({
    required this.id,
    required this.houseCode,
    required this.squareMeter,
    required this.supplyAreaMeter,
    required this.floor,
    required this.address,
    required this.deposit,
    required this.monthlyRent,
    this.salePrice,
    required this.maintenance,
    required this.title,
    required this.status,
    required this.fileNames,
    required this.sidoName,
    required this.gunguName,
    required this.dongleeName,
    required this.wished,
  });

  factory House.fromJson(Map<String, dynamic> json) {
    return House(
      id: json['id'],
      houseCode: json['houseCode'],
      squareMeter: json['squareMeter'].toDouble(),
      supplyAreaMeter: json['supplyAreaMeter'].toDouble(),
      floor: json['floor'],
      address: json['address'],
      deposit: json['deposit'],
      monthlyRent: json['monthlyRent'],
      salePrice: json['salePrice'],
      maintenance: json['maintenance'],
      title: json['title'],
      status: json['status'],
      fileNames: List<String>.from(json['fileNames']),
      sidoName: json['sidoName'],
      gunguName: json['gunguName'],
      dongleeName: json['dongleeName'],
      wished: json['wished'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'houseCode': houseCode,
      'squareMeter': squareMeter,
      'supplyAreaMeter': supplyAreaMeter,
      'floor': floor,
      'address': address,
      'deposit': deposit,
      'monthlyRent': monthlyRent,
      'salePrice': salePrice,
      'maintenance': maintenance,
      'title': title,
      'status': status,
      'fileNames': fileNames,
      'sidoName': sidoName,
      'gunguName': gunguName,
      'dongleeName': dongleeName,
      'wished': wished,
    };
  }
}
