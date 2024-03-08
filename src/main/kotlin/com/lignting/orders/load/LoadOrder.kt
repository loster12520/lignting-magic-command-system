package com.lignting.orders.load

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class LoadOrder : AbstractOrder() {
    override val orderPrefix = "lignting.order"
    override val orderText = "load"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        val successList = mutableListOf<String>()
        val failList = mutableListOf<String>()
        val superClass = AbstractOrder::class.java
        orderArgs.forEach {
            val clazz = Class.forName(it)
            if (!clazz.isInterface && superClass.isAssignableFrom(clazz)) {
                val instance = clazz.getDeclaredConstructor().newInstance() as AbstractOrder
                orderLoader.orders.forEach {
                    if (
                        it.orderPrefix == instance.orderPrefix &&
                        it.orderText == instance.orderText
                    )
                        orderLoader.orders.remove(it)
                }
                orderLoader.orders.add(instance)
                successList.add(it)
            } else
                failList.add(it)
        }
        return "success load: ${successList}\nfail load: $failList"
    }

    override val description = "这是一个通过类名加载指令类的指令"

    override val author = "lignting"

    override val version = "1.0"

    override val details = """
        使用方法：
        ```
        lignting.order::load \$\{你需要加载的指令类的完整路径\}
        ```
    """.trimIndent()
}