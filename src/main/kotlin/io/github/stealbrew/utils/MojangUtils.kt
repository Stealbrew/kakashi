package io.github.stealbrew.utils

import io.github.stealbrew.api.exceptions.RouteError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.*
import java.util.UUID

@Serializable
data class SimpleMojangProfile(
    @Contextual
    val uuid: UUID,
    val username: String
)

object MojangUtils {
    suspend fun getProfile(uuid: UUID): Result<SimpleMojangProfile> {
        return getProfile(uuid.toString())
    }

    suspend fun getProfile(query: String): Result<SimpleMojangProfile> {
        val res = client.get<SimpleProxyAshconProfile> {
            url {
                host = "api.ashcon.app"
                path("mojang", "v2", "user", query)
                protocol = URLProtocol.HTTPS
            }
        }

        if (!res.status.isSuccess()) {
            return if (query.length > 16) {
                getProfileFromUUID(UUID.fromString(query))
            } else {
                getProfileFromName(query)
            }
        }

        return Result.success(res.toMojangProfile())
    }

    private suspend fun getProfileFromUUID(uuid: UUID): Result<SimpleMojangProfile> {
        val res = client.get<SimpleProxyMojangProfile> {
            url {
                host = "sessionserver.mojang.com"
                path("session", "minecraft", "profile", uuid.toString())
                protocol = URLProtocol.HTTPS
            }
        }

        if (!res.status.isSuccess() || res.status == HttpStatusCode.NoContent) {
            return Result.failure(RouteError(res.status.value, "mojang api uuid fetch failed for $uuid"))
        }
        return Result.success(res.toMojangProfile())
    }

    private suspend fun getProfileFromName(name: String): Result<SimpleMojangProfile> {
        val res = client.get<SimpleProxyMojangProfile> {
            url {
                host = "api.mojang.com"
                path("users", "profiles", "minecraft", name)
                protocol = URLProtocol.HTTPS
            }
        }

        if (!res.status.isSuccess() || res.status == HttpStatusCode.NoContent) {
            return Result.failure(RouteError(res.status.value, "mojang api name fetch failed for $name"))
        }
        return Result.success(res.toMojangProfile())
    }

    @Serializable
    private data class SimpleProxyAshconProfile(
        @Contextual
        @SerialName("uuid")
        val uuid: UUID,

        @SerialName("username")
        val username: String
    ) : ISimpleProfile {
        fun toMojangProfile() = SimpleMojangProfile(uuid, username)
    }
    
    @Serializable
    private data class SimpleProxyMojangProfile(
        @Contextual
        @SerialName("id")
        val uuid: UUID,

        @SerialName("name")
        val username: String
    ) : ISimpleProfile {
        fun toMojangProfile() = SimpleMojangProfile(uuid, username)
    }

    private interface ISimpleProfile
}
