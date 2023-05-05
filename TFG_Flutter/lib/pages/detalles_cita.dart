import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/Animales/Animales.dart';
import '../modelos/Citas/Citas.dart';
import '../modelos/Clientes/Clientes.dart';

class DetallesCita extends StatefulWidget {
  const DetallesCita({Key? key}) : super(key: key);

  @override
  State<DetallesCita> createState() => _DetallesCitaState();
}

class _DetallesCitaState extends State<DetallesCita> {
  late bool _isPendiente = true;

  @override
  Widget build(BuildContext context) {
    var cita = ModalRoute.of(context)!.settings.arguments as Citas;
    final clientePrueba = Clientes(
      idCliente: 1,
      dni: '12345678A',
      nombreCompleto: 'Juan Pérez',
      telefono: '555123456',
      imagen: 'https://i.pravatar.cc/300?img=10',
    );

// Animal de prueba
    final animalPrueba = Animales(
      idAnimal: 1,
      id_clientes: 1,
      tipoAnimal: 'Perro',
      caracteristicas: 'Raza: Labrador Retriever, Edad: 3 años',
      imagen: 'https://i.pravatar.cc/300?img=30',
    );
    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles de la cita'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Text(
                  'Dueño: ',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Text(
                  '${clientePrueba.nombreCompleto}',
                  style: TextStyle(fontSize: 18),
                )
              ],
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Text(
                  'Teléfono: ',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Text(
                  '${clientePrueba.telefono}',
                  style: TextStyle(fontSize: 18),
                )
              ],
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Text(
                  'Nombre del animal: ',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Text(
                  '${animalPrueba.tipoAnimal}',
                  style: TextStyle(fontSize: 18),
                )
              ],
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Text(
                  'Fecha: ',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Text(
                  '${DateFormat('dd/MM/yyyy ').format(cita.fecha!)}',
                  style: TextStyle(fontSize: 18),
                )
              ],
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Text(
                  'Hora de inicio: ',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
                Text(
                  '${cita.horaInicio}',
                  style: TextStyle(fontSize: 18),
                )
              ],
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Text(
                  'Pendiente: ',
                  style: TextStyle(fontSize: 16),
                ),
                Checkbox(
                  value: _isPendiente,
                  onChanged: (bool? value) {
                    setState(() {
                      _isPendiente = value!;
                    });
                  },
                ),
              ],
            ),
            SizedBox(height: 10),
            Text(
              'Descripción:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10),
            Text(
              cita.descripcion!,
              style: TextStyle(fontSize: 16),
            ),
          ],
        ),
      ),
    );
  }
}
