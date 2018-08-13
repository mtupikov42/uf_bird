package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.unit.ft_bird.view.FtBird;

import java.util.Random;

public class Pipe implements DrawableObject {
	private float								elapsedTime = 0;
	private Animation<TextureAtlas.AtlasRegion>	pipe;
	private Vector2								position;
	private final int							PIPE_WIDTH = Gdx.app.getGraphics().getWidth() / 10;
	private final int							PIPE_HEIGHT = Gdx.app.getGraphics().getHeight() / 2;
	private Rectangle							collisionRectangle;
	private boolean								isScored;

	public Pipe(int x, int y, boolean isUp) {
		pipe = isUp ? PipeFactory.getPipeUp() : PipeFactory.getPipeDown();
		position = new Vector2(x, y);
		collisionRectangle = new Rectangle();
		isScored = false;
	}

	public void draw() {
		elapsedTime += Gdx.graphics.getDeltaTime();
		FtBird.batch.draw(
				pipe.getKeyFrame(elapsedTime, true),
				position.x,
				position.y,
				PIPE_WIDTH,
				PIPE_HEIGHT
		);
	}

	public void update() {
		Random random = new Random();
		if ((position.x -= 8) < -PIPE_WIDTH) {
			position.x = Gdx.app.getGraphics().getWidth() + Gdx.app.getGraphics().getWidth() / 3;
			position.y = Gdx.graphics.getHeight() / 2 + random.nextInt(Gdx.graphics.getHeight() / 3);
			isScored = false;
		}
		collisionRectangle.set(position.x, position.y, PIPE_WIDTH, PIPE_HEIGHT);
	}

	public void update(Pipe before) {
		if ((position.x -= 8) < -PIPE_WIDTH) {
			position.x = Gdx.app.getGraphics().getWidth() + Gdx.app.getGraphics().getWidth() / 3;
			position.y = before.position.y - Gdx.graphics.getHeight() / 2 -  Gdx.graphics.getHeight() / 4;
			isScored = false;
		}
		collisionRectangle.set(position.x, position.y, PIPE_WIDTH, PIPE_HEIGHT);
	}

	public boolean checkCollision(Circle bird) {
		if (!isScored && (bird.x > position.x + PIPE_WIDTH / 2)) {
			FtBird.score++;
			isScored = true;
		}
		return Intersector.overlaps(bird, collisionRectangle);
	}

	public void dispose() {}
}
