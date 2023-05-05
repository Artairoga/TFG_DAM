import 'package:ante_proyecto/modelos/Animales/Animales.dart';
import 'package:ante_proyecto/pages/animal_page.dart';
import 'package:ante_proyecto/pages/lista_animales_page.dart';
import 'package:ante_proyecto/pages/lista_clientes_page.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'pages/pages.dart';
import 'providers/ip_provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => connectionProvider),
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        initialRoute: "HomePage",
        routes: {
          "HomePage": (context) => HomePage(),
          "PetList": (context) => ListaAnimales(),
          "ClientList": (context) => ListaClientes(),
          "Citas": (context) => CitasPage(),
          "Settings": (context) => SettingsPage(),
        },
      ),
    );
  }
}
