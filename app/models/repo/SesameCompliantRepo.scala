package models.repo

import org.openrdf.repository.RepositoryConnection
import org.openrdf.query.TupleQueryResult
import org.openrdf.query.impl.TupleQueryResultBuilder
import org.openrdf.query.QueryLanguage

trait SesameCompliantRepo {
  
	def access[T](body: RepositoryConnection => T): T
	def transaction(body: RepositoryConnection => Unit)
	def shutdown(): Unit
	
	def runSparql(query: String): TupleQueryResult = access(conn => {
		val handler = new TupleQueryResultBuilder()
		conn.prepareTupleQuery(QueryLanguage.SPARQL, query).evaluate(handler)
		handler.getQueryResult()
	})
	
}