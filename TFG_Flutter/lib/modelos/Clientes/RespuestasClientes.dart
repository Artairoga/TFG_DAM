import '../Page/PageInfo.dart';
import 'Cliente.dart';

class RespuestaClientes {
  RespuestaClientes({
    required this.list,
    required this.pageInfo,
  });

  List<Clientes> list;
  PageInfo pageInfo;

  factory RespuestaClientes.fromJson(Map<String, dynamic> json) =>
      RespuestaClientes(
        list:
            List<Clientes>.from(json["list"].map((x) => Clientes.fromJson(x))),
        pageInfo: PageInfo.fromJson(json["pageInfo"]),
      );
}
