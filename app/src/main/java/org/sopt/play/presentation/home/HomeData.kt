package org.sopt.play.presentation.home

import org.sopt.play.R
import org.sopt.play.ui.model.HomeProduct

object HomeData {
    val menus = listOf(
        "2배적립",
        "홈",
        "셀렉티드",
        "브랜드",
        "뷰티",
        "라이프",
        "랭킹"
    )

    val categories = listOf(
        "전체",
        "상의",
        "팬츠",
        "원피스",
        "아우터",
        "스커트",
        "슈즈",
        "가방",
        "이너웨어/잠옷",
        "폰꾸미기"
    )

    val banners = listOf(
        HomeBanner(
            imageRes = R.drawable.img_banner_waiting_for_you,
            contentDescription = "WAITING FOR YOU 컬렉션"
        ),
        HomeBanner(
            imageRes = R.drawable.img_banner_western_collection,
            contentDescription = "WESTERN COLLECTION"
        ),
        HomeBanner(
            imageRes = R.drawable.img_banner_hello_spring,
            contentDescription = "HELLO NEW SPRING 컬렉션"
        )
    )

    val products = listOf(
        HomeProduct(
            id = 1,
            imageRes = R.drawable.img_product_capri_pants,
            storeName = "트랜디어패럴",
            name = "[카프리1위🏆/5부,7부,9부기장/MADE] 클립 스판 슬릿 바이커 카프리 데님 팬츠",
            discountRate = 36,
            price = 28_800
        ),
        HomeProduct(
            id = 2,
            imageRes = R.drawable.img_product_slacks,
            storeName = "슬로우앤드",
            name = "[🍁가을/3기장선택!] made. 모먼트 투커버 올데이슬랙스 (+숏/베이직/롱ver) - 4 size",
            discountRate = 50,
            price = 26_820,
            isFavorite = true
        ),
        HomeProduct(
            id = 3,
            imageRes = R.drawable.img_product_boat_neck_top,
            storeName = "어텀",
            name = "보울7부티셔츠",
            discountRate = 10,
            price = 17_100
        ),
        HomeProduct(
            id = 4,
            imageRes = R.drawable.img_product_white_shirt,
            storeName = "슬로우앤드",
            name = "[오픈할인중] made. 도브 클래식 탄탄핏 무지셔츠 - 4 color",
            discountRate = 40,
            price = 35_200
        )
    )
}
