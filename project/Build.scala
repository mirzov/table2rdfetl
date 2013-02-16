import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

	val appName         = "canmoveprototype"
	val appVersion      = "1.0"

	val sesameVersion   = "2.6.10"
 
	val appDependencies = Seq(
		//"org.scalatest" %% "scalatest" % "2.0.M3" % "test",
		"org.openrdf.sesame" % "sesame-repository-sparql" % sesameVersion,
		"org.openrdf.sesame" % "sesame-queryparser-sparql" % sesameVersion,
		"org.openrdf.sesame" % "sesame-queryresultio-text" % sesameVersion,
		"org.openrdf.sesame" % "sesame-repository-sail" % sesameVersion,
		"org.openrdf.sesame" % "sesame-sail-memory" % sesameVersion,
		"org.openrdf.sesame" % "sesame-rio-rdfxml" % sesameVersion
	)
  
  	val main = play.Project(appName, appVersion, appDependencies).settings(
  		//testOptions in Test := Nil,
  		organization := "se.lu.canmove",
  	    scalaVersion := "2.10.0",
		autoScalaLibrary := true,
		resolvers ++= Seq(
			"Sesame Repo" at "http://repo.aduna-software.org/maven2/releases/"
		)
	)

}
