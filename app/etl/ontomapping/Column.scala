package etl.ontomapping

import org.openrdf.model._
import org.openrdf.model.vocabulary.XMLSchema

sealed trait Column{
	def predicate: URI
	def obj(in: String, factory: ValueFactory): Value
}

class TypedLiteralColumn(val predicate: URI, datatype: URI) extends Column{
	def obj(in: String, factory: ValueFactory): Literal = 
		factory.createLiteral(in, datatype)
}

class PlainLiteralColumn(val predicate: URI) extends Column{
	def obj(in: String, factory: ValueFactory): Literal = factory.createLiteral(in)
}

object Column {

	def plain(predicate: URI) = new PlainLiteralColumn(predicate)
	def float(predicate: URI) = new TypedLiteralColumn(predicate, XMLSchema.FLOAT)
}

