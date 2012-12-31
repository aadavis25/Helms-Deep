#include <stdio.h>
#include<string.h>
#define OUTPUT 80

void main(){
	//80 char array to be output
	char output[81] = {'\0'};
	int c = 0;
	//close unneeded pipe end

	while((c = fgetc(stdin)) > 0) {
		//read c from pipe
		output[strlen(output)] = c;
		//if 80 chars, output
		if (strlen(output) == OUTPUT){
			printf("%s", output);
			printf("%c", '\n');
			memset(output, '\0', strlen(output) * sizeof(char));
		}
		if (c == EOF){
			//when EOF, close up the pipe and go home
			break;
		}
	
	}
}
