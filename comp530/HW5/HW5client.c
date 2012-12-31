#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#define MAX_LINE 1024

#include "demo/Socket.h"

int main (int argc, char* argv[]){
	int i;
	int ch;
	int returnCh;
	char string[MAX_LINE] = {'\0'};
	Socket connect;

	if (argc < 3){
		printf("Need port and host\n");
		return (-1);
	}
	
	connect = Socket_new(argv[1], atoi(argv[2]));
	if(connect < 0){
		printf("connection failed\n");
		return (-1);
	}
	
	while(1){
		printf("-->");
		while(1){
			ch = getc(stdin);
			if (ch == EOF){
				Socket_close(connect);
				return;
			}
			returnCh = Socket_putc(ch, connect);
			if (returnCh < 0){
				printf("EOF on put or server error\n");
				Socket_close(connect);
				exit(-1);
			}
			if(ch == '\n')
				break;
		}
		
		while(1){
			ch = Socket_getc(connect);
			if (ch == EOF){
				printf("EOF on get or server error\n");
				Socket_close(connect);
				exit(-1);
			} else if (ch == '\0') {
					string[strlen(string)] = '\n';
					while(1){
						ch = Socket_getc(connect);
						//start handling response
						string[strlen(string)] = ch;
						if (ch == '\0')
							break;
					}
					break;
			} else {
				string[strlen(string)] = ch;
			}

		}

		printf("%s", string);
		memset(string, '\0', strlen(string));
		
	}
	printf("%c", '\n');
	Socket_close(connect);
	exit(0);
}
