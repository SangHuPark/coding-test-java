class Solution {
    public long solution(String numbers) {
        // numbers = numbers.replaceAll("zero", "0")
        //     .replaceAll("one", "1")
        //     .replaceAll("two", "2")
        //     .replaceAll("three", "3")
        //     .replaceAll("four", "4")
        //     .replaceAll("five", "5")
        //     .replaceAll("six", "6")
        //     .replaceAll("seven", "7")
        //     .replaceAll("eight", "8")
        //     .replaceAll("nine", "9");
        
        String[] numbers_arr = {"zero", "one", "two", "three", 
                                "four", "five", "six", "seven", 
                                "eight", "nine"};

        for (int i = 0; i < numbers_arr.length; i++) {
            numbers = numbers.replaceAll(numbers_arr[i], String.valueOf(i));
        }
        
        return Long.parseLong(numbers);
    }
}