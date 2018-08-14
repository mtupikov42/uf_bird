package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.unit.ft_bird.view.FtBird;

public class Bird implements DrawableObject {
	private Animation<TextureAtlas.AtlasRegion>	birdAnimation;
	private TextureAtlas						birdAnimAtlas;
	private float								elapsedTime = 0;
	private final int							BIRD_WIDTH = Gdx.app.getGraphics().getWidth() / 10;
	private final int							BIRD_HEIGHT = Gdx.app.getGraphics().getWidth() / 14;
	private Vector2								position, velocity, acceleration;
	private float								rotation;
	private Circle								collisionCircle;
	private final float							START_X = Gdx.app.getGraphics().getWidth() / 3 - BIRD_WIDTH / 2;
	private final float							START_Y = Gdx.app.getGraphics().getHeight() / 1.65f - BIRD_HEIGHT / 2;

	public Bird() {
		birdAnimAtlas = new TextureAtlas(Gdx.files.internal("data/flappy_bird_animation.atlas"));
		birdAnimation = new Animation<TextureAtlas.AtlasRegion>(1/10f,  birdAnimAtlas.getRegions());
		position = new Vector2(
				START_X,
				START_Y
		);
		velocity = new Vector2(0, -24);
		acceleration = new Vector2(0, 1);
		rotation = 0;
		collisionCircle = new Circle();
	}

	public void setDefault() {
		position.x = START_X;
		position.y = START_Y;
		velocity.x = 0;
		velocity.y = -24;
		acceleration.x = 0;
		acceleration.y = 1.2f;
		rotation = 0;
	}

	public void draw() {
		if (FtBird.gameMode != GameMode.GAME)
			birdAnimation.setFrameDuration(Float.MAX_VALUE);
		else
			birdAnimation.setFrameDuration(1/10f);
		elapsedTime += Gdx.graphics.getDeltaTime();
		FtBird.batch.draw(
				birdAnimation.getKeyFrame(elapsedTime, true),
				position.x,
				position.y,
				BIRD_WIDTH,
				BIRD_HEIGHT,
				BIRD_WIDTH,
				BIRD_HEIGHT,
				1.0f,
				1.0f,
				-rotation
		);
	}

	public void update() {
		velocity.add(acceleration);
		if (velocity.y > 50)
			velocity.y = 50;
		if (collisionCircle.y > Gdx.graphics.getHeight() / 4) {
			position.add(velocity.cpy().scl(-1.0f));
			if (velocity.y < 0) {
				if ((rotation -= 5) < -20) {
					rotation = -20;
				}
			} else if (isFalling()) {
				if ((rotation += 2) > 90)
					rotation = 90;
			}
		}
		collisionCircle.set(position.x + BIRD_WIDTH / 2, position.y + BIRD_HEIGHT / 2, BIRD_WIDTH / 2);
	}

	public void changeSkin(String pathToAtlas) {
		birdAnimAtlas.dispose();
		birdAnimAtlas = new TextureAtlas(Gdx.files.internal(pathToAtlas));
		birdAnimation = new Animation<TextureAtlas.AtlasRegion>(1/10f,  birdAnimAtlas.getRegions());
	}

	public Circle getCollisionCircle() {
		return collisionCircle;
	}

	private boolean isFalling() {
		return velocity.y > 0;
	}

	public void onClick() {
		velocity.y = -24;
	}

	public void dispose() {
		birdAnimAtlas.dispose();
	}
}
