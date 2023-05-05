import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/Citas/SolictudesCitas.dart';
import '../modelos/modelos.dart';

class AnimalPage extends StatelessWidget {
  const AnimalPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var animal = ModalRoute.of(context)!.settings.arguments as Animales;
    return Scaffold(
      appBar: AppBar(
        title: Text(animal.tipoAnimal!),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _InfoAnimal(animal: animal),
              _InfoCitas(animal: animal)
            ],
          ),
        ),
      ),
    );
  }
}
class _InfoAnimal extends StatelessWidget {
  final Animales animal;
  _InfoAnimal({Key? key,required this.animal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          width: double.infinity,
          height: 200,
          decoration: BoxDecoration(
            image: DecorationImage(
              fit: BoxFit.cover,
              image: NetworkImage("https://picsum.photos/id/237/200/300/"),
            ),
          ),
        ),
        SizedBox(height: 16),
        Text(
          'Características:',
          style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        SizedBox(height: 8),
        Text(
          animal.caracteristicas!,
          style: TextStyle(fontSize: 16),
        ),
        SizedBox(height: 16),
        Text(
          'Dueño:',
          style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        SizedBox(height: 8),
        Text(
          "NombreCompletoDueño",
          style: TextStyle(fontSize: 16),
        ),
        SizedBox(height: 16),
        Text(
          'Teléfono:',
          style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        SizedBox(height: 8),
        Text(
          "TelefonoDueño",
          style: TextStyle(fontSize: 16),
        ),
        SizedBox(height: 16),
      ],
    );
  }
}

class _InfoCitas extends StatelessWidget {
  final Animales animal;

  _InfoCitas({Key? key, required this.animal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: SolcitudesCitas().obtenerCitasPorAnimales(idAnimal: animal.idAnimal!),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          List<Citas> listaCitasCompleta = snapshot.data as List<Citas>;
          listaCitasCompleta.sort((c1, c2) => c1.fecha!.compareTo(c2.fecha!));
          return SizedBox(
            height: 280,
            child: ListView.builder(
              itemCount: listaCitasCompleta.length,
              itemBuilder: (context, index) {
                return ListTile(
                  leading: Icon(Icons.calendar_today),
                  title: Column(
                    children: [
                      Text(DateFormat('dd/MM/yyyy HH:mm').format(listaCitasCompleta[index].fecha!)),
                      SizedBox(width: 8.0),
                      Text(listaCitasCompleta[index].pendiente==0! ? 'Pendiente' : 'Realizada'),
                    ],
                  ),
                  trailing: Icon(Icons.arrow_forward),
                );
              },
            ),
          );
        } else {
          return Center(
            child: CircularProgressIndicator(),
          );
        }
      },
    );
  }
}
