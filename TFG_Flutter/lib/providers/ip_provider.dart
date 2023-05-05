import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

ConnectionProvider connectionProvider = ConnectionProvider._();
class ConnectionProvider extends ChangeNotifier {
  String _ip = '192.168.102.253';
  String _port = '8090';
  String _apiKey = '__ZoB_khecUj4pVOKcJKB4FJ91L1Ec-_etLpA4mH';

  int _ConnectionCode = 200;
  SharedPreferences? prefs = null;

  ConnectionProvider._() {
    _loadFromPrefs();
  }

  Future<int> changeConnection(
      {required String newIp, required String newPort}) async {
    try {
      final url = Uri.parse(
          'http://$newIp:$newPort');
      final response = await http.get(url).timeout(Duration(seconds: 5));
      _ConnectionCode = response.statusCode;
      _ip = newIp;
      _port = newPort;
    } catch (e) {
      print(e);
      _ConnectionCode=404;
    }
    notifyListeners();
    await _safeToPrefs();
    return _ConnectionCode;
  }

  Future<void> checkConnection() async {
    try {
      final response = await http
          .get(Uri.parse('http://$ip:$port'))
          .timeout(Duration(seconds: 5));
      _ConnectionCode = response.statusCode;
    } catch (e) {
      print(e);
      _ConnectionCode=404;
    }
    notifyListeners();
  }

  //carga las preferencias
  _loadFromPrefs() async {
    await _initPreferences();
    _ip = prefs!.getString('ip') ?? _ip;
    _port = prefs!.getString('port') ?? _port;
    _apiKey = prefs!.getString('apiKey') ?? _apiKey;
    await checkConnection();
    notifyListeners();
  }

  //guarda las preferencias
  _safeToPrefs() async {
    await _initPreferences();
    await prefs!.setString('ip', _ip);
    await prefs!.setString('port', _port);
    await prefs!.setString('apiKey', _apiKey);
  }

  //inicializa las preferencias
  _initPreferences() async {
    if (prefs == null) {
      prefs = await SharedPreferences.getInstance();
    }
  }

  //getters y setters
  String get ip => _ip;

  set ip(String value) {
    _ip = value;
  }

  String get port => _port;

  set port(String value) {
    _port = value;
  }


  int get ConnectionCode => _ConnectionCode;

  set ConnectionCode(int value) {
    _ConnectionCode = value;
  }

  String get apiKey => _apiKey;

  set apiKey(String value) {
    _apiKey = value;
  }
}
