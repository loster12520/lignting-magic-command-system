package com.lignting.orders

import com.lignting.main.OrderLoader

class BlankOrder(
    override val orderPrefix:String,
    override val orderText:String,
    val function:(List<String>, OrderLoader)-> String) :AbstractOrder(){
    override fun doIt(args: List<String>, orderLoader: OrderLoader): String {
        return function(args,orderLoader)
    }

}