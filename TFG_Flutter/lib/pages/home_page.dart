import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/ip_provider.dart';
import '../widgets/custom_widget.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("PLUS VET"),
        centerTitle: true,
      ),
      body: Consumer<ConnectionProvider>(
        builder: (context, provider, child) {
          if (provider.ConnectionCode == 200) {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Bienvenido a Plus Vet",
                    style: TextStyle(fontSize: 20),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Text(
                    "¿Qué desea hacer?",
                    style: TextStyle(fontSize: 20),
                  ),
                ],
              ),
            );
          } else {
            return Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Problemas con la conexion al servidor",
                    style: TextStyle(fontSize: 20),
                  ),
                  SizedBox(
                    height: 20,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Por favor, compruebe los",
                        style: TextStyle(fontSize: 20),
                      ),
                      TextButton(
                        onPressed: () {
                          // Acción a realizar cuando se presiona el botón
                        },
                        child: Text(
                          'Ajustes',
                          style: TextStyle(fontSize: 20),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            );
          }
        },
      ),
      drawer: CustomDrawer(),
    );
  }
}
