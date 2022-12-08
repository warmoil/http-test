package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler

class CustomHandler : HttpHandler {
    override fun handle(exchange: HttpExchange?) {
        if (exchange == null || exchange.requestBody == null) return

        val queryStr: String? = exchange.requestURI.query

        val queryArr = queryStr?.split("&")
        var ret = "{\n"
        queryArr?.forEachIndexed {idx,query->
            val query = query.split("=")
            ret += "\t\"${query[0]}\":\"${query[1]}\""
            if(queryArr.size>1 && queryArr.size-1 >idx){
                ret += ", \n"
            }
        }
        ret += "\n}"
        exchange.responseHeaders.set("Content-Type", "application/json")
        exchange.sendResponseHeaders(200, ret.length.toLong())
        val ost = exchange.responseBody
        ost.write(ret.toByteArray())
        ost.close()
    }

}