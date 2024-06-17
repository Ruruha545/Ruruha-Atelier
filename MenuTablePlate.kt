package com.example.expert_kiosk

// 키오스크 화면 출력을 담당하는 클래스(DispScreen)
class MenuTablePlate(var table:MutableList<Menu>) {

    // 주문서 內 주문입력 간 참조용 테이블 생성 메서드
    fun MakeMenuTable(): MenuTablePlate{
        val UniSu = Menu(1, "성게알", 4500)
        val TakoSu = Menu(2, "자숙문어", 3500)
        val EbiSu = Menu(3, "생새우", 3000)
        val SakeSu = Menu(4, "연어", 3500)
        val HirameSu = Menu(5, "광어", 3000)
        val BasuSu = Menu(6, "농어", 3000)
        val MadaiSu = Menu(7, "참돔", 3500)
        val SabaSu = Menu(8, "고등어", 3500)
        val NishinSu = Menu(9, "청어(전어)", 2500)
        val MaguroSu = Menu(10, "참치", 5500)

        val KaisenSaMd = Menu(21, "해산물(중)", 55000)
        val KaisenSaLg = Menu(22, "해산물(대)", 75000)
        val TakoSaMd = Menu(23, "문어숙회(중)", 45000)
        val TakoSaLg = Menu(24, "문어숙회(대)", 60000)
        val EbiSaMd = Menu(25, "생새우(중)", 55000)
        val EbiSaLg = Menu(26, "생새우(대)", 65000)
        val HirameSaMd = Menu(27, "광어(중)", 40000)
        val HirameSaLg = Menu(28, "광어(대)", 55000)
        val MaguroSaMd = Menu(29, "참치(중)", 100000)
        val MaguroSaLg = Menu(30, "참치(대)", 150000)
        val AllSaMd = Menu(31, "모둠회(중)", 75000)
        val AllSaLg = Menu(32, "모둠회(대)", 100000)

        val KayaSet = Menu(41, "카야", 80000)
        val AoiSet = Menu(42, "아오이", 120000)
        val RinSet = Menu(43, "린", 160000)

        val NishinYaki = Menu(51, "청어구이", 35000)
        val TaiYaki = Menu(52, "도미구이", 45000)
        val AllYaki = Menu(53, "모둠구이", 60000)

        val TenDon = Menu(61, "튀김덮밥", 10000)
        val SakeDon = Menu(62, "연어덮밥", 12000)
        val KaisenDon = Menu(63, "모둠회덮밥", 15000)
        val KakeUdong = Menu(64, "튀김우동", 10000)
        val TaiSomen = Menu(65, "타이소멘", 15000)
        val Onigiri = Menu(66, "주먹밥(5개)", 10000)
        val Ebifura = Menu(67, "새우튀김(5개)", 35000)
        val Tenfura = Menu(68, "모둠튀김", 55000)
        val Odeng = Menu(69, "오뎅", 200000)
        val ShimeSaba = Menu(70, "고등어 초절임", 200000)
        val Ankimo = Menu(71, "아귀간", 200000)

        val Ramune = Menu(81, "라무네(소다/멜론)", 3000)
        val ColaCider = Menu(82, "콜라/사이다", 2500)
        val Juice = Menu(83, "주스(망고/포도)", 2000)

        val Highball = Menu(101, "호로요이(시로이사와/복숭아/레몬)", 4500)
        val Draft1 = Menu(102, "산토리 생맥주(285ml)", 3000)
        val Draft2 = Menu(103, "산토리 생맥주(450ml)", 5000)
        val Soju = Menu(104, "소주", 4000)
        val Shochu = Menu(105, "토키다이코 쇼츄(720ml)", 50000)
        val Sake = Menu(106, "니키타쓰 다이긴죠(720ml)", 75000)

        // 메뉴판 데이터 저장용 가변 리스트 생성
        var MenuTb: MutableList<Menu> = mutableListOf()

        // 초밥메뉴 입력(10개) 로직(인덱스 : 0 ~ 9번)
        MenuTb.add(UniSu); MenuTb.add(TakoSu); MenuTb.add(EbiSu);MenuTb.add(SakeSu);
        MenuTb.add(HirameSu);MenuTb.add(BasuSu);MenuTb.add(MadaiSu);MenuTb.add(SabaSu);
        MenuTb.add(NishinSu);MenuTb.add(MaguroSu);

        // 회메뉴 입력(12개) 로직(인덱스 : 10 ~ 21번)
        MenuTb.add(KaisenSaMd); MenuTb.add(KaisenSaLg); MenuTb.add(TakoSaMd);
        MenuTb.add(TakoSaLg);MenuTb.add(EbiSaMd);MenuTb.add(EbiSaLg);
        MenuTb.add(HirameSaMd);MenuTb.add(HirameSaLg);MenuTb.add(MaguroSaMd);
        MenuTb.add(MaguroSaLg);MenuTb.add(AllSaMd); MenuTb.add(AllSaLg);

        // 세트메뉴 입력(3개) 로직(인덱스 : 22 ~ 24번)
        MenuTb.add(KayaSet);MenuTb.add(AoiSet);MenuTb.add(RinSet);

        // 구이메뉴 입력(3개) 로직(인덱스 : 25 ~ 27번)
        MenuTb.add(NishinYaki); MenuTb.add(TaiYaki); MenuTb.add(AllYaki);

        // 요리메뉴 입력(11개) 로직(인덱스 : 28 ~ 38번)
        MenuTb.add(TenDon); MenuTb.add(SakeDon); MenuTb.add(KaisenDon);
        MenuTb.add(KakeUdong);MenuTb.add(TaiSomen);MenuTb.add(Onigiri);
        MenuTb.add(Ebifura);MenuTb.add(Tenfura);MenuTb.add(Odeng);
        MenuTb.add(ShimeSaba);MenuTb.add(Ankimo);

        // 음료메뉴 입력(3개) 로직(인덱스 : 39 ~ 41번)
        MenuTb.add(Ramune); MenuTb.add(ColaCider); MenuTb.add(Juice);

        // 주류메뉴 입력(6개) 로직(인덱스 : 42 ~ 47번)
        MenuTb.add(Highball); MenuTb.add(Draft1); MenuTb.add(Draft2);
        MenuTb.add(Soju); MenuTb.add(Shochu); MenuTb.add(Sake);

        // 최종 메뉴판 반환용 클래스(MenuTablePlate) 객체(MenuTbPro) 생성 및 반환
        var MenuTbPro = MenuTablePlate(MenuTb)

        return MenuTbPro
        }
    }
