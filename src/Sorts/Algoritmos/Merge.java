package Sorts.Algoritmos;

import Interfaz.Sort;
import Principal.Barras;
import Principal.Main;
import Sorts.AdicionalesSorts;

public class Merge extends AdicionalesSorts implements Sort {

	public Merge(Main m, int[] n) {
		this.m = m;
		sort(n);
	}

	@Override
	public void sort(int[] n) {
		setInicio(System.currentTimeMillis());
		sortingMerge(0, n.length - 1, n);
		Barras.finSort = true;
	}

	public void sortingMerge(int left, int right, int[] n) {
		if (left < right) {
			cambiosArray++;
			int mitad = (left + right) / 2;
			sortingMerge(left, mitad, n);
			sortingMerge(mitad + 1, right, n);
			merge(left, mitad, right, n);
		}
	}

	void merge(int left, int mitad, int right, int[] arr) {
		int len_left = mitad - left + 1;
		int len_right = right - mitad;

		int arr_left[] = new int[len_left];
		int arr_right[] = new int[len_right];

		for (int i = 0; i < len_left; i++) {
			arr_left[i] = arr[left + i];
			accesoArray++;
		}
		for (int j = 0; j < len_right; j++) {
			arr_right[j] = arr[mitad + 1 + j];
			accesoArray++;
		}
		int i = 0, j = 0;
		int k = left;
		while (i < len_left && j < len_right) {
			if (arr_left[i] <= arr_right[j]) {
				arr[k] = arr_left[i];
				i++;
				accesoArray++;
			} else {
				arr[k] = arr_right[j];
				j++;
				accesoArray++;
			}
			k++;
			cambiosArray++;
			m.updateAnimacionesSinTiempo();
		}

		while (i < len_left) {
			arr[k] = arr_left[i];
			i++;
			k++;
			accesoArray++;
			m.updateAnimacionesSinTiempo();
		}

		while (j < len_right) {
			arr[k] = arr_right[j];
			j++;
			k++;
			accesoArray++;
			m.updateAnimacionesSinTiempo();
		}
		m.textos();
		setFin(System.currentTimeMillis());
	}

	@Override
	public String getNombre() {
		return "Merge Sort";
	}

}
