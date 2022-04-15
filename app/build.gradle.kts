//import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    id(Plugins.androidApplication)
    kotlin(KotlinPlugins.android)
    kotlin(KotlinPlugins.kapt)
    id(Plugins.hilt)
//    id(Plugins.dokka)
    id(KotlinPlugins.parcelize)
    id(Plugins.googleKspPlugin) version (Plugins.googleKspPluginVersion)
}

android {
    compileSdk = Application.compileSdk

    defaultConfig {
        applicationId = Application.appId
        minSdk = Application.minSdk
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

    flavorDimensions += "store"
    productFlavors {
        /* create("playstore") {
             dimension = "store"
             buildConfigField(type = "String", name = "category", value = "\"playstore\"")
             kotlin {
                 sourceSets {
                     debug {
                         kotlin.srcDir("build/generated/ksp/playstoredebug/kotlin")
                     }
                     release {
                         kotlin.srcDir("build/generated/ksp/playstorerelease/kotlin")
                     }
                 }
             }
         }*/

        create("cafebazaar") {
            dimension = "store"
            buildConfigField(type = "String", name = "category", value = "\"cafebazaar\"")
            kotlin {
                sourceSets {
                    debug {
                        kotlin.srcDir("build/generated/ksp/cafebazaardebug/kotlin")
                    }
                    release {
                        kotlin.srcDir("build/generated/ksp/cafebazaarrelease/kotlin")
                    }
                }
            }
        }

        /*create("myket") {
            dimension = "store"
            buildConfigField(type = "String", name = "category", value = "\"myket\"")
            kotlin {
                sourceSets {
                    debug {
                        kotlin.srcDir("build/generated/ksp/myketdebug/kotlin")
                    }
                    release {
                        kotlin.srcDir("build/generated/ksp/myketrelease/kotlin")
                    }
                }
            }
        }*/
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


/*
tasks.dokkaHtml.configure {
    failOnWarning.set(true)
    outputDirectory.set(file("../documentation/html"))
}

tasks.withType<DokkaTaskPartial>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Subvention Inquiry - app module")
            suppressInheritedMembers.set(true)
            skipEmptyPackages.set(true)
            jdkVersion.set(8)
            includes.from("AppModule.md")
        }
    }
}
*/


dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)
    testImplementation(Junit.junit)
    androidTestImplementation(Junit.junitTestExt)
    androidTestImplementation(Junit.junitTestExtKtx)
    androidTestImplementation(Espresso.espresso)
    androidTestImplementation(ComposeUiTest.ComposeUiTest)

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
    implementation(Compose.navigation)

    //leak canary
    debugImplementation(LeakCanary.leakCanary)

    //Hilt - Core
    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)

    //Hilt - Navigation
    implementation(Hilt.hiltNavigation)
    implementation(Hilt.hiltComposeNavigation)

    //Accompanist
    implementation(Accompanist.animationNavigation)
    implementation(Accompanist.materialNavigation)

    //Data Store
    implementation(DataStore.dataStore)

    //Navigation
    implementation(Navigation.navigationAnimationCore)
    ksp(Navigation.navigationDestinationKsp)

    //splash screen
    implementation(Splash.splashScreen)

    //lottie animation
    implementation(Lottie.lottie)
}