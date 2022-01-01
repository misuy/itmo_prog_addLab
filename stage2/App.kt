package addLabStage2;

class App()
{
    var list = mutableListOf<Pair<String, CatData>>();


    private fun printCat(name: String?, catData: CatData?)
    {
        var color = catData?.color;
        var age = catData?.age;
        var weight = catData?.weight;
        println("$name(color: $color, age: $age, weight: $weight);");
    }


    private fun findInList(name: String): Pair<String, CatData>?
    {
        var match: Pair<String, CatData>? = null;
        for (element in list)
        {
            if (element.first == name)
            {
                match = element;
                break;
            }
        }
        return(match);
    }


    private fun read(targetName: String)
    {
        var match: Pair<String, CatData>? = this.findInList(targetName);
        if (match != null)
        {
            printCat(match.first, match.second);
        }
        else
        {
            println("Read: not found");
        }
    }

    private fun create(name: String, color: String, age: Int, weight: Float)
    {
        if (this.findInList(name) != null)
        {
            println("Create: already exists");
        }
        else
        {
            list.add(Pair(name, CatData(color, age, weight)));
            this.read(name);
        }
    }


    private fun delete(targetName: String)
    {
        var match: Pair<String, CatData>? = this.findInList(targetName);
        if (match != null)
        {
            list.remove(match);
            println("OK");
        }
        else
        {
            println("Delete: not found");
        }

    }


    private fun readAll()
    {
        for (element in list)
        {
            printCat(element.first, element.second);
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
                var weight = splittedLine[4].toFloat();
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