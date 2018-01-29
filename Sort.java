import java.util.Arrays;

public class Sort {
	static int bububu = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int arr[] = {8, 5, 7, 1, 9, 3};
//		selectionSort(arr);
//		System.out.println(Arrays.toString(arr));
		int arr2[] = {8, 5, 7, 1, 9, 3};
		shellSort(arr2);
		System.out.println(Arrays.toString(arr2));
		int[] data3 = {7,9,2,5,21,13,17,32,53,1,8,19,44,20,10,2,51};
		int[] data4 = {38, 27, 43, 3, 9, 82, 10};
		bubbleSort2(data3);
		System.out.println(bububu);
		mergeSort(data4);
	}
	
	//힙을 생성한다.
	static void buildMaxHeapify(int[] data) {
		int startIndex = getParentIndex(data.length - 1);
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(data, data.length, i);
		}
	}

	static void maxHeapify(int[] data, int heapSize, int index) {
		int left = getChildLeftIndex(index);
		int right = getChildRightIndex(index);
		int largest = index;	//현재

		//현재가 왼쪽자식보다 작을 때 largest를 바꾼다.
		if (left < heapSize && data[index] < data[left]) {
			largest = left;
		}

		//현재가 오른쪽자식보다 작을 때 largest를 바꾼다.
		if (right < heapSize && data[largest] < data[right]) {
			largest = right;
		}

		// largest 바뀌면 swap
		if (largest != index) {
			int temp = data[index];
			data[index] = data[largest];
			data[largest] = temp;
			maxHeapify(data, heapSize, largest);
		}
	}

	/**
	 * Heap sorting
	 * @param data
	 */
	static void heapSort(int[] data) {
		//뒤에서부터
		for (int i = data.length-1; i > 0; i--) {
			//현재 root를 맨 끝으로 보내기
			int temp = data[0];
			data[0] = data[i];
			data[i] = temp;
			//자리 잡기
			maxHeapify(data, i, 0);
		}
	}

	// parent index = (currentIndex-1)/2
	static int getParentIndex(int current) {
		return (current - 1) >> 1;
	}

	// leftChild = (Index*2)+1
	static int getChildLeftIndex(int current) {
		return (current << 1) + 1;
	}

	// rightChild = (Index*2)+2 = leftChild+1
	static int getChildRightIndex(int current) {
		return (current << 1) + 2;
	}
	
	
	//첫번째 값을 pivot으로
	static int count = 0;
	public static void final_quickSort(Integer[] arr) {
		final_quickSort(arr, 0, arr.length-1);
		System.out.println("Quick Sort count : "+ count );
	}
    public static void final_quickSort(Integer[] arr, int low, int high){
        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;
        int temp = arr[left];
        while (left < right){
            while(left < right && arr[right] >= temp){  
                right--;
            }
            arr[left] = arr[right];
            count ++;
            while(left < right && arr[left] <= temp){   
                left++;
            }
            arr[right] = arr[left];
            count ++;
        }
        arr[left] = temp;
        final_quickSort(arr, low, left-1);
        final_quickSort(arr, left+1, high);
    }
    
    public static void shellSort(int[] arr){
        int gap = arr.length / 2;
        for (; gap > 0; gap /= 2) {
            System.out.println("Gap=" + gap); //
            for (int j = 0; (j+gap) < arr.length; j++){
            	
                for(int k = 0; (k+gap)< arr.length; k += gap){
                	
                    System.out.println("Compare： arr[" + (k+gap)+ "]=" //
                + arr[k+gap] + ", arr[" + k + "]=" + arr[k]);
                    
                    if(arr[k] > arr[k+gap]) {
                        int temp = arr[k+gap];      
                        arr[k+gap] = arr[k];
                        arr[k] = temp;
                        System.out.println("    Sorting:  " + Arrays.toString(arr));
                    }
                }
            }
        }
    }
    
    public static int[] mergeSort(int[] arr){
        if(arr.length <= 1) return arr;

        int num = arr.length >> 1;	// 나누기 2
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        System.out.println("split two array: " + Arrays.toString(leftArr) + 
        		" And " + Arrays.toString(rightArr));
        return mergeTwoArray(mergeSort(leftArr), mergeSort(rightArr));      
    }

    private static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr1.length + arr2.length];  
        while(i < arr1.length && j < arr2.length){      
            if(arr1[i] <= arr2[j]){
                result[k++] = arr1[i++];
            }else{
                result[k++] = arr2[j++];
            }
        }
        while(i < arr1.length){     
            result[k++] = arr1[i++];
        }
        while(j < arr2.length){   
            result[k++] = arr2[j++];
        }
        System.out.println("Merging: " + Arrays.toString(result));
        return result;
    }

	static void selectionSort(int arr[]) {
		for(int fill = 0; fill<arr.length-1; fill++) {
			System.out.println(fill);
			int posMin = fill;
			for(int next = fill+1; next<arr.length; next++) {
				System.out.println("n : "+next);
				if(arr[next]<arr[posMin]) {
					posMin = next;
				}
			}
			int temp = arr[fill];
			arr[fill] = arr[posMin];
			arr[posMin] = temp;
		}
	}
	
	static void bubbleSort(int[] arr){
		for(int i=arr.length-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(arr[j]>arr[j+1])
					swap(arr, j, j+1);
			}
		}
	}	
	
	static void bubbleSort2(int[] arr){
		int top = -1;
		int bound = 0;
		do {
			top = arr.length-1;
			for(int i = top; i>bound; i--) {
				if(arr[i]<arr[i-1]) {
					bububu++;
					int temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
					top = i;
				}
			}
			bound = top;
		}
		while(top < arr.length-1);
	}



	private static void swap(int[] arr, int j, int i) {
//		bububu++;
		int temp = arr[j];
		arr[j] = arr[j+1];
		arr[j+1] = temp;
	}
}
