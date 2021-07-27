


fun main(args: Array<String>) {
        println("--------------")
        println("\tWElCOME:\nMini SQL-Formatter")
        println("--------------")
        println("This is just für standart SQL!!")
        println("""Pls give an SQL input:(Don't forget to "exit" or "EXIT" end of the input)""")

        var st = ""
        while (!st.contains("exit") && !st.contains("EXIT")) {
            st += " " + readLine()!!

        }
        val formatter = Formatter()
        val sqlst: String = formatter.format(st.replace("exit", "").replace("EXIT",""))
        println(sqlst)
    }

/*
//1
SELECT supplier_name, city  FROM suppliers inner        JOIN
contacts WHERE supplier_id > 500 and x=2 or (m >=8) ORDER BY supplier_name ASC,
city DESC;SELECT supplier_name, city  fROM suppliers NATURAL LEFT OUTER JOIN contacts WHERE supplier_id <> 500 and x = 2
exit

d = 9 or x      < 6 exit
//2
update a set a.ID = b.ID, Name = b.Name from Table1 a
inner join (select ID, Name from Table2 where Active <> 0 and Current <> 0)  b
on a.ownerid = b.ownerid and a.type = b.type and b.version = '87.1'
and a.Name is Null where a.expired <> 0
EXIT

//3
Das ist Project für PUC exit

* */