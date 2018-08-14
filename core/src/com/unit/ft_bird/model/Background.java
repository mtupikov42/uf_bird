package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.unit.ft_bird.view.FtBird;

public class Background implements DrawableObject {
	private float								elapsedTime = 0;
	private TextureAtlas						backgroundAtlas;
	private TextureRegion bg;
	private TextureAtlas						floorAtlas;
	private Animation<TextureAtlas.AtlasRegion> floorAnimation;
	private final int							FLOOR_HEIGHT = Gdx.graphics.getHeight() / 4;

	public Background() {
		backgroundAtlas = new TextureAtlas(Gdx.files.internal("data/bg.atlas"));
		bg = backgroundAtlas.findRegion("0001");
		floorAtlas = new TextureAtlas(Gdx.files.internal("data/floor.atlas"));
		floorAnimation = new Animation<TextureAtlas.AtlasRegion>(1/9f, floorAtlas.getRegions());
	}

	public void draw() {
		FtBird.batch.draw(
				bg,
				0,
				0,
				Gdx.app.getGraphics().getWidth(),
				Gdx.app.getGraphics().getHeight()
		);
	}

	public void drawFloor() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		if (FtBird.gameMode != GameMode.GAME)
			floorAnimation.setFrameDuration(Float.MAX_VALUE);
		else
			floorAnimation.setFrameDuration(1/18f);
		FtBird.batch.draw(
				floorAnimation.getKeyFrame(elapsedTime, true),
				0,
				0,
				Gdx.app.getGraphics().getWidth(),
				FLOOR_HEIGHT
		);
	}

	public void dispose() {
		backgroundAtlas.dispose();
		floorAtlas.dispose();
	}
}
