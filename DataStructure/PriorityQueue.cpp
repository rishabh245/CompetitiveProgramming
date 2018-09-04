// Min Priority Queue

#include <iostream>
#define MAX 1000010
class priority_queue{
    private :
        int * heap;
        int size;
    
    public:

        priority_queue(){
            heap = new int[MAX];
            size = 0;
        }

        public bool empty(){
            return size==0;
        }

        public int size(){
            return size;
        }

        public int getMin(){
            if(size>0){
                return heap[0];
            }
            return NULL;
        }

        public void insert(int element){
            heap[size] = element;
            size++;

            //Up-Heapify

            int childIndex = size-1;
            int parentIndex = (childIndex-1)/2;

            while(childIndex>0){
                if(heap[parentIndex] > heap[childIndex]){
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[childIndex];
                    heap[childIndex] = temp;
                 }else{
                     return;
                 }
                 childIndex = parentIndex;
                 parentIndex = (childIndex-1)/2;
            }
        }

        public void removeMin(){
            if(size==0){
                return;
            }
            heap[0] = heap[size-1];
            size--;

            // Down Heapify
            int parentIndex = 0;
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = 2 * parentIndex + 2;
            
        }

}


using namespace std;
int main() {
   
}
