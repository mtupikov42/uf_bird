package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.unit.ft_bird.view.FtBird;

public class Button implements DrawableObject {
	private TextureRegion						button;
	private TextureAtlas						buttonAtlas;
	private Vector2								position;
	private int									buttonWidth, buttonHeight;

	public Button(String pathToAtlas, int width, int height, float x, float y) {
		buttonAtlas = new TextureAtlas(Gdx.files.internal(pathToAtlas));
		button = buttonAtlas.findRegion("0001");
		position = new Vector2(x, y);
		buttonWidth = width;
		buttonHeight = height;
	}

	public int getButtonHeight() {
		return buttonHeight;
	}

	public int getButtonWidth() {
		return buttonWidth;
	}

	@Override
	public void draw() {
		FtBird.batch.draw(
				button,
				position.x,
				position.y,
				buttonWidth,
				buttonHeight
		);
	}

	public boolean isClicked(float x, float y) {
		return (
				x >= position.x &&
				x <= (position.x + buttonWidth) &&
				y <= Gdx.graphics.getHeight() - position.y &&
				y >= Gdx.graphics.getHeight() - position.y - buttonHeight
		);
	}

	@Override
	public void dispose() {
		buttonAtlas.dispose();
	}
}
