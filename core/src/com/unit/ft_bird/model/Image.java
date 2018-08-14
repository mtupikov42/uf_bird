package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.unit.ft_bird.view.FtBird;

public class Image implements DrawableObject {
	private TextureRegion						image;
	private TextureAtlas						imageAtlas;
	private Vector2								position;
	private int									imageWidth, imageHeight;

	public Image(String pathToAtlas, int width, int height, float x, float y) {
		imageAtlas = new TextureAtlas(Gdx.files.internal(pathToAtlas));
		image = imageAtlas.findRegion("0001");
		position = new Vector2(x, y);
		imageWidth = width;
		imageHeight = height;
	}

	@Override
	public void draw() {
		FtBird.batch.draw(
				image,
				position.x,
				position.y,
				imageWidth,
				imageHeight
		);
	}

	public void drawScale(float scale) {
		FtBird.batch.draw(
				image,
				position.x,
				position.y,
				imageWidth,
				imageHeight,
				imageWidth,
				imageHeight,
				scale,
				scale,
				0
		);
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void dispose() {
		imageAtlas.dispose();
	}
}
