package com.example.javamodule

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

/**
 * Created by xiaolong on 2018/6/8.
 */
class HttpServer {

    fun await() {

        try {
            val serverSocket = ServerSocket(8888, 1, InetAddress.getByName("localhost"))

            run {
                while (true) {
                    var accept: Socket? = null
                    try {
                        accept = serverSocket.accept()

                        val inputStream = accept!!.getInputStream()
                        Request(inputStream).parse()
                        val outputStream = accept.getOutputStream()

                        accept.close()
                    } catch (e: Exception) {
                        continue
                    }

                }


            }


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    companion object {
        val WEB_ROOT = ""

        val SHUT_DOWN_COMMAND = "/SHUTDOWN"
    }


}
