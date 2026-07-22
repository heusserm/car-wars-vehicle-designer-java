# Car Wars Vehicle Designer (Android)

A native Android companion app for designing vehicles for **Car Wars**
(Steve Jackson Games), built in Java with Android Studio/Gradle. It walks
through the vehicle construction rules from the *Car Wars Compendium, 2nd
Edition* and earlier editions — body, chassis, suspension, power plant,
tires, armor and weapons — and shows live totals for cost, weight,
available space and performance as you build.

This is the native Android counterpart to the cross-platform Flutter
version of the same app; both implement the same construction rules and
weapon/ammo data cross-checked against the Compendium's Weapon Charts.

## Features

- **Home** — entry point with navigation to your garage and the designer.
- **My Vehicles** — list of saved vehicle designs, each deletable with a
  confirmation prompt.
- **Vehicle Detail** — read-only view of a saved design: chassis, power
  plant, itemized armor per facing, tires, driver (150 lb, 3 DP), mounted
  weapons, and notes. Deletable from here too.
- **Design New Vehicle** — the core designer:
  - Body type (Subcompact through Van), chassis strength, and suspension,
    each pulled from the rulebook's price/weight/space/armor tables.
  - Electric power plants (Small–Thundercat) or gas engines with a
    configurable gas tank type and size.
  - Tires (Standard through Plasticore).
  - Six-sided armor allocation (Front/Back/Left/Right/Top/Underbody) via
    compact steppers.
  - A weapons loadout builder covering the small/large-bore projectile,
    rocket, laser and flamethrower charts, with per-weapon ammo purchase
    where the Compendium prices it.
  - A live summary: total cost (incl. ammo), spaces used vs. available,
    weight vs. max load, handling class, acceleration and top speed —
    computed with the same formulas as the tabletop rules.

Dropped weapons (gas/liquid/solid), hand dischargers, and a few
complex-ammo weapons (Grenade Launcher, Mine-Flinger, Micromissile
Launcher, Rocket Launcher, Variable-Fire Rocket Pod) aren't modeled; the
rest of the construction rules are implemented.

## Tech Stack

- Java, Android SDK (`minSdk` 24, `targetSdk`/`compileSdk` 35)
- AndroidX AppCompat, Material Components, RecyclerView, CardView
- No backend — saved vehicles are persisted locally on-device via
  `SharedPreferences` (JSON-encoded, no account, no sync, no server)

## Prerequisites

| Tool | Purpose | Install |
|---|---|---|
| Android Studio + SDK | Build/run the app | [developer.android.com/studio](https://developer.android.com/studio) |
| JDK 17 | Compiling (bundled with recent Android Studio) | — |

Launch Android Studio once so it finishes its own first-run setup (it
installs the Android SDK to `~/Library/Android/sdk` by default on macOS).

## Building & Running

### From Android Studio

Open the project directory in Android Studio, let it sync Gradle, then
run the `app` configuration on an emulator or connected device.

### From the command line

```bash
cd CarWarsVehicleDesignerJava
./gradlew assembleDebug         # build only
./gradlew installDebug          # build + install on a running emulator/device
```

List and start an emulator if you don't have one running:

```bash
$ANDROID_HOME/emulator/emulator -list-avds
$ANDROID_HOME/emulator/emulator -avd <avd-name> &
```

Then launch the app:

```bash
adb shell am start -n com.xndev.carwarsvehicledesignerjava/.HomeActivity
```

## Installing on a Physical Device Without the Play Store

1. On the phone: **Settings → About phone** → tap *Build number* 7 times to
   enable Developer Options, then **Settings → Developer options** → enable
   *USB debugging*.
2. Connect the phone via USB and accept the "Allow USB debugging?" prompt.
3. Confirm it's detected: `adb devices`
4. Install and run directly: `./gradlew installDebug`, or build an APK and
   install it manually:
   ```bash
   ./gradlew assembleDebug
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```
   The device may warn about "unknown sources" the first time — allow it
   for this install.

## Project Structure

```
app/src/main/java/com/xndev/carwarsvehicledesignerjava/
  model/      Rulebook data tables (body types, chassis, suspension,
              power plants, tires, weapons, armor) and the Vehicle/
              VehicleGarage persistence model
  util/       Vehicle stat calculator (cost/weight/space/acceleration/
              top speed formulas)
  *.java      HomeActivity, DesignVehicleActivity, VehicleListActivity,
              VehicleDetailActivity, VehicleAdapter
app/src/main/res/
  layout/     Activity and list-item layouts
  values/     Styles, colors, strings
```

## Development

Built by Matt Heusser with assistance from [Claude Code](https://claude.com/claude-code)
(Anthropic).

## License

Copyright © 2026 Matt Heusser

Released under the [MIT License](LICENSE).

## Fan Content Notice

This is an unofficial, free companion app and play aid for **Car Wars
Compendium, 2nd Edition** (and earlier editions of *Car Wars*), published by
Steve Jackson Games. It is fan-made, non-commercial, and distributed free of
charge in accordance with Steve Jackson Games' online policy for fan-created
materials. It is not produced, sponsored, endorsed, or affiliated with Steve
Jackson Games.

*Car Wars* and *Car Wars Compendium* are trademarks of Steve Jackson Games
Incorporated. For the official game, rules, and more information, visit
[carwars.sjgames.com](https://carwars.sjgames.com/).
