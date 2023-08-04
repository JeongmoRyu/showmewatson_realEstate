// import 'package:flutter/material.dart';
// import 'package:camera/camera.dart';
//
// Future<void> main() async {
//   WidgetsFlutterBinding.ensureInitialized();
//   final cameras = await availableCameras();
//   final backCamera = cameras.firstWhere((camera) => camera.lensDirection == CameraLensDirection.back);
//   runApp(MyApp(camera: backCamera));
// }
//
// class prac5 extends StatelessWidget {
//   final CameraDescription camera;
//
//   const prac5({required this.camera});
//
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       home: CameraScreen(camera: camera),
//     );
//   }
// }
//
// class CameraScreen extends StatefulWidget {
//   final CameraDescription camera;
//
//   const CameraScreen({required this.camera});
//
//   @override
//   _CameraScreenState createState() => _CameraScreenState();
// }
//
// class _CameraScreenState extends State<CameraScreen> {
//   late CameraController _controller;
//   late Future<void> _initializeControllerFuture;
//
//   @override
//   void initState() {
//     super.initState();
//     _controller = CameraController(
//       widget.camera,
//       ResolutionPreset.high,
//     );
//     _initializeControllerFuture = _controller.initialize();
//   }
//
//   @override
//   void dispose() {
//     _controller.dispose();
//     super.dispose();
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(title: Text('Camera Example')),
//       body: FutureBuilder<void>(
//         future: _initializeControllerFuture,
//         builder: (context, snapshot) {
//           if (snapshot.connectionState == ConnectionState.done) {
//             return CameraPreview(_controller);
//           } else {
//             return Center(child: CircularProgressIndicator());
//           }
//         },
//       ),
//     );
//   }
// }