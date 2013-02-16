package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import models.repo.Repo
import models.table.SparqlTextTable

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  	def sparql(query: String) = Action {
  		val qres = Repo.runSparql(query)
  		val table = new SparqlTextTable(qres)
  	  
  		SimpleResult(
  			header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")), 
  			body = Enumerator(table.getTsvString)
  		)
  	}
  
}