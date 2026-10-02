package com.babacafe.app.data.datasource

import com.babacafe.app.data.model.Category
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.data.model.SizeOption

object MenuDataSource {
    val items: List<MenuItem> = listOf(
        // --- 01. TEA ---
        MenuItem(
            id = "tea_regular",
            name = "Tea",
            hindiName = "चाय",
            category = Category.TEA,
            description = "Traditional freshly brewed kadak milk chai.",
            basePrice = 10.0,
            sizeOptions = listOf(
                SizeOption("Small", 10.0),
                SizeOption("Medium", 15.0),
                SizeOption("Large", 25.0)
            ),
            isBestseller = true
        ),
        MenuItem(
            id = "tea_masala",
            name = "Masala Tea",
            hindiName = "मसाला चाय",
            category = Category.TEA,
            description = "Brewed with authentic Indian spices, ginger and cardamom.",
            basePrice = 25.0,
            isBestseller = true
        ),
        MenuItem(
            id = "tea_kulhad",
            name = "Kulhad Tea",
            hindiName = "कुल्हड़ चाय",
            category = Category.TEA,
            description = "Earthen clay cup aromatic chai - Jaipur special taste.",
            basePrice = 25.0,
            isBestseller = true,
            isBabaSpecial = true,
            displayBadge = "Popular"
        ),
        MenuItem(
            id = "tea_green",
            name = "Green Tea",
            hindiName = "ग्रीन टी",
            category = Category.TEA,
            description = "Pure soothing antioxidant green tea infusion.",
            basePrice = 35.0
        ),
        MenuItem(
            id = "tea_ginger_honey",
            name = "Ginger Honey Tea",
            hindiName = "अदरक शहद चाय",
            category = Category.TEA,
            description = "Warm blend of freshly crushed ginger and organic honey.",
            basePrice = 40.0
        ),
        MenuItem(
            id = "tea_honey_lemon",
            name = "Honey Lemon Tea",
            hindiName = "हनी लेमन टी",
            category = Category.TEA,
            description = "Refreshing detox tea with zesty lemon and sweet honey.",
            basePrice = 40.0
        ),

        // --- 02. COFFEE ---
        MenuItem(
            id = "coffee_hot",
            name = "Hot Coffee",
            hindiName = "हॉट कॉफी",
            category = Category.COFFEE,
            description = "Rich creamy hot coffee with frothy crema.",
            basePrice = 30.0,
            isBestseller = true
        ),
        MenuItem(
            id = "coffee_black",
            name = "Black Coffee",
            hindiName = "ब्लैक कॉफी",
            category = Category.COFFEE,
            description = "Strong aromatic dark roast espresso brew.",
            basePrice = 30.0
        ),
        MenuItem(
            id = "coffee_cold",
            name = "Cold Coffee",
            hindiName = "कोल्ड कॉफी",
            category = Category.COFFEE,
            description = "Classic thick chilled coffee blended with milk and chocolate drizzle.",
            basePrice = 49.0,
            sizeOptions = listOf(
                SizeOption("Medium", 49.0),
                SizeOption("Large", 69.0)
            ),
            isBestseller = true
        ),
        MenuItem(
            id = "coffee_cold_icecream",
            name = "Cold Coffee with Ice Cream",
            hindiName = "कोल्ड कॉफी विद आइसक्रीम",
            category = Category.COFFEE,
            description = "Signature cold coffee topped with a creamy vanilla scoop.",
            basePrice = 59.0,
            sizeOptions = listOf(
                SizeOption("Medium", 59.0),
                SizeOption("Large", 79.0)
            ),
            isBabaSpecial = true,
            displayBadge = "Must Try"
        ),

        // --- 03. MAGGIE ---
        MenuItem(
            id = "maggie_plain",
            name = "Plain Maggie",
            hindiName = "प्लेन मैगी",
            category = Category.MAGGIE,
            description = "Classic 2-minute comfort noodles with signature tastemaker.",
            basePrice = 49.0
        ),
        MenuItem(
            id = "maggie_masala",
            name = "Masala Maggie",
            hindiName = "मसाला मैगी",
            category = Category.MAGGIE,
            description = "Spicy street-style masala Maggie with extra tadka.",
            basePrice = 79.0,
            isBestseller = true
        ),
        MenuItem(
            id = "maggie_veg",
            name = "Veg Maggie",
            hindiName = "वेज मैगी",
            category = Category.MAGGIE,
            description = "Loaded with fresh crunchy onions, tomatoes, and green peas.",
            basePrice = 79.0
        ),
        MenuItem(
            id = "maggie_cheese",
            name = "Cheese Maggie",
            hindiName = "चीज़ मैगी",
            category = Category.MAGGIE,
            description = "Topped with melted shredded mozzarella and cheddar cheese.",
            basePrice = 99.0,
            isBestseller = true
        ),
        MenuItem(
            id = "maggie_cream",
            name = "Cream Maggie",
            hindiName = "क्रीम मैगी",
            category = Category.MAGGIE,
            description = "Rich velvety cream tossed Maggie with herb seasoning.",
            basePrice = 99.0
        ),

        // --- 04. BREAKFAST ---
        MenuItem(
            id = "breakfast_poha",
            name = "Poha",
            hindiName = "पोहा",
            category = Category.BREAKFAST,
            description = "Indori style flattened rice with roasted peanuts, curry leaves & sev.",
            basePrice = 30.0,
            sizeOptions = listOf(
                SizeOption("Small", 30.0),
                SizeOption("Medium", 40.0),
                SizeOption("Large", 50.0)
            ),
            isBestseller = true
        ),
        MenuItem(
            id = "breakfast_aloo_paratha",
            name = "Aloo Paratha",
            hindiName = "आलू पराठा",
            category = Category.BREAKFAST,
            description = "Freshly made crispy golden paratha stuffed with spiced mashed potatoes, served with butter & curd.",
            basePrice = 78.0,
            isBestseller = true,
            displayBadge = "Verified Favorite"
        ),

        // --- 05. CONTINENTAL ---
        MenuItem(
            id = "cont_red_sauce_pasta",
            name = "Red Sauce Pasta",
            hindiName = "रेड सॉस पास्ता",
            category = Category.CONTINENTAL,
            description = "Arrabbiata pasta cooked in tangy spicy tomato & basil concassé.",
            basePrice = 99.0
        ),
        MenuItem(
            id = "cont_white_sauce_pasta",
            name = "White Sauce Pasta",
            hindiName = "व्हाइट सॉस पास्ता",
            category = Category.CONTINENTAL,
            description = "Alfredo pasta in rich garlic butter and creamy white cheese sauce.",
            basePrice = 109.0,
            isBestseller = true
        ),
        MenuItem(
            id = "cont_pink_sauce_pasta",
            name = "Pink Sauce Pasta",
            hindiName = "पिंक सॉस पास्ता",
            category = Category.CONTINENTAL,
            description = "Delightful fusion of rich cream and tangy Arrabbiata tomato sauce.",
            basePrice = 119.0
        ),
        MenuItem(
            id = "cont_paneer_satte",
            name = "Paneer Satte",
            hindiName = "पनीर साते",
            category = Category.CONTINENTAL,
            description = "Grilled paneer skewers marinated in aromatic herbs and spices.",
            basePrice = 129.0
        ),
        MenuItem(
            id = "cont_spaghetti_pasta",
            name = "Spaghetti Pasta",
            hindiName = "स्पेगेटी पास्ता",
            category = Category.CONTINENTAL,
            description = "Al dente spaghetti tossed with olive oil, garlic, chilli flakes and herbs.",
            basePrice = 159.0
        ),
        MenuItem(
            id = "cont_veg_lasagna",
            name = "Veg Lasagna",
            hindiName = "वेज लज़ानिया",
            category = Category.CONTINENTAL,
            description = "Layered baked pasta sheets with seasonal vegetables and baked cheese.",
            basePrice = 149.0,
            displayBadge = "Chef Special"
        ),
        MenuItem(
            id = "cont_veg_satte",
            name = "Veg Satte",
            hindiName = "वेज साते",
            category = Category.CONTINENTAL,
            description = "Char-grilled vegetable satay skewers served with spicy dip.",
            basePrice = 149.0
        ),
        MenuItem(
            id = "cont_dahi_kaliya",
            name = "Dahi Kaliya",
            hindiName = "दही कालिया",
            category = Category.CONTINENTAL,
            description = "Unique continental curd delicacy blended with mild spices.",
            basePrice = 179.0
        ),

        // --- 06. FRIES ---
        MenuItem(
            id = "fries_french",
            name = "French Fries",
            hindiName = "फ्रेंच फ्राइज",
            category = Category.FRIES,
            description = "Golden crispy salted potato fries.",
            basePrice = 59.0
        ),
        MenuItem(
            id = "fries_peri_peri",
            name = "Peri Peri Fries",
            hindiName = "पेरी पेरी फ्राइज",
            category = Category.FRIES,
            description = "Crisp fries generously tossed in spicy zesty peri peri seasoning.",
            basePrice = 89.0,
            isBestseller = true
        ),
        MenuItem(
            id = "fries_cheese_loaded",
            name = "Cheese Loaded Fries",
            hindiName = "चीज़ लोडेड फ्राइज",
            category = Category.FRIES,
            description = "Hot fries smothered with liquid cheddar and melted mozzarella.",
            basePrice = 99.0,
            isBestseller = true
        ),
        MenuItem(
            id = "fries_mexican",
            name = "Mexican Fries",
            hindiName = "मैक्सिकन फ्राइज",
            category = Category.FRIES,
            description = "Topped with salsa, jalapeños, olives and Mexican cheese drizzle.",
            basePrice = 129.0
        ),

        // --- 07. SHAKES ---
        MenuItem(
            id = "shake_banana",
            name = "Banana Shake",
            hindiName = "बनाना शेक",
            category = Category.SHAKES,
            description = "Fresh bananas blended with thick sweet cream milk.",
            basePrice = 49.0
        ),
        MenuItem(
            id = "shake_chocolate",
            name = "Chocolate Shake",
            hindiName = "चॉकलेट शेक",
            category = Category.SHAKES,
            description = "Thick chocolate fudge shake with cocoa sprinkles.",
            basePrice = 59.0,
            isBestseller = true
        ),
        MenuItem(
            id = "shake_vanilla",
            name = "Vanilla Shake",
            hindiName = "वैनिला शेक",
            category = Category.SHAKES,
            description = "Classic smooth Madagascar vanilla cream shake.",
            basePrice = 69.0
        ),
        MenuItem(
            id = "shake_oreo",
            name = "Oreo Shake",
            hindiName = "ओरियो शेक",
            category = Category.SHAKES,
            description = "Crushed crunchy Oreo cookies blitzed with ice cream and milk.",
            basePrice = 79.0,
            isBestseller = true
        ),
        MenuItem(
            id = "shake_kitkat",
            name = "Kit-Kat Shake",
            hindiName = "किट-कैट शेक",
            category = Category.SHAKES,
            description = "Crispy Kit-Kat fingers blended with milk chocolate fudge.",
            basePrice = 79.0,
            isBestseller = true
        ),
        MenuItem(
            id = "shake_butterscotch",
            name = "Butter Scotch Shake",
            hindiName = "बटरस्कॉच शेक",
            category = Category.SHAKES,
            description = "Sweet caramel butterscotch shake with praline crunch.",
            basePrice = 89.0
        ),

        // --- 08. MOCKTAILS ---
        MenuItem(
            id = "mocktail_mini_mojito",
            name = "Mini Mojito",
            hindiName = "मिनी मोहीतो",
            category = Category.MOCKTAILS,
            description = "Refreshing crushed mint and lime cooler with bubbly soda.",
            basePrice = 89.0
        ),
        MenuItem(
            id = "mocktail_green_apple",
            name = "Green Apple Mojito",
            hindiName = "ग्रीन एप्पल मोहीतो",
            category = Category.MOCKTAILS,
            description = "Crisp green apple syrup with fresh mint and sparkling soda.",
            basePrice = 100.0,
            isBestseller = true
        ),
        MenuItem(
            id = "mocktail_blue_lagoon",
            name = "Blue Lagoon",
            hindiName = "ब्लू लगून",
            category = Category.MOCKTAILS,
            description = "Vibrant curacao citrus cooler with chilled lemon fizz.",
            basePrice = 109.0,
            displayBadge = "Refreshing"
        ),
        MenuItem(
            id = "mocktail_pina_colada",
            name = "Pina Colada",
            hindiName = "पिना कोलाडा",
            category = Category.MOCKTAILS,
            description = "Tropical blend of creamy coconut milk and sweet pineapple juice.",
            basePrice = 149.0
        ),
        MenuItem(
            id = "beverage_soft_drinks",
            name = "Soft Drinks",
            hindiName = "सॉफ्ट ड्रिंक्स",
            category = Category.MOCKTAILS,
            description = "Chilled bottled aerated beverages (Coke / Sprite / Thums Up).",
            basePrice = 40.0
        ),
        MenuItem(
            id = "beverage_mineral_water",
            name = "Mineral Water",
            hindiName = "मिनरल वाटर",
            category = Category.MOCKTAILS,
            description = "Packaged drinking mineral water bottle.",
            basePrice = 20.0
        ),

        // --- 09. SANDWICH ---
        MenuItem(
            id = "sand_bombay",
            name = "Bombay Sandwich",
            hindiName = "बॉम्बे सैंडविच",
            category = Category.SANDWICH,
            description = "Street-style spiced potato slices, beetroot, cucumber & mint chutney.",
            basePrice = 50.0
        ),
        MenuItem(
            id = "sand_veg",
            name = "Veg Sandwich",
            hindiName = "वेज सैंडविच",
            category = Category.SANDWICH,
            description = "Freshly cut garden veggies layered with butter and green chutney.",
            basePrice = 59.0
        ),
        MenuItem(
            id = "sand_club",
            name = "Club Sandwich",
            hindiName = "क्लब सैंडविच",
            category = Category.SANDWICH,
            description = "Double-decker toasted sandwich with coleslaw, paneer and spices.",
            basePrice = 69.0,
            isBestseller = true
        ),
        MenuItem(
            id = "sand_cheese",
            name = "Cheese Sandwich",
            hindiName = "चीज़ सैंडविच",
            category = Category.SANDWICH,
            description = "Melted cheese and sweet corn toasted to golden perfection.",
            basePrice = 79.0,
            isBestseller = true
        ),
        MenuItem(
            id = "sand_farmhouse",
            name = "Farm House Sandwich",
            hindiName = "फार्म हाउस सैंडविच",
            category = Category.SANDWICH,
            description = "Loaded with capsicum, onion, tomato, olives, corn and double cheese.",
            basePrice = 89.0
        ),

        // --- 10. MOMOS ---
        MenuItem(
            id = "momos_veg",
            name = "Veg Momos",
            hindiName = "वेज मोमोज़",
            category = Category.MOMOS,
            description = "Steamed dumplings stuffed with minced fresh cabbage and carrots.",
            basePrice = 59.0
        ),
        MenuItem(
            id = "momos_veg_cheese",
            name = "Veg Cheese Momos",
            hindiName = "वेज चीज़ मोमोज़",
            category = Category.MOMOS,
            description = "Steamed vegetable momos packed with gooey melted cheese.",
            basePrice = 79.0
        ),
        MenuItem(
            id = "momos_paneer",
            name = "Paneer Momos",
            hindiName = "पनीर मोमोज़",
            category = Category.MOMOS,
            description = "Soft dumplings stuffed with spiced cottage cheese filling.",
            basePrice = 99.0,
            isBestseller = true
        ),
        MenuItem(
            id = "momos_paneer_cheese",
            name = "Paneer Cheese Momos",
            hindiName = "पनीर चीज़ मोमोज़",
            category = Category.MOMOS,
            description = "Rich fusion of cottage cheese and mozzarella with spicy momo dip.",
            basePrice = 125.0
        ),
        MenuItem(
            id = "momos_corn_spinach",
            name = "Corn n Spinach Momos",
            hindiName = "कॉर्न और पालक मोमोज़",
            category = Category.MOMOS,
            description = "Healthy flavorful blend of sweet corn kernels and baby spinach.",
            basePrice = 145.0
        ),

        // --- 11. CHINESE STARTER ---
        MenuItem(
            id = "starter_veg_cutlet",
            name = "Veg Cutlet",
            hindiName = "वेज कटलेट",
            category = Category.CHINESE_STARTER,
            description = "Crispy shallow fried spiced vegetable patties.",
            basePrice = 80.0
        ),
        MenuItem(
            id = "starter_chilli_potato",
            name = "Chilli Potato",
            hindiName = "चिली पोटैटो",
            category = Category.CHINESE_STARTER,
            description = "Crispy fried potatoes tossed in sweet and spicy chilli garlic sauce.",
            basePrice = 100.0,
            isBestseller = true
        ),
        MenuItem(
            id = "starter_honey_chilli_potato",
            name = "Honey Chilli Potato",
            hindiName = "हनी चिली पोटैटो",
            category = Category.CHINESE_STARTER,
            description = "Crispy potatoes glazed in honey, toasted sesame seeds and chilli.",
            basePrice = 110.0,
            isBestseller = true
        ),
        MenuItem(
            id = "starter_veg_manchurian",
            name = "Veg Manchurian",
            hindiName = "वेज मंचूरियन",
            category = Category.CHINESE_STARTER,
            description = "Minced vegetable balls tossed in soy ginger garlic sauce.",
            basePrice = 100.0,
            availableStyles = listOf(PreparationStyle.DRY, PreparationStyle.GRAVY),
            isBestseller = true
        ),
        MenuItem(
            id = "starter_baba_biloni_tikki",
            name = "Baba Special Biloni Tikki",
            hindiName = "बाबा स्पेशल बिलोनी टिक्की",
            category = Category.CHINESE_STARTER,
            description = "House special signature tikki made with secret spices and herbs.",
            basePrice = 120.0,
            isBestseller = true,
            isBabaSpecial = true,
            displayBadge = "Must Try Special"
        ),
        MenuItem(
            id = "starter_paneer_65",
            name = "Paneer 65",
            hindiName = "पनीर 65",
            category = Category.CHINESE_STARTER,
            description = "Crispy spicy paneer cubes tempered with curry leaves and green chillies.",
            basePrice = 120.0
        ),
        MenuItem(
            id = "starter_chilli_paneer",
            name = "Chilli Paneer",
            hindiName = "चिली पनीर",
            category = Category.CHINESE_STARTER,
            description = "Tender paneer cubes wok-tossed with capsicum, onion and soy chilli.",
            basePrice = 100.0,
            availableStyles = listOf(PreparationStyle.DRY, PreparationStyle.GRAVY),
            isBestseller = true
        ),
        MenuItem(
            id = "starter_dragon_paneer",
            name = "Dragon Paneer",
            hindiName = "ड्रैगन पनीर",
            category = Category.CHINESE_STARTER,
            description = "Fiery red dragon sauce paneer with crunchy cashew nuts.",
            basePrice = 140.0
        ),

        // --- 12. ROLL ---
        MenuItem(
            id = "roll_veg",
            name = "Veg Roll",
            hindiName = "वेज रोल",
            category = Category.ROLL,
            description = "Flaky flatbread wrap filled with spiced sautéed veggies and tangy mayo.",
            basePrice = 69.0
        ),
        MenuItem(
            id = "roll_veg_schezwan",
            name = "Veg Sezwan Roll",
            hindiName = "वेज शेजवान रोल",
            category = Category.ROLL,
            description = "Spicy Schezwan sauce coated veggies in a crisp roll.",
            basePrice = 79.0
        ),
        MenuItem(
            id = "roll_paneer",
            name = "Paneer Roll",
            hindiName = "पनीर रोल",
            category = Category.ROLL,
            description = "Succulent paneer tikka chunks with onions, mint and spicy sauce.",
            basePrice = 89.0,
            isBestseller = true
        ),
        MenuItem(
            id = "roll_baba_special",
            name = "Baba Special Roll",
            hindiName = "बाबा स्पेशल रोल",
            category = Category.ROLL,
            description = "Double stuffed paneer, cheese and secret cafe masala blend.",
            basePrice = 120.0,
            isBabaSpecial = true,
            displayBadge = "House Special"
        ),

        // --- 13. ITALIAN PIZZA ---
        MenuItem(
            id = "pizza_mushroom",
            name = "Mushroom Pizza",
            hindiName = "मशरूम पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Fresh button mushrooms, herb tomato sauce and mozzarella.",
            basePrice = 110.0,
            sizeOptions = listOf(
                SizeOption("Medium", 110.0),
                SizeOption("Large", 150.0)
            )
        ),
        MenuItem(
            id = "pizza_margherita",
            name = "Margherita Pizza",
            hindiName = "मार्गेरिटा पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Classic Italian thin crust topped with tomato sauce, basil and double cheese.",
            basePrice = 129.0,
            sizeOptions = listOf(
                SizeOption("Medium", 129.0),
                SizeOption("Large", 149.0)
            ),
            isBestseller = true
        ),
        MenuItem(
            id = "pizza_golden_fry",
            name = "Golden Fry Pizza",
            hindiName = "गोल्डन फ्राई पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Loaded with sweet golden corn and crispy toppings.",
            basePrice = 129.0,
            sizeOptions = listOf(
                SizeOption("Medium", 129.0),
                SizeOption("Large", 149.0)
            )
        ),
        MenuItem(
            id = "pizza_onion_horse",
            name = "Onion Horse Pizza",
            hindiName = "अनियन हॉर्स पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Crispy red onions, spicy herbs and melted cheese.",
            basePrice = 139.0,
            sizeOptions = listOf(
                SizeOption("Medium", 139.0),
                SizeOption("Large", 159.0)
            )
        ),
        MenuItem(
            id = "pizza_otc",
            name = "OTC Pizza",
            hindiName = "ओटीसी पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Classic Onion, Tomato & Capsicum loaded trio pizza.",
            basePrice = 139.0,
            sizeOptions = listOf(
                SizeOption("Medium", 139.0),
                SizeOption("Large", 159.0)
            ),
            isBestseller = true
        ),
        MenuItem(
            id = "pizza_double_cheese",
            name = "Double Cheese Pizza",
            hindiName = "डबल चीज़ पिज़्ज़ा",
            category = Category.ITALIAN_PIZZA,
            description = "Overloaded with mozzarella and processed cheese pull.",
            basePrice = 149.0,
            sizeOptions = listOf(
                SizeOption("Medium", 149.0),
                SizeOption("Large", 200.0)
            ),
            isBestseller = true,
            displayBadge = "Chef Favorite"
        ),

        // --- 14. NOODLES ---
        MenuItem(
            id = "noodles_veg",
            name = "Veg Noodles",
            hindiName = "वेज नूडल्स",
            category = Category.NOODLES,
            description = "Street style wok-tossed noodles with shredded vegetables.",
            basePrice = 89.0
        ),
        MenuItem(
            id = "noodles_hakka",
            name = "Hakka Noodles",
            hindiName = "हक्का नूडल्स",
            category = Category.NOODLES,
            description = "Authentic Indo-Chinese Hakka noodles with crunchy peppers.",
            basePrice = 99.0,
            isBestseller = true
        ),
        MenuItem(
            id = "noodles_brown_garlic",
            name = "Brown Garlic Noodles",
            hindiName = "ब्राउन गार्लिक नूडल्स",
            category = Category.NOODLES,
            description = "Infused with slow-roasted golden brown aromatic garlic.",
            basePrice = 129.0
        ),
        MenuItem(
            id = "noodles_chilli_garlic",
            name = "Chilli Garlic Noodles",
            hindiName = "चिली गार्लिक नूडल्स",
            category = Category.NOODLES,
            description = "Spicy noodles tossed in fiery red chilli garlic sauce.",
            basePrice = 159.0,
            isBestseller = true
        ),
        MenuItem(
            id = "noodles_singapuri",
            name = "Veg Singapuri Noodles",
            hindiName = "वेज सिंगापुरी नूडल्स",
            category = Category.NOODLES,
            description = "Curry-flavored spicy noodles with vibrant seasonal vegetables.",
            basePrice = 169.0
        )
    )
}
