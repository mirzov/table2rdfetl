package etl.ontomapping

import etl.TextTable
import org.openrdf.model.URI
import org.openrdf.model.Value
import org.openrdf.model.ValueFactory
import org.openrdf.model.Literal
import org.openrdf.model.Statement
import org.openrdf.model.vocabulary.RDF
import scala.collection.mutable.ArrayBuffer

sealed trait ColumnDestination{
	def predicate: URI
	def obj(in: String, factory: ValueFactory): Value
}

class TypedLiteralColumn(val predicate: URI, datatype: URI) extends ColumnDestination{
	def obj(in: String, factory: ValueFactory): Literal = 
		factory.createLiteral(in, datatype)
}

class PlainLiteralColumn(val predicate: URI) extends ColumnDestination{
	def obj(in: String, factory: ValueFactory): Literal = factory.createLiteral(in)
}


class TableToRdfConverter(key: Key, map: Map[String, ColumnDestination], relations: Seq[Relation]) {

	def getStatements(table: TextTable, factory: ValueFactory): Seq[Statement] = {
		val cols = table.columnNames.intersect(map.keys.toSeq)
		table.rows.flatMap(row => {
		  
			val subj = factory.createURI(key.makeKey(row).toString)
			
			val ownTypeStat = factory.createStatement(subj, RDF.TYPE, key.classUri)
			
			val relStats = for(rel <- relations) yield {
				val otherEnd = factory.createURI(rel.key.makeKey(row).toString)
				rel match {
				  case to: RelationTo => factory.createStatement(subj, to.predicate, otherEnd)
				  case from: RelationFrom => factory.createStatement(otherEnd, from.predicate, subj)
				}
			}
			
			val colValStats = for(col <- cols) yield{
				val dest = map(col)
		 		val obj = dest.obj(row(col), factory)
		 		factory.createStatement(subj, dest.predicate, obj)
			}
			
			ownTypeStat +: (relStats ++ colValStats)
		})
	}
  
}