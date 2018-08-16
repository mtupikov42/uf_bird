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
			game.getFlapWings().play();
		} else if (FtBird.gameMode == GameMode.MAIN_MENU) {
			if (game.getSkinsButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.CHOOSE_SKIN;
			else if (game.getMenuButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.MENU;
			else
				FtBird.gameMode = GameMode.GAME;
		} else if (FtBird.gameMode == GameMode.GAMEOVER) {
			if (game.getPlayButton().isClicked(screenX, screenY)) {
				FtBird.gameMode = GameMode.MAIN_MENU;
				game.getFlappyBird().setDefault();
				game.getPipeCollector().setDefault();
			} else if (game.getScoreButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.SCORE_MENU;
		} else if (FtBird.gameMode == GameMode.CHOOSE_SKIN) {
			if (game.getOkButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.MAIN_MENU;
			else {
				if (game.getArrowLeftButton().isClicked(screenX, screenY)) {
					if (game.getScroller().getChosenItem() > 0)
						game.getScroller().decChosenItem();
				} else if (game.getArrowRightButton().isClicked(screenX, screenY)) {
					if (game.getScroller().getChosenItem() < game.getScroller().getScrollerSize() - 1)
						game.getScroller().incChosenItem();
				}
				changeSkin();
			}
		} else if (FtBird.gameMode == GameMode.MENU) {
			if (game.getPlayButton().isClicked(screenX, screenY)) {
				FtBird.gameMode = GameMode.MAIN_MENU;
				game.getFlappyBird().setDefault();
				game.getPipeCollector().setDefault();
			} else if (game.getScoreButton().isClicked(screenX, screenY))
				FtBird.gameMode = GameMode.SCORE_MENU;
		} else if (FtBird.gameMode == GameMode.SCORE_MENU) {
			FtBird.gameMode = GameMode.MENU;
		}
		return true;
	}

	private void	changeSkin() {
		switch (game.getScroller().getChosenItem()) {
			case 0:
				game.getFlappyBird().changeSkin("data/flappy_bird_blue_animation.atlas");
				break;
			case 1:
				game.getFlappyBird().changeSkin("data/flappy_bird_animation.atlas");
				break;
			case 2:
				game.getFlappyBird().changeSkin("data/flappy_bird_red_animation.atlas");
				break;
			case 3:
				game.getFlappyBird().changeSkin("data/abodnar_animation.atlas");
				break;
			case 4:
				game.getFlappyBird().changeSkin("data/unit_animation.atlas");
				break;
		}
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
