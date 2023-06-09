import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/modelos.dart';
import '../pages/pages.dart';

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
            listaCitas.sort((c1, c2) => c1.fecha!.compareTo(c2.fecha!));
            return ListView.builder(
              itemCount: listaCitas.length,
              itemBuilder: (BuildContext context, int index) {
                Citas cita = listaCitas[index];
                return ListTile(
                  title: Text(
                      (DateFormat('dd/MM/yyyy HH:mm').format(cita.fecha!))),
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
