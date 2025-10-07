class Solution {
public:
    string getPermutation(int n, int k) {
        vector<int> numbers; //to store all the numers from 1->n
        int fact = 1; //factorial calculation

        for(int i=1; i<n; i++){
            fact = fact * i;
            numbers.push_back(i);
        }
        numbers.push_back(n);
        string ans = "";

        //to align with 0 index based
        k = k-1;

        while(1){
            // to select the fixed ith index
            ans = ans + to_string(numbers[k/fact]);

            //delete the chosen number
            numbers.erase(numbers.begin() + k/fact);

            //break the loop if nothing is left in numbers
            if(numbers.size()==0){
                break;
            }

            //to get next iteration k, fact
            k = k%fact;
            fact= fact / numbers.size();
        }

        return ans;
    }
};