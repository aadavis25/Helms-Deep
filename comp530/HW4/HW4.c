#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define INPUT stdin
//number of characters in lines to be output
#define output_size 80
//read and write globals so I dont use the wrong ones!
#define READ 0
#define WRITE 1
//size of int global for the pipe writes and reads
#define INT sizeof(int)

//global pipe vars
int pipe_rd_fp[2];
int pipe_fp_sp[2];
int pipe_sp_op[2];


//read function: takess in raw characters and turns them into ascii code which it passes
//to the first-processing thread. Ends at EOF asfter passing it along
//
//Observer 
void read_func (){
	int c = 0;
	//don't need the read end
	close(pipe_rd_fp[READ]);
	while (1){
		c = fgetc(INPUT);
		write(pipe_rd_fp[WRITE], &c, INT);
		if (c == EOF){
			//pass EOF by closing off the pipe
			close(pipe_rd_fp[WRITE]);
			break;
		}
	}

}

//the first of the two processing functions: takes in a character in ascii and processes whether the character is a newline
//and if it is, it will pass along a space otherwise it passes the character. Ends on EOF after passing it along
//
//Modifier
void firstproc_func (){
	int c = 0;
	int space = 32;
	//close unused pipe ends
	close(pipe_fp_sp[READ]);
	close(pipe_rd_fp[WRITE]);
	while(1) {
		read(pipe_rd_fp[READ], &c, INT);
		//do our processing, pass the space if we meet a newline
		if (c == '\n')
			write(pipe_fp_sp[WRITE], &space, INT);
		else
			write(pipe_fp_sp[WRITE], &c, INT);
		if (c == EOF){
			close(pipe_fp_sp[WRITE]);//pass EOF by closing pipe
			close(pipe_rd_fp[READ]);
			break;	
		}
	}
}

//second processor function: takes in a character and checks if it's an asterisk. If not, it just passes the character 
//If so, pulls in another character and check if it's another
//asterisk. If so, it will pass along a carat symbol, if not it just passes both characters. Ends at EOF after passing
//
//Modifier
void secondproc_func(){
	/*
 	use a buffer system to check for double-asteriks
	*/
	int c = 0; //if c is not an asterisk, just produce it
	int char_buff = 0;
	int carat = '^'; //convenience

	//close off uneeded pipe ends
	close(pipe_fp_sp[WRITE]);
	close(pipe_sp_op[READ]);
	
	while(1) {
		//read c from pipe
		read(pipe_fp_sp[READ], &c, INT);
		if (c == '*'){//if c is a asterisk, get another character to check
			read(pipe_fp_sp[READ], &char_buff, INT);
			if (char_buff == '*'){
				write(pipe_sp_op[WRITE], &carat, INT); //if both are asterisks, produce carat
			}
			else{
				write(pipe_sp_op[WRITE], &c, INT);		//if only one is asterisk, write both
				write(pipe_sp_op[WRITE], &char_buff, INT);
			}
		}
		else{
			write(pipe_sp_op[WRITE], &c, INT); //if not asterisks, produce c
		}
		
		if (c == EOF || char_buff == EOF){
			//close pipe, pass EOF
			close(pipe_sp_op[WRITE]);
			close(pipe_fp_sp[READ]);
			break;
		}
	}
}

//The output thread: Takes in characters and jams them in an array to wait. Once the thread reaches 80 characters, it outputs the array.
//Exciting.
//Ends at EOF; doesn't output it because it's a weird lookin character.
//
//Observer, kinda
void output_func(){
	//80 char array to be output
	char output[81] = {'\0'};
	int c = 0;
	//close unneeded pipe end
	close(pipe_sp_op[WRITE]);
	while(1) {
		//read c from pipe
		read(pipe_sp_op[READ], &c, INT);
		output[strlen(output)] = c;
		//if 80 chars, output
		if (strlen(output) == output_size){
			printf("%s", output);
			printf("%c", '\n');
			memset(output, '\0', strlen(output) * sizeof(char));
		}
		if (c == EOF){
			//when EOF, close up the pipe and go home
			close(pipe_sp_op[READ]);
			break;
		}
	
	}
}

int main(void){
	pipe(pipe_rd_fp);
	pipe(pipe_fp_sp);
	pipe(pipe_sp_op);

	pid_t read = fork();
	pid_t first;
	pid_t second;
	pid_t output;
	
	//here main will fork the first processing process. Control will then flow to check if pid is 0 (if it is the first processing process)
	//if so, it will proceed to run the function associated with that pid. If not, main will go on and fork the next process which will behave in
	//a similar way. This happend until the output function is run, then the parent, main, will wait.

	if (read == 0){
		read_func();	
		exit(0);
	}
	else if (read == -1){
		perror("fork error");
		exit(1);
	}
	else
		first = fork();
	if (first == 0){
		firstproc_func();
		exit(0);
	}
	else if (first == -1){
		perror ("fork error");
		exit (1);
	}
	else
		second = fork();
	if (second == 0){
		secondproc_func();
		exit(0);
	}
	else if (second == -1){
		perror ("fork error");
		exit(1);
	}
	else 
		output = fork();
	if (output == 0){
		output_func();
		exit(0);
	}
	else if(output == -1){
		perror("fork error");
		exit(1);
	}
	else {
		wait(NULL);
		exit(0);
	}
}


