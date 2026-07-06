package com.animarena.mod.client;

import com.animarena.mod.entity.FighterEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

/**
 * Renderer عام لأي شخصية من AnimArena. بيستخدم شكل بشري بسيط (biped)
 * زي شكل الزومبي التقني بس بتكستشر مختلف لكل شخصية.
 *
 * ده الـ placeholder المثالي لمبتدئ: تقدر تفتح ملف الـ PNG بتاع كل شخصية
 * في src/main/resources/assets/animarena/textures/entity/ وترسم عليه
 * زي ما تحب من غير ما تلمس أي كود جافا.
 *
 * لما تكون جاهز لموديل مخصص (3D حقيقي غير الشكل البشري العادي)،
 * هتحتاج تستخدم Blockbench وتعمل موديل + layer جديد وتستبدل الكلاس ده.
 */
public class FighterEntityRenderer<T extends FighterEntity> extends MobEntityRenderer<T, BipedEntityModel<T>> {

    private final Identifier texture;

    public FighterEntityRenderer(EntityRendererFactory.Context context, String characterName) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5F);
        this.texture = AnimArenaModClient.texture(characterName);
    }

    @Override
    public Identifier getTexture(T entity) {
        return texture;
    }
}
