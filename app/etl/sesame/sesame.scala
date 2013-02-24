package etl

import org.openrdf.repository.RepositoryResult

package object sesame {
	
	def repResultToStream[T](repRes: RepositoryResult[T]): Stream[T] = 
		if(!repRes.hasNext){
			repRes.close()
			Stream.empty[T]
		}else Stream.cons(repRes.next, repResultToStream(repRes))
		

	implicit class RepositoryResultEnriched[T](repRes: RepositoryResult[T]){
		
		def toStream: Stream[T] = repResultToStream(repRes)
		
	  
	}
  
}