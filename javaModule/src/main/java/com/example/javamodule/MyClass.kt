package com.example.javamodule

object MyClass {
    @JvmStatic
    fun main(args: Array<String>) {
        //        new HttpServer().await();

        val str = "1234189441789"

        val length = str.length
        val i = str.indexOf("2")
        val substring = str.substring(1, length)
        System.err.println(i)

    }
}
