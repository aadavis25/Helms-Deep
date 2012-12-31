/* 
 * A server program that provides the service of converting a string
 * to upper case.  It is implemented using the "daemon-service" 
 * model.  In this model, multiple clients can be serviced
 * concurrently by the service.  The server main process is 
 * a simple loop that accepts incoming socket connections, and for 
 * each new connection established, uses fork() to create a child 
 * process that is a new instance of the service process.  This child 
 * process will provide the service for the single client program that 
 * established the socket connection.  
 *
 * Each new instance of the server process accepts input strings from 
 * its client program, converts the characters to upper case and returns 
 * the converted string back to the client.
 *
 * Since the main process (the daemon) is intended to be continuously
 * available, it has no defined termination condition and must be
 * terminated by an external action (Ctrl-C or kill command).
 *
 * The server has one parameter: the port number that will be used
 * for the "welcoming" socket where the client makes a connection.
 */

#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/wait.h>

#include "Socket.h"
#include "ToUpper.h" /* definitions shared by client and server */


/* variables to hold socket descriptors */
  ServerSocket welcome_socket;
  Socket connect_socket;

void toupper_service(void);

int main(int argc, char* argv[])
{
  pid_t spid, term_pid; /* pid_t is typedef for Linux process ID */ 
  int chld_status;
  bool forever = true;

  if (argc < 2)
     {
      printf("No port specified\n");
      return (-1);
     }

  /* create a "welcoming" socket at the specified port */
  welcome_socket = ServerSocket_new(atoi(argv[1]));
  if (welcome_socket < 0)
      {
      printf("Failed new server socket\n");
      return (-1);
     }

  /* The daemon infinite loop begins here; terminate with external action*/
  while (forever)
    {
     /* accept an incoming client connection; blocks the
      * process until a connection attempt by a client.
      * creates a new data transfer socket.
      */
     connect_socket = ServerSocket_accept(welcome_socket);
     if (connect_socket < 0)
        {
         printf("Failed accept on server socket\n");
         exit (-1);
        }
     spid = fork();  /* create child == service process */
     if (spid == -1) 
        {
         perror("fork"); 
         exit (-1);
        }
     if (spid == 0) 
        {/* code for the service process */
	 toupper_service();
         Socket_close(connect_socket);
         exit (0);
        } /* end service process */
     else /* daemon process closes its connect socket */
        {
         Socket_close(connect_socket);
         /* reap a zombie every time through the loop, avoid blocking*/
         term_pid = waitpid(-1, &chld_status, WNOHANG);
        }
    } /* end of infinite loop for daemon process */
}

void toupper_service(void)
{
  int i, c, rc;
  int count = 0;
  bool forever = true;
  char line_data[MAX_LINE]; 

  /* will not use the server socket */
  Socket_close(welcome_socket);

  while (forever)  /* actually, until EOF on connect socket */
     {
      /* get a null-terminated string from the client on the data
       * transfer socket up to the maximim input line size.  Continue 
       * getting strings from the client until EOF on the socket.
       */ 
      for (i = 0; i < MAX_LINE; i++)
          {
           c = Socket_getc(connect_socket);
           if (c == EOF)
	      {
	       printf("Socket_getc EOF or error\n"); 
               return; /* assume socket EOF ends service for this client */           
              }
           else
	      {
               if (c == '\0')
		  {
		   line_data[i] = c;
		   break;
                  }
	       line_data[i] = toupper(c);
              }
          }
      /* be sure the string is terminated if max size reached */
      if (i == MAX_LINE)
	 line_data[i-1] = '\0';

      /* send it back to the client on the data transfer socket */
      count = strlen(line_data) + 1; /* include '\0' in count */
      for (i = 0; i < count; i++)
          {
	   c = line_data[i];
           rc = Socket_putc(c, connect_socket);
           if (rc == EOF)
	      {
	       printf("Socket_putc EOF or error\n");             
               return;  /* assume socket EOF ends service for this client */
              }
          }
     } /* end while loop of the service process */
   return;
}
