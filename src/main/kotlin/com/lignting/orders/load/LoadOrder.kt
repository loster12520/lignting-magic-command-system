package com.lignting.orders.load

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder

class LoadOrder : AbstractOrder() {
    override val orderPrefix="lignting.order"
    override val orderText="load"

    override fun doIt(args: List<String>, orderLoader: OrderLoader): String {
        val successList= mutableListOf<String>()
        val failList= mutableListOf<String>()
        val superClass= AbstractOrder::class.java
        args.forEach {
            val clazz=Class.forName(it)
            if (!clazz.isInterface&&superClass.isAssignableFrom(clazz)){
                val instance=clazz.getDeclaredConstructor().newInstance() as AbstractOrder
                orderLoader.orders.forEach {
                    if (
                        it.orderPrefix==instance.orderPrefix&&
                        it.orderText==instance.orderText
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
}