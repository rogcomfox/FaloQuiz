package com.nusantarian.faloquiz.model

class Question {
    var mQuestions = arrayOf(
        "Burung Kakaktua Termasuk Burung Endemik?",
        "Burung Maleo Merupakan Burung Asli?",
        "Di Papua, terdapat burung yang Terkenal dengan nama?",
        "Manakah Dibawah Ini Yang tidak Termasuk kelas dari aves (burung)?",
        "Burung Kasuari merupakan burung endemik dari?"
    )
    private val mChoices = arrayOf(
        arrayOf("Sumatera", "Jawa", "Bali", "Papua"),
        arrayOf("Sulawesi", "Bali", "Nusa Tenggara", "Kalimantan"),
        arrayOf("Ayam", "Kakaktua", "Pinguin", "Cendrawasih"),
        arrayOf("Pinguin", "Merpati", "Kelelawar", "Ayam"),
        arrayOf("Jawa", "Kalimantan", "Papua", "Sulawesi")
    )

    var mCorrectAnswer = arrayOf(
        "Jawa",
        "Sulawesi",
        "Cendrawasih",
        "Kelelawar",
        "Papua"
    )

    fun getQuestion(a: Int): String {
        return mQuestions[a]
    }

    fun getChoice1(a: Int): String {
        return mChoices[a][0]
    }

    fun getChoice2(a: Int): String {
        return mChoices[a][1]
    }

    fun getChoice3(a: Int): String {
        return mChoices[a][2]
    }

    fun getChoice4(a: Int): String {
        return mChoices[a][3]
    }

    fun getCorrectAnswer(a: Int): String {
        return mCorrectAnswer[a]
    }
}