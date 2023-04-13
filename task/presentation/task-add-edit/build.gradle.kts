plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    id(Plugins.safeArgsNavigationPlugin)
    id(Plugins.hilt)
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
            isMinifyEnabled = true
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compose_compiler_version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.alitafreshi.task_add_edit"

}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    implementation(AndroidX.androidxAnnotation)
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

    //JETPACK-NAVIGATION
    implementation(Navigation.navigation_fragments)
    implementation(Navigation.navigation_kotlin_ui)

    //room
    implementation(Room.room)
    ksp(Room.roomCompiler)
    implementation(Room.roomKtxExtension)

    //Hilt - Core
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    //Hilt - Navigation
    implementation(Hilt.hiltNavigation)
    implementation(Hilt.hiltFragmentsNavigation)

    //Persian Date Time
    implementation(DateTime.persianDateTime)

    //resources module
    implementation(project(Modules.resources))

    //components module
    implementation(project(Modules.components))

    //task domain module
    implementation(project(Modules.taskDomain))

    implementation(project(Modules.taskData))

    //task components module
    implementation(project(Modules.taskComponents))

    //state manager module
    implementation(project(Modules.stateManager))

    //domain module
    implementation(project(Modules.domain))

    //room - db - module
    implementation(project(Modules.roomDb))
}