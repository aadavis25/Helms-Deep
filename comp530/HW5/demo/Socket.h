typedef int ServerSocket;
typedef int Socket;

/* NOTE:  All the Socket functions will cause
 * the process to block until they complete.
 */

ServerSocket ServerSocket_new(int port);
/* Creates a new server "welcoming" socket prepared
 * to handle incoming connections at the port number
 * specified in the parameter port.  A socket 
 * descriptor for the server socket is returned.  
 * A returned value of -1 indicates a failure.
 */

Socket ServerSocket_accept(ServerSocket sd);
/* Accepts (completes) an incoming connection on the
 * server "welcoming" socket descriptor specified in 
 * the parameter sd.  A new socket descriptor
 * for a data transfer connection is returned.  A 
 * returned value of -1 indicates a failure.
 */

Socket Socket_new(char * host, int port);
/* Creates a new client data connection socket that is
 * accepted by the server at the DNS name specified
 * by the parameter host and the port number specified
 * by the parameter post.  A new socket descriptor is
 * returned that can be used for transferring data 
 * between the client and server.  A returned value
 * of -1 indicates a failure.
 */
   
int Socket_getc(Socket sd);
/* Reads the next character from the data connection
 * socket descriptor specified by the parameter sd
 * and returns it as an unsigned char cast to an int, 
 * or EOF on end of file or error.  Note that the
 * semantics are essentially the same as for the
 * system call getc().
 */


int Socket_putc(int c, Socket sd);
/* writes the character c, cast from an int to an 
 * unsigned char, to the data connection socket 
 * descriptor specified by the parameter sd.  Returns 
 * 1 (success) or EOF on an error.  Note that the 
 * semantics are essentially the same as for the 
 * system call putc() (a difference is that putc()
 * returns the character written instead of 1 for 
 * success.
 */

int Socket_close(Socket sd);
/* Closes the data connection socket specified by the
 * parameter sd.  The program at the other end of the
 * socket connection will receive and EOF or error for
 * any subsequent operation on the socket.
 */

