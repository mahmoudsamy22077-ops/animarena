package com.animarena.mod.client;

import com.animarena.mod.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.util.Identifier;

/**
 * تسجيل الـ Renderers بتاعة كل شخصية.
 *
 * ملحوظة مهمة: هنا بنستخدم شكل الزومبي (هيكل بشري عادي/بايبد) كـ placeholder
 * مؤقت لحد ما تحط تكستشرز وموديلز خاصة بيك. ده أسهل حاجة كنقطة بداية
 * لمبتدئ - وبعدين تقدر تستبدلها بموديل مخصص (Blockbench مثلاً) وتغير
 * الـ Renderer هنا لموديل مخصص لما تكون جاهز.
 *
 * التكستشر بتاعة كل شخصية موجودة في:
 * src/main/resources/assets/animarena/textures/entity/<اسم الشخصية>.png
 * وده أول حاجة تقدر تعدلها إنت كمبتدئ بدون ما تلمس الكود.
 */
public class AnimArenaModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.KAITO_WINDRUNNER,
                context -> new FighterEntityRenderer<>(context, "kaito_windrunner"));
        EntityRendererRegistry.register(ModEntities.DRAVEN_IRONFIST,
                context -> new FighterEntityRenderer<>(context, "draven_ironfist"));
        EntityRendererRegistry.register(ModEntities.RYUA_EMBERWEAVE,
                context -> new FighterEntityRenderer<>(context, "ryua_emberweave"));
        EntityRendererRegistry.register(ModEntities.SELYS_FROSTVEIL,
                context -> new FighterEntityRenderer<>(context, "selys_frostveil"));
    }

    public static Identifier texture(String character) {
        return new Identifier("animarena", "textures/entity/" + character + ".png");
    }
}
