#include <stdio.h>

void main(){
	/*
 	use a buffer system to check for double-asteriks
	*/
	int c = 0; //if c is not an asterisk, just produce it
	int char_buff = 0;
	int carat = '^'; //convenience

	//close off uneeded pipe ends
	
	while((c = fgetc(stdin)) > 0) {
		//read c from pipe
		if (c == '*'){//if c is a asterisk, get another character to check
			char_buff = fgetc(stdin);
			if (char_buff == '*'){
				printf("%c", carat);  //if both are asterisks, produce carat
			}
			else{
				printf("%c", c); 	//if only one is asterisk, write both
				printf("%c", char_buff); 
			}
		}
		else{
			printf("%c", c); //if not asterisks, produce c
		}
		
		if (c == EOF || char_buff == EOF){
			break;
		}
	}
}
