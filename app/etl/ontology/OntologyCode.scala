package etl.ontology

import org.openrdf.model.URI
import org.openrdf.model.impl.URIImpl

object Ont{

	def toURI(uri: String): URI = new URIImpl(uri)


	//Classes
	val AnimalMigrationMeasurement = toURI("dm:AnimalMigrationMeasurement")
	val Experiment = toURI("dm:Experiment")
	val Measurement = toURI("dm:Measurement")
	val ResearchProject = toURI("dm:ResearchProject")
	val Researcher = toURI("dm:Researcher")
	val Species = toURI("dm:Species")
	val Subject = toURI("dm:Subject")

	//Object properties
	val belongsToSpecies = toURI("dm:belongsToSpecies")
	val conductedBy = toURI("dm:conductedBy")
	val hasMeasurement = toURI("dm:hasMeasurement")
	val hasSubject = toURI("dm:hasSubject")
	val isRelatedToProject = toURI("dm:isRelatedToProject")
	val worksOn = toURI("dm:worksOn")

	//Datatype properties
	val latinName = toURI("dm:latinName")
	val maxAltitude = toURI("dm:maxAltitude")
	val name = toURI("dm:name")
	val subjectId = toURI("dm:subjectId")
	val timeStamp = toURI("dm:timeStamp")
	val weight = toURI("dm:weight")
}
