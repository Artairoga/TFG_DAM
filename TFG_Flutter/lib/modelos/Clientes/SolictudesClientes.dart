import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:provider/provider.dart';

import '../../providers/ip_provider.dart';
import 'Cliente.dart';
import 'RespuestasClientes.dart';

class SolcitudesClientes {
  final apikey =connectionProvider.apiKey;
  final ip = connectionProvider.ip;
  final port = connectionProvider.port;
  Future<List<Clientes>> listaClientes() async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_z1cj1saiajqqvd/Clientes/views/Clientes');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      List<Clientes> clientes =
          RespuestaClientes.fromJson(jsonDecode(response.body)).list;
      return clientes;
    } else {
      print(response.statusCode);
      return [];
    }
  }

  Future<Clientes> obtenerCliente({required int idCliente}) async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_z1cj1saiajqqvd/Clientes/views/Clientes?where=(IdCliente,eq,$idCliente)');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      Clientes cliente =
          RespuestaClientes.fromJson(jsonDecode(response.body)).list.single;
      return cliente;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar las citas');
    }
  }
}
