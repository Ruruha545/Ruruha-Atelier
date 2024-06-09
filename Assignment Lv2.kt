package com.example.alogo

// ※ Lv. 2 과제   :  연속한 연산 및 나머지 연산기능을 추가하라
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

    fun rem(numb1:Double, numb2:Double): Double{
        return (numb1 % numb2)
    }
}

fun main(){
    // 전체 로직의 반복을 통제하는 변수("-1" : 종료  ,  "0" : 처음부터  ,  그 외 : 이어서)
    var retry:String?
    retry = "Yume"

    // 계산기를 동작하기 위한 객체 생성
    var calLogic = Calculator()

    // 첫번째 연산 대상 값이 저장되는 변수
    var number1:Double?
    // 두번째 연산 대상 값이 저장되는 변수
    var number2:Double?
    // 최종 연산 결과 값이 저장되는 변수
    var result:Double?
    // 연산의 종류가 저장되는 변수
    var logic:String?

    // 반복문 1번 시작점(처음부터)
    do{
        print("첫번째 숫자 입력 : ")
        number1 = readLine()?.toDoubleOrNull()
        if(number1 == null){
            println("잘못된 값이 입력됨")
            retry = "0"
        }

        print("연산자 입력(더하기:1, 빼기:2, 곱하기:3, 나누기:4, 나머지:5) : ")
        logic = readLine()

        // 반복문 2번 시작점(이어서)
        do{
            if (logic != "1"
                && logic != "2"
                && logic != "3"
                && logic != "4"
                && logic != "5"
                && logic != "-1") {
                println("잘못된 연산자가 입력됨")
                retry = "0"
                break
            }

            print("두번째 숫자 입력 : ")
            number2 = readLine()?.toDoubleOrNull()
            if(number2 == null || (number2 == 0.0 && logic == "4")){
                println("잘못된 값이 입력됨")
                retry = "0"
                break
            }

            // 반복문 2번 내에서 연산 대상의 값이 null 인지를 점검하는 구문
            if(number1 == null){
                break
            }

            // 입력 값에 따른 연산수행
            result = when (logic) {
                "1" -> calLogic.add(number1, number2)
                "2" -> calLogic.sub(number1, number2)
                "3" -> calLogic.mul(number1, number2)
                "4" -> calLogic.div(number1, number2)
                "5" -> calLogic.rem(number1, number2)
                else -> {
                    println("유메 선배를 실장하라 !!")
                    retry = "0"
                    break
                }
            }

            // 연산 결과값 출력
            println("계산 결과 : ${result}")

            // 연산 지속여부 및 연산종류 입력
            println("계산기 종료 : -1, 처음으로 : 0, 이어서 : (+:1, -:2, *:3, /:4, %:5)")
            retry = readLine()
            if(retry == "-1"){
                break
            }
            else if(retry == "0"){
                break
            }
            else{
                number1 = result
                logic = retry
            }
        }while(retry != "0" || retry != "-1")

    }while(retry != "-1")

    println("이용해주셔서 감사합니다")
}