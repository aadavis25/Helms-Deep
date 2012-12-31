function newImage = scrollUp(image, pixels)
% function newImage = scrollUp(image, pixels)
% Move the binary image up the specified number of pixels,
% filling in with zeros.  If omitted, pixels = 1.
% Example: If  X = [0 1 0    then scrollUp(X,1) is [0 1 1
%                   0 1 1                           1 0 1
%                   1 0 1]                          0 0 0]

imsize = size(image);
row = imsize (:,1);
column = imsize (:,2);
subImage = image ((1+pixels):row,:);
addImage = false(pixels,column);
newImage = [subImage;addImage];
