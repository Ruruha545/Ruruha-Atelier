package com.android.spartanmarket_240719

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.spartanmarket_240719.databinding.DialogScreenBinding
import com.android.spartanmarket_240719.databinding.FirstScreenBinding

class MainActivity : AppCompatActivity() {

    private lateinit var firstScreenBinding: FirstScreenBinding

    private lateinit var dialogScreenBinding: DialogScreenBinding
    private lateinit var exitCall: AlertDialog

    private val alram_id = 1
    private val alram_ch = "베이직반 침몰"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firstScreenBinding = FirstScreenBinding.inflate(layoutInflater)
        setContentView(firstScreenBinding.root)

        dialogScreenBinding = DialogScreenBinding.inflate(layoutInflater)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.first_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 데이터 원본 준비
        val dataList = mutableListOf<SpartanItem>().apply {
            add(
                SpartanItem(
                    R.drawable.item_sample1, "산지 한달 된 선풍기 팝니다.", "이사가서 필요없음",
                    "대연동 용연탕", "서울 서대문구 창천동", 7000, "25", "13"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample2, "김치냉장고 중고1년 판매", "이사로 인해 내놓습니다",
                    "블레스유", "인천 계양구 귤현동", 150000, "28", "8"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample3, "샤넬 카드지갑 급매", "고퀄 상품이고, 사용감이 조금 있어서 싸게 처분합니다.",
                    "코코나맘", "대구 수성구 범어동", 1000000, "5", "23"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample4, "안쓰는 금고 판매", "금고 배송은 구매자 부담입니다. 버튼형 다이얼식, 독일제입니다.",
                    "니콜라", "부산 해운대구 우제2동", 270000, "17", "14"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample5,
                    "갤럭시Z플립3 팝니다",
                    "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                    "절체절명",
                    "부산 연제구 연산8동",
                    870000,
                    "9",
                    "22"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample6, "프라다 복조리 백 판매", "까임, 오염 없고, 상태 깨끗합니다. \n정품 여부 불명",
                    "미니멀랜드", "수원 영통구 원천동", 1100000, "16", "25"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                    "\n울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n" +
                            "1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n" +
                            "4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 " +
                            "뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능" +
                            "\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개" +
                            "\n10. 저희 집안이 쓰는 별장입니다.", "굿리치", "강릉 남구 옥동", 250000, "54", "142"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample8,
                    "샤넬 탑핸들 가방",
                    "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n + \"\n\"" +
                            " + \"색상 : 블랙\n\" + \"사이즈 : 25.5cm * 17.5cm * 8cm\n\" + \"구성 : 본품더스트\n\" + \"\n\" + \"급하게 돈이 필요해서 팝니다 ㅠ ㅠ",
                    "난쉽",
                    "부산 동래구 온천2동",
                    800000,
                    "7",
                    "31"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample9,
                    "4행정 엔진분무기 판매합니다.",
                    "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서" +
                            " 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",
                    "알뜰한",
                    "원주 명륜2동",
                    30000,
                    "28",
                    "7"
                )
            )

            add(
                SpartanItem(
                    R.drawable.item_sample10,
                    "셀린느 버킷 가방",
                    "22년 신세계 대전 구매입니당\n + \"셀린느 버킷백\n\" + \"구매해서 몇번사용했어요\n\" + " +
                            "\"까짐 스크래치 없습니다.\n\" + \"타지역에서 보내는거라 택배로 진행합니당!\"",
                    "똑태현",
                    "광주 중구 동화동",
                    190000,
                    "6",
                    "40"
                )
            )
        }

        val adapter = MarketAdapter(dataList)
        firstScreenBinding.recyleListRecyclerView.adapter = adapter
        firstScreenBinding.recyleListRecyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity)

        adapter.listClick = { position ->
            val MoveItem: SpartanItem = dataList[position]
            val Go_SecActivity: Intent = Intent(this, SecActivity::class.java).apply {
                putExtra("SelectItem", MoveItem)
            }
            startActivity(Go_SecActivity)
        }

        dialogScreenBinding.apply {
            val go_exit: TextView = dialogBtnLeftTextView
            go_exit.setOnClickListener {
                finish()
            }
            val go_non: TextView = dialogBtnRightTextView
            go_non.setOnClickListener {
                super.onBackPressed()
            }
        }

        val alram_switch: ImageView = firstScreenBinding.firstUpsideBellMark
        alram_switch.setOnClickListener {
            alram_start()
        }

        enableEdgeToEdge()
    }

    // 이거 onBackPressed() 메서드에 계속 빨간줄이 뜹니다.
    // super를 부르라는데 그거 불렀다가는 다이얼로그를 사용할 수 없는데
    // 이런식으로 코딩하는게 맞는건가요 ? 일단 앱은 정상 작동합니다만.
    override fun onBackPressed() {
        setContentView(dialogScreenBinding.dialogContainerOutter)
        showDialog()
    }

    fun showDialog() {
        dialogScreenBinding.apply {
            val dialView: ConstraintLayout = dialogContainerOutter
            val go_exit1: View = dialogBtnLeftView
            val go_exit2: TextView = dialogBtnLeftTextView
            val go_non1: View = dialogBtnRightView
            val go_non2: TextView = dialogBtnRightTextView

            exitCall = AlertDialog.Builder(this@MainActivity)
                .setView(dialView)
                .create()

            go_exit1.setOnClickListener {
                finish()
            }
            go_exit2.setOnClickListener {
                finish()
            }
            go_non1.setOnClickListener {
                setContentView(firstScreenBinding.root)
            }
            go_non2.setOnClickListener {
                setContentView(firstScreenBinding.root)
            }
        }
    }

    private fun alram_start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Android 8.0
            val channel = NotificationChannel(
                alram_ch, "베이직반 침몰",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "사용하는 레퍼런스에 대한 지식도 없고, 알려주는 곳도 없다보니 너무 어렵습니다."
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val go_home:Intent = Intent(this, MainActivity::class.java)
        val pendingIntent = with(TaskStackBuilder.create(this)){
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val container: NotificationCompat.Builder = NotificationCompat.Builder(this, alram_ch)
            .setSmallIcon(R.drawable.prf_user)
            .setContentTitle("레퍼런스에 대하여 제대로 된 학습을")
            .setContentText("건너뛴 것 같아서 너무나도 어렵습니다.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }
}