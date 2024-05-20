Fruitypedia
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
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
## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/dansmors"><img src="https://avatars.githubusercontent.com/u/137648870?v=4?s=100" width="100px;" alt="dansmors"/><br /><sub><b>dansmors</b></sub></a><br /><a href="#design-dansmors" title="Design">🎨</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!