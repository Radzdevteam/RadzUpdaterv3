# RadzUpdater [![](https://jitpack.io/v/Radzdevteam/RadzUpdaterv3.svg)](https://jitpack.io/#Radzdevteam/RadzUpdaterv3)

## Android Library

Android Library that checks for updates on Google Play, GitHub, Amazon, F-Droid, or your own server.

## How to Include

### Add the repository to your project `settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Add the library to your module `build.gradle`:

```gradle
dependencies {
    implementation("com.github.Radzdevteam:RadzUpdaterv3:3.0")
}
```

## Usage

### In an Activity
By default, the basic usage will show a default dialog when a new version is found on the Play Store (otherwise, nothing will be shown).

#### Example:

```kotlin
val appUpdater = RadzUpdater(this, "https://raw.githubusercontent.com/Radzdevteam/test/refs/heads/main/RadzUpdaterv3.json")
appUpdater.start()
```

### Required Imports:

```kotlin
import com.radzdev.radzupdater.RadzUpdater
```
### JSON FORMAT:

```kotlin
{
  "latestVersion": "1.2.0",
  "url": "https://github.com/Radzdevteam/test/raw/refs/heads/main/test.apk",
  "releaseNotes": [
    "✓ Performance boosts",
    "✓ Bug fixes & stability",
    "✓ UI improvements",
    "✓ Faster search",
    "✓ Better file handling",
    "✓ New notifications",
    "✓ Various optimizations"
  ]
}
```

https://github.com/Radzdevteam/RadzUpdaterv3/blob/master/Screenshots/DOWNLOADING.png?raw=true
https://github.com/Radzdevteam/RadzUpdaterv3/blob/master/Screenshots/INSTALLING.png?raw=true
https://github.com/Radzdevteam/RadzUpdaterv3/blob/master/Screenshots/UPDATE%20DETECTED.png?raw=true

## License

```
Copyright 2015 Mhuradz Alegre
```
