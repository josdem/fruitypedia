Fruitypedia
----------------------------

This project contains juice and smoothie recipes to provide alternatives to heal our body and/or boost energy using [Android](https://www.android.com/) and [Kotlin](https://kotlinlang.org/).

### Requirements

* [Android Studio](https://developer.android.com/studio)
* Android 5 (Lollipop) or above emulator or real device

**Note:** It was developed with a Pixel 4 Android 14 

### To run tests
```bash
gradle test
```

### To build the project
```bash
gradle assembleDebug
```

### To format the code
```bash
gradle spotlessApply
```

### To run emulator
```bash
${androidSdk}/emulator emulator -adv ${deviceName} 
```

Where:
- `${androidSdk}` is your Android SDK directory
- `${deviceName}` is device you created 

### To install the app
```bash
${androidSdk}/platform-tools/adb -s emulator-5554 install ${projectHome}/app/build/outputs/apk/debug/app-debug.apk
```

Where:
- `${androidSdk}` is your Android SDK directory
- `${projectHome}` is your project directory

**We appreciate contribution to this open source project**
[Contributors](https://github.com/josdem/fruitypedia/CONTRIBUTORS.md)

**Note:** Fruitypedia uses [fruitypedia-spring-boot](https://github.com/josdem/fruitypedia-spring-boot) project as a backend service.