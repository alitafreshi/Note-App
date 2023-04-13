object Modules {

    const val app = ":app"


    //core
    const val constance = ":core:constance"
    const val stateManager = ":core:state-manager"
    const val resources = ":core:resource"
    const val components = ":core:components"
    const val data = ":core:data"
    const val domain = ":core:domain"

    //core - local-storage
    const val protoDatastore = ":core:local-storage:proto-datastore"
    const val room = ":core:local-storage:room"
    const val roomDb = ":core:local-storage:room-db"

    //task
    const val taskList = ":task:presentation:task-list"
    const val taskDomain = ":task:domain"
    const val taskData = ":task:data"
    const val taskComponents = ":task:presentation:task-components"
    const val taskAdEdit = ":task:presentation:task-add-edit"

}