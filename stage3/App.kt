package addLabStage3;
import kotlin.math.*;


class App()
{
    val hashTableSize = 1000;
    val epsilon = 0.001;
    var hashTable: Array<MutableList<Pair<String, CatData>>> = Array<MutableList<Pair<String, CatData>>>(hashTableSize, {_ -> mutableListOf<Pair<String, CatData>>()});



    private fun hashFun(name: String): Int
    {
        var hash = 0;
        for (ch in name)
        {
            hash += ch.hashCode();
        }
        hash %= hashTableSize;
        return(hash);
    }


    private fun printCat(name: String?, catData: CatData?)
    {
        var color = catData?.color;
        var age = catData?.age;
        var weight = catData?.weight;
        println("$name(color: $color, age: $age, weight: $weight);");
    }


    private fun findInHashTable(name: String): Pair<String, CatData>?
    {
        var match: Pair<String, CatData>? = null;
        for (element in hashTable[hashFun(name)])
        {
            if (element.first == name)
            {
                match = element;
                break;
            }
        }
        return(match);
    }


    private fun read(name: String)
    {
        var match: Pair<String, CatData>? = this.findInHashTable(name);
        if (match != null)
        {
            printCat(match.first, match.second);
        }
        else
        {
            println("Read: not found");
        }
    }


    private fun readAll()
    {
        for (i in 0..(hashTableSize-1))
        {
            for (element in hashTable[i])
            {
                printCat(element.first, element.second);
            }
        }
    }


    private fun create(name: String, color: String, age: Int, weight: Float)
    {
        if (this.findInHashTable(name) == null)
        {
            hashTable[hashFun(name)].add(Pair(name, CatData(color, age, weight)));
            this.read(name);
        }
        else
        {
            println("Create: already exists");
        }
    }


    private fun update(name: String, color: String, age: Int, weight: Float)
    {
        var match: Pair<String, CatData>? = this.findInHashTable(name);
        if (match != null)
        {
            hashTable[hashFun(name)].remove(match);
            hashTable[hashFun(name)].add(Pair(name, CatData(color, age, weight)));
            print("Updated: ");
            this.read(name);
        }
        else
        {
            println("Update: not found");
        }
    }


    private fun delete(name: String)
    {
        var match: Pair<String, CatData>? = this.findInHashTable(name);
        if (match != null)
        {
            hashTable[hashFun(name)].remove(match);
            println("OK");
        }
        else
        {
            println("Delete: not found");
        }
    }


    private fun where(weight: Float)
    {
        for (i in 0..(hashTableSize-1))
        {
            for (element in hashTable[i])
            {
                if (abs(element.second.weight - weight) < epsilon)
                {
                    printCat(element.first, element.second);
                }
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
            "update" -> {
                var name = splittedLine[1];
                var color = splittedLine[2];
                var age = splittedLine[3].toInt();
                var weight = splittedLine[4].toFloat();
                this.update(name, color, age, weight);
            }
            "readall" -> {
                this.readAll();
            }
            "where" -> {
                var weight = splittedLine[1].split("=")[1].toFloat();
                this.where(weight);
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