class Detail {
  final String? sidoName;
  final String? gunguName;
  final String? dongleeName;
  final int houseId;
  final int? houseCode;
  final double? squareMeter;
  final double? supplyAreaMeter;
  final int? floor;
  final String? address;
  final int? deposit;
  final int? monthlyRent;
  final int? salePrice;
  final int? maintenance;
  final String? title;
  final String? status;
  final List<String> fileNames;
  final Map<String, dynamic>? realtor;
  final String? maintenanceList;
  final int? contractCode;
  final int? totalFloor;
  final String? buildingUse;
  final String? approvalBuildingDate;
  final int? bathroom;
  final int? room;
  final String? content;
  final String? regDate;
  final HouseOption? houseOption;

  Detail({
    required this.sidoName,
    required this.gunguName,
    required this.dongleeName,
    required this.houseId,
    required this.houseCode,
    required this.squareMeter,
    required this.supplyAreaMeter,
    required this.floor,
    required this.address,
    this.deposit,
    this.monthlyRent,
    this.salePrice,
    required this.maintenance,
    required this.title,
    required this.status,
    required this.fileNames,
    required this.realtor,
    required this.maintenanceList,
    required this.contractCode,
    required this.totalFloor,
    required this.buildingUse,
    required this.approvalBuildingDate,
    required this.bathroom,
    required this.room,
    required this.content,
    required this.regDate,
    required this.houseOption,
  });

  factory Detail.fromJson(Map<String, dynamic> json) {
    return Detail(
      sidoName: json['sidoName'],
      gunguName: json['gunguName'],
      dongleeName: json['dongleeName'],
      houseId: json['houseId'],
      houseCode: json['houseCode'],
      squareMeter: json['squareMeter'].toDouble(),
      supplyAreaMeter: json['supplyAreaMeter'].toDouble(),
      floor: json['floor'],
      address: json['address'],
      deposit: json['deposit'] as int?,
      monthlyRent: json['monthlyRent'] as int?,
      salePrice: json['salePrice'] as int?,
      maintenance: json['maintenance'],
      title: json['title'],
      status: json['status'],
      fileNames: List<String>.from(json['fileNames']),
      realtor: json['realtor'],
      maintenanceList: json['maintenanceList'],
      contractCode: json['contractCode'],
      totalFloor: json['totalFloor'],
      buildingUse: json['buildingUse'],
      approvalBuildingDate: json['approvalBuildingDate'],
      bathroom: json['bathroom'],
      room: json['room'],
      content: json['content'],
      regDate: json['regDate'],
      houseOption: HouseOption.fromJson(json['houseOption']),
    );
  }
}

class HouseOption {
  final bool sink;
  final bool airConditioner;
  final bool shoeCloset;
  final bool washingMachine;
  final bool refrigerator;
  final bool closet;
  final bool induction;
  final bool desk;
  final bool elevator;
  final bool coldHeating;
  final bool parking;

  HouseOption({
    required this.sink,
    required this.airConditioner,
    required this.shoeCloset,
    required this.washingMachine,
    required this.refrigerator,
    required this.closet,
    required this.induction,
    required this.desk,
    required this.elevator,
    required this.coldHeating,
    required this.parking,
  });

  factory HouseOption.fromJson(Map<String, dynamic> json) {
    return HouseOption(
      sink: json['sink'],
      airConditioner: json['airConditioner'],
      shoeCloset: json['shoeCloset'],
      washingMachine: json['washingMachine'],
      refrigerator: json['refrigerator'],
      closet: json['closet'],
      induction: json['induction'],
      desk: json['desk'],
      elevator: json['elevator'],
      coldHeating: json['coldHeating'],
      parking: json['parking'],
    );
  }
}
