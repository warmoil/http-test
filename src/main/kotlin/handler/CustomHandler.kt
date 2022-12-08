package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler

class CustomHandler : HttpHandler {
    override fun handle(exchange: HttpExchange?) {
        if (exchange == null || exchange.requestBody == null) return

        val queryStr = exchange.requestURI.query

        val queryArr = queryStr.split("&")
        var ret = "{"
        queryArr.forEach {
            val query = it.split("=")
            ret += "\"${query[0]}\":\"${query[1]}\""
        }
        ret += "}"
        exchange.responseHeaders.set("Content-Type", "application/json")
        exchange.sendResponseHeaders(200, ret.length.toLong())

        val ost = exchange.responseBody
        ost.write(ret.toByteArray())
        ost.close()
    }
}