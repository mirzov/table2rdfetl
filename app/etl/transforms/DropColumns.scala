package etl.transforms

import scala.collection.Seq
import etl._

class DropColumns(orig: TextTable, columns: String*) extends ArrayTextTable {
  
	override val columnNames: Seq[String] = orig.columnNames.diff(columns)

	private val nCols = columnNames.length
	
	private val indicesToKeep = orig.columnNames.zipWithIndex.collect{
	  								case (col, i) if !columns.contains(col) => i
	  							}.toArray
	
	override def arrays: Seq[Array[String]] = orig.rows.map{ row =>
		indicesToKeep.map(row(_))
	}
}