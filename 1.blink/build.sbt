scalaVersion := "2.12.13"

scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xsource:2.11",
    "-language:reflectiveCalls",
    "-P:chiselplugin:useBundlePlugin"
)

resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("release")
)

libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.4.3"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
// libraryDependencies += "edu.berkeley.cs" %% "chisel-iotesters" % "1.5.3"
// libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.3.3"
addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.4.3" cross CrossVersion.full)
