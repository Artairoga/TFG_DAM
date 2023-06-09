import 'package:ante_proyecto/providers/ip_provider.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/modelos.dart';

class AnimalPage extends StatelessWidget {
  const AnimalPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var animal = ModalRoute.of(context)!.settings.arguments as Animales;
    return Scaffold(
      appBar: AppBar(
        title: Text(animal.nombreAnimal!),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [_InfoAnimal(animal: animal), _InfoCitas(animal: animal)],
          ),
        ),
      ),
    );
  }
}

class _InfoAnimal extends StatelessWidget {
  final Animales animal;

  _InfoAnimal({Key? key, required this.animal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        ImagenAnimal(),
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
        _InfoCliente(animal: animal,)
      ],
    );
  }

  Container ImagenAnimal() {
    String ip = connectionProvider.ip;
    if (animal.imagen == null) {
      return Container(
        width: double.infinity,
        height: 200,
        color: Colors.grey[200], // Color de fondo gris claro
        child: Center(
          child: Text(
            'No Image',
            style: TextStyle(
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
      );
    } else {
      return Container(
        width: double.infinity,
        height: 200,
        decoration: BoxDecoration(
          image: DecorationImage(
            fit: BoxFit.cover,
            image: NetworkImage('http://$ip/Images/${animal.imagen!}'),
          ),
        ),
      );
    }
  }
}

class _InfoCitas extends StatelessWidget {
  final Animales animal;

  _InfoCitas({Key? key, required this.animal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future:
          SolcitudesCitas().obtenerCitasPorAnimales(idAnimal: animal.idAnimal!),
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
                      Text(DateFormat('dd/MM/yyyy HH:mm')
                          .format(listaCitasCompleta[index].fecha!)),
                      SizedBox(width: 8.0),
                      Text(listaCitasCompleta[index].pendiente == 0!
                          ? 'Pendiente'
                          : 'Realizada'),
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
class _InfoCliente extends StatelessWidget {
  final Animales animal;
  _InfoCliente({Key? key,required this.animal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: SolcitudesClientes().obtenerCliente(idCliente: animal.id_clientes!),
      builder: (context, snapshot) {
        if(snapshot.hasData) {
          Clientes cliente = snapshot.data as Clientes;
          return Column(
            crossAxisAlignment: CrossAxisAlignment.start, // Alineación a la izquierda
            children: [
              Text(
                'Dueño:',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 8),
              Text(
                cliente.nombreCompleto!,
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 16),
              Text(
                'Teléfono:',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              SizedBox(height: 8),
              Text(
                cliente.telefono!,
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 16),
            ],
          );
        } else {
          return Container();
        }
      },
    );
  }
}

