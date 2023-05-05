import 'package:ante_proyecto/modelos/Animales/Animale.dart';

import '../Page/PageInfo.dart';

class RespuestaAnimales {
  RespuestaAnimales({
    required this.list,
    required this.pageInfo,
  });

  List<Animales> list;
  PageInfo pageInfo;

  factory RespuestaAnimales.fromJson(Map<String, dynamic> json) =>
      RespuestaAnimales(
        list:
            List<Animales>.from(json["list"].map((x) => Animales.fromJson(x))),
        pageInfo: PageInfo.fromJson(json["pageInfo"]),
      );
}
