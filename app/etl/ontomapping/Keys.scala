package etl.ontomapping

import etl.TextTableRow
import org.openrdf.model.URI
import java.util.UUID
import java.net.URL

trait Key{
  
	def makeKey(row: TextTableRow): URL
	def classUri: URI
	
}

class ColumnKey(val classUri: URI, colNames: String*) extends Key {

	def makeKey(row: TextTableRow): URL = {
		val cellValues = colNames.map(row(_))
		val uuid = Key.makeUUID(cellValues.toList)
		new URL(classUri.stringValue.stripSuffix("/") + "/" + uuid)
	}
	
  
}

object Key{
  
	def columnBased(classUri: URI, colNames: String*) = new ColumnKey(classUri, colNames :_*)
  
	def makeUUID(ss: Seq[String]): String = UUID.nameUUIDFromBytes(stringsToBytes(ss)).toString
	
	
	private def stringsToBytes(ss: Seq[String]): Array[Byte] = {
		var list = List[Byte]()
		for(s <- ss){
		   for(c <- s){
			   list = c.toByte :: list
		   }
		   list = 0.toByte :: list
		}
		list.toArray
	}
}