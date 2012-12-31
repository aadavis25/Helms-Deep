%% Aaron Davis
%9/1/2010
%(I'm not sure what to type for the pledge, I couldn't find it) I honorably pledge that I did
%this assignment myself and did not use any sketchy, cheating resources.  

%% This is Assignment #1 which is basic MATLAB image manipulation i.e.
%% color mapping, combining, subtracting, etc.

%% Displaying an Image
%Show Directory information, then load and display the left image.
dir;
leftimg = imread('Left.png'); 
imagesc(leftimg); 
axis image; 
colormap(gray(256));

%% Creating the difference image
%Load anothe image, convert both images to doubles, and display a
%difference image
rightimg = imread('right.png');
leftimg = double(leftimg);
rightimg = double(rightimg);
imagesc(leftimg - rightimg);
axis image;
colormap(gray(256));

%% Image morphing
%%Loading the first image
%Load and display the first picture
img01 = imread('head01.png');
imagesc(img01);
axis image;

%% Loading the second picture
%Load and display the second picture
img02 = imread('head02.png');
imagesc(img02);
axis image;

%% Compute and display the sverage image
% Compute and display the average image
img03 = 0.5*img01 + 0.5*img02;
imagesc(img03);
axis image;

%% Comments
%This assignment was pretty simple and straightfoward (since we had
%step-by-step instruction). I had a little bit of an issue getting the 
%images to display since I saved the script file in a different directory 
%than the images. It came up with an error message and I hit a button and it worked. I'm 
%not sure whether or not we are supposed to see both product images, but I 
%guess not since it doesn't happen. I'm still a little bit unsure 
%about the whole "programming" thing since I'm a "noob" at writing code, 
%so this helped solidify some of the logistics for me, help me learn some commands etc.