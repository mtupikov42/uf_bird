package com.unit.ft_bird.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.unit.ft_bird.controller.Controller;
import com.unit.ft_bird.model.Background;
import com.unit.ft_bird.model.Bird;
import com.unit.ft_bird.model.Button;
import com.unit.ft_bird.model.GameMode;
import com.unit.ft_bird.model.PipeCollector;
import com.unit.ft_bird.model.PipeFactory;

public class FtBird extends ApplicationAdapter {
	static public SpriteBatch	batch;
	private Background			gameBG;
	private Bird				flappyBird;
	private PipeCollector		pipeCollector;
	private BitmapFont			font;
	private Button				tap_tap, menuButton, skinsButton, okButton, playButton, scoreButton;
	public static GameMode		gameMode;
	public static int			score = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameBG = new Background();
		flappyBird = new Bird();
		pipeCollector = new PipeCollector();
		tap_tap = new Button(
				"data/flappy_tap_tap.atlas",
				Gdx.graphics.getWidth() / 2 - 50,
				Gdx.graphics.getHeight() / 2 + 100,
				0.8f,
				0.8f
		);
		menuButton = new Button(
				"data/menu_button.atlas",
				0,
				0,
				0.6f,
				0.4f
		);
		skinsButton = new Button(
				"data/skins_button.atlas",
				0,
				menuButton.getButtonHeight() + 1,
				0.6f,
				0.4f
		);
		font = new BitmapFont(Gdx.files.internal("data/myfont.fnt"));
		font.setColor(Color.WHITE);
		font.getData().setScale(Gdx.graphics.getWidth() / 300);
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
		tap_tap.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		menuButton.draw();
		skinsButton.draw();
		flappyBird.draw();
		score = 0;
	}

	private void renderGame() {
		gameBG.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		flappyBird.draw();
		font.draw(batch, Integer.toString(score / 2), 1, Gdx.graphics.getHeight() - 20);
		flappyBird.update();
		pipeCollector.update();
		if (pipeCollector.checkCollision(flappyBird.getCollisionCircle()))
			gameMode = GameMode.GAMEOVER;
	}

	private void renderGameOver() {
		gameBG.draw();
		pipeCollector.draw();
		gameBG.drawFloor();
		flappyBird.draw();
		font.draw(batch, Integer.toString(score / 2), 1, Gdx.graphics.getHeight() - 20);
		flappyBird.update();
	}

	@Override
	public void dispose () {
		batch.dispose();
		tap_tap.dispose();
		skinsButton.dispose();
		flappyBird.dispose();
		menuButton.dispose();
		gameBG.dispose();
		PipeFactory.disposeAtlas();
	}


}
