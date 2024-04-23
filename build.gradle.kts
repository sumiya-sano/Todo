// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    val hilt_version = "2.44"
    id("com.google.dagger.hilt.android") version "$hilt_version" apply false
}