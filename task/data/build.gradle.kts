plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    id(Plugins.kotlinAndroid)
    kotlin(KotlinPlugins.serialization)

    id(Plugins.googleKspPlugin) version (Plugins.googleKspPluginVersion)

}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)

    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)


    //room
    implementation(Room.room)
    ksp(Room.roomCompiler)
    implementation(Room.roomKtxExtension)

    //task domain module
    implementation(project(Modules.taskDomain))

    implementation(ayan.Core.ayanCore)
}