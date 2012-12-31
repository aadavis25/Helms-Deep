function [ ErodedImg ] = Untitled( image )
%ErodeImage uses FindBoundary to locate the boundary of and image and then
%removes the boundary outputting an "eroded image."

boundary = FindBoundary(image);
ErodedImg = image - boundary;


end

