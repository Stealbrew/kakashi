package io.github.stealbrew.plugins

import io.github.stealbrew.utils.requestPrincipal
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import kotlin.time.seconds

fun Application.configureRateLimiter() {
    install(RateLimit) {
        global {
            requestKey { call ->
                call.requestPrincipal ?: PrincipalResult.Unauthorized
            }

            rateLimiter { _, key ->
                when (key) {
                    is PrincipalResult.Authorized -> RateLimiter.Unlimited
                    else -> RateLimiter.default(30, 30.seconds)
                }
            }
        }
    }
}
