class Animales {
  Animales({
    this.idAnimal,
    this.nombreAnimal,
    this.caracteristicas,
    this.imagen,
    this.id_clientes,
    this.list_id_citas,
  });

  int? idAnimal;
  String? nombreAnimal;
  String? caracteristicas;
  String? imagen;
  int? id_clientes;
  List<int>? list_id_citas;

  factory Animales.fromJson(Map<String, dynamic> json) => Animales(
        idAnimal: json["IdAnimal"],
        nombreAnimal: json["NombreAnimal"],
        caracteristicas: json["Caracteristicas"],
        imagen: json["Imagen"],
        id_clientes: json["Clientes"]["IdCliente"],
        list_id_citas:
            List<int>.from(json["Citas List"].map((x) => x["IdCita"])),
      );

  @override
  String toString() {
    return 'Animales{idAnimal: $idAnimal, tipoAnimal: $nombreAnimal, caracteristicas: $caracteristicas, imagen: $imagen, id_clientes: $id_clientes, list_id_citas: $list_id_citas}';
  }
}
