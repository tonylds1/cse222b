//
//  tasks.kt
//  Week 5 Tasks
//
//  You need to write code to complete the functions below to complete each task.
//
//  task4() is meant to be open-ended. There are probably many ways to solve it. Use creativity
//  to come to a good solution
//

enum class IntOperationType {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

typealias IntOperation = (Int, Int) -> Int

typealias IntOperationMap = Map<IntOperationType, IntOperation>

//  Task 0
//
//  Lambda functions can be stored in a Map, just like any other type of variable
//
//  In this task, create a Map that contains operations for all types in IntOperationType
//  The key of the map will be an IntOperationType, and the value will be an IntOperation
fun task0(): IntOperationMap {
    return mapOf(
        IntOperationType.ADD to { a, b -> a + b },
        IntOperationType.SUBTRACT to { a, b -> a - b },
        IntOperationType.MULTIPLY to { a, b -> a * b },
        IntOperationType.DIVIDE to { a, b -> if (b != 0) a / b else throw IllegalArgumentException("Division by zero!") }
    )
}

typealias IntResult = () -> Int

//  Task 1
//
//  Higher-order functions are functions that... wait for it... return another function
//
//  In this task, return a function that, when called, will add the two numbers passed _into_ the higher order function
fun task1(a: Int, b: Int): IntResult? {
    return { a + b }
}

//  Task 2
//
//  Create a higher order function that simple executes the parameter that also is a function.
//  This gets _really_ confusing, so here is the best description I can come up with:
//      - task2 is a function, which has a single parameter: a function that returns an Int
//      - task2 returns a function, that when called, will return an Int
//      - So the return value of task2 is a function that should return the result of the parameter function
//
//  Is your head spinning yet?
fun task2(execute: () -> Int): IntResult? {
    return { execute() }
}

//  Task 3
//
//  Like task2, you'll call the passed in function parameters and do an operation on them
//  The operation you do needs to be based on the "op" parameter
fun task3(op: IntOperationType, first: () -> Int, second: () -> Int): IntResult? {
    val operations = task0()
    val operation = operations[op] ?: throw IllegalArgumentException("Invalid operation type!")

    return { operation(first(), second()) }
}

// AnyValue can represent any type of value, and provides simple mechanisms check the type of value,
// as well as perform different operations on the value
//
// To keep things simple, our AnyValue type only supports String and Int types
//
// Please fill out all of the TODO tasks for the AnyValue class
class AnyValue {

    private val value: Any

    constructor(s: String) { value = s }
    constructor(i: Int) { value = i }

    fun stringValue(): String? { return value as? String  }
    fun intValue(): Int? { return value as? Int }

    // TODO: Replace get() = false with the proper code
    val isString: Boolean
        get() = stringValue() != null

    // TODO: Replace get() = false with the proper code
    val isInt: Boolean
        get() = intValue() != null

    fun canPerformOperation(other: AnyValue, type: OperationType): Boolean {

        // TODO: This function should determine if two AnyValue objects can perform a given operation
        // If both values contain an Int, then the operation can be performed
        // If both values contain a String, then the operation can be performed
        // Otherwise, it cannot

        return this.isInt == other.isInt
    }

    fun add(other: AnyValue): Pair<OperationStatus, AnyValue?> {
        // TODO: First check if the operation can be performed by called canPerformOperation
        // If the operation can be performed, then perform the the ADD operation, and return the appropriate result
        if  (!canPerformOperation(other, OperationType.ADD)) {
            return Pair(OperationStatus.INVALID, null)
        }

        if (this.isString) {
            return Pair(OperationStatus.VALID, AnyValue(this.stringValue()!! + other.stringValue()!!))
        }

        return Pair(OperationStatus.VALID, AnyValue(this.intValue()!! + other.intValue()!!))
    }

    fun subtract(other: AnyValue): Pair<OperationStatus, AnyValue?> {
        // TODO: First check if the operation can be performed by called canPerformOperation
        // If the operation can be performed, then perform the the SUBTRACT operation, and return the appropriate result
        if  (!canPerformOperation(other, OperationType.SUBTRACT)) {
            return Pair(OperationStatus.INVALID, null)
        }

        if (this.isString) {
            return Pair(OperationStatus.VALID, AnyValue(this.stringValue()!!.replace(other.stringValue()!!, "")))
        }

        return Pair(OperationStatus.VALID, AnyValue(this.intValue()!! - other.intValue()!!))
    }

    fun concat(other: AnyValue): Pair<OperationStatus, AnyValue?> {
        // TODO: First check if the operation can be performed by called canPerformOperation
        // If the operation can be performed, then perform the the SUBTRACT operation, and return the appropriate result
        if  (!canPerformOperation(other, OperationType.CONCATENATE)) {
            return Pair(OperationStatus.INVALID, null)
        }

        val first = this.stringValue() ?: this.intValue().toString()
        val second = other.stringValue() ?: other.intValue().toString()

        if (this.isString) {
            return Pair(OperationStatus.VALID, AnyValue("$first$second"))
        }

        return Pair(OperationStatus.VALID, AnyValue("$first$second".toInt()))
    }
}

enum class OperationType {
    ADD, SUBTRACT, CONCATENATE
}

enum class OperationStatus {
    VALID, INVALID
}

//  Task 4
//
//  Lambda functions in Kotlin are first-class citizens, meaning they can be assigned
//  to variables and passed as a parameter to a function.
//
// For task4, you will return a OperationHandler. The OperationHandler takes 3 parameters:
//  - An OperationType, such as ADD or CONCATENATE
//  - Two AnyValue objects
// The Operation handler returns a Pair<OperationStatus, AnyValue?>:
//  - The OperationStatus denotes whether the operation could be completed or not
//  - And the AnyValue? should be null if the operation cannot be completed, or the result if it can
//
// Please use the add, subtract and concat functions from AnyValue to do the actual operations.
//
typealias OperationHandler = (OperationType, AnyValue, AnyValue) -> Pair<OperationStatus, AnyValue?>
// The OperationHandler implementation
fun task4(): OperationHandler? {
    return { op: OperationType, first: AnyValue, second: AnyValue ->
        when (op) {
            OperationType.ADD -> first.add(second)
            OperationType.SUBTRACT -> first.subtract(second)
            OperationType.CONCATENATE -> first.concat(second)
        }
    }
}
