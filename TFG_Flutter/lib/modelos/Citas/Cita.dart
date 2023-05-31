class Citas {
  Citas({
    this.idCita,
    this.fecha,
    this.pendiente,
    this.id_clientes,
    this.id_animales,
    this.descripcion,
  });

  int? idCita;
  DateTime? fecha;
  int? pendiente;
  int? id_clientes;
  int? id_animales;
  String? descripcion;

  factory Citas.fromJson(Map<String, dynamic> json) {
    return Citas(
      idCita: json["IdCita"],
      fecha: DateTime.parse(json["Fecha"] + " " + json["HoraInicio"]),
      pendiente: json["Pendiente"],
      id_clientes: json["Clientes"]["IdCliente"],
      id_animales: json["Animales"]["IdAnimal"],
      descripcion: json["Descripcion"],
    );
  }

  @override
  String toString() {
    return 'Citas{idCita: $idCita, fecha: $fecha, pendiente: $pendiente, id_clientes: $id_clientes, id_animales: $id_animales, descripcion: $descripcion}';
  }
}
