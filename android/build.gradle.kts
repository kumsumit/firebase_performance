import org.gradle.api.JavaVersion
import org.gradle.api.Project

group = "io.flutter.plugins.firebaseperformance"
version = "1.0-SNAPSHOT"

plugins {
    id("com.android.library")
}

apply(from = "local-config.gradle.kts")

rootProject.allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val firebaseCoreProject =
    findProject(":firebase_core")
        ?: throw GradleException(
            "Could not find the firebase_core FlutterFire plugin, " +
                "have you added it as a dependency in your pubspec?"
        )

if (firebaseCoreProject.findProperty("FirebaseSDKVersion") == null) {
    throw GradleException(
        "A newer version of the firebase_core FlutterFire plugin is required, " +
            "please update your firebase_core pubspec dependency."
    )
}

fun getRootProjectExtOrCoreProperty(
    name: String,
    firebaseCoreProject: Project,
): Any {
    val flutterFire =
        rootProject.extensions.extraProperties
            .properties["FlutterFire"] as? Map<*, *>

    return flutterFire?.get(name)
        ?: firebaseCoreProject.findProperty(name)
        ?: throw GradleException("Property '$name' not found")
}

val compileSdkValue: extra["compileSdk"] as Int
val minSdkValue: extra["minSdk"] as Int
val targetSdk: extra["targetSdk"] as Int
val javaVersion: extra["javaVersion"] as Int

android {
    namespace = "io.flutter.plugins.firebase.performance"

    compileSdk = compileSdkValue

    defaultConfig {
        minSdk = minSdkValue

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    buildFeatures {
        buildConfig = true
    }

    lint {
        disable += "InvalidPackage"
    }
}

dependencies {
    api(firebaseCoreProject)

    implementation(
        platform(
            "com.google.firebase:firebase-bom:${
                getRootProjectExtOrCoreProperty(
                    "FirebaseSDKVersion",
                    firebaseCoreProject,
                )
            }"
        )
    )

    implementation("com.google.firebase:firebase-perf")
    implementation("androidx.annotation:annotation:1.10.0")
}

apply(from = "user-agent.gradle.kts")