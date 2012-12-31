function [ BoundaryImage ] = FindBoundary( image )
%Finds boundary of input binary images my scrolling image 1 pixel in all 4 carnal
%directions and using logical operators to compare the 4 scrolled images.

LI = scrollLeft (image,1);
RI = scrollRight (image,1);
UI = scrollUp(image,1);
DI = scrollDown(image,1);
 BoundaryImage = (~UI & image) + (~LI & image) + (~RI & image) + (~DI & image);


end

