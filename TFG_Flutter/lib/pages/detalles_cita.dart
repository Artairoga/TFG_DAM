import 'package:ante_proyecto/modelos/Animales/SolictudesAnimales.dart';
import 'package:ante_proyecto/modelos/Clientes/SolictudesClientes.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/Animales/Animale.dart';
import '../modelos/Citas/Cita.dart';
import '../modelos/Clientes/Cliente.dart';

class DetallesCita extends StatelessWidget {
  const DetallesCita({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    var cita = ModalRoute.of(context)!.settings.arguments as Citas;
    /*TODO aqui en base a la cita que nos llega,
        debemos de hacer una consulta a la base de datos
        para obtener el cliente y el animal
        Tambien deberia de cambiar el check del pendiente a que solo eso sea
        stateful
  */
    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles de la cita'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            _InfoCliente(cita: cita),
            SizedBox(height: 10),
            _InfoAnimal(cita: cita),
            SizedBox(height: 10),
            _InfoCita(cita: cita),
          ],
        ),
      ),
    );
  }
}

class _InfoCliente extends StatelessWidget {
  _InfoCliente({Key? key, required this.cita}) : super(key: key);
  Citas cita;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: SolcitudesClientes().obtenerCliente(idCliente: cita.id_clientes!),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
        Clientes cliente = snapshot.data as Clientes;
          return Column(
            children: [
              Row(
                children: [
                  Text(
                    'Dueño: ',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  Text(
                    '${cliente.nombreCompleto}',
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
                    '${cliente.telefono}',
                    style: TextStyle(fontSize: 18),
                  )
                ],
              )
            ],
          );
        }
        return Container();
      },
    );
  }
}

class _InfoAnimal extends StatelessWidget {
  _InfoAnimal({Key? key, required this.cita}) : super(key: key);
  Citas cita;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: SolcitudesAnimales().obtenerAnimal(idAnimal: cita.id_animales!),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
        Animales animal = snapshot.data as Animales;
          return Row(
            children: [
              Text(
                'Nombre del animal: ',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              Text(
                '${animal.tipoAnimal}',
                style: TextStyle(fontSize: 18),
              )
            ],
          );
        }
        return Container();
      },
    );
  }
}

class _InfoCita extends StatefulWidget {
  _InfoCita({Key? key, required this.cita}) : super(key: key);
  Citas cita;

  @override
  State<_InfoCita> createState() => _InfoCitaState();
}

class _InfoCitaState extends State<_InfoCita> {
  bool _isPendiente = false;
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Row(
          children: [
            Text(
              'Fecha: ',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            Text(
              '${DateFormat('dd/MM/yyyy ').format(widget.cita.fecha!)}',
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
              '${widget.cita.horaInicio}',
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
          widget.cita.descripcion!,
          style: TextStyle(fontSize: 16),
        )
      ],
    );
  }
}
