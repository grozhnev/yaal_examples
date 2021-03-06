/*
 * Run: sbt assembly
 */
import Dependencies._

lazy val root = (project in file(".")).
  settings(
    name := "MergeStrategy",
    organization := "ru.yaal.build.sbt.plugin.assembly",
    scalaVersion := "2.11.12",
    version := "1",
    libraryDependencies ++= allDeps,
    mainClass in assembly := Some("my.MyMain"),
    assemblyJarName in assembly := "fat.jar",
    assemblyMergeStrategy in assembly := {
      case x if x.startsWith("META-INF") => MergeStrategy.discard
      case "org/pcap4j/packet/factory/StaticUnknownPacketFactory.class" => MergeStrategy.first
      case _ => MergeStrategy.deduplicate
    }
  )
