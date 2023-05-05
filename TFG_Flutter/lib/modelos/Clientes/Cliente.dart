class Clientes {
  Clientes({
    this.idCliente,
    this.dni,
    this.nombreCompleto,
    this.telefono,
    this.imagen,
    this.list_id_animales,
    this.list_id_citas,
  });

  int? idCliente;
  String? dni;
  String? nombreCompleto;
  String? telefono;
  String? imagen;
  List<int>? list_id_animales;
  List<int>? list_id_citas;

  factory Clientes.fromJson(Map<String, dynamic> json) => Clientes(
        idCliente: json["IdCliente"],
        dni: json["Dni"],
        nombreCompleto: json["NombreCompleto"],
        telefono: json["Telefono"],
        imagen: json["Imagen"],
        list_id_animales:
            List<int>.from(json["Animales List"].map((x) => x["IdAnimal"])),
        list_id_citas:
            List<int>.from(json["Citas List"].map((x) => x["IdCita"])),
      );

  @override
  String toString() {
    return 'Clientes{idCliente: $idCliente, dni: $dni, nombreCompleto: $nombreCompleto, telefono: $telefono, imagen: $imagen, list_id_animales: $list_id_animales, list_id_citas: $list_id_citas}';
  }
}
