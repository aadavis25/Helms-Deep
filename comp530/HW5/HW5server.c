#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include "demo/Socket.h"

#define MAX_ARGS 256
#define MAX_TMP 100
#define MAX_LINE 1024

ServerSocket welcome;
Socket connect;

void parse_execute_service(); //pointers to functionss
void execute(char **args, char *command_line);
void parsecmd(char *cmdline, char **args, char delim, char end);

int main (int argc, char* argv[]){
	pid_t child, end;
	int child_status;

	
	if(argc < 2){
		printf("Port not specified\n");
		return (-1);
	}
	
	welcome = ServerSocket_new(atoi(argv[1]));
	if (welcome < 0){
		printf("Welcome server ceation error\n");
		return (-1);
	}
	
	while(1){
		connect = ServerSocket_accept(welcome);
		if (connect < 0){
			printf("Failed accepting new connection. Aborting.\n");
			exit(-1);
		}
		child = fork();
		if(child == -1){
			printf("Error creating new child to service request\n");
			exit(-1);
		}
		if(child == 0){
			parse_execute_service();
			Socket_close(connect);
			exit(0);
		} else {
			Socket_close(connect);
			end = waitpid(-1, &child_status, WNOHANG); //avoid waiting for child
		}
		
	}
}


void parse_execute_service(){
	char *args[MAX_ARGS];
	char *cmdline;
	int ch;
	int id;
	int i = 0;
	char string[MAX_LINE];
	pid_t child, term;
	int child_status;
	char tmp_name[MAX_TMP];
  	char id_str[MAX_TMP];
  	FILE *tempF;
  	FILE *f;
	FILE *f2;
  	char response[MAX_LINE];
  	int count;
	
	Socket_close(welcome);
	id = (int) getpid();
	sprintf(id_str, "%d", id);
 	strcpy(tmp_name,"tmp");
 	strcat(tmp_name, id_str);

	while(1){	
		child = fork();
		if (child == -1){
			perror("fork");
			exit(-1);
		}
		if (child == 0){
			while(1){
				ch = Socket_getc(connect);
				
				if (ch == EOF){
					printf("EOF on get or error\n");
					remove(tmp_name);
					return;
				} else {
					string[i] = ch;
					i++;
				}
				if (ch == '\n'){
					string[i] = '\0';
					break;
				}
			}
			cmdline = string;
			f = freopen(tmp_name, "w", stdout); 
			f2 = freopen(tmp_name, "w", stderr);
			execute(args, cmdline);
			
		} else {
			int c;
			int returnC;
			
			term = waitpid(child, &child_status, 0);
			if (WIFEXITED(child_status)) 
	    			sprintf(response, "%d exited, status %d\n\0", child, WEXITSTATUS(child_status));
	       		else
     				sprintf(response, "%d error, did not exit\n\0", child);
         	
     		
			if (term == -1) 
          		perror("waitpid"); 
          	else {
          		tempF = fopen (tmp_name, "r");
          		if (tempF < 0){
					printf ("error opening tmp file\n");
					exit(-1);
				}
				
				while (1){ 
					c = getc(tempF);
  					if (c == EOF){
  						returnC = Socket_putc('\0', connect);
  						count = 0;
  						while(c != '\0'){
  							c = response[count];
  							returnC = Socket_putc(c, connect);
  							count++;
  						}
  						break;
  					} else {
  						returnC = Socket_putc(c, connect);
  					}
  				}
 			}
        		
		}
		printf("%s", response);
	}
	
    exit(0);
	
}

void execute (char **args, char *command_line){
        pid_t pid;
        int status;		
        int i=0;
		struct stat buff;

		parsecmd(command_line, args, ' ', '\n');

		/*check for path entered instead of command*/
		if(stat(*args,&buff)>=0){
			execv(*args,args);
        }
		
		/*get path variable, start checking paths*/
		char *envorig;
		envorig = getenv("PATH");
		char *env = malloc(strlen(envorig)*sizeof(char));
		strcpy(env, envorig);
		env = strcat(env,":./");
		char *paths[256];
		
		parsecmd(env, paths, ':', '\0');
		while (paths[i] != '\0'){
			/*create a copy of paths[i] so strcat doesnt break it*/
			/*then append /'filename' to the end*/
			char *temp = malloc(strlen(paths[i])+5);
			temp = strcat(strcat(strcpy(temp, paths[i]),"/"),*args);
			
			
			if (stat(temp,&buff)>=0){
				execv(temp, args);	
            }
			i++;
		}
		printf("Invalid command, level up n00b\n");
		exit(0);
}

void parsecmd(char *cmdline, char **args, char delim, char end){
        while (*cmdline != '\0'){
                	if (*cmdline == end){
                        	*cmdline = '\0';
	                        break;
        	        }
	                while (*cmdline == delim || *cmdline == '\t'){
        	                *cmdline = '\0';
				cmdline++;
			}
			if ((char*)args == (*args+256)){
				perror("Too many arguements. Exiting");
				exit(0);
			}
			*args = cmdline;
			args++;
			
                	while (*cmdline != delim && *cmdline != '\t' && *cmdline != end)
                        	cmdline++;
	}
       	*args = '\0';
}
