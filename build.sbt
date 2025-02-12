name := "play-recaptcha"
description := "Google reCAPTCHA integration for Play Framework"
organization := "com.nappin"
version := "2.5"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

crossScalaVersions := Seq("2.12.8", "2.13.6")

libraryDependencies ++= Seq(
  ws,
  specs2 % Test,
  "org.mockito" % "mockito-core" % "1.+" % Test
)

// specs2 dependency not in maven central
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

// latest sbt-gpg plugin needs to know these explicitly
pgpSecretRing := file("/Users/Chris.Nappin/Development/SonatypeKey/secring.asc")

pgpPublicRing := file("/Users/Chris.Nappin/Development/SonatypeKey/pubring.asc")

// adds "test-conf" to the test classpath (for message resolving)
Test / unmanagedClasspath += baseDirectory.value / "test-conf"

// needed to publish to maven central
publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

Test / publishArtifact := false
pomIncludeRepository := { _ => false }

pomExtra := (
  <url>http://chrisnappin.github.io/play-recaptcha</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:git@github.com:chrisnappin/play-recaptcha.git</connection>
    <developerConnection>scm:git:git@github.com:chrisnappin/play-recaptcha.git</developerConnection>
    <url>git@github.com:chrisnappin/play-recaptcha.git</url>
  </scm>
  <developers>
    <developer>
      <id>chrisnappin</id>
      <name>Chris Nappin</name>
      <email>chris@nappin.com</email>
      <timezone>UTC</timezone>
    </developer>
  </developers>)
  