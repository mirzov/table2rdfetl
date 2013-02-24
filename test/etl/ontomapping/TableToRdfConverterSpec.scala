package etl.ontomapping

import org.scalatest.FunSpec
import etl.sources.GivenTextTable
import etl.ontology.Ont
import org.openrdf.model.vocabulary.XMLSchema
import org.openrdf.model.impl.ValueFactoryImpl
import models.repo.Repo

class TableToRdfConverterSpec extends FunSpec {

	describe("TableToRdfConverter"){
	  
		val tbl = new GivenTextTable(
		    Seq("SUBJID", "WEIGHT", "SPECIES"),
		    Array("gull01", "0.97", "Some gull"),
		    Array("gull02", "0.73", "Some gull")
		)
		
		val map = Map(
		    "WEIGHT" -> new TypedLiteralColumn(Ont.weight, XMLSchema.FLOAT),
		    "SPECIES" -> new PlainLiteralColumn(Ont.belongsToSpecies)
		)
		
		val key = Key.columnBased(Ont.Species, "SUBJID")
		
		val conv = new TableToRdfConverter(map, key)
		val ss = conv.getStatements(tbl, new ValueFactoryImpl())
		
		it("emits correct number of RDF statements"){
			assert(ss.length === 6, "must have produced 6 statements")
		}
	  
	}
  
}