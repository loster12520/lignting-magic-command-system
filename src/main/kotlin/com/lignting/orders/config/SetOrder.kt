package com.lignting.orders.config

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class SetOrder : AbstractOrder() {
    override val orderPrefix = "lignting.config"
    override val orderText = "set"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        configurationPool[orderArgs[0]]?.set(orderArgs[1], orderArgs[2])
        return ""
    }
}