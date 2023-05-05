class Citas {
  Citas({
    this.idCita,
    this.fecha,
    this.horaInicio,
    this.pendiente,
    this.id_clientes,
    this.id_animales,
    this.descripcion,
  });

  int? idCita;
  DateTime? fecha;
  String? horaInicio;
  int? pendiente;
  int? id_clientes;
  int? id_animales;
  String? descripcion;

  factory Citas.fromJson(Map<String, dynamic> json) {
    return Citas(
      idCita: json["IdCita"],
      fecha: DateTime.parse(json["Fecha"]),
      horaInicio: json["HoraInicio"],
      pendiente: json["Pendiente"],
      id_clientes: json["Clientes"]["IdCliente"],
      id_animales: json["Animales"]["IdAnimal"],
      descripcion: json["Descripcion"],
    );
  }

  @override
  String toString() {
    return 'Citas{idCita: $idCita, fecha: $fecha, horaInicio: $horaInicio, pendiente: $pendiente, id_clientes: $id_clientes, id_animales: $id_animales, descripcion: $descripcion}';
  }
}
