package com.onebit.mytraining.util


import com.google.gson.Gson
import com.onebit.mytraining.model.Program

/**
 * Created by jason on 11/3/17.
 */

fun gJsonParser(obj: Program): String = Gson().toJson(obj)

fun gObjParser(json: String): Program = Gson().fromJson<Program>(json, Program::class.java)

fun parsePrograms(programs: ArrayList<Program>): String = Gson().toJson(programs)

fun parseJsons(json: String): ArrayList<Program> {
    val programs: ArrayList<Program> = ArrayList();
    programs.addAll(Gson().fromJson(json, Array<Program>::class.java))

    return programs
}