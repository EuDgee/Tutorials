name := """Hadoop-Tutorial"""

organization := "LiveTex"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.apache.hadoop" % "hadoop-client" % "2.3.0-cdh5.1.2"
)

resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

initialCommands := "import ru.livetex.log._"
