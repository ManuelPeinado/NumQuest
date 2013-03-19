package com.manuelpeinado.numquest.gameplay;

public abstract class AbstractGameplay {
	private final IGame game;

	public AbstractGameplay(IGame game) {
		this.game = game;
	}

	public void onOperationSolved() {
	}

	protected IGame getGame() {
		return game;
	}
}
