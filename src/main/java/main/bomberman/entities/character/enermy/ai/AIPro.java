package main.bomberman.entities.character.enermy.ai;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;

import java.util.Stack;

public class AIPro extends AI {
	private Bomber bomber;
	private Enemy myseft;
	private int[][] map;
	private boolean find = false;
	private int n = 15, m = 23;
	private int desX, desY, posX, posY;
	private Point oldP;
	private Point newP;
	private boolean isThinking = false;
	private boolean canSlove = false;

	private Stack<Point> way = new Stack<>();

	public AIPro(Bomber b, Enemy e) {
		this.bomber = b;
		this.myseft = e;
		find = false;
	}
						
	@Override
	public int calcDirection() {
		creatWay();
		return 0;
	}
	
	public void createWay() {
		
	}

	public boolean isThinking() {
		return isThinking;
	}

	public String getNextDir() {
		if (way.size() == 0)
			return " ";
		if (newP.x > oldP.x) {
			return "RIGHT";
		} else if (newP.x < oldP.x) {
			return "LEFT";
		} else {
			if (newP.y > oldP.y) {
				return "DOWN";
			} else
				return "UP";
		}
	}

	public void nextDir() {
		if (way.size() < 1)
			return;
		else {
			oldP = newP;
			newP = way.firstElement();
			way.remove(0);
		}
	}

	public boolean checkPos(int x, int y) {
		if (x == newP.x * myseft.getWidth() && y == newP.y * myseft.getHeight()) {
			nextDir();
			return true;
		}
		return false;
	}

	public boolean inTheDes() {
		return way.size() == 0;
	}

	public boolean canSolve() {
		return canSlove;
	}

	private void resetProperties() {
		find = false;
		canSlove = false;
		map = BoardGame.getMap();
		posX = myseft.getPositionX() / myseft.getWidth();
		posY = myseft.getPositionY() / myseft.getHeight();
		desX = (bomber.getPositionX() + bomber.getWidth() / 2) / bomber.getWidth();
		desY = (bomber.getPositionY() + bomber.getHeight() / 2) / bomber.getHeight();
		newP = new Point(posX, posY);
		oldP = new Point(posX, posY);
		if (!way.empty())
			way.clear();
	}

	public void creatWay() {
		isThinking = true;
		resetProperties();
		// System.out.println("thinking");

		if (posX >= desX) {
			if (posY >= desY) {
				searchTopLeft(posY, posX);
			} else {
				searchTopRight(posY, posX);
			}
		} else {
			if (posY >= desY) {
				searchBottomLeft(posY, posX);
			} else {
				searchBottomRight(posY, posX);
			}
		}

		if (!find) {
			// System.out.println("can't find");
			canSlove = false;
		} else {
			canSlove = true;
			// System.out.println("Find a way!");
		}
		// System.out.println("Solve Completed!");
		isThinking = false;
	}

	public class Point {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void print() {
			System.out.println("(" + x + "," + y + ")");
		}

	}

	public void searchTopLeft(int i, int j) {
		if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0) {
			way.push(new Point(j, i));
			if (i == desY && j == desX) {
				find = true;
			} else {
				searchTopLeft(i - 1, j);
				searchTopLeft(i, j - 1);
				searchTopLeft(i, j + 1);
				searchTopLeft(i + 1, j);
			}
			if (!find)
				way.pop();
			map[i][j] = 1;
		}
	}

	public void searchTopRight(int i, int j) {
		if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0) {
			way.push(new Point(j, i));
			if (i == desY && j == desX) {
				find = true;
			} else {
				searchTopRight(i + 1, j);
				searchTopRight(i, j - 1);
				searchTopRight(i, j + 1);
				searchTopRight(i - 1, j);
			}
			if (!find)
				way.pop();
			map[i][j] = 1;
		}
	}

	public void searchBottomLeft(int i, int j) {
		if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0) {
			way.push(new Point(j, i));
			if (i == desY && j == desX) {
				find = true;
			} else {
				searchBottomLeft(i - 1, j);
				searchBottomLeft(i, j + 1);
				searchBottomLeft(i, j - 1);
				searchBottomLeft(i + 1, j);
			}
			if (!find)
				way.pop();
			map[i][j] = 1;
		}
	}

	public void searchBottomRight(int i, int j) {
		if (i >= 0 && j >= 0 && i < n && j < m && !find && map[i][j] != 0) {
			way.push(new Point(j, i));
			if (i == desY && j == desX) {
				find = true;
			} else {
				searchBottomRight(i + 1, j);
				searchBottomRight(i, j + 1);
				searchBottomRight(i, j - 1);
				searchBottomRight(i - 1, j);
			}
			if (!find)
				way.pop();
			map[i][j] = 1;
		}
	}
}
