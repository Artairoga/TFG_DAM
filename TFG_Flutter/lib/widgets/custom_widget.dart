import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

/*
  * Created by  @ArtaiRoga
  * Project: AnteProyecto
 */
class CustomDrawer extends StatefulWidget {
  const CustomDrawer({super.key});

  @override
  State<CustomDrawer> createState() => _CustomDrawerState();
}

class _CustomDrawerState extends State<CustomDrawer> {
  IconData icono = Icons.sunny;

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: SingleChildScrollView(
        child: Wrap(
          runSpacing: 16,
          children: [
            _drawerHeader(context),
            _drawerBody(),
          ],
        ),
      ),
    );
  }

  Widget _drawerBody() {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.only(
        bottom: 12.0,
        left: 12.0,
        right: 12.0,
      ),
      child: SingleChildScrollView(
        child: Column(
          children: [
            ListTile(
              onTap: () {
                Navigator.pop(context);
                Navigator.of(context).pushNamed("PetList");
              },
              leading: Icon(Icons.pets),
              title: Text(
                'Lista animales',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            ListTile(
              onTap: () {
                Navigator.pop(context);
                Navigator.of(context).pushNamed("ClientList");
              },
              leading: Icon(
                Icons.person_2_outlined,
              ),
              title: Text(
                'Lista Clientes',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            ListTile(
              onTap: () {
                Navigator.pop(context);
                Navigator.of(context).pushNamed("Citas");
              },
              leading: Icon(
                Icons.calendar_month,
              ),
              title: Text(
                'Citas',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            const Divider(
              color: Colors.grey,
              height: 0.5,
            ),
            ListTile(
              onTap: () {
                Navigator.pop(context);
                Navigator.of(context).pushNamed("Settings");
              },
              leading: Icon(
                Icons.settings,
              ),
              title: Text(
                'Ajustes',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            ListTile(
              onTap: () {
                SystemNavigator.pop();
              },
              leading: Icon(
                Icons.exit_to_app,
              ),
              title: Text(
                'Salir de la app',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                ),
              ),
            )
          ],
        ),
      ),
    );
  }

  Widget _drawerHeader(context) {
    return Container(
      color: Colors.blue,
      width: double.infinity,
      child: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(12.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const SizedBox(height: 5),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "Menu PlusVet",
                        style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.w500,
                          fontSize: 18,
                        ),
                      ),
                      const SizedBox(height: 5),
                    ],
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
