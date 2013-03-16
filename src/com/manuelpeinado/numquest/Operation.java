package com.manuelpeinado.numquest;

import java.util.Random;

import com.manuelpeinado.numquest.utils.Utils;

public enum Operation {
	ADD {
		@Override
		public int getRandomOperand(Random random) {
			return Utils.randomInt(random, 2, 100);
		}

		@Override
		public int apply(int firstOperand, int secondOperand) {
			return firstOperand + secondOperand;
		}

		@Override
		public String toString() {
			return "+";
		}
	},
	SUB {
		@Override
		public int getRandomOperand(Random random) {
			return Utils.randomInt(random, 2, 100);
		}

		@Override
		public int apply(int firstOperand, int secondOperand) {
			return firstOperand - secondOperand;
		}

		@Override
		public String toString() {
			return "-";
		}
	},
	MUL {
		@Override
		public int getRandomOperand(Random random) {
			return Utils.randomInt(random, 2, 10);
		}

		@Override
		public int apply(int firstOperand, int secondOperand) {
			return firstOperand * secondOperand;
		}

		@Override
		public String toString() {
			return "*";
		}
	},
	DIV {
		@Override
		public int getRandomOperand(Random random) {
			return Utils.randomInt(random, 2, 10);
		}

		@Override
		public int apply(int firstOperand, int secondOperand) {
			return firstOperand / secondOperand;
		}

		@Override
		public String toString() {
			return "/";
		}
	};

	public static Operation getRandom(Random random) {
		int n = random.nextInt(values().length);
		return values()[n];
	}

	public abstract int getRandomOperand(Random random);

	public abstract int apply(int firstOperand, int secondOperand);
}
