package com.lignting.orders.config

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class ListOrder : AbstractOrder() {

    override val orderPrefix = "lignting.config"

    override val orderText = "list"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        val res = StringBuffer()
        configurationPool.forEach {
            res.append("- ${it.key}")
            it.value.forEach {
                res.append("  - ${it.key}:\t${it.value}")
            }
        }
        return res.toString()
    }
}
