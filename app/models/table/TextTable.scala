package models.table

trait TextTableRow extends IndexedSeq[String] {
  
	def apply(colName: String): String
	
}

trait TextTable {

	def columnNames: Seq[String]
	def rows: Seq[TextTableRow]
	
	def getTsvString: String = {
		val str = new StringBuilder()
		str.append(columnNames.mkString("\t"))
		for(row <- rows){
		  str.append(columnNames.map(row(_)).mkString("\n", "\t", ""))
		}
		str.toString
	} 
	
}

