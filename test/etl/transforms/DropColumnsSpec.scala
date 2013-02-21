package etl.transforms

import etl.sources.GivenTextTable
import org.scalatest.FunSpec
import etl._

class DropColumnsSpec extends FunSpec {

	val orig = new GivenTextTable(
	    Seq("A", "B"),
	    Array("a1", "b1"),
	    Array("a2", "b2")
	)
  
	describe("Dropping a single column of two works as expected:"){
		
		val tbl = orig.dropColumns("A")
		
		it("leaves only one column"){
			assert(tbl.columnNames === Seq("B"))
		}
		
		it("leaves single-cell rows"){
			assert(tbl.rows === Seq(Seq("b1"), Seq("b2")))
		}
		
		it("preserves number of rows"){
			assert(tbl.rows.length === orig.rows.length)
		}
	}
	
	describe("Dropping all columns works as expected:"){
		val tbl = orig.dropColumns("A", "B")
		
		it("leaves no columns"){
			assert(tbl.columnNames === Seq())
		}
		
		it("preserves number of rows"){
			assert(tbl.rows.length === orig.rows.length)
		}
	}
	
	describe("Dropping non-existant columns leaves the table unaffected:"){
		val tbl = orig.dropColumns("non-existant, fake column")
		
		it("preserves columns"){
			assert(tbl.columnNames === orig.columnNames)
		}
		
		it("preserves rows"){
			assert(tbl.rows === orig.rows)
		}
	}
	
}