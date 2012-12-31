#include <stdlib.h>
#include <stdio.h>
#include <ctype.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/time.h>
#include <netinet/tcp.h>        
#include <fcntl.h>
#include <sys/types.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>
#include <math.h>

#include "Socket.h"

/* Server side */
ServerSocket ServerSocket_new(int port)
/* Creates a new server "welcoming" socket prepared
 * to handle incoming connections at the port number
 * specified in the parameter port.  A socket 
 * descriptor for the server socket is returned.  
 * A returned value of -1 indicates a failure.
 */
{
 int accept_fd;
 struct   sockaddr_in  serv_addr;

 /* initialize the accepting socket */
 accept_fd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
 if (accept_fd == -1)
    { 
     perror("ServerSocket_new:");
     return (-1);
    }

 bzero((char *) &serv_addr, sizeof(serv_addr));
 serv_addr.sin_family       = AF_INET;
 serv_addr.sin_addr.s_addr  = htonl(INADDR_ANY);
 serv_addr.sin_port         = htons(port);

 if (bind(accept_fd, (struct sockaddr *) &serv_addr,
          sizeof(serv_addr)) < 0) 
    {
     perror("ServerSocket_new:");
     return (-1);
    }

 if (listen(accept_fd, 10) < 0) 
    {
     perror("ServerSocket_new:");
     return (-1);
    }
 return accept_fd;
}

Socket ServerSocket_accept(ServerSocket sd)
/* Accepts (completes) an incoming connection on the
 * server "welcoming" socket descriptor specified in 
 * the parameter sd.  A new socket descriptor
 * for a data transfer connection is returned.  A 
 * returned value of -1 indicates a failure.
 */
{
 int fd;
 struct   sockaddr_in  cli_addr;
 int clilen;

 clilen = sizeof(cli_addr);
 fd = accept(sd, (struct sockaddr *) &cli_addr, &clilen);
 if (fd < 0)
    {
     perror("ServerSocket_accept");
     return (-1);
    }
 return fd;
}

/* Client side */

Socket Socket_new(char * host, int port)
/* Creates a new client data connection socket that is
 * accepted by the server at the DNS name specified
 * by the parameter host and the port number specified
 * by the parameter post.  A new socket descriptor is
 * returned that can be used for transferring data 
 * between the client and server.  A returned value
 * of -1 indicates a failure.
 */
{
 int fd;
 int status;

 struct   sockaddr_in  serv_addr;
 struct   hostent *addr;

 bzero((char *)&serv_addr, sizeof(serv_addr));
 if (atoi(host) > 0 )  /* ASSUME it is a dotted-decimal address */
     serv_addr.sin_addr.s_addr = inet_addr(host);
 else 
     {                        
      /* Look up hostname*/
      if ((addr = gethostbyname(host)) == NULL )
          {
	   errno = h_errno;
	   perror("Socket_new:");
           return (-1);
          }
     }
 bcopy (addr->h_addr,
       (char *)&(serv_addr.sin_addr), addr->h_length);
 serv_addr.sin_family = AF_INET;
 serv_addr.sin_port = htons(port);

 /* get a socket to use */
 fd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
 if (fd == -1)
    { 
     perror("Socket_new:");
     return (-1);
    }
 /* use it to connect to server */
 status = connect(fd, (struct sockaddr *) &serv_addr,
		      sizeof(struct sockaddr_in));
 if (status == -1)
    { 
     perror("Socket_new:");
     return (-1);
    }
 return fd;
}

int Socket_getc(Socket sd)
/* Reads the next character from the data connection
 * socket descriptor specified by the parameter sd
 * and returns it as an unsigned char cast to an int, 
 * or EOF on end of file or error.  Note that the
 * semantics are essentially the same as for the
 * system call getc().
 */
{
 int bytes_read;
 unsigned char read_buffer[2];

 bytes_read = read(sd, &read_buffer[0], 1); 
  
 if (bytes_read < 0) 
    perror("Socket_getc:");
                      
 if (bytes_read <= 0)
    return EOF;
 else
    return ((int) read_buffer[0]);
}

int Socket_putc(int c, Socket sd)
/* writes the character c, cast from an int to an 
 * unsigned char, to the data connection socket 
 * descriptor specified by the parameter sd.  Returns 
 * 1 (success) or EOF on an error.  Note that the 
 * semantics are essentially the same as for the 
 * system call putc() (a difference is that putc()
 * returns the character written instead of 1 for 
 * success.
 */
{
 int bytes_written;
 unsigned char send_buffer[2];

 send_buffer[0] = (unsigned char) c;

 bytes_written = write(sd, &send_buffer[0], 1);

 if (bytes_written <0)
    perror("Socket_putc:");

 if (bytes_written <= 0)
    return EOF;
 else
    return (1);
}

int Socket_close(Socket sd)
/* Closes the data connection socket specified by the
 * parameter sd.  The program at the other end of the
 * socket connection will receive and EOF or error for
 * any subsequent operation on the socket.
 */
 {
   close(sd);
 }

                  
 
