#include <stdio.h>

void main (){
	int c = 0;
	while (	(c = fgetc(stdin)) > 0){
		printf("%c", c);
	}
}
