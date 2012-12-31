function newImage = scrollDown(image, pixels)
% function newImage = scrollDown(image, pixels)
% Move binary image down the specified number of pixels, 
% filling in with zeros.  If omitted, pixels = 1. 
% Example: If  X = [0 1 0    then scrollDown(X,1) is [0 0 0
%                   0 1 1                             0 1 0
%                   1 0 1]                            0 1 1]

imsize = size(image);
row = imsize (:,1);
column = imsize (:,2);
subImage = image (1:(row - pixels),:);
addImage = false(pixels,column);
newImage = [addImage;subImage];
