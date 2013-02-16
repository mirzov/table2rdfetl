package models.repo

import org.openrdf.repository.RepositoryConnection
import org.openrdf.repository.Repository
import org.openrdf.repository.sail.SailRepository
import org.openrdf.sail.memory.MemoryStore
import java.io.File
import org.openrdf.rio.RDFFormat
import org.openrdf.model.Resource

class SesameInMemoryRepo(pathToRdf: String) extends SesameCompliantRepo {
	
	private val repo: Repository = new SailRepository(new MemoryStore())
	repo.initialize()
	val factory = repo.getValueFactory
	val graph = factory.createURI("http://www.canmove.lu.se/data/prototype/")
	
	transaction(conn => {
		conn.add(new File(pathToRdf), "", RDFFormat.RDFXML, graph)
	})
	
	def access[T](body: RepositoryConnection => T): T = {
		val cxn = repo.getConnection()
		try {
			body(cxn)
		} finally {
		    cxn.close()
		}
	}
	
	def transaction(body: RepositoryConnection => Unit){
		val cxn = repo.getConnection()
		cxn.setAutoCommit(false)
		try {
			body(cxn)
			cxn.commit()
		} catch { case ex: Throwable =>
		    cxn.rollback()
		    throw ex
		} finally {
		    cxn.close()
		}
	}
	
	def shutdown(){
		repo.shutDown()
	}
}