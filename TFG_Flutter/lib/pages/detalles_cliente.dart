import 'package:ante_proyecto/modelos/Citas/SolictudesCitas.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../modelos/modelos.dart';
import '../providers/ip_provider.dart';

class DetalleCliente extends StatelessWidget {
  const DetalleCliente({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var cliente = ModalRoute.of(context)!.settings.arguments as Clientes;
    return Scaffold(
      appBar: AppBar(
        title: Text('Detalles del cliente'),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              _InfoCliente(cliente: cliente),
              _InfoCitas(cliente: cliente),
            ],
          ),
        ),
      ),
    );
  }
}

class _InfoCliente extends StatelessWidget {
  final Clientes cliente;

  _InfoCliente({Key? key, required this.cliente}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        ImagenCliente(),
        SizedBox(height: 16.0),
        Text(
          'Nombre completo:',
          style: TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 16.0,
          ),
        ),
        Text(cliente.nombreCompleto!),
        SizedBox(height: 8.0),
        Text(
          'DNI:',
          style: TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 16.0,
          ),
        ),
        Text(cliente.dni!),
        SizedBox(height: 8.0),
        Text(
          'Teléfono:',
          style: TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 16.0,
          ),
        ),
        Text(cliente.telefono!),
        SizedBox(height: 8.0),
      ],
    );
  }
  Container ImagenCliente(){
    String ip=connectionProvider.ip;
    if(cliente.imagen==null){
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
    }else{
      return Container(
        width: double.infinity,
        height: 200,
        decoration: BoxDecoration(
          image: DecorationImage(
            fit: BoxFit.cover,
            image: NetworkImage('http://$ip/Images/${cliente.imagen!}'),
          ),
        ),
      );
    }
  }

}

class _InfoCitas extends StatelessWidget {
  final Clientes cliente;

  _InfoCitas({Key? key, required this.cliente}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: SolcitudesCitas()
          .obtenerCitasPorClientes(idCliente: cliente.idCliente!),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          List<Citas> listaCitasCompleta = snapshot.data as List<Citas>;
          listaCitasCompleta.sort((c1, c2) => c1.fecha!.compareTo(c2.fecha!));
          return SizedBox(
            height: 300,
            child: ListView.builder(
              itemCount: listaCitasCompleta.length,
              itemBuilder: (context, index) {
                return ListTile(
                  leading: Icon(Icons.calendar_today),
                  title: Row(
                    children: [
                      Text(DateFormat('dd/MM/yyyy - hh:MM')
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
