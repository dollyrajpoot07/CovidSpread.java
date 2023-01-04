// Given a matrix arr[][], consisting of only 0, 1, and 2, that represents an empty ward, 
// an uninfected patient, and an infected patient respectively. In one unit of time, an 
// infected person at index (i, j) can infect an uninfected person adjacent to it i.e., 
// at index (i – 1, j), (i + 1, j), (i, j – 1), and (i, j + 1). The task is to find the 
// minimum amount of time required to infect all the patients. If it is impossible to infect 
// all the patients, then print “-1”.

import java.util.*;

class CovidSpread {
    static class pair {
        int first, second, third;
        pair(int first,int second,int third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }
    }
    static int[][] direction = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
    static int maximumTime(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] timeofinfection = new int[n][m];
        Queue<pair> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                timeofinfection[i][j] = -1;
                if (arr[i][j] == 2) {
                    q.add(new pair(i, j, 0));
                    timeofinfection[i][j] = 0;
                }
		    }
	    }
	    while (!q.isEmpty()) {
		    pair current = q.peek();
		    q.poll();

		    for(int[] it : direction) {
			    int i = current.first + it[0];
			    int j = current.second + it[1];
			    if (i < 0 || j < 0 || i >= n || j >= m || arr[i][j] != 1 ||
			    timeofinfection[i][j] != -1) {
				    continue;
			    }
			    q.add(new pair(i, j , current.second + 1 ));
			    timeofinfection[i][j] = current.third + 1;
		    }
	    }
	    int maxi = Integer.MIN_VALUE;

	    int flag = 0;
	    for(int i = 0; i < n; i++) {
		    for(int j = 0; j < m; j++) {
			    if (arr[i][j] != 1)
				    continue;
			    if (arr[i][j] == 1 && timeofinfection[i][j] == -1) {
				    flag = 1;
				    break;
			    }
			    maxi = Math.max(maxi, timeofinfection[i][j]);
		    }
	    }
	    if (flag == 1)
		    return -1;
	    return maxi;
    }

    public static void main(String[] args) {
        int[][] arr = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
	    System.out.print(maximumTime(arr));
    }
}

