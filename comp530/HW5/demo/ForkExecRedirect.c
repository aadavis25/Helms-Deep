/* 
 * Program to emulate the core operations in a shell program.
 * The program uses the OS system call fork() to create a child
 * process which will do the call to "exec" a "command".  Before
 * creating the child, the program constructs a file name for
 * a temporary file that will be known to the parent and child.
 * Before "execing", the child process dynamically redirects its
 * stdout to this temporary file using the OS system call freopen().
 * It uses the OS system call execvp() to replace its process image
 * (executable and stack) with a new image (executable and stack).
 * The new code runs with the same process id and file descriptors
 * (e.g., stdin and stdout) as the child process.  The child
 * process terminates when the new executable returns or exits.
 * The parent process waits (using waitpid()) for the exit and then
 * opens and reads the file for redirected stdout displaying the 
 * lines on its stdout.  NOTE THAT SINCE THE freopen() IS EXECUTED
 * BY THE CHILD PROCESS, THE sdtout FOR THE PARENT IS STILL BOUND
 * TO THE DISPLAY AND NOT A FILE.  Before terminating, the parent
 * process deletes the temporary file using a call to remove().
 *
 * This program has three positional parameters in the following
 * order:
 *  - The file name of the executable to be run (must be in the set
 *    of directories named in the PATH environment variable which
 *    is searched for the named file, or be an explicit full pathname).
 *  - Parameter string 1 to be passed to the new program as positional
 *    parameter 1
 *  - Parameter string 2 to be passed to the new program as positional
 *    parameter 2
 * NOTE: string 2 or both string 1 and string 2 may be absent. This 
 * program cannot be used to execute a new program that REQUIRES more 
 * than two parameters.
 */

#define MAX_LINE 1024
#define MAX_TMP 100
#define MAX_ARGS 4  /* allows program name + 3 positional parameters */
#define MIN_ARGS 2  /* must have at least 1 positional parameter */
#define NUM_PARMS 4  /* allows program name + 2 parameters + NULL */
#define ERR_RETURN -1

#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdarg.h>

int main(int argc, char *argv[])
{
  pid_t cpid, term_pid; /* type for Linux process id */
  int chld_status; 
  int i;
  int rc;
  char *parms_array[NUM_PARMS];  /* arrary of pointers to strings */
  unsigned char new_line[MAX_LINE];
  unsigned char tmp_name[MAX_TMP];
  unsigned char id_str[MAX_TMP];
  int id;

  /* variable names for file "handles" */
  FILE *tmpFP;
  FILE *fp;

  /* validity check parameter count */
  if ((argc > MAX_ARGS) ||
      (argc < MIN_ARGS))
    {
      printf("Bad parameter count %d\n", argc);
      printf("Positional parameters: executable name, [parm 1 [parm 2]]\n");
      exit (ERR_RETURN);
    }

  /* The positional parameters passed to this program as pointers to 
   * strings can just be passed on to execvp which requires a
   * NULL-terminated array of pointers to strings.  By convention, the
   * first entry in the array is the file name to be executed, and the
   * next non-null pointers are for positional parameters to that program.
   */

  for (i = 0; i < (argc - 1); i++)
       parms_array[i] = argv[i+1];
  parms_array[i] = NULL;

  /* get the parent process ID and use it to create a file name for the
   * temporary file with the format "tmpxxxxx" where xxxxx is the ID 
   */

  id = (int) getpid();
  sprintf(id_str, "%d", id);
  strcpy(tmp_name,"tmp");
  strcat(tmp_name, id_str);    

  /* create child process, report error if present and exit */  
  cpid = fork(); 
  if (cpid == -1) 
     {
      perror("fork"); /* perror creates description of OS error codes */
      exit(ERR_RETURN); 
     }

  /* conditional execution of statements depending on whether the code is
   * if running as the parent or child process.
   */

  if (cpid == 0) 
     { /* the child process */
      /* dynamically redirect stdout to the named temporary 
       * file open for writing
       */ 
      fp = freopen(tmp_name, "w", stdout); 

      /* do the OS execvp() call, if there is an error return, report it.  Otherwise
       * this call will not return and the new image will execute as this process ID.
       */

      rc = execvp(parms_array[0], parms_array);
      if (rc == -1) 
         {
          perror("execvp"); 
          exit(-1); 
         }
     }  /* end of code that can be executed by the child process */
  else 
     {/* code executed by the parent process */
      /* wait for the child to terminate and determine its exit status */
      /* this is necessary to prevent the accumulation of "Zombie" processes */

      term_pid = waitpid(cpid, &chld_status, 0);
      if (term_pid == -1) 
          perror("waitpid"); 
      else
	 {
          if (WIFEXITED(chld_status)) 
	     printf("PID %d exited, status = %d\n", cpid, WEXITSTATUS(chld_status));
          else
	     printf("PID %d did not exit normally\n", cpid);
         }

      if ((tmpFP = fopen (tmp_name, "r")) == NULL) {
	  fprintf (stderr, "error opening tmp file\n");
	  exit (-1);
         }
      while (!feof (tmpFP)) {
         /* Get line from file */
         if (fgets (new_line, sizeof(new_line), tmpFP) == NULL)
            break;
         printf("%s", new_line);
       }
      /* delete the temporary file */
      remove(tmp_name);
      printf("Parent process terminating.\n");
     } /* end of code that can be executed by the parent process */
 exit(0);
}
