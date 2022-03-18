package com.a1218v.binpo

open class TypeCast {
    fun a() {}
}

class TypeCast2 : TypeCast() {
    var a = 0
    fun b() {}
}

class TypeCast3 : TypeCast() {

}

class Test {
    init {
        val tc: TypeCast = TypeCast2()
        val tc2: TypeCast2 = tc as TypeCast2 // type cast example
        tc.b()

        val typeCast = TypeCast2()


        // with and apply are syntax constructions for shortening syntax
        with(typeCast) {
            a() // same as typeCast.a()
            b()
            a = 10 // with doesn't apply changes to typeCast field a
        }

        typeCast.apply {
            a()
            b()
            a = 10 // apply applies changes to typeCast field a
        }
    }


}