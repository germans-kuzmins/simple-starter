package eu.simple.persistance

import eu.simple.command.CommonModuleConfig
import eu.simple.command.ModuleConfiguration
import org.springframework.context.annotation.Import

@ModuleConfiguration
@Import(CommonModuleConfig::class)
class PersistanceModuleConfig