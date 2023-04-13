rootProject.name = "Note App"
include(":app")

//CORE-MODULES
include(":core")
include(":core:components")
include(":core:resource")
include(":core:constance")
include(":core:data")
include(":core:state-manager")
include(":core:domain")
include(":core:local-storage")
include(":core:local-storage:proto-datastore")
include(":core:local-storage:room")
include(":core:local-storage:room-db")




//TASK-MODULES
include(":task")
include(":task:data")
include(":task:domain")
include(":task:presentation")
include(":task:presentation:task-list")
include(":task:presentation:task-components")
include(":task:presentation:task-add-edit")