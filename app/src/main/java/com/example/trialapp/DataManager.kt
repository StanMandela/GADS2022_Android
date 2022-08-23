package com.example.trialapp

import java.util.*

object DataManager {
    val courses = HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()
    //initializer block
    init {
        initializeCourses()

    }

    private fun initializeCourses(){
        var course =CourseInfo("android_intents","Android Programing with Intents")
        courses.set(course.courseId,course)
         course =CourseInfo("android_async","Android Async Programing and Services")
        courses.set(course.courseId,course)
        course =CourseInfo(title = "Java Fundamentals:The Java Language",courseId="java_lang")
        courses.set(course.courseId,course)
        course =CourseInfo(courseId="java_core",title = "Java Fundamentals: The Core Platform")
        courses.set(course.courseId,course)


    }
}