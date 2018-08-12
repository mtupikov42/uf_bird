package com.unit.ft_bird.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unit.ft_bird.controller.Controller;
import com.unit.ft_bird.model.Background;
import com.unit.ft_bird.model.Bird;
import com.unit.ft_bird.model.GameMode;
import com.unit.ft_bird.model.PipeCollector;
import com.unit.ft_bird.model.PipeFactory;

public class FtBird extends ApplicationAdapter {
	static public SpriteBatch	batch;
	private Background			gameBG;
	private Bird				flappyBird;
	private PipeCollector		pipeCollector;
	public static GameMode		gameMode;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameBG = new Background();
		flappyBird = new Bird();
		pipeCollector = new PipeCollector();
		Gdx.input.setInputProcessor(new Controller(flappyBird, pipeCollector));
		gameMode = GameMode.MENU;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		switch (gameMode) {
			case MENU:
				renderMenu();
				break;
			case GAME:
				renderGame();
				break;
			case GAMEOVER:
				renderGameOver();
				break;
		}
		batch.end();
	}

	private void renderMenu() {
		gameBG.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		flappyBird.draw();
	}

	private void renderGame() {
		flappyBird.update();
		pipeCollector.update();
		if (pipeCollector.checkCollision(flappyBird.getCollisionCircle()))
			gameMode = GameMode.GAMEOVER;
		gameBG.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		flappyBird.draw();
	}

	private void renderGameOver() {
		flappyBird.update();
		gameBG.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		flappyBird.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();
		flappyBird.dispose();
		gameBG.dispose();
		PipeFactory.disposeAtlas();
	}


}
