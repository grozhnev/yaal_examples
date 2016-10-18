package coderetreat.lifegame.impl;

import java.util.BitSet;

import coderetreat.lifegame.api.Square;

public class IndefiniteBitSetSquareImpl implements Square {
	private final int size = 3;
	private final BitSet data = new BitSet(size * size);

	@Override
	public int size() {
		return size;
	}

	/**
	 * abc def ghi
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	public int aliveNeiboursCount(int x, int y) {
		return (byte) (isAliveSafe(x - 1, y - 1) + isAliveSafe(x - 1, y) + isAliveSafe(x - 1, y + 1)
				+ isAliveSafe(x, y - 1) + isAliveSafe(x, y + 1) + isAliveSafe(x + 1, y - 1) + isAliveSafe(x + 1, y)
				+ isAliveSafe(x + 1, y + 1));
	}

	private byte isAliveSafe(int x, int y) {
		if (x <= 1 || x >= data.length()) {
			return 0;
		}
		if (y <= 1 || y >= data.length()) {
			return 0;
		}
		int index = x * size() + y;
		return data.get(index) ? (byte) 1 : (byte) 0;
	}

	@Override
	public void setAlive(int x, int y, boolean alive) {
		int e = x * size() + y;
		data.set(e, alive);
	}

	@Override
	public boolean isAlive(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
