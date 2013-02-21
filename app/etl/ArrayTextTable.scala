package etl

trait ArrayTextTable extends TextTable{
	protected def arrays: Seq[Array[String]]
	
	override def rows: Seq[TextTableRow] = {
		val colNames = columnNames
		val nOfCols = colNames.length
		val colIndexLookup = colNames.zipWithIndex.toMap
		arrays.map{array =>
			assert(array.length == nOfCols, "TextTable must have the same number of columns in every row!")
			new ArrayTableRow(array, colIndexLookup)
		}
	}
	
	private class ArrayTableRow(row: Array[String], colIndexLookup: Map[String, Int]) extends TextTableRow{
		override def length = row.length
		def apply(i: Int) = row(i)
		def apply(colName: String) = row(colIndexLookup(colName))
	}
}