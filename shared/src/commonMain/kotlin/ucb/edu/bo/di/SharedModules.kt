import org.koin.core.module.Module
import ucb.edu.bo.di.dataModule
import ucb.edu.bo.di.domainModule
import ucb.edu.bo.di.presentationModule

fun sharedModules(): List<Module> = listOf(
    dataModule,
    presentationModule,
    domainModule
)
