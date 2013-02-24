package etl.ontology

import models.repo.Repo
import com.typesafe.config.ConfigFactory

object OntologyCodeApp extends App {

	val config = ConfigFactory.load()
	val home = config.getString("app.home")
	val ontFilePath = config.getString("app.ontocodefile")
	
	val code = Repo.access(conn => CodeGenerator.generate(conn))
	
	val pw = new java.io.PrintWriter(new java.io.File(home + ontFilePath))
    pw.write(code)
    pw.close()
  
}