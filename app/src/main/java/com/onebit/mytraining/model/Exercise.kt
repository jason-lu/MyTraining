package com.onebit.mytraining.model

/**
 * Created by jason on 10/24/17.
 * It describes exercises to do in a day or session.
 * Rest can leave blank. Other fields are mandatory.
 */
class Exercise(var order: String, name: String, reps: Int,
               sets: Int, tempo: String="", rest: Int =0)