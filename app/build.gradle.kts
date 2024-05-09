plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.apollographql.apollo3").version("3.8.3")
}

android {
    namespace = "com.mehrab.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mehrab.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // optional: if you want to use the normalized cache
    implementation(libs.apollo.normalized.cache.sqlite)
    // optional: if you just want the generated models and parsers and write your own HTTP code/cache code, you can remove apollo-runtime
    implementation(libs.apollo.runtime)
    // and use apollo-api instead
    implementation(libs.apollo.api)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

apollo {
    service("service") {
        packageName.set("com.mehrab.myapplication")
        //generateOptionalOperationVariables.set(false)

        introspection {
            endpointUrl.set("http://panel.proservers.ir/graphql")
            //schemaFile.set(file("src/main/graphql/schema.json"))
        }
    }
}