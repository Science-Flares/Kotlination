package OOP

 class Encapsulation() {

    var capsule : Int = 0
        get() {
       return field.coerceIn(0..100)
    }
    set(value) {
        field = value.coerceIn(0..100)
    }
}
