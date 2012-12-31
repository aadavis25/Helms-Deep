#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

void parsecmd(char *cmdline, char **args){
        /* first arg, the command*/
        *args = cmdline;
        args++;
        /*rest of the args*/
        while (*cmdline != '\0'){
                if (*cmdline == '\n'){
                        *cmdline = '\0';
                        break;
                }
                if (*cmdline == ' '){
                        *cmdline = '\0';
                        cmdline++;
                        *args = cmdline;
                        args++;
                }
                else
                        cmdline++;
        }
        *args = '\0';
}
void execute (char **args, char *command_line){
        pid_t pid;
        pid = fork();
        int status;
        if (pid == -1){
                perror("Fork failed, ABORT!");
                exit(1);
        }
        if (pid == 0){/*child process*/
		parsecmd(command_line, args);
               		if (execvp(args[0], args) < 0){
                        if (strcmp(strerror(errno), "No such file or directory") == 0)
                                printf("Invalid command, n00b.");
                        else
                                printf("%s", strerror(errno));
                        printf("%c" , '\n');
                }
                exit(0);

        }
        else{/* parent */
                if(wait(&status) == -1)
                        perror("Error while waiting for command to complete");
                /*else check the status of the child*/
                else{
                        if (WIFSIGNALED(status) != 0)
                                printf("Error signal : %d\n",WTERMSIG(status));
                        else if (WIFEXITED(status) != 0)
                                ;/*success!*/
			else
				printf("Error in evaluating command."); 
		}
	}
}

int main(){
	char *command;
	char *args[256];
	size_t bytes = 100;
	int ret;

	char *path; 
	path = getenv("PATH");
 	printf("%c",path);

	while(1){
		printf("% ");
		getline(&command, &bytes, stdin)
		if (strcmp(*command, "exit\n") == 0)
			exit(0);
		else
			execute(args,command);
	}
}
