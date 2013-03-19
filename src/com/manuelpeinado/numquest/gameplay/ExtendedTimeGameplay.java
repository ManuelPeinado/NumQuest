package com.manuelpeinado.numquest.gameplay;

public class ExtendedTimeGameplay extends AbstractGameplay {
	
	private static final int TIME_EXTENSION = 2000;

	public ExtendedTimeGameplay(IGame game) {
		super(game);
	}

	@Override
	public void onOperationSolved() {
		getGame().extendTime(TIME_EXTENSION);
	}
}
