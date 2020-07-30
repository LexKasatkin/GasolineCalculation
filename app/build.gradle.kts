import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.android.extensions")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.invest.altair"

        minSdkVersion(21)
        targetSdkVersion(30)
        versionName = "0.0.1"
        versionCode = 1

        buildToolsVersion = "30.0.1"

        lintOptions {
            isWarningsAsErrors = true
            isIgnoreTestSources = true
        }

        multiDexEnabled = true


        signingConfigs {
            create("prod") {

            }
        }


        buildTypes {
            getByName("debug") {
                isMinifyEnabled = true
                versionNameSuffix = " debug"

                proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        file("proguard-rules.pro")
                )
            }
            getByName("release") {
                isMinifyEnabled = true
                signingConfig = signingConfigs.getByName("prod")

                proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        file("proguard-rules.pro")
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    //Support
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("com.google.android.material:material:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")

    //Timber logger
    implementation("com.jakewharton.timber:timber:4.7.1")
    //Moxy
    val moxyVersion = "2.1.2"
    kapt("com.github.moxy-community:moxy-compiler:$moxyVersion")
    implementation("com.github.moxy-community:moxy:$moxyVersion")
    implementation("com.github.moxy-community:moxy-androidx:$moxyVersion")
    //Navigation
    implementation("ru.terrakok.cicerone:cicerone:5.1.1")
    //Toothpick
    val toothpickVersion = "3.1.0"
    implementation("com.github.stephanenicolas.toothpick:toothpick-runtime:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:ktp:$toothpickVersion")
    implementation("com.github.stephanenicolas.toothpick:smoothie-androidx:$toothpickVersion")
    kapt("com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion")

    //Gson
    implementation("com.google.code.gson:gson:2.8.5")
    //Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.2")

    //Image load and cache
    val glideVersion = "4.9.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")
    implementation("com.github.bumptech.glide:okhttp3-integration:$glideVersion")

    //UI
    implementation("com.aurelhubert:ahbottomnavigation:2.3.4")

    //Tests
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
}

configurations.all {
    resolutionStrategy {
        force("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")
    }
}
