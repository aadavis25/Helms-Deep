#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "st.h"
#include "semaphore.h"
#include "buffer.h"

#define INPUT stdin
#define OUTPUT stdout
#define default_size 20
#define output_size 80


//two different Thread sructs since we need two different types
//one for IO which only has 1 shared buffer and semaphore 
//one for the other processes which will have two shared buffers and semaphores
//Naming convention:
//producer = sems and buffers where Im producer
//consumer = sems and buffers where im consumer
//complex stuff
typedef struct{
	FILE *IO; //print to/read from
	semaphore *mutex; //shared semaphores
	semaphore *fullbuffs;
	semaphore *emptybuffs;
	CircularBuffer *buffer;  // the shared buffer
} IOThreadInit;

typedef struct{
	//buffers and semaphores related to the producer and consumer roles of each thread
	semaphore *consumer_sem; // pointers to the shared semaphores
	semaphore *consumer_fullbuffs;
	semaphore *consumer_emptybuffs;
	semaphore *producer_sem;
	semaphore *producer_fullbuffs;
	semaphore *producer_emptybuffs;
	CircularBuffer *consumer_buffer;  // the shared buffers
	CircularBuffer *producer_buffer;
} ThreadInit;

//function for our 4 thread functions pointers
void *read_thread_func(void *s);
void *firstproc_thread_func (void *s);
void *secondproc_thread_func (void *s);
void *output_thread_func (void *s);

/*
	methods to avoid repeated code on produces and consumes
	Drops the semaphores and calls the cbProduce on
	the int argument on the thread's producerbuffer
	Produce: 
	@args - Thread struct, integer
	@return - none
*/
void produceChar (ThreadInit *i, int c){
	down(i->producer_emptybuffs);
	down(i->producer_sem);
	//@post-condition
	//see below

	//@pre-condition
	//There must be an empty buffer and we must have the critical section
	cbProduce(i->producer_buffer, c); 
	//@post-conditin 
	//the passed in integer must be on the producer buffer
	//and the start and end must be moved appropriately
	up(i->producer_sem);
	up(i->producer_fullbuffs);
}
/*
 	Drops the semaphores and calls the cbConsume on 
	the thread's consumer buffer
	Produce: 
	@args - Thread struct
	@return - integer as ascii
*/
int consumeChar (ThreadInit *i){	
	int c;
	down(i->consumer_fullbuffs);
	down(i->consumer_sem);
	//@post-condition
	//There must be a full buffer and we must have critical section
	
	//@pre-condition
	//see above
	c = cbConsume(i->consumer_buffer);
	//@post condition
	//c must equal the sppropriate int on the sequence from the buffer
	//end must point to the next integer that will be consumed in correct sequence
	up(i->consumer_sem);
	up(i->consumer_emptybuffs);
	return c;
}
/*
4 threads are created and wired here; the first will process the input
from INPUT and place the results in a buffer shared with the first of two string
processing threads. The first of those will replace carriage returns with spaces
and the seccond will replace pairs of asterisks with carats. Both of these threads will
place the character whether replaced or not on the next shared buffer. The final thread will
count to 80 and output while keeping the passed characters in an array that will be output

Naming convention:
rd = read
fp = first processing thread
sp = second processing thread
op = output
ex rd_fp_buf = read to first processing thread buffer, the buffer between read and first process
*/
void main(void){
	st_init();
	
	//integers so I don't get confused about the buffers' zeros
	int end = 0;
	int start = 0;
	//malloced arrays for the circular buffers
	char *rd_fp_buf = malloc(default_size * sizeof(char));
	char *fp_sp_buf = malloc(default_size * sizeof(char));
	char *sp_op_buf = malloc(default_size * sizeof(char));
	//Circular buffers for use by threads
	CircularBuffer rd_fp_cirbuf = {
		default_size,
		start,
		end,
		rd_fp_buf
	};
	CircularBuffer fp_sp_cirbuf = {
		default_size,
		start,
		end,
		fp_sp_buf
	};
	CircularBuffer sp_op_cirbuf = {
		default_size,
		start,
		end,
		sp_op_buf
	};
	
	//woo semaphores! three for each pair of threads
	semaphore rd_fp_sem;
	semaphore fp_sp_sem;
	semaphore sp_op_sem;
	//initialize 1 to make sure we can get in the first time!
	createSem(&rd_fp_sem,1);
	createSem(&fp_sp_sem,1);
	createSem(&sp_op_sem,1);

	semaphore rd_fp_fullbuffs;
	semaphore fp_sp_fullbuffs;
	semaphore sp_op_fullbuffs;
	createSem(&rd_fp_fullbuffs, 0);
	createSem(&fp_sp_fullbuffs, 0);
	createSem(&sp_op_fullbuffs, 0);
	
	semaphore rd_fp_emptybuffs;
	semaphore fp_sp_emptybuffs;
	semaphore sp_op_emptybuffs;
	//must initialize the full buffs semaphore to the correct value
	createSem(&rd_fp_emptybuffs, default_size);
	createSem(&fp_sp_emptybuffs, default_size);
	createSem(&sp_op_emptybuffs, default_size);

	//thread initiators for the various threads
	//This instantion goes on forever...
	IOThreadInit rd_thread_init = {
		//read source
		INPUT,
		//semaphores
		&rd_fp_sem,
		&rd_fp_fullbuffs,
		&rd_fp_emptybuffs,
		//circular buffer
		&rd_fp_cirbuf
	};
	ThreadInit fp_thread_init = {
		//semaphores
		&rd_fp_sem,
		&rd_fp_fullbuffs,
		&rd_fp_emptybuffs,
		&fp_sp_sem,
		&fp_sp_fullbuffs,
		&fp_sp_emptybuffs,
		//circular buffers
		&rd_fp_cirbuf,
		&fp_sp_cirbuf
	};
	ThreadInit sp_thread_init = {
		//semaphores
		&fp_sp_sem,
		&fp_sp_fullbuffs,
		&fp_sp_emptybuffs,
		&sp_op_sem,
		&sp_op_fullbuffs,
		&sp_op_emptybuffs,
		//circular buffers
		&fp_sp_cirbuf,
		&sp_op_cirbuf
	};
	IOThreadInit op_thread_init = {
		//output destination
		OUTPUT,
		//semaphores
		&sp_op_sem,
		&sp_op_fullbuffs,
		&sp_op_emptybuffs,
		//circular buffer
		&sp_op_cirbuf
	};
	
	//create the threads, finally!
	if (st_thread_create(read_thread_func, &rd_thread_init, 0, 0) == NULL){
		perror("Error creating read thread");
		exit(1);
	}
	if (st_thread_create(firstproc_thread_func, &fp_thread_init, 0, 0) == NULL){
		perror("Error creating first process thread");
		exit(1);
	}
	if (st_thread_create(secondproc_thread_func, &sp_thread_init, 0, 0) == NULL){
		perror("Error creating second process thread");
		exit(1);
	}
	if (st_thread_create(output_thread_func, &op_thread_init, 0, 0) == NULL){
		perror("Error creating output thread");
		exit(1);
	}
	
	st_thread_exit(NULL);
}
//**See documentation above for produces and consumes**
//
//Define these functions
//read function: takess in raw characters and turns them into ascii code which it passes
//to the first-processing thread. Ends at EOF asfter passing it along
//
//Observer 
void *read_thread_func (void *s){
	IOThreadInit *i = s; //keep the compiler from whining about void stuf
	int c;
	
	while (1){
		//we can assert that there's a empty buffer for two reasons
		//first, this is the only thread that produces to it so no other one can touch it
		//second, if the empty buffers semaphore here passes its down, it means the first processing
		//thread has run empty buffers up so at least one caracter has to have been consumed

		c = fgetc(INPUT);
		down(i->emptybuffs);
			down(i->mutex);
				cbProduce(i->buffer, c);
			up(i->mutex);
		up(i->fullbuffs);
		if (c == EOF)
			break;
		 //by ending at EOF, we will pass the final WOF character which we'll use to end the program
		 //This also helps assert when the threads should end because we can say that after EOF has 
		 //passed through the previous thread, we know there's no more to be processed
	}
	st_thread_exit(NULL);
}

//the first of the two processing functions: takes in a character in ascii and processes whether the character is a newline
//and if it is, it will pass along a space otherwise it passes the character. Ends on EOF after passing it along
//
//Modifier
void *firstproc_thread_func (void *s){
	ThreadInit *i = s;
	int c = 0;
	int space = 32;
	while(1) {
		//assert there must be at least one full buff if full buffers down passes
		//so read has run at least once and called full buffers up
		c = consumeChar(i);
		//do our processing, pass the space if we meet a newline
		if (c == '\n')
			produceChar(i, space);
		
		//we can assert that we know the buffer isn't full
		//by similar logic as the above. This is the only thread that
		//writes to it and if empty buffers passes, second process thread function has run
		else
			produceChar(i ,c);
		if (c == EOF)
			break;
	}
	st_thread_exit(NULL);
}

//second processor function: takes in a character and checks if it's an asterisk. If not, it just passes the character 
//If so, pulls in another character and check if it's another
//asterisk. If so, it will pass along a carat symbol, if not it just passes both characters. Ends at EOF after passing
//
//Modifier
void *secondproc_thread_func(void *s){
	ThreadInit *i = s;
	/*
 	use a buffer system to check for double-asteriks
	*/
	int c = 0; //if c is not an asterisk, just produce it
	int char_buff = 0;
	int carat = '^'; //convenience
	while(1) {
		//assert there must be at least one full buff if full buffers down passes
		//so read has run at least once and called full buffers up
		c = consumeChar(i);

		if (c == '*'){//if c is a asterisk, get another character to check
			char_buff = consumeChar(i);
			if (char_buff == '*'){
				//we can assert that we know the buffer isn't full
				//by similar logic as the above. This is the only thread that
				//writes to it and if empty buffers passes, output thread function has run
				
				produceChar (i, carat); //if both are asterisks, produce carat
			}
			else{
				produceChar (i, c);		//if only one is asterisk, produce both
				produceChar (i, char_buff);
			}
		}
		else
			produceChar (i, c); //if not asterisks, produce c
		
		if (c == EOF || char_buff == EOF)
			break;
	}
	st_thread_exit(NULL);
}

//The output thread: Takes in characters and jams them in an array to wait. Once the thread reaches 80 characters, it outputs the array.
//Exciting.
//Ends at EOF; doesn't output it because it's a weird lookin character.
//
//Observer, kinda
void *output_thread_func (void *s){
	IOThreadInit *i = s;
	//80 char array to be output
	char output[81];
	int c = 0;
	//printf("%c",'\n');
	while(1) {
		//code saving functions dont take IOThreadInit :/
		//has to be written explicitly
		down(i->fullbuffs);
		down(i->mutex);
		c = cbConsume(i->buffer);
		up(i->mutex);
		up(i->emptybuffs);
		output[strlen(output)] = c;
		if (strlen(output) == output_size){
			printf("%s", output);
			printf("%c", '\n');
			memset(output, '\0', strlen(output) * sizeof(char));
		}
		if (c == EOF)
			break;
	}
	st_thread_exit(NULL);
}
