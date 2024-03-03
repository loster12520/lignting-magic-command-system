package com.lignting.orders

import com.lignting.main.OrderLoader

abstract class AbstractOrder {
    abstract val orderPrefix:String
    abstract val orderText:String
    abstract fun doIt(args:List<String>, orderLoader:OrderLoader):String
}