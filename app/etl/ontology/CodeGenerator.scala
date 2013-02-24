package etl.ontology

import org.openrdf.repository.RepositoryConnection
import org.openrdf.model.vocabulary._
import etl.sesame._
import java.net.URL
import org.openrdf.model.URI
import org.openrdf.model.impl.URIImpl

object CodeGenerator {
  
	def generate(conn: RepositoryConnection): String = {

		val sb = new StringBuilder
		
		sb.append("package etl.ontology\n\n")
		
		sb.append("import org.openrdf.model.URI\n")
		sb.append("import org.openrdf.model.impl.URIImpl\n\n")
		
		sb.append("object Ont{\n\n")
		
		sb.append("\tdef toURI(uri: String): URI = new URIImpl(uri)\n\n")
		
		val nsMap = conn.getNamespaces.toStream.map{ns => (ns.getName, ns.getPrefix)}.toMap
		
		def getUrisOfType(uriType: URI): Seq[String] = 
			conn.getStatements(null, RDF.TYPE, uriType, true).toStream
			.map(st => st.getSubject).collect{
				case uri: URI => nsMap.getOrElse(uri.getNamespace, uri.getNamespace) + ":" + uri.getLocalName
			}
		
		def printUrisOfType(uriType: URI, comment: String){
			sb.append("\n\t//" + comment + "\n")
			for(uri <- getUrisOfType(uriType)){
				val name = uri.split(":").last
				sb.append(s"""\tval $name = toURI("$uri")\n""")
			}
		}
		
		printUrisOfType(OWL.CLASS, "Classes")
		printUrisOfType(OWL.OBJECTPROPERTY, "Object properties")
		printUrisOfType(OWL.DATATYPEPROPERTY, "Datatype properties")
		
		sb.append("}\n")
		
		sb.toString
	}

}