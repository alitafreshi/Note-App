plugins {
    id(Plugins.androidLibrary)
    kotlin(KotlinPlugins.android)
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
        kotlinCompilerExtensionVersion = Compose.composeVersion
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

    //Compose
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.preview)
    implementation(Compose.activity)
    debugImplementation(Compose.uiTooling)
    implementation(Compose.uiUtils)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.foundation)
    implementation(Compose.compiler)
    implementation(Compose.animations)
    implementation(Compose.icons)
    implementation(Compose.constraintLayout)

    implementation(ayan.Core.ayanCore)
}