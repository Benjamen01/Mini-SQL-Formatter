import java.util.*

class Data { // 2 type keywords
    fun checkReservedTopLevelWords(word: String): Int {
        for (s in reservedTopLevelWords) {
            if (s.equals(word, ignoreCase = true)) return 1
            if (s.toLowerCase().contains(word.toLowerCase())) return 2
        }
        return 0
    }

    fun checkReservedNewlineWords(word: String): Int {
        for (s in reservedNewlineWords) {
            if (s.equals(word, ignoreCase = true)) return 1
            if (s.toLowerCase().contains(word.toLowerCase())) return 2
        }
        return 0
    }


    val reservedTopLevelWords = Arrays.asList(
        "create Table",
        "union",
        "intersect",
        "if",
        "then",
        "else",
        "ADD",
        "EXCEPT",
        "FROM",
        "HAVING",
        "INSERT",
        "LIMIT",
        "SELECT",
        "SET",
        "UPDATE",
        "VALUES",
        "WHERE",
        "ALTER COLUMN",
        "ALTER TABLE",
        "DELETE FROM",
        "GROUP BY",
        "INSERT INTO",
        "ORDER BY"
    )


    val reservedNewlineWords = Arrays.asList(
        "any",
        "as",
        "min",
        "max",
        "index",
        "primary key",
        "values",
        "like",
        "check",
        "AND",
        "ELSE",
        "OR",
        "WHEN",  // joins
        "JOIN",
        "INNER JOIN",
        "LEFT JOIN",
        "LEFT OUTER JOIN",
        "RIGHT JOIN",
        "RIGHT OUTER JOIN",
        "CROSS JOIN",
        "NATURAL JOIN",  // non-standard joins
        "STRAIGHT_JOIN",
        "NATURAL LEFT JOIN",
        "NATURAL LEFT OUTER JOIN",
        "NATURAL RIGHT JOIN",
        "NATURAL RIGHT OUTER JOIN"
    )

}