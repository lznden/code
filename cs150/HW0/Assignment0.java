public class Assignment0 {
    public static void main(String[] args) {
    int[] arr = {4, 4, 4, 1, 1, 2};

    int[][] compressed = compressRuns(arr);
    System.out.println("Compressed:");
        for (int[] compressed1 : compressed) {
            System.out.println("{" + compressed1[0] + ", " + compressed1[1] + "}");
        }

    int[] decompressed = decompressRuns(compressed);
    System.out.println("\nDecompressed:");
    for (int i = 0; i < decompressed.length; i++) {
        System.out.print(decompressed[i] + " ");
    }
    }

    public static int[][] compressRuns(int[] arr) {
    if (arr == null || arr.length == 0) {
        return new int[0][2]; // empty result
    }

    // First pass: count how many runs there are
    int runs = 1; // at least one run if array is not empty
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] != arr[i - 1]) {
            runs++;
        }
    }

    // Second pass: fill the result array
    int[][] result = new int[runs][2];

    int currentValue = arr[0];
    int currentCount = 1;
    int resultIndex = 0;

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] == currentValue) {
            currentCount++;
        } else {
            // store the completed run
            result[resultIndex][0] = currentValue;
            result[resultIndex][1] = currentCount;
            resultIndex++;

            // start a new run
            currentValue = arr[i];
            currentCount = 1;
        }
    }

    // store the final run
    result[resultIndex][0] = currentValue;
    result[resultIndex][1] = currentCount;

    return result;
}


    public static int[] decompressRuns(int[][] compressedArr) {
    if (compressedArr == null || compressedArr.length == 0) {
        return new int[0]; // empty result
    }

    int totalLength = 0;
        for (int[] compressedArr1 : compressedArr) {
            totalLength += compressedArr1[1]; // add the count
        }
    int[] result = new int[totalLength];
    int index = 0;

        for (int[] compressedArr1 : compressedArr) {
            int value = compressedArr1[0];
            int count = compressedArr1[1];
            for (int j = 0; j < count; j++) {
                result[index] = value;
                index++;
            }
        }

    return result;
}
}