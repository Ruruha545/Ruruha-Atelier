package com.example.expert_kiosk

// 주문서 內 주문 건수를 담당하는 클래스(ClientOrder)
class ClientOrder(
    var orOrder:Int, var mnNumber:Int, var mnName:String, var mnPrice:Int,
    var orQuantity:Int, var orPrice:Int)
{
    // 주문서 內 주문 추가용 로직
    fun MakeOrder(mnTab:MenuTablePlate):ClientOrder{
        // 반환용 주문 객체(outOrder) 생성
        var outOrder = ClientOrder(0,0,"",0,0,0)

        // 반환용 주문 객체 완성용 변수(6개) 생성 및 초기화
        var setOrder:Int = 0; var seMnNumb:Int = 0; var seMnName:String? = null;
        var seMnPrice:Int = 0; var seMnQuant:Int = 0; var sePrice:Int = 0

        // 루프 통제용 변수 lo_3 생성 및 루프점 생성
        var lo_3 = 0
        while(lo_3 == 0){
            // 값을 입력받을 임시변수 inj 생성, 사용 시 마다 초기화 조치
            var inj:String?

            // 메뉴 선택용 로직(주문 메뉴 입력)
            println("주문하고자 하는 메뉴 번호를 입력해주세요.")
            inj = readln()
            if(inj == null){ println("번호를 입력하지 않았습니다. 메뉴 선택으로 돌아갑니다."); continue }
            else {
                // 변수 치환(inj >> seMnNumb) 간 에러발생에 따른 예외처리 적용
                try { seMnNumb = inj.toInt(); inj = null }
                catch (e: NumberFormatException) {
                    println("입력이 잘못되었습니다. 메뉴 선택으로 돌아갑니다.")
                    inj = null; continue
                }
            }

            // 메뉴 선택용 로직(메뉴명, 메뉴 가격 대조 및 선택)
            mnTab.table.forEach{ ele ->
                if(seMnNumb == ele.mnNumber){
                    seMnName = ele.mnName; seMnPrice = ele.mnPrice
                    return@forEach
                }
            }

            // 메뉴 선택용 로직(주문 수량 입력)
            println("주문하고자 하는 수량을 입력해주세요.")
            inj = readln()
            if(inj == null){ println("수량을 입력하지 않았습니다. 메뉴 선택으로 돌아갑니다."); continue }
            else {
                // 변수 치환(inj >> seMnNumb) 간 에러발생에 따른 예외처리 적용
                try { seMnQuant = inj.toInt(); inj = null }
                catch (e: NumberFormatException) {
                    println("입력이 잘못되었습니다. 메뉴 선택으로 돌아갑니다.")
                    inj = null; continue
                }
            }

            // 메뉴 선택용 로직(주문 금액 계산)
            sePrice = seMnPrice * seMnQuant

            // 반환용 주문 객체(outOrder) 속성 수정
            outOrder.mnNumber = seMnNumb; outOrder.mnName = "${seMnName}";
            outOrder.mnPrice = seMnPrice; outOrder.orQuantity = seMnQuant;
            outOrder.orPrice = sePrice; break
        }
        return outOrder
    }



}