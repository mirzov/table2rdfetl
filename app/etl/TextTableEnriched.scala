import etl.transforms.DropColumns

package object etl{

	implicit class TextTableEnriched(tbl: TextTable) {
	  
		def getTsvString: String = {
			val str = new StringBuilder()
			str.append(tbl.columnNames.mkString("\t"))
			for(row <- tbl.rows){
			  str.append(tbl.columnNames.map(row(_)).mkString("\n", "\t", ""))
			}
			str.append("\n")
			str.toString
		}
		
		def dropColumns(cols: String*): TextTable = new DropColumns(tbl, cols:_*)
	}

}