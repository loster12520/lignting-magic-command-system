package com.lignting.orders.load

import com.lignting.main.OrderLoader
import com.lignting.orders.AbstractOrder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.net.URLClassLoader
import java.util.jar.JarFile

class JarLoadOrder : AbstractOrder() {
    override val orderPrefix = "lignting.order"
    override val orderText = "jar-load"

    override fun doIt(orderArgs: List<String>, orderLoader: OrderLoader): String {
        val successList = mutableListOf<String>()
        val failList = mutableListOf<String>()
        orderArgs.forEach {
            GlobalScope.launch {
                val subTypes = try {
                    successList.add(it)
                    getSubClass(it)
                } catch (e: RuntimeException) {
                    failList.add(it)
                    null
                }
                subTypes?.forEach {
                    orderLoader.orders.add(it.getDeclaredConstructor().newInstance())
                }
            }
        }
        return "success load: ${successList}\nfail load: $failList"
    }

    private fun getSubClass(filePath: String): Set<Class<out AbstractOrder>> {
        val classLoader = URLClassLoader(
            arrayOf(File(filePath).toURI().toURL())
        )
        val superClass = AbstractOrder::class.java
        val res = mutableSetOf<Class<out AbstractOrder>>()
        val entries = JarFile(filePath).entries()
        while (entries.hasMoreElements()) {
            val jarEntry = entries.nextElement()
            val name = jarEntry.name.let {
                if (it[0] == '/')
                    it.substring(1)
                else
                    it
            }
            if (jarEntry.isDirectory || !name.endsWith(".class"))
                continue
            val className = name.substring(0, name.length - 6).replace("/", ".")
            val clazz = classLoader.loadClass(className)
            if (!clazz.isInterface && superClass.isAssignableFrom(clazz))
                res.add(clazz as Class<out AbstractOrder>)
        }
        return res
    }

    override val description = "这是一个通过jar包路径加载插件的指令"

    override val author = "lignting"

    override val version = "1.0"

    override val details = """
        使用方法：
        ```
        lignting.order::jar-load \$\{你需要加载jar包的绝对路径（或者是相对于工作路径的相对路径）\}
        ```
    """.trimIndent()
}