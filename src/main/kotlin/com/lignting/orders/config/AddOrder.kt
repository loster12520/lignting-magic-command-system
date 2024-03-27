package com.lignting.orders.config

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class AddOrder : AbstractOrder() {

    override val orderPrefix = "lignting.config"

    override val orderText = "add"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        val a = configurationPool[orderArgs[0]]
        configurationPool[orderArgs[0]]?.set(orderArgs[1], orderArgs[2])
        return ""
    }
}