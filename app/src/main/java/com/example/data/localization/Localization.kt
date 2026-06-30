package com.example.data.localization

enum class Language(val displayName: String) {
    ENGLISH("English"),
    ARABIC("العربية الفصحى"),
    EGYPTIAN_SLANG("العامية المصرية"),
    FRANCO("الفرانكو")
}

enum class LocKey {
    START_FOCUS,
    POMODORO_SWITCH,
    FOCUS_SCREEN_MSG,
    FAILURE_LOSS_NOTIF,
    SUCCESS_NOTIF,
    
    // Dashboard & ID Card
    TOTAL_FOCUS,
    LEVEL,
    MINUTES,
    MEMBER_IDENTITY,
    TITLE,
    START_SESSION,
    EXPORT_ID,
    FREE_FLOW,
    POMODORO_ON,
    POMODORO_OFF,
    
    // Focus Screen
    QUIT_SESSION,
    CONFIRM_QUIT,
    
    // Profile
    CHOOSE_IDENTITY,
    GEOMETRIC_ANCHOR,
    USERNAME,
    STATUS,
    SAVE_PROFILE,
    VAULT_SECURE_MSG,
    
    // Settings
    SETTINGS,
    PROFILE_APPEARANCE,
    EDIT_PROFILE,
    MANAGE_IDENTITY,
    CUSTOMIZATION_SHOP,
    THEMES_VISUALS,
    NOTIFICATIONS_DND,
    AUTO_DND,
    SYS_OVERRIDE,
    TIMER_ALARM,
    DAILY_REMINDER,
    SET_FOR,
    LANGUAGE_PREFERENCE,
    DISPLAY_LANGUAGE,
    ABOUT,
    SYS_INFO,
    PRIVACY_MSG,
    
    // Ledger
    THE_LEDGER,
    LEDGER_SUBTITLE,
    TOTAL_FOCUSED_MIN,
    BROKEN_SESSIONS,
    EFFICIENCY_RATIO,
    VS_LAST_WEEK,
    IMPROVEMENT,
    PEAK_PERF,
    WEEKLY_OUTPUT,
    WEEK,
    MONTH,
    RECENT_AUDITS,
    SHOW_ALL,
    SUCCESS,
    INTERRUPTED,
    
    // Shop
    SHOP_TITLE,
    SHOP_SUBTITLE,
    PREMIUM_GRADIENTS,
    STATUS_TITLES,
    MINIMALIST_TYPO,
    TYPO_SUBTITLE,
    COMING_SOON,
    ZEN_PACK,
    EQUIPPED,
    UNLOCKED,
    DEFAULT,
    PREVIEW,

    // New Keys for Localization
    // Onboarding
    WELCOME_TITLE,
    WELCOME_SUBTITLE,
    FEATURE_FOCUS_TITLE,
    FEATURE_FOCUS_DESC,
    FEATURE_LEDGER_TITLE,
    FEATURE_LEDGER_DESC,
    FEATURE_ECONOMY_TITLE,
    FEATURE_ECONOMY_DESC,
    FEATURE_PENALTY_TITLE,
    FEATURE_PENALTY_DESC,
    ENTER_NAME_PLACEHOLDER,
    ONBOARDING_FOOTER,
    INITIALIZE_PROTOCOL,

    // Settings
    CUSTOM_MUSIC,
    SELECTED_PREFIX,
    MUSIC_SUBTITLE,
    SELECT_MUSIC_FILE,
    VERSION_LABEL,

    // Home
    NAV_HOME,
    NAV_LEDGER,
    NAV_SHOP,
    POMODORO_WORK,
    POMODORO_BREAK,
    MIN_SUFFIX,
    EXPORT_SUCCESS,
    EXPORT_FAILURE,

    // Focus
    BREAK_TIME_MSG,

    // Ledger
    NO_SESSIONS,
    TODAY_PREFIX,

    // Profile
    BACK_DESC,
    SELECT_AVATAR,
    ERROR_EMPTY_NAME,
    PROFILE_SAVED,

    // Shop
    EQUIP_ACTION,
    INSUFFICIENT_BALANCE,
    UNLOCKED_AND_EQUIPPED,

    // Merchandise
    GRADIENT_BRUSHED_TITANIUM,
    DESC_BRUSHED_TITANIUM,
    GRADIENT_CYBER_NEON,
    DESC_CYBER_NEON,
    GRADIENT_ROSE_QUARTZ,
    DESC_ROSE_QUARTZ,
    GRADIENT_MATTE_OBSIDIAN,
    DESC_MATTE_OBSIDIAN,
    GRADIENT_EMERALD_MATRIX,
    DESC_EMERALD_MATRIX,
    GRADIENT_SOLAR_FLARE,
    DESC_SOLAR_FLARE,
    GRADIENT_GOLDEN_AURUM,
    DESC_GOLDEN_AURUM,

    FONT_INTER_SANS,
    DESC_INTER_SANS,
    FONT_MONOSPACE_PRO,
    DESC_MONOSPACE_PRO,
    FONT_EXECUTIVE_SERIF,
    DESC_EXECUTIVE_SERIF,

    TITLE_CASUAL,
    DESC_CASUAL,
    TITLE_DEEP_WORK_MASTER,
    DESC_DEEP_WORK_MASTER,
    TITLE_ELITE,
    DESC_ELITE,
    SUCCESS_NOTIF_BODY,
    FAILURE_NOTIF_BODY,
    BREAK_END_NOTIF_TITLE,
    BREAK_END_NOTIF_BODY,
    ERR_DND_PERMISSION,
    ERR_SETTINGS_OPEN,
    MON, TUE, WED, THU, FRI, SAT, SUN
}

object Localization {
    private val dictionary: Map<LocKey, Map<Language, String>> = mapOf(
        LocKey.START_FOCUS to mapOf(
            Language.ENGLISH to "Start Focus",
            Language.ARABIC to "ابدأ التركيز",
            Language.EGYPTIAN_SLANG to "يلا بينا نركز",
            Language.FRANCO to "Yalla Start"
        ),
        LocKey.POMODORO_SWITCH to mapOf(
            Language.ENGLISH to "Pomodoro Mode",
            Language.ARABIC to "نظام البومودورو",
            Language.EGYPTIAN_SLANG to "نظام البومودورو",
            Language.FRANCO to "Pomodoro Mode"
        ),
        LocKey.FOCUS_SCREEN_MSG to mapOf(
            Language.ENGLISH to "Stay Focused",
            Language.ARABIC to "حافظ على تركيزك",
            Language.EGYPTIAN_SLANG to "سيب الموبايل وركز في مستقبلك",
            Language.FRANCO to "Syb el mobile w rkez"
        ),
        LocKey.FAILURE_LOSS_NOTIF to mapOf(
            Language.ENGLISH to "Session Broken!",
            Language.ARABIC to "لقد استسلمت وكسرت الجلسة!",
            Language.EGYPTIAN_SLANG to "يا خسارة! الجلسة باظت والبروفايل زعل",
            Language.FRANCO to "Yaa khsara! El session b2et broken"
        ),
        LocKey.SUCCESS_NOTIF to mapOf(
            Language.ENGLISH to "Session Complete!",
            Language.ARABIC to "اكتملت الجلسة بنجاح!",
            Language.EGYPTIAN_SLANG to "عاش يا وحش! عملت اللي عليك",
            Language.FRANCO to "Ash ya btal! El session khlest"
        ),
        LocKey.TOTAL_FOCUS to mapOf(
            Language.ENGLISH to "Total Focus",
            Language.ARABIC to "إجمالي التركيز",
            Language.EGYPTIAN_SLANG to "إجمالي التركيز",
            Language.FRANCO to "Total Focus"
        ),
        LocKey.LEVEL to mapOf(
            Language.ENGLISH to "LEVEL",
            Language.ARABIC to "المستوى",
            Language.EGYPTIAN_SLANG to "المستوى",
            Language.FRANCO to "LEVEL"
        ),
        LocKey.MINUTES to mapOf(
            Language.ENGLISH to "min",
            Language.ARABIC to "دقيقة",
            Language.EGYPTIAN_SLANG to "دقيقة",
            Language.FRANCO to "min"
        ),
        LocKey.MEMBER_IDENTITY to mapOf(
            Language.ENGLISH to "MEMBER IDENTITY",
            Language.ARABIC to "هوية العضو",
            Language.EGYPTIAN_SLANG to "الكارت الشخصي",
            Language.FRANCO to "MEMBER IDENTITY"
        ),
        LocKey.TITLE to mapOf(
            Language.ENGLISH to "TITLE",
            Language.ARABIC to "اللقب",
            Language.EGYPTIAN_SLANG to "اللقب",
            Language.FRANCO to "TITLE"
        ),
        LocKey.START_SESSION to mapOf(
            Language.ENGLISH to "START SESSION",
            Language.ARABIC to "ابدأ الجلسة",
            Language.EGYPTIAN_SLANG to "يلا بينا",
            Language.FRANCO to "START SESSION"
        ),
        LocKey.EXPORT_ID to mapOf(
            Language.ENGLISH to "EXPORT ID",
            Language.ARABIC to "تصدير الهوية",
            Language.EGYPTIAN_SLANG to "شير الكارت",
            Language.FRANCO to "EXPORT ID"
        ),
        LocKey.FREE_FLOW to mapOf(
            Language.ENGLISH to "Free-Flow Session",
            Language.ARABIC to "جلسة حرة",
            Language.EGYPTIAN_SLANG to "تركيز حر",
            Language.FRANCO to "Free-Flow Session"
        ),
        LocKey.POMODORO_ON to mapOf(
            Language.ENGLISH to "Pomodoro Mode ON",
            Language.ARABIC to "البومودورو مفعل",
            Language.EGYPTIAN_SLANG to "البومودورو شغال",
            Language.FRANCO to "Pomodoro ON"
        ),
        LocKey.POMODORO_OFF to mapOf(
            Language.ENGLISH to "Pomodoro Mode OFF",
            Language.ARABIC to "البومودورو مغلق",
            Language.EGYPTIAN_SLANG to "البومودورو مقفول",
            Language.FRANCO to "Pomodoro OFF"
        ),
        LocKey.QUIT_SESSION to mapOf(
            Language.ENGLISH to "Quit Session",
            Language.ARABIC to "إنهاء الجلسة",
            Language.EGYPTIAN_SLANG to "كفاية كدة",
            Language.FRANCO to "Quit Session"
        ),
        LocKey.CONFIRM_QUIT to mapOf(
            Language.ENGLISH to "Are you sure you want to end your focus session?",
            Language.ARABIC to "هل أنت متأكد أنك تريد إنهاء الجلسة؟",
            Language.EGYPTIAN_SLANG to "متأكد إنك عايز تقفل الجلسة وتضيع تعبك؟",
            Language.FRANCO to "Mta2ked enk 3ayz t2fel el session?"
        ),
        LocKey.CHOOSE_IDENTITY to mapOf(
            Language.ENGLISH to "CHOOSE IDENTITY",
            Language.ARABIC to "اختر هويتك",
            Language.EGYPTIAN_SLANG to "نقي شكلك",
            Language.FRANCO to "CHOOSE IDENTITY"
        ),
        LocKey.GEOMETRIC_ANCHOR to mapOf(
            Language.ENGLISH to "Geometric anchors represent your focus trajectory.",
            Language.ARABIC to "المرتكزات الهندسية تمثل مسار تركيزك.",
            Language.EGYPTIAN_SLANG to "الأشكال دي بتعبر عن طريقك في التركيز.",
            Language.FRANCO to "Geometric anchors represent focus trajectory."
        ),
        LocKey.USERNAME to mapOf(
            Language.ENGLISH to "USERNAME",
            Language.ARABIC to "اسم المستخدم",
            Language.EGYPTIAN_SLANG to "اسمك",
            Language.FRANCO to "USERNAME"
        ),
        LocKey.STATUS to mapOf(
            Language.ENGLISH to "STATUS",
            Language.ARABIC to "الحالة",
            Language.EGYPTIAN_SLANG to "حالتك إيه",
            Language.FRANCO to "STATUS"
        ),
        LocKey.SAVE_PROFILE to mapOf(
            Language.ENGLISH to "SAVE PROFILE",
            Language.ARABIC to "حفظ الملف الشخصي",
            Language.EGYPTIAN_SLANG to "حفظ التغييرات",
            Language.FRANCO to "SAVE PROFILE"
        ),
        LocKey.VAULT_SECURE_MSG to mapOf(
            Language.ENGLISH to "Your profile details are stored in the secure FocusLedger vault. High-precision updates ensure metadata integrity across all ledger nodes.",
            Language.ARABIC to "يتم تخزين تفاصيل ملفك الشخصي في خزنة FocusLedger الآمنة. تضمن التحديثات عالية الدقة سلامة البيانات عبر جميع العقد.",
            Language.EGYPTIAN_SLANG to "كل بياناتك متشالة في خزنة FocusLedger السحرية وجاهزة دايماً على تليفونك.",
            Language.FRANCO to "Your profile details are stored in the secure FocusLedger vault."
        ),
        LocKey.SETTINGS to mapOf(
            Language.ENGLISH to "Settings",
            Language.ARABIC to "الإعدادات",
            Language.EGYPTIAN_SLANG to "الظبط",
            Language.FRANCO to "Settings"
        ),
        LocKey.PROFILE_APPEARANCE to mapOf(
            Language.ENGLISH to "Profile & Appearance",
            Language.ARABIC to "الملف الشخصي والمظهر",
            Language.EGYPTIAN_SLANG to "الشكل والبروفايل",
            Language.FRANCO to "Profile & Appearance"
        ),
        LocKey.EDIT_PROFILE to mapOf(
            Language.ENGLISH to "Edit Profile",
            Language.ARABIC to "تعديل الملف الشخصي",
            Language.EGYPTIAN_SLANG to "تعديل بروفايلك",
            Language.FRANCO to "Edit Profile"
        ),
        LocKey.MANAGE_IDENTITY to mapOf(
            Language.ENGLISH to "Manage your identity",
            Language.ARABIC to "إدارة هويتك الخاصة",
            Language.EGYPTIAN_SLANG to "عدل اسمك وصورتك",
            Language.FRANCO to "Manage your identity"
        ),
        LocKey.CUSTOMIZATION_SHOP to mapOf(
            Language.ENGLISH to "Customization Shop",
            Language.ARABIC to "متجر التخصيص",
            Language.EGYPTIAN_SLANG to "سوق الدلع والترويق",
            Language.FRANCO to "Customization Shop"
        ),
        LocKey.THEMES_VISUALS to mapOf(
            Language.ENGLISH to "Themes and visual assets",
            Language.ARABIC to "السمات والأصول المرئية",
            Language.EGYPTIAN_SLANG to "أشكال الكروت والخطوط",
            Language.FRANCO to "Themes and visual assets"
        ),
        LocKey.NOTIFICATIONS_DND to mapOf(
            Language.ENGLISH to "Notifications & DND",
            Language.ARABIC to "الإشعارات وعدم الإزعاج",
            Language.EGYPTIAN_SLANG to "الإشعارات والهدوء",
            Language.FRANCO to "Notifications & DND"
        ),
        LocKey.AUTO_DND to mapOf(
            Language.ENGLISH to "Auto Do Not Disturb",
            Language.ARABIC to "عدم الإزعاج التلقائي",
            Language.EGYPTIAN_SLANG to "منع الدوشة التلقائي",
            Language.FRANCO to "Auto DND"
        ),
        LocKey.SYS_OVERRIDE to mapOf(
            Language.ENGLISH to "System Override",
            Language.ARABIC to "تجاوز النظام",
            Language.EGYPTIAN_SLANG to "قفل جرس التليفون",
            Language.FRANCO to "System Override"
        ),
        LocKey.TIMER_ALARM to mapOf(
            Language.ENGLISH to "Timer Alarm Sound",
            Language.ARABIC to "صوت منبه المؤقت",
            Language.EGYPTIAN_SLANG to "نغمة النهاية",
            Language.FRANCO to "Timer Alarm Sound"
        ),
        LocKey.DAILY_REMINDER to mapOf(
            Language.ENGLISH to "Daily Reminder",
            Language.ARABIC to "التذكير اليومي",
            Language.EGYPTIAN_SLANG to "منبه يومي يفكرك",
            Language.FRANCO to "Daily Reminder"
        ),
        LocKey.SET_FOR to mapOf(
            Language.ENGLISH to "Set for",
            Language.ARABIC to "محدد على",
            Language.EGYPTIAN_SLANG to "مظبوط على",
            Language.FRANCO to "Set for"
        ),
        LocKey.LANGUAGE_PREFERENCE to mapOf(
            Language.ENGLISH to "Language Preference",
            Language.ARABIC to "تفضيل اللغة",
            Language.EGYPTIAN_SLANG to "لغة التطبيق",
            Language.FRANCO to "Language Preference"
        ),
        LocKey.DISPLAY_LANGUAGE to mapOf(
            Language.ENGLISH to "Display Language",
            Language.ARABIC to "لغة العرض",
            Language.EGYPTIAN_SLANG to "اللغة",
            Language.FRANCO to "Display Language"
        ),
        LocKey.ABOUT to mapOf(
            Language.ENGLISH to "About",
            Language.ARABIC to "حول",
            Language.EGYPTIAN_SLANG to "عن التطبيق",
            Language.FRANCO to "About"
        ),
        LocKey.SYS_INFO to mapOf(
            Language.ENGLISH to "System Information",
            Language.ARABIC to "معلومات النظام",
            Language.EGYPTIAN_SLANG to "معلومات التطبيق والمطور",
            Language.FRANCO to "System Info"
        ),
        LocKey.PRIVACY_MSG to mapOf(
            Language.ENGLISH to "Your privacy is absolute. 100% Offline, always.",
            Language.ARABIC to "خصوصيتك مطلقة. 100% غير متصل بالإنترنت، دائمًا.",
            Language.EGYPTIAN_SLANG to "خصوصيتك في أمان مطلق. شغال 100% أوفلاين دايماً.",
            Language.FRANCO to "Your privacy is absolute. 100% Offline, always."
        ),
        LocKey.THE_LEDGER to mapOf(
            Language.ENGLISH to "The Ledger",
            Language.ARABIC to "السجل العملي",
            Language.EGYPTIAN_SLANG to "كتاب الإنجازات",
            Language.FRANCO to "The Ledger"
        ),
        LocKey.LEDGER_SUBTITLE to mapOf(
            Language.ENGLISH to "Quantitative audit of your cognitive cycles.",
            Language.ARABIC to "تدقيق كمي لدوراتك المعرفية وتركيزك.",
            Language.EGYPTIAN_SLANG to "دفتر حسابات دقيق لكل دقيقة تركيز ليك.",
            Language.FRANCO to "Quantitative audit of your cognitive cycles."
        ),
        LocKey.TOTAL_FOCUSED_MIN to mapOf(
            Language.ENGLISH to "TOTAL FOCUSED MINUTES",
            Language.ARABIC to "إجمالي دقائق التركيز",
            Language.EGYPTIAN_SLANG to "إجمالي دقايق التركيز",
            Language.FRANCO to "TOTAL FOCUSED MINUTES"
        ),
        LocKey.BROKEN_SESSIONS to mapOf(
            Language.ENGLISH to "BROKEN SESSIONS",
            Language.ARABIC to "الجلسات المكسورة",
            Language.EGYPTIAN_SLANG to "جلسات باظت / استسلام",
            Language.FRANCO to "BROKEN SESSIONS"
        ),
        LocKey.EFFICIENCY_RATIO to mapOf(
            Language.ENGLISH to "EFFICIENCY RATIO",
            Language.ARABIC to "نسبة الكفاءة",
            Language.EGYPTIAN_SLANG to "نسبة شطارتك وإنجازك",
            Language.FRANCO to "EFFICIENCY RATIO"
        ),
        LocKey.VS_LAST_WEEK to mapOf(
            Language.ENGLISH to "vs last week",
            Language.ARABIC to "مقارنة بالأسبوع الماضي",
            Language.EGYPTIAN_SLANG to "عن الأسبوع اللي فات",
            Language.FRANCO to "vs last week"
        ),
        LocKey.IMPROVEMENT to mapOf(
            Language.ENGLISH to "improvement",
            Language.ARABIC to "تحسن",
            Language.EGYPTIAN_SLANG to "تحسن وتطور",
            Language.FRANCO to "improvement"
        ),
        LocKey.PEAK_PERF to mapOf(
            Language.ENGLISH to "Peak performance",
            Language.ARABIC to "أداء ذروة فائق",
            Language.EGYPTIAN_SLANG to "مستوى الوحوش والقمة",
            Language.FRANCO to "Peak performance"
        ),
        LocKey.WEEKLY_OUTPUT to mapOf(
            Language.ENGLISH to "Weekly Output Distribution",
            Language.ARABIC to "توزيع الإنتاجية الأسبوعي",
            Language.EGYPTIAN_SLANG to "رسم بياني لإنتاجيتك الأسبوعية",
            Language.FRANCO to "Weekly Output Distribution"
        ),
        LocKey.WEEK to mapOf(
            Language.ENGLISH to "Week",
            Language.ARABIC to "الأسبوع",
            Language.EGYPTIAN_SLANG to "الأسبوع",
            Language.FRANCO to "Week"
        ),
        LocKey.MONTH to mapOf(
            Language.ENGLISH to "Month",
            Language.ARABIC to "الشهر",
            Language.EGYPTIAN_SLANG to "الشهر",
            Language.FRANCO to "Month"
        ),
        LocKey.RECENT_AUDITS to mapOf(
            Language.ENGLISH to "Recent Audits",
            Language.ARABIC to "عمليات التدقيق الأخيرة",
            Language.EGYPTIAN_SLANG to "أخر جولات التركيز",
            Language.FRANCO to "Recent Audits"
        ),
        LocKey.SHOW_ALL to mapOf(
            Language.ENGLISH to "Show All",
            Language.ARABIC to "عرض الكل",
            Language.EGYPTIAN_SLANG to "كل الجلسات",
            Language.FRANCO to "Show All"
        ),
        LocKey.SUCCESS to mapOf(
            Language.ENGLISH to "SUCCESS",
            Language.ARABIC to "ناجحة",
            Language.EGYPTIAN_SLANG to "كملت للآخر",
            Language.FRANCO to "SUCCESS"
        ),
        LocKey.INTERRUPTED to mapOf(
            Language.ENGLISH to "INTERRUPTED",
            Language.ARABIC to "مكسورة",
            Language.EGYPTIAN_SLANG to "باظت / استسلمت",
            Language.FRANCO to "INTERRUPTED"
        ),
        LocKey.SHOP_TITLE to mapOf(
            Language.ENGLISH to "The Customization Shop",
            Language.ARABIC to "متجر التخصيص والأناقة",
            Language.EGYPTIAN_SLANG to "دكان الروقان والدلع",
            Language.FRANCO to "The Customization Shop"
        ),
        LocKey.SHOP_SUBTITLE to mapOf(
            Language.ENGLISH to "Exchange your hard-earned Focus Points for premium aesthetic upgrades.",
            Language.ARABIC to "استبدل نقاط التركيز التي حصلت عليها بترقيات جمالية مميزة وحصرية.",
            Language.EGYPTIAN_SLANG to "بدل نقط التركيز اللي عرقان فيها بأشكال فخمة وألوان جامدة.",
            Language.FRANCO to "Exchange earned FP for premium aesthetic upgrades."
        ),
        LocKey.PREMIUM_GRADIENTS to mapOf(
            Language.ENGLISH to "Premium Gradients",
            Language.ARABIC to "التدرجات الفاخرة",
            Language.EGYPTIAN_SLANG to "ألوان كروت فخمة",
            Language.FRANCO to "Premium Gradients"
        ),
        LocKey.STATUS_TITLES to mapOf(
            Language.ENGLISH to "Status Titles",
            Language.ARABIC to "ألقاب الهيبة والمقام",
            Language.EGYPTIAN_SLANG to "ألقاب جامدة لبروفايلك",
            Language.FRANCO to "Status Titles"
        ),
        LocKey.MINIMALIST_TYPO to mapOf(
            Language.ENGLISH to "Minimalist Typography",
            Language.ARABIC to "الخطوط الهندسية البسيطة",
            Language.EGYPTIAN_SLANG to "أشكال الخطوط",
            Language.FRANCO to "Minimalist Typography"
        ),
        LocKey.TYPO_SUBTITLE to mapOf(
            Language.ENGLISH to "Transform your entire interface experience.",
            Language.ARABIC to "غيّر مظهر خطوط التطبيق بالكامل.",
            Language.EGYPTIAN_SLANG to "روق على شكل الخطوط في التطبيق.",
            Language.FRANCO to "Transform entire interface typography."
        ),
        LocKey.COMING_SOON to mapOf(
            Language.ENGLISH to "Coming Soon",
            Language.ARABIC to "قريباً جداً",
            Language.EGYPTIAN_SLANG to "قريب أوي استنونا",
            Language.FRANCO to "Coming Soon"
        ),
        LocKey.ZEN_PACK to mapOf(
            Language.ENGLISH to "The 'Zen' Interface Pack",
            Language.ARABIC to "حزمة واجهة 'الزين' الكاملة",
            Language.EGYPTIAN_SLANG to "شكل واجهة 'الرايق والمزاج'",
            Language.FRANCO to "The 'Zen' Interface Pack"
        ),
        LocKey.EQUIPPED to mapOf(
            Language.ENGLISH to "Equipped",
            Language.ARABIC to "مفعّل حالياً",
            Language.EGYPTIAN_SLANG to "شغال دلوقتي",
            Language.FRANCO to "Equipped"
        ),
        LocKey.UNLOCKED to mapOf(
            Language.ENGLISH to "Unlocked",
            Language.ARABIC to "مفتوح",
            Language.EGYPTIAN_SLANG to "اتفتح",
            Language.FRANCO to "Unlocked"
        ),
        LocKey.DEFAULT to mapOf(
            Language.ENGLISH to "Default",
            Language.ARABIC to "الافتراضي",
            Language.EGYPTIAN_SLANG to "العادي",
            Language.FRANCO to "Default"
        ),
        LocKey.PREVIEW to mapOf(
            Language.ENGLISH to "Preview",
            Language.ARABIC to "معاينة",
            Language.EGYPTIAN_SLANG to "شكلها",
            Language.FRANCO to "Preview"
        ),

        // New Translations
        LocKey.WELCOME_TITLE to mapOf(
            Language.ENGLISH to "Welcome to your Cognitive Audit",
            Language.ARABIC to "مرحباً بك في تدقيقك المعرفي",
            Language.EGYPTIAN_SLANG to "أهلاً بيك في رحلة التركيز",
            Language.FRANCO to "Welcome ya basha"
        ),
        LocKey.WELCOME_SUBTITLE to mapOf(
            Language.ENGLISH to "A minimalist quantitative framework designed to measure, discipline, and upgrade your cognitive capacity.",
            Language.ARABIC to "إطار عمل كمي بسيط مصمم لقياس وتأديب وترقية قدرتك المعرفية.",
            Language.EGYPTIAN_SLANG to "نظام بسيط هيساعدك تركز وتطور تفكيرك وتحسب كل دقيقة.",
            Language.FRANCO to "Framework minimalist l-upgrade el focus bta3ak"
        ),
        LocKey.FEATURE_FOCUS_TITLE to mapOf(
            Language.ENGLISH to "Deep Focus Cycles",
            Language.ARABIC to "دورات التركيز العميق",
            Language.EGYPTIAN_SLANG to "دورات تركيز بجد",
            Language.FRANCO to "Deep Focus Cycles"
        ),
        LocKey.FEATURE_FOCUS_DESC to mapOf(
            Language.ENGLISH to "Choose Pomodoro or Free-Flow sessions. Keep the screen active during cycles to accumulate data.",
            Language.ARABIC to "اختر جلسات البومودورو أو التدفق الحر. حافظ على الشاشة نشطة لتجميع البيانات.",
            Language.EGYPTIAN_SLANG to "نقي نظام البومودورو أو التركيز الحر. خلي الشاشة منورة عشان تحسب وقتك.",
            Language.FRANCO to "Choose Pomodoro or Free-Flow sessions"
        ),
        LocKey.FEATURE_LEDGER_TITLE to mapOf(
            Language.ENGLISH to "Quantitative Ledger",
            Language.ARABIC to "السجل الكمي",
            Language.EGYPTIAN_SLANG to "دفتر الحسابات",
            Language.FRANCO to "Quantitative Ledger"
        ),
        LocKey.FEATURE_LEDGER_DESC to mapOf(
            Language.ENGLISH to "Every session is registered. Analyze focus metrics, efficiency ratio, and weekly output distributions.",
            Language.ARABIC to "يتم تسجيل كل جلسة. حلل مقاييس التركيز ونسبة الكفاءة وتوزيعات الإنتاجية.",
            Language.EGYPTIAN_SLANG to "كل جلسة بتتسجل. شوف أرقامك وشطارتك وتطورك كل أسبوع.",
            Language.FRANCO to "Every session is registered & analyzed"
        ),
        LocKey.FEATURE_ECONOMY_TITLE to mapOf(
            Language.ENGLISH to "Focus Token Economy",
            Language.ARABIC to "اقتصاد رموز التركيز",
            Language.EGYPTIAN_SLANG to "نظام نقط التركيز",
            Language.FRANCO to "Focus Token Economy"
        ),
        LocKey.FEATURE_ECONOMY_DESC to mapOf(
            Language.ENGLISH to "Earn 1 FP token per minute of deep focus. Redeem them in the store for premium color gradients and executive fonts.",
            Language.ARABIC to "اكسب رمز FP واحد مقابل كل دقيقة تركيز. استبدلها من المتجر بألوان وخطوط مميزة.",
            Language.EGYPTIAN_SLANG to "خد نقطة عن كل دقيقة تركيز. اشتري بيهم ألوان وخطوط شيك من المحل.",
            Language.FRANCO to "Earn 1 FP per minute of focus"
        ),
        LocKey.FEATURE_PENALTY_TITLE to mapOf(
            Language.ENGLISH to "Strict Distraction Penalty",
            Language.ARABIC to "عقوبة التشتيت الصارمة",
            Language.EGYPTIAN_SLANG to "عقوبة التشتيت",
            Language.FRANCO to "Strict Distraction Penalty"
        ),
        LocKey.FEATURE_PENALTY_DESC to mapOf(
            Language.ENGLISH to "Abandoning sessions or switching apps during focus triggers an automatic penalty of -1 FP. Discipline is non-negotiable.",
            Language.ARABIC to "ترك الجلسات أو تبديل التطبيقات يؤدي لخصم نقطة. الانضباط لا يقبل التفاوض.",
            Language.EGYPTIAN_SLANG to "لو قفلت الجلسة أو فتحت فيسبوك هنخصم منك نقطة. الالتزام ملوش بديل.",
            Language.FRANCO to "Switching apps = -1 FP penalty"
        ),
        LocKey.ENTER_NAME_PLACEHOLDER to mapOf(
            Language.ENGLISH to "Enter your name",
            Language.ARABIC to "أدخل اسمك",
            Language.EGYPTIAN_SLANG to "اكتب اسمك هنا",
            Language.FRANCO to "Enter your name"
        ),
        LocKey.ONBOARDING_FOOTER to mapOf(
            Language.ENGLISH to "This will be permanently printed on your digital member card ID. You can update this anytime in Settings.",
            Language.ARABIC to "سيتم طباعة هذا بشكل دائم على بطاقة هويتك الرقمية. يمكنك تحديثه لاحقاً.",
            Language.EGYPTIAN_SLANG to "الاسم ده هيتكتب على كارت العضوية بتاعك. تقدر تغيره في أي وقت.",
            Language.FRANCO to "This will be on your digital ID card"
        ),
        LocKey.INITIALIZE_PROTOCOL to mapOf(
            Language.ENGLISH to "INITIALIZE PROTOCOL",
            Language.ARABIC to "بدء البروتوكول",
            Language.EGYPTIAN_SLANG to "بدء الرحلة",
            Language.FRANCO to "INITIALIZE PROTOCOL"
        ),
        LocKey.CUSTOM_MUSIC to mapOf(
            Language.ENGLISH to "Custom Focus Music",
            Language.ARABIC to "موسيقى التركيز المخصصة",
            Language.EGYPTIAN_SLANG to "مزيكتك الخاصة للتركيز",
            Language.FRANCO to "Custom Focus Music"
        ),
        LocKey.SELECTED_PREFIX to mapOf(
            Language.ENGLISH to "Selected:",
            Language.ARABIC to "المختار:",
            Language.EGYPTIAN_SLANG to "اللي اخترته:",
            Language.FRANCO to "Selected:"
        ),
        LocKey.MUSIC_SUBTITLE to mapOf(
            Language.ENGLISH to "Use your own music during sessions",
            Language.ARABIC to "استخدم موسيقاك الخاصة أثناء الجلسات",
            Language.EGYPTIAN_SLANG to "شغل مزيكتك وأنت بتركز",
            Language.FRANCO to "Use your own music"
        ),
        LocKey.SELECT_MUSIC_FILE to mapOf(
            Language.ENGLISH to "Select Music File",
            Language.ARABIC to "اختر ملف موسيقى",
            Language.EGYPTIAN_SLANG to "نقي ملف المزيكا",
            Language.FRANCO to "Select Music File"
        ),
        LocKey.VERSION_LABEL to mapOf(
            Language.ENGLISH to "Version 1.0.0",
            Language.ARABIC to "الإصدار 1.0.0",
            Language.EGYPTIAN_SLANG to "نسخة 1.0.0",
            Language.FRANCO to "Version 1.0.0"
        ),
        LocKey.NAV_HOME to mapOf(
            Language.ENGLISH to "Home",
            Language.ARABIC to "الرئيسية",
            Language.EGYPTIAN_SLANG to "الرئيسية",
            Language.FRANCO to "Home"
        ),
        LocKey.NAV_LEDGER to mapOf(
            Language.ENGLISH to "Ledger",
            Language.ARABIC to "السجل",
            Language.EGYPTIAN_SLANG to "السجل",
            Language.FRANCO to "Ledger"
        ),
        LocKey.NAV_SHOP to mapOf(
            Language.ENGLISH to "Shop",
            Language.ARABIC to "المتجر",
            Language.EGYPTIAN_SLANG to "المحل",
            Language.FRANCO to "Shop"
        ),
        LocKey.POMODORO_WORK to mapOf(
            Language.ENGLISH to "Pomodoro Work",
            Language.ARABIC to "عمل بومودورو",
            Language.EGYPTIAN_SLANG to "وقت الشغل",
            Language.FRANCO to "Pomodoro Work"
        ),
        LocKey.POMODORO_BREAK to mapOf(
            Language.ENGLISH to "Pomodoro Break",
            Language.ARABIC to "استراحة بومودورو",
            Language.EGYPTIAN_SLANG to "وقت الراحة",
            Language.FRANCO to "Pomodoro Break"
        ),
        LocKey.MIN_SUFFIX to mapOf(
            Language.ENGLISH to "min",
            Language.ARABIC to "دقيقة",
            Language.EGYPTIAN_SLANG to "دقيقة",
            Language.FRANCO to "min"
        ),
        LocKey.EXPORT_SUCCESS to mapOf(
            Language.ENGLISH to "ID Card exported to Gallery",
            Language.ARABIC to "تم تصدير بطاقة الهوية إلى المعرض",
            Language.EGYPTIAN_SLANG to "الكارت اتسيف في الجاليري",
            Language.FRANCO to "ID Card exported"
        ),
        LocKey.EXPORT_FAILURE to mapOf(
            Language.ENGLISH to "Failed to export ID Card",
            Language.ARABIC to "فشل تصدير بطاقة الهوية",
            Language.EGYPTIAN_SLANG to "معرفناش نسيف الكارت",
            Language.FRANCO to "Export failed"
        ),
        LocKey.BREAK_TIME_MSG to mapOf(
            Language.ENGLISH to "POMODORO BREAK TIME",
            Language.ARABIC to "وقت استراحة البومودورو",
            Language.EGYPTIAN_SLANG to "وقت البريك يا وحش",
            Language.FRANCO to "POMODORO BREAK TIME"
        ),
        LocKey.NO_SESSIONS to mapOf(
            Language.ENGLISH to "No recent sessions found.",
            Language.ARABIC to "لم يتم العثور على جلسات أخيرة.",
            Language.EGYPTIAN_SLANG to "مفيش جلسات لسه.",
            Language.FRANCO to "No sessions found"
        ),
        LocKey.TODAY_PREFIX to mapOf(
            Language.ENGLISH to "Today •",
            Language.ARABIC to "اليوم •",
            Language.EGYPTIAN_SLANG to "النهاردة •",
            Language.FRANCO to "Today •"
        ),
        LocKey.BACK_DESC to mapOf(
            Language.ENGLISH to "Back",
            Language.ARABIC to "رجوع",
            Language.EGYPTIAN_SLANG to "رجوع",
            Language.FRANCO to "Back"
        ),
        LocKey.SELECT_AVATAR to mapOf(
            Language.ENGLISH to "SELECT AVATAR",
            Language.ARABIC to "اختر صورتك",
            Language.EGYPTIAN_SLANG to "نقي صورتك",
            Language.FRANCO to "SELECT AVATAR"
        ),
        LocKey.ERROR_EMPTY_NAME to mapOf(
            Language.ENGLISH to "Username cannot be empty",
            Language.ARABIC to "لا يمكن ترك اسم المستخدم فارغاً",
            Language.EGYPTIAN_SLANG to "لازم تكتب اسمك",
            Language.FRANCO to "Name can't be empty"
        ),
        LocKey.PROFILE_SAVED to mapOf(
            Language.ENGLISH to "Profile Saved Locally",
            Language.ARABIC to "تم حفظ الملف الشخصي محلياً",
            Language.EGYPTIAN_SLANG to "البروفايل اتسيف خلاص",
            Language.FRANCO to "Profile Saved"
        ),
        LocKey.EQUIP_ACTION to mapOf(
            Language.ENGLISH to "EQUIP",
            Language.ARABIC to "تفعيل",
            Language.EGYPTIAN_SLANG to "شغل",
            Language.FRANCO to "EQUIP"
        ),
        LocKey.INSUFFICIENT_BALANCE to mapOf(
            Language.ENGLISH to "Insufficient FP Balance",
            Language.ARABIC to "رصيد نقاط التركيز غير كافٍ",
            Language.EGYPTIAN_SLANG to "نقطك مش كفاية",
            Language.FRANCO to "Insufficient FP"
        ),
        LocKey.UNLOCKED_AND_EQUIPPED to mapOf(
            Language.ENGLISH to "Unlocked and Equipped",
            Language.ARABIC to "تم الفتح والتفعيل",
            Language.EGYPTIAN_SLANG to "اتفتح واشتغل خلاص",
            Language.FRANCO to "Unlocked & Equipped"
        ),

        // Merchandise
        LocKey.GRADIENT_BRUSHED_TITANIUM to mapOf(
            Language.ENGLISH to "Brushed Titanium",
            Language.ARABIC to "تيتانيوم مصقول",
            Language.EGYPTIAN_SLANG to "تيتانيوم شيك",
            Language.FRANCO to "Brushed Titanium"
        ),
        LocKey.DESC_BRUSHED_TITANIUM to mapOf(
            Language.ENGLISH to "Cold, metallic precision.",
            Language.ARABIC to "دقة معدنية باردة.",
            Language.EGYPTIAN_SLANG to "لمسة معدنية فخمة.",
            Language.FRANCO to "Cold, metallic precision."
        ),
        LocKey.GRADIENT_CYBER_NEON to mapOf(
            Language.ENGLISH to "Cyber Neon",
            Language.ARABIC to "نيون سايبر",
            Language.EGYPTIAN_SLANG to "نيون سايبر",
            Language.FRANCO to "Cyber Neon"
        ),
        LocKey.DESC_CYBER_NEON to mapOf(
            Language.ENGLISH to "Deep violet obsidian flow.",
            Language.ARABIC to "تدفق سبج بنفسجي عميق.",
            Language.EGYPTIAN_SLANG to "لون بنفسجي عميق.",
            Language.FRANCO to "Deep violet obsidian flow."
        ),
        LocKey.GRADIENT_ROSE_QUARTZ to mapOf(
            Language.ENGLISH to "Rose Quartz",
            Language.ARABIC to "كوارتز وردي",
            Language.EGYPTIAN_SLANG to "كوارتز وردي",
            Language.FRANCO to "Rose Quartz"
        ),
        LocKey.DESC_ROSE_QUARTZ to mapOf(
            Language.ENGLISH to "Luxury ruby gold obsidian flow.",
            Language.ARABIC to "تدفق سبج ياقوتي ذهبي فاخر.",
            Language.EGYPTIAN_SLANG to "مزيج ياقوتي وذهبي فخم.",
            Language.FRANCO to "Luxury ruby gold obsidian flow."
        ),
        LocKey.GRADIENT_MATTE_OBSIDIAN to mapOf(
            Language.ENGLISH to "Matte Obsidian",
            Language.ARABIC to "سبج مطفي",
            Language.EGYPTIAN_SLANG to "أسود ملكي",
            Language.FRANCO to "Matte Obsidian"
        ),
        LocKey.DESC_MATTE_OBSIDIAN to mapOf(
            Language.ENGLISH to "The ultimate deep void.",
            Language.ARABIC to "الفراغ العميق النهائي.",
            Language.EGYPTIAN_SLANG to "الأسود الفخم العميق.",
            Language.FRANCO to "The ultimate deep void."
        ),
        LocKey.GRADIENT_EMERALD_MATRIX to mapOf(
            Language.ENGLISH to "Emerald Matrix",
            Language.ARABIC to "مصفوفة الزمرد",
            Language.EGYPTIAN_SLANG to "زمرد الماتريكس",
            Language.FRANCO to "Emerald Matrix"
        ),
        LocKey.DESC_EMERALD_MATRIX to mapOf(
            Language.ENGLISH to "High-contrast cyber matrix grid.",
            Language.ARABIC to "شبكة مصفوفة سايبر عالية التباين.",
            Language.EGYPTIAN_SLANG to "شكل مصفوفة أخضر فسفوري.",
            Language.FRANCO to "High-contrast cyber matrix grid."
        ),
        LocKey.GRADIENT_SOLAR_FLARE to mapOf(
            Language.ENGLISH to "Solar Flare",
            Language.ARABIC to "توهج شمسي",
            Language.EGYPTIAN_SLANG to "لهب الشمس",
            Language.FRANCO to "Solar Flare"
        ),
        LocKey.DESC_SOLAR_FLARE to mapOf(
            Language.ENGLISH to "Vibrant magnetic plasma glow.",
            Language.ARABIC to "توهج بلازما مغناطيسي نابض بالحياة.",
            Language.EGYPTIAN_SLANG to "توهج برتقالي ناري.",
            Language.FRANCO to "Vibrant magnetic plasma glow."
        ),
        LocKey.GRADIENT_GOLDEN_AURUM to mapOf(
            Language.ENGLISH to "Golden Aurum",
            Language.ARABIC to "أوروم ذهبي",
            Language.EGYPTIAN_SLANG to "الذهب الملكي",
            Language.FRANCO to "Golden Aurum"
        ),
        LocKey.DESC_GOLDEN_AURUM to mapOf(
            Language.ENGLISH to "Absolute pinnacle prestige gold.",
            Language.ARABIC to "ذهب برستيج في القمة المطلقة.",
            Language.EGYPTIAN_SLANG to "أفخم لون ذهبي ممكن تشوفه.",
            Language.FRANCO to "Absolute pinnacle prestige gold."
        ),

        LocKey.FONT_INTER_SANS to mapOf(
            Language.ENGLISH to "Inter Sans",
            Language.ARABIC to "إنتر سانس",
            Language.EGYPTIAN_SLANG to "إنتر سانس",
            Language.FRANCO to "Inter Sans"
        ),
        LocKey.DESC_INTER_SANS to mapOf(
            Language.ENGLISH to "Modern, clean, legible.",
            Language.ARABIC to "حديث، نظيف، سهل القراءة.",
            Language.EGYPTIAN_SLANG to "شكل عصري ونضيف.",
            Language.FRANCO to "Modern, clean, legible."
        ),
        LocKey.FONT_MONOSPACE_PRO to mapOf(
            Language.ENGLISH to "Monospace Pro",
            Language.ARABIC to "مونو سبيس برو",
            Language.EGYPTIAN_SLANG to "خط المهندسين",
            Language.FRANCO to "Monospace Pro"
        ),
        LocKey.DESC_MONOSPACE_PRO to mapOf(
            Language.ENGLISH to "The engineer's choice.",
            Language.ARABIC to "خيار المهندس.",
            Language.EGYPTIAN_SLANG to "اختيار المبرمجين والمهندسين.",
            Language.FRANCO to "The engineer's choice."
        ),
        LocKey.FONT_EXECUTIVE_SERIF to mapOf(
            Language.ENGLISH to "Executive Serif",
            Language.ARABIC to "إكزيكتيف سيريف",
            Language.EGYPTIAN_SLANG to "الخط الملكي",
            Language.FRANCO to "Executive Serif"
        ),
        LocKey.DESC_EXECUTIVE_SERIF to mapOf(
            Language.ENGLISH to "Luxury and heritage.",
            Language.ARABIC to "الفخامة والتراث.",
            Language.EGYPTIAN_SLANG to "شياكة وفخامة.",
            Language.FRANCO to "Luxury and heritage."
        ),

        LocKey.TITLE_CASUAL to mapOf(
            Language.ENGLISH to "Casual Focus",
            Language.ARABIC to "تركيز عادي",
            Language.EGYPTIAN_SLANG to "تركيز هادي",
            Language.FRANCO to "Casual Focus"
        ),
        LocKey.DESC_CASUAL to mapOf(
            Language.ENGLISH to "Default status.",
            Language.ARABIC to "الحالة الافتراضية.",
            Language.EGYPTIAN_SLANG to "البداية العادية.",
            Language.FRANCO to "Default status."
        ),
        LocKey.TITLE_DEEP_WORK_MASTER to mapOf(
            Language.ENGLISH to "Deep Work Master",
            Language.ARABIC to "سيد العمل العميق",
            Language.EGYPTIAN_SLANG to "وحش الشغل العميق",
            Language.FRANCO to "Deep Work Master"
        ),
        LocKey.DESC_DEEP_WORK_MASTER to mapOf(
            Language.ENGLISH to "Highly-disciplined executive.",
            Language.ARABIC to "مسؤول تنفيذي منضبط للغاية.",
            Language.EGYPTIAN_SLANG to "شخص ملتزم جداً وشاطر.",
            Language.FRANCO to "Highly-disciplined executive."
        ),
        LocKey.TITLE_ELITE to mapOf(
            Language.ENGLISH to "FOCUS ELITE",
            Language.ARABIC to "نخبة التركيز",
            Language.EGYPTIAN_SLANG to "نخبة التركيز",
            Language.FRANCO to "FOCUS ELITE"
        ),
        LocKey.DESC_ELITE to mapOf(
            Language.ENGLISH to "Prestige absolute master.",
            Language.ARABIC to "سيد البرستيج المطلق.",
            Language.EGYPTIAN_SLANG to "المعلم الكبير في التركيز.",
            Language.FRANCO to "Prestige absolute master."
        ),
        LocKey.SUCCESS_NOTIF_BODY to mapOf(
            Language.ENGLISH to "Great job! It's time for your break.",
            Language.ARABIC to "اكتملت الجلسة بنجاح! حان وقت الاستراحة.",
            Language.EGYPTIAN_SLANG to "عاش يا بطل! وقت الاستراحة جه.",
            Language.FRANCO to "Great job! It's time for your break."
        ),
        LocKey.FAILURE_NOTIF_BODY to mapOf(
            Language.ENGLISH to "Your session was broken because you left the app.",
            Language.ARABIC to "تم قطع جلسة التركيز بسبب الخروج من التطبيق.",
            Language.EGYPTIAN_SLANG to "الجلسة باظت عشان خرجت من الأبليكيشن.",
            Language.FRANCO to "Your session was broken because you left the app."
        ),
        LocKey.BREAK_END_NOTIF_TITLE to mapOf(
            Language.ENGLISH to "Break Complete!",
            Language.ARABIC to "انتهت الاستراحة!",
            Language.EGYPTIAN_SLANG to "الاستراحة خلصت!",
            Language.FRANCO to "Break Complete!"
        ),
        LocKey.BREAK_END_NOTIF_BODY to mapOf(
            Language.ENGLISH to "Time to return to deep focus and build your ID card.",
            Language.ARABIC to "حان الوقت للعودة إلى التركيز وتحسين كارت الهوية الخاص بك.",
            Language.EGYPTIAN_SLANG to "يلا نرجع نركز تاني ونظبط الكارت بتاعك.",
            Language.FRANCO to "Time to return to deep focus and build your ID card."
        ),
        LocKey.ERR_DND_PERMISSION to mapOf(
            Language.ENGLISH to "Please grant DND permission to FocusLedger",
            Language.ARABIC to "يرجى منح إذن عدم الإزعاج لـ FocusLedger",
            Language.EGYPTIAN_SLANG to "يا ريت تدينا إذن الهدوء عشان نعرف نشتغل",
            Language.FRANCO to "Please grant DND permission"
        ),
        LocKey.ERR_SETTINGS_OPEN to mapOf(
            Language.ENGLISH to "Unable to open settings",
            Language.ARABIC to "تعذر فتح الإعدادات",
            Language.EGYPTIAN_SLANG to "مش عارفين نفتح الإعدادات دلوقتي",
            Language.FRANCO to "Unable to open settings"
        ),
        LocKey.MON to mapOf(Language.ENGLISH to "MON", Language.ARABIC to "اثن", Language.EGYPTIAN_SLANG to "اتنين", Language.FRANCO to "MON"),
        LocKey.TUE to mapOf(Language.ENGLISH to "TUE", Language.ARABIC to "ثلا", Language.EGYPTIAN_SLANG to "تلات", Language.FRANCO to "TUE"),
        LocKey.WED to mapOf(Language.ENGLISH to "WED", Language.ARABIC to "أرب", Language.EGYPTIAN_SLANG to "اربع", Language.FRANCO to "WED"),
        LocKey.THU to mapOf(Language.ENGLISH to "THU", Language.ARABIC to "خمي", Language.EGYPTIAN_SLANG to "خميس", Language.FRANCO to "THU"),
        LocKey.FRI to mapOf(Language.ENGLISH to "FRI", Language.ARABIC to "جمعة", Language.EGYPTIAN_SLANG to "جمعة", Language.FRANCO to "FRI"),
        LocKey.SAT to mapOf(Language.ENGLISH to "SAT", Language.ARABIC to "سبت", Language.EGYPTIAN_SLANG to "سبت", Language.FRANCO to "SAT"),
        LocKey.SUN to mapOf(Language.ENGLISH to "SUN", Language.ARABIC to "أحد", Language.EGYPTIAN_SLANG to "حد", Language.FRANCO to "SUN")
    )

    fun getLanguage(languageStr: String): Language {
        return when (languageStr.lowercase()) {
            "العربية الفصحى", "arabic" -> Language.ARABIC
            "العامية المصرية", "egyptian slang", "egyptian" -> Language.EGYPTIAN_SLANG
            "الفرانكو", "franco", "franco-arabic" -> Language.FRANCO
            else -> Language.ENGLISH
        }
    }

    fun get(key: LocKey, languageStr: String): String {
        val language = getLanguage(languageStr)
        return dictionary[key]?.get(language) ?: key.name
    }

    fun getDynamicTitle(xp: Int, languageStr: String, activeTitle: String): String {
        if (activeTitle != "Casual Focus" && activeTitle.isNotEmpty()) {
            return activeTitle
        }
        val language = getLanguage(languageStr)
        return if (xp >= 300) {
            when (language) {
                Language.ENGLISH -> "Focus Elite"
                Language.ARABIC -> "مُركّز عميق"
                Language.EGYPTIAN_SLANG -> "شبح التركيز"
                Language.FRANCO -> "Focus El Wahsh"
            }
        } else {
            when (language) {
                Language.ENGLISH -> "Novice"
                Language.ARABIC -> "مبتدئ"
                Language.EGYPTIAN_SLANG -> "لسه بيبدأ"
                Language.FRANCO -> "Yadob Bybd2"
            }
        }
    }
}
