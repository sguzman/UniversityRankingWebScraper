/** Name of project */
name := "CollegeRankingScraper"

/** Organization (me!) */
organization := "initialcommit"

offline := true

/** Project Version */
version := "1.0"
/** Scala version */
scalaVersion := "2.12.1"
/** Scalac parameters */
scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding", "utf8", "-language:implicitConversions")
/** Javac parameters */
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
/** Resolver */
resolvers ++= Seq(
  "Search Maven" at "https://repo1.maven.org/maven2/"
)

/** Source Dependencies */
libraryDependencies ++= Seq(
  "com.mashape.unirest" % "unirest-java" % "1.4.9",
  "com.lihaoyi" % "upickle_2.12" % "0.4.4"
)

/** Make sure to fork on run */
fork in run := true

mainClass in assembly := Some("io.initialcommit.sguzman.upwork.UCC")