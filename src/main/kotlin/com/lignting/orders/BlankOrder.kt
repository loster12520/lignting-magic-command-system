package com.lignting.orders

import com.lignting.main.OrderLoader

class BlankOrder(
    override val orderPrefix: String,
    override val orderText: String,
    val function: (List<String>, OrderLoader) -> String,
    override val description:String,
    override val author:String,
    override val version:String,
    override val details:String
) : AbstractOrder() {
    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        return function(orderArgs, orderLoader)
    }

    constructor(
        orderPrefix: String,
        orderText: String,
        function: (List<String>, OrderLoader) -> String
    ):this(
        orderPrefix,orderText,function,
        "一个通过空白指令创建的初始指令",
        "unknown",
        "unknown",
        "unknown"
    )

}