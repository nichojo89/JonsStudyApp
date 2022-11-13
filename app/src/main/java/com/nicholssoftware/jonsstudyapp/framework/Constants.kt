package com.nicholssoftware.jonsstudyapp.framework

import com.nicholssoftware.core.data.Question
import com.nicholssoftware.jonsstudyapp.R

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()
        val que1 = Question(1,
            "A party is going on with “n” number of attendees, with only a single person known to every party attendee. This known person may be present at the party. However, this person doesn’t know anyone at the party, and we can only ask questions like, does A know B? How can you learn the stranger’s identity with the minimum number of questions asked? In how many ways can we solve this problem?",
            R.drawable.ic_party,
            "Based on the elimination method, we have the following observations: If A knows B, then A is not the person while B might be. Thus, discard A. If A doesn’t know B, then B is not the person while A might be. So, discard B. Repeat these two aforementioned steps until you’re left with only one person. Ensure that the remaining person is the person we are searching for.",
            "Each friend will ask each friend if they know each other until the unknown is found",
            "Farty party",
            "Too hard. Just give up",
            1
        )
        questionsList.add(que1)

        val que2 = Question(2,
            "The sum of n natural numbers is :",
            R.drawable.ic_whole_nums,
            "(n*(n+1))/2","(n*(n-1))/2","(n+1)/2","(n*(n+1)*(2*n+1))/6", 1
        )

        questionsList.add(que2)

        val que3 = Question(3,
            "What is the number of times we need to divide N by 2 till it reaches 1 ?",
            R.drawable.ic_ntwo,
            "ceil(logN)","floor(logN)","logN","n/2", 2
        )
        questionsList.add(que3)

        val que4 = Question(4,
            "How many elements are there between [135, 246] (inclusive of them) ?",
            R.drawable.ic_cube,
            "111","112","113","114", 2
        )
        questionsList.add(que4)

        return questionsList
    }
}