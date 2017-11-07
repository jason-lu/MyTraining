package com.onebit.mytraining.util

import com.onebit.mytraining.model.Exercise
import com.onebit.mytraining.model.Program
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jason on 11/3/17.
 */
fun parsePrograms(json: String) : ArrayList<Program>?{
    val formatter = SimpleDateFormat("dd/mm/yyyy")
    val jsonObj = JSONObject(json)
    val title = jsonObj.getString("title")
    val date = formatter.parse(jsonObj.getString("date"))
    val trainer = jsonObj.getString("trainer")
    val trainee = jsonObj.getString("trainee")
    val mainGoal = jsonObj.getString("mainGoal")
    val proGoal = jsonObj.getString("proGoal")
    val days = jsonObj.getJSONArray("days")
    var proDays = ArrayList<Exercise>()

    return null
}