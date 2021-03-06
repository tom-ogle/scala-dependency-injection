name := "scala-dependency-injection"

version := "1.0"

scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.2",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
)
