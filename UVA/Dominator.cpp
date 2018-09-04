#include <iostream>
using namespace std;
void dfs(int src , bool *visited , int V , int **edgeList , bool **dominator){
    visited[src] = true;
    for(int row = 0; row<V;row++){
        dominator[row][src] = true;
    }
    for(int i=0;i<V;i++){
        if(edgeList[src][i] && !visited[i] ){
            dfs(i,visited,V,edgeList,dominator);
        }
    }
}

void dominatorDFS(int src , int X , bool *visited , int V , int **edgeList , bool **dominator){
    if(src==X){
        return;
    }
    dominator[X][src] = false;
    visited[src] = true;
    for(int i=0;i<V;i++){
        if(edgeList[src][i] && !visited[i] ){
            dominatorDFS(i,X,visited,V,edgeList,dominator);
        }
    }
}

int main() {
    int t;
    scanf("%d",&t);
    int test = 1;
    while(t--){
        int V;
        scanf("%d",&V);
        int **edgeList = new int*[V];
        for(int i=0;i<V;i++){
            edgeList[i] = new int[V];
            for(int j=0;j<V;j++){
                scanf("%d",&edgeList[i][j]);
            }
        }
        bool ** dominator = new bool*[V];
        for(int i=0;i<V;i++){
            dominator[i] = new bool[V];
            for(int j=0;j<V;j++){
                dominator[i][j] = false;
            }
        }

        bool * visited = new bool[V];
        for(int i=0;i<V;i++){
            visited[i] = false;
        }
        dfs(0,visited,V,edgeList,dominator);

        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                visited[j] = false;
            }
            dominatorDFS(0,i,visited,V,edgeList,dominator);
        }
        printf("Case %d:\n",test++);
        for(int i=0;i<V;i++){
            printf("+");
            for(int j=0;j<2*V-1;j++){
                printf("-");
            }
            printf("+\n");
             printf("|");
            for(int j=0;j<V;j++){
               if(dominator[i][j]){
                   printf("Y|");
               }else{
                   printf("N|");
               }
            }
            printf("\n");
        }
        printf("+");
            for(int j=0;j<2*V-1;j++){
                printf("-");
            }
        printf("+\n");
    }
}


/*
Input:
2
5
0 1 1 0 0
0 0 0 1 0
0 0 0 1 0
0 0 0 0 0
0 0 0 1 0
1
1

Output:

Case 1:
+---------+
|Y|Y|Y|Y|N|
+---------+
|N|Y|N|N|N|
+---------+
|N|N|Y|N|N|
+---------+
|N|N|N|Y|N|
+---------+
|N|N|N|N|N|
+---------+
Case 2:
+-+
|Y|
+-+

*/