import play.api._

object Global extends GlobalSettings {

	override def onStop(app: Application) {
		super.onStop(app)
		models.repo.Repo.shutdown()
	}  
    
}