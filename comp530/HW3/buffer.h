

typedef struct{
        const int size; //max size
        int start;
        int end;
        char *elms;
} CircularBuffer;

void cbProduce(CircularBuffer*, int);


int cbConsume(CircularBuffer*);
