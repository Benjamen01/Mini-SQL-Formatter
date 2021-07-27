open class Formatter {
    var i = 0
    var n = 0
    var words = 0
    var spaces = 0
    val MAX_KEY_WORD_COUNT = 4
    var value = ""
    var sqlst: Array<String> = emptyArray()
    val NEW_LINE = "newLine"
    val redList = arrayOf("<>")
    val greenList = arrayOf("[", "<=", ">=", "=", "<", ">", "]", "(", ")")
    val newLineList = arrayOf(",", ";")
    var data = Data()

    fun format(statment: String): String {
        var statment = statment
        for (r in redList.indices) statment = statment.replace(redList[r], " " + redList[r] + " ")
        for (r in greenList.indices) statment = statment.replace(greenList[r], " " + greenList[r] + " ")
        for (r in newLineList.indices) statment = statment.replace(newLineList[r], " " + newLineList[r] + " ")
        statment = statment.replace("<  =", "<=")
            .replace(">  =", ">=")
            .replace("<  >", "<>")
        statment = statment.replace("\\s+".toRegex(), " ")


        sqlst = statment.split(" ").toTypedArray()
        words = sqlst.size
        i = 0
        while (i < sqlst.size) {
            if (sqlst[i].isEmpty() || sqlst[i] == " " || sqlst[i] == "\n") {
                i++
                continue
            }
            //println(ANSI_RED_BACKGROUND+sqlst[i]+ANSI_RESET);
            val c = checkWord(sqlst[i].trim { it <= ' ' })
            if (c != 1) {
                n = 0
                checkWord2(sqlst[i].trim { it <= ' ' })
            }
            i += n
            n = 0
            i++
        }
        return value
    }

    private fun checkWord(word: String): Int { //which type
        var check = data.checkReservedTopLevelWords(word)
        //println(word);
        if (check == 1) {
            if (i > 0) addToValue(NEW_LINE)
            spaces = 0
            addToValue(ANSI_BLUE + word.toUpperCase() + ANSI_RESET)
            addToValue(NEW_LINE)
            spaces = 2
            //  println(word);
        }
        if (check == 2 && i + n + 1 < words && n <= MAX_KEY_WORD_COUNT) {
            n++
            val newWord = word + " " + sqlst[i + n]
            check = checkWord(newWord)
        }
        return check
    }

    private fun checkWord2(word: String) {
        val check = data.checkReservedNewlineWords(word)
        if (check == 0) {

            addToValue(sqlst[i])
            n = 0
            spaces = if (value.endsWith("\n")) 2 else 1
        }
        if (check == 1) {
            addToValue(NEW_LINE)
            spaces = 2
            addToValue(ANSI_PURPLE + word.toUpperCase() + ANSI_RESET)
        }
        if (check == 2 && i + n + 1 < words && n <= MAX_KEY_WORD_COUNT) {
            n++
            val newWord = word + " " + sqlst[i + n]
            checkWord2(newWord)
        }

    }

    private fun addToValue(word: String) {
        var type = word
        for (y in 0 until spaces) value += " "
        when (type) {
            "newLine" -> value += "\n"
            else -> {
                if (!type.contains(ANSI_RESET)) type = putColor(type)
                value += type
            }
        }
        if (type.contains(",") || type.contains(";")) addToValue(NEW_LINE)
    }

    private fun putColor(word: String): String { //einferben
        for (k in redList.indices) if (word.contains(redList[k])) return word.replace(
            redList[k],
            ANSI_RED + redList[k] + ANSI_RESET
        )
        for (k in greenList.indices) if (word.contains(greenList[k])) return word.replace(
            greenList[k],
            ANSI_GREEN + greenList[k] + ANSI_RESET
        )
        return word
    }

    companion object { //konstanten
        const val ANSI_RESET = "\u001B[0m"
        const val ANSI_RED = "\u001B[31m"
        const val ANSI_GREEN = "\u001B[32m"
        const val ANSI_BLUE = "\u001B[34m"
        const val ANSI_PURPLE = "\u001B[35m"
    }
}















