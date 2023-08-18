import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:prac2/dm/chat_screen.dart';
import 'package:modal_progress_hud_nsn/modal_progress_hud_nsn.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:prac2/dm/chatting_list.dart';
import 'package:prac2/base/appbar.dart';
import 'package:go_router/go_router.dart';
import 'package:prac2/real_estate_agent/estate_screen/estate_home_screen.dart';

class directMessageLogin extends StatefulWidget {
  const directMessageLogin({super.key});

  @override
  State<directMessageLogin> createState() => _directMessageLoginState();
}

class _directMessageLoginState extends State<directMessageLogin> {
  FirebaseAuth _authentication = FirebaseAuth.instance;
  bool isSignupScreen = true;
  bool showSpinner = false;
  final _formKey = GlobalKey<FormState>();
  String userName = '';
  String userEmail = '';
  String userPassword = '';

  void _tryValidation() {
    final isValid = _formKey.currentState!.validate();
    if (isValid) {
      _formKey.currentState!.save();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        onLeadingPressed: () {
          context.go('/');
        },
        showEstateHomeButton: true,
        onEstateHomePressed: () {
          context.go('/estate_home');
        },
      ),
      body: Stack(
        children: [
          Positioned(
            top: 0,
            right: 0,
            left: 0,
            child: Container(
              height: 200,
              decoration: BoxDecoration(
                image: DecorationImage(
                    image: AssetImage('assets/images/dummy/room_picture.jpg'),
                    fit: BoxFit.fill),
              ),
              child: Container(
                padding: EdgeInsets.only(top: 90, left: 20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    RichText(
                      text: TextSpan(
                        text: 'Welcome',
                        style: TextStyle(
                          letterSpacing: 1.0,
                          fontSize: 25,
                        ),
                        children: [
                          TextSpan(
                            text:
                            isSignupScreen ? ' to Yummy chat!' : ' back',
                            style: TextStyle(
                              letterSpacing: 1.0,
                              fontSize: 25,

                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                    ),
                    SizedBox(
                      height: 5.0,
                    ),
                    Text(
                      isSignupScreen
                          ? 'Signup to continue'
                          : 'Signin to continue',
                      style: TextStyle(
                        letterSpacing: 1.0,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          //배경
          AnimatedPositioned(
            duration: Duration(milliseconds: 500),
            curve: Curves.easeIn,
            top: 180,
            child: AnimatedContainer(
              duration: Duration(milliseconds: 500),
              curve: Curves.easeIn,
              padding: EdgeInsets.all(20.0),
              height: isSignupScreen ? 280.0 : 250.0,
              width: MediaQuery.of(context).size.width - 40,
              margin: EdgeInsets.symmetric(horizontal: 20.0),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15.0),
                boxShadow: [
                  BoxShadow(
                      color: Colors.black.withOpacity(0.3),
                      blurRadius: 15,
                      spreadRadius: 5),
                ],
              ),
              child: SingleChildScrollView(
                padding: EdgeInsets.only(bottom: 20),
                child: Column(
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              isSignupScreen = false;
                            });
                          },
                          child: Column(
                            children: [
                              Text(
                                'LOGIN',
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              if (!isSignupScreen)
                                Container(
                                  margin: EdgeInsets.only(top: 3),
                                  height: 2,
                                  width: 55,
                                  color: Colors.orange,
                                )
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              isSignupScreen = true;
                            });
                          },
                          child: Column(
                            children: [
                              Text(
                                'SIGNUP',
                                style: TextStyle(
                                  fontSize: 16,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              if (isSignupScreen)
                                Container(
                                  margin: EdgeInsets.only(top: 3),
                                  height: 2,
                                  width: 55,
                                  color: Colors.orange,
                                )
                            ],
                          ),
                        )
                      ],
                    ),
                    if (isSignupScreen)
                      Container(
                        margin: EdgeInsets.only(top: 20),
                        child: Form(
                          key: _formKey,
                          child: Column(
                            children: [
                              TextFormField(
                                key: ValueKey(1),
                                validator: (value) {
                                  if (value!.isEmpty || value.length < 4) {
                                    return 'Please enter at least 4 characters';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  userName = value!;
                                },
                                onChanged: (value) {
                                  userName = value;
                                },
                                decoration: InputDecoration(
                                    prefixIcon: Icon(
                                      Icons.account_circle,
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    focusedBorder: OutlineInputBorder(

                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    hintText: 'User name',
                                    hintStyle: TextStyle(
                                      fontSize: 14,
                                    ),
                                    contentPadding: EdgeInsets.all(10)),
                              ),
                              SizedBox(
                                height: 8,
                              ),
                              TextFormField(
                                keyboardType: TextInputType.emailAddress,
                                key: ValueKey(2),
                                validator: (value) {
                                  if (value!.isEmpty && !value.contains('@')) {
                                    return 'Please enter a valid email address.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  userEmail = value!;
                                },
                                onChanged: (value) {
                                  userEmail = value;
                                },
                                decoration: InputDecoration(
                                    prefixIcon: Icon(
                                      Icons.email,
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    hintText: 'email',
                                    hintStyle: TextStyle(
                                      fontSize: 14,
                                    ),
                                    contentPadding: EdgeInsets.all(10)),
                              ),
                              SizedBox(
                                height: 8,
                              ),
                              TextFormField(
                                obscureText: true,
                                key: ValueKey(3),
                                validator: (value) {
                                  if (value!.isEmpty || value.length < 7) {
                                    return 'Password must be at least 7 characters long.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  userPassword = value!;
                                },
                                onChanged: (value) {
                                  userPassword = value;
                                },
                                decoration: InputDecoration(
                                    prefixIcon: Icon(
                                      Icons.lock,
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    hintText: 'password',
                                    hintStyle: TextStyle(
                                      fontSize: 14,
                                    ),
                                    contentPadding: EdgeInsets.all(10)),
                              )
                            ],
                          ),
                        ),
                      ),
                    if (!isSignupScreen)
                      Container(
                        margin: EdgeInsets.only(top: 20),
                        child: Form(
                          key: _formKey,
                          child: Column(
                            children: [
                              TextFormField(
                                key: ValueKey(4),
                                validator: (value) {
                                  if (value!.isEmpty ||
                                      !value.contains('@')) {
                                    return 'Please enter a valid email address.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  userEmail = value!;
                                },
                                onChanged: (value) {
                                  userEmail = value;
                                },
                                decoration: InputDecoration(
                                    prefixIcon: Icon(
                                      Icons.email,
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                      borderSide: BorderSide(
                                      ),
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    hintText: 'email',
                                    hintStyle: TextStyle(
                                      fontSize: 14,
                                    ),
                                    contentPadding: EdgeInsets.all(10)),
                              ),
                              SizedBox(
                                height: 8.0,
                              ),
                              TextFormField(
                                key: ValueKey(5),
                                validator: (value) {
                                  if (value!.isEmpty || value.length < 7) {
                                    return 'Password must be at least 7 characters long.';
                                  }
                                  return null;
                                },
                                onSaved: (value) {
                                  userPassword = value!;
                                },
                                onChanged: (value) {
                                  userPassword = value;
                                },
                                decoration: InputDecoration(
                                    prefixIcon: Icon(
                                      Icons.lock,
                                    ),
                                    enabledBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    focusedBorder: OutlineInputBorder(
                                      borderRadius: BorderRadius.all(
                                        Radius.circular(35.0),
                                      ),
                                    ),
                                    hintText: 'password',
                                    hintStyle: TextStyle(
                                      fontSize: 14,
                                    ),
                                    contentPadding: EdgeInsets.all(10)),
                              )
                            ],
                          ),
                        ),
                      )
                  ],
                ),
              ),
            ),
          ),
          //텍스트 폼 필드
          AnimatedPositioned(
            duration: Duration(milliseconds: 500),
            curve: Curves.easeIn,
            top: isSignupScreen ? 430 : 390,
            right: 0,
            left: 0,
            child: Center(
              child: Container(
                padding: EdgeInsets.all(15),
                height: 90,
                width: 90,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(50)),
                child: GestureDetector(
                  onTap: () async {
                    setState(() {
                      showSpinner = true;
                    });
                    if (isSignupScreen) {
                      _tryValidation();

                      try {
                        final newUser = await _authentication.createUserWithEmailAndPassword(
                          email: userEmail,
                          password: userPassword,
                        );

                        await FirebaseFirestore.instance.collection('user').doc(newUser.user!.uid)
                            .set({
                          'userName' : userName,
                          'email' : userEmail
                        });

                        if (newUser.user != null) {
                          // Navigator.push(
                          //   context,
                          //   MaterialPageRoute(
                          //     builder: (context) {
                          //       return ChatScreen();
                          //     },
                          //   ),
                          // );
                          setState(() {
                            showSpinner = false;
                          });
                        }
                      } catch (e) {
                        print(e);
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(
                            content:
                            Text('Please check your email and password'),
                          ),
                        );
                      }
                    }

                    if (!isSignupScreen) {
                      _tryValidation();

                      try {
                        final newUser =
                        await _authentication.signInWithEmailAndPassword(
                          email: userEmail,
                          password: userPassword,
                        );
                        if (newUser.user != null) {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) {
                                return ChattingList();
                              },
                            ),
                          );
                        }
                      }catch(e){
                        print(e);
                      }
                    }
                  },
                  child: Container(
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(30),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.black.withOpacity(0.3),
                          spreadRadius: 1,
                          blurRadius: 1,
                          offset: Offset(0, 1),
                        ),
                      ],
                    ),
                    child: Icon(
                      Icons.arrow_forward,
                      color: Colors.white,
                    ),
                  ),
                ),
              ),
            ),
          ),
          //전송버튼
          AnimatedPositioned(
            duration: Duration(milliseconds: 500),
            curve: Curves.easeIn,
            top: isSignupScreen
                ? MediaQuery.of(context).size.height - 125
                : MediaQuery.of(context).size.height - 165,
            right: 0,
            left: 0,
            child: Column(
              children: [
                Text(isSignupScreen ? 'or Signup with' : 'or Signin with'),
                SizedBox(
                  height: 10,
                ),
                TextButton.icon(
                  onPressed: () {},
                  style: TextButton.styleFrom(
                    minimumSize: Size(155, 40),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(20)),
                  ),
                  icon: Icon(Icons.add),
                  label: Text('Google'),
                ),
              ],
            ),
          ),
          //구글 로그인 버튼
        ],
      ),
    );
  }
}
