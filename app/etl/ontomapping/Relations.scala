package etl.ontomapping

import org.openrdf.model.URI

sealed trait Relation{
	def predicate: URI
	def key: Key
}

class RelationTo(val predicate: URI, val key: Key) extends Relation
class RelationFrom(val predicate: URI, val key: Key) extends Relation

object Relations {

	def to(predicate: URI, key: Key) = new RelationTo(predicate, key)
  
	def from(predicate: URI, key: Key) = new RelationFrom(predicate, key)
}