import org.jetbrains.gradle.ext.runConfigurations
import org.jetbrains.gradle.ext.settings

plugins {
    id("java")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.3"
}

group = "org.example.hytalemod"
version = "0.0.1-earlyaccess"

val os = System.getProperty("os.name").lowercase()
val hytalePath = when {
    "linux" in os -> "${System.getenv("HOME")}/.var/app/com.hypixel.HytaleLauncher/data/Hytale/install/release/package/game/latest"
    "windows" in os -> "${System.getenv("APPDATA")}/Hytale/install/release/package/game/latest"
    "mac" in os -> "${System.getProperty("user.home")}/Library/Application Support/Hytale/install/release/package/game/latest"
    else -> throw GradleException("Unsupported OS: $os")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("${hytalePath}/Server/HytaleServer.jar"))
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
    withSourcesJar()
}

idea.project.settings {
    runConfigurations {
        create<org.jetbrains.gradle.ext.Application>("RunServer") {
            mainClass = "com.hypixel.hytale.Main"
            moduleName = project.idea.module.name + ".main"
            programParameters = "--allow-op --disable-sentry --assets=\"$hytalePath/Assets.zip\" --mods=${file("src/main/").absolutePath} --auth-mode authenticated"
            workingDirectory = file("$projectDir/run").absolutePath
        }
    }
}