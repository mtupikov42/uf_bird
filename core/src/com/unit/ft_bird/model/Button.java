package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.unit.ft_bird.view.FtBird;

public class Button implements DrawableObject {
	private float								elapsedTime = 0;
	private Animation<TextureAtlas.AtlasRegion> button;
	private TextureAtlas						buttonAtlas;
	private Vector2								position;
	private int									buttonWidth, buttonHeight;
	private float								scaleX, scaleY;

	public Button(String pathToAtlas, float x, float y, float scaleX, float scaleY) {
		buttonAtlas = new TextureAtlas(Gdx.files.internal(pathToAtlas));
		button = new Animation<TextureAtlas.AtlasRegion>(1f,  buttonAtlas.getRegions());
		position = new Vector2(x, y);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		buttonWidth = (int)(buttonAtlas.getTextures().first().getWidth() * scaleX);
		buttonHeight = (int)(buttonAtlas.getTextures().first().getHeight() * scaleY);
	}

	public int getButtonHeight() {
		return buttonHeight;
	}

	// FIX DIS

	@Override
	public void draw() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		FtBird.batch.draw(
				button.getKeyFrame(elapsedTime, true),
				position.x,
				position.y,
				buttonWidth,
				buttonHeight,
				buttonWidth,
				buttonHeight,
				scaleX,
				scaleY,
				0f
		);
	}

	public boolean isClicked(float x, float y) {
		return (x >= position.x && x <= position.x + buttonWidth && y >= position.y && y <= position.y + buttonHeight);
	}

	@Override
	public void dispose() {
		buttonAtlas.dispose();
	}
}
