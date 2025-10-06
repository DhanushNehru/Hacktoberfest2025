/*  
this is best solution using only TC => O(k) and SC => O(N) 
k is uninque elements

first check if size unequal then return false 
then iterate through indexes of string 
here check if currrent element in str1 exist or not 
if not exist update it 
if exist then check whether value of it is eual to str2 current element if not then return false

at end return true


 */

#include <iostream>
#include<string>
#include<map>
#include<set>
#include<algorithm>
#include<stack>


using namespace std;




bool check_two_morphically(string str1 , string str2){
    unordered_map <char, char> record; 
    if (str1.size() != str2.size()) return false; 
    for (int i = 0 ; i < str1.size() ; i++){
            if (record.find(str1[i]) == record.end()) 
                record[str1[i]] = str2[i] ; 
            else 
                if (record[str1[i]] != str2[i]) return false;
    }
    return true;
}

 int main(){

    string  str1 = "abbcded" , str2 = "dcchefe" ;
    cout << check_two_morphically(str1, str2) << endl;
    return 0;
}

 
