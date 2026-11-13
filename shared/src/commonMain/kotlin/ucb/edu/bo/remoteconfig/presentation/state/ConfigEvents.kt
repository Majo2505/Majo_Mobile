package ucb.edu.bo.remoteconfig.presentation.state

sealed class ConfigEvents {
    data object LoadConfig : ConfigEvents()
    data object RefreshConfig : ConfigEvents()
}