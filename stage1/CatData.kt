package addLabStage1;

data class CatData(val _color: String, val _age: Int, val _weight: Int)
{
    var color: String;
    var age: Int;
    var weight: Int;
    init
    {
        color = _color;
        age = _age;
        weight = _weight;
    }
}