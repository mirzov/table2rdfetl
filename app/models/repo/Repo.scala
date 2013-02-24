package models

package object repo{
	import com.typesafe.config.ConfigFactory
	
	private def pathsToRdf: Seq[String] = {
		val config = ConfigFactory.load()
		val home = config.getString("app.home")
		return Seq("db.ontofile", "db.datafile").map(home + config.getString(_)) 
 	}
	
	object Repo extends SesameInMemoryRepo(pathsToRdf) 
}

