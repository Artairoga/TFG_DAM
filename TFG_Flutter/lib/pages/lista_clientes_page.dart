import 'package:ante_proyecto/modelos/Clientes/SolictudesClientes.dart';
import 'package:ante_proyecto/pages/detalle_cliente.dart';
import 'package:flutter/material.dart';

import '../modelos/modelos.dart';

class ListaClientes extends StatelessWidget {
  const ListaClientes({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    TextEditingController searchController = TextEditingController();
    return FutureBuilder<List<Clientes>>(
      future: SolcitudesClientes().listaClientes(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.done) {
          List<Clientes> listaCientes = snapshot.data as List<Clientes>;
          return Scaffold(
            appBar: AppBar(
              title: Text('Lista de clientes'),
              actions: [
                IconButton(
                  onPressed: () {
                    showSearch(
                        context: context,
                        delegate: ClientSearch(listaCientes, searchController));
                  },
                  icon: Icon(Icons.search),
                )
              ],
            ),
            body: ListView.builder(
              itemCount: listaCientes.length,
              itemBuilder: (BuildContext context, int index) {
                Clientes cliente = listaCientes[index];
                return ListTile(
                  leading: cliente.imagen!.isNotEmpty
                      ? CircleAvatar(
                          backgroundImage: NetworkImage(cliente.imagen!),
                        )
                      : const CircleAvatar(
                          child: Icon(Icons.person),
                        ),
                  title: Text(cliente.nombreCompleto!),
                  subtitle: Text(cliente.dni!),
                  trailing: Icon(Icons.arrow_forward),
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) {
                              return DetalleCliente();
                            },
                            settings: RouteSettings(arguments: cliente!)));
                    // Navegar a la página de detalles del cliente
                  },
                );
              },
            ),
          );
        } else {
          return Scaffold(
            appBar: AppBar(
              title: Text('Lista de Clientes'),
            ),
            body: const Center(
              child: CircularProgressIndicator(),
            ),
          );
        }
      },
    );
  }
}

class ClientSearch extends SearchDelegate {
  final List<Clientes> clientes;
  final TextEditingController searchController;

  ClientSearch(this.clientes, this.searchController);

  @override
  List<Widget> buildActions(BuildContext context) {
    return [
      IconButton(
        icon: Icon(Icons.clear),
        onPressed: () {
          query = '';
          searchController.clear();
        },
      ),
    ];
  }

  @override
  Widget buildLeading(BuildContext context) {
    return IconButton(
      icon: Icon(Icons.arrow_back),
      onPressed: () {
        close(context, null);
      },
    );
  }

  @override
  Widget buildResults(BuildContext context) {
    List<Clientes> results = clientes
        .where((cliente) =>
            cliente.nombreCompleto!.toLowerCase().contains(query.toLowerCase()))
        .toList();

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (BuildContext context, int index) {
        Clientes cliente = results[index];
        return ListTile(
          leading: cliente.imagen!.isNotEmpty
              ? CircleAvatar(
                  backgroundImage: NetworkImage(cliente.imagen!),
                )
              : const CircleAvatar(
                  child: Icon(Icons.person),
                ),
          title: Text(cliente.nombreCompleto!),
          subtitle: Text(cliente.dni!),
          trailing: Icon(Icons.arrow_forward),
          onTap: () {
            // Navegar a la página de detalles del cliente
          },
        );
      },
    );
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    List<Clientes> results = clientes
        .where((cliente) =>
            cliente.nombreCompleto!.toLowerCase().contains(query.toLowerCase()))
        .toList();

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (BuildContext context, int index) {
        Clientes cliente = results[index];
        return ListTile(
          leading: cliente.imagen!.isNotEmpty
              ? CircleAvatar(
                  backgroundImage: NetworkImage(cliente.imagen!),
                )
              : const CircleAvatar(
                  child: Icon(Icons.person),
                ),
          title: Text(cliente.nombreCompleto!),
          subtitle: Text(cliente.dni!),
          trailing: Icon(Icons.arrow_forward),
          onTap: () {
            // Navegar a la página de detalles del cliente
          },
        );
      },
    );
  }
}
