package com.unit.ft_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;
import java.util.Random;

public class PipeCollector  implements DrawableObject {
	private ArrayList<Pipe> pipes;

	public PipeCollector() {
		pipes = new ArrayList<Pipe>();
		addPipes();
	}

	@Override
	public void draw() {
		for (Pipe pipe : pipes)
			pipe.draw();
	}

	public void update() {
		for (int i = 0; i < pipes.size(); i++) {
			if (i % 2 == 0)
				pipes.get(i).update();
			else
				pipes.get(i).update(pipes.get(i - 1));
		}
	}

	public boolean checkCollision(Circle bird) {
		for (Pipe pipe : pipes)
			if (pipe.checkCollision(bird) || (bird.y <= Gdx.graphics.getHeight() / 4) || (bird.y > Gdx.graphics.getHeight()))
				return true;
		return false;
	}

	public void setDefault() {
		pipes.clear();
		addPipes();
	}

	private void addPipes() {
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			int randomPipeHeight = Gdx.graphics.getHeight() / 2 + random.nextInt(Gdx.graphics.getHeight() / 3);
			pipes.add(new Pipe(
					Gdx.graphics.getWidth() + i * Gdx.graphics.getWidth() / 2,
					randomPipeHeight,
					true
			));
			pipes.add(new Pipe(
					Gdx.graphics.getWidth() + i * Gdx.graphics.getWidth() / 2,
					randomPipeHeight - Gdx.graphics.getHeight() / 2 -  Gdx.graphics.getHeight() / 4,
					false
			));
		}
	}

	@Override
	public void dispose() {
		for (Pipe pipe : pipes)
			pipe.dispose();
	}
}
