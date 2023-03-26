object Compose {
    //COMPOSE BOOM DEPENDENCY
    private const val compose_boom_version = "2023.03.00"
    const val composeBoom = "androidx.compose:compose-bom:$compose_boom_version"

    //COMPOSE DEPENDENCIES
    const val compose_material_2 = "androidx.compose.material:material"
    const val compose_preview = "androidx.compose.ui:ui-tooling-preview"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling"

    private const val compose_activity_version = "1.6.1"
    const val compose_activity = "androidx.activity:activity-compose:$compose_activity_version"

    private const val compose_viewMode_version = "2.6.0"
    const val compose_viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$compose_viewMode_version"

    private const val compose_constraint_layout_version = "1.0.1"
    const val compose_constraint_layout =
        "androidx.constraintlayout:constraintlayout-compose:$compose_constraint_layout_version"


    //COMPOSE COMPILER DEPENDENCY
    const val compose_compiler_version = "1.3.2"
    const val compose_compiler = "androidx.compose.compiler:compiler:$compose_compiler_version"

    //COMPOSE VIEWBINDING
    const val compose_view_binding_version = "1.4.0"
    const val compose_view_binding =
        "androidx.compose.ui:ui-viewbinding:$compose_view_binding_version"
}
