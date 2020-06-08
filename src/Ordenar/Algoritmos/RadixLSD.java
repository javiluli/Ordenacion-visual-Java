package Ordenar.Algoritmos;

import Ordenar.AdicionalesSorts;
import Ordenar.ISort;
import Principal.DibujarGraficos;
import Principal.MainAplicacion;

public class RadixLSD extends AdicionalesSorts implements ISort {
	public RadixLSD(MainAplicacion m, int[] n) {
		this.m = m;
		sort(n);
	}

	@Override
	public void sort(int[] n) {
		setInicio(System.currentTimeMillis());
		ordenar(n, 4);
		DibujarGraficos.finSort = true;
	}

	public void ordenar(int[] array, int len) {
		if (array.length == 0) {
			return;
		}

		int minValue = array[0];
		int maxValue = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
			} else if (array[i] > maxValue) {
				maxValue = array[i];
			}
		}

		int exponent = 1;
		while ((maxValue - minValue) / exponent >= 1) {
			countingSortByDigit(array, len, exponent, minValue);
			exponent *= len;
		}
	}

	private void countingSortByDigit(int[] array, int len, int exponent, int minValue) {
		int bucketIndex;
		int[] buckets = new int[len];
		int[] output = new int[array.length];

		for (int i = 0; i < len; i++) {
			buckets[i] = 0;
			accesoArray++;
		}

		for (int i = 0; i < array.length; i++) {
			bucketIndex = (int) (((array[i] - minValue) / exponent) % len);
			buckets[bucketIndex]++;
			accesoArray++;
		}

		for (int i = 1; i < len; i++) {
			buckets[i] += buckets[i - 1];
			accesoArray++;
		}

		for (int i = array.length - 1; i >= 0; i--) {
			bucketIndex = (int) (((array[i] - minValue) / exponent) % len);
			output[--buckets[bucketIndex]] = array[i];
			accesoArray++;
			cambiosArray++;

		}

		for (int i = 0; i < array.length; i++) {
			array[i] = output[i];
			accesoArray++;
			cambiosArray++;
			m.updateAnimaciones();
		}

	}
}
