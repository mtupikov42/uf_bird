package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class PipeFactory {
	static private TextureAtlas pipeAtlas = new TextureAtlas(Gdx.files.internal("data/pipes.atlas"));

	static Animation<TextureAtlas.AtlasRegion>		getPipeUp() {
		return new Animation<TextureAtlas.AtlasRegion>(1.0f, pipeAtlas.getRegions().get(1));
	}

	static Animation<TextureAtlas.AtlasRegion>		getPipeDown() {
		return new Animation<TextureAtlas.AtlasRegion>(1.0f, pipeAtlas.getRegions().get(0));
	}

	public static void	disposeAtlas() {
		pipeAtlas.dispose();
	}
}
