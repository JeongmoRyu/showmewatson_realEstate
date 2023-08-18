import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';

class Live extends StatefulWidget {
  const Live({super.key});

  @override
  State<Live> createState() => _LiveState();
}

class _LiveState extends State<Live> {
  WebViewController? _webViewController;

  void initState() {
    _webViewController = WebViewController()
      ..loadRequest(Uri.parse('http://70.12.245.37:3000'))
      ..setJavaScriptMode(JavaScriptMode.unrestricted);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: WebViewWidget(controller: _webViewController!),
    );
  }
}
