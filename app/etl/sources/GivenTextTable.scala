package etl.sources

import etl.ArrayTextTable

class GivenTextTable(
    override val columnNames: Seq[String],
    override protected val arrays: Array[String]*
) extends ArrayTextTable