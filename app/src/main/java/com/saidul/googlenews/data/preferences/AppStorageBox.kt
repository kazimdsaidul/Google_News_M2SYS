package com.saidul.googlenews.data.preferences

import android.content.Context

import com.orhanobut.hawk.Hawk

/**
 * Created by Kazi Md. Saidul Email: Kazimdsaidul@gmail.com  Mobile: +8801675349882 on 2019-09-05.
 */
object AppStorageBox {

    enum class Key {
        KEY_USERNAME,
        KEY_PASSWORD,
        KEY_USER
    }

    fun put(context: Context, key: Key, value: Any) {
        Hawk.init(context).build()
        Hawk.put(key.name, value)
        Hawk.destroy()
    }

    operator fun get(context: Context, key: Key): Any {
        Hawk.init(context).build()
        val data = Hawk.get<Any>(key.name)
        Hawk.destroy()
        return data
    }

    fun delete(context: Context, key: Key) {
        Hawk.init(context).build()
        Hawk.delete(key.name)
        Hawk.destroy()
    }

    fun check(context: Context, key: Key): Boolean {
        Hawk.init(context).build()
        return Hawk.contains(key.name)
    }


}

