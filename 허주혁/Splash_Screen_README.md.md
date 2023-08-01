# Splash Screen

## 개요

- Flutter은 크로스 플랫폼 언어이나, 스플래시 스크린을 변경하기 위해서는 안드로이드와 iOS, 각각의 플랫폼에 맞게 스플래시 스크린을 설정해야 한다.
- 안드로이드 12 이상부터는 내장 스플래시 스크린이 존재
    - 내장 스플래시 스크린은 앱 아이콘 이미지를 중앙으로 가져오며, 배경색은 하얀색이다.
    - 다만, 조작이 굉장히 제한적이고, 삭제도 불가능해 거의 사용하지 않는다.
    - 거의 대부분의 앱들은 이 내장 스플래시 스크린을 아이콘을 감춰 빈 하얀색 스크린을 보여준 후 제작한 스플래시 패이지를 보여주거나, 아이콘을 중앙으로 가져와서 보여주고 제작한 스플래시 페이지를 보여준다.
- `flutter_native_splash` 패키지 사용하여 자체 제작
    - `flutter_native_splash` 패키지는 별도의 코드 없이도 스플래시 스크린을 만들어준다.

- [flutter_native_splash | Flutter Package](https://pub.dev/packages/flutter_native_splash)

</br>

## 이미지 파일 준비

- **무조건 PNG 파일**
- 3000 px X 3000 px 사이즈 이상의 이미지

</br>

## flutter_native_splash 설치

```bash
flutter pub add flutter_native_splash 
```

이러면 `pubspec.yaml` 파일 안에 이렇게 추가가 될 것이다. (확인해보자)

```yaml
dependencies:
  flutter_native_splash: ^2.3.1
```

`pubspec.yaml` 수정 사항이 발생했으므로 꼭 `flutter pub get` 해주자!

```bash
flutter pub get
```

</br>

## 이미지 파일

- Project 내에 `assets` 폴더 생성 후 `image` 폴더 생성, 이  `image`폴더 안에 넣고자 하는 이미지를 넣자.
- `splash.png`를 넣어보자

</br>

## 스플래시 이미지 설정

- `pubspec.yaml`과 같은 위치에 `flutter_native_splash.yaml` 파일 생성
- 그 안을 다음과 같이 채우면 된다.

```yaml
flutter_native_splash:
  # This package generates native code to customize Flutter's default white native splash screen
  # with background color and splash image.
  # Customize the parameters below, and run the following command in the terminal:
  # dart run flutter_native_splash:create
  # To restore Flutter's default white splash screen, run the following command in the terminal:
  # dart run flutter_native_splash:remove

  # color or background_image is the only required parameter.  Use color to set the background
  # of your splash screen to a solid color.  Use background_image to set the background of your
  # splash screen to a png image.  This is useful for gradients. The image will be stretch to the
  # size of the app. Only one parameter can be used, color and background_image cannot both be set.
  color: "#42a5f5"
  #background_image: "assets/background.png"

  # Optional parameters are listed below.  To enable a parameter, uncomment the line by removing
  # the leading # character.

  # The image parameter allows you to specify an image used in the splash screen.  It must be a
  # png file and should be sized for 4x pixel density.
  #image: assets/splash.png

  # The branding property allows you to specify an image used as branding in the splash screen.
  # It must be a png file. It is supported for Android, iOS and the Web.  For Android 12,
  # see the Android 12 section below.
  #branding: assets/dart.png

  # To position the branding image at the bottom of the screen you can use bottom, bottomRight,
  # and bottomLeft. The default values is bottom if not specified or specified something else.
  #branding_mode: bottom

  # The color_dark, background_image_dark, image_dark, branding_dark are parameters that set the background
  # and image when the device is in dark mode. If they are not specified, the app will use the
  # parameters from above. If the image_dark parameter is specified, color_dark or
  # background_image_dark must be specified.  color_dark and background_image_dark cannot both be
  # set.
  #color_dark: "#042a49"
  #background_image_dark: "assets/dark-background.png"
  #image_dark: assets/splash-invert.png
  #branding_dark: assets/dart_dark.png

  # Android 12 handles the splash screen differently than previous versions.  Please visit
  # https://developer.android.com/guide/topics/ui/splash-screen
  # Following are Android 12 specific parameter.
  android_12:
    # The image parameter sets the splash screen icon image.  If this parameter is not specified,
    # the app's launcher icon will be used instead.
    # Please note that the splash screen will be clipped to a circle on the center of the screen.
    # App icon with an icon background: This should be 960×960 pixels, and fit within a circle
    # 640 pixels in diameter.
    # App icon without an icon background: This should be 1152×1152 pixels, and fit within a circle
    # 768 pixels in diameter.
    #image: assets/android12splash.png

    # Splash screen background color.
    #color: "#42a5f5"

    # App icon background color.
    #icon_background_color: "#111111"

    # The branding property allows you to specify an image used as branding in the splash screen.
    #branding: assets/dart.png

    # The image_dark, color_dark, icon_background_color_dark, and branding_dark set values that
    # apply when the device is in dark mode. If they are not specified, the app will use the
    # parameters from above.
    #image_dark: assets/android12splash-invert.png
    #color_dark: "#042a49"
    #icon_background_color_dark: "#eeeeee"

  # The android, ios and web parameters can be used to disable generating a splash screen on a given
  # platform.
  #android: false
  #ios: false
  #web: false

  # Platform specific images can be specified with the following parameters, which will override
  # the respective parameter.  You may specify all, selected, or none of these parameters:
  #color_android: "#42a5f5"
  #color_dark_android: "#042a49"
  #color_ios: "#42a5f5"
  #color_dark_ios: "#042a49"
  #color_web: "#42a5f5"
  #color_dark_web: "#042a49"
  #image_android: assets/splash-android.png
  #image_dark_android: assets/splash-invert-android.png
  #image_ios: assets/splash-ios.png
  #image_dark_ios: assets/splash-invert-ios.png
  #image_web: assets/splash-web.gif
  #image_dark_web: assets/splash-invert-web.gif
  #background_image_android: "assets/background-android.png"
  #background_image_dark_android: "assets/dark-background-android.png"
  #background_image_ios: "assets/background-ios.png"
  #background_image_dark_ios: "assets/dark-background-ios.png"
  #background_image_web: "assets/background-web.png"
  #background_image_dark_web: "assets/dark-background-web.png"
  #branding_android: assets/brand-android.png
  #branding_dark_android: assets/dart_dark-android.png
  #branding_ios: assets/brand-ios.gif
  #branding_dark_ios: assets/dart_dark-ios.gif

  # The position of the splash image can be set with android_gravity, ios_content_mode, and
  # web_image_mode parameters.  All default to center.
  #
  # android_gravity can be one of the following Android Gravity (see
  # https://developer.android.com/reference/android/view/Gravity): bottom, center,
  # center_horizontal, center_vertical, clip_horizontal, clip_vertical, end, fill, fill_horizontal,
  # fill_vertical, left, right, start, or top.
  #android_gravity: center
  #
  # ios_content_mode can be one of the following iOS UIView.ContentMode (see
  # https://developer.apple.com/documentation/uikit/uiview/contentmode): scaleToFill,
  # scaleAspectFit, scaleAspectFill, center, top, bottom, left, right, topLeft, topRight,
  # bottomLeft, or bottomRight.
  #ios_content_mode: center
  #
  # web_image_mode can be one of the following modes: center, contain, stretch, and cover.
  #web_image_mode: center

  # The screen orientation can be set in Android with the android_screen_orientation parameter.
  # Valid parameters can be found here:
  # https://developer.android.com/guide/topics/manifest/activity-element#screen
  #android_screen_orientation: sensorLandscape

  # To hide the notification bar, use the fullscreen parameter.  Has no effect in web since web
  # has no notification bar.  Defaults to false.
  # NOTE: Unlike Android, iOS will not automatically show the notification bar when the app loads.
  #       To show the notification bar, add the following code to your Flutter app:
  #       WidgetsFlutterBinding.ensureInitialized();
  #       SystemChrome.setEnabledSystemUIMode(SystemUiMode.manual, overlays: [SystemUiOverlay.bottom, SystemUiOverlay.top], );
  #fullscreen: true

  # If you have changed the name(s) of your info.plist file(s), you can specify the filename(s)
  # with the info_plist_files parameter.  Remove only the # characters in the three lines below,
  # do not remove any spaces:
  #info_plist_files:
  #  - 'ios/Runner/Info-Debug.plist'
  #  - 'ios/Runner/Info-Release.plist'
```

```yaml
flutter_native_splash:
  color: "#FFFFFF"
  image: assets/image/splash.png
  fullscreen: true
```

</br>

## flutter_native_splash 패키지 옵션

`flutter_native_splash` 패키지는 다양한 옵션을 가지고 있다.

- color: 스플래시 스크린의 배경색
- background_image: 스플래시 스크린의 배경 이미지
- image: 스플래시 스크린의 이미지
- color_dark: 디바이스 설정이 다크 모드일 경우의 배경색
- background_image_dark: 디바이스 설정이 다크 모드일 경우의 배경 이미지
- image_dark: 디바이스 설정이 다크 모드일 경우의 스플래시 스크린 이미지
- android_gravity: 안드로이드에서 스플래시 이미지의 위치를 설정합니다. (bottom, center, center_horizontal, center_vertical, clip_horizontal, clip_vertical, end, fill, fill_horizontal, fill_vertical, left, right, start, top)
- ios_content_mode: iOS에서 스플래시 이미지의 위치를 설정합니다. (scaleToFill, scaleAspectFit, scaleAspectFill, center, top, bottom, left, right, topLeft, topRight, bottomLeft, bottomRight)
- web_image_mode: 웹에서 스플래시 이미지의 위치를 설정합니다. (center, contain, stretch, cover)
- fullscreen: 스플래시 스크린을 전체 화면으로 표시할지 여부(true, false)
- info_plist_files: info.plist 이름을 변경한 경우, 해당 파일을 설정하기 위한 옵션

</br>

## 스플래시 이미지 생성

```bash
flutter pub run flutter_native_splash:create
```

- 이 패키지는 flutter에서 특별히 사용되는 함수들이 아니라, 터미널을 통해 패키지를 사용하여 네이티브 패키지(프로젝트) 내에 코드를 직접 삽입하는 패키지이다
- 만약 `flutter_native_splash.yaml` 내용에 변경 사항이 생기거나, 이미지 파일을 교체하거나(`splash.png`) 할 경우, 네이티브에 적용된 파일과 코드들을 지워야한다.
- 그래서 지웠다가(`remove` ) 다시 깔아야(`create`)한다.

```bash
flutter pub run flutter_native_splash:remove
flutter pub run flutter_native_splash:create
```

</br>

## 안드로이드 12 문제

- `안드로이드 12`부터는 기본적으로 앱 아이콘이 스플래시 스크린으로 표시된다.
- `flutter_native_splash`에는 앱 아이콘 대신 다른 이미지를 표시하기 위한 옵션들을 제공하고 있다.
- 여기서 표시되는 이미지는 원형 아이콘 이미지로 짤려서 표시된다.
- 앱 아이콘이 스플래시 스크린으로 표시된 후, 이후 `flutter_native_splash`으로 만든 스플래시 스크린이 다시 한번 표시되게 하는 것이 제일 현실적 선택.
- `android/app/src/main/AndroidManifest.xml` 파일에 다음과 같은 내용을 추가하여 사용

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.app">
   <application
        android:label="app"
        android:name="${applicationName}"
        android:icon="@mipmap/ic_launcher">
        <activity
            android:name=".MainActivity"
            ...>
            ...
            <!-- Displays an Android View that continues showing the launch screen
                Drawable until Flutter paints its first frame, then this splash
                screen fades out. A splash screen is useful to avoid any visual
                gap between the end of Android's launch screen and the painting of
                Flutter's first frame. -->
            <meta-data
              android:name="io.flutter.embedding.android.SplashScreenDrawable"
              android:resource="@drawable/launch_background"
              />
            <intent-filter>
                ...
            </intent-filter>
        </activity>
        ...
    </application>
</manifest>
```

</br>

## 네이티브 스플래시 페이지 처리

### 1. 기존 Splash 사용하되, 빈 화면 처리

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Theme.SplashEx" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
		....
		<item name="android:windowSplashScreenAnimatedIcon" tools:targetApi="S">@android:color/transparent</item>
    </style>
</resources>
```

</br>

### 2. 아이콘 띄우기

- `android>app>src>main>res>AndroidManifest.xml` 내의 내용 수정

```xml
<meta-data
	android:name="io.flutter.embedding.android.SplashScreenDrawable"
  android:resource="@drawable/launch_background"
 />
```

- `res` 안의 모든 `drawable~` 폴더 내용물과  `mipmap~` 폴더 내용물 교체
    - `drawable~` - `launch_background.xml`
        
        ```xml
        <?xml version="1.0" encoding="utf-8"?>
        <layer-list xmlns:android="http://schemas.android.com/apk/res/android">
            <item>
                <bitmap android:gravity="fill" android:src="@drawable/background"/>
            </item>
            <item>
                <bitmap android:gravity="center" android:src="@drawable/splash"/>
            </item>
        </layer-list>
        ```
        
    - `mipmap~`  - `ic_launcher.png`
    
    - `value~`  - `style.xml`
    
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <!-- Theme applied to the Android Window while the process is starting when the OS's Dark Mode setting is off -->
        <style name="LaunchTheme" parent="@android:style/Theme.Black.NoTitleBar">
            <item name="android:forceDarkAllowed">false</item>
            <item name="android:windowFullscreen">true</item>
            <item name="android:windowDrawsSystemBarBackgrounds">true</item>
            <item name="android:windowLayoutInDisplayCutoutMode">shortEdges</item>
        </style>
        <!-- Theme applied to the Android Window as soon as the process has started.
             This theme determines the color of the Android Window while your
             Flutter UI initializes, as well as behind your Flutter UI while its
             running.
             
             This Theme is only used starting with V2 of Flutter's Android embedding. -->
        <style name="NormalTheme" parent="@android:style/Theme.Black.NoTitleBar">
            <item name="android:windowBackground">?android:colorBackground</item>
        </style>
    </resources>
    ```