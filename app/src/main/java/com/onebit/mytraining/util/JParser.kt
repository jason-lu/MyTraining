package com.onebit.mytraining.util

import android.util.Log
import com.google.gson.Gson
import com.onebit.mytraining.model.Program

/**
 * Created by jason on 11/3/17.
 */

fun gJsonParser(obj: Program): String {
    val gson = Gson()
    return gson.toJson(obj)

}

fun gObjParser(json: String): Program {
    val gson = Gson()
    return gson.fromJson<Program>(json, Program::class.java)
}