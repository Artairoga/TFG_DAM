import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/ip_provider.dart';

class SettingsPage extends StatelessWidget {
  final TextEditingController _ipController = TextEditingController();
  final TextEditingController _portController = TextEditingController();
  final TextEditingController _apiController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    final connectionProvider = Provider.of<ConnectionProvider>(context,
        listen: true); //provider de la conexion
    int connectionStatus =
        connectionProvider.ConnectionCode; //se obtiene el estado de la conexion
    //relleno los textfields con los datos actuales del provider
    //aunque la ip actual no funcione,se podria comprobar pero considero mejor asi
    _ipController.text = connectionProvider.ip;
    _portController.text = connectionProvider.port;
    return Scaffold(
      appBar: AppBar(
        title: const Text('Ajustes'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              const Text(
                'Configuración Servidor',
                style: TextStyle(fontSize: 18.0, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 16.0),
              Row(
                children: [
                  Expanded(
                    flex: 6,
                    child: TextField(
                      controller: _ipController,
                      decoration: const InputDecoration(
                        labelText: 'IP',
                        hintText: 'Ejemplo: 192.168.186.253',
                      ),
                    ),
                  ),
                  const SizedBox(width: 8.0),
                  Expanded(
                    flex: 3,
                    child: TextField(
                      controller: _portController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: 'Puerto',
                        hintText: 'Ejemplo: 8090',
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16.0),
              const SizedBox(height: 32.0),
              ElevatedButton(
                onPressed: () async {
                  //al darle al boton se comprueba la conexion con los nuevos datos
                  connectionStatus = await connectionProvider.changeConnection(
                      newIp: _ipController.text,
                      newPort: _portController.text);
                  showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: Text(connectionStatus==200
                            ? 'Conexión exitosa'
                            : 'Conexión fallida'),
                        content: Text(connectionStatus==200
                            ? 'La conexión con el servidor se ha establecido correctamente.'
                            : 'No se ha podido establecer la conexión con el servidor.'),
                        actions: [
                          TextButton(
                            onPressed: () {
                              Navigator.of(context).pop();
                            },
                            child: const Text('OK'),
                          ),
                        ],
                      );
                    },
                  );
                },
                child: const Text('Comprobar conexión'),
              ),
              const SizedBox(height: 16.0),
              if (connectionStatus == 200)
                const Text(
                  'La conexión con el servidor se ha establecido correctamente.',
                  style: TextStyle(color: Colors.green),
                )
              else if(connectionStatus==404)
                const Text(
                  'Servidor Inaccesible. Error 404',
                  style: TextStyle(color: Colors.red),
                )
              else if(connectionStatus==401)
                const Text(
                  'Api Incorrecta. Error 403',
                  style: TextStyle(color: Colors.red),
                )
              else
                Text(
                  'No se ha podido establecer la conexión con el servidor. Error $connectionStatus',
                  style: TextStyle(color: Colors.red),
                ),
              const SizedBox(height: 32.0),
            ],
          ),
        ),
      ),
    );
  }
}
