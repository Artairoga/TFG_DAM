import 'dart:convert';

import 'package:ante_proyecto/modelos/Animales/Animale.dart';
import 'package:http/http.dart' as http;

import '../../providers/ip_provider.dart';
import 'RespuestasAnimales.dart';

class SolcitudesAnimales {
  final apikey = connectionProvider.apiKey;
  final ip = connectionProvider.ip;
  final port = connectionProvider.port;

  Future<List<Animales>> listaAnimales() async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_z1cj1saiajqqvd/Animales/views/Animales');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      List<Animales> listaAnimales =
          RespuestaAnimales.fromJson(jsonDecode(response.body)).list;
      return listaAnimales;
    } else {
      print(response.statusCode);
      return [];
    }
  }

  Future<Animales> obtenerAnimal({required int idAnimal}) async {
    final url = Uri.parse(
        'http://$ip:$port/api/v1/db/data/noco/p_z1cj1saiajqqvd/Animales/views/Animales?where=(IdAnimal,eq,$idAnimal)');
    final headers = {'xc-token': apikey};
    final response = await http.get(url, headers: headers);
    if (response.statusCode == 200) {
      Animales animal =
          RespuestaAnimales.fromJson(jsonDecode(response.body)).list.single;
      return animal;
    } else {
      print(response.statusCode);
      throw Exception('Error al cargar el animal');
    }
  }
}
