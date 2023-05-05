import 'package:ante_proyecto/modelos/Citas/SolictudesCitas.dart';
import 'package:ante_proyecto/pages/animal_page.dart';
import 'package:ante_proyecto/pages/detalles_cita.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/modelos.dart';

class CitasPage extends StatelessWidget {
  const CitasPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    TextEditingController searchController = TextEditingController();
    return Scaffold(
      appBar: AppBar(
        title: const Text('Citas'),
      ),
      body: FutureBuilder(
        future: SolcitudesCitas().listaCitas(),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.done &&
              snapshot.hasData) {
            List<Citas> listaCitas = snapshot.data as List<Citas>;
            return ListView.builder(
              itemCount: listaCitas.length,
              itemBuilder: (BuildContext context, int index) {
                Citas cita = listaCitas[index];
                return ListTile(
                  title:
                      Text(DateFormat('HH:mm dd/MM/yyyy ').format(cita.fecha!)),
                  subtitle: Text(cita.descripcion!),
                  trailing: Icon(Icons.arrow_forward),
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) {
                              return DetallesCita();
                            },
                            settings: RouteSettings(arguments: cita!)));

                    // Navegar a la página de detalles del animal
                  },
                );
              },
            );
          } else {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      ),
    );
  }
}
