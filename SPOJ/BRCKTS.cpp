#include <iostream>
using namespace std;
int tree[1000001][2];

void buildTree(string str , int s , int e , int index){
    if(s==e){
        tree[index][0] = str[s]==')';
        tree[index][1] = str[s]=='('; 
        return;
    }

    int mid = s + (e-s)/2;
    buildTree(str,s,mid,2*index);
    buildTree(str,mid+1,e,2*index+1);
    int minValue = min(tree[2*index][1],tree[2*index+1][0]);
    tree[index][0] = tree[2*index][0] + tree[2*index+1][0] - minValue;
    tree[index][1] = tree[2*index][1] + tree[2*index+1][1] - minValue;
}

void update(string &str , int l , int r , int index , int idx){
    if(l==r){
        if(str[idx]==')'){
            str[idx] = '('; 
        }else{
            str[idx] = ')';
        }
        tree[index][0] = str[idx]==')';
        tree[index][1] = str[idx]=='(';
        return; 
    }

    int mid = l + (r-l)/2;
    if(idx<=mid){
        update(str,l,mid,2*index,idx);
    }
    else{
        update(str,mid+1,r,2*index+1,idx);
    }

    int minValue = min(tree[2*index][1],tree[2*index+1][0]);
    tree[index][0] = tree[2*index][0] + tree[2*index+1][0] - minValue;
    tree[index][1] = tree[2*index][1] + tree[2*index+1][1] - minValue;
   // cout<<index<<" "<<tree[index][0]<<" "<<tree[index][1]<<endl;

}

int main() {
    int t = 10;
    int test = 1;
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    while(t--){
        int n;
        cin>>n;
        string str;
        cin>>str;
        cout<<"Test "<<test++<<":"<<"\n";
        buildTree(str,0,n-1,1);
        int q;
        cin>>q;
        while(q--){
            int k;
            cin>>k;
            if(k==0){
                if(tree[1][0]==tree[1][1] && tree[1][0]==0){
                    cout<<"YES"<<"\n";
                }else{
                    cout<<"NO"<<"\n";
                }
            }
            else{
                update(str,0,n-1,1,k-1);
            }
        }
    }
}
