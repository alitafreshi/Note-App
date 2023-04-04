plugins {
    id(Plugins.androidApplication)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    kotlin(KotlinPlugins.serialization)
    id(Plugins.hilt)
    id(Plugins.safeArgsNavigationPlugin)
    id(KotlinPlugins.parcelize)
    id(Plugins.googleKspPlugin) version (Plugins.googleKspPluginVersion)
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
        multiDexEnabled = true
        targetSdk = Application.targetSdk
        versionCode = Application.versionCode
        versionName = Application.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compose_compiler_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.alitafreshi.noteapp"
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.livedateLifcycleRuntime)
    implementation(AndroidX.appCompatActivity)
    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)


    //Compose
    implementation(platform(Compose.composeBoom))
    androidTestImplementation(Compose.composeBoom)

    implementation(Compose.compose_activity)
    implementation(Compose.compose_material_2)
    implementation(Compose.compose_preview)
    implementation(Compose.compose_ui_tooling)
    implementation(Compose.compose_compiler)
    implementation(Compose.compose_constraint_layout)
    implementation(Compose.compose_viewModel)
    implementation(Compose.compose_view_binding)


    //leak canary
    debugImplementation(LeakCanary.leakCanary)

    //Hilt - Core
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    //Hilt - Navigation
    implementation(Hilt.hiltNavigation)
    implementation(Hilt.hiltFragmentsNavigation)

    //Accompanist
    implementation(Accompanist.animationNavigation)
    implementation(Accompanist.materialNavigation)

    //Data Store
    implementation(DataStore.preferencesDataStore)
    implementation(DataStore.kotlinCollection)
    implementation(DataStore.kotlinJsonSerialization)

    //Room
    implementation(Room.room)
    ksp(Room.roomCompiler)
    implementation(Room.roomKtxExtension)

    //JETPACK-NAVIGATION
    implementation(Navigation.navigation_fragments)
    implementation(Navigation.navigation_kotlin_ui)

    //splash screen
    implementation(Splash.splashScreen)

    //lottie animation
    implementation(Lottie.lottie)

    //components module
    implementation(project(Modules.components))

    //task modules
    implementation(project(Modules.taskList))

    implementation(project(Modules.taskAdEdit))

    implementation(project(Modules.taskData))

    implementation(project(Modules.taskDomain))

    //constance module
    implementation(project(Modules.constance))

    //data module
    implementation(project(Modules.data))

    //state manager module
    implementation(project(Modules.stateManager))

    //domain module
    implementation(project(Modules.domain))

}