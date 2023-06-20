fun main() {
    startCoffeeMachine()
}

fun startCoffeeMachine() {
    val coffeeMachine = CoffeeMachine(
        waterAmount = 400,
        milkAmount = 540,
        beansAmount = 120,
        cupsCount = 9,
        moneyAmount = 550
    )
    while (true) {
        println(coffeeMachine.getMachineStateMessage())
        val action = readln()
        if (action == "exit") break
        coffeeMachine.processInput(action)
    }
}

