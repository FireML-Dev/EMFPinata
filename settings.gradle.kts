rootProject.name = "EMFPinata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("paper-api", "io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
            library("evenmorefish", "com.oheers:EvenMoreFish:1.7.3")
            library("commandapi", "dev.jorel:commandapi-bukkit-shade-mojang-mapped:9.5.3")
            library("bstats", "org.bstats:bstats-bukkit:3.0.2")
            library("mythicmobs", "io.lumine:Mythic-Dist:5.7.1")

            plugin("shadow", "com.gradleup.shadow").version("8.3.1")
            plugin("plugin-yml", "net.minecrell.plugin-yml.bukkit").version("0.6.0")
        }
    }
}