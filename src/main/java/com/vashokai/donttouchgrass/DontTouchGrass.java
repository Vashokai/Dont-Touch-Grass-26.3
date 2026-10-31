package com.vashokai.donttouchgrass;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontTouchGrass implements ModInitializer {
	public static final String MOD_ID = "donttouchgrass";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		// Run Every tick
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			var players = server.getPlayerList().getPlayers();

			for (ServerPlayer player : players) {
				var playerPos = player.blockPosition();
				var blockPos = playerPos.below();
				var world = player.level();
				var blockState = world.getBlockState(blockPos);
				if (blockState.is(Blocks.GRASS_BLOCK)){
					player.hurt(player.damageSources().magic(), 2.0F);
				}
				if (blockState.is(Blocks.NETHERRACK)){
					player.hurt(player.damageSources().magic(), 2.0F);
				}
				if (blockState.is(Blocks.END_STONE)){
					player.hurt(player.damageSources().magic(), 2.0F);
				}
			}

		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
