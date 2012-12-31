#include <stdio.h>

void main(){
	int c = 0;
	int space = 32;


	while((c = fgetc(stdin)) > 0) {	
		if (c == '\n')
			printf("%c", space);
		else
			printf("%c", c);
}
}
