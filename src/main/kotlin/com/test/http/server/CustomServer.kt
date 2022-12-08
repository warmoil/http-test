package com.test.http.server

import com.sun.net.httpserver.HttpServer
import com.test.http.handler.CustomHandler
import java.net.InetSocketAddress

class CustomServer(
    private val port: Int = 8080,
) {
    init {
        println("${this.javaClass.name} on port:$port")
        val server = HttpServer.create(InetSocketAddress(port), 0)
        server.createContext("/hello", CustomHandler())
        server.executor = null
        server.start()
    }

}