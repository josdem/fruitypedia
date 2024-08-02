Fruitypedia
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=josdem_fruitypedia&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=josdem_fruitypedia)
----------------------------

This project contains juice and smoothie recipes to provide alternatives to heal our body and/or boost energy using [Android](https://www.android.com/) and [Kotlin](https://kotlinlang.org/).

### Requirements

* [Android Studio](https://developer.android.com/studio)
* Android 5 (Lollipop) or above emulator or real device

**Note:** It was developed with a Pixel 4 Android 14 

### To run unit tests
```bash
./gradlew test
```

### To run integration tests
```bash
./gradlew connectedAndroidTest
```

### To build the project
```bash
./gradlew assembleDebug
```

### To format the code
```bash
./gradlew spotlessApply
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
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/MJJacobs01"><img src="https://avatars.githubusercontent.com/u/52112400?v=4?s=100" width="100px;" alt="MJ Jacobs"/><br /><sub><b>MJ Jacobs</b></sub></a><br /><a href="https://github.com/josdem/fruitypedia/pulls?q=is%3Apr+reviewed-by%3AMJJacobs01" title="Reviewed Pull Requests">👀</a> <a href="https://github.com/josdem/fruitypedia/commits?author=MJJacobs01" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!