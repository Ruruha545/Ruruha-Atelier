package com.example.alogo

// ※ Lv. 4 과제  :  추상 클래스를 적용하라
abstract class AbstractOperation {
    abstract fun cal(numb1:Double, numb2:Double): Double
}

// 덧셈 연산용 클래스
class AddOperation : AbstractOperation() {
    override fun cal(numb1:Double, numb2:Double): Double {
        val res: Double = (numb1 + numb2)
        return res
    }
}

// 뺄셈 연산용 클래스
class SubstractOperation : AbstractOperation() {
    override fun cal(numb1:Double, numb2:Double): Double {
        val res: Double = (numb1 - numb2)
        return res
    }
}

// 곱셈 연산용 클래스
class MultiplyOperation : AbstractOperation() {
    override fun cal(numb1:Double, numb2:Double): Double {
        val res: Double = (numb1 * numb2)
        return res
    }
}

// 나눗셈 연산용 클래스
class DivideOperation : AbstractOperation() {
    override fun cal(numb1:Double, numb2:Double): Double {
        val res: Double = (numb1 / numb2)
        return res
    }
}

// 나머지 연산용 클래스
class RemainderOperation : AbstractOperation() {
    override fun cal(numb1:Double, numb2:Double): Double {
        val res: Double = (numb1 % numb2)
        return res
    }
}

fun main(){
    // 전체 로직의 반복을 통제하는 변수("-1" : 종료  ,  "0" : 처음부터  ,  그 외 : 이어서)
    var retry:String?
    retry = "Yume"

    // 계산기를 동작하기 위한 객체 생성
    val addOp: AbstractOperation = AddOperation()
    val subOp: AbstractOperation = SubstractOperation()
    val mulOp: AbstractOperation = MultiplyOperation()
    val divOp: AbstractOperation = DivideOperation()
    val remOp: AbstractOperation = RemainderOperation()

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

        print("연산자 입력(더하기:1, 빼기:2, 곱하기:3, 나누기:4, 나머지:5, 처음으로 : -1, 끝내기 : 0) : ")
        logic = readLine()
        if(logic == "-1"){
            continue
        }
        else if(logic == "0"){
            break
        }

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

            // 입력 값에 따른 연산수행(추상 클래스 활용)
            result = when (logic) {
                "1" -> addOp.cal(number1, number2)
                "2" -> subOp.cal(number1, number2)
                "3" -> mulOp.cal(number1, number2)
                "4" -> divOp.cal(number1, number2)
                "5" -> remOp.cal(number1, number2)
                else -> {
                    println("유메 선배를 실장하라 !!")
                    retry = "0"
                    break
                }
            }

            println("계산결과 : ${result}")

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