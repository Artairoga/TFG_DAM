import '../Page/PageInfo.dart';
import 'Cita.dart';

class RespuestaCitas {
  RespuestaCitas({
    required this.list,
    required this.pageInfo,
  });

  List<Citas> list;
  PageInfo pageInfo;

  factory RespuestaCitas.fromJson(Map<String, dynamic> json) =>
    RespuestaCitas(
        list:
        List<Citas>.from(json["list"].map((x) => Citas.fromJson(x))),
        pageInfo: PageInfo.fromJson(json["pageInfo"]),
      );
}
