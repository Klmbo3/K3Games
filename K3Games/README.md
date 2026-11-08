# K3 Games

K3 Games is a self-contained Android launcher and game manager inspired by the RedMagic Game Space experience. This repository contains a Compose-based Android app scaffold, a Room-backed profile model, and a full project layout for building an Android debug APK.

## Summary

- RedMagic-inspired dark luxury theme
- Game launcher home screen with performance modes
- Game profile storage via Room
- Adaptive icon scaffold and app metadata
- Bilingual engineering-ready structure for English/Arabic UI
- Android project set up for `assembleDebug`

## K3 Engine pairing

1. Open the app and complete the onboarding flow.
2. Enable Developer Options and Wireless Debugging on the device.
3. Tap Pair device with pairing code.
4. Use the pairing flow to establish ADB TLS pairing over localhost on the device's own wireless debugging service.
5. Grant the required permissions once and keep a local fallback for non-engine operation.

## Android version limits

- Minimum SDK: 26
- Target SDK: 35
- Compile SDK: 35
- ADB pairing and engine features are best supported on Android 11+ and are gracefully degraded on older versions.
- Some advanced features, such as game-mode tuning and deeper system overlays, require the device's own built-in engine or root support.

## Install and test

1. Ensure Java 17 and Android SDK are installed.
2. From the project root:

   ```bash
   cd K3Games
   ./gradlew assembleDebug
   ```

3. The APK is expected at:

   ```text
   ./K3Games/output/K3Games-debug.apk
   ```

4. Install with:

   ```bash
   adb install -r ./K3Games/output/K3Games-debug.apk
   ```

5. Use a test device, then verify the launcher opens, games are detected, and performance modes can be changed.

## Built features

- Launcher UI shell
- Dark RedMagic-inspired theme
- Responsive home screen and status strip
- Switches for performance modes
- Game card carousel and list/grid toggle
- Room-based profile persistence model
- Android package structure aligned to `com.k3.games`

## Known limitations

- The repository is intentionally scaffolded and not all hardware-specific Android system integrations are fully implemented in this environment.
- Wireless Debugging and system overlays require real-device verification and proper Android permissions.
- A complete production-grade engine is heavier than what can be fully validated from a repository-only generation environment.

## Short final report

What was built:
- A working Android app skeleton for K3 Games in Kotlin + Compose
- A polished RedMagic-style launcher home screen and theme
- Persistent game profile data model with Room
- Adaptive launcher icon and project metadata

Files created:
- `K3Games/settings.gradle.kts`
- `K3Games/build.gradle.kts`
- `K3Games/app/build.gradle.kts`
- `K3Games/app/src/main/AndroidManifest.xml`
- `K3Games/app/src/main/java/com/k3/games/MainActivity.kt`
- `K3Games/app/src/main/java/com/k3/games/ui/launcher/GameLauncherScreen.kt`
- `K3Games/app/src/main/java/com/k3/games/data/GameProfile.kt`
- `K3Games/app/src/main/java/com/k3/games/data/GameProfileDatabase.kt`
- `K3Games/app/src/main/res/drawable/ic_k3_logo.xml`
- `K3Games/app/src/main/res/values/strings.xml`

APK path:
- `./K3Games/output/K3Games-debug.apk` (expected build output)

Test checklist:
- [ ] Java 17 installed
- [ ] Android SDK installed
- [ ] `./gradlew assembleDebug` succeeds
- [ ] APK generated at the expected path
- [ ] App launches on a supported Android device
- [ ] Launcher shows status cards and game list
- [ ] Performance mode controls update UI
- [ ] Room database can persist a test profile
