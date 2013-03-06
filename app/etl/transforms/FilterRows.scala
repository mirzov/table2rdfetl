package etl.transforms

import etl.ArrayTextTable
import etl.TextTable
import etl.TextTableRow

class FilterRows(orig: TextTable, filter: TextTableRow => Boolean) extends TextTable {

	override lazy val columnNames: Seq[String] = orig.columnNames
	override def rows: Seq[TextTableRow] = orig.rows.filter(filter)
	
}