package com.unit.ft_bird.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class SkinScroller implements DrawableObject{
	private ArrayList<Image>	scrollArray;
	private float				startX, startY, delta;
	private int					chosenItem;

	public SkinScroller(float x, float y, float delta) {
		scrollArray = new ArrayList<Image>();
		startX = x;
		startY = y;
		chosenItem = 1;
		this.delta = delta;
	}

	public void add(Image item) {
		scrollArray.add(item);
	}

	@Override
	public void draw() {
		int index;
		if (chosenItem == 0)
			index = 0;
		else if (chosenItem == scrollArray.size() - 1)
			index = chosenItem - 2;
		else
			index = chosenItem - 1;
		for (int i = index; i < index + 3; i++) {
			Image image = scrollArray.get(i);
			if (i == index) {
				float width;
				if (i == chosenItem)
					width = startX + 50;
				else
					width = startX;
				image.setPosition(new Vector2(width, startY));
			} else {
				Image back = scrollArray.get(i - 1);
				float width;
				if (i == chosenItem)
					width = back.getImageWidth() * 1.5f;
				else
					width = back.getImageWidth();
				image.setPosition(new Vector2(
						back.getPosition().x + width + delta, startY)
				);
			}
			if (i == chosenItem)
				image.drawScale(1.5f);
			else
				image.draw();
		}
	}

	@Override
	public void dispose() {
		for (Image image: scrollArray)
			image.dispose();
	}

	public int getChosenItem() {
		return chosenItem;
	}

	public void incChosenItem() {
		++chosenItem;
	}

	public void decChosenItem() {
		--chosenItem;
	}

	public int getScrollerSize() {
		return scrollArray.size();
	}
}
