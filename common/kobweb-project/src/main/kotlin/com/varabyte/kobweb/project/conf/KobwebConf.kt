@file:Suppress("unused") // Used by serializer

package com.varabyte.kobweb.project.conf

import com.charleskorn.kaml.Yaml
import com.varabyte.kobweb.project.KobwebFolder
import com.varabyte.kobweb.project.io.KobwebReadableFile
import kotlinx.serialization.Serializable

@Serializable
class Site(val title: String)

@Serializable
class Server(
    val files: Files,
    val port: Int = 8080
) {
    /**
     * A collection of files and paths needed by the Kobweb server to serve its files.
     */
    @Serializable
    class Files(
        val dev: Dev,
        val prod: Prod,
    ) {
        /**
         * The dev server only serves a single html file that represents the whole project.
         *
         * @param contentRoot The path to serve content from, which includes the Kobweb index.html file.
         * @param script The path to the final JavaScript file generated from the user's Kotlin code.
         */
        @Serializable
        class Dev(
            val contentRoot: String,
            val script: String,
        )

        /**
         * The prod server serves static files but needs a fallback in case one is missing.
         *
         * @param siteRoot The path to the root of where the static site lives
         */
        @Serializable
        class Prod(
            val siteRoot: String,
        )
    }
}

@Serializable
class KobwebConf(
    val site: Site,
    val server: Server,
)

class KobwebConfFile(kobwebFolder: KobwebFolder) : KobwebReadableFile<KobwebConf>(
    kobwebFolder,
    "conf.yaml",
    deserialize = { text -> Yaml.default.decodeFromString(KobwebConf.serializer(), text) }
)