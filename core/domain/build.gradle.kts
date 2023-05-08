plugins {
    id(Plugins.javaLibrary)
    id(KotlinPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{
    //retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)
    implementation(Retrofit.gson_convertor)
    implementation("androidx.annotation:annotation-jvm:+")
}