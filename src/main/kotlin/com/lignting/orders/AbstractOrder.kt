package com.lignting.orders

import com.lignting.main.OrderLoader

// 指令格式："${orderPrefix}：：${orderText} ${args}"

abstract class AbstractOrder {

    // 指令前缀
    abstract val orderPrefix: String

    // 指令名
    abstract val orderText: String

    // 指令执行函数
    abstract fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String

    // 指令简单描述
    open val description = "这是一个抽象指令"

    // 作者
    open val author = "lignting"

    // 版本
    open val version = "1.0"

    // 细节，支持markdown语法
    open val details = """
        还需要我说更多的吗
    """.trimIndent()
}