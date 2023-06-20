/**
 * Class that implements coffee machine interface
 */
class CoffeeMachine(
    private var waterAmount: Int,
    private var milkAmount: Int,
    private var beansAmount: Int,
    private var cupsCount: Int,
    private var moneyAmount: Int
) {
    /**
     * Enumeration class for coffee machine state representing
     */
    private enum class State(val message: String) {
        WAITING_FOR_ACTION("Write action (buy, fill, take, remaining, exit):"),
        WAITING_FOR_ITEM_NUMBER("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:"),
        WAITING_FOR_WATER_AMOUNT("Write how many ml of water you want to add:"),
        WAITING_FOR_MILK_AMOUNT("Write how many ml of milk you want to add:"),
        WAITING_FOR_BEANS_AMOUNT("Write how many grams of coffee beans you want to add:"),
        WAITING_FOR_CUPS_COUNT("Write how many disposable cups you want to add:"),
    }

    private var machineState = State.WAITING_FOR_ACTION

    /**
     * Returns current machine state message
     */
    fun getMachineStateMessage(): String {
        return machineState.message
    }

    /**
     * Prints information about remaining consumables
     */
    private fun printRemainingConsumables() {
        println("""
            The coffee machine has:
            $waterAmount ml of water
            $milkAmount ml of milk
            $beansAmount g of coffee beans
            $cupsCount disposable cups
            $$moneyAmount of money
        """.trimIndent())
        println()
    }

    /**
     * Processes user input and changes machine state based on that input
     */
    fun processInput(input: String) {
        when (machineState) {
            State.WAITING_FOR_ACTION -> {
                when (input) {
                    "buy" -> machineState = State.WAITING_FOR_ITEM_NUMBER
                    "fill" -> machineState = State.WAITING_FOR_WATER_AMOUNT
                    "take" -> giveAllMoney()
                    "remaining" -> printRemainingConsumables()
                }
            }

            State.WAITING_FOR_ITEM_NUMBER -> {
                sellCoffee(input)
                machineState = State.WAITING_FOR_ACTION
            }

            State.WAITING_FOR_WATER_AMOUNT -> {
                fillWater(input.toInt())
                machineState = State.WAITING_FOR_MILK_AMOUNT
            }
            State.WAITING_FOR_MILK_AMOUNT -> {
                fillMilk(input.toInt())
                machineState = State.WAITING_FOR_BEANS_AMOUNT
            }
            State.WAITING_FOR_BEANS_AMOUNT -> {
                fillBeans(input.toInt())
                machineState = State.WAITING_FOR_CUPS_COUNT
            }
            State.WAITING_FOR_CUPS_COUNT -> {
                fillCups(input.toInt())
                machineState = State.WAITING_FOR_ACTION
            }
        }
        println()
    }

    /**
     * Using the makeCoffee() method subtracts the amount of consumables
     * needed to make the selected coffee and adds the price of the beverage
     * to moneyAmount
     */
    private fun sellCoffee(itemNum: String) {
        if (itemNum == "back") return

        when (itemNum.toInt()) {
            1 -> {
                makeCoffee(250, 0, 16)
                moneyAmount += 4
            }
            2 -> {
                makeCoffee(350, 75, 20)
                moneyAmount += 7
            }
            3 -> {
                makeCoffee(200, 100, 12)
                moneyAmount += 6
            }
        }
    }

    /**
     * Subtracts specified amount of consumables if enough
     */
    private fun makeCoffee(water: Int, milk: Int, beans: Int) {
        when {
            waterAmount - water < 0 -> {
                println("Sorry, not enough water!")
                return
            }
            milkAmount - milk < 0 -> {
                println("Sorry, not enough milk!")
                return
            }
            beansAmount - beans < 0 -> {
                println("Sorry, not enough coffee beans!")
                return
            }
            else -> println("I have enough resources, making you a coffee!")
        }

        waterAmount -= water
        milkAmount -= milk
        beansAmount -= beans
        cupsCount--
    }

    /**
     * Increases waterAmount by amount
     */
    private fun fillWater(amount: Int) {
        waterAmount += amount
    }
    /**
     * Increases milkAmount by amount
     */
    private fun fillMilk(amount: Int) {
        milkAmount += amount
    }
    /**
     * Increases beansAmount by amount
     */
    private fun fillBeans(amount: Int) {
        beansAmount += amount
    }
    /**
     * Increases cupsCount by count
     */
    private fun fillCups(count: Int) {
        cupsCount += count
    }

    /**
     * Prints message about moneyAmount and
     * sets it to 0
     */
    private fun giveAllMoney() {
        println("I gave you $$moneyAmount")
        moneyAmount = 0
    }
}