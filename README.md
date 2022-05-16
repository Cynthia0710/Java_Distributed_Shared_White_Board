# Java_Distributed_Shared_White_Board

## Content
Shared whiteboards allow multiple users to draw simultaneously on a canvas.

1. Multiple users can draw on a shared interactive canvas
2. Your system will support a single whiteboard that is shared between all of the clients.
3. GUI Elements:

        Shapes(line, circle, rectangle and oval)
    
        Free draw and erase
    
        Text inputting
    
        Choose favorite color to draw
     
        New drawing
    
        Chat Window
    
        A “File” menu with new, open, save, saveAs and close(only the manager can control this)
  
## Excute
The first user creates a whiteboard and becomes the whiteboard’s manager:

`java –jar CreateWhiteBoard.jar <serverIPAddress> <serverPort>`

Other users can ask to join the whiteboard application any time by inputting server’s IP address and port number:

`java –jar JoinWhiteBoard.jar <serverIPAddress> <serverPort>`

    
