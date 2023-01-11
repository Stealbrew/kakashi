fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/ws") {
            // handle incoming text frames
            incoming.filterIsInstance<Frame.Text>().map { it.readText() }
                .filter { text -> text.equals("bye", ignoreCase = true) }
                .onEach { text -> outgoing.send(Frame.Text("YOU SAID: $text")) }
                .onEach { close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE")) }
        }
    }
}
