package com.lignting.main

import com.lignting.orders.AbstractOrder
import com.lignting.orders.load.JarLoadOrder
import com.lignting.orders.load.LoadOrder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.RuntimeException
import java.net.ServerSocket
import kotlin.concurrent.thread

class OrderLoader {
    val orders= mutableSetOf<AbstractOrder>()
    init {
        loadOrders()
        initServer(1770)
    }
    private fun loadOrders(){
        orders.add(LoadOrder()) // 很抱歉这是无法避免的第一条指令，用于加载加载别的指令的指令
        runOrder(
            "lignting.order",
            "load" ,
            listOf(
                JarLoadOrder::class.java.name
            )
        )
    }


    fun runOrder(text:String):String{
        val split=text.split(" ")
        val orderTag=split[0]
        val orderArgs=split.subList(1,split.size)
        return runOrder(orderTag,orderArgs)
    }
    private fun runOrder(orderTag: String, orderArgs:List<String>): String {
        orders.forEach {
            if (orderTag=="${it.orderPrefix}::${it.orderText}"){
                return it.doIt(orderArgs,this)
            }
        }
        throw RuntimeException("未知指令")
    }
    private fun initServer(port:Int){
        val serverSocket= ServerSocket(port)
        while (true){
            val socket=serverSocket.accept()
            val input= BufferedReader(InputStreamReader(socket.getInputStream()))
            val output= PrintWriter(socket.getOutputStream())
            thread {
                while (!socket.isConnected){
                    try {
                        output.println(runOrder(input.readLine()))
                    }catch (e:RuntimeException){
                        output.println(e.message)
                    }
                    output.buffered()
                    Thread.sleep(100)
                }
            }

        }
    }
    fun runOrder(orderPrefix:String,orderText:String,orderArgs:List<String>):String=
        runOrder("$orderPrefix::$orderText",orderArgs)
}