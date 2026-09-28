package ucb.edu.bo.earthquake.presentation.states

sealed interface EarthquakeEvents {
    object LoadEarthquakes : EarthquakeEvents
}
