package com.example.alogo

// ※ Lv.1 과제   :  사칙연산 기능을 구현하라
class Calculator {
    fun add(numb1:Double, numb2:Double): Double {
        return (numb1 + numb2)
    }

    fun sub(numb1:Double, numb2:Double): Double{
        return (numb1 - numb2)
    }

    fun div(numb1:Double, numb2:Double): Double{
        return (numb1 / numb2)
    }

    fun mul(numb1:Double, numb2:Double): Double{
        return (numb1 * numb2)
    }
}

fun main(){
    var retry:String?
    retry = "n"

    var calLogic = Calculator()

    do{
        var number1_i:Int? = null
        var number2_i:Int? = null

        var number1:Double? = readLine()?.toDoubleOrNull()

        if(number1 == null){
            println("잘못된 값이 입력됨")
            continue
        }
        else if(number1 == number1.toInt().toDouble()){
            number1_i = number1.toInt()
            println("${number1_i}")
        }
        else{
            println("${number1}")
        }

        var logic:String? = readLine()
        if (logic != "+" && logic != "-" && logic != "*" && logic != "/") {
            println("잘못된 연산자가 입력됨")
            continue
        }
        if(number1_i != null){
            println("${number1_i} ${logic}")
        }
        else{
            println("${number1} ${logic}")
        }

        var number2:Double? = readLine()?.toDoubleOrNull()
        if(number2 == null){
            println("잘못된 값이 입력됨")
            continue
        }
        else if(number2 == number2.toInt().toDouble()){
            number2_i = number2.toInt()
            if(number1_i != null){
                println("${number1_i} ${logic} ${number2_i}")
            }
            else{
                println("${number1} ${logic} ${number2_i}")
            }
        }
        else{
            if(number1_i != null){
                println("${number1_i} ${logic} ${number2}")
            }
            else{
                println("${number1} ${logic} ${number2}")
            }
        }

        var result: Double = when (logic) {
            "+" -> calLogic.add(number1, number2)
            "-" -> calLogic.sub(number1, number2)
            "/" -> calLogic.div(number1, number2)
            "*" -> calLogic.mul(number1, number2)
            else -> {
                println("세상이 말세다")
                continue
            }
        }

        println("계산 결과 : ${result}")
        println("이제 그만(y/n)?")
        retry = readLine()

    }while(retry != "y")
}