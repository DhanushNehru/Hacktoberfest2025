#include <algorithm>
#include <cstdint>
#include <unordered_set>
#include <vector>
#include <iostream>

using namespace std;

#include <algorithm>
#include <unordered_set>
#include <vector>

using namespace std;

int maximumpoints(vector<vector<int>> arr, int n, int x, int y) {
    // Initialize a set to store the points that are covered by the rectangle
    unordered_set<pair<int, int>> covered_points;

    // Initialize a counter to store the number of covered points
    int count = 0;

    // Iterate over the points in the 2D array
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            // Check if the current point is covered by the rectangle
            if (arr[i][j] == 1 && (i == 0 || i == n - 1 || j == 0 || j == n - 1 || (i > 0 && i < x && j > 0 && j < y))) {
                // If the point is covered, add it to the set and increment the counter
                covered_points.insert({ i, j });
                count++;
            }
        }
    }

    // Initialize variables to store the maximum number of covered points
    int max_count = count;

    // Iterate over the points in the set
    for (auto point : covered_points) {
        // Initialize a counter to store the number of covered points for the current rectangle
        count = 0;

        // Iterate over the points in the 2D array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the current point is covered by the rectangle
                if (arr[i][j] == 1 && (i == point.first || i == point.first + x - 1 || j == point.second || j == point.second + y - 1)) {
                    // If the point is covered, increment the counter
                    count++;
                }
            }
        }
        max_count = max(max_count, count);
    }
    return max_count;
}


/*
    Test case:
    N = 5
    Points = {(1,1), (2,3), (3,4), (2,4), (5,5)}
    x = 2, y = 2
*/
int main() {
    int n = 5;
    vector<vector<int> > arr(n);
    arr = { {1, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1} };
    int x = 2, y = 2;

    cout << maximumpoints(arr, n, x, y) << endl;
}
