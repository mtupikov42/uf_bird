package com.unit.ft_bird.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.unit.ft_bird.model.Bird;
import com.unit.ft_bird.model.GameMode;
import com.unit.ft_bird.model.PipeCollector;
import com.unit.ft_bird.view.FtBird;

public class Controller implements InputProcessor {
	private Bird			flappyBird;
	private PipeCollector	pipeCollector;

	public Controller(Bird flappyBird, PipeCollector pipeCollector) {
		this.flappyBird = flappyBird;
		this.pipeCollector = pipeCollector;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (FtBird.gameMode == GameMode.GAME) {
			flappyBird.onClick();
		} else if (FtBird.gameMode == GameMode.MENU) {
			FtBird.gameMode = GameMode.GAME;
		} else if (FtBird.gameMode == GameMode.GAMEOVER) {
			FtBird.gameMode = GameMode.MENU;
			flappyBird.setDefault();
			pipeCollector.setDefault();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
