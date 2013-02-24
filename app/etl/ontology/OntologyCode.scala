package etl.ontology

import org.openrdf.model.URI
import org.openrdf.model.impl.URIImpl

object Ont{

	def toURI(uri: String): URI = new URIImpl(uri)


	//Classes
	val AnimalMigrationMeasurement = toURI("http://www.canmove.lu.se/data/prototype/datamodel/AnimalMigrationMeasurement")
	val Experiment = toURI("http://www.canmove.lu.se/data/prototype/datamodel/Experiment")
	val Measurement = toURI("http://www.canmove.lu.se/data/prototype/datamodel/Measurement")
	val ResearchProject = toURI("http://www.canmove.lu.se/data/prototype/datamodel/ResearchProject")
	val Researcher = toURI("http://www.canmove.lu.se/data/prototype/datamodel/Researcher")
	val Species = toURI("http://www.canmove.lu.se/data/prototype/datamodel/Species")
	val Subject = toURI("http://www.canmove.lu.se/data/prototype/datamodel/Subject")

	//Object properties
	val belongsToSpecies = toURI("http://www.canmove.lu.se/data/prototype/datamodel/belongsToSpecies")
	val conductedBy = toURI("http://www.canmove.lu.se/data/prototype/datamodel/conductedBy")
	val hasMeasurement = toURI("http://www.canmove.lu.se/data/prototype/datamodel/hasMeasurement")
	val hasSubject = toURI("http://www.canmove.lu.se/data/prototype/datamodel/hasSubject")
	val isRelatedToProject = toURI("http://www.canmove.lu.se/data/prototype/datamodel/isRelatedToProject")
	val worksOn = toURI("http://www.canmove.lu.se/data/prototype/datamodel/worksOn")

	//Datatype properties
	val latinName = toURI("http://www.canmove.lu.se/data/prototype/datamodel/latinName")
	val maxAltitude = toURI("http://www.canmove.lu.se/data/prototype/datamodel/maxAltitude")
	val name = toURI("http://www.canmove.lu.se/data/prototype/datamodel/name")
	val subjectId = toURI("http://www.canmove.lu.se/data/prototype/datamodel/subjectId")
	val timeStamp = toURI("http://www.canmove.lu.se/data/prototype/datamodel/timeStamp")
	val weight = toURI("http://www.canmove.lu.se/data/prototype/datamodel/weight")
}
