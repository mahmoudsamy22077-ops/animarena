# AnimArena — مود المصارعة

مود Fabric لـ Minecraft 1.20.1 يضيف 4 شخصيات مصارعة أصلية، كل واحدة بقدرة خاصة:

| الشخصية | النوع | القدرة | ملاحظات |
|---|---|---|---|
| Kaito Windrunner | نينجا سريع | Dash Strike (اندفاع + ضربة) | حياة قليلة، سريع جداً |
| Draven Ironfist | ضخم/تانك | Ground Slam (ضربة أرضية AOE) | حياة عالية، بطيء |
| Ryua Emberweave | ساحرة نار | Ember Bolt (كورة نار عن بعد) | هشة، مدى بعيد |
| Selys Frostveil | دفاعية | Guardian Shield (درع + تعافي) | متوازنة، صعبة القتل |

## المتطلبات قبل ما تبدأ

1. **Java 21 JDK** — نزّله من [Adoptium](https://adoptium.net/) (اختار نسخة 21)
2. **IntelliJ IDEA** (النسخة المجانية Community كفاية) — أو أي IDE بيدعم Gradle
3. اتصال إنترنت شغال وقت أول Build (عشان يحمل المكتبات)

## خطوات التشغيل

### 1. افتح المشروع
- افتح IntelliJ IDEA
- File → Open → اختار فولدر `animarena` (اللي فيه build.gradle)
- سيبه يعمل Sync لأول مرة (هياخد وقت، بيحمل Minecraft + Mappings + Fabric API)

### 2. شغّل بيئة التطوير
**ملحوظة:** ملفات `gradlew` و `gradlew.bat` (الـ wrapper scripts) مش موجودة في المشروع لأني معنديش اتصال إنترنت أقدر أنزل بيه الملفات الثنائية بتاعتها. أسهل حل:
- في IntelliJ IDEA، لما تفتح المشروع هيسألك يعمل Import كـ Gradle project — سيبه يستخدم أي Gradle موجود عندك، أو يديك خيار يظبط نسخة تلقائيًا
- أو لو عندك Gradle متثبت أصلاً على جهازك، من أي terminal جوه فولدر المشروع اكتب:
```
gradle wrapper --gradle-version 8.8
```
ده هيولّد ملفات الـ wrapper تلقائيًا، وبعدها تقدر تستخدم:
```
./gradlew runClient
```
ده هيفتح نسخة Minecraft فيها المود شغال تلقائيًا. لو حصل خطأ، رجّع اقرأ رسالة الخطأ كاملة وابعتهالي.

### 3. جرب المود
- افتح Creative mode
- دور في الـ Inventory على تبويب "Combat" أو دور بالاسم على "Kaito" / "Draven" / "Ryua" / "Selys"
- هتلاقي 4 عناصر استحضار (Summon Eggs) بألوان مختلفة
- استخدمهم عشان تستدعي الشخصيات، وحطهم قريب من بعض عشان تتقاتل

## الخطوة اللي جاية: التكستشرز (Textures)

دلوقتي كل الشخصيات شكلها زي الزومبي (placeholder) لحد ما تحط تكستشر خاص بيهم. ده أسهل حتة تعدل فيها كمبتدئ:

1. افتح `src/main/resources/assets/animarena/textures/entity/`
2. لازم تحط فيه 4 ملفات PNG بمقاس **64x64** بيكسل بنفس الأسماء:
   - `kaito_windrunner.png`
   - `draven_ironfist.png`
   - `ryua_emberweave.png`
   - `selys_frostveil.png`
3. أسهل طريقة: حمّل أي "zombie skin template" 64x64 من النت (فيه مواقع كتير بتديك القالب فاضي)، وارسم عليه بأي برنامج (حتى Paint) أو حمل سكن جاهز وجرب.
4. بعد ما تحط الملفات، اعمل `./gradlew runClient` تاني وهتلاقي الشخصية اتغير شكلها.

**ملحوظة:** لو عايز موديل 3D حقيقي (مش شكل بشري بس بلون مختلف)، هتحتاج تتعلم برنامج [Blockbench](https://www.blockbench.net/) (مجاني وسهل) وتعمل موديل مخصص، وبعدين نعدل كلاس `FighterEntityRenderer` عشان يستخدمه بدل شكل الزومبي.

## إزاي تضيف شخصية خامسة (لما تحب توسع)

1. اعمل كلاس Ability جديد في `ability/` (زي DashStrikeAbility) لو عايز قدرة جديدة
2. اعمل كلاس Entity جديد في `entity/` بيعمل extend لـ `FighterEntity` (انسخ أي شخصية موجودة كنموذج)
3. سجله في `ModEntities.java` (نفس نمط الأربعة الموجودين)
4. سجل الـ Summon Item بتاعه في `ModItems.java`
5. سجل الـ Renderer في `AnimArenaModClient.java`
6. ضيف الاسم في ملفات `lang/en_us.json` و `lang/ar_sa.json`

## مشاكل شائعة

- **"gradlew: Permission denied"** على Linux/Mac: شغّل `chmod +x gradlew` مرة واحدة
- **الـ Sync بياخد وقت طويل**: طبيعي أول مرة، بيحمل حاجات كتير
- **خطأ compile عن method مش موجودة**: يبقى محتاجين نظبط اسم method حسب نسخة الـ mappings بالظبط، ابعتلي رسالة الخطأ وهساعدك أظبطها فورًا

## بنية المشروع

```
animarena/
├── build.gradle              ← إعدادات البناء
├── gradle.properties         ← أرقام الإصدارات
├── settings.gradle
└── src/main/
    ├── java/com/animarena/mod/
    │   ├── AnimArenaMod.java          ← نقطة الدخول الرئيسية
    │   ├── ability/                   ← نظام القدرات
    │   │   ├── Ability.java
    │   │   ├── AbilityManager.java
    │   │   ├── DashStrikeAbility.java
    │   │   ├── FireballAbility.java
    │   │   ├── GroundSlamAbility.java
    │   │   └── GuardianShieldAbility.java
    │   ├── entity/                    ← الشخصيات
    │   │   ├── FighterEntity.java     ← الكلاس الأساسي
    │   │   ├── KaitoWindrunnerEntity.java
    │   │   ├── DravenIronfistEntity.java
    │   │   ├── RyuaEmberweaveEntity.java
    │   │   ├── SelysFrostveilEntity.java
    │   │   └── ModEntities.java       ← التسجيل
    │   ├── item/
    │   │   └── ModItems.java          ← عناصر الاستحضار
    │   └── client/
    │       ├── AnimArenaModClient.java
    │       └── FighterEntityRenderer.java
    └── resources/
        ├── fabric.mod.json
        └── assets/animarena/
            ├── lang/ (en_us.json, ar_sa.json)
            ├── models/item/ (4 ملفات)
            └── textures/entity/  ← هنا تحط التكستشرز بتاعتك
```
