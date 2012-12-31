#import <stdio.h>
#import <string.h>
#import <stdlib.h>

void printString(char string[]){
	string[80] = '\n';
	printf("%.*s", strlen(string), string);
}

void emptyString(char string[]){
	memset(string, 0, strlen(string));
}

int main(void){
        char print[82] = "\0"; /* array to hold parsed string */
	char *stream = 0;  /* initial cpatured stream pointer */
	char ch;  /* individual captured characters */
	int n;  /* number of allocated character blocks after stream pointer */
	int index;  /* index into string pointer for parsing the string */
	
	/* get the initial character stream by dynamically*/
	/* allocating space after the stream pointer*/
 	do {   
       		stream = (char*) realloc (stream, n+1);
	        ch = fgetc (stdin);
         	stream[n] = ch;
         	if (ch != EOF)
             		n++;
	} while (ch != EOF);

	/* loop over cpatured stream and parse it into the print array*/
 	for (index = 0; index < n; index++){

                /*test for double-asterisk*/
                if (stream[index] == '*' & print[strlen(print)-1] == '*'){
                                print[strlen(print)-1] = '^';
		}

                /* test for carriage return */
                else if (stream[index] == '\n'){
                        print[strlen(print)] = ' ';
		}

		/* otherwise just add the character*/
                else{
                        print[strlen(print)] = (char)stream[index]; /*cast to be safe*/
		}

		/* if the print array has reached the desired length, print it */
		if (strlen(print) == 80){
			printString(print);
			emptyString(print);
		}
	}
}
