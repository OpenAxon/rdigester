name := "rdigester"

version := "0.0.1-SNAPSHOT"

organization := "com.evidence"

resolvers += "Nexus" at "https://nexus.taservs.net/content/groups/public"

resolvers += "Twitter" at "http://maven.twttr.com"

// Test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.0" % "test"
)

// Core
libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "commons-codec" % "commons-codec" % "1.10"
)

val compileJniLibTask = taskKey[Int]("Build openssl JNI bindings")
val cleanJniLibTask = taskKey[Int]("Clean openssl JNI bindings")

lazy val rdigester = (project in file(".")).
  settings(
    compileJniLibTask := {
      println("[jnilib] make all")

      val result = sbt.Process("make" :: "-f" :: "Makefile" :: "all" :: Nil, Some(baseDirectory.value / "")) !

      if (result != 0) {
        sys.error(s"Error cleaning native library [$result]")
      }

      result
    },
    cleanJniLibTask := {
      println("[jnilib] make clean")

      val result = sbt.Process("make" :: "-f" :: "Makefile" :: "clean" :: Nil, Some(baseDirectory.value / "")) !

      if (result != 0) {
        sys.error(s"Error cleaning native library [$result]")
      }

      result
    }
  )

sbt.Keys.compile <<= (sbt.Keys.compile in Compile) dependsOn compileJniLibTask

sbt.Keys.clean <<= (sbt.Keys.clean in ThisScope) dependsOn cleanJniLibTask

fork in run := true

javaOptions in run += "-Djava.library.path=./target/so"