package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.unit.ft_bird.view.FtBird;

public class BirdFont{
	private BitmapFont	font;

	public BirdFont() {
		font = new BitmapFont(Gdx.files.internal("data/myfont.fnt"));
		font.setColor(Color.WHITE);
	}

	public void draw(String text, float x, float y, float scale) {
		font.getData().setScale(scale);
		font.draw(FtBird.batch, text, x, y);
	}

	public void dispose() {
		font.dispose();
	}
}
