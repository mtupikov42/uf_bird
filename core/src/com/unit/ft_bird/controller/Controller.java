package com.unit.ft_bird.controller;

import com.badlogic.gdx.InputProcessor;
import com.unit.ft_bird.model.GameMode;
import com.unit.ft_bird.view.FtBird;

public class Controller implements InputProcessor {
	private FtBird	game;

	public Controller(FtBird game) {
		this.game = game;
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
			game.getFlappyBird().onClick();
		} else if (FtBird.gameMode == GameMode.MENU) {
			if (game.getSkinsButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.CHOOSE_SKIN;
			else
				FtBird.gameMode = GameMode.GAME;
		} else if (FtBird.gameMode == GameMode.GAMEOVER) {
			if (game.getPlayButton().isClicked(screenX, screenY)) {
				FtBird.gameMode = GameMode.MENU;
				game.getFlappyBird().setDefault();
				game.getPipeCollector().setDefault();
			}
		} else if (FtBird.gameMode == GameMode.CHOOSE_SKIN) {
			if (game.getOkButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.MENU;
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
