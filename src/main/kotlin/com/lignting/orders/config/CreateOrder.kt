package com.lignting.orders.config

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class CreateOrder : AbstractOrder() {

    override val orderPrefix = "lignting.config"

    override val orderText = "create"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        val successList = mutableListOf<String>()
        val failList = mutableListOf<String>()
        orderArgs.forEach {
            configurationPool[it] = mutableMapOf()
            if (configurationPool.containsKey(it))
                successList.add(it)
            else
                failList.add(it)
        }
        return "success load: ${successList}\nfail load: $failList"
    }
}