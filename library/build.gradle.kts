@file:Suppress("UnusedPrivateMember")
import com.vanniktech.maven.publish.SonatypeHost

plugins {
  kotlin(Kotlin.MultiplatformPluginId)
  id(MavenPublish.PluginId)
}

kotlin {
  explicitApi()

  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "1.8"
    }
    testRuns["test"].executionTask.configure {
      useJUnitPlatform()
    }
  }

  sourceSets {
    matching { it.name.endsWith("Test") }.all {
      languageSettings {
        optIn("kotlin.time.ExperimentalTime")
      }
    }

    val commonMain by getting {
      dependencies {
        implementation(Coroutines.Core)
        implementation(Kotlin.StdLib)
      }
    }
    val jvmMain by getting
  }
}

plugins.withId("com.vanniktech.maven.publish") {
  mavenPublish {
    sonatypeHost = SonatypeHost.S01
    releaseSigningEnabled = true
  }
}
