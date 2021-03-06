package com.gas.ext


/**
 * 发现好多地方需要 tryCatch 以保证代码安全性
 * 但是我们又不关心它的运行结果，所以单独写了一个方法
 * 将需要运行的代码块用 tryCatch 包裹起来
 */
fun <T : Any> T.runInTryCatch(block: () -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}
/**
 * 不执行任何操作的闭包。
 */
val nop: Any.() -> Unit = {}

/**
 * 给一个Int色值加上一个透明度。
 * (重载掉原来的透明度)
 */
fun Int.withAlpha(alpha: Float): Int {
    require(alpha in 0f..1.0f)
    return ((this shl 8) shr 8) + ((0xff * alpha).toInt() shl 24)
}

/**
 * 获取 IntRange 里边可能出现的整数的个数, 范围为闭区间.
 * 比如说 3..5 的 Range 代表可以取 3, 4, 5 三个值,
 * 所以这里的 intCount 值为 3
 */
val IntRange.intCount: Int
    get() = last - first + 1

/**
 * 给定一个整数的Range, 随机返回属于这个Range的整数值.
 */
fun IntRange.random() = start + (Math.random() * intCount).toInt()

/**
 * 随机返回一个Array里边的一项.
 * 如果Array为空, 会抛异常
 */
fun <T> Array<T>.randomItem(): T {
    if (isEmpty()) {
        throw ArrayIndexOutOfBoundsException("array is empty")
    }

    return get((0 until size).random())
}

/**
 * 随机返回一个Array里边的一项.
 * 如果Array为空, 会返回null
 */
fun <T> Array<T>.randomItemOrNull() = try {
    randomItem()
} catch (e: ArrayIndexOutOfBoundsException) {
    null
}

fun <T> List<T>.randomItem(): T {
    if (isEmpty()) {
        throw ArrayIndexOutOfBoundsException("list is empty")
    }

    return get((0 until size).random())
}

/**
 * 随机返回一个List里边的一项.
 * 如果List为空, 会返回null
 */
fun <T> List<T>.randomItemOrNull() = try {
    randomItem()
} catch (e: ArrayIndexOutOfBoundsException) {
    null
}

/**
 * 判断一个类是否是另一个类的子类，或者实现。
 *
 * 例如：
 * class SomeClass extends BaseClass implement Interface
 * class SomeChildClass extends SomeClass 的情况下，
 *
 * 下述几种情况都会返回true。
 *
 * isInstanceOf(SomeClass.class, SomeClass.class)
 * isInstanceOf(SomeClass.class, BaseClass.class)
 * isInstanceOf(SomeClass.class, Interface.class)
 * isInstanceOf(SomeChildClass.class, BaseClass.class)
 * isInstanceOf(SomeChildClass.class, Interface.class)
 * isInstanceOf(SomeChildClass.class, SomeClass.class)
 *
 * @param cls  要判断的class
 * @param type 要判断的cls是不是符合的类型。
 * @return 如果cls是type的子类或者实现（包括多重继承的情况），返回true，否则返回false。
 */
fun isInstanceOf(cls: Class<*>?, type: Class<*>): Boolean {
    var clss = cls
    while (clss != null) {
        if (clss == type) {
            return true
        }
        val interfaces = clss.interfaces
        for (ifs in interfaces) {
            if (ifs == type) {
                return true
            }
        }
        clss = clss.superclass
    }
    return false
}

/**
 * 判断一个对象属于的类是否是另一个类的子类或者实现。
 *
 * 类似于[.isInstanceOf]，但是参数略有不同。
 *
 * @param obj  要判断的对象
 * @param type 要判断的对象是不是符合的类型。
 * @return 如果obj所属的类是cls的子类或者实现（包括多重继承的情况），返回true，否则返回false。
 */
fun isInstanceOf(obj: Any?, type: Class<*>): Boolean {
    return obj != null && isInstanceOf(obj.javaClass, type)
}