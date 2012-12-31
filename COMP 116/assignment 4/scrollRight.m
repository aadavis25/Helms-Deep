function newImage = scrollRight(image, pixels)
% function newImage = scrollRight(image, pixels)
% Move the binary image to the right the specified number of pixels,
% filling in with zeros.  If omitted, pixels = 1.
% Example: If  X = [0 1 0    then scrollRight(X,1) is [0 0 1
%                   0 1 1                              0 0 1
%                   1 0 1]                             0 1 0]

imsize = size(image);
row = imsize (:,1);
column = imsize (:,2);
subImage = image (:,1:(column-pixels));
addImage = false(row,pixels);
newImage = [addImage subImage];

