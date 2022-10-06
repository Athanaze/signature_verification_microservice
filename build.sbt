ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.17"

lazy val root = (project in file("."))
  .settings(
    name := "onyest"
  )
// https://mvnrepository.com/artifact/org.ergoplatform/ergo-appkit
libraryDependencies += "org.ergoplatform" %% "ergo-appkit" % "4.0.10"
libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.5.4",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.12.v20180830"
)