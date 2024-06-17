package com.example.expert_kiosk

// 키오스크 화면 출력을 담당하는 클래스(DispScreen)
class DispScreen{
    // 작성된 주문서를 출력하는 메서드
    fun DispOrPaper(nowBill:OrderPaper, nowClient:Client, orNumb:Int):String?{
        println("===================================================================")
        println("=                                                                 =")
        println("                       주   문   내   역   서                       ")
        println("고객번호 : ${nowClient.clNumber} - - - 주문번호 : ${orNumb}")
        println("   순번     주문품목           메뉴가격        수량           주문가격")
        println("-------------------------------------------------------------------")
        nowBill.paper.forEach{ele ->
            println("   ${ele.orOrder}      ${ele.mnName}           ${ele.mnPrice}원" +
                    "       ${ele.orQuantity}          ${ele.orPrice}원")}
        println("=                                                                 =")
        println("===================================================================")
        println()

        // 루프 통제용 변수 lo_5, 기능 입력용 변수(chkFunc) 및 루프점 생성
        var lo_5:Int = 0; var chkFunc:String? = null
        while(lo_5 == 0){
            println("주문 추가는 1번, 주문 수정은 2번, 주문 결제는 3번을 입력해주세요.")
            chkFunc = readln()
            if(chkFunc == "1"){ break }
            else if(chkFunc == "2"){ break }
            else if(chkFunc == "3"){ break }
            else{ println("잘못된 값을 입력하셨습니다."); continue }
        }
        // 값 반환, 반환된 값은 기능 통제용 변수(funcTop)로 사용됨
        return chkFunc
    }

    // 메뉴판 변경 및 주문용 메서드
    fun DispAfterMenu():String?{
        // 루프 통제용 변수 lo_4, 기능 입력용 변수(ordFunc) 및 루프점 생성
        var lo_4:Int = 0; var ordFunc:String? = null
        while(lo_4 == 0){
            println("다른 메뉴를 보시려면 0번, 주문을 하시려면 1번을 입력해주세요.")
            ordFunc = readln()
            if(ordFunc == "0"){ println("메뉴 선택화면으로 돌아가겠습니다."); break }
            else if(ordFunc == "1"){ println("메뉴 주문화면으로 이동하겠습니다."); break }
            else{ println("잘못된 값을 입력하셨습니다."); continue }
        }
        // 값 반환, 반환된 값은 기능 통제용 변수(funcTop)로 사용됨
        return ordFunc
    }


    // 키오스크 동작 타이틀 출력 메서드
    fun DispTitle():String?{
        println()
        println("===================================================================")
        println()
        println("     일식당 \"어제는 이사, 오늘은 평사원 박민철\"에 오신것을 환영합니다.")
        println()
        println("  원하시는 기능을 입력해주세요.")
        println("  1 : 주문서 작성         2 : 주문서 재발급         0 : 키오스크 종료")
        println()
        println("===================================================================")
        println()

        // 루프 통제용 변수 lo_1, 기능 입력용 변수(titleFunc) 및 루프점 생성
        var lo_1:Int = 0; var titleFunc:String? = null
        while (lo_1 == 0){
            titleFunc = readln()
            if(titleFunc == "1"){
                println("주문서 작성 절차로 이동하겠습니다."); break   // 루프점 탈출
            }
            else if(titleFunc == "2"){
                println("주문서 재발급 절차로 이동하겠습니다."); break   // 루프점 탈출
            }
            else if(titleFunc == "0"){
                println("키오스크 종료 절차로 이동하겠습니다."); break   // 루프점 탈출
            }
            else { println("잘못된 입력입니다. 다시 입력해주세요."); }   // 루프점 순환
        }
        // 값 반환, 반환된 값은 기능 통제용 변수(funcTop)로 사용됨
        return titleFunc
    }

    // 초기 메뉴판 출력 메서드
    fun DispMenu(prMenu:MenuTablePlate):String?{
        // 루프 통제용 변수 lo_2, 기능 입력용 변수(selMenu) 및 루프점 생성
        var lo_2:Int = 0; var selMenu:String? = null
        while (lo_2 == 0){
            println("===================================================================")
            println("=                                                                 =")
            println("                     세부 메뉴판을 선택해주세요                       ")
            println("1 : 초밥          2 : 회          3 : 세트          4 : 구이")
            println("5 : 요리          6 : 음료        7 : 주류")
            println("=                                                                 =")
            println("===================================================================")

            selMenu = readln()
            when(selMenu){
                "1" -> { DispMenu(prMenu.table, 0, 9); break }
                "2" -> { DispMenu(prMenu.table, 10, 21); break }
                "3" -> { DispMenu(prMenu.table, 22, 24); break }
                "4" -> { DispMenu(prMenu.table, 25, 27); break }
                "5" -> { DispMenu(prMenu.table, 28, 38); break }
                "6" -> { DispMenu(prMenu.table, 39, 41); break }
                "7" -> { DispMenu(prMenu.table, 42, 47); break }
                else ->{ println("입력이 잘못되었습니다. 처음으로 돌아갑니다."); continue }
            }
        }
        // 값 반환, 반환된 값은 기능 통제용 변수(funcTop)로 사용 및 변수 초기화(null) 예정
        return null
    }

    // 세부 메뉴판 출력 메서드
    fun DispMenu(mnTable:MutableList<Menu>, sP:Int, eP:Int){
        println("===================================================================")
        println("=                                                                 =")

        // 메뉴항목 출력을 위한 반복문
        for(a:Int in sP .. eP){
            println("   ${mnTable[a].mnNumber} - - - ${mnTable[a].mnName}" +
                    " - - -  ${mnTable[a].mnPrice}원")
        }
        println("=                                                                 =")
        println("===================================================================")
    }
}