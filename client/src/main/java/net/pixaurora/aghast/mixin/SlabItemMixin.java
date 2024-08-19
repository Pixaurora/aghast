package net.pixaurora.aghast.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.StoneSlabBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabItem;

@Mixin(SlabItem.class)
public class SlabItemMixin {
	private final String ILLEGAL_SLAB_TRANSLATION_KEY = "aghast.tile.stoneSlab.what";
	private final String SMOOTH_STONE_SLAB_TRANSLATION_KEY = "aghast.tile.stoneSlab.best";

	@Inject(method = "getTranslationKey", at = @At("HEAD"), cancellable = true)
	public void stopThisFromCrashing(ItemStack stack, CallbackInfoReturnable<String> cir) {
		int metadata = stack.getMetadata();

		if (metadata == 8) {
			cir.setReturnValue(SMOOTH_STONE_SLAB_TRANSLATION_KEY);
		} else if (metadata >= StoneSlabBlock.VARIANTS.length) {
			cir.setReturnValue(ILLEGAL_SLAB_TRANSLATION_KEY);
		}
	}
}
