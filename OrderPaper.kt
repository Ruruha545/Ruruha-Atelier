package com.example.expert_kiosk

// 주문서를 담당하는 클래스(OrderPaper)
class OrderPaper(var paper:MutableList<ClientOrder>) {

    // 입력받은 주문(nowOrder)을 주문서(newPaper)에 작성하는 메서드
    fun writePaper(nowOrder: ClientOrder, myPaper: OrderPaper): OrderPaper {
        // 반환용 주문서 객체(newPapaer) 생성(입력된 주문 복제)
        var newOrder = ClientOrder(
            orOrder = nowOrder.orOrder, mnNumber = nowOrder.mnNumber, mnName = "${nowOrder.mnName}",
            mnPrice = nowOrder.mnPrice, orQuantity = nowOrder.orQuantity, orPrice = nowOrder.orPrice
        )
        var newPaperOrg: MutableList<ClientOrder> = myPaper.paper.toMutableList()

        // 반환용 주문서 內 주문 순번 확인용 변수(noOrder) 생성 및 입력 순번 지정
        var noOrder: Int = 1
        myPaper.paper.forEach { ele ->
            noOrder++
        }
        newOrder.orOrder = noOrder

        newPaperOrg.add(newOrder)
        var newPaper = OrderPaper(newPaperOrg)

        return newPaper
    }

    // 입력받은 주문서(nowBill)에서 입력된 주문 순번(Inj)을 삭제하는 메서드
    fun DelOrder(nowBill: OrderPaper): OrderPaper {
        // 반환용 주문 객체의 속성(newPaper) 생성(nowBill.paper 복사)
        var newPaper: MutableList<ClientOrder> = nowBill.paper.toMutableList()

        // 주문서 內 주문 목록이 없는 경우, 주문 목록이 있는 경우 처리할 로직
        if (newPaper.size == 0) {
            println("삭제할 수 있는 주문이 없습니다.")
            var newBill = OrderPaper(newPaper)

            return newBill
        } else {
            // 삭제할 주문 순번 변수(selOrd) 및 입력값 저장용 임시변수(inj) 생성
            var selOrd: Int? = 0;
            var inj: String? = null

            // 루프 통제용 변수 lo_1 생성 및 루프점 생성
            var lo_1: Int? = 0
            while (lo_1 == 0) {
                println("삭제할 주문의 순번을 입력해주세요.")
                inj = readln()
                if (inj == null) {
                    println("순번을 입력하지 않았습니다. 삭제할 주문 순번 선택으로 돌아갑니다."); continue
                } else {
                    // 변수 치환(inj >> delOrd) 간 에러발생에 따른 예외처리 적용
                    try {
                        selOrd = inj.toInt(); inj = null
                    } catch (e: NumberFormatException) {
                        println("입력값이 잘못되었습니다. 삭제할 주문 순번 선택으로 돌아갑니다.")
                        inj = null; continue
                    }
                }

                // 반환용 주문 객체의 속성(newPaper) 內 지정된 값 제거
                newPaper.forEach { ele ->
                    if (ele.orOrder == selOrd) {
                        ele.orOrder = 0; ele.mnNumber = 0; ele.mnName = ""; ele.mnPrice = 0;
                        ele.orQuantity = 0; ele.orPrice = 0; return@forEach
                    }
                }

                // 반환용 주문 객체의 속성(newPaper) 내부 정렬 및 순번 인식용 변수(neOrder) 생성
                var neOrder: Int = 0
                for (chkI: Int in 0 until newPaper.size) {
                    neOrder++
                    if (newPaper[chkI].orOrder == 0) {
                        // 더 이상 복사할 뒷 인덱스가 없는 경우(현 인덱스가 마지막), 현 인덱스 삭제
                        if (chkI == (newPaper.size - 1)) {
                            newPaper.removeAt(chkI); break
                        } else {
                            // 뒷 인덱스의 값, 빈 인덱스의 값으로 복사
                            newPaper[chkI].orOrder = neOrder
                            newPaper[chkI].mnNumber = newPaper[chkI + 1].mnNumber
                            newPaper[chkI].mnName = newPaper[chkI + 1].mnName
                            newPaper[chkI].mnPrice = newPaper[chkI + 1].mnPrice
                            newPaper[chkI].orQuantity = newPaper[chkI + 1].orQuantity
                            newPaper[chkI].orPrice = newPaper[chkI + 1].orPrice

                            // 복사가 끝난 뒷 인덱스의 값 비우기
                            newPaper[chkI + 1].orOrder = 0; newPaper[chkI + 1].mnNumber = 0;
                            newPaper[chkI + 1].mnName = ""; newPaper[chkI + 1].mnPrice;
                            newPaper[chkI + 1].orQuantity = 0; newPaper[chkI + 1].orPrice = 0
                        }
                    }
                }
                println("주문을 삭제하였습니다."); break
            }
            // 반환용 객체(newBill) 생성 및 값 반환, 주문 삭제가 처리된 주문서 객체(newBill)
            var newBill = OrderPaper(newPaper)
            return newBill
        }
    }

    // 입력받은 주문서(nowBill)에서 입력된 주문 순번(Inj)의 수량을 수정하는 메서드
    fun PlMiOrder(nowBill: OrderPaper): OrderPaper{
        // 반환용 주문 객체의 속성(newPaper) 생성(nowBill.paper 복사)
        var newPaper: MutableList<ClientOrder> = nowBill.paper.toMutableList()

        // 주문서 內 주문 목록이 없는 경우, 주문 목록이 있는 경우 처리할 로직
        if (newPaper.size == 0) {
            println("수정 할 수 있는 주문이 없습니다.")
            var newBill = OrderPaper(newPaper)

            return newBill
        }
        else{
            // 수정 할 주문 순번 변수(selOrd) 및 입력값 저장용 임시변수(inj) 생성
            var selOrd: Int? = 0;
            var inj: String? = null

            // 루프 통제용 변수 lo_1 생성 및 루프점 생성
            var lo_1: Int? = 0
            while (lo_1 == 0){
                println("수량을 조정 할 주문의 순번을 입력해주세요. 항목 변경은 0번을 입력해주세요.")
                println("(항목 변경은 주문 삭제 후 가능)")
                inj = readln()
                if (inj == null) {
                    println("순번을 입력하지 않았습니다. 수량을 조정 할 주문 순번 선택으로 돌아갑니다."); continue
                }
                else if(inj == "0"){
                    println("항목 변경 화면으로 이동합니다."); var newBill = OrderPaper(newPaper);
                    newBill = newBill.DelOrder(newBill); break
                }
                else {
                    // 변수 치환(inj >> selOrd) 간 에러발생에 따른 예외처리 적용
                    try {
                        selOrd = inj.toInt(); inj = null
                    } catch (e: NumberFormatException) {
                        println("입력값이 잘못되었습니다. 수정할 주문 순번 선택으로 돌아갑니다.")
                        inj = null; continue
                    }
                }

                // 반환용 주문 객체의 속성(newPaper) 內 지정된 값 수정
                newPaper.forEach { ele ->
                    if (ele.orOrder == selOrd) {
                        // 루프 통제용 변수(lo_2), 신규 수량 변수(newOrd) 생성 및 루프점 생성
                        var lo_2: Int? = 0; var newOrd:Int = 0
                        while (lo_2 == 0){
                            println("새로운 수량을 입력해주세요.")
                            // 입력용 변수 사용 간 문제 발생으로 신규 입력용 변수(inj2) 생성
                            var inj2:String? = readln()
                            if (inj2 == null) {
                                println("수량을 입력하지 않았습니다. 수량 입력으로 돌아갑니다."); continue
                            }
                            else {
                                // 변수 치환(inj >> delOrd) 간 에러발생에 따른 예외처리 적용
                                // newOrd = inj.toInt(); 해당 구문의 inj 값에서 에러가 발생
                                // inj 변수를 앞에서부터 계속 재활용 한 것이 문제로 추측됨
                                // 결국 inj -> inj2로 변경 후 문제 해결됨, 이유는 모름
                                try {
                                    newOrd = inj2.toInt(); inj2 = null;
                                    ele.orQuantity = newOrd; break
                                } catch (e: NumberFormatException) {
                                    println("입력값이 잘못되었습니다. 수량 입력으로 돌아갑니다.")
                                    inj2 = null; continue
                                }
                            }
                        }
                        return@forEach
                    }
                }
                println("주문을 수정하였습니다."); break
            }
            // 반환용 객체(newBill) 생성 및 값 반환, 주문 삭제가 처리된 주문서 객체(newBill)
            var newBill = OrderPaper(newPaper)
            return newBill
        }
    }
}