import 'package:flutter/material.dart';

import '../modelos/Animales/SolictudesAnimales.dart';
import '../modelos/modelos.dart';
import 'animal_page.dart';

class ListaAnimales extends StatelessWidget {
  const ListaAnimales({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    TextEditingController searchController = TextEditingController();
    return FutureBuilder(
      future: SolcitudesAnimales().listaAnimales(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.done) {
          List<Animales> listaAnimales = snapshot.data as List<Animales>;
          return Scaffold(
            appBar: AppBar(
              title: Text('Lista de animales'),
              actions: [
                IconButton(
                  onPressed: () {
                    showSearch(
                        context: context,
                        delegate:
                            AnimalSearch(listaAnimales, searchController));
                  },
                  icon: Icon(Icons.search),
                )
              ],
            ),
            body: ListView.builder(
              itemCount: listaAnimales.length,
              itemBuilder: (BuildContext context, int index) {
                Animales animal = listaAnimales[index];
                return ListTile(
                  leading: animal.imagen!.isNotEmpty
                      ? CircleAvatar(
                          backgroundImage: NetworkImage(animal.imagen!),
                        )
                      : const CircleAvatar(
                          child: Icon(Icons.pets),
                        ),
                  title: Text(animal.tipoAnimal!),
                  subtitle: Text(animal.tipoAnimal!),
                  trailing: Icon(Icons.arrow_forward),
                  onTap: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) {
                              return AnimalPage();
                            },
                            settings: RouteSettings(arguments: animal)));
                    // Navegar a la página de detalles del animal
                  },
                );
              },
            ),
          );
        } else {
          return Scaffold(
            appBar: AppBar(
              title: Text('Lista de animales'),
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

class AnimalSearch extends SearchDelegate {
  final List<Animales> animales;
  final TextEditingController searchController;

  AnimalSearch(this.animales, this.searchController);

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
    List<Animales> results = animales
        .where((animal) =>
            animal.tipoAnimal!.toLowerCase().contains(query.toLowerCase()))
        .toList();

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (BuildContext context, int index) {
        return ListTile(
          leading: results[index].imagen!.isNotEmpty
              ? CircleAvatar(
                  backgroundImage: NetworkImage(results[index].imagen!),
                )
              : const CircleAvatar(
                  child: Icon(Icons.pets),
                ),
          title: Text(results[index].tipoAnimal!),
          subtitle: Text(results[index].tipoAnimal!),
          trailing: Icon(Icons.arrow_forward),
          onTap: () {
            // Navegar a la página de detalles del animal
          },
        );
      },
    );
  }

  @override
  Widget buildSuggestions(BuildContext context) {
    List<Animales> results = animales
        .where((animal) =>
            animal.tipoAnimal!.toLowerCase().contains(query.toLowerCase()))
        .toList();

    return ListView.builder(
      itemCount: results.length,
      itemBuilder: (BuildContext context, int index) {
        return ListTile(
          leading: results[index].imagen!.isNotEmpty
              ? CircleAvatar(
                  backgroundImage: NetworkImage(results[index].imagen!),
                )
              : const CircleAvatar(
                  child: Icon(Icons.pets),
                ),
          title: Text(results[index].tipoAnimal!),
          subtitle: Text(results[index].tipoAnimal!),
          trailing: Icon(Icons.arrow_forward),
          onTap: () {
            // Navegar a la página de detalles del animal
          },
        );
      },
    );
  }
}
