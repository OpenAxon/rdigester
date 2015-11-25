name := "rdigester"

version := "0.0.1-SNAPSHOT"

organization := "com.evidence"

resolvers += "Nexus" at "https://nexus.taservs.net/content/groups/public"

resolvers += "Twitter" at "http://maven.twttr.com"

// Test
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.0" % "it,test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "it,test"
)

// Core
libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "ch.qos.logback" % "logback-core" % "1.1.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "commons-codec" % "commons-codec" % "1.10"
)


// Although I think I've locked sufficiently, sometimes I get test
// failures without this.
//parallelExecution in Test := false

/*
scalacOptions ++= Seq("-unchecked", "-deprecation")

// call make -f Makefile.native clean
clean <<= (clean, resourceManaged in Compile, sourceDirectory, classDirectory in Compile,
  managedClasspath in Compile) map { (clean, dir, src, classDir, runPath) => {
  val home = System.getProperty("java.home")
  val basePath = runPath.map(_.data.toString).reduceLeft(_ + ":" + _)
  val classpath = classDir.toString + ":" + basePath
  val result = sbt.Process(
    "make" :: "-f" :: "Makefile.native" :: "clean" :: Nil,
    None,
    "COMPILE_PATH" -> classDir.toString,
    "CLASSPATH" -> classpath,
    "JAVA_HOME" -> home
  ) ! ;
  //
  if (result != 0)
    error("Error cleaning native library")
  clean
}
}

// call make -f Makefile.native all
compile <<= (compile in Compile, resourceManaged in Compile, sourceDirectory, classDirectory in Compile,
  managedClasspath in Compile) map { (compile, dir, src, classDir, runPath) => {
  val superCompile = compile
  val home = System.getProperty("java.home")
  val basePath = runPath.map(_.data.toString).reduceLeft(_ + ":" + _)
  val classpath = classDir.toString + ":" + basePath
  val result = sbt.Process(
    "make" :: "-f" :: "Makefile" :: "all" :: Nil,
    None,
    "COMPILE_PATH" -> classDir.toString,
    "CLASSPATH" -> classpath,
    "JAVA_HOME" -> home
  ) ! ;
  //
  if (result != 0)
    error("Error compiling native library")
  superCompile
}
}
*/

fork in run := true

javaOptions in run += "-Djava.library.path=./target/so"