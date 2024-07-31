package com.modding.lemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;

public class Fluid extends FluidType
{
	private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;

    public Fluid(final String name, final ResourceLocation stillTexture, final ResourceLocation flowingTexture) {
        super(null);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
    }

    public ResourceLocation getStillTexture() {
        return stillTexture;
    }

    public ResourceLocation getFlowingTexture() {
        return flowingTexture;
    }

}
