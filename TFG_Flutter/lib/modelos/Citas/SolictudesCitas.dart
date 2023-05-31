import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../providers/ip_provider.dart';
import 'Cita.dart';
import 'RespuestasCitas.dart';

class SolcitudesCitas {
  final apikey = connectionProvider.apiKey;
  final ip = connectionProvider.ip;
  final port = connectionProvider.port;

  Future<List<Citas>> listaCitas() async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_n964z0esixbmcl/Citas/views/Citas');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      List<Citas> citas =
          RespuestaCitas.fromJson(jsonDecode(response.body)).list;
      return citas;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar las citas');
    }
  }

  Future<Citas> obtenerCita({required int idCita}) async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_n964z0esixbmcl/Citas/views/Citas?where=(IdCita,eq,$idCita)');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      print(response.body);
      Citas cita =
          RespuestaCitas.fromJson(jsonDecode(response.body)).list.single;
      return cita;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar la cita');
    }
  }

  Future<List<Citas>> obtenerCitasPorClientes({required int idCliente}) async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_n964z0esixbmcl/Citas/views/Citas?where=(IdCliente,eq,$idCliente)');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      List<Citas> citas =
          RespuestaCitas.fromJson(jsonDecode(response.body)).list;
      return citas;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar las citas');
    }
  }

  Future<List<Citas>> obtenerCitasPorAnimales({required int idAnimal}) async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_n964z0esixbmcl/Citas/views/Citas?where=(IdAnimal,eq,$idAnimal)');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      List<Citas> citas =
          RespuestaCitas.fromJson(jsonDecode(response.body)).list;
      return citas;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar las citas');
    }
  }
}
