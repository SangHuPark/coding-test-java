class Solution {
    public int solution(int[] box, int n) {
        int answer = (int) (Math.floor(box[0] / n) * Math.floor(box[1] / n)
            * Math.floor(box[2] / n));
        return answer;
    }
}