function [ DialatedImage ] = DialateImage( image )
%DialateImage finds the outside of the edge of an image and adds it to the
%original image by comparing the 4 carnal scolling functions to the
%original image.


LI = scrollLeft (image,1);
RI = scrollRight (image,1);
UI = scrollUp(image,1);
DI = scrollDown(image,1);
 outedge = (UI & ~image) + (LI & ~image) + (RI & ~image) + (DI & ~image);
 DialatedImage = outedge + image;
 

end

