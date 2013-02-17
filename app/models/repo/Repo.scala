package models

package object repo{
	import com.typesafe.config.ConfigFactory
	
	private def pathsToRdf: Seq[String] = {
		val config = ConfigFactory.load()
		return Seq("db.ontofile", "db.datafile").map(config.getString(_)) 
 	}
	
	object Repo extends SesameInMemoryRepo(pathsToRdf) 
}

