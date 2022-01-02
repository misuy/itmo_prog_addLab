package addLabStage3;

data class CatData(val _color: String, val _age: Int, val _weight: Float)
{
    var color: String;
    var age: Int;
    var weight: Float;
    init
    {
        color = _color;
        age = _age;
        weight = _weight;
    }
}