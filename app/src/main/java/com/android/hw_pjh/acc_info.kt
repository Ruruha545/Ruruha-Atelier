package com.android.hw_pjh

import java.io.Serializable

// 로그인용 계정 정보
data class accInfo(val v_id:String?, var v_pw:String?): Serializable {}
data class accTab1(var tab:MutableList<accInfo>): Serializable{}

// 자기소개용 계정 정보
data class accData(val id:String?, val name:String?, val school:String?, val hobby:String?, val oneWord:String?, val pic:Int): Serializable{}
data class accTab2(var tab:MutableList<accData>): Serializable{}

class prcAcc(){
    // 계정정보 추가용 로직(로그인)
    fun AddInfo(oriTab:accTab1, inpInfo:accInfo): accTab1{
        // 반환용 객체 생성(로그인 테이블)
        var recTab: MutableList<accInfo> = mutableListOf()
        var InfoTab: accTab1

        // 입력받은 계정정보(로그인) 재가공 로직
        recTab = oriTab.tab.toMutableList()
        recTab.add(inpInfo)
        InfoTab = accTab1(recTab)

        return InfoTab
    }

    // 계정정보 추가용 로직(자기소개)
    fun AddData(oriTab: accTab2, inpData:accData): accTab2{
        // 반환용 객체 생성(자기소개 테이블)
        var recTab: MutableList<accData> = mutableListOf()
        var InfoTab: accTab2

        // 입력받은 계정정보(자기소개) 재가공 로직
        recTab = oriTab.tab.toMutableList()
        recTab.add(inpData)
        InfoTab = accTab2(recTab)

        return InfoTab
    }

    // 계정정보 검색/대조용 로직(로그인)
    fun ChkAcc(chkAcc:accInfo, chkTab:accTab1):Boolean {
        chkTab.tab.forEach { ele ->
            // 계정정보(ID, PW) 일치 시 true 반환
            if (ele.v_id == chkAcc.v_id && ele.v_pw == chkAcc.v_pw) { return true }
        }
        return false
    }

    // 계정정보 출력용 로직(자기소개)
    fun DispAcc(inpId: String, accTab: accTab2): accData {
        // 반환용 객체(accData) 생성/초기화
        var nowData: accData = accData("mugiwara", "루피", "", "", "",0)

        // 계정정보 대조(결과 : 존재) 및 반환용 객체 구체화
        accTab.tab.forEach { ele ->
            if (ele.id == inpId) {
                nowData = accData(ele.id, ele.name, ele.school, ele.hobby, ele.oneWord, ele.pic)
                return@forEach
            }
        }
        return nowData
    }

    // 계정정보 테이블 초기화(로그인)      ※ 현재, 앱에 없는 데이터 정보 작성필요
    fun genAccSign(): accTab1 {
        // 반환용 객체 생성
        var table: MutableList<accInfo> = mutableListOf();
        var outTab: accTab1 = accTab1(table)

        // 테스트용 회원정보
        val h01: accInfo = accInfo("kanpeki", "tpalskghlrP")
        val h02: accInfo = accInfo("poemday", "tpalsktjrl")
        val h03: accInfo = accInfo("shirousagi", "tpalskqndnjs")
        val h04: accInfo = accInfo("avangard", "tpalskghlwkd")

        // 반환용 객체 內 테스트용 회원정보 기입
        outTab.tab.add(h01); outTab.tab.add(h02); outTab.tab.add(h03); outTab.tab.add(h04)

        return outTab
    }

    // 계정정보 테이블 초기화(자기소개)      ※ 현재, 앱에 없는 데이터 정보 작성필요
    fun genAccData(): accTab2 {
        // 반환용 객체 생성
        var table: MutableList<accData> = mutableListOf();
        var outTab: accTab2 = accTab2(table)

        // 테스트용 회원정보
        val h01: accData = accData("kanpeki", "하야세 유우카", "밀레니엄 사이언스 스쿨(2)",
            "계산", "계산대로야.", R.drawable.yuuka_c)
        val h02: accData = accData("poemday", "우시오 노아", "밀레니엄 사이언스 스쿨(2)",
            "독서, 암송", "키보토스의 우울.",R.drawable.noa_c)
        val h03: accData = accData("shirousagi", "쿠로사키 코유키", "밀레니엄 사이언스 스쿨(1)",
            "운 시험하기, 탈출", "아니 이게 제 잘못임;;?", R.drawable.koyuki_c)
        val h04: accData = accData("avangard", "츠카츠키 리오", "밀레니엄 사이언스 스쿨(3)",
            "로보트 만들기", "세계를 멸망시킬 병기다.",R.drawable.rio_c)

        // 반환용 객체 內 테스트용 회원정보 기입
        outTab.tab.add(h01); outTab.tab.add(h02); outTab.tab.add(h03); outTab.tab.add(h04)

        return outTab
    }
}