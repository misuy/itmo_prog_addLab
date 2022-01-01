package addLabStage1;

class App()
{
    val listLen = 1000;
    var keyList = arrayOfNulls<String>(listLen);
    var dataList = arrayOfNulls<CatData>(listLen);
    var emptyElements = sortedSetOf<Int>();

    init
    {
        for (i in 0..(listLen-1))
        {
            emptyElements.add(i);
        }
    }


    private fun printCat(name: String?, catData: CatData?)
    {
        var color = catData?.color;
        var age = catData?.age;
        var weight = catData?.weight;
        println("$name(color: $color, age: $age, weight: $weight);");
    }


    private fun create(name: String, color: String, age: Int, weight: Int)
    {
        var index = emptyElements.first();
        emptyElements.remove(index);
        keyList[index] = name;
        dataList[index] = CatData(color, age, weight);
    }


    private fun read(targetName: String)
    {
        for (i in 0..(listLen-1))
        {   
            var name = keyList[i];
            if (name == targetName)
            {
                var catData = dataList[i];
                printCat(name, catData);
                break;
            }
        }
    }


    private fun delete(targetName: String)
    {
        for (i in 0..(listLen-1))
        {   
            var name = keyList[i];
            if (name == targetName)
            {
                emptyElements.add(i);
                keyList[i] = null;
                dataList[i] = null;
                break;
            }
        }
    }


    private fun readAll()
    {
        for (i in 0..(listLen-1))
        {
            if (!emptyElements.contains(i))
            {
                printCat(keyList[i], dataList[i]);
            }
        }
    }


    private fun parseLine(line: String)
    {
        var splittedLine = line.split(" ");
        var command = splittedLine[0];

        when (command)
        {
            "create" -> {
                var name = splittedLine[1];
                var color = splittedLine[2];
                var age = splittedLine[3].toInt();
                var weight = splittedLine[4].toInt();
                this.create(name, color, age, weight);
            }
            "read" -> {
                var name = splittedLine[1];
                this.read(name);
            }
            "delete" -> {
                var name = splittedLine[1];
                this.delete(name);
            }
            "readall" -> {
                this.readAll();
            }
        }
    }


    fun run()
    {
        while (true)
        {
            var line = readLine();
            if (line != null)
            {
                this.parseLine(line);
            }
        }
    }
}