package DSA_pactice;

public class Sort012 {

	public static void main(String args[]) {
		int arr[] = new int[] { 0, 2, 0, 2, 0, 1, 2 };
		sort012(arr);
		for (int temp : arr)
			System.out.print(temp + " ");
	}

	public static void sort012(int[] arr) {
		// work for 1s
		int i = 0, j;
		while (i < arr.length && arr[i] == 0)
			i++;
		j = i + 1;
		while (j < arr.length) {
			if (arr[j] == 0) {
				arr[j] = arr[i];
				arr[i] = 0;
				i++;
				while (i < arr.length && arr[i] == 0)
					i++;
			}
			j++;
		}

		// work for 2s
		i = arr.length - 1;
		while (i >= 0 && arr[i] == 2)
			i--;
		j = i - 1;
		while (j >= 0) {
			if (arr[j] == 2) {
				arr[j] = arr[i];
				arr[i] = 2;
				i--;
				while (i >= 0 && arr[i] == 2)
					i--;
			}
			j--;
		}
	}
}
