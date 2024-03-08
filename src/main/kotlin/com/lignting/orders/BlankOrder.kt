package com.lignting.orders

import com.lignting.main.OrderLoader

class BlankOrder(
    override val orderPrefix: String,
    override val orderText: String,
    val function: (List<String>, OrderLoader) -> String
) : AbstractOrder() {
    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        return function(orderArgs, orderLoader)
    }

}