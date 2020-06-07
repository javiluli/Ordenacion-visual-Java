package Sorts.Algoritmos;

import Interfaz.Sort;
import Principal.Barras;
import Principal.Main;
import Sorts.AdicionalesSorts;

public class Pancake extends AdicionalesSorts implements Sort {
	public Pancake(Main m, int[] n) {
		this.m = m;
		sort(n);
	}

	@Override
	public void sort(int[] n) {
		setInicio(System.currentTimeMillis());

		for (int curr_size = n.length; curr_size > 1; --curr_size) {
			int mi = findMax(n, curr_size);
			if (mi != curr_size - 1) {
				flip(n, mi);
				flip(n, curr_size - 1);
			}
			m.updateAnimaciones();
		}
		Barras.finSort = true;
	}

	static void flip(int arr[], int i) {
		int temp, start = 0;
		while (start < i) {
			temp = arr[start];
			arr[start] = arr[i];
			arr[i] = temp;
			start++;
			i--;
		}
	}

	static int findMax(int arr[], int n) {
		int mi, i;
		for (mi = 0, i = 0; i < n; ++i)
			if (arr[i] > arr[mi])
				mi = i;
		return mi;
	}

	@Override
	public String getNombre() {
		return "Pancake Sort";
	}

}
