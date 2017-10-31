package com.onebit.mytraining.model

/**
 * Created by jason on 10/24/17.
 * It describes exercises to do in a day or session.
 * Rest can leave blank. Other fields are mandatory.
 */
class Exercise(var order: String, var name: String, var reps: Int,
               var sets: Int, var tempo: String="", var rest: Int =0)