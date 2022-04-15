// Top-level build file where you can add configuration options common to all sub-projects/modules.
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
/*plugins {
    id(Plugins.dokka)
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(file("../documentation/html"))
    includes.from("Readme.md")
}*/

buildscript {
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(Build.buildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltGradlePlugin)
//        classpath(Document.dokkaGradleVersion)
    }
}

allprojects {
    repositories {
        maven(url = "https://jitpack.io")
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}