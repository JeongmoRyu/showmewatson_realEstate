import 'package:flutter/material.dart';

class Signin extends StatefulWidget {
  const Signin({super.key});

  @override
  State<Signin> createState() => _SigninState();
}

class _SigninState extends State<Signin> {
  @override
  Widget build(BuildContext context) {
    final double currentWidth = MediaQuery.of(context).size.width;
    return Center(
      child: Column(
        children: [
          const Text(
            'Sign in to continue',
            style: TextStyle(
              fontSize: 20,
              color: Colors.red,
            ),
          ),
          Text(
            currentWidth.toString(),
            style: const TextStyle(fontSize: 15),
          ),
          const SizedBox(
            height: 20,
          ),
          TextField(
            decoration: InputDecoration(
              prefixIcon: const Icon(Icons.person),
              labelText: 'Email',
              enabledBorder: OutlineInputBorder(
                borderSide: const BorderSide(width: 3, color: Colors.blueAccent),
                borderRadius: BorderRadius.circular(15),
              ),
              focusedBorder: OutlineInputBorder(
                borderSide: const BorderSide(width: 3, color: Colors.redAccent),
                borderRadius: BorderRadius.circular(15),
              ),
            ),
          ),
          TextField(
            obscureText: true,
            decoration: InputDecoration(
              prefixIcon: const Icon(Icons.person),
              labelText: 'password',
              enabledBorder: OutlineInputBorder(
                borderSide: const BorderSide(width: 3, color: Colors.blueAccent),
                borderRadius: BorderRadius.circular(15),
              ),
              focusedBorder: OutlineInputBorder(
                borderSide: const BorderSide(width: 3, color: Colors.redAccent),
                borderRadius: BorderRadius.circular(15),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

