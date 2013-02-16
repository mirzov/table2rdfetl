package models

package object repo{
	import com.typesafe.config.ConfigFactory
	
	private def pathToRdf: String = {
		val config = ConfigFactory.load()
    	return config.getString("db.rdffile")
  	}
	
	object Repo extends SesameInMemoryRepo(pathToRdf) 
}

