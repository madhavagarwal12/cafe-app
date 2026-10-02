package com.babacafe.app.data.model

enum class Category(
    val id: String,
    val title: String,
    val hindiTitle: String,
    val shortCode: String
) {
    ALL("all", "All Items", "सभी", "00"),
    TEA("tea", "Tea", "चाय", "01"),
    COFFEE("coffee", "Coffee", "कॉफी", "02"),
    MAGGIE("maggie", "Maggie", "मैगी", "03"),
    BREAKFAST("breakfast", "Breakfast", "नाश्ता", "04"),
    CONTINENTAL("continental", "Continental", "कॉन्टिनेंटल", "05"),
    FRIES("fries", "Fries", "फ्राइज", "06"),
    SHAKES("shakes", "Shakes", "शेक्स", "07"),
    MOCKTAILS("mocktails", "Mocktails", "मॉकटेल्स", "08"),
    SANDWICH("sandwich", "Sandwich", "सैंडविच", "09"),
    MOMOS("momos", "Momos", "मोमोज़", "10"),
    CHINESE_STARTER("chinese_starter", "Chinese Starter", "चाइनीज स्टार्टर", "11"),
    ROLL("roll", "Roll", "रोल", "12"),
    ITALIAN_PIZZA("italian_pizza", "Italian Pizza", "पिज़्ज़ा", "13"),
    NOODLES("noodles", "Noodles", "नूडल्स", "14")
}
