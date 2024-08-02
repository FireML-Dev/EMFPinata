rootProject.name = "EMFPinata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("paper-api", "io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
            library("evenmorefish", "com.oheers:EvenMoreFish:1.7.2")
            library("commandapi", "dev.jorel:commandapi-bukkit-shade-mojang-mapped:9.5.1")

            plugin("shadow", "io.github.goooler.shadow").version("8.1.8")
            plugin("plugin-yml", "net.minecrell.plugin-yml.paper").version("0.6.0")
        }
    }
}