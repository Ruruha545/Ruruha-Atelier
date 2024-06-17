package com.example.expert_kiosk

import kotlin.random.Random

fun main() {
    // 키오스크 동작루프(1~3) 통제용 변수(retry) 생성
    var retry_1: Int? = 10
    var retry_2: Int? = 10
    var retry_3: Int? = 10

    // 기능 통제용 변수(funcTop) 생성
    var funcTop: String? = null

    // 메뉴 선택용 통제 변수(funcMid) 생성
    var funcMid: String? = null

    // 메뉴 삭제용 통제 변수(funcMid) 생성
    var funcBot: String? = null

    // 주문번호 변수(orderNumber) 생성
    var orderNumber: Int = 100

    // 주문서 작성 주체 클래스(Client) 객체(player) 생성
    val clNumb: Int = Random.nextInt(1, 51)
    var player = Client(clNumber = clNumb, 10)

    // 주문서 작성용 클래스(OrderPaper) 객체(bill) 생성
    var bill_org: MutableList<ClientOrder> = mutableListOf()
    var bill = OrderPaper(bill_org)

    // 주문서 內 작성한 주문 클래스(ClientOrder) 객체(nowOrder) 생성
    var orOrdNumber: Int = 1
    var nowOrder = ClientOrder(0, 0, "", 0, 0, 0)

    // 메뉴판용 클래스(MenuTablePlate)의 객체(MnTbPl) 생성 및 메뉴판 생성용 메서드 실행
    var MnTbPl_neta: MutableList<Menu> = mutableListOf()
    var MnTbPl = MenuTablePlate(MnTbPl_neta)
    MnTbPl = MnTbPl.MakeMenuTable()

    //키오스크 동작루프_1 생성
    while (retry_1 != 0) {
        // 화면 출력용 클래스(DispScreen) 객체(screen) 생성
        val screen = DispScreen()

        // 최초 화면 출력 및 사용자 메뉴판 조작용 코드
        funcTop = screen.DispTitle()

        // 키오스크 동작루프_2 생성
        while (retry_2 != 0) {
            when (funcTop) {
                // 주문서 작성 선택(1번)에 따른 메뉴판 출력용 코드
                "1" -> {
                    // 메뉴판 화면 출력 및 기능 통제용 변수(funcTop) 반환
                    funcTop = screen.DispMenu(MnTbPl)
                    funcTop = screen.DispAfterMenu()

                    // 주문 작성을 위한 검증 로직, 통과 실패 시 키오스크 동작루프_2로 이동
                    if (funcTop == "0") {
                        funcTop = "1"; continue
                    } else if (funcTop == "1") {
                        nowOrder = nowOrder.MakeOrder(MnTbPl)
                    } else {
                        continue
                    }

                    // 주문서에 주문 기입 및 주문 내역서를 출력하는 로직
                    bill = bill.writePaper(nowOrder, bill)
                    funcTop = screen.DispOrPaper(bill, player, orderNumber)

                    // 주문 추가(1번)/수정(2번)/결제(3번) 분기용 로직
                    when (funcTop) {
                        // 주문 추가용 로직, 키오스크 동작루프_2로 이동
                        "1" -> {
                            continue
                        }

                        // 주문 수정용 로직
                        "2" -> {
                            println("주문 메뉴의 수량 조정은 1번, 주문 메뉴 삭제는 2번을 입력해주세요.")
                            funcTop = readln()

                            when (funcTop) {
                                // 주문 메뉴 수량 조정용 로직
                                "1" -> {
                                    println("주문 메뉴 수량 조정을 처리하겠습니다.")
                                    bill = bill.PlMiOrder(bill)
                                }

                                // 주문 메뉴 삭제용 로직
                                "2" -> {
                                    println("주문 메뉴 삭제를 처리하겠습니다.")
                                    bill = bill.DelOrder(bill)
                                }

                                // 그외 처리용 로직(오입력 포함), 키오스크 동작루프_2로 이동
                                else -> {
                                    println("잘못된 값을 입력하였습니다.")
                                    funcTop = "1"; continue
                                }
                            }
                            // 주문 수정 완료
                            println("주문 수정이 완료되었습니다.");
                            funcTop = screen.DispOrPaper(bill,player, orderNumber);

                            // 주문내역서 출력 및 분기 조건 값 결정, 키오스크 동작루프_2로 이동
                            if(funcTop == "3"){ funcTop = "2"; continue }
                            else{ funcTop = "1"; continue }
                        }

                        // 주문 결제용 로직
                        "3" -> {

                        }

                    }

                }

                // 주문서 재발급 선택(2번)에 따른 주문서 출력용 코드
                "2" -> {}


                // 키오스크 종료 선택(0번)에 따른 키오스크 종료용 코드, 키오스크 동작루프_2 탈출
                "0" -> {
                    println("키오스크 사용을 종료하겠습니다."); break
                }
            }

        }   // 키오스크 동작루프_2 종말점

        println("키오스크 사용을 종료하겠습니다."); break
    }      // 키오스크 동작루프_1 종말점
    // 키오스크 동작 종료직전 확인용 구문
    println("안녕히 가십시요.")
}