package etl.sources

import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import au.com.bytecode.opencsv.CSVReader
import etl._

class TsvDataTable(file: File) extends ArrayTextTable {

	private val ioReader = new BufferedReader(new FileReader(file))
	private val reader = new CSVReader(ioReader, '\t')
	
	override val columnNames: Seq[String] = reader.readNext().toSeq
	
	final override protected def arrays: Stream[Array[String]] = {
		val row = reader.readNext()
		if(row == null) {
			reader.close()
			Stream.empty 
		}
		else Stream.cons(row, arrays)
	}
	
}