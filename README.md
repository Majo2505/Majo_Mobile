This is a Kotlin Multiplatform project targeting Android.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
---

## Funciones traídas del examen grupal

- **Notificaciones push (FCM):** al iniciar sesión el token se guarda en Firestore (`usuarios/<id>`) y en Realtime Database (`device_tokens/<token>`). Con la app abierta llega una notificación local; al tocarla se abre `DetailActivity` con el `message_id`. Al cerrar sesión el token se borra.
- **Notificación local:** botón "Probar notificación" en la pantalla de películas (`LocalNotifier` en común, `NotificationHelper` en Android).
- **Remote Config con caché:** pantalla "Configuración remota" desde el perfil (`welcome_message`, `max_items`, `feature_enabled`). Sin internet muestra los valores guardados con el badge "offline". En películas se usan `mensaje_bienvenida` y `mostrar_promo`.
- **Dólar:** sincronización Room + Firestore (colección `dollars`) con WorkManager cada 15 minutos, y pantalla "Dólar en vivo" con Realtime Database.
- **Logs por gRPC (Wire):** el `SyncWorker` envía el resultado de la sincronización a `GrpcConfig.LOGS_URL`.
- **Analytics, encuesta (SurveyMonkey), tema claro/oscuro/alto contraste, tamaño de texto y traducciones es/en.**
- **Design System** (`designsystem/`, incluido con `includeBuild`).
- **push-backend/**: servidor Node.js para mandar notificaciones a un usuario (necesita `serviceAccountKey.json` del proyecto Firebase).

En la consola de Firebase del proyecto `majo-c7d3c` deben estar habilitados Firestore, Remote Config y Cloud Messaging.
