plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    //kotlinCoroutines
    implementation(Kotlin.kotlinCoroutines)

    //javax
    implementation(Hilt.javaInject)

    //data module
    implementation(project(Modules.data))

    //domain module
    implementation(project(Modules.domain))
}