import java.util.*;

class Solution {
    public int solution(int[][] dots) {
        for(int dot1 = 0; dot1 < 4; dot1++) {
            int row1 = dots[dot1][0];
            int col1 = dots[dot1][1];
            for(int dot2 = 0; dot2 < 4; dot2++) {
                if(dot1 == dot2) continue;
                
                int row2 = dots[dot2][0];
                int col2 = dots[dot2][1];
                
                double degree1 = (double) (col2 - col1) / (row2 - row1);
                
                for(int dot3 = 0; dot3 < 4; dot3++) {
                    if(dot3 == dot1 || dot3 == dot2) continue;
                    
                    int row3 = dots[dot3][0];
                    int col3 = dots[dot3][1];
                    
                    for(int dot4 = 0; dot4 < 4; dot4++) {
                        if(dot4 == dot1 || dot4 == dot2 || dot4 == dot3) continue;
                        
                        int row4 = dots[dot4][0];
                        int col4 = dots[dot4][1];
                        
                        double degree2 = (double) (col4 - col3) / (row4 - row3);
                        
                        if(degree1 == degree2) {
                            System.out.printf("dot1: (%d, %d) dot2: (%d, %d)\n", row1, col1, row2, col2);
                            System.out.printf("dot3: (%d, %d) dot4: (%d, %d)", row3, col3, row4, col4);
                            return 1;
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}