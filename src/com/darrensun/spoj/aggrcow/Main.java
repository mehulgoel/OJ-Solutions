import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//The time complexity of the resulting algorithm is O(n*logr) where r is the range of positions.
public class Main {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) throws IOException 
    {
        new Main().run();
    }

    void run() throws IOException 
    {
        int testcases = Integer.parseInt(in.readLine());
        while (testcases-- > 0)
            solve();
        out.flush();
    }

    void solve() 
    {
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int stalls = Integer.parseInt(tokenizer.nextToken());
        int cows = Integer.parseInt(tokenizer.nextToken());
        int[] stallPositions = new int[stalls];
        for (int i = 0; i < stalls; i++)
            stallPositions[i] = Integer.parseInt(in.readLine());
        Arrays.sort(stallPositions);
        out.println(binarySearch(cows, stallPositions));
    }
    
    int binarySearch(int cows, int[] positions) 
    {
        int left = 0, right = positions[positions.length-1]-positions[0];
        while (left <= right) 
        {
            int middle = (left + right) / 2;
            if (validMinDistance(middle, cows, positions))
                left = middle + 1;
            else
                right = middle - 1;
        }
        return right;
    }
    boolean validMinDistance(int distance, int cows, int[] positions) 
    {
        int cowPlaced = 1;
        int lastPosition = positions[0];
        for (int i = 1; i < positions.length; i++) 
        {
            if (positions[i]-lastPosition < distance)
                continue;
            if (++cowPlaced == cows)
                return true;
            lastPosition = positions[i];
        }
        return false;
    }
}
