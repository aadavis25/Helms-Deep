#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <signal.h>

void doexec(char* path, char** args){
	if (execv(path, args) < 0){
                	if (strcmp(strerror(errno), "No such file or directory") == 0)
   	         		printf("Invalid command, level up n00b.\n");
                	else
               			printf("%s", strerror(errno));
		}
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

void execute (char **args, char *command_line){
        pid_t pid;
        pid = fork();
        int status;
        if (pid == -1){
                perror("Fork failed, ABORT!");
                exit(1);
        }
	/*child process*/
        if (pid == 0){
		parsecmd(command_line, args, ' ', '\n');
		int i=0;
		struct stat buff;
		
		/*check for path entered instead of command*/
		if(stat(*args,&buff)>=0){
			doexec(*args,args);
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
				doexec(temp,args);	
                	}
			i++;
		}
		printf("Invalid command, level up n00b\n");
		exit(0);
        }
	/* parent */
        else{
		signal(SIGINT,SIG_IGN);
		signal(SIGTSTP, SIG_IGN);
                if(waitpid(-1,&status, WUNTRACED) == -1)
                        perror("Error while waiting for command to complete\n");
                /*else check the status of the child*/
                else{
                        if (WIFSIGNALED(status) != 0)
				if (WTERMSIG(status) == 2)
					printf("Command interrupted\n");
				else
                                	printf("Error signal : %d\n",WTERMSIG(status));
                        else if (WIFEXITED(status) != 0)
                                ;/*success!*/
			else
				printf("Command stopped\n"); 
		}
		signal(SIGINT,SIG_DFL);
		signal(SIGTSTP,SIG_DFL);
	}
}

int main(){
	char *command;
	char *args[256];
	size_t bytes = 100;

	while(1){
		printf("%s","% ");
		getline(&command, &bytes, stdin);
		if (strcmp(command, "exit\n") == 0)
			exit(0);
		else
			execute(args,command);
	}
}
