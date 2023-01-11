package io.github.stealbrew.utils

import io.github.stealbrew.plugins.PrincipalResult
import io.ktor.server.application.*

val ApplicationCall.requestPrincipal: PrincipalResult?
    get() = authentication.principal()

val ApplicationCall.isAuthorized: Boolean
    get() = requestPrincipal?.isAuthorized == true
