package com.onebit.mytraining.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by jason on 10/23/17.
 * Collection of days. Title can leave blank, others are mandatory.
 */

class Program(var title: String=" ", var date: Date, var trainer: String, var trainee: String,
              var mainGoal: String, var proGoal: String, var restrains: String,
              var days: ArrayList<Day>)