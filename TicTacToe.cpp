#include <iostream>
using namespace std;

char board[3][3]={{'1','2','3'},{'4','5','6'},{'7','8','9'}};
char turn='X';
int choice; 
bool draw=false;

void display(){
    cout<<"Tic Tac Toe\n";
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            cout<<board[i][j]<<" ";
        }
        cout<<"\n";
    }
}

bool win(){
    for(int i=0;i<3;i++) if(board[i][0]==board[i][1]&&board[i][1]==board[i][2]) return true;
    for(int i=0;i<3;i++) if(board[0][i]==board[1][i]&&board[1][i]==board[2][i]) return true;
    if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]) return true;
    if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]) return true;
    return false;
}

bool filled(){
    for(int i=0;i<3;i++) for(int j=0;j<3;j++) if(board[i][j]!='X'&&board[i][j]!='O') return false;
    return true;
}

int main(){
    while(true){
        display();
        cout<<"Player "<<turn<<" turn: ";
        cin>>choice;
        int row=(choice-1)/3, col=(choice-1)%3;
        if(board[row][col]=='X'||board[row][col]=='O') continue;
        board[row][col]=turn;
        if(win()){display();cout<<"Player "<<turn<<" wins!\n";break;}
        if(filled()){display();cout<<"Draw!\n";break;}
        turn=(turn=='X')?'O':'X';
    }
}
