package etl.ontomapping

import etl.TextTable
import org.openrdf.model.URI
import org.openrdf.model.Value
import org.openrdf.model.ValueFactory
import org.openrdf.model.Literal
import org.openrdf.model.Statement
import org.openrdf.model.vocabulary.RDF

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

class RelationColumn(val predicate: URI, targetKey: Key) extends ColumnDestination{
	def obj(in: String, factory: ValueFactory): URI = factory.createURI(in)
}

class ReverseRelationColumn(val predicate: URI, targetKey: Key) extends ColumnDestination{
	def obj(in: String, factory: ValueFactory): URI = factory.createURI(in)
}

class TableToRdfConverter(map: Map[String, ColumnDestination], key: Key) {

	def getStatements(table: TextTable, factory: ValueFactory): Seq[Statement] = {
		val cols = table.columnNames.intersect(map.keys.toSeq)
		(for(row <- table.rows;
			subj = factory.createURI(key.makeKey(row).toString);
			col <- cols;
			dest = map(col)
		) yield dest match {
		 	case dest: ReverseRelationColumn => 
		 		val predicate = dest.predicate;
		 		val obj = dest.obj(row(col), factory)
		 		factory.createStatement(obj, predicate, subj)
		 	case dest =>
		 		val predicate = dest.predicate;
		 		val obj = dest.obj(row(col), factory)
		 		factory.createStatement(subj, predicate, obj)
		}) ++
		(for(row <- table.rows) yield {
			val subj = factory.createURI(key.makeKey(row).toString);
			factory.createStatement(subj, RDF.TYPE, key.classUri)
		})
	}
  
}